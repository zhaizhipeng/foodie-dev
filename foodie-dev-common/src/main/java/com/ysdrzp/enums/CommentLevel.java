package com.ysdrzp.enums;

/**
 * 评价等级
 */
public enum CommentLevel {

    Good(1, "好评"),
    Normal(2, "中评"),
    Bad(3, "差评");

    public final Integer type;

    public final String value;

    CommentLevel(Integer type, String value) {
        this.type = type;
        this.value = value;
    }
}
