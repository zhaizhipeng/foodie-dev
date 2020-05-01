package com.ysdrzp.enums;

/**
 * 是否默认收货地址
 */
public enum IsDefault {

    Yes(1, "是"),
    No(0, "否");

    public final Integer type;

    public final String value;

    IsDefault(Integer type, String value) {
        this.type = type;
        this.value = value;
    }
}
