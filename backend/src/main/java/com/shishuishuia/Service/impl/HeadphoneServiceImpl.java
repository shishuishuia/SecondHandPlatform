package com.shishuishuia.Service.impl;

import com.shishuishuia.Service.HandphoneService;
import com.shishuishuia.mapper.HandphoneMapper;
import com.shishuishuia.pojo.HandPhone;
import com.shishuishuia.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 晓梦之尘
 * more about author: www.shuishuia.cn
 * @ClassName ProductServiceImpl
 * @Description TODO
 * @date 2025/4/25 15:29
 * @Version 1.0
 */

@Service
public class HeadphoneServiceImpl implements HandphoneService {

    @Autowired
    private HandphoneMapper handphoneMapper;

    @Override

    public Result uploadHeadphone(long userId, HandPhone headphone) {
        System.out.println("headphoneService:"+headphone);
        System.out.println(userId);
        int handphoneid = handphoneMapper.saveHeadphone(headphone);

        System.out.println(handphoneid);
        System.out.println("主键是 : key :: "+headphone.getId());


        int i = handphoneMapper.savephonePhoto(headphone.getId(), headphone.getPhotos());
        int publishhandphone = handphoneMapper.publishhandphone((int) userId, handphoneid);
        return Result.ok(publishhandphone);
    }

    public Result getAllHandphoneByUserId(int id){
        List<HandPhone> allrHandphoneByUseId = handphoneMapper.getAllrHandphoneByUseId(id);
        return Result.ok(allrHandphoneByUseId);
    }
}
