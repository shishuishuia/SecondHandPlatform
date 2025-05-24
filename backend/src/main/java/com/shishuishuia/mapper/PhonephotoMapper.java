package com.shishuishuia.mapper;

import java.util.List;

/**
 * @author 晓梦之尘
 * more about author: www.shuishuia.cn
 * @ClassName PhonephotoMapper
 * @Description TODO
 * @date 2025/5/24 21:38
 * @Version 1.0
 */
public interface PhonephotoMapper {
    public int deletephonephotoByphoneId(int phoneId);
    public List<String> getPhonephotosByphoneId(int phoneId);

}
