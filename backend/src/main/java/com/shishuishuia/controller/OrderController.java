package com.shishuishuia.controller;

import com.shishuishuia.Service.OrderService;
import com.shishuishuia.pojo.Orders;
import com.shishuishuia.utils.Result;
import com.shishuishuia.utils.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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

    @GetMapping("buy/orderList")
    public Result getBuyOrderListByBuyerId(
            @RequestParam int userId,
            @RequestParam(defaultValue = "1") int pageNum,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startTime,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endTime
            ){
        Result orderListByBuyerId = orderService.getOrderBuyerList(userId,pageNum,pageSize,status,keyword,startTime,endTime);
        System.out.println(orderListByBuyerId);
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
    @GetMapping("sell/orderList")
    public Result getSellOrderListBySellerId(
            @RequestParam int userId,
            @RequestParam(defaultValue = "1") int pageNum,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startTime,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endTime
    ){
        Result orderListBySellerId = orderService.getOrderSellerList(userId,pageNum,pageSize,status,keyword,startTime,endTime);
        System.out.println(orderListBySellerId);
        return orderListBySellerId;
    }
    @PutMapping("cancel/{orderId}")
    public Result putCancelOrder(@PathVariable int orderId){
        System.out.println(orderId);
        Result result = orderService.cancelOrder(orderId);
        return result;
    }
    @PutMapping("paynow/{orderId}")
    public Result putOrderPayNow(@PathVariable int orderId){
        Result result = orderService.payOrderNow(orderId);
        return result;
    }
    @PutMapping("deliver/{orderId}")
    public Result deli(@PathVariable int orderId){
        Result result = orderService.deliverProductNow(orderId);
        return result;
    }
    @PutMapping("confirm/{orderId}")
    public Result confirm(@PathVariable int orderId){
        Result result = orderService.confirmOrder(orderId);
        return result;
    }

}
