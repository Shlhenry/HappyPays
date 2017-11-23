package com.futeng.happypays.activity.watermoney;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

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
import com.futeng.happypays.activity.UpWaterImgActivity;
import com.futeng.happypays.activity.UpWaterTicketActivity;
import com.futeng.happypays.adapter.ZfbwatermoneyAdapter;
import com.futeng.happypays.tools.Config;
import com.futeng.happypays.tools.MyApp;
import com.futeng.happypays.tools.MyCallBack;
import com.futeng.happypays.tools.SPUtils;
import com.futeng.happypays.tools.XUtil;
import com.futeng.happypays.utils.GetTicketBean;
import com.futeng.happypays.utils.ZfbwatermoneyBean;
import com.mylhyl.circledialog.CircleDialog;
import com.mylhyl.circledialog.params.ProgressParams;
import com.silencezwm.libs.app.Comm;

public class waterzfbmoneyActivity extends Fragment {

    @InjectView(R.id.waterzfbmoney_back)
    ImageView waterzfbmoneyBack;
    @InjectView(R.id.zfbwater_lv_list)
    ListView zfbwaterLvList;

    XRefreshView xRefreshView;
    @InjectView(R.id.notrad_img)
    ImageView notradImg;
    @InjectView(R.id.notrad_txt)
    TextView notradTxt;
    @InjectView(R.id.notrad)
    LinearLayout notrad;

    private ZfbwatermoneyBean zfbwatermoneyBean = null;
    private ZfbwatermoneyAdapter zfbwatermoneyAdapter = null;

    private int tranTag = 6;
    private int count = 20;
    private int waterType = 1;
    private int curpage = 1;

    List<ZfbwatermoneyBean.ListBean> ShopList = new ArrayList<>();

    private View view;
    private DialogFragment dialogFragment;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_waterzfbmoney, container, false);
        ButterKnife.inject(this, view);
        String amNumber1 = SPUtils.getString(getActivity(), "AmNumber");
        getSubjectList(0, amNumber1, waterType, count, tranTag);
        initRefresh();

        zfbwaterLvList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //防止单击界面过快 界面叠加
                if (Comm.isFastDoubleClick(1000)) {
                    return;
                }
                String zfbtran37 = ShopList.get(position).getTran37();
                double TranMoney = ShopList.get(position).getTranMoney();
                String tranmoney = TranMoney + "";
                String TranTime = ShopList.get(position).getTranTime();
                if ("".equals(zfbtran37) || zfbtran37 == null) {
                    MyApp.getInstance().showToast("无此订单");
                } else {
                    //保存订单号跳转小票展示界面  wechattran37不可更改由于支付宝微信使用同个activity
                    //获取item的数据当没有小票的时候获取数据 生成图片再上传
                    //共用界面参数设置一样
                    SPUtils.put(getActivity(), "tran37", zfbtran37);
                    SPUtils.put(getActivity(), "upmoney", tranmoney);
                    SPUtils.put(getActivity(), "uptime", TranTime);
                    SPUtils.put(getActivity(), "uptype", "2");
                    dialogFragment = new CircleDialog.Builder(getActivity())
                            .setProgressText("正在查询，请稍等。。。")
                            .setCanceledOnTouchOutside(false)
                            .setProgressStyle(ProgressParams.STYLE_SPINNER)
                            .show();
                    getticket(zfbtran37, "");
                }
            }
        });
        return view;
    }

    private void initRefresh() {
        xRefreshView = (XRefreshView) view.findViewById(R.id.zfb_view);
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
        intent.putExtra("AmNumber", SPUtils.getString(getActivity(), "AmNumber"));
        final String amNumber = SPUtils.getString(getActivity(), "AmNumber");

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
        zfbwatermoneyAdapter = new ZfbwatermoneyAdapter(getActivity(), ShopList);
        zfbwaterLvList.setAdapter(zfbwatermoneyAdapter);
        zfbwatermoneyAdapter.notifyDataSetChanged();
    }

    private void getSubjectList(final int loadtype, String amNumber, int waterType, int count, int tranTag) {
        //loadtype 0为刷新 1为加载更多
        if (loadtype == 0) {
            curpage = 1;
        }
        Map<String, Object> map = new HashMap<>();
        map.put("amNumber", amNumber);
        map.put("waterType", waterType);
        map.put("page", curpage);
        map.put("count", count);
        map.put("tranTag", tranTag);
        Log.e("+-+-+-+-+-+", map + "");

        XUtil.Post(Config.ReceivablesWaterServlet_URL, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                zfbwatermoneyBean = JSON.parseObject(result, ZfbwatermoneyBean.class);
                if (zfbwatermoneyBean.isIsOK() && "00".equals(zfbwatermoneyBean.getCode())) {
                    if (zfbwatermoneyBean.getList() == null) {
                            //没有数据加载图片
                            notrad.setVisibility(View.VISIBLE);
                    } else {
                        //有数据加载列表
                        xRefreshView.setVisibility(View.VISIBLE);
                        curpage++;
                        if (zfbwatermoneyBean.getList().size() == 0) {
                            return;
                        }
                        if (loadtype == 0) {
                            //shopWaterBean1 = shopWaterBean;
                            //初次加载、或者刷新
                            ShopList.clear();
                            ShopList.addAll(zfbwatermoneyBean.getList());
                            initView();
                        } else {
                            ShopList.addAll(zfbwatermoneyBean.getList());
                            zfbwatermoneyAdapter.notifyDataSetChanged();
                        }
                    }

                } else if (!zfbwatermoneyBean.isIsOK() && "99".equals(zfbwatermoneyBean.getCode())) {
                    if ("NULL".equals(zfbwatermoneyBean.getMessage())) {
//                        MyApp.getInstance().showToast(zfbwatermoneyBean.getMessage());
                        notrad.setVisibility(View.VISIBLE);
                    } else if ("异常".equals(zfbwatermoneyBean.getMessage())) {
                        MyApp.getInstance().showToast(zfbwatermoneyBean.getMessage());
                    } else if ("失败".equals(zfbwatermoneyBean.getMessage())) {
                        // failMessage
                    } else {
                        notrad.setVisibility(View.VISIBLE);
                    }
                } else {
                    Log.e("null","null");
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
//                //网络请求失败
//                notrad.setVisibility(View.VISIBLE);
//                notradTxt.setText("您的网络崩溃了");
//                notradImg.setImageDrawable(getResources().getDrawable(R.mipmap.nointernet));
            }

            @Override
            public void onFinished() {
                super.onFinished();
                xRefreshView.stopRefresh();
                xRefreshView.stopLoadMore();
            }
        });
    }

    @OnClick(R.id.waterzfbmoney_back)
    public void onClick() {
        getActivity().finish();
    }


    private void getticket(String tran37, String ticket) {
        Map<String, Object> map = new HashMap<>();
        map.put("tran37", tran37);
        map.put("ticket", ticket);
        map.put("mode", 3);
        XUtil.Post(Config.TicketServlet_URL, map, new MyCallBack<String>() {

            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                dialogFragment.dismiss();
                GetTicketBean getTicketBean = JSON.parseObject(result, GetTicketBean.class);
                if ("00".equals(getTicketBean.getCode())) {
                    SPUtils.put(getActivity(), "ticketbase64", getTicketBean.getSuccessMessage());
                    startActivity(new Intent(getActivity(), UpWaterImgActivity.class));
                } else if ("99".equals(getTicketBean.getCode())) {
                    startActivity(new Intent(getActivity(), UpWaterTicketActivity.class));
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
                dialogFragment.dismiss();
                MyApp.getInstance().showToast("网络异常!");
            }
        });


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
