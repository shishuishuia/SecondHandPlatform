package com.shishuishuia.Service.impl;

import com.shishuishuia.Service.HeadphoneService;
import com.shishuishuia.pojo.Headphone;
import com.shishuishuia.utils.Result;
import org.springframework.stereotype.Service;

/**
 * @author 晓梦之尘
 * more about author: www.shuishuia.cn
 * @ClassName ProductServiceImpl
 * @Description TODO
 * @date 2025/4/25 15:29
 * @Version 1.0
 */

@Service
public class HeadphoneServiceImpl implements HeadphoneService {


    @Override
    public Result uploadHeadphone(long userId, Headphone headphone) {
        System.out.println("headphoneService:"+headphone);
        System.out.println(userId);
        return null;
    }
}
