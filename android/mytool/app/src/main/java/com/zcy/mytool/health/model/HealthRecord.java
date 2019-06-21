package com.zcy.mytool.health.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class HealthRecord extends HealthCommonRecord {

    // 时间，因为有可能是多个，所以以集合方式记录
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
