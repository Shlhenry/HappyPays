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

import com.alibaba.fastjson.JSON;
import com.andview.refreshview.XRefreshView;
import com.futeng.happypays.R;
import com.futeng.happypays.adapter.InteralAdapter;
import com.futeng.happypays.tools.Config;
import com.futeng.happypays.tools.MyApp;
import com.futeng.happypays.tools.MyCallBack;
import com.futeng.happypays.tools.SPUtils;
import com.futeng.happypays.tools.XUtil;
import com.futeng.happypays.utils.WaterBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class RedPacketFragment extends Fragment {

    @InjectView(R.id.integral_lv_list)
    ListView integralLvList;
    @InjectView(R.id.integral_view)
    XRefreshView integralView;
    @InjectView(R.id.notrad_img)
    ImageView notradImg;
    @InjectView(R.id.notrad_txt)
    TextView notradTxt;
    @InjectView(R.id.notrad)
    LinearLayout notrad;

    private WaterBean waterBean = null;
    private InteralAdapter interalAdapter = null;

    private int curpage = 1;
    private int count = 20;
    private int waterType = 3;

    List<WaterBean.ListBean> ShopList = new ArrayList<>();

    private View view;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_red_packet_fragment, container, false);
        ButterKnife.inject(this, view);

        String amNumber1 = SPUtils.getString(getActivity(), "AmNumber");
        getSubjectList(0, amNumber1, waterType, count);
        initRefresh();

        return view;
    }
    private void initRefresh() {
        integralView = (XRefreshView) view.findViewById(R.id.integral_view);
        integralView.setMoveForHorizontal(true);
        integralView.setAutoLoadMore(true);
        integralView.setPinnedContent(false);
        //获取商户号
        final String amNumber = SPUtils.getString(getActivity(), "AmNumber");

        integralView.setXRefreshViewListener(new XRefreshView.SimpleXRefreshListener() {
            @Override
            public void onRefresh(boolean isPullDown) {
                super.onRefresh(isPullDown);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getSubjectList(0, amNumber, waterType, count);
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
                        getSubjectList(1, amNumber, waterType, count);
                    }
                }, 2000);
            }
        });
    }

    private void initView() {
        interalAdapter = new InteralAdapter(getActivity(), ShopList);
        integralLvList.setAdapter(interalAdapter);
        interalAdapter.notifyDataSetChanged();
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
        map.put("awtIds", "20,21");

        Log.e("+-+-+-Interal+-+-+", map + "");

        XUtil.Post(Config.ReceivablesWaterServlet_URL, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                waterBean = JSON.parseObject(result, WaterBean.class);
                Log.e("++++++++", waterBean.isIsOK() + "");
                if (waterBean.isIsOK() && "00".equals(waterBean.getCode())) {
                    if (waterBean.getList() == null) {
                        notrad.setVisibility(View.VISIBLE);
                    } else {
                        integralView.setVisibility(View.VISIBLE);
                        curpage++;
                        if (waterBean.getList().size() == 0) {
                            return;
                        }
                        if (loadtype == 0) {
                            ShopList.clear();
                            ShopList.addAll(waterBean.getList());
                            initView();
                        } else {
                            ShopList.addAll(waterBean.getList());
                            interalAdapter.notifyDataSetChanged();
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
                integralView.stopRefresh();
                integralView.stopLoadMore();
            }
        });
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
