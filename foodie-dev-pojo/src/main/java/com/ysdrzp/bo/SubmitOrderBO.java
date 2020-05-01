package com.ysdrzp.bo;

import lombok.Data;

/**
 * 订单创建
 */
@Data
public class SubmitOrderBO {

    /**
     * 结算商品规格信息
     */
    private String itemSpecIds;

    /**
     * 用户 Id
     */
    private String userId;

    /**
     * 地址 Id
     */
    private String addressId;

    /**
     * 支付方式
     */
    private Integer payMethod;

    /**
     * 留言
     */
    private String leftMsg;
}
