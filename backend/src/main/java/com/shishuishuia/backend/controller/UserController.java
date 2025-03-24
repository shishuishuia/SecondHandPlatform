package com.shishuishuia.backend.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 晓梦之尘
 * more about author: www.shuishuia.cn
 * @ClassName UserController
 * @Description TODO
 * @date 2025/3/23 19:16
 * @Version 1.0
 */


@RestController
@RequestMapping("user")
public class UserController {
    @PostMapping
    public String login(){
        return "1";
    }
}
