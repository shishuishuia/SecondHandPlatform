package com.shishuishuia.mapper;

import com.shishuishuia.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
/**
* @ClassName UserMapperTest
* @Description TODO
* @date 2025/4/7 14:11
* @Version 1.0
* @author 晓梦之尘
* more about author: www.shuishuia.cn
*/
@SpringBootTest
class UserMapperTest {

    @Autowired
    private UserMapper userMapper;


    @Test
    void loginTest(){
        User login = userMapper.login("zxc","zxc");
        System.out.println(login);
    }


}