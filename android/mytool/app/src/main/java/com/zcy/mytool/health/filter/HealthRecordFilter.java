package com.zcy.mytool.health.filter;

import com.zcy.mytool.base.filter.BaseFilter;
import com.zcy.mytool.health.model.HealthTypeEnum;

import java.util.Set;

/**
 * 记录检索条件
 */
public class HealthRecordFilter extends BaseFilter {

    /**
     * 健康类型
     */
    private Set<HealthTypeEnum> HealthType;

    public Set<HealthTypeEnum> getHealthType() {
        return HealthType;
    }

    public void setHealthType(Set<HealthTypeEnum> healthType) {
        HealthType = healthType;
    }
}
