package com.zcy.mytool.health.service;

import com.zcy.mytool.health.dao.HealthRecordDao;
import com.zcy.mytool.health.filter.HealthRecordFilter;
import com.zcy.mytool.health.model.HealthRecord;

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
        HealthRecordDao.instance().create(healthRecord);
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
        List<HealthRecord> healthRecords = HealthRecordDao.instance().query(healthRecordFilter);
        return healthRecords;
    }


}
