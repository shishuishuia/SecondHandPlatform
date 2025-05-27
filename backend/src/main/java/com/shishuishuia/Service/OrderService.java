package com.shishuishuia.Service;

import com.shishuishuia.pojo.Orders;
import com.shishuishuia.utils.Result;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.time.LocalDate;

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
    public Result getOrderBuyerList(int userId, int pageNum, int pageSize, String status, String keyword, LocalDate startTime,LocalDate endTime);
    public Result getOrderSellerList(int userId, int pageNum, int pageSize, String status, String keyword, LocalDate startTime,LocalDate endTime);
    public Result cancelOrder(int id);
    public Result payOrderNow(int orderId);
    public Result deliverProductNow(int orderId);
    public Result confirmOrder(int orderId);
}
