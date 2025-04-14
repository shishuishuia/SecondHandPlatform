package com.shishuishuia.backend;

import com.shishuishuia.mapper.UserMapper;
import com.shishuishuia.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BackendApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    void contextLoads() {
    }

    @Test
    void connectMysql() {
        System.out.println("heloow");
    }

    @Test
    void loginTest(){

    }
}
