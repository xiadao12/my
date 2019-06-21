package com.zcy.mytool.health.service;

import com.zcy.mytool.MainActivity;
import com.zcy.mytool.health.db.HealthDatabaseHelper;

public class HealthService {

    private static HealthService healthService;

    /**
     * 单例
     *
     * @return
     */
    public static HealthService instance() {
        if (healthService == null) {
            synchronized (HealthService.class) {
                if (healthService == null) {
                    healthService = new HealthService();
                }
            }
        }

        return healthService;
    }

    /**
     * 初始化
     *
     * @param mainActivity 上下文
     */
    public void init(MainActivity mainActivity) {
        // 初始化数据库
        HealthDatabaseHelper healthDatabaseHelper = new HealthDatabaseHelper(mainActivity);
        healthDatabaseHelper.getWritableDatabase();
    }

}
