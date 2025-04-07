package com.shishuishuia.Service.impl;

import com.shishuishuia.Service.UserService;
import com.shishuishuia.mapper.UserMapper;
import com.shishuishuia.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public User login(String username, String password) {
        User login = userMapper.login(username,password);
        return login;
    }
}
