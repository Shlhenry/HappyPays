package com.futeng.happypays.activity.erweimashoukuan;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.andview.refreshview.XRefreshView;
import com.andview.refreshview.XRefreshViewFooter;
import com.futeng.happypays.R;
import com.futeng.happypays.adapter.SimpleAdapter;
import com.futeng.happypays.tools.Config;
import com.futeng.happypays.tools.MyApp;
import com.futeng.happypays.tools.MyCallBack;
import com.futeng.happypays.tools.SPUtils;
import com.futeng.happypays.tools.XUtil;
import com.futeng.happypays.utils.AddressBean;
import com.futeng.happypays.utils.StoreBean;
import com.mylhyl.circledialog.CircleDialog;
import com.mylhyl.circledialog.params.ProgressParams;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class StoreActivity extends FragmentActivity {

    @InjectView(R.id.shopRecyclerView)
    RecyclerView shopRecyclerView;
    @InjectView(R.id.ShopXRefreshView)
    XRefreshView ShopXRefreshView;
    @InjectView(R.id.exchange_record)
    TextView exchangeRecord;
    @InjectView(R.id.ourintegral)
    TextView ourintegral;
    @InjectView(R.id.store_back)
    ImageView storeBack;

    private StoreBean storeBean;
    private int curpage = 1;

    List<StoreBean.ListBean> StoreList = new ArrayList<>();
    GridLayoutManager layoutManager;
    SimpleAdapter adapter;

    private String amnumber;
    private AddressBean addressBean;
    private DialogFragment dialogFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);
        ButterKnife.inject(this);
        initRefresh();

        amnumber = SPUtils.getString(getApplication(), "AmNumber");

        ourintegral.setText(SPUtils.getString(this, "accountInegral"));

        dialogFragment = new CircleDialog.Builder(StoreActivity.this)
                .setProgressText("正在加载，请稍等。。。")
                .setCanceledOnTouchOutside(false)
                .setProgressStyle(ProgressParams.STYLE_SPINNER)
                .show();

    }

    private void initRefresh() {
        ShopXRefreshView.setMoveForHorizontal(true);
        ShopXRefreshView.setPullLoadEnable(true);
////        ShopXRefreshView.setAutoLoadMore(true);
        ShopXRefreshView.enableReleaseToLoadMore(true);
        ShopXRefreshView.enableRecyclerViewPullUp(true);
        ShopXRefreshView.enablePullUpWhenLoadCompleted(true);

        Storedata(0);
        ShopXRefreshView.setPullLoadEnable(true);
        shopRecyclerView.setHasFixedSize(true);
        layoutManager = new GridLayoutManager(this, 2);
        shopRecyclerView.setLayoutManager(layoutManager);

        adapter = new SimpleAdapter(StoreList, getApplicationContext());

        shopRecyclerView.setAdapter(adapter);
        ShopXRefreshView.setPinnedTime(1000);
        ShopXRefreshView.setMoveForHorizontal(true);

        adapter.setCustomLoadMoreView(new XRefreshViewFooter(this));

        ShopXRefreshView.setXRefreshViewListener(new XRefreshView.SimpleXRefreshListener() {

            @Override
            public void onRefresh(boolean isPullDown) {
                super.onRefresh(isPullDown);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Log.e("刷新刷新刷新刷新", "刷新刷新刷新刷新");
                        Storedata(0);
                    }
                }, 2000);
            }

            @Override
            public void onLoadMore(boolean isSilence) {
                super.onLoadMore(isSilence);
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        Log.e("加载加载加载加载", "加载加载加载加载");
                        Storedata(1);
                    }
                }, 2000);
            }

        });
        //商城首页商品跳转的item监听
        adapter.setOnItemClickListener(new SimpleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                dialogFragment = new CircleDialog.Builder(StoreActivity.this)
                        .setProgressText("正在登入，请稍等。。。")
                        .setCanceledOnTouchOutside(false)
                        .setProgressStyle(ProgressParams.STYLE_SPINNER)
                        .show();
                getaddresslv(amnumber);
                SPUtils.put(getApplication(), "comId", StoreList.get(position).getComId() + "");
                startActivity(new Intent(StoreActivity.this, StoreDetailsActivity.class));
            }
        });

    }

    //商城界面数据请求
    private void Storedata(final int loadtype) {
        //loadtype 0为刷新 1为加载更多
        if (loadtype == 0) {
            curpage = 1;
        }
        Map<String, Object> map = new HashMap<>();
        map.put("mode", "1");
        map.put("count", "20");
        map.put("page", curpage);
        Log.e("+++map+++", map + "");
        XUtil.Post(Config.SmCommodityServlet_URL, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                dialogFragment.dismiss();
                storeBean = JSON.parseObject(result, StoreBean.class);
                if (storeBean.getList() == null) {
                    return;
                } else {
                    curpage++;
                    if (loadtype == 0) {
                        //初次加载、或者刷新
                        StoreList.clear();
                        StoreList.addAll(storeBean.getList());
//                    shopRecyclerView.setAdapter(adapter);
                    } else {
                        Log.e("11111111", "1111111111111111111111111111111111");
                        StoreList.addAll(storeBean.getList());
                        Log.e("2222222222", StoreList.size() + "");
//                    shopRecyclerView.setAdapter(adapter);
//                    adapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFinished() {
                super.onFinished();
                dialogFragment.dismiss();
                ShopXRefreshView.stopRefresh();
                ShopXRefreshView.stopLoadMore();
            }
        });

    }

    private void getaddresslv(String amNumber) {
        Map<String, Object> map = new HashMap<>();
        map.put("mode", "1");
        map.put("amNumber", amNumber);
        XUtil.Post(Config.SmReceiptinfoServlet_URL, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                dialogFragment.dismiss();
                addressBean = JSON.parseObject(result, AddressBean.class);
                if ("00".equals(addressBean.getCode()) && addressBean.getList() != null) {
                    SPUtils.put(getApplication(), "riId", addressBean.getList().get(0).getRiId() + "");
                    SPUtils.put(getApplication(), "storeName", addressBean.getList().get(0).getRiName());
                    SPUtils.put(getApplication(), "textviewAddress", "收货人:");
                    SPUtils.put(getApplication(), "storeTel", addressBean.getList().get(0).getRiPhone());
                    SPUtils.put(getApplication(), "getstoreDetaile", "收货地址:" + addressBean.getList().get(0).getRiCity() + addressBean.getList().get(0).getRiAddress());
                    SPUtils.put(getApplication(), "judge", "1");//判断是否有地址
                } else if ("00".equals(addressBean.getCode()) && addressBean.getList() == null) {
                    SPUtils.put(getApplication(), "judge", "2");//判断是否有地址
                    return;
                } else {
                    SPUtils.put(getApplication(), "judge", "3");//判断是否有地址
                    MyApp.getInstance().showToast(addressBean.getFailMessage());
                    return;
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
                dialogFragment.dismiss();
            }
        });

    }


    @OnClick({R.id.store_back, R.id.exchange_record})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.store_back:
                finish();
                break;
            case R.id.exchange_record:
                startActivity(new Intent(StoreActivity.this, GetStoreActivity.class));
                break;
        }
    }
}
