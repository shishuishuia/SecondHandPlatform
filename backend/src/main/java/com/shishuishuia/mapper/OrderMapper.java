package com.shishuishuia.mapper;

import com.shishuishuia.pojo.Orders;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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
    public Orders getOrderDetailById(int id);
    public Orders getOrderDetailByphoneId(int id);
    public List<Orders> getBuyOrderListByBuyerId(int id);
    public List<Orders> getSellOrderListBySellerId(int id);
    public int updateOrderStateInt(@Param("orderId")int id, @Param("state" )int state);
}
