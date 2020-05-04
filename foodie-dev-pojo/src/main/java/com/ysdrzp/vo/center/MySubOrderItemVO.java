package com.ysdrzp.vo.center;

import lombok.Data;

/**
 * 用户中心-我的订单列表-嵌套商品VO
 */
@Data
public class MySubOrderItemVO {

    /**
     * 商品Id
     */
    private String itemId;

    /**
     * 商品图片
     */
    private String itemImg;

    /**
     * 商品名称
     */
    private String itemName;

    /**
     * 商品规格名称
     */
    private String itemSpecName;

    /**
     * 购买数量
     */
    private Integer buyCounts;

    /**
     * 价格
     */
    private Integer price;

}
