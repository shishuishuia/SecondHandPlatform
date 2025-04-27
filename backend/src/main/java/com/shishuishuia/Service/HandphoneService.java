package com.shishuishuia.Service;

import com.shishuishuia.pojo.HandPhone;
import com.shishuishuia.utils.Result;

/**
 * @author 晓梦之尘
 * more about author: www.shuishuia.cn
 * @ClassName ProductService
 * @Description TODO
 * @date 2025/4/25 15:29
 * @Version 1.0
 */
public interface HandphoneService {
    public Result uploadHeadphone(long userId, HandPhone headphone);
    public Result getAllHandphoneByUserId(int id);
    public Result getDetailByphoneId(int id);
}
