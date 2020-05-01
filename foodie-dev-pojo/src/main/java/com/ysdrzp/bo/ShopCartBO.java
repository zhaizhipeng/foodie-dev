package com.ysdrzp.bo;

import lombok.Data;

/**
 * 购物车参数
 */
@Data
public class ShopCartBO {

    /**
     * 商品Id
     */
    private String itemId;

    /**
     * 商品图片
     */
    private String itemImgUrl;

    /**
     * 商品名称
     */
    private String itemName;

    /**
     * 商品规格Id
     */
    private String specId;

    /**
     * 商品规格名称
     */
    private String specName;

    /**
     * 购买数量
     */
    private int buyCounts;

    /**
     * 促销价
     */
    private int priceDiscount;

    /**
     * 原价
     */
    private int priceNormal;

}
