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

    @ResponseBody
    @PostMapping("/login")
    public Result login(@RequestBody User user){

        String username = user.getUsername();
        String password = user.getPassword();

        User login = userService.login(username, password);
        System.out.println(login);
        return Result.ok("yes");
    }

}
