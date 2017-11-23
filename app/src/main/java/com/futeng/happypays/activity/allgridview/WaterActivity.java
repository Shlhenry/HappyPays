package com.futeng.happypays.activity.allgridview;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.futeng.happypays.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * 缴水费--无接口  暂停使用
 */
public class WaterActivity extends Activity {


    @InjectView(R.id.water_back)
    ImageView waterBack;
    @InjectView(R.id.water_btn)
    Button waterBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_water);
        ButterKnife.inject(this);
    }

    @OnClick({R.id.water_back, R.id.water_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.water_back:
                finish();
                break;
            case R.id.water_btn:
                Toast.makeText(this,"程序猿正在努力开发中",Toast.LENGTH_SHORT).show();
                finish();
                break;
        }
    }
}
