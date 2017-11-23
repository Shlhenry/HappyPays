package com.futeng.happypays.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.ImageView;

import com.futeng.happypays.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/4/6.
 */
public class AgreementActivity extends Activity {
    @InjectView(R.id.xieyi_back)
    ImageView xieyiBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_xieyi);
        ButterKnife.inject(this);

    }

    @OnClick(R.id.xieyi_back)
    public void onClick() {
        finish();
    }
}
