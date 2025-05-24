package com.shishuishuia.Service.impl;

import com.shishuishuia.Service.UserService;
import com.shishuishuia.mapper.UserMapper;
import com.shishuishuia.pojo.User;
import com.shishuishuia.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
    private MD5Util md5Util;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private JwtHelper jwtHelper;

    @Override
    public Result login(String username, String password) {

        System.out.println("zxc:  "+md5Util.encrypt("zxc"));
        System.out.println("123123:  "+md5Util.encrypt("123123"));
        System.out.println("testzxc:  "+md5Util.encrypt("testzxc"));
        System.out.println("service::: "+username+password);
        User user = userMapper.findbyUsername(username);

        if (user == null) return Result.build(null, ResultCodeEnum.USERNAME_ERROR);

        if(user.getPassword().equals(md5Util.encrypt(password))){
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

        if(user1 == null) {
            user.setAvatar("/uploads/5-23/2aa0fb67-6bfd-479e-89c4-142ef99d2b35.jpg"); //默认头像
            user.setName(user.getUsername());   //默认名字
            String pass = md5Util.encrypt(user.getPassword());
            user.setPassword(pass);
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

    @Override
    public Result updateUserInfo(int id,String name, String phone, MultipartFile avatar) {
        try {
            String s = fileStorageService.storeFile(avatar);
            User user = new User();
            user.setAvatar(s);
            user.setName(name);
            user.setPhone(phone);
            user.setId(id);
            int i = userMapper.updateUserInfo(user);
            if(i>0) return Result.ok(i);
            else return Result.build(Map.of("message","修改失败"),ResultCodeEnum.OTHERMISTAKE);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
