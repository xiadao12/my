package com.zcy.mytool.health.model;

import java.util.Date;

public class HealthFoodRecord extends HealthRecord {

    /**
     * 默认构造方法
     */
    public HealthFoodRecord() {
    }

    /**
     * 构造方法
     *
     * @param healthTypeEnum
     * @param title
     * @param createTime
     */
    public HealthFoodRecord(Long id, HealthTypeEnum healthTypeEnum, String title, Date createTime) {
        super(id, healthTypeEnum, title, createTime);
    }

}
