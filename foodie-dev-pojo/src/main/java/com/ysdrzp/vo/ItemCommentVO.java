package com.ysdrzp.vo;

import lombok.Data;

@Data
public class ItemCommentVO {

    /**
     * 评价等级
     */
    private Integer commentLevel;

    /**
     * 评价内容
     */
    private String content;

    /**
     * 评价时间
     */
    private String createdTime;

    /**
     * 商品规格名称
     */
    private String specName;

    /**
     * 用户昵称
     */
    private String nickname;

    /**
     * 用户头像
     */
    private String userFace;

}
