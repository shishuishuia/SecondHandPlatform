package com.shishuishuia.utils;
import com.alibaba.fastjson.JSON;
import com.shishuishuia.ws.ResultMessage;

import java.util.Set;

public class MessageUtils {

    /**
     * 构建消息
     * @param isSystem 是否是系统消息
     * @param fromId 发送者ID
     * @param message 消息内容
     * @return JSON格式的消息
     */
    public static String getMessage(boolean isSystem, String fromId, Object message) {
        ResultMessage resultMessage = new ResultMessage();
        resultMessage.setIsSystem(isSystem);
        resultMessage.setFromId(fromId);
        resultMessage.setMessage(message);
        return JSON.toJSONString(resultMessage);
    }

    /**
     * 构建在线用户列表消息
     * @param onlineUserIds 在线用户ID集合
     * @return JSON格式的在线用户列表消息
     */
    public static String buildOnlineUserMessage(Set<Integer> onlineUserIds) {
        return getMessage(true, null, onlineUserIds);
    }

    /**
     * 构建普通消息
     * @param fromId 发送者ID
     * @param content 消息内容
     * @return JSON格式的普通消息
     */
    public static String buildCommonMessage(String fromId, String content) {
        return getMessage(false, fromId, content);
    }
}