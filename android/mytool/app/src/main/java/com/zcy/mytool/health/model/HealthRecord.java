package com.zcy.mytool.health.model;

import java.util.Date;
import java.util.List;

public class HealthRecord extends HealthCommonRecord{

    // 时间，因为有可能是多个，所以以集合方式记录
    private List<Date> dates;

    public List<Date> getDates() {
        return dates;
    }

    public void setDates(List<Date> dates) {
        this.dates = dates;
    }
}
