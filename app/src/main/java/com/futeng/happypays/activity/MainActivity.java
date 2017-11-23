package com.futeng.happypays.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;
import com.alibaba.fastjson.JSON;
import com.futeng.happypays.R;
import com.futeng.happypays.activity.allgridview.ShapeActivity;
import com.futeng.happypays.fragment.FirstFragment;
import com.futeng.happypays.fragment.FirstFragments;
import com.futeng.happypays.fragment.FouthFragments;
import com.futeng.happypays.fragment.SecondFragment;
import com.futeng.happypays.fragment.ThirdFragments;
import com.futeng.happypays.tools.Base64;
import com.futeng.happypays.tools.Config;
import com.futeng.happypays.tools.MyApp;
import com.futeng.happypays.tools.MyCallBack;
import com.futeng.happypays.tools.MyProgressCallBack;
import com.futeng.happypays.tools.SPUtils;
import com.futeng.happypays.tools.UserInfo;
import com.futeng.happypays.tools.XUtil;
import com.futeng.happypays.utils.JifenBean;
import com.futeng.happypays.utils.JifenguizeBean;
import com.futeng.happypays.utils.UpDownBean;
import com.futeng.happypays.utils.User;
import com.futeng.happypays.utils.WalletBean;
import com.silencezwm.libs.app.Comm;

public class MainActivity extends FragmentActivity implements View.OnClickListener {

    @InjectView(R.id.cont)
    FrameLayout cont;
    @InjectView(R.id.fgone)
    RelativeLayout fgone;
    @InjectView(R.id.fgtwo)
    RelativeLayout fgtwo;
    @InjectView(R.id.fgthree)
    RelativeLayout fgthree;
    @InjectView(R.id.fgfour)
    RelativeLayout fgfour;
    @InjectView(R.id.img_fgone)
    ImageView imgFgone;
    @InjectView(R.id.img_fgtwo)
    ImageView imgFgtwo;
    @InjectView(R.id.img_fgthree)
    ImageView imgFgthree;
    @InjectView(R.id.img_fgfour)
    ImageView imgFgfour;
    @InjectView(R.id.txt_fgone)
    TextView txtFgone;
    @InjectView(R.id.txt_fgtwo)
    TextView txtFgtwo;
    @InjectView(R.id.txt_fgthree)
    TextView txtFgthree;
    @InjectView(R.id.txt_fgfour)
    TextView txtFgfour;
    @InjectView(R.id.fgfive)
    RelativeLayout fgfive;

    private FirstFragments f1;
    private SecondFragment f2;
    private ThirdFragments f3;
    private FouthFragments f4;
    private ShapeActivity f5;
    private UserInfo userInfo;

    private ProgressDialog progressDialog;

    String versionName;
    int versionCode;
    PackageInfo info;
    String packageName;

    String UrlPath;

    private PackageManager pManager;
    private WalletBean walletBean = null;

    private String phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        phone=SPUtils.getString(getApplication(),"phone");
        loginstate(phone);

        fgone.setOnClickListener(this);
        fgtwo.setOnClickListener(this);
        fgthree.setOnClickListener(this);
        fgfour.setOnClickListener(this);
        fgfive.setOnClickListener(this);


        setDefaultFragment();
        getVersion();
        downloadApk();

        //获取登入patate赋值
        Integer pstate7 = SPUtils.getInt(this, "pState");
        if (pstate7 == 1) {
            Intent intent = new Intent();
            intent.putExtra("AmNumber", SPUtils.getString(MainActivity.this, "AmNumber"));
            String amNumber = SPUtils.getString(MainActivity.this, "AmNumber");

            jifen(amNumber);
            Integral(amNumber, 2 + "");
        } else {
            Log.e("----", "58915891");
        }
    }

    private void setDefaultFragment() {
        //开启
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        f1 = new FirstFragments();
        transaction.replace(R.id.cont, new FirstFragments());
        transaction.commit();

        imgFgone.setImageDrawable(getResources().getDrawable(R.mipmap.wallet));
        imgFgtwo.setImageDrawable(getResources().getDrawable(R.mipmap.noreceipt));
        imgFgthree.setImageDrawable(getResources().getDrawable(R.mipmap.noincome));
        imgFgfour.setImageDrawable(getResources().getDrawable(R.mipmap.nomine));

        txtFgone.setTextColor(getResources().getColor(R.color.home));
        txtFgtwo.setTextColor(getResources().getColor(R.color.hui));
        txtFgthree.setTextColor(getResources().getColor(R.color.hui));
        txtFgfour.setTextColor(getResources().getColor(R.color.hui));

        Intent intert = getIntent();
        int id = intert.getIntExtra("grxx", -1);
        if (id > 0) {
            System.out.println("aaa" + id);
            if (id == 2) {
                transaction.replace(R.id.cont, new SecondFragment()); //这里是指定跳转到指定的fragment
            } else if (id == 3) {
                transaction.replace(R.id.cont, new ThirdFragments()); //这里是指定跳转到指定的fragment
            } else if (id == 4) {
                transaction.replace(R.id.cont, new FouthFragments()); //这里是指定跳转到指定的fragment
            }
        }
    }

    @Override
    public void onClick(View v) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        switch (v.getId()) {
            case R.id.fgone:
                //防止单击界面过快 界面叠加
                if (Comm.isFastDoubleClick(1000)) {
                    return;
                }
                loginstate(phone);
                //获取钱包对应的数据
                wallet(SPUtils.getString(getApplicationContext(), "AmNumber"));

                if (f1 == null) {
                    f1 = new FirstFragments();
                }
                // 使用当前Fragment的布局替代id_content的控件
                // coo就是你在布局里放的fragment
                transaction.replace(R.id.cont, new FirstFragments());

                imgFgone.setImageDrawable(getResources().getDrawable(R.mipmap.wallet));
                imgFgtwo.setImageDrawable(getResources().getDrawable(R.mipmap.noreceipt));
                imgFgthree.setImageDrawable(getResources().getDrawable(R.mipmap.noincome));
                imgFgfour.setImageDrawable(getResources().getDrawable(R.mipmap.nomine));

                txtFgone.setTextColor(getResources().getColor(R.color.home));
                txtFgtwo.setTextColor(getResources().getColor(R.color.hui));
                txtFgthree.setTextColor(getResources().getColor(R.color.hui));
                txtFgfour.setTextColor(getResources().getColor(R.color.hui));
                break;
            case R.id.fgtwo:
                //防止单击界面过快 界面叠加
                if (Comm.isFastDoubleClick(1000)) {
                    return;
                }
                loginstate(phone);
                if (f2 == null) {
                    f2 = new SecondFragment();
                }
                transaction.replace(R.id.cont, new SecondFragment());

                imgFgone.setImageDrawable(getResources().getDrawable(R.mipmap.nowallet));
                imgFgtwo.setImageDrawable(getResources().getDrawable(R.mipmap.receipt));
                imgFgthree.setImageDrawable(getResources().getDrawable(R.mipmap.noincome));
                imgFgfour.setImageDrawable(getResources().getDrawable(R.mipmap.nomine));

                txtFgone.setTextColor(getResources().getColor(R.color.hui));
                txtFgtwo.setTextColor(getResources().getColor(R.color.home));
                txtFgthree.setTextColor(getResources().getColor(R.color.hui));
                txtFgfour.setTextColor(getResources().getColor(R.color.hui));

                break;
            case R.id.fgthree:
                //防止单击界面过快 界面叠加
                if (Comm.isFastDoubleClick(1000)) {
                    return;
                }
                loginstate(phone);
                if (f3 == null) {
                    f3 = new ThirdFragments();
                }
                transaction.replace(R.id.cont, new ThirdFragments());

                imgFgone.setImageDrawable(getResources().getDrawable(R.mipmap.nowallet));
                imgFgtwo.setImageDrawable(getResources().getDrawable(R.mipmap.noreceipt));
                imgFgthree.setImageDrawable(getResources().getDrawable(R.mipmap.income));
                imgFgfour.setImageDrawable(getResources().getDrawable(R.mipmap.nomine));

                txtFgone.setTextColor(getResources().getColor(R.color.hui));
                txtFgtwo.setTextColor(getResources().getColor(R.color.hui));
                txtFgthree.setTextColor(getResources().getColor(R.color.home));
                txtFgfour.setTextColor(getResources().getColor(R.color.hui));

                break;
            case R.id.fgfour:
                //防止单击界面过快 界面叠加
                if (Comm.isFastDoubleClick(1000)) {
                    return;
                }
                loginstate(phone);
                if (f4 == null) {
                    f4 = new FouthFragments();
                }
                transaction.replace(R.id.cont, new FouthFragments());

                imgFgone.setImageDrawable(getResources().getDrawable(R.mipmap.nowallet));
                imgFgtwo.setImageDrawable(getResources().getDrawable(R.mipmap.noreceipt));
                imgFgthree.setImageDrawable(getResources().getDrawable(R.mipmap.noincome));
                imgFgfour.setImageDrawable(getResources().getDrawable(R.mipmap.mine));


                txtFgone.setTextColor(getResources().getColor(R.color.hui));
                txtFgtwo.setTextColor(getResources().getColor(R.color.hui));
                txtFgthree.setTextColor(getResources().getColor(R.color.hui));
                txtFgfour.setTextColor(getResources().getColor(R.color.home));

                break;

            case R.id.fgfive:
                if (f5 == null) {
                    f5 = new ShapeActivity();
                }
                transaction.replace(R.id.cont, new ShapeActivity());

                imgFgone.setImageDrawable(getResources().getDrawable(R.mipmap.nowallet));
                imgFgtwo.setImageDrawable(getResources().getDrawable(R.mipmap.noreceipt));
                imgFgthree.setImageDrawable(getResources().getDrawable(R.mipmap.noincome));
                imgFgfour.setImageDrawable(getResources().getDrawable(R.mipmap.nomine));


                txtFgone.setTextColor(getResources().getColor(R.color.hui));
                txtFgtwo.setTextColor(getResources().getColor(R.color.hui));
                txtFgthree.setTextColor(getResources().getColor(R.color.hui));
                txtFgfour.setTextColor(getResources().getColor(R.color.hui));



                break;

        }
        //事物提交
        transaction.commit();
    }

    private void jifen(String amNumber) {

        Map<String, Object> map = new HashMap<>();
        map.put("amNumber", amNumber);

        XUtil.Post(Config.AMAccountServlet_URL, map, new MyCallBack<String>() {

            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);

                JifenBean jifenBean = JSON.parseObject(result, JifenBean.class);

                SPUtils.put(getApplicationContext(), "accountIntegral", jifenBean.getMap().getAccountIntegral());
                SPUtils.put(getApplicationContext(), "accountMoney", jifenBean.getMap().getAccountMoney());
                SPUtils.put(getApplicationContext(), "actAccountMoney", jifenBean.getMap().getActAccountMoney());
                SPUtils.put(getApplicationContext(), "ableIntegral", jifenBean.getMap().getAbleIntegral());

            }
        });

    }

    private void Integral(String amNumber, String mode) {
        Map<String, Object> map = new HashMap<>();
        map.put("amNumber", amNumber);
        map.put("mode", mode);
        XUtil.Post(Config.IntegralWithdrawalsServlet_URL, map, new MyCallBack<String>() {

            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                JifenguizeBean jifenguizeBean = JSON.parseObject(result, JifenguizeBean.class);
                String Rule = Base64.getFromBase64(jifenguizeBean.getMap().getIpRule());
                SPUtils.put(MainActivity.this, "rule", Rule);
            }
        });
    }

    //检查更新
    public void getVersion() {
        try {
            info = getPackageManager().getPackageInfo(getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        versionName = info.versionName;
        versionCode = info.versionCode;
        packageName = info.packageName;
        Log.e(this.getClass() + "+++versionName+++", versionName + "//" + versionCode + "//" + packageName);
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
                SPUtils.put(getApplication(), "VersionNumber", upDownBean.getMap().getVersionNumber());
                SPUtils.put(getApplication(), "UrlPath", upDownBean.getMap().getUrlPath());
                SPUtils.put(getApplication(), "avForce", upDownBean.getMap().getAvForce());
                UrlPath = upDownBean.getMap().getUrlPath();
                if (versionName.equals(upDownBean.getMap().getVersionNumber())) {
                    Log.e("版本号一致", "版本号一致");
                } else {
                    Log.e("avForce456", upDownBean.getMap().getAvForce() + "");
                    if (0 == upDownBean.getMap().getAvForce()) {
                        Log.e("不需要更新", "不需要更新");
                    } else if (1 == upDownBean.getMap().getAvForce()) {
                        Dialog();
                    }
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
                    Toast.makeText(MainActivity.this, "成功", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onError(Throwable ex, boolean isOnCallback) {
                    super.onError(ex, isOnCallback);
                    progressDialog.dismiss();
                    Toast.makeText(MainActivity.this, "失败", Toast.LENGTH_SHORT).show();
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

    //版本升级弹框
    private void Dialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
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
//        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                dialog.dismiss();
//            }
//        });
        builder.setCancelable(false);
        builder.create().show();
    }

    private void wallet(String amnumber) {
        Map<String, Object> map = new HashMap<>();
        map.put("mode", 1);
        map.put("amNumber", amnumber);
        XUtil.Post(Config.WalletServlet_URL, map, new MyCallBack<String>() {

            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                walletBean = JSON.parseObject(result, WalletBean.class);
                if ("00".equals(walletBean.getCode()) && walletBean.getMap() != null) {
                    SPUtils.put(getApplication(), "toDayActExaMoney", walletBean.getMap().getToDayActExaMoney());
                    SPUtils.put(getApplication(), "sumTranMoney", walletBean.getMap().getSumTranMoney());
                    SPUtils.put(getApplication(), "allCount", walletBean.getMap().getAllCount());
                    SPUtils.put(getApplication(), "accountMoney", walletBean.getMap().getAccountMoney());
                    SPUtils.put(getApplication(), "toDaySumTranMoeny", walletBean.getMap().getToDaySumTranMoeny());
                } else {
                    if ("数据缺失".equals(walletBean.getFailMessage())){
                        return;
                    }else{
                        MyApp.getInstance().showToast(walletBean.getFailMessage());
                    }
                }
            }
        });
    }

    private void loginstate(String phone){
        Map<String,Object>map=new HashMap<>();
        map.put("phone",phone);
        XUtil.Post(Config.LoginStateServlet_URL,map,new MyCallBack<String>(){
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                User userStr = JSON.parseObject(result, User.class);
                if ("00".equals(userStr.getCode())){
                    //保存账号供调用
                    SPUtils.put(getApplication(), "phone", userStr.getMap().getPPhone());
                    SPUtils.put(getApplication(), "AmNumber", userStr.getMap().getAmNumber());
                    SPUtils.put(getApplication(), "pState", userStr.getMap().getPState());
                    SPUtils.put(getApplication(),"amName",userStr.getMap().getAmName());
                    SPUtils.put(getApplication(),"isOK",true);
                    SPUtils.put(getApplication(),"amIdNumber",userStr.getMap().getAmIdNumber());
                    SPUtils.put(getApplication(),"amAddress",userStr.getMap().getAmAddress());
                    SPUtils.put(getApplication(),"amPerson",userStr.getMap().getAmPerson());
                    SPUtils.put(getApplication(),"gradeName",userStr.getMap().getGradeName());
                    Log.e("++++","单击");
                }else if ("99".equals(userStr.getCode())){
                    MyApp.getInstance().showToast(userStr.getFailMessage());
                }
            }
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);

            }
        });
    }

}

