package com.ysdrzp.vo;

import lombok.Data;

/**
 * 购物车参数
 */
@Data
public class ShopCartVO {

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
     * 促销价
     */
    private int priceDiscount;

    /**
     * 原价
     */
    private int priceNormal;

}
