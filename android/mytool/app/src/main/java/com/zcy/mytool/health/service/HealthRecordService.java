package com.zcy.mytool.health.service;

import com.zcy.mytool.health.db.HealthDatabaseHelper;
import com.zcy.mytool.health.filter.HealthRecordFilter;
import com.zcy.mytool.health.model.HealthExerciseRecord;
import com.zcy.mytool.health.model.HealthFoodRecord;
import com.zcy.mytool.health.model.HealthRecord;
import com.zcy.mytool.health.model.HealthTypeEnum;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * 记录
 */
public class HealthRecordService {

    private static HealthRecordService healthRecordService;

    /**
     * 单例
     *
     * @return
     */
    public static HealthRecordService instance() {
        if (healthRecordService == null) {
            synchronized (HealthRecordService.class) {
                if (healthRecordService == null) {
                    healthRecordService = new HealthRecordService();
                }
            }
        }

        return healthRecordService;
    }

    /**
     * 创建记录
     *
     * @param healthRecord
     * @return
     */
    public HealthRecord create(HealthRecord healthRecord) {

        // 健康类型, 1 food, 2 exercise
        HealthTypeEnum healthTypeEnum = healthRecord.getHealthTypeEnum();
        Integer healthType = healthTypeEnum.getCode();

        // 内容
        String content = healthRecord.getContent();

        // 创建时间
        Date createTime = healthRecord.getCreateTime();

        if (createTime == null) {
            createTime = new Date();
        }

        // todo 后期研究其他解决方案
        // 格式化时间
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ");
        String createTimeString = formatter.format(createTime);

        String insertSql = "";

        if (healthRecord instanceof HealthFoodRecord) {
            insertSql = String.format(
                    "insert into health_record_food(health_type,content,create_time) values(%s,'%s','%s')",
                    healthType,
                    content,
                    createTimeString);

        } else if (healthRecord instanceof HealthExerciseRecord) {
            insertSql = String.format(
                    "insert into health_record_exercise(health_type,content,create_time) values(%s,'%s','%s')",
                    healthType,
                    content,
                    createTimeString);
        } else {
            return null;
        }

        HealthDatabaseHelper.db.execSQL(insertSql);


        return null;
    }

    /**
     * 根据ids删除
     *
     * @param ids
     * @return
     */
    public Integer delete(Set<Integer> ids) {
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
        return null;
    }


}
