package com.shishuishuia.controller;

import com.shishuishuia.Service.UserService;
import com.shishuishuia.pojo.User;
import com.shishuishuia.utils.JwtHelper;
import com.shishuishuia.utils.Result;
import com.shishuishuia.utils.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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


    @Autowired
    private JwtHelper jwtHelper;

    @PostMapping("/login")
    public Result login(@RequestBody User user){

        String username = user.getUsername();
        String password = user.getPassword();

        Result result = userService.login(username, password);
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

    @GetMapping("/getdetail/{userid}")
    public Result getGetail(@PathVariable int userid){
        Result userInfoById = userService.getUserInfoById(userid);
        return Result.ok(userInfoById);
    }
    @PutMapping("/uprofile")
    public Result updateProfile(
            @RequestPart(required = true) String token,
            @RequestPart(required = false) String nickname,
            @RequestPart(required = false) String phone,
            @RequestPart(required = false) MultipartFile avatar)
    {
        Long userId = jwtHelper.getUserId(token);
        Result userInfoById = userService.getUserInfoById(Math.toIntExact(userId));
        if(userInfoById.getCode()==200){
            Result result = userService.updateUserInfo(Math.toIntExact(userId), nickname, phone, avatar);
            return result;
        } else return Result.build(Map.of("message","用户错误"), ResultCodeEnum.OTHERMISTAKE);

    }
}
