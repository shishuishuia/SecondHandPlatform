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


public interface  UserMapper {

    public User findbyUsername(String username);
    public int insert(User user);
    public User getUserInfoById(int id);
    public User getUserInfoByhandphoneId(int id);

    public int updateUserInfo(User user);
    //发布商品

    public int increasetransactionnumber(int userId);

}
