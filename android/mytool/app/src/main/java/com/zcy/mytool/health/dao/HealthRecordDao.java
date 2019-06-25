package com.zcy.mytool.health.dao;

import android.content.ContentValues;
import android.database.Cursor;

import com.zcy.mytool.base.core.SqlUtil;
import com.zcy.mytool.health.db.HealthDatabaseHelper;
import com.zcy.mytool.health.filter.HealthRecordFilter;
import com.zcy.mytool.health.model.HealthRecord;
import com.zcy.mytool.health.model.HealthTypeEnum;

import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class HealthRecordDao {

    private static HealthRecordDao healthRecordDao;

    /**
     * 单例
     *
     * @return
     */
    public static HealthRecordDao instance() {
        if (healthRecordDao == null) {
            synchronized (HealthRecordDao.class) {
                if (healthRecordDao == null) {
                    healthRecordDao = new HealthRecordDao();
                }
            }
        }

        return healthRecordDao;
    }

    /**
     * 创建记录
     *
     * @param healthRecord
     * @return 新插入的id
     */
    public Long create(HealthRecord healthRecord) {

        // 健康类型, 1 food, 2 exercise
        HealthTypeEnum healthTypeEnum = healthRecord.getHealthTypeEnum();
        Integer healthType = healthTypeEnum.getCode();

        // 标题
        String title = healthRecord.getTitle();

        // 创建时间
        Date createTime = healthRecord.getCreateTime();
        if (createTime == null) {
            createTime = new Date();
        }

        // todo 后期研究其他解决方案
        // 格式化时间
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ");
        String createTimeString = formatter.format(createTime);

        ContentValues contentValues = new ContentValues();
        contentValues.put("health_type", healthType);
        contentValues.put("title", title);
        contentValues.put("create_time", createTimeString);
        long resultId = HealthDatabaseHelper.db.insert("health_record", "health_type", contentValues);

        return resultId;
    }

    /**
     * 根据ids删除
     *
     * @param ids
     * @return
     */
    public Integer delete(Set<Long> ids) {
        return null;
    }

    /**
     * 修改
     *
     * @param healthRecord
     * @return
     */
    public HealthRecord update(HealthRecord healthRecord) {
        return null;
    }

    /**
     * 查询
     *
     * @param healthRecordFilter
     * @return
     */
    public List<HealthRecord> query(HealthRecordFilter healthRecordFilter) {

        String querySql = "select id,health_type,title,create_time from health_record";

        /*
        条件查询
         */
        if (healthRecordFilter != null) {

            querySql = SqlUtil.buildWhereSql(querySql, healthRecordFilter);

            // 根据类型查询
            if (healthRecordFilter.getHealthType() != null && healthRecordFilter.getHealthType().size() > 0) {
                Set<Integer> healthTypes = new HashSet<>();
                for (HealthTypeEnum healthTypeEnum : healthRecordFilter.getHealthType()) {
                    healthTypes.add(healthTypeEnum.getCode());
                }
                if (healthTypes.size() > 0) {
                    String healthTypesString = StringUtils.join(healthTypes, ',');
                    querySql = querySql + String.format(" health_type in (%s)", healthTypesString);
                }
            }
        }

        String[] values = {};

        Cursor cursor = HealthDatabaseHelper.db.rawQuery(querySql, values);

        /*
        获取各字段index
         */
        Integer idIndex = cursor.getColumnIndex("id");
        Integer healthTypeIndex = cursor.getColumnIndex("health_type");
        Integer titleIndex = cursor.getColumnIndex("title");
        Integer createTimeIndex = cursor.getColumnIndex("create_time");

        /**
         * 存放查询出来的数据
         */
        List<HealthRecord> healthRecords = new ArrayList<>();
        long id;
        int healthType;
        String title;
        Long createTimeLong;
        Date createTime;
        HealthRecord healthRecord;
        while (cursor.moveToNext()) {

            // 获取id
            id = cursor.getInt(idIndex);

            // 获取类型
            healthType = cursor.getInt(healthTypeIndex);
            HealthTypeEnum healthTypeEnum = HealthTypeEnum.getEnum(healthType);

            // 获取标题
            title = cursor.getString(titleIndex);

            // 获取创建时间
            createTimeLong = cursor.getLong(createTimeIndex);
            if (createTimeLong != null) {
                createTime = new Date(createTimeLong);
            } else {
                createTime = null;
            }

            healthRecord = new HealthRecord(id, healthTypeEnum, title, createTime);
            healthRecords.add(healthRecord);
        }

        return healthRecords;
    }
}
