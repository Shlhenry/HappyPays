package com.futeng.happypays.activity.watermoney.watermanage;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class T0WaterFragment extends Fragment {


    @InjectView(R.id.balanceother_lv_list)
    ListView balanceotherLvList;
    @InjectView(R.id.balanceother_view)
    XRefreshView balanceotherView;
    @InjectView(R.id.notrad)
    LinearLayout notrad;
    @InjectView(R.id.notrad_img)
    ImageView notradImg;
    @InjectView(R.id.notrad_txt)
    TextView notradTxt;

    private BalanceBean balanceBean = null;
    private BalanceAdapter balanceAdapter = null;

    List<BalanceBean.ListBean> ShopList = new ArrayList<>();


    private int curpage = 1;
    private int count = 10;
    private int waterType = 2;
    private int exaMark = 0;

    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_t0_water, container, false);
        ButterKnife.inject(this, view);
        String amNumber1 = SPUtils.getString(getActivity(), "AmNumber");
        getSubjectList(0, amNumber1, waterType, count, exaMark);
        initRefresh();

        return view;
    }

    private void initRefresh() {
        balanceotherView = (XRefreshView) view.findViewById(R.id.balanceother_view);//这个不能删
        balanceotherView.setPinnedTime(1000);
        balanceotherView.setMoveForHorizontal(true);
        balanceotherView.setPullLoadEnable(true);
        balanceotherView.setAutoLoadMore(false);
        balanceotherView.enableReleaseToLoadMore(true);
        balanceotherView.enableRecyclerViewPullUp(true);
        balanceotherView.enablePullUpWhenLoadCompleted(true);
        //设置静默加载时提前加载的item个数
        balanceotherView.setPreLoadCount(20);

        //获取商户号
        final String amNumber = SPUtils.getString(getActivity(), "AmNumber");

        balanceotherView.setXRefreshViewListener(new XRefreshView.SimpleXRefreshListener() {
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
        balanceAdapter = new BalanceAdapter(getActivity(), ShopList);
        balanceotherLvList.setAdapter(balanceAdapter);
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
                        balanceotherView.setVisibility(View.VISIBLE);
                        curpage++;
                        if (balanceBean.getList().size() == 0) {
                            return;
                        }
                        if (loadtype == 0) {
                            ShopList.clear();
                            ShopList.addAll(balanceBean.getList());
                            initView();
                        } else {
                            ShopList.addAll(balanceBean.getList());
                            balanceAdapter.notifyDataSetChanged();
                        }
                    }

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
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
                //网络请求失败
                notrad.setVisibility(View.VISIBLE);
                notradTxt.setText("您的网络崩溃了");
                notradImg.setImageDrawable(getResources().getDrawable(R.mipmap.nointernet));
            }

            @Override
            public void onFinished() {
                super.onFinished();
                balanceotherView.stopRefresh();
                balanceotherView.stopLoadMore();
            }
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
