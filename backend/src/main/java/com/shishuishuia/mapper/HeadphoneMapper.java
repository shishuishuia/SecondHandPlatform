package com.shishuishuia.mapper;

import com.shishuishuia.pojo.Headphone;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 晓梦之尘
 * more about author: www.shuishuia.cn
 * @ClassName ProductMapper
 * @Description TODO
 * @date 2025/4/25 15:52
 * @Version 1.0
 */
public interface HeadphoneMapper {

    //返回值为该手机 id
    public int saveHeadphone(Headphone headphone);
    public int savephonePhoto(int id, @Param("list") List<String> photos);
    public int publishhandphone(@Param("userId") int userId, @Param("handphoneId") int handphoneId);
}
