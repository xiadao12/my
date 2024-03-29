package com.zcy.mytool.health.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class HealthRecord {

    private Long id;

    // 健康类型, 1 food, 2 exercise
    private HealthTypeEnum healthTypeEnum;

    // 标题
    private String title;

    // todo 表结构需要新增
    // 创建时间
    private Date createDate;

    // 创建时间，因为有可能是多个，所以以集合方式记录
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    /**
     * 默认构造方法
     */
    public HealthRecord() {
    }

    /**
     * 构造方法
     *
     * @param healthTypeEnum
     * @param title
     * @param createTime
     */
    public HealthRecord(Long id, HealthTypeEnum healthTypeEnum, String title, Date createTime) {
        this.id = id;
        this.healthTypeEnum = healthTypeEnum;
        this.title = title;
        this.createDate = createDate;
        this.createTime = createTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public HealthTypeEnum getHealthTypeEnum() {
        return healthTypeEnum;
    }

    public void setHealthTypeEnum(HealthTypeEnum healthTypeEnum) {
        this.healthTypeEnum = healthTypeEnum;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
