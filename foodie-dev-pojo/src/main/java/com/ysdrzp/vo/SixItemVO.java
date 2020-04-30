package com.ysdrzp.vo;

import lombok.Data;

import java.util.List;

@Data
public class SixItemVO {

    /**
     * 商品类型Id
     */
    private String rootCatId;

    /**
     * 商品类别名称
     */
    private String rootCatName;

    /**
     * 类别图片
     */
    private String catImage;

    /**
     * 口号
     */
    private String slogan;

    /**
     * 背景色
     */
    private String bgColor;

    /**
     * 商品推荐列表
     */
    private List<SimpleItemVO> simpleItemList;
}
