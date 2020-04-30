package com.ysdrzp.vo;

import lombok.Data;

import java.util.List;

@Data
public class SixItemVO {

    /**
     * 商品类型名称
     */
    private String rootCatName;

    /**
     * 口号
     */
    private String slogan;

    /**
     * 商品推荐列表
     */
    private List<SimpleItemVO> simpleItemList;
}
