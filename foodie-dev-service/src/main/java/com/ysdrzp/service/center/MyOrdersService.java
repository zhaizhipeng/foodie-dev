package com.ysdrzp.service.center;

import com.ysdrzp.pojo.Orders;
import com.ysdrzp.utils.PagedGridResult;
import com.ysdrzp.vo.center.OrderStatusCountsVO;

public interface MyOrdersService {

    /**
     * 查询我的订单列表
     * @param userId
     * @param orderStatus
     * @param page
     * @param pageSize
     * @return
     */
    PagedGridResult queryMyOrders(String userId, Integer orderStatus, Integer page, Integer pageSize);

    /**
     * 查询我的订单
     * @param userId
     * @param orderId
     * @return
     */
    Orders queryMyOrder(String userId, String orderId);

    /**
     * 删除订单（逻辑删除）
     * @param userId
     * @param orderId
     * @return
     */
    boolean deleteOrder(String userId, String orderId);

    /**
     * 查询用户订单数
     * @param userId
     * @return
     */
    OrderStatusCountsVO getOrderStatusCounts(String userId);

    /**
     * 获得分页的订单动向
     * @param userId
     * @param page
     * @param pageSize
     * @return
     */
    PagedGridResult getOrdersTrend(String userId, Integer page, Integer pageSize);

    /**
     * 订单状态 --> 商家发货 - （手动处理）
     * @param orderId
     */
    void updateDeliverOrderStatus(String orderId);

    /**
     * 更新订单状态 —> 确认收货 - （手动处理）
     * @param orderId
     * @return
     */
    boolean updateReceiveOrderStatus(String orderId);

}
