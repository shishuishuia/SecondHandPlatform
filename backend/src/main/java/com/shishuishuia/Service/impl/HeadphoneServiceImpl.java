package com.shishuishuia.Service.impl;

import com.shishuishuia.Service.HeadphoneService;
import com.shishuishuia.mapper.HeadphoneMapper;
import com.shishuishuia.pojo.Headphone;
import com.shishuishuia.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Autowired
    private HeadphoneMapper headphoneMapper;

    @Override

    public Result uploadHeadphone(long userId, Headphone headphone) {
        System.out.println("headphoneService:"+headphone);
        System.out.println(userId);
        int handphoneid = headphoneMapper.saveHeadphone(headphone);
        System.out.println(handphoneid);
        int i = headphoneMapper.savephonePhoto(handphoneid, headphone.getPhotos());
        int publishhandphone = headphoneMapper.publishhandphone((int) userId, handphoneid);
        return Result.ok(publishhandphone);
    }
}
