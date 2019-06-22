package com.zcy.mytool.health.listener;

import android.app.Activity;
import android.view.View;
import android.widget.GridLayout;
import android.widget.TextView;

import com.zcy.mytool.R;
import com.zcy.mytool.health.model.HealthRecord;
import com.zcy.mytool.health.service.HealthRecordService;

import java.util.List;

public class HealthShowGraidButtonListener implements View.OnClickListener {

    private Activity activity;

    public HealthShowGraidButtonListener(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void onClick(View v) {
        List<HealthRecord> healthRecords = HealthRecordService.instance().query(null);

        if (healthRecords == null || healthRecords.size() <= 0) {
            return;
        }

        // 展示列表事件
        GridLayout contentGrid = activity.findViewById(R.id.health_contentGrid);
        if (contentGrid == null) {
            return;
        }

        for (HealthRecord healthRecord : healthRecords) {
            TextView titleView = new TextView(activity);
            titleView.setText(healthRecord.getTitle());
            contentGrid.addView(titleView);
        }
    }
}
