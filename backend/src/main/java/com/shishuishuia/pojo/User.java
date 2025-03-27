package com.shishuishuia.pojo;

import lombok.Data;

/**
 * @author 晓梦之尘
 * more about author: www.shuishuia.cn
 * @ClassName User
 * @Description TODO
 * @date 2025/3/25 10:53
 * @Version 1.0
 */

@Data
public class User {
    private int id;
    private String name;
    private String phone;
    private String username;
    private String password;
    private String gender;
    private String avatar;
    private String location;
}
