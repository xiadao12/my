package com.zcy.mytool.health.listener;


import android.view.View;

import com.zcy.mytool.health.model.HealthFoodRecord;
import com.zcy.mytool.health.model.HealthTypeEnum;
import com.zcy.mytool.health.service.HealthRecordService;

import java.util.Date;

/**
 * 添加餐饮按钮监听
 */
public class HealthAddFoodRecordButtonListener implements View.OnClickListener {
    @Override
    public void onClick(View v) {

        HealthFoodRecord healthFoodRecord = new HealthFoodRecord();
        healthFoodRecord.setTitle("早餐包子");
        healthFoodRecord.setCreateTime(new Date());
        healthFoodRecord.setHealthTypeEnum(HealthTypeEnum.FOOD);

        HealthRecordService.instance().create(healthFoodRecord);
    }
}
