package com.shishuishuia.Service;

import com.shishuishuia.pojo.Orders;
import com.shishuishuia.utils.Result;

import java.math.BigDecimal;

/**
 * @author 晓梦之尘
 * more about author: www.shuishuia.cn
 * @ClassName OrderService
 * @Description TODO
 * @date 2025/4/29 18:49
 * @Version 1.0
 */
public interface OrderService {
    public Result createOrder(Orders orders);
    public Result getOrderDetailById(int id);
    public Result getOrderListByBuyerId(int id);
    public Result getOrderListBySellerId(int id);
    public Result cancelOrder(int id);
}
