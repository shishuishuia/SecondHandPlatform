package com.shishuishuia.backend.ws;

import com.alibaba.fastjson.JSON;
import com.shishuishuia.backend.config.GetHttpSessionConfig;
import com.shishuishuia.backend.pojo.Message;
import com.shishuishuia.backend.utils.MessageUtils;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.*;
import jakarta.websocket.server.ServerEndpoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 晓梦之尘
 * more about author: www.shuishuia.cn
 * @ClassName ChatEndpooint
 * @Description TODO
 * @date 2025/3/23 19:19
 * @Version 1.0
 */

@Component
@ServerEndpoint(value = "/chat", configurator = GetHttpSessionConfig.class)
public class ChatEndpooint {

    private static final Map<String ,Session> onlineUsers = new ConcurrentHashMap<>();
    private HttpSession httpSession;

    /*
       建立WebSocket连接后被调用
     */
    @OnOpen
    public void onOpen(Session session, EndpointConfig config){
        //1.将session保存
        this.httpSession = (HttpSession) config.getUserProperties().get(HttpSession.class.getName());
        String user = (String) httpSession.getAttribute("user");
        onlineUsers.put(user, session);

        //2.广播消息。 将登录状态推送给所有用户
        String message = MessageUtils.getMessage(true, null, getFriends());
        broadcastAllUsers(message);

    }

    public Set getFriends(){
        Set<String> set = onlineUsers.keySet();
        return set;
    }

    private void broadcastAllUsers(String message){
        try {
        //遍历Map集合
            Set<Map.Entry<String,Session>> entries = onlineUsers.entrySet();
            for(Map.Entry<String, Session> it : entries) {
                Session session = it.getValue();
                session.getBasicRemote().sendText(message);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /*
    浏览器发送消息到服务器，该方法被调用
     */
    @OnMessage
    public void onMessage(String message){
        try {
            //1. 将消息推送给指定用户
            Message message1 = JSON.parseObject(message, Message.class);
            //获取消息接收方的用户名
            String toName = message1.getToName();
            String mess = message1.getMessage();

            Session session = onlineUsers.get(toName);
            String user = (String) httpSession.getAttribute("user");
            String message2 = MessageUtils.getMessage(false, user, mess);
            session.getBasicRemote().sendText(message2);


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    /*
        断开WebSocket连接时自动被调用
     */
    @OnClose
    public void onClose(Session session){
        //1.从 onlineUsers中删除当前用户的session对象
        String user = (String) httpSession.getAttribute("user");
        onlineUsers.remove(user);

        //2. 通知其他用户，当前用户下线了

        String message = MessageUtils.getMessage(true,null,getFriends());
        broadcastAllUsers(message);

    }
}
