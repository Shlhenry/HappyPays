package com.futeng.happypays.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.view.Window;
import android.widget.ImageView;

import org.xutils.x;

import java.util.HashMap;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.futeng.happypays.R;
import com.futeng.happypays.tools.CircleTextProgressbar;
import com.futeng.happypays.tools.Config;
import com.futeng.happypays.tools.MyApp;
import com.futeng.happypays.tools.MyCallBack;
import com.futeng.happypays.tools.SPUtils;
import com.futeng.happypays.tools.XUtil;
import com.futeng.happypays.utils.ControllerBean;
import com.futeng.happypays.utils.User;

/**
 * Created by Administrator on 2017/2/22.
 */
public class GuideActivity extends FragmentActivity {
    @InjectView(R.id.tv_red_skip)
    CircleTextProgressbar tvRedSkip;

    private static boolean i = true;
    @InjectView(R.id.adpage)
    ImageView adpage;


//    public String rember= SPUtils.getString(getApplication(),"rember");

    private DialogFragment dialogFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.zhiyingye);
        ButterKnife.inject(this);

        tvRedSkip.setOutLineColor(Color.parseColor("#4faaff"));
        tvRedSkip.setInCircleColor(Color.parseColor("#50c0c0c0"));
        tvRedSkip.setProgressColor(Color.parseColor("#ffffff"));
        tvRedSkip.setProgressLineWidth(5);
        //启动倒计时
        tvRedSkip.reStart();

        loadIntent();


        Glide.with(this)
                .load("http://120.27.194.146/adpage.png")
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .dontAnimate().placeholder
                (R.mipmap.adpage).into(adpage);


    }

    @OnClick(R.id.tv_red_skip)
    public void onViewClicked() {
        if ("1".equals(SPUtils.getString(getApplication(), "rember"))) {
            String username = SPUtils.getString(getApplication(), "username");
            String password = SPUtils.getString(getApplication(), "password");
            login(username, password);
        } else {
            i = false;
            startActivity(new Intent(GuideActivity.this, LoginActivity.class));
//            finish();
        }

    }

    //延迟
    private void loadIntent() {
        new Thread() {
            public void run() {
                try {
                    sleep(3000);
                    if (i) {
                        if ("1".equals(SPUtils.getString(getApplication(), "rember"))) {
                            String username = SPUtils.getString(getApplication(), "username");
                            String password = SPUtils.getString(getApplication(), "password");
                            login(username, password);
                        } else {
                            startActivity(new Intent(GuideActivity.this, LoginActivity.class));
                            finish();
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    private void login(String phone, String password) {
        Map<String, Object> map = new HashMap<>();
        map.put("phone", phone);
        map.put("pwd", password);
        XUtil.Post(Config.Login__URL, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                User userStr = JSON.parseObject(result, User.class);

                if ("00".equals(userStr.getCode())) {
                    switchservlet("1");
                    //保存账号供调用
                    SPUtils.put(getApplicationContext(), "phone", userStr.getMap().getPPhone());
                    SPUtils.put(getApplicationContext(), "AmNumber", userStr.getMap().getAmNumber());
                    SPUtils.put(getApplicationContext(), "pState", userStr.getMap().getPState());
                    SPUtils.put(getApplicationContext(), "amName", userStr.getMap().getAmName());
                    SPUtils.put(getApplicationContext(), "isOK", true);
                    SPUtils.put(getApplicationContext(), "amIdNumber", userStr.getMap().getAmIdNumber());
                    SPUtils.put(getApplicationContext(), "amAddress", userStr.getMap().getAmAddress());
                    SPUtils.put(getApplicationContext(), "amPerson", userStr.getMap().getAmPerson());
                    SPUtils.put(getApplicationContext(), "gradeName", userStr.getMap().getGradeName());

                } else if ("99".equals(userStr.getCode())) {
                    MyApp.getInstance().showToast(userStr.getFailMessage());
                }
            }
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
            }
        });
    }

    private void switchservlet(String mode) {
        Map<String, Object> map = new HashMap<>();
        map.put("mode", mode);
        XUtil.Post(Config.SwitchServlet_URL, map, new MyCallBack<String>() {

            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                ControllerBean controllerBean = JSON.parseObject(result, ControllerBean.class);

                if ("00".equals(controllerBean.getCode())) {
                    SPUtils.put(getApplication(), "101", controllerBean.getList().get(0).getSwitchName());
                    SPUtils.put(getApplication(), "qrCode", controllerBean.getList().get(0).getSwitchValue());

                    SPUtils.put(getApplication(), "102", controllerBean.getList().get(1).getSwitchName());
                    SPUtils.put(getApplication(), "mposReceivables", controllerBean.getList().get(1).getSwitchValue());

                    SPUtils.put(getApplication(), "103", controllerBean.getList().get(2).getSwitchName());
                    SPUtils.put(getApplication(), "noCardShortcut", controllerBean.getList().get(2).getSwitchValue());

                    SPUtils.put(getApplication(), "104", controllerBean.getList().get(3).getSwitchName());
                    SPUtils.put(getApplication(), "qrCodeWater", controllerBean.getList().get(3).getSwitchValue());

                    SPUtils.put(getApplication(), "105", controllerBean.getList().get(4).getSwitchName());
                    SPUtils.put(getApplication(), "mposReceivablesWater", controllerBean.getList().get(4).getSwitchValue());

                    SPUtils.put(getApplication(), "106", controllerBean.getList().get(5).getSwitchName());
                    SPUtils.put(getApplication(), "noCardShortcutWater", controllerBean.getList().get(5).getSwitchValue());

                    SPUtils.put(getApplication(), "107", controllerBean.getList().get(6).getSwitchName());
                    SPUtils.put(getApplication(), "creditCardApplication", controllerBean.getList().get(6).getSwitchValue());

                    SPUtils.put(getApplication(), "108", controllerBean.getList().get(7).getSwitchName());
                    SPUtils.put(getApplication(), "creditCardPayment", controllerBean.getList().get(7).getSwitchValue());

                    SPUtils.put(getApplication(), "109", controllerBean.getList().get(8).getSwitchName());
                    SPUtils.put(getApplication(), "quickAndEasyCredit", controllerBean.getList().get(8).getSwitchValue());

                    SPUtils.put(getApplication(), "110", controllerBean.getList().get(9).getSwitchName());
                    SPUtils.put(getApplication(), "phoneReplenishing", controllerBean.getList().get(9).getSwitchValue());

                    SPUtils.put(getApplication(), "111", controllerBean.getList().get(10).getSwitchName());
                    SPUtils.put(getApplication(), "wateFeePaid", controllerBean.getList().get(10).getSwitchValue());

                    SPUtils.put(getApplication(), "112", controllerBean.getList().get(11).getSwitchName());
                    SPUtils.put(getApplication(), "payElectricityBill", controllerBean.getList().get(11).getSwitchValue());

                    SPUtils.put(getApplication(), "113", controllerBean.getList().get(12).getSwitchName());
                    SPUtils.put(getApplication(), "auth", controllerBean.getList().get(12).getSwitchValue());

                    SPUtils.put(getApplication(), "114", controllerBean.getList().get(13).getSwitchName());
                    SPUtils.put(getApplication(), "myTeam", controllerBean.getList().get(13).getSwitchValue());

                    SPUtils.put(getApplication(), "115", controllerBean.getList().get(14).getSwitchName());
                    SPUtils.put(getApplication(), "sharingCeremony", controllerBean.getList().get(14).getSwitchValue());

                    startActivity(new Intent(GuideActivity.this, MainActivity.class));
                    finish();
                }
            }
        });
    }


}