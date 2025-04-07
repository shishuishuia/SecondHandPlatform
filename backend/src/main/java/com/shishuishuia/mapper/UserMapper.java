package com.shishuishuia.mapper;

import com.shishuishuia.pojo.User;
import org.apache.ibatis.annotations.Param;

/**
 * @author 晓梦之尘
 * more about author: www.shuishuia.cn
 * @ClassName UserMapper
 * @Description TODO
 * @date 2025/3/27 21:56
 * @Version 1.0
 */


public interface UserMapper {
    public User login(@Param("username") String username, @Param("password") String password);
}
