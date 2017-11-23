package com.futeng.happypays.activity.allgridview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.futeng.happypays.R;
import com.futeng.happypays.activity.MainActivity;
import com.futeng.happypays.activity.webview.WebviewEasydai;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
/***
 * 易速贷
 */
public class EastDaiActivity extends Activity {

    @InjectView(R.id.easy_back)
    ImageView easyBack;
    @InjectView(R.id.linearLayout_easy_one)
    LinearLayout linearLayoutEasyOne;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_east_dai);
        ButterKnife.inject(this);
    }

    @OnClick({R.id.easy_back, R.id.linearLayout_easy_one})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.easy_back:
                Intent show=new Intent(EastDaiActivity.this,MainActivity.class);
                show.putExtra("grxx",3);
                startActivity(show);
                finish();
                break;
            case R.id.linearLayout_easy_one:
                startActivity(new Intent(this,WebviewEasydai.class));
                break;
        }
    }
}
