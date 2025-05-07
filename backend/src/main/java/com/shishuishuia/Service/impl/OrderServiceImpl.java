package com.shishuishuia.Service.impl;

import com.shishuishuia.Service.OrderService;
import com.shishuishuia.mapper.OrderMapper;
import com.shishuishuia.pojo.Orders;
import com.shishuishuia.utils.Result;
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
    @Override
    public Result createOrder(Orders orders) {

//        int res = orderMapper.createOrder(orders);
//        System.out.println(orders.getOrderId());
        return Result.ok("res");
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
}
