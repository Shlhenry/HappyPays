package com.futeng.happypays.activity.fgmentfour;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import com.futeng.happypays.R;
import com.futeng.happypays.activity.LoginActivity;

public class SetActivity extends Activity {

    @InjectView(R.id.fg_pw)
    LinearLayout fgPw;
    @InjectView(R.id.exit)
    TextView exit;
    @InjectView(R.id.set_back)
    ImageView setBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set);
        ButterKnife.inject(this);
    }

    @OnClick({R.id.fg_pw, R.id.exit,R.id.set_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fg_pw:
                startActivity(new Intent(this, PasswordAgain.class));
                break;
            case R.id.exit:
                startActivity(new Intent(this, LoginActivity.class));
                finish();
                break;
            case R.id.set_back:
                finish();
                break;
        }
    }
}
