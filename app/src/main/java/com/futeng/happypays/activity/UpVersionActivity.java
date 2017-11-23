package com.futeng.happypays.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.futeng.happypays.R;
import com.futeng.happypays.tools.Config;
import com.futeng.happypays.tools.MyCallBack;
import com.futeng.happypays.tools.MyProgressCallBack;
import com.futeng.happypays.tools.SPUtils;
import com.futeng.happypays.tools.XUtil;
import com.futeng.happypays.utils.UpDownBean;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class UpVersionActivity extends Activity {

    @InjectView(R.id.up_btn)
    Button upBtn;
    @InjectView(R.id.version_back)
    ImageView versionBack;

    private ProgressDialog progressDialog;
    String versionName;
    int versionCode;
    PackageInfo info;
    String packageName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_up_version);
        ButterKnife.inject(this);
    }

    public void getVersion() {
        try {
            info = getPackageManager().getPackageInfo(getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        versionName = info.versionName;
        versionCode = info.versionCode;
        packageName = info.packageName;
        Log.e("+++versionName+++", versionName + "//" + versionCode + "//" + packageName);
    }

    protected void downloadApk() {
        getVersion();
        Map<String, Object> map = new HashMap<>();
        map.put("packageName", packageName);

        XUtil.Post(Config.Version_URL, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                UpDownBean upDownBean = JSON.parseObject(result, UpDownBean.class);
                Log.e("+++++", upDownBean.getMap().getVersionNumber());
                Log.e("+++++", upDownBean.getMap().getUrlPath());
                SPUtils.put(getApplicationContext(), "VersionNumber", upDownBean.getMap().getVersionNumber());
                SPUtils.put(getApplicationContext(), "UrlPath", upDownBean.getMap().getUrlPath());

                if (versionName.equals(upDownBean.getMap().getVersionNumber())) {
                    Log.e("+++++++------", versionName + "///" + upDownBean.getMap().getVersionNumber());
                    Toast.makeText(UpVersionActivity.this, "您当前版本为最新版，暂无更新", Toast.LENGTH_LONG).show();
                } else {
                    Dialog();
                }
            }
        });
    }

    private void downloadfile() {
        progressDialog = new ProgressDialog(this);
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            //文件下载地址
            String url = "http://120.27.138.219:8083/app-new/happypays.apk";
            //文件保存在本地的路径
            String filepath = Environment.getExternalStorageDirectory().getAbsolutePath()
                    + File.separator + "happypays.apk";
            XUtil.DownLoadFile(url, filepath, new MyProgressCallBack<File>() {
                @Override
                public void onSuccess(File result) {
                    super.onSuccess(result);
                    File file = result;
                    installAPK(file);
                    progressDialog.dismiss();
                    Toast.makeText(UpVersionActivity.this, "成功", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onError(Throwable ex, boolean isOnCallback) {
                    super.onError(ex, isOnCallback);
                    progressDialog.dismiss();
                    Toast.makeText(UpVersionActivity.this, "失败", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onLoading(long total, long current, boolean isDownloading) {
                    super.onLoading(total, current, isDownloading);
                    progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                    progressDialog.setMessage("亲，努力下载中。。。");
                    progressDialog.show();
                    progressDialog.setMax((int) total);
                    progressDialog.setProgress((int) current);
                    progressDialog.setCanceledOnTouchOutside(false);
                }
            });
        }

    }

    /**
     * 开启安装APK页面的逻辑
     *
     * @param file 要安装的APK文件
     */
    private void installAPK(File file) {
        //系统应用界面，安装apk入口，看源码
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        startActivityForResult(intent, 0);
    }


    @OnClick({R.id.version_back, R.id.up_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.version_back:
                finish();
                break;
            case R.id.up_btn:
                downloadApk();
                break;
        }
    }

    //版本升级弹框
    private void Dialog(){
        AlertDialog.Builder builder=new AlertDialog.Builder(UpVersionActivity.this);
        builder.setTitle("检测到有新版本");
        builder.setMessage("确认更新？");
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
//                getVersion();
//                downloadApk();
                downloadfile();
                dialog.dismiss();

            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }
}
