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

    public static HealthTypeEnum getEnum(int code) {
        HealthTypeEnum[] var1 = values();
        int var2 = var1.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            HealthTypeEnum healthTypeEnum = var1[var3];
            if (healthTypeEnum.getCode() == code) {
                return healthTypeEnum;
            }
        }

        return null;
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
