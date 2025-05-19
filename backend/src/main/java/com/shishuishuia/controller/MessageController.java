package com.shishuishuia.controller;

import com.shishuishuia.Service.MessageService;
import com.shishuishuia.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 晓梦之尘
 * more about author: www.shuishuia.cn
 * @ClassName MessageController
 * @Description TODO
 * @date 2025/5/11 20:07
 * @Version 1.0
 */
@RestController("message/")
public class MessageController {

@Autowired
private MessageService messageService;

    @GetMapping("messagelist/{sendid}")
    public Result getMessageListBySenderID(@PathVariable int sendid){
        Result messageListById = messageService.getMessageListById(sendid);
        return messageListById;
    };
}
