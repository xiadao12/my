package com.zcy.mytool.health.listener;

import android.app.Activity;
import android.view.Gravity;
import android.view.View;
import android.widget.GridLayout;
import android.widget.TextView;

import com.zcy.mytool.R;
import com.zcy.mytool.health.model.HealthRecord;
import com.zcy.mytool.health.service.HealthRecordService;

import java.text.SimpleDateFormat;
import java.util.Date;
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
        GridLayout recordGrid = (GridLayout) activity.findViewById(R.id.health_recordGrid);
        if (recordGrid == null) {
            return;
        }

        // 先移除所有的视图
        recordGrid.removeAllViews();

        HealthRecord healthRecord;
        GridLayout.LayoutParams titleParams;
        TextView titleView;
        GridLayout.LayoutParams createTimeParams;
        TextView createTimeView;
        for (int i = 0; i < healthRecords.size(); i++) {

            healthRecord = healthRecords.get(i);

/*          GridLayout.Spec rowSpec  rowSpec = GridLayout.spec(i);     //设置它的行和列
            GridLayout.Spec columnSpec = columnSpec = GridLayout.spec(0);
            GridLayout.LayoutParams params = new GridLayout.LayoutParams(rowSpec, columnSpec);*/
            titleParams = new GridLayout.LayoutParams();
            titleParams.setGravity(Gravity.CENTER);
            titleView = new TextView(activity);
            titleView.setText(healthRecord.getTitle());
            recordGrid.addView(titleView);

            createTimeParams = new GridLayout.LayoutParams();
            createTimeParams.setGravity(Gravity.CENTER);
            createTimeView = new TextView(activity);
            Date createTime = healthRecord.getCreateTime();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ");
            String createTimeString = formatter.format(createTime);
            createTimeView.setText(createTimeString);
            recordGrid.addView(createTimeView);
        }
    }
}
