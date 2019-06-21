package com.zcy.mytool.health.model;

/**
 * 常用动作，例如：早餐包子
 */
public class HealthCommonRecord {
    // 健康类型, 1 food, 2 exercise
    private Enum HealthType;

    // 内容
    private String content;

    public Enum getHealthType() {
        return HealthType;
    }

    public void setHealthType(Enum healthType) {
        HealthType = healthType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
