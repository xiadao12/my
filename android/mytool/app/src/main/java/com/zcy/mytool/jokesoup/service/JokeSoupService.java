package com.zcy.mytool.jokesoup.service;

import android.view.View;
import android.widget.TextView;

import com.zcy.mytool.MainActivity;
import com.zcy.mytool.R;

public class JokeSoupService {

    private static JokeSoupService jokeSoupService;

    public static JokeSoupService instance() {
        if (jokeSoupService == null) {
            synchronized (JokeSoupService.class) {
                if (jokeSoupService == null) {
                    jokeSoupService = new JokeSoupService();
                }
            }
        }
        return jokeSoupService;
    }

    /**
     * 初始化
     *
     * @param mainActivity
     */
    public void init(MainActivity mainActivity) {
        TextView jokesoupContentView = mainActivity.findViewById(R.id.id_jokesoup_content);
        jokesoupContentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jokesoupContentView.setText(ContentService.getInstance().getNextContent());
            }
        });
    }

}
