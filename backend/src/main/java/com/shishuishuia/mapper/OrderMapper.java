package com.shishuishuia.mapper;

import com.shishuishuia.pojo.Orders;
import org.apache.ibatis.annotations.Param;

/**
 * @author 晓梦之尘
 * more about author: www.shuishuia.cn
 * @ClassName OrderMapper
 * @Description TODO
 * @date 2025/4/29 18:51
 * @Version 1.0
 */
public interface OrderMapper {
    public int createOrder(Orders orders);
}
