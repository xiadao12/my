package com.zcy.mytool.base.filter;

import java.util.Date;
import java.util.Set;

public class BaseFilter {

    //id
    private Long id;

    //id集合
    private Set<Long> ids;

    //开始日期
    private Date startTime;

    //结束日期
    private Date endTime;

    //每页数量
    private Integer pageSize = 0;

    //第几页
    private Integer pageNum = 0;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Long> getIds() {
        return ids;
    }

    public void setIds(Set<Long> ids) {
        this.ids = ids;
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

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }
}
