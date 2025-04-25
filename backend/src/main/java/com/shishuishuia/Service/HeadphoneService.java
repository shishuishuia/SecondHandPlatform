package com.shishuishuia.Service;

import com.shishuishuia.pojo.Headphone;
import com.shishuishuia.utils.Result;

/**
 * @author 晓梦之尘
 * more about author: www.shuishuia.cn
 * @ClassName ProductService
 * @Description TODO
 * @date 2025/4/25 15:29
 * @Version 1.0
 */
public interface HeadphoneService {
    public Result uploadHeadphone(long userId, Headphone headphone);
}
