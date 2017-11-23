package com.futeng.happypays.activity.allgridview;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.ImageView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import com.futeng.happypays.R;
/**
 * 缴话费--无接口 不能使用
 */
public class TelmoneyActivity extends Activity {

    @InjectView(R.id.telmoney_back)
    ImageView telmoneyBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_telmoney);
        ButterKnife.inject(this);


    }

    @OnClick(R.id.telmoney_back)
    public void onClick() {
        finish();
    }
}
