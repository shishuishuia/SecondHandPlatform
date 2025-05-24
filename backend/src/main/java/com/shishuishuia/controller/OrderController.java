package com.shishuishuia.controller;

import com.shishuishuia.Service.OrderService;
import com.shishuishuia.pojo.Orders;
import com.shishuishuia.utils.Result;
import com.shishuishuia.utils.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author 晓梦之尘
 * more about author: www.shuishuia.cn
 * @ClassName OrderController
 * @Description TODO
 * @date 2025/4/29 8:16
 * @Version 1.0
 */

@CrossOrigin
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;
    @PostMapping("/create")
    public Result createOrdes(@RequestBody Orders orders){
        System.out.println("phoneId: "+ orders);
        if(orders.getBuyerId() == orders.getSellerId())
            return Result.build(Map.of("message","买家和卖家不能为同一个人"), ResultCodeEnum.OTHERMISTAKE);
        Result result = orderService.createOrder(orders);
        return result;
    }

    @GetMapping("buy/orderList/{id}")
    public Result getBuyOrderListByBuyerId(@PathVariable int id){
        Result orderListByBuyerId = orderService.getOrderListByBuyerId(id);
        return orderListByBuyerId;
    }
    @GetMapping("buy/detail/{id}")
    public Result getBuyOrderDetailById(@PathVariable int id){
        Result orderDetailById = orderService.getOrderDetailById(id);
        return orderDetailById;
    }
    @GetMapping("sell/detail/{id}")
    public Result getsellOrderDetailById(@PathVariable int id){
        Result orderDetailById = orderService.getOrderDetailById(id);
        return orderDetailById;
    }
    @GetMapping("sell/orderList/{id}")
    public Result getSellOrderListBySellerId(@PathVariable int id){
        System.out.println(id);
        Result orderListBySellerId = orderService.getOrderListBySellerId(id);
        return orderListBySellerId;
    }
    @PutMapping("cancel/{orderId}")
    public Result putCancelOrder(@PathVariable int orderId){
        System.out.println(orderId);
        Result result = orderService.cancelOrder(orderId);
        return result;
    }
}
