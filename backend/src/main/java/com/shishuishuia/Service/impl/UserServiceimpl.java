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
            data.put("token",token);
            data.put("username",user.getUsername());
            data.put("photo", user.getPhone());
            data.put("gender", user.getGender());
            data.put("name",user.getName());
            return Result.ok(data);
        }
        return Result.build(null,ResultCodeEnum.PASSWORD_ERROR);

    }

    @Override
    public Result register(User user) {
        User user1 = userMapper.findbyUsername(user.getUsername());
        if(user1.getUsername() == user.getUsername()){
            return null;
        }
        return null;
    }
}
