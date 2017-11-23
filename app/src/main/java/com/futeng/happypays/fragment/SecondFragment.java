package com.futeng.happypays.fragment;

/**
 * Created by Administrator on 2017/3/24.
 */

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import com.alibaba.fastjson.JSON;
import com.androidkun.xtablayout.XTabLayout;
import com.futeng.happypays.R;
import com.futeng.happypays.activity.erweima.ErweimamainActivity;
import com.futeng.happypays.activity.erweima.UnionActivity;
import com.futeng.happypays.activity.erweimashoukuan.QRcodeActivity;
import com.futeng.happypays.activity.erweimashoukuan.QuickTransationActivity;
import com.futeng.happypays.activity.nocard.XinCardActivity;
import com.futeng.happypays.activity.watermoney.UnionPayActivity;
import com.futeng.happypays.activity.watermoney.WaterNoCardActivity;
import com.futeng.happypays.activity.watermoney.WaterwechatmoneyActivity;
import com.futeng.happypays.activity.watermoney.waterzfbmoneyActivity;
import com.futeng.happypays.adapter.FragmentAdapter;
import com.futeng.happypays.tools.Config;
import com.futeng.happypays.tools.MyApp;
import com.futeng.happypays.tools.MyCallBack;
import com.futeng.happypays.tools.SPUtils;
import com.futeng.happypays.tools.XUtil;
import com.futeng.happypays.utils.SelectBankcardBean;
import com.futeng.happypays.utils.UnionBean;
import com.mylhyl.circledialog.CircleDialog;
import com.mylhyl.circledialog.params.ProgressParams;


/**
 * Created by Administrator on 2017/3/24.
 */
public class SecondFragment extends Fragment {
    List<Fragment> fragments = new ArrayList<>();
    @InjectView(R.id.LinearLayout_first)
    LinearLayout LinearLayoutFirst;
    @InjectView(R.id.LinearLayout_second)
    LinearLayout LinearLayoutSecond;
    @InjectView(R.id.LinearLayout_third)
    LinearLayout LinearLayoutThird;

    private View view;
    private SelectBankcardBean selectBankcardBean;
    private DialogFragment dialogFragment;

    private int pState;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view==null){
            view = inflater.inflate(R.layout.activity_car, container, false);
        }
        pState=SPUtils.getInt(getContext(), "pState");
        Log.e("-----收款界面的pState值-----",pState+"");

        ViewGroup group= (ViewGroup) view.getParent();
        if (group!=null){
            group.removeView(view);
        }

        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.inject(this, view);

            initViewPager(view);
    }

    private void initViewPager(View view) {
        List<String> titles = new ArrayList<>();
        titles.add("微信流水");
        titles.add("支付宝流水");
        titles.add("银联扫码流水");
        titles.add("无卡流水");
        for (int i = 0; i < titles.size(); i++) {
            if (i == 0) {
                fragments.add(new WaterwechatmoneyActivity());
            } else if (i == 1) {
                fragments.add(new waterzfbmoneyActivity());
            } else if (i == 2) {
                fragments.add(new UnionPayActivity());
            } else if (i == 3) {
                fragments.add(new WaterNoCardActivity());
            }
        }
        FragmentAdapter adapter = new FragmentAdapter(getChildFragmentManager(), fragments, titles);
        ViewPager viewPager = (ViewPager) view.findViewById(R.id.viewPager);
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(4);
        //将TabLayout和ViewPager关联起来。
        XTabLayout tabLayout = (XTabLayout) view.findViewById(R.id.xTablayout);
        tabLayout.setupWithViewPager(viewPager);
        //给TabLayout设置适配器
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @OnClick({R.id.LinearLayout_first, R.id.LinearLayout_second, R.id.LinearLayout_third})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.LinearLayout_first:
                if ("1,1".equals(SPUtils.getString(getActivity(),"qrCode"))){
                    //获取登入patate赋值
                    if (pState == 2) {
                        Toast.makeText(getActivity(), "您认证资料失败，请重新认证", Toast.LENGTH_SHORT).show();
                    } else if (pState == 3) {
                        Toast.makeText(getActivity(), "您还未实名认证，请先认证", Toast.LENGTH_SHORT).show();
                    } else if (pState == 4) {
                        Toast.makeText(getActivity(), "正在审核资料中，请耐心等待", Toast.LENGTH_SHORT).show();
                    } else if (pState == 5) {
                        Toast.makeText(getActivity(), "账号已停用", Toast.LENGTH_SHORT).show();
                    } else if (pState == 1) {
                        startActivity(new Intent(getActivity(), QRcodeActivity.class));
                    }
                }else{
                    MyApp.getInstance().showToast("此功能暂未开通,敬请期待");
                }
                break;
            case R.id.LinearLayout_second:
                if ("1,1".equals(SPUtils.getString(getActivity(),"mposReceivables"))){
                    //获取登入patate赋值
                    if (pState == 2) {
                        Toast.makeText(getActivity(), "您认证资料失败，请重新认证", Toast.LENGTH_SHORT).show();
                    } else if (pState == 3) {
                        Toast.makeText(getActivity(), "您还未实名认证，请先认证", Toast.LENGTH_SHORT).show();
                    } else if (pState == 4) {
                        Toast.makeText(getActivity(), "正在审核资料中，请耐心等待", Toast.LENGTH_SHORT).show();
                    } else if (pState == 5) {
                        Toast.makeText(getActivity(), "账号已停用", Toast.LENGTH_SHORT).show();
                    } else if (pState == 1) {
                        String amNumber=SPUtils.getString(getActivity(), "AmNumber");
                        union(amNumber);
                    }
                }else{
                    MyApp.getInstance().showToast("此功能暂未开通,敬请期待");
                }
                break;
            case R.id.LinearLayout_third:
                if ("1,1".equals(SPUtils.getString(getActivity(),"noCardShortcut"))){
                    //获取登入patate赋值
                    if (pState == 2) {
                        Toast.makeText(getActivity(), "您认证资料失败，请重新认证", Toast.LENGTH_SHORT).show();
                    } else if (pState == 3) {
                        Toast.makeText(getActivity(), "您还未实名认证，请先认证", Toast.LENGTH_SHORT).show();
                    } else if (pState == 4) {
                        Toast.makeText(getActivity(), "正在审核资料中，请耐心等待", Toast.LENGTH_SHORT).show();
                    } else if (pState == 5) {
                        Toast.makeText(getActivity(), "账号已停用", Toast.LENGTH_SHORT).show();
                    } else if (pState == 1) {
                        dialogFragment = new CircleDialog.Builder(getActivity())
                                .setProgressText("请稍等。。。")
                                .setProgressStyle(ProgressParams.STYLE_SPINNER)
                                .show();
                        String amnumber=SPUtils.getString(getActivity(), "AmNumber");
                        selectbankcard(amnumber);
                    }
                }else{
                    MyApp.getInstance().showToast("此功能暂未开通,敬请期待");
                }

                break;
        }
    }


    private void union(String amNumber){
        Map<String,Object> map=new HashMap<>();
        map.put("amNumber",amNumber);
        map.put("mcc",81);
        map.put("mode",2);
        XUtil.Post(Config.PayResultServlet_URL,map,new MyCallBack<String>(){
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);

                UnionBean union= JSON.parseObject(result,UnionBean.class);
                if ("00".equals(union.getCode())){
                    SPUtils.put(getActivity(), "PsUrl", union.getMap().getPsUrl());
                    startActivity(new Intent(getActivity(),UnionActivity.class));

                }else if("99".equals(union.getCode())){
                    MyApp.getInstance().showToast(union.getFailMessage());
                }
            }
        });
    }

    private void selectbankcard(String amnumber){
        Map<String,Object> map=new HashMap<>();
        map.put("mode",2);
        map.put("amNumber",amnumber);
        XUtil.Post(Config.AMBandCreditServlet_URL,map,new MyCallBack<String>(){
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                dialogFragment.dismiss();
                selectBankcardBean= JSON.parseObject(result,SelectBankcardBean.class);
                if ("00".equals(selectBankcardBean.getCode())){
                    if (selectBankcardBean.getList()!=null){

                        SPUtils.put(getActivity(),"AbcRemark",selectBankcardBean.getList().get(0).getAbcRemark());
                        SPUtils.put(getActivity(),"AccountName",selectBankcardBean.getList().get(0).getAccountName());
                        SPUtils.put(getActivity(),"AccountNumber",selectBankcardBean.getList().get(0).getAccountNumber());

                        startActivity(new Intent(getActivity(), QuickTransationActivity.class));
                    }else {
                        startActivity(new Intent(getActivity(), QuickTransationActivity.class));
                        return;
                    }
                }else {
                    MyApp.getInstance().showToast(selectBankcardBean.getMessage());
                }
            }
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
                dialogFragment.dismiss();
            }
        });
    }
}

