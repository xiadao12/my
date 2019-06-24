package com.zcy.mytool.base.core;

import com.zcy.mytool.base.filter.BaseFilter;

import org.apache.commons.lang3.StringUtils;

public class SqlUtil {

    public static String buildWhereSql(String sql, BaseFilter baseFilter) {

        sql = sql + " where 1=1";

        // id
        if (baseFilter.getId() != null) {
            sql = sql + " and id=" + baseFilter.getId();
        }

        // ids
        if (baseFilter.getIds() != null && baseFilter.getIds().size() > 0) {
            String idsString = StringUtils.join(baseFilter.getIds(), ',');
            sql = sql + " and ids in (" + idsString + ")";
        }

        // 开始时间
        if (baseFilter.getStartTime() != null) {
            sql = sql + " and create_time >= " + baseFilter.getStartTime();
        }

        // 结束时间
        if (baseFilter.getEndTime() != null) {
            sql = sql + " and create_time <= " + baseFilter.getEndTime();
        }

        return sql;
    }

}
