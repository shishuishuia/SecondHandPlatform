package com.shishuishuia.controller;

import com.shishuishuia.Service.UserService;
import com.shishuishuia.pojo.User;
import com.shishuishuia.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Result login(@RequestBody User user){

        String username = user.getUsername();
        String password = user.getPassword();

        System.out.println( username+password);
        Result result = userService.login(username, password);
        System.out.println(result);
        return result;
    }

    @GetMapping("/userinfo/{userId}")
    public Result getUserinfo(@PathVariable int userId){
        System.out.println("getinfo"+userId);
        Result userInfoById = userService.getUserInfoById(userId);
        return userInfoById;
    }

    @PostMapping("/register")
    public Result register(@RequestBody User user) {
        System.out.println("controller"+user);
        Result result = userService.register(user);
        return result;
    }

    @GetMapping("/getdetail")
    public Result getGetail(){
        return Result.ok(1);
    }
}
