package com.shishuishuia.controller;

import com.shishuishuia.pojo.User;
import com.shishuishuia.utils.Result;
import org.springframework.web.bind.annotation.*;

/**
 * @author 晓梦之尘
 * more about author: www.shuishuia.cn
 * @ClassName UserController
 * @Description TODO
 * @date 2025/3/23 19:16
 * @Version 1.0
 */

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {

    @ResponseBody
    @PostMapping("/login")
    public String login(@RequestBody User user){
        System.out.println(user.toString());
        return "nihao";
    }

//    @PostMapping("/register")
//    public Result register(@RequestBody User user) {
//
//        return Result.ok("a");
//
//    }
}
