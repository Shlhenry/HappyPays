package com.futeng.happypays.activity.fgmentfour;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.ImageView;

import com.futeng.happypays.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * 关于我们
 */
public class OurActivity extends Activity {

    @InjectView(R.id.our_back)
    ImageView ourBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_our);
        ButterKnife.inject(this);
    }

    @OnClick(R.id.our_back)
    public void onClick() {
        finish();
    }
}
