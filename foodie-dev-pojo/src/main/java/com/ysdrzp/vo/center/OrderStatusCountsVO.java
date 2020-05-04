package com.ysdrzp.vo.center;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 订单状态概览数量VO 
 */
@Data
@AllArgsConstructor
public class OrderStatusCountsVO {

    /**
     * 待支付
     */
    private Integer waitPayCounts;

    /**
     * 待发货
     */
    private Integer waitDeliverCounts;

    /**
     * 待收货
     */
    private Integer waitReceiveCounts;

    /**
     * 待评论
     */
    private Integer waitCommentCounts;
}