package com.shishuishuia.ws;

import com.alibaba.fastjson.JSON;
import com.shishuishuia.Service.MessageService;
import com.shishuishuia.config.GetHttpSessionConfig;
import com.shishuishuia.pojo.Message;
import com.shishuishuia.utils.MessageUtils;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.*;
import jakarta.websocket.server.ServerEndpoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Component
@ServerEndpoint(value = "/chat", configurator = GetHttpSessionConfig.class)
public class ChatEndpooint {

    private static final Map<Integer, Session> onlineUsers = new ConcurrentHashMap<>();
    private HttpSession httpSession;
    @Autowired
    private MessageService messageService;
    /*
       建立WebSocket连接后被调用
     */
    @OnOpen
    public void onOpen(Session session, EndpointConfig config) {
        //1.将session保存
        this.httpSession = (HttpSession) config.getUserProperties().get(HttpSession.class.getName());
        Integer userId = (Integer) httpSession.getAttribute("userId");
        onlineUsers.put(userId, session);

        //2.广播消息。 将登录状态推送给所有用户
        String message = MessageUtils.getMessage(true, null, getOnlineUserIds());
        broadcastAllUsers(message);
    }

    public Set<Integer> getOnlineUserIds() {
        return onlineUsers.keySet();
    }

    private void broadcastAllUsers(String message) {
        try {
            //遍历Map集合
            Set<Map.Entry<Integer, Session>> entries = onlineUsers.entrySet();
            for (Map.Entry<Integer, Session> it : entries) {
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
    public void onMessage(String message) {
        try {
            // 解析消息
            com.shishuishuia.pojo.Message msgData = JSON.parseObject(message, com.shishuishuia.pojo.Message.class);
            Integer fromId = msgData.getSenderId();
            Integer toId = msgData.getReceiverId();
            String content = msgData.getContent();

            // 保存消息到数据库
            Message dbMessage = new Message();
            dbMessage.setSenderId(fromId);
            dbMessage.setReceiverId(toId);
            dbMessage.setContent(content);
            messageService.saveMessage(dbMessage);

            // 发送消息给接收者
            Session toSession = onlineUsers.get(toId);
            if (toSession != null) {
                String message2 = MessageUtils.getMessage(false, fromId.toString(), content);
                toSession.getBasicRemote().sendText(message2);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /*
        断开WebSocket连接时自动被调用
     */
    @OnClose
    public void onClose(Session session) {
        //1.从 onlineUsers中删除当前用户的session对象
        Integer userId = (Integer) httpSession.getAttribute("userId");
        onlineUsers.remove(userId);

        //2. 通知其他用户，当前用户下线了
        String message = MessageUtils.getMessage(true, null, getOnlineUserIds());
        broadcastAllUsers(message);
    }
}