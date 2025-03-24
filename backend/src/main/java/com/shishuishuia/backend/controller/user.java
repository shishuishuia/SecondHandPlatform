package com.shishuishuia.backend.controller;

import com.shishuishuia.backend.utils.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 晓梦之尘
 * more about author: www.shuishuia.cn
 * @ClassName user
 * @Description TODO
 * @date 2025/3/24 16:43
 * @Version 1.0
 */

@RestController
@RequestMapping("/user")
public class user {

    @GetMapping("/login")
    public Result login(){
        return new Result<>();
    }
}
