package com.zcy.mytool.health.service;

import android.app.Activity;
import android.view.View;
import android.widget.Button;

import com.zcy.mytool.R;
import com.zcy.mytool.health.db.HealthDatabaseHelper;
import com.zcy.mytool.health.listener.HealthAddFoodRecordButtonListener;
import com.zcy.mytool.health.listener.HealthShowGraidButtonListener;

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
     * @param activity 上下文
     */
    public void init(Activity activity) {
        // 初始化数据库
        HealthDatabaseHelper healthDatabaseHelper = new HealthDatabaseHelper(activity);
        HealthDatabaseHelper.db = healthDatabaseHelper.getWritableDatabase();

        /*
        点击按钮添加记录
         */
        // 餐饮记录按钮事件
        Button addFoodRecordButton = activity.findViewById(R.id.health_addExerciseRecord);
        if (addFoodRecordButton != null) {
            addFoodRecordButton.setOnClickListener(new HealthAddFoodRecordButtonListener());
        }

        // 展示列表事件
        Button showGraidButton = activity.findViewById(R.id.health_showGraid);
        if (showGraidButton != null) {
            showGraidButton.setOnClickListener(new HealthShowGraidButtonListener(activity));
        }

        /*
        显示表数据
         */
        HealthRecordService.instance().query(null);
        View contentGrid = activity.findViewById(R.id.health_contentGrid);
        if (contentGrid != null) {
        }
    }

}
