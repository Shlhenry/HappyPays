package com.futeng.happypays.activity.watermoney;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
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
import com.futeng.happypays.activity.MposticketActivity;
import com.futeng.happypays.activity.NoUpTicketActivity;
import com.futeng.happypays.adapter.ShopwaterAdapter;
import com.futeng.happypays.tools.Config;
import com.futeng.happypays.tools.MyApp;
import com.futeng.happypays.tools.MyCallBack;
import com.futeng.happypays.tools.SPUtils;
import com.futeng.happypays.tools.XUtil;
import com.futeng.happypays.utils.GetTicketBean;
import com.futeng.happypays.utils.ShopWaterBean;
import com.mylhyl.circledialog.CircleDialog;
import com.mylhyl.circledialog.params.ProgressParams;

public class MPoswatermoneyActivity extends FragmentActivity {

    @InjectView(R.id.putmoney_back)
    ImageView putmoneyBack;
    @InjectView(R.id.putongwater_lv_list)
    ListView putongwaterLvList;
    XRefreshView xRefreshView;

    private ShopWaterBean shopWaterBean = null;
    private ShopwaterAdapter shopwaterAdapter = null;

    private int tranTag = 2;
    private int count = 20;
    private int waterType = 1;
    private int curpage = 1;
    List<ShopWaterBean.ListBean> ShopList=new ArrayList<>();

    private DialogFragment dialogFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_putwatermoney);
        ButterKnife.inject(this);
        String amNumber1 = SPUtils.getString(this, "AmNumber");
        getSubjectList(0, amNumber1, waterType, count, tranTag);
        initRefresh();

        putongwaterLvList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                dialogFragment = new CircleDialog.Builder(MPoswatermoneyActivity.this)
                        .setProgressText("正在加载...")
                        .setCanceledOnTouchOutside(false)
                        .setProgressStyle(ProgressParams.STYLE_SPINNER)
                        .show();


                String mpostran37=ShopList.get(position).getTran37();
                Log.e("++++++---**///",mpostran37);
                SPUtils.put(getApplicationContext(), "tran37",mpostran37);
                getticket(mpostran37,"");

                SPUtils.put(getApplicationContext(), "tranTime",ShopList.get(position).getTranTime());
                SPUtils.put(getApplicationContext(), "tran2",ShopList.get(position).getTran2());
                SPUtils.put(getApplicationContext(), "macSerial",ShopList.get(position).getMacSerial());
                SPUtils.put(getApplicationContext(), "macNumber",ShopList.get(position).getMacNumber());
                SPUtils.put(getApplicationContext(), "tranFee",ShopList.get(position).getTranFee());
                SPUtils.put(getApplicationContext(), "tran37",ShopList.get(position).getTran37());
                SPUtils.put(getApplicationContext(), "macBatch",ShopList.get(position).getMacBatch());
                SPUtils.put(getApplicationContext(), "tranId",ShopList.get(position).getTranId());
                SPUtils.put(getApplicationContext(), "mccName",ShopList.get(position).getMccName());
                SPUtils.put(getApplicationContext(), "amNumber",ShopList.get(position).getAmNumber());
                SPUtils.put(getApplicationContext(), "tranMoney",ShopList.get(position).getTranMoney()+"");
                SPUtils.put(getApplicationContext(), "tran14",ShopList.get(position).getTran14());

                Log.e("**tranMoney",ShopList.get(position).getTranMoney()+"");


            }
        });
    }

    private void initRefresh() {
        xRefreshView = (XRefreshView) findViewById(R.id.putong_view);
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
                        getSubjectList(0, amNumber, waterType, count, tranTag);
                    }
                }, 2000);
            }

            @Override
            public void onLoadMore(boolean isSilence) {
                super.onLoadMore(isSilence);
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        getSubjectList(1, amNumber, waterType, count, tranTag);
                    }
                }, 2000);
            }
        });
    }

    private void initView() {
        shopwaterAdapter = new ShopwaterAdapter(this, ShopList);
        putongwaterLvList.setAdapter(shopwaterAdapter);
        shopwaterAdapter.notifyDataSetChanged();
    }

    private void getSubjectList(final int loadtype, String amNumber, int waterType, int count, int tranTag) {
        //loadtype 0为刷新 1为加载更多
        if (loadtype == 0 ) {
            curpage = 1;
        }
        Map<String, Object> map = new HashMap<>();
        map.put("amNumber", amNumber);
        map.put("waterType", waterType);
        map.put("page", curpage);
        map.put("count", count);
        map.put("tranTag", tranTag);
        Log.e("-----mpos流水-----", map + "");

        XUtil.Post(Config.ReceivablesWaterServlet_URL, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                shopWaterBean = JSON.parseObject(result, ShopWaterBean.class);
                Log.e("++++++++", shopWaterBean.isIsOK() + "");
                if (shopWaterBean.isIsOK() && "00".equals(shopWaterBean.getCode())) {
                    if (shopWaterBean.getList() == null) {
                        MyApp.getInstance().showToast(shopWaterBean.getSuccessMessage());
                    } else {
                        curpage++;

                        if (shopWaterBean.getList().size()==0){
                            return;
                        }
                        if (loadtype == 0) {
                            //shopWaterBean1 = shopWaterBean;
                            //初次加载、或者刷新
                            ShopList.clear();
                            ShopList.addAll(shopWaterBean.getList());
                            initView();
                        }else {
                            ShopList.addAll(shopWaterBean.getList());
                            shopwaterAdapter.notifyDataSetChanged();
                        }
                    }
                } else if (!shopWaterBean.isIsOK() && "99".equals(shopWaterBean.getCode())) {
                    if ("NULL".equals(shopWaterBean.getMessage())) {
                        MyApp.getInstance().showToast(shopWaterBean.getMessage());
                    } else if ("异常".equals(shopWaterBean.getMessage())) {
                        MyApp.getInstance().showToast(shopWaterBean.getMessage());
                    } else if ("失败".equals(shopWaterBean.getMessage())) {
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

    @OnClick(R.id.putmoney_back)
    public void onClick() {
        finish();
    }

    private void getticket(String tran37,String ticket){
        Map<String,Object>map=new HashMap<>();
        map.put("tran37",tran37);
        map.put("ticket",ticket);
        map.put("mode",3);

        XUtil.Post(Config.TicketServlet_URL,map,new MyCallBack<String>(){

            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);

                GetTicketBean getTicketBean= JSON.parseObject(result,GetTicketBean.class);
                if ("00".equals(getTicketBean.getCode())){
                    MyApp.getInstance().showToast(getTicketBean.getMessage());
                    dialogFragment.dismiss();
                    startActivity(new Intent(MPoswatermoneyActivity.this,MposticketActivity.class));
                }else if ("99".equals(getTicketBean.getCode())){
                    MyApp.getInstance().showToast(getTicketBean.getFailMessage());
                    dialogFragment.dismiss();
                    startActivity(new Intent(MPoswatermoneyActivity.this,NoUpTicketActivity.class));
                }
            }
        });
    }


}
