package com.futeng.happypays.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.futeng.happypays.R;
import com.futeng.happypays.tools.CircleTextProgressbar;

public class textActivity extends AppCompatActivity {

    /**
     * 顺数进度条。
     */
    private CircleTextProgressbar mTvCount;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);

        // 和系统进度条一样，由0-100。
        mTvCount = (CircleTextProgressbar) findViewById(R.id.tv_count);
        mTvCount.setProgressType(CircleTextProgressbar.ProgressType.COUNT);

        mTvCount.reStart();

    }
}
