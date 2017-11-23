package com.futeng.happypays.activity.sign;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

import com.alibaba.fastjson.JSON;
import com.futeng.happypays.R;
import com.futeng.happypays.tools.Config;
import com.futeng.happypays.tools.MyApp;
import com.futeng.happypays.tools.MyCallBack;
import com.futeng.happypays.tools.SPUtils;
import com.futeng.happypays.tools.XUtil;
import com.futeng.happypays.utils.SignBean;
import com.mylhyl.circledialog.CircleDialog;
import com.mylhyl.circledialog.params.ProgressParams;

public class SignActivity extends FragmentActivity {

    RecyclerView recyclerView;
    @InjectView(R.id.sign_back)
    ImageView signBack;
    @InjectView(R.id.nowSignIntegral)
    TextView nowSignIntegral;
    @InjectView(R.id.nowSignSongIntegral)
    TextView nowSignSongIntegral;
    @InjectView(R.id.nowSignAllIntegral)
    TextView nowSignAllIntegral;
    @InjectView(R.id.signok)
    TextView signok;
    @InjectView(R.id.signCount)
    TextView signCount;
    @InjectView(R.id.btn_sign)
    LinearLayout btnSign;
    @InjectView(R.id.nowtime)
    TextView nowtime;

    private List<Integer> list = new ArrayList<>();
    private SignBean signBean = null;
    private DialogFragment dialogFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signnew);
        ButterKnife.inject(this);

        //获取商户号发起签到请求
        String amnumber = SPUtils.getString(getApplication(), "AmNumber");
        appsign(amnumber);

        nowtime();

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 7));
    }


    @OnClick({R.id.sign_back, R.id.btn_sign})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.sign_back:
                finish();
                break;
            case R.id.btn_sign:
                dialogFragment = new CircleDialog.Builder(SignActivity.this)
                        .setProgressText("正在签到，请稍等。。。")
                        .setCanceledOnTouchOutside(false)
                        .setProgressStyle(ProgressParams.STYLE_SPINNER)
                        .show();
                String amnumber = SPUtils.getString(getApplication(), "AmNumber");
                btnsign(amnumber);
                break;
        }
    }


    //获取签到数据
    private void appsign(String amnumber) {
        Map<String, Object> map = new HashMap<>();
        map.put("mode", 1);
        map.put("amNumber", amnumber);
        XUtil.Post(Config.AppSignServlet_URL, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                signBean = JSON.parseObject(result, SignBean.class);
                if ("00".equals(signBean.getCode()) && signBean.getMap() != null) {
                    nowSignIntegral.setText(signBean.getMap().getNowSignIntegral() + "");
                    nowSignSongIntegral.setText(signBean.getMap().getNowSignSongIntegral() + "");
                    nowSignAllIntegral.setText(signBean.getMap().getNowSignAllIntegral() + "");
                    signCount.setText(signBean.getMap().getSignCount() + "");
                    int ok = signBean.getMap().getAsState();
                    if (ok == 1) {
                        signok.setText("已签到");
                    } else {
                        signok.setText("未签到");
                    }
                    if (signBean.getList() != null) {
                        for (int i = 0; i < signBean.getList().size(); i++) {
                            Log.e("111", signBean.getList().get(i).getAsTime());
                            String temp = signBean.getList().get(i).getAsTime();
                            int value = Integer.parseInt(temp.substring(temp.length() - 2, temp.length()));
                            list.add(value);
                            recyclerView.setAdapter(new AdapterSign(getApplicationContext(), list));
                            recyclerView.getAdapter().notifyDataSetChanged();
                        }
                    } else {
                        recyclerView.setAdapter(new AdapterSign(getApplicationContext(), list));
                        recyclerView.getAdapter().notifyDataSetChanged();
                    }


                }
            }
        });
    }

    //单击签到
    private void btnsign(String amnumber) {
        Map<String, Object> map = new HashMap<>();
        map.put("mode", 2);
        map.put("amNumber", amnumber);
        XUtil.Post(Config.AppSignServlet_URL, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                dialogFragment.dismiss();
                signBean = JSON.parseObject(result, signBean.getClass());
                if ("00".equals(signBean.getCode())) {
                    //获取商户号发起签到请求
                    String amnumber = SPUtils.getString(getApplication(), "AmNumber");
                    appsign(amnumber);
                } else {
                    MyApp.getInstance().showToast(signBean.getMessage());
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
                dialogFragment.dismiss();
            }
        });
    }

    private void nowtime() {
        //获取当前的年
        SimpleDateFormat year_formatter = new SimpleDateFormat("yyyy");
        Date year_curDate = new Date(System.currentTimeMillis());
        //获取当前的月
        SimpleDateFormat month_formatter = new SimpleDateFormat("MM");
        Date month_curDate1 = new Date(System.currentTimeMillis());
        //获取当前的日
        SimpleDateFormat day_formatter = new SimpleDateFormat("dd");
        Date day_curDate1 = new Date(System.currentTimeMillis());


        String year = year_formatter.format(year_curDate);
        String month = month_formatter.format(month_curDate1);
        String day = day_formatter.format(day_curDate1);

        nowtime.setText(year + "年" + month + "月" + day + "日");
    }


}
