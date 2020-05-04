package com.ysdrzp.vo.center;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 用户中心订单列表
 */
@Data
public class MyOrdersVO {

    /**
     * 订单 Id
     */
    private String orderId;

    /**
     * 创建时间
     */
    private Date createdTime;

    /**
     * 支付方式
     */
    private Integer payMethod;

    /**
     * 后付金额
     */
    private Integer realPayAmount;

    /**
     * 邮费
     */
    private Integer postAmount;

    /**
     * 是否评论
     */
    private Integer isComment;

    /**
     * 订单状态
     */
    private Integer orderStatus;

    /**
     * 商品列表
     */
    private List<MySubOrderItemVO> subOrderItemList;
}
