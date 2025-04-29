package com.shishuishuia.controller;

import com.shishuishuia.Service.OrderService;
import com.shishuishuia.pojo.Orders;
import com.shishuishuia.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        Result result = orderService.createOrder(orders);
        return result;
    }
}
