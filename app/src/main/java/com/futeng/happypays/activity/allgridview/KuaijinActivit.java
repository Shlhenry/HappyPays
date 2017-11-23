package com.futeng.happypays.activity.allgridview;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;

import com.futeng.happypays.R;
/**
 * 贷款----快金
 */
public class KuaijinActivit extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_kuaijin);
    }
}
