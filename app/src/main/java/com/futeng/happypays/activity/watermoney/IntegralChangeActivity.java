package com.futeng.happypays.activity.watermoney;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import com.alibaba.fastjson.JSON;
import com.andview.refreshview.XRefreshView;
import com.futeng.happypays.R;
import com.futeng.happypays.adapter.ChangeMoneyAdapter;
import com.futeng.happypays.tools.Config;
import com.futeng.happypays.tools.MyApp;
import com.futeng.happypays.tools.MyCallBack;
import com.futeng.happypays.tools.SPUtils;
import com.futeng.happypays.tools.XUtil;
import com.futeng.happypays.utils.WaterBean;

public class IntegralChangeActivity extends Activity {

    @InjectView(R.id.integral_back)
    ImageView integralBack;
    @InjectView(R.id.integral_lv_list)
    ListView integralLvList;
    @InjectView(R.id.integral_view)
    XRefreshView integralView;


    private WaterBean waterBean = null;
    private ChangeMoneyAdapter changeMoneyAdapter = null;

    private int curpage = 1;
    private int count = 20;
    private int waterType = 3;

    List<WaterBean.ListBean> ShopList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_integral_change);
        ButterKnife.inject(this);

        String amNumber1 = SPUtils.getString(this, "AmNumber");
        getSubjectList(0, amNumber1, waterType,count);
        initRefresh();
    }


    private void initRefresh() {
        integralView = (XRefreshView) findViewById(R.id.integral_view);
        integralView.setMoveForHorizontal(true);
        integralView.setAutoLoadMore(true);
        integralView.setPinnedContent(false);
        //获取商户号
        final String amNumber = SPUtils.getString(this, "AmNumber");

        integralView.setXRefreshViewListener(new XRefreshView.SimpleXRefreshListener() {
            @Override
            public void onRefresh(boolean isPullDown) {
                super.onRefresh(isPullDown);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getSubjectList(0, amNumber, waterType,count);
                    }
                }, 2000);
            }
            @Override
            public void onLoadMore(boolean isSilence) {
                super.onLoadMore(isSilence);
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        //getSubjectList(1, amNumber, curpage,waterType, count, tranTag,curpage);
                        getSubjectList(1, amNumber, waterType,count);
                    }
                }, 2000);
            }
        });
    }

    private void initView() {
        changeMoneyAdapter = new ChangeMoneyAdapter(this, ShopList);

        integralLvList.setAdapter(changeMoneyAdapter);

        changeMoneyAdapter.notifyDataSetChanged();

    }

    private void getSubjectList(final int loadtype, String amNumber, int waterType, int count) {
        //loadtype 0为刷新 1为加载更多
        if (loadtype == 0) {
            curpage = 1;
        }
        Map<String, Object> map = new HashMap<>();
        map.put("amNumber", amNumber);
        map.put("waterType", waterType);
        map.put("page", curpage);
        map.put("count", count);
        map.put("awtIds","15,16,17,18");

        Log.e("Integral--map", map + "");

        XUtil.Post(Config.ReceivablesWaterServlet_URL, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                waterBean = JSON.parseObject(result, WaterBean.class);
                Log.e("++++++++", waterBean.isIsOK() + "");
                if (waterBean.isIsOK() && "00".equals(waterBean.getCode())) {
                    if (waterBean.getList() == null) {
                        MyApp.getInstance().showToast(waterBean.getSuccessMessage());
                    } else {
                        curpage++;
                        if (waterBean.getList().size()==0){
                            return;
                        }
                        if (loadtype == 0) {
                            ShopList.clear();
                            ShopList.addAll(waterBean.getList());
                            initView();
                        }else {
                            ShopList.addAll(waterBean.getList());
                            changeMoneyAdapter.notifyDataSetChanged();
                        }
                    }
                } else if (!waterBean.isIsOK() && "99".equals(waterBean.getCode())) {
                    if ("NULL".equals(waterBean.getMessage())) {
                        MyApp.getInstance().showToast(waterBean.getMessage());
                    } else if ("异常".equals(waterBean.getMessage())) {
                        MyApp.getInstance().showToast(waterBean.getMessage());
                    } else if ("失败".equals(waterBean.getMessage())) {
                        // failMessage
                    } else {
                        MyApp.getInstance().showToast("null");
                    }
                } else {
                    MyApp.getInstance().showToast("null!");
                }
            }

            @Override
            public void onFinished() {
                super.onFinished();
                integralView.stopRefresh();
                integralView.stopLoadMore();
            }
        });
    }

    @OnClick(R.id.integral_back)
    public void onClick() {
        finish();
    }
}
