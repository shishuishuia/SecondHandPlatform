package com.shishuishuia.Service.impl;

import com.shishuishuia.Service.OrderService;
import com.shishuishuia.mapper.HandphoneMapper;
import com.shishuishuia.mapper.OrderMapper;
import com.shishuishuia.mapper.UserMapper;
import com.shishuishuia.pojo.Orders;
import com.shishuishuia.utils.Result;
import com.shishuishuia.utils.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author 晓梦之尘
 * more about author: www.shuishuia.cn
 * @ClassName OrderServiceImpl
 * @Description TODO
 * @date 2025/4/29 18:49
 * @Version 1.0
 */

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private HandphoneMapper handphoneMapper;

    @Autowired
    private UserMapper userMapper;
    @Override
    public Result createOrder(Orders orders) {
        Orders orderDetailById = orderMapper.getOrderDetailByphoneId(orders.getPhoneId());
        if(orderDetailById == null || orderDetailById.getOrderState() == 4) {
            int res = orderMapper.createOrder(orders);
            int i = handphoneMapper.updatephoneState(orders.getPhoneId(), 1);

            return Result.ok(res);
        }else return Result.build("订单已存在", ResultCodeEnum.OTHERMISTAKE);

    }

    @Override
    public Result getOrderDetailById(int id) {
        Orders orderDetailById = orderMapper.getOrderDetailById(id);

        return Result.ok(orderDetailById);
    }

    @Override
    public Result getOrderListByBuyerId(int id) {
        List<Orders> buyOrderListByBuyerId = orderMapper.getBuyOrderListByBuyerId(id);

        return Result.ok(buyOrderListByBuyerId);
    }

    @Override
    public Result getOrderListBySellerId(int id) {
        List<Orders> sellOrderListBySellerId = orderMapper.getSellOrderListBySellerId(id);
        return Result.ok(sellOrderListBySellerId);
    }

    @Override
    public Result cancelOrder(int orderid) {
        int i = orderMapper.updateOrderStateInt(orderid, 4);
        Orders orderDetailById = orderMapper.getOrderDetailById(orderid);
        int i1 = handphoneMapper.updatephoneState(orderDetailById.getPhoneId(), 0);

        return Result.ok(i);
    }
}
