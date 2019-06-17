package com.zcy.mytool.health.model;

/**
 * 健康类型
 */
public enum HealthTypeEnum {

    FOOD(1, "食物"),
    EXERCISE(2,"锻炼");

    private Integer code;
    private String value;

    HealthTypeEnum(Integer code, String value) {
        this.code = code;
        this.value = value;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
