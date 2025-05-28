package com.shishuishuia.mapper;

import com.shishuishuia.pojo.Orders;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;
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
    public List<Orders> getBuyerOrderlist(
            @Param("userId") int userId,
            @Param("status") String status,
            @Param("keyword")String keyword,
            @Param("startTime")LocalDate startTime,
            @Param("endTime")LocalDate endTime);
    public List<Orders> getSellerOrderlist(
            @Param("userId") int userId,
            @Param("status") String status,
            @Param("keyword")String keyword,
            @Param("startTime")LocalDate startTime,
            @Param("endTime")LocalDate endTime);
    public List<Orders> getSellOrderListBySellerId(int id);
    public int updateOrderStateInt(@Param("orderId")int id, @Param("state" )int state);
    public int updatePayTime(@Param("orderId")int orderId,@Param("payTime") LocalDate payTime);
    public int updatedeliverTime(@Param("orderId")int orderId,@Param("deliverTime") LocalDate deliverTime);
    public int updateconfirmTime(@Param("orderId")int orderId,@Param("confirmTime") LocalDate confirmTime);
    public int updatecancelTime(@Param("orderId")int orderId,@Param("cancelTime") LocalDate cancelTime);
    public int updatefinishTime(@Param("orderId")int orderId,@Param("finishTime") LocalDate finishTime);
    public int deleteByOrderIdInt(int orderId);

}
