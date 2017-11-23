package com.futeng.happypays.activity.watermoney;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.andview.refreshview.XRefreshView;
import com.futeng.happypays.R;
import com.futeng.happypays.adapter.ChangeMoneyAdapter;
import com.futeng.happypays.tools.Config;
import com.futeng.happypays.tools.MyApp;
import com.futeng.happypays.tools.MyCallBack;
import com.futeng.happypays.tools.SPUtils;
import com.futeng.happypays.tools.XUtil;
import com.futeng.happypays.utils.ShopWaterBean;
import com.futeng.happypays.utils.WaterBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class WaterChangemoneyActivity extends Activity {

    @InjectView(R.id.changemoney_lv_list)
    ListView changemoneyLvList;
    XRefreshView xRefreshView;
    @InjectView(R.id.changemoney_back)
    ImageView changemoneyBack;


    private WaterBean waterBean = null;
    private ChangeMoneyAdapter changeMoneyAdapter = null;

    private int curpage = 1;
    private int count = 20;
    private int waterType = 3;

    List<WaterBean.ListBean> ShopList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_water_changemoney);
        ButterKnife.inject(this);
        String amNumber1 = SPUtils.getString(this, "AmNumber");
        getSubjectList(0, amNumber1, waterType,count);
        initRefresh();
    }

    private void initRefresh() {
        xRefreshView = (XRefreshView) findViewById(R.id.changemoney_view);
        xRefreshView.setMoveForHorizontal(true);
        xRefreshView.setAutoLoadMore(true);
        xRefreshView.setPinnedContent(false);
        //获取商户号
        final String amNumber = SPUtils.getString(this, "AmNumber");

        xRefreshView.setXRefreshViewListener(new XRefreshView.SimpleXRefreshListener() {
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

        changemoneyLvList.setAdapter(changeMoneyAdapter);

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
        map.put("awtIds","13,5,1,4");

        Log.e("+-+-+-+-+-+", map + "");

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
                xRefreshView.stopRefresh();
                xRefreshView.stopLoadMore();
            }
        });
    }

    @OnClick(R.id.changemoney_back)
    public void onClick() {
        startActivity(new Intent(WaterChangemoneyActivity.this,WaterAllActivity.class));
    }
}
