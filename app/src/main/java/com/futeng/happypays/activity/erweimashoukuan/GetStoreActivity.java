package com.futeng.happypays.activity.erweimashoukuan;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.andview.refreshview.XRefreshView;
import com.futeng.happypays.R;
import com.futeng.happypays.adapter.ExchangerecordAdapter;
import com.futeng.happypays.tools.Config;
import com.futeng.happypays.tools.MyApp;
import com.futeng.happypays.tools.MyCallBack;
import com.futeng.happypays.tools.SPUtils;
import com.futeng.happypays.tools.XUtil;
import com.futeng.happypays.utils.ChangerecordBean;
import com.mylhyl.circledialog.CircleDialog;
import com.mylhyl.circledialog.params.ProgressParams;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class GetStoreActivity extends FragmentActivity {

    @InjectView(R.id.exchange_back)
    ImageView exchangeBack;
    @InjectView(R.id.textView8)
    TextView textView8;
    @InjectView(R.id.exchange_lv_list)
    ListView exchangeLvList;
    @InjectView(R.id.exchange_view)
    XRefreshView exchangeView;
    private ChangerecordBean changerecordBean = null;
    private ExchangerecordAdapter exchangerecordAdapter = null;
    List<ChangerecordBean.ListBean> ChangeList = new ArrayList<>();

    private int curpage = 1;
    private int count = 20;

    private DialogFragment dialogFragment;
    private String amNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_store);
        ButterKnife.inject(this);
        amNumber = SPUtils.getString(getApplication(), "AmNumber");

        dialogFragment = new CircleDialog.Builder(GetStoreActivity.this)
                .setProgressText("正在加载，请稍等。。。")
                .setCanceledOnTouchOutside(false)
                .setProgressStyle(ProgressParams.STYLE_SPINNER)
                .show();


        getSubjectList(0, amNumber);
        initRefresh();

        exchangeLvList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                if (changerecordBean.getList().get(i).getExpName()!=null){
                    SPUtils.put(getApplication(),"expname",changerecordBean.getList().get(i).getExpName());
                    SPUtils.put(getApplication(),"expressOrderNo",changerecordBean.getList().get(i).getExpressOrderNo());
                    SPUtils.put(getApplication(),"logistic_state",changerecordBean.getList().get(i).getPaoState());
                }else{
                    SPUtils.put(getApplication(),"logistic_state",changerecordBean.getList().get(i).getPaoState());
                    SPUtils.put(getApplication(),"expressOrderNo","");
                    SPUtils.put(getApplication(),"expname","承运公司暂无");
                }
                startActivity(new Intent(GetStoreActivity.this,LogisticActivity.class));

            }
        });

    }

    private void initRefresh() {
        exchangeView = (XRefreshView) findViewById(R.id.exchange_view);
        exchangeView.setPinnedTime(1000);
        exchangeView.setMoveForHorizontal(true);
        exchangeView.setPullLoadEnable(true);
        exchangeView.setAutoLoadMore(false);

        exchangeView.enableReleaseToLoadMore(true);
        exchangeView.enableRecyclerViewPullUp(true);
        exchangeView.enablePullUpWhenLoadCompleted(true);
        //设置静默加载时提前加载的item个数
        exchangeView.setPreLoadCount(20);


        exchangeView.setXRefreshViewListener(new XRefreshView.SimpleXRefreshListener() {

            @Override
            public void onRefresh(boolean isPullDown) {
                super.onRefresh(isPullDown);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getSubjectList(0, amNumber);
                    }
                }, 2000);
            }

            @Override
            public void onLoadMore(boolean isSilence) {
                super.onLoadMore(isSilence);
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        getSubjectList(1, amNumber);
                    }
                }, 2000);
            }
        });
    }

    private void initView() {
        exchangerecordAdapter = new ExchangerecordAdapter(GetStoreActivity.this, ChangeList);
        exchangeLvList.setAdapter(exchangerecordAdapter);
        exchangerecordAdapter.notifyDataSetChanged();
    }

    //兑换记录的接口请求
    private void getSubjectList(final int loadtype, String amNumber) {
        //loadtype 0为刷新 1为加载更多
        if (loadtype == 0) {
            curpage = 1;
        }
        Map<String, Object> map = new HashMap<>();
        map.put("mode", "2");
        map.put("amNumber", amNumber);
        map.put("page", curpage);
        map.put("count", count);

        XUtil.Post(Config.SmPlaceanOrderServlet_URL, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);

                dialogFragment.dismiss();

                changerecordBean = JSON.parseObject(result, ChangerecordBean.class);
                Log.e("++++++++", changerecordBean.isIsOK() + "");
                if (changerecordBean.isIsOK() && "00".equals(changerecordBean.getCode())) {
                    if (changerecordBean.getList() == null) {
                        return;
                    } else {
                        //有数据加载列表
                        exchangeView.setVisibility(View.VISIBLE);
                        curpage++;
                        if (changerecordBean.getList().size() == 0) {
                            return;
                        }
                        if (loadtype == 0) {
                            //shopWaterBean1 = shopWaterBean;
                            //初次加载、或者刷新
                            ChangeList.clear();
                            ChangeList.addAll(changerecordBean.getList());
                            initView();
                        } else {
                            ChangeList.addAll(changerecordBean.getList());
                            exchangerecordAdapter.notifyDataSetChanged();
                        }
                    }
                } else if (!changerecordBean.isIsOK() && "99".equals(changerecordBean.getCode())) {
                    if ("NULL".equals(changerecordBean.getMessage())) {
//                        notrad.setVisibility(View.VISIBLE);
                    } else if ("异常".equals(changerecordBean.getMessage())) {
                        MyApp.getInstance().showToast(changerecordBean.getMessage());
                    } else if ("失败".equals(changerecordBean.getMessage())) {
                    } else {
//                        notrad.setVisibility(View.VISIBLE);
                    }
                } else {
                    Log.e("null", "null");
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
                dialogFragment.dismiss();
            }

            @Override
            public void onFinished() {
                super.onFinished();
                exchangeView.stopRefresh();
                exchangeView.stopLoadMore();
            }
        });
    }

    @OnClick(R.id.exchange_back)
    public void onViewClicked() {
        finish();
    }
}
