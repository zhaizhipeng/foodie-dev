package com.ysdrzp.service;

import com.ysdrzp.bo.SubmitOrderBO;
import com.ysdrzp.pojo.OrderStatus;
import com.ysdrzp.vo.OrderVO;

public interface IOrdersService {

    /**
     * 创建订单
     * @param submitOrderBO
     * @return
     */
    OrderVO createOrder(SubmitOrderBO submitOrderBO);

    /**
     * 更新订单状态
     * @param merchantOrderId
     * @param status 状态
     */
    void updateOrderStatus(String merchantOrderId, Integer status);

    /**
     * 查询订单交易状态信息
     * @param orderId
     * @return
     */
    OrderStatus queryOrderStatusInfo(String orderId);
}
