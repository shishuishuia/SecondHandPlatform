package com.shishuishuia.Service.impl;

import com.shishuishuia.Service.MessageService;
import com.shishuishuia.mapper.MessageMapper;
import com.shishuishuia.pojo.Message;
import com.shishuishuia.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public Result getMessageListById(int id) {
        List<Message> messageBySenderId = messageMapper.getMessageBySenderId(id);
        return Result.ok(messageBySenderId);
    }
}
