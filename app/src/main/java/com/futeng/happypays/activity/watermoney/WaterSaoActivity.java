package com.futeng.happypays.activity.watermoney;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.futeng.happypays.R;
import com.futeng.happypays.activity.MainActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class WaterSaoActivity extends Activity {

    @InjectView(R.id.watersao_back)
    ImageView watersaoBack;
    @InjectView(R.id.water_weixin)
    LinearLayout waterWeixin;
    @InjectView(R.id.water_zhifubao)
    LinearLayout waterZhifubao;
    @InjectView(R.id.water_yinlian)
    LinearLayout waterYinlian;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_water_sao);
        ButterKnife.inject(this);
    }

    @OnClick({R.id.watersao_back, R.id.water_weixin, R.id.water_zhifubao, R.id.water_yinlian})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.watersao_back:
                Intent show=new Intent(WaterSaoActivity.this,MainActivity.class);
                show.putExtra("grxx",3);
                startActivity(show);
                finish();
                break;
            case R.id.water_weixin:
                startActivity(new Intent(this,WaterwechatmoneyActivity.class));
                break;
            case R.id.water_zhifubao:
                startActivity(new Intent(this,waterzfbmoneyActivity.class));
                break;
            case R.id.water_yinlian:
                startActivity(new Intent(this,UnionPayActivity.class));
                break;
        }
    }
}
