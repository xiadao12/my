package com.zcy.mytool.health.model;

import java.util.Date;
import java.util.List;

public class HealthRecord {

    // 健康类型, 1 food, 2 exercise
    Integer HeahthType;

    // 内容
    String content;

    // 个数
    Integer size;

    // 时间，因为有可能是多个，所以以集合方式记录
    List<Date> dates;

    public Integer getHeahthType() {
        return HeahthType;
    }

    public void setHeahthType(Integer heahthType) {
        HeahthType = heahthType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public List<Date> getDates() {
        return dates;
    }

    public void setDates(List<Date> dates) {
        this.dates = dates;
    }
}
