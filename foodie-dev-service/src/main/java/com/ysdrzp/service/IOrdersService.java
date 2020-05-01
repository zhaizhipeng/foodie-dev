package com.ysdrzp.service;

import com.ysdrzp.bo.SubmitOrderBO;

public interface IOrdersService {

    /**
     * 创建订单
     * @param submitOrderBO
     * @return
     */
    String createOrder(SubmitOrderBO submitOrderBO);
}
