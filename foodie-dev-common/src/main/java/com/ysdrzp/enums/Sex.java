package com.ysdrzp.enums;

/**
 * 性别
 */
public enum Sex {

    woman(0, "女"),
    man(1, "男"),
    sexSerect(2, "保密");

    private Integer type;

    private String value;

    public Integer getType() {
        return type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    Sex(Integer type, String value) {
        this.type = type;
        this.value = value;
    }

}
