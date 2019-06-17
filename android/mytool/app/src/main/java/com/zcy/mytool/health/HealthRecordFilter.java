package com.zcy.mytool.health;

import java.util.Date;
import java.util.Set;

/**
 * 记录检索条件
 */
public class HealthRecordFilter {

    /**
     * id 检索
     */
    private Integer id;

    /**
     * id 集合检索
     */
    private Set<Integer> ids;

    /**
     * 健康类型
     */
    private Set<Integer> HealthType;

    /**
     * 开始时间
     */
    private Date startTime;

    /**
     * 结束时间
     */
    private Date endTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Set<Integer> getIds() {
        return ids;
    }

    public void setIds(Set<Integer> ids) {
        this.ids = ids;
    }

    public Set<Integer> getHealthType() {
        return HealthType;
    }

    public void setHealthType(Set<Integer> healthType) {
        HealthType = healthType;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
