package com.shishuishuia.Service.impl;

import com.shishuishuia.Service.HandphoneService;
import com.shishuishuia.mapper.HandphoneMapper;
import com.shishuishuia.mapper.PhonephotoMapper;
import com.shishuishuia.mapper.PublishMapper;
import com.shishuishuia.mapper.UserMapper;
import com.shishuishuia.pojo.HandPhone;
import com.shishuishuia.pojo.User;
import com.shishuishuia.utils.FileStorageService;
import com.shishuishuia.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PublishMapper publishMapper;

    @Autowired
    private PhonephotoMapper phonephotoMapper;

    @Autowired
    private FileStorageService fileStorageService;

    @Override

    public Result uploadHeadphone(long userId, HandPhone headphone) {
        System.out.println("headphoneService:"+headphone);
        System.out.println(userId);
        int handphoneid = handphoneMapper.saveHeadphone(headphone);

        System.out.println(handphoneid);
        System.out.println("主键是 : key :: "+headphone.getId());


        int i = handphoneMapper.savephonePhoto(headphone.getId(), headphone.getPhotos());
        int publishhandphone = handphoneMapper.publishhandphone((int) userId, headphone.getId());
        return Result.ok(publishhandphone);
    }

    public Result getAllHandphoneByUserId(int id){
        List<HandPhone> allrHandphoneByUseId = handphoneMapper.getAllrHandphoneByUseId(id);
        return Result.ok(allrHandphoneByUseId);
    }

    @Override
    public Result getDetailByphoneId(int id) {
        HandPhone detailById = handphoneMapper.getDetailById(id);
        handphoneMapper.updateBrowseInt(id);

        User userInfoByhandphoneId = userMapper.getUserInfoByhandphoneId(id);
        Map data = new HashMap<>();
        data.put("id",detailById.getId());
        data.put("state",detailById.getState());
        data.put("browse",detailById.getBrowse());
        data.put("price",detailById.getPrice());
        data.put("publishedtime",detailById.getPublishedtime());
        data.put("headline",detailById.getHeadline());
        data.put("detail",detailById.getDetail());
        data.put("quality",detailById.getQuality());
        data.put("photos",detailById.getPhotos());
        data.put("userId",userInfoByhandphoneId.getId());
        data.put("name",userInfoByhandphoneId.getName());
        data.put("avatar",userInfoByhandphoneId.getAvatar());
        data.put("transactions",userInfoByhandphoneId.getTransactionnumber());

        return Result.ok(data);
    }

    @Override
    public Result getHandphoneListBylocation(String location) {
        List<HandPhone> allByLocationHandPhones = handphoneMapper.getAllByLocationHandPhones(location);
        return Result.ok(allByLocationHandPhones);
    }

    @Override
    public Result deleteHandphoneById(int id) {
        int i1 = publishMapper.deleteByPhoneId(id);

        int i2 = phonephotoMapper.deletephonephotoByphoneId(id);

        int i = handphoneMapper.deleteHandphoneByid(id);
        return Result.ok(Map.of("message","删除图片："+i2+" 删除手机： "+i ));
    }

    @Override
    public Result searchProduct(String searchtext, String location) {
        List<HandPhone> handPhones = handphoneMapper.SearchHandphone(location, searchtext);
        return Result.ok(handPhones);
    }
}
