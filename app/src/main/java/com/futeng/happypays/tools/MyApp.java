package com.futeng.happypays.tools;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.futeng.happypays.utils.User;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;

import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/7.
 */
public class MyApp extends Application {

    private static MyApp instance;
    List<User>users=new ArrayList<>();
    public List<Activity> activityList = new ArrayList<>();


    public String VERSION = null;
//    public List<PlanBean> PLANLIST=new ArrayList<>();
//    public UserBean MYUSER = null;
    public int WINDOWWIDTHDP, WINDOWHEIGHTDP, WINDOWWIDTHPX, WINDOWHEIGHTPX;
    public float WINDOWDESITY;
    public SharedPreferences SHARE;
    public SharedPreferences.Editor EDITO;

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化xutil
        x.Ext.init(this);
        //设置输出debug模式
        x.Ext.setDebug(true);
        instance = this;
        //zxing初始化
        ZXingLibrary.initDisplayOpinion(this);
        //生成错误日志sdcard 截取日志到本地
//        CrashHandlers crashHandler = CrashHandlers.getInstance();
//        crashHandler.init(getApplicationContext());

    }


    private void setShare() {
        SHARE = getSharedPreferences("maca", MODE_PRIVATE);
        EDITO = SHARE.edit();
        String userstr = SHARE.getString("user", "");
        //MYUSER = JSON.parseObject(userstr, UserBean.class);
        String planstr = SHARE.getString("plan", "");
        //PLANLIST = JSON.parseArray(planstr, PlanBean.class);
//        if (PLANLIST==null){
//            PLANLIST=new ArrayList<>();
//        }
    }


    public static synchronized MyApp getInstance() {
        return instance;
    }
    public void showToast(String s) {
        Toast.makeText(instance.getApplicationContext(), s, Toast.LENGTH_SHORT).show();
    }
    public void addActivity(Activity activity) {
        activityList.add(activity);
    }
    private void setWindowHW() {
        WindowManager wm = (WindowManager) getApplicationContext()
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        WINDOWWIDTHPX = dm.widthPixels;// 屏幕宽度（像素）
        WINDOWHEIGHTPX = dm.heightPixels; // 屏幕高度（像素）
        WINDOWDESITY = dm.density;//屏幕密度（0.75 / 1.0 / 1.5）
        //屏幕宽度算法:屏幕宽度（像素）/屏幕密度
        WINDOWWIDTHDP = (int) (WINDOWWIDTHPX / WINDOWDESITY);//屏幕宽度(dp)
        WINDOWHEIGHTDP = (int) (WINDOWHEIGHTPX / WINDOWDESITY);//屏幕高度(dp)
    }

    /**
     * 返回当前程序版本名
     */
    public String getAppVersionName(Context context) {
        String versionName = "";
        try {
            // ---get the package info---
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
            versionName = pi.versionName;
            if (versionName == null || versionName.length() <= 0) {
                return "";
            }
        } catch (Exception e) {
            Log.e("VersionInfo", "Exception", e);
        }
        return versionName;
    }


    public void FinishActivity() {
        for (int i = 0; i < activityList.size(); i++) {
            activityList.get(i).finish();
        }
    }

    public void FinishLastActivity(int i) {
        for (int j = 0; j < i; j++) {
            if (activityList.size() == 2) {
                break;
            } else {
                Log.e("Finish", activityList.size() - 1 + ":i" + i);
                activityList.get(activityList.size() - 1).finish();
            }
        }
    }
}
