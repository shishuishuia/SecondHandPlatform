package com.shishuishuia.Service;

/**
 * @author 晓梦之尘
 * more about author: www.shuishuia.cn
 * @ClassName UserService
 * @Description TODO
 * @date 2025/3/27 21:54
 * @Version 1.0
 */

import com.shishuishuia.pojo.User;
import com.shishuishuia.utils.Result;
import org.springframework.stereotype.Service;


public interface UserService {

    public Result login(String username, String password);
    public Result register(User user);
}
