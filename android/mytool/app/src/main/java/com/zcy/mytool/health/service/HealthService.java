package com.zcy.mytool.health.service;

import android.widget.Button;

import com.zcy.mytool.MainActivity;
import com.zcy.mytool.R;
import com.zcy.mytool.health.db.HealthDatabaseHelper;
import com.zcy.mytool.health.listener.AddFoodRecordOnClickListener;

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
        HealthDatabaseHelper.db = healthDatabaseHelper.getWritableDatabase();

        /*
        点击按钮添加记录
         */
        // 餐饮记录按钮事件
        Button addFoodRecordButton = mainActivity.findViewById(R.id.health_addExerciseRecord);
        if (addFoodRecordButton != null) {
            addFoodRecordButton.setOnClickListener(new AddFoodRecordOnClickListener());
        }
    }

}
