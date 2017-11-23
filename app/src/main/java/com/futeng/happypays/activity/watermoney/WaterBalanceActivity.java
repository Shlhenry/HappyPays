package com.futeng.happypays.activity.watermoney;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import com.futeng.happypays.adapter.BalanceAdapter;
import com.futeng.happypays.tools.Config;
import com.futeng.happypays.tools.MyApp;
import com.futeng.happypays.tools.MyCallBack;
import com.futeng.happypays.tools.SPUtils;
import com.futeng.happypays.tools.XUtil;
import com.futeng.happypays.utils.BalanceBean;

public class WaterBalanceActivity extends Activity {


    @InjectView(R.id.balance_lv_list)
    ListView balanceLvList;

    XRefreshView xRefreshView;
    @InjectView(R.id.balance_back)
    ImageView balanceBack;
    @InjectView(R.id.balance_view)
    XRefreshView balanceView;
    @InjectView(R.id.notrad)
    LinearLayout notrad;

    private BalanceBean balanceBean = null;
    private BalanceAdapter balanceAdapter = null;

    private int curpage = 1;
    private int count = 20;
    private int waterType = 2;
    private int exaMark = 1;

    List<BalanceBean.ListBean> ShopList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_water_balances);
        ButterKnife.inject(this);
        String amNumber1 = SPUtils.getString(this, "AmNumber");
        getSubjectList(0, amNumber1, waterType, count, exaMark);
        initRefresh();
    }

    private void initRefresh() {
        xRefreshView = (XRefreshView) findViewById(R.id.balance_view);
        xRefreshView.setPinnedTime(1000);
        xRefreshView.setMoveForHorizontal(true);
        xRefreshView.setPullLoadEnable(true);
        xRefreshView.setAutoLoadMore(false);
        xRefreshView.enableReleaseToLoadMore(true);
        xRefreshView.enableRecyclerViewPullUp(true);
        xRefreshView.enablePullUpWhenLoadCompleted(true);
        //设置静默加载时提前加载的item个数
        xRefreshView.setPreLoadCount(20);

        //获取商户号
        Intent intent = new Intent();
        intent.putExtra("AmNumber", SPUtils.getString(this, "AmNumber"));
        final String amNumber = SPUtils.getString(this, "AmNumber");

        xRefreshView.setXRefreshViewListener(new XRefreshView.SimpleXRefreshListener() {
            @Override
            public void onRefresh(boolean isPullDown) {
                super.onRefresh(isPullDown);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getSubjectList(0, amNumber, waterType, count, exaMark);
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
                        getSubjectList(1, amNumber, waterType, count, exaMark);
                    }
                }, 2000);
            }
        });
    }

    private void initView() {
        balanceAdapter = new BalanceAdapter(this, ShopList);
        balanceLvList.setAdapter(balanceAdapter);
        balanceAdapter.notifyDataSetChanged();
    }

    private void getSubjectList(final int loadtype, String amNumber, int waterType, int count, int exaMark) {
        //loadtype 0为刷新 1为加载更多
        if (loadtype == 0) {
            curpage = 1;
        }
        Map<String, Object> map = new HashMap<>();
        map.put("amNumber", amNumber);
        map.put("waterType", waterType);
        map.put("page", curpage);
        map.put("count", count);
        map.put("exaMark", exaMark);

        Log.e("+-+-+-+-+-+", map + "");

        XUtil.Post(Config.ReceivablesWaterServlet_URL, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                balanceBean = JSON.parseObject(result, BalanceBean.class);
                Log.e("++++++++", balanceBean.isIsOK() + "");
                if (balanceBean.isIsOK() && "00".equals(balanceBean.getCode())) {
                    if (balanceBean.getList() == null) {
                       notrad.setVisibility(View.VISIBLE);
                    } else {
                       xRefreshView.setVisibility(View.VISIBLE);
                        curpage++;
                        if (balanceBean.getList().size() == 0) {
                            return;
                        }
                        if (loadtype == 0) {
                            //shopWaterBean1 = shopWaterBean;
                            ShopList.clear();
                            ShopList.addAll(balanceBean.getList());
                            initView();
                        } else {
                            ShopList.addAll(balanceBean.getList());
                            balanceAdapter.notifyDataSetChanged();
                        }
                    }
//                    else {
//                        if (shopWaterBean.getList().size()==0) {
//                            MyApp.getInstance().showToast("没有更多数据了");
//                        } else {
//                            shopWaterBean.getList().addAll(shopWaterBean.getList());
//                            shopwaterAdapter.notifyDataSetChanged();
//                        }
//                    }
                } else if (!balanceBean.isIsOK() && "99".equals(balanceBean.getCode())) {
                    if ("NULL".equals(balanceBean.getMessage())) {
                        MyApp.getInstance().showToast(balanceBean.getMessage());
                    } else if ("异常".equals(balanceBean.getMessage())) {
                        MyApp.getInstance().showToast(balanceBean.getMessage());
                    } else if ("失败".equals(balanceBean.getMessage())) {
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


    @OnClick(R.id.balance_back)
    public void onClick() {
        startActivity(new Intent(WaterBalanceActivity.this, WaterAllActivity.class));
    }
}
