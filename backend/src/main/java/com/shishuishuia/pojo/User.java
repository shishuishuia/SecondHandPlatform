package com.shishuishuia.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.LocalDateTime;

/**
 * @author 晓梦之尘
 * more about author: www.shuishuia.cn
 * @ClassName User
 * @Description TODO
 * @date 2025/3/25 10:53
 * @Version 1.0
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private int id;
    private String name;
    private String phone;
    private String username;
    private String password;
    private String gender;
    private String avatar;
    private String location;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime registered;
    private int transactionnumber;
}
