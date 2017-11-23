package com.futeng.happypays.activity.renzheng;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import com.futeng.happypays.R;
/**
 * 实名认证指引
 * */
public class RenzhengGuideActivity extends Activity {

    @InjectView(R.id.guild_back)
    ImageView guildBack;
    @InjectView(R.id.guild_next)
    TextView guildNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_renzheng_guide);
        ButterKnife.inject(this);
    }

    @OnClick({R.id.guild_back, R.id.guild_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.guild_back:
                finish();
                break;
            case R.id.guild_next:
                startActivity(new Intent(this,Renzheng_base.class));
                break;
        }
    }
}
