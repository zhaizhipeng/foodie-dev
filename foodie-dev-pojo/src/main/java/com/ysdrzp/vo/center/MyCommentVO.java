package com.ysdrzp.vo.center;

import lombok.Data;

import java.util.Date;

/**
 * 商品评论详情
 */
@Data
public class MyCommentVO {

    /**
     * 评论Id
     */
    private String commentId;

    /**
     * 品论内容
     */
    private String content;

    /**
     * 评论时间
     */
    private Date createdTime;

    /**
     * 商品 Id
     */
    private String itemId;

    /**
     * 商品详情
     */
    private String itemName;

    /**
     * 商品规格名称
     */
    private String specName;

    /**
     * 商品图片
     */
    private String itemImg;
}