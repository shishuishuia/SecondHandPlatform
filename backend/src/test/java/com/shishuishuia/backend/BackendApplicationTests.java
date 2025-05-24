package com.shishuishuia.backend;

import com.shishuishuia.mapper.UserMapper;
import com.shishuishuia.pojo.User;
import com.shishuishuia.utils.FileStorageService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BackendApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private FileStorageService fileStorageService;

    @Test
    void contextLoads() {
        boolean b = fileStorageService.deleteFileByUrl("uploads/4-25/66589f6f-4afb-438b-9145-39def86131f6.jpg");
        if(b) {
            System.out.println("yes");
        }else System.out.println("no");
    }


    @Test
    void connectMysql() {
        System.out.println("heloow");
    }

    @Test
    void loginTest(){

    }
}
