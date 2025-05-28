package com.shishuishuia.Service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shishuishuia.Service.OrderService;
import com.shishuishuia.mapper.HandphoneMapper;
import com.shishuishuia.mapper.OrderMapper;
import com.shishuishuia.mapper.UserMapper;
import com.shishuishuia.pojo.HandPhone;
import com.shishuishuia.pojo.Orders;
import com.shishuishuia.utils.PageBean;
import com.shishuishuia.utils.Result;
import com.shishuishuia.utils.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

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
        HandPhone detailById = handphoneMapper.getDetailById(orders.getPhoneId());
        //如果订单不存在或是已经取消
        if(detailById.getState() == 0) {
            int res = orderMapper.createOrder(orders);
            int i = handphoneMapper.updatephoneState(orders.getPhoneId(), 1);

            return Result.ok(Map.of("orderId",orders.getOrderId()));
        }else return Result.build("订单已存在", ResultCodeEnum.OTHERMISTAKE);

    }

    @Override
    public Result getOrderDetailById(int id) {
        Orders orderDetailById = orderMapper.getOrderDetailById(id);

        return Result.ok(orderDetailById);
    }

    @Override
    public Result getOrderBuyerList(int userId, int pageNum, int pageSize, String status, String keyword, LocalDate startTime, LocalDate endTime) {

        PageHelper.startPage(pageNum,pageSize);

        List<Orders> buyerOrderlist = orderMapper.getBuyerOrderlist(userId, status, keyword, startTime, endTime);
        PageInfo<Orders> pageInfo = new PageInfo<>(buyerOrderlist);
        PageBean<Orders> pageBean = new PageBean<>(pageNum,pageSize,pageInfo.getTotal(),pageInfo.getList());
        Result<PageBean<Orders>> ok = Result.ok(pageBean);
        return ok;
    }

    @Override
    public Result getOrderSellerList(int userId, int pageNum, int pageSize, String status, String keyword, LocalDate startTime, LocalDate endTime) {

        PageHelper.startPage(pageNum,pageSize);

        List<Orders> buyerOrderlist = orderMapper.getSellerOrderlist(userId, status, keyword, startTime, endTime);
        PageInfo<Orders> pageInfo = new PageInfo<>(buyerOrderlist);
        PageBean<Orders> pageBean = new PageBean<>(pageNum,pageSize,pageInfo.getTotal(),pageInfo.getList());
        Result<PageBean<Orders>> ok = Result.ok(pageBean);
        return ok;
    }

    @Override
    public Result cancelOrder(int orderid) {
        int i = orderMapper.updateOrderStateInt(orderid, 4);
        orderMapper.updatecancelTime(orderid,LocalDate.now());
        Orders orderDetailById = orderMapper.getOrderDetailById(orderid);
        int i1 = handphoneMapper.updatephoneState(orderDetailById.getPhoneId(), 0);

        return Result.ok(i);
    }

    @Override
    public Result payOrderNow(int orderId) {
        int i1 = orderMapper.updatePayTime(orderId, LocalDate.now());
//        修改订单状态为已支付
        int i = orderMapper.updateOrderStateInt(orderId, 1);


        return Result.ok(i);
    }

    @Override
    public Result deliverProductNow(int orderId) {
        int i1 = orderMapper.updatedeliverTime(orderId, LocalDate.now());
        int i = orderMapper.updateOrderStateInt(orderId, 2);
        return Result.ok(i);
    }

    @Override
    public Result confirmOrder(int orderId) {
        int i1 = orderMapper.updateconfirmTime(orderId, LocalDate.now());
        int i = orderMapper.updateOrderStateInt(orderId, 3);
        Orders orderDetailById = orderMapper.getOrderDetailById(orderId);
        int i2 = handphoneMapper.updatephoneState(orderDetailById.getPhoneId(), 2);
        return Result.ok(i);
    }

    @Override
    public Result deleteOrder(int orderId) {
        int i = orderMapper.deleteByOrderIdInt(orderId);
        return Result.ok(i);

    }

}
