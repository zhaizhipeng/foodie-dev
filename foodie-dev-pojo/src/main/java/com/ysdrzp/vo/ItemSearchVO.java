package com.ysdrzp.vo;

import lombok.Data;

/**
 * 商品查询返回对象
 */
@Data
public class ItemSearchVO {

    /**
     * 商品Id
     */
    private String itemId;

    /**
     * 商品名称
     */
    private String itemName;

    /**
     * 销量
     */
    private int sellCounts;

    /**
     * 商品主图
     */
    private String imgUrl;

    /**
     * 商品价格
     */
    private int price;
}
