package com.zcy.mytool.health.model;

/**
 * 常用动作，例如：早餐包子
 */
public class HealthCommonRecord {
    // 健康类型, 1 food, 2 exercise
    private HealthTypeEnum healthTypeEnum;

    // 内容
    private String content;

    public HealthTypeEnum getHealthTypeEnum() {
        return healthTypeEnum;
    }

    public void setHealthTypeEnum(HealthTypeEnum healthTypeEnum) {
        this.healthTypeEnum = healthTypeEnum;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
