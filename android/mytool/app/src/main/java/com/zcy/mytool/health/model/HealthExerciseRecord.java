package com.zcy.mytool.health.model;

import java.util.Date;

public class HealthExerciseRecord extends HealthRecord {

    /**
     * 默认构造方法
     */
    public HealthExerciseRecord() {
    }

    /**
     * 构造方法
     *
     * @param healthTypeEnum
     * @param title
     * @param createTime
     */
    public HealthExerciseRecord(Integer id, HealthTypeEnum healthTypeEnum, String title, Date createTime) {
        super(id, healthTypeEnum, title, createTime);
    }

}
