package com.futeng.happypays.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.futeng.happypays.R;
import com.futeng.happypays.tools.SPUtils;

public class GuideBeforeActivity extends Activity {

    String versionCode;
    PackageInfo info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide_before);
        loadIntent();
    }

    //延迟
    private void loadIntent() {

        new Thread() {
            public void run() {
                try {
                    sleep(500);
                    getVersion();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    public void getVersion() {
        try {
            info = getPackageManager().getPackageInfo(getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
         versionCode = info.versionCode+"";
        Log.e("-------",versionCode+"");
        Log.e("-------",SPUtils.getInt(getApplication(),"code")+"");

        if (versionCode.equals(SPUtils.getInt(getApplication(),"code")+"")){
            startActivity(new Intent(GuideBeforeActivity.this,GuideActivity.class));
            Log.e("等于","等于");
                finish();
        }else{
            Log.e("不等于","不等于");
            startActivity(new Intent(GuideBeforeActivity.this,GuideViewpageActivity.class));
                finish();
        }
    }
}
