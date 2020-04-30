package com.ysdrzp.vo;

import lombok.Data;

@Data
public class SimpleItemVO {

    /**
     * 商品 Id
     */
    private String itemId;

    /**
     * 商品名称
     */
    private String itemName;

    /**
     * 商品图片地址
     */
    private String itemUrl;

    /**
     * 创建时间
     */
    private String createdtime;
}
