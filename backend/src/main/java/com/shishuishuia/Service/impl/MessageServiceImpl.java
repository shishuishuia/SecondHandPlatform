package com.shishuishuia.Service.impl;

import com.shishuishuia.Service.MessageService;
import com.shishuishuia.mapper.MessageMapper;
import com.shishuishuia.pojo.Message;
import com.shishuishuia.pojo.User;
import com.shishuishuia.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author 晓梦之尘
 * more about author: www.shuishuia.cn
 * @ClassName MessageServiceImpl
 * @Description TODO
 * @date 2025/5/11 20:21
 * @Version 1.0
 */

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageMapper messageMapper;
    @Override
    public Result getMessageListById(int id,int receiverId) {
        List<Message> messageBySenderId = messageMapper.getMessageBySenderId(id,receiverId);
        return Result.ok(messageBySenderId);
    }

    @Override
    public int saveMessage(Message message) {

        return messageMapper.saveMessage(message);
    }

    @Override
    public Result getUserListBySenderId(int id) {

        List<User> userlistBySenderId = messageMapper.getUserlistBySenderId(id);
        List<User> userlistByReceiverId = messageMapper.getUserlistByReceiverId(id);
        List<User> union = Stream.concat(userlistBySenderId.stream(),userlistByReceiverId.stream()).distinct().collect(Collectors.toList());

        return Result.ok(union);
    }
}
