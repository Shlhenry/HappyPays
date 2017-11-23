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
 * 缴电费--无接口 不能使用
 */
public class ElectricActivity extends Activity {


    @InjectView(R.id.electric_back)
    ImageView electricBack;
    @InjectView(R.id.electric_btn)
    Button electricBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_electric);
        ButterKnife.inject(this);


    }


    @OnClick({R.id.electric_back, R.id.electric_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.electric_back:
                finish();
                break;
            case R.id.electric_btn:
                Toast.makeText(this,"程序猿正在努力开发中",Toast.LENGTH_SHORT).show();
                finish();
                break;
        }
    }
}
