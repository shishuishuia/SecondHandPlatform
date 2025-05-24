package com.shishuishuia.controller;

import com.shishuishuia.Service.MessageService;
import com.shishuishuia.pojo.Message;
import com.shishuishuia.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author 晓梦之尘
 * more about author: www.shuishuia.cn
 * @ClassName MessageController
 * @Description TODO
 * @date 2025/5/11 20:07
 * @Version 1.0
 */

@CrossOrigin
@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @GetMapping("/chatuserlist/{id}")
    public Result getChattingList(@PathVariable int id){
        System.out.println(id);
        Result userListBySenderId = messageService.getUserListBySenderId(id);
//        return Result.ok(id);
        return userListBySenderId;
    }

    @GetMapping("messagelist/{sendid}/{receiverId}")
    public Result getMessageListBySenderID(@PathVariable int sendid, @PathVariable int receiverId){
        System.out.println(receiverId);
        Result messageListById = messageService.getMessageListById(sendid,receiverId);
        return messageListById;
    };

    @PostMapping("/send")
    public Result postMessage(@RequestBody Message message){
        System.out.println(message);
        int i = messageService.saveMessage(message);
        return Result.ok(i);
    }
}
