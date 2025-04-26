package com.shishuishuia.Service.impl;

import com.shishuishuia.Service.UserService;
import com.shishuishuia.mapper.UserMapper;
import com.shishuishuia.pojo.User;
import com.shishuishuia.utils.JwtHelper;
import com.shishuishuia.utils.Result;
import com.shishuishuia.utils.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 晓梦之尘
 * more about author: www.shuishuia.cn
 * @ClassName UserServiceimpl
 * @Description TODO
 * @date 2025/3/27 21:54
 * @Version 1.0
 */

@Service
public class UserServiceimpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private JwtHelper jwtHelper;

    @Override
    public Result login(String username, String password) {

        System.out.println("service::: "+username+password);
        User user = userMapper.findbyUsername(username);

        if (user == null) return Result.build(null, ResultCodeEnum.USERNAME_ERROR);

        if(user.getPassword().equals(password)){
            //登陆成功
            String token = jwtHelper.createToken((long) user.getId());
            Map data = new HashMap();
            System.out.println("UserServicelogin User:  "+user);
            data.put("id",user.getId());
            data.put("token",token);
            data.put("username",user.getUsername());
            data.put("phone", user.getPhone());
            data.put("photo",user.getAvatar());
            data.put("gender", user.getGender());
            data.put("name",user.getName());
            return Result.ok(data);
        }
        return Result.build(null,ResultCodeEnum.PASSWORD_ERROR);

    }

    @Override
    public Result register(User user) {
        User user1 = userMapper.findbyUsername(user.getUsername());
        System.out.println(user1);
        if(user1 == null) {
            user.setAvatar("https://www.keaitupian.cn/cjpic/frombd/0/253/1869181623/3579598273.jpg"); //默认头像
            user.setName(user.getUsername());   //默认名字
            System.out.println("service"+user);
            int insert = userMapper.insert(user);
            return Result.ok(insert);

        }
        else
            return Result.build(null, ResultCodeEnum.USERNAME_USED);


    }

    @Override
    public Result getUserInfoById(int id) {

        User user = userMapper.getUserInfoById(id);
        if(user!=null){
            Map data = new HashMap();
            data.put("id",user.getId());
            data.put("username",user.getUsername());
            data.put("phone", user.getPhone());
            data.put("photo",user.getAvatar());
            data.put("gender", user.getGender());
            data.put("name",user.getName());
            data.put("avatar",user.getAvatar());
            data.put("location",user.getLocation());
            data.put("transactionnumber",user.getTransactionnumber());
            data.put("registered",user.getRegistered());
            return Result.ok(data);
        }
        return Result.build(Map.of(
                "success", false,
                "message", "没找到用户 "
        ),ResultCodeEnum.OTHERMISTAKE);
    }
}
