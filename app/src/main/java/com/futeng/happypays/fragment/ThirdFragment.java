package com.futeng.happypays.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import com.alibaba.fastjson.JSON;
import com.futeng.happypays.R;
import com.futeng.happypays.activity.MainActivity;
import com.futeng.happypays.activity.allgridview.EastDaiActivity;
import com.futeng.happypays.activity.allgridview.MyTeamAllFragment;
import com.futeng.happypays.activity.allgridview.ShapeActivity;
import com.futeng.happypays.activity.erweima.ErweimamainActivity;
import com.futeng.happypays.activity.erweima.UnionActivity;
import com.futeng.happypays.activity.mposgather.BbposListActivity;
import com.futeng.happypays.activity.nocard.XinCardActivity;
import com.futeng.happypays.activity.renzheng.RenzhengGuideActivity;
import com.futeng.happypays.activity.renzheng.RenzhengZiliaoqueren;
import com.futeng.happypays.activity.renzheng.Renzheng_base;
import com.futeng.happypays.activity.watermoney.MPoswatermoneyActivity;
import com.futeng.happypays.activity.watermoney.UnionPayActivity;
import com.futeng.happypays.activity.watermoney.WaterNoCardActivity;
import com.futeng.happypays.activity.watermoney.WaterSaoActivity;
import com.futeng.happypays.activity.watermoney.erweimawater.erweimaallActivity;
import com.futeng.happypays.tools.Config;
import com.futeng.happypays.tools.MyApp;
import com.futeng.happypays.tools.MyCallBack;
import com.futeng.happypays.tools.SPUtils;
import com.futeng.happypays.tools.XUtil;
import com.futeng.happypays.utils.ControllerBean;
import com.futeng.happypays.utils.UnionBean;
import com.futeng.happypays.utils.User;

public class ThirdFragment extends Fragment implements View.OnClickListener {

    @InjectView(R.id.LinearLayout_second)
    LinearLayout LinearLayoutSecond;
    @InjectView(R.id.LinearLayout_third)
    LinearLayout LinearLayoutThird;
    @InjectView(R.id.LinearLayout_first)
    LinearLayout LinearLayoutFirst;
    @InjectView(R.id.main_one)
    LinearLayout mainOne;
    @InjectView(R.id.main_two)
    LinearLayout mainTwo;
    @InjectView(R.id.main_three)
    LinearLayout mainThree;
    @InjectView(R.id.main_four)
    LinearLayout mainFour;
    @InjectView(R.id.main_five)
    LinearLayout mainFive;
    @InjectView(R.id.main_six)
    LinearLayout mainSix;
    @InjectView(R.id.main_seven)
    LinearLayout mainSeven;
    @InjectView(R.id.main_eight)
    LinearLayout mainEight;
    @InjectView(R.id.main_nine)
    LinearLayout mainNine;
    @InjectView(R.id.main_ten)
    LinearLayout mainTen;
    @InjectView(R.id.main_twlever)
    LinearLayout mainTwlever;
    @InjectView(R.id.main_elever)
    LinearLayout mainElever;
    @InjectView(R.id.qrCode)
    TextView qrCode;
    @InjectView(R.id.mposReceivables)
    TextView mposReceivables;
    @InjectView(R.id.noCardShortcut)
    TextView noCardShortcut;
    @InjectView(R.id.qrCodeWater)
    TextView qrCodeWater;
    @InjectView(R.id.mposReceivablesWater)
    TextView mposReceivablesWater;
    @InjectView(R.id.noCardShortcutWater)
    TextView noCardShortcutWater;
    @InjectView(R.id.creditCardApplication)
    TextView creditCardApplication;
    @InjectView(R.id.creditCardPayment)
    TextView creditCardPayment;
    @InjectView(R.id.quickAndEasyCredit)
    TextView quickAndEasyCredit;
    @InjectView(R.id.phoneReplenishing)
    TextView phoneReplenishing;
    @InjectView(R.id.wateFeePaid)
    TextView wateFeePaid;
    @InjectView(R.id.payElectricityBill)
    TextView payElectricityBill;
    @InjectView(R.id.auth)
    TextView auth;
    @InjectView(R.id.myTeam)
    TextView myTeam;
    @InjectView(R.id.sharingCeremony)
    TextView sharingCeremony;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_mains, container, false);
        ButterKnife.inject(this, v);


        qrCode.setText(SPUtils.getString(getActivity(),"101"));
        mposReceivables.setText(SPUtils.getString(getActivity(),"102"));
        noCardShortcut.setText(SPUtils.getString(getActivity(),"103"));
        qrCodeWater.setText(SPUtils.getString(getActivity(),"104"));
        mposReceivablesWater.setText(SPUtils.getString(getActivity(),"105"));
        noCardShortcutWater.setText(SPUtils.getString(getActivity(),"106"));
        creditCardApplication.setText(SPUtils.getString(getActivity(),"107"));
        creditCardPayment.setText(SPUtils.getString(getActivity(),"108"));
        quickAndEasyCredit.setText(SPUtils.getString(getActivity(),"109"));
        phoneReplenishing.setText(SPUtils.getString(getActivity(),"110"));
        wateFeePaid.setText(SPUtils.getString(getActivity(),"111"));
        payElectricityBill.setText(SPUtils.getString(getActivity(),"112"));
        auth.setText(SPUtils.getString(getActivity(),"113"));
        myTeam.setText(SPUtils.getString(getActivity(),"114"));
        sharingCeremony.setText(SPUtils.getString(getActivity(),"115"));

        switchservlet("1");

        String tel=SPUtils.getString(getActivity(), "phone");
        loginstate(tel);

        return v;
    }

    @OnClick({R.id.LinearLayout_second, R.id.LinearLayout_third, R.id.LinearLayout_first, R.id.main_one, R.id.main_two, R.id.main_three, R.id.main_four, R.id.main_five, R.id.main_six, R.id.main_seven, R.id.main_eight, R.id.main_nine, R.id.main_ten, R.id.main_twlever, R.id.main_elever})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.LinearLayout_first:
                if ("1,1".equals(SPUtils.getString(getActivity(),"qrCode"))){
                //获取登入patate赋值
                Intent intent3 = new Intent();
                intent3.putExtra("pState", SPUtils.getInt(getContext(), "pState"));
                Integer pstate2 = SPUtils.getInt(getContext(), "pState");
                if (pstate2 == 2) {
                    Toast.makeText(getActivity(), "您认证资料失败，请重新认证", Toast.LENGTH_SHORT).show();
                } else if (pstate2 == 3) {
                    Toast.makeText(getActivity(), "您还未实名认证，请先认证", Toast.LENGTH_SHORT).show();
                } else if (pstate2 == 4) {
                    Toast.makeText(getActivity(), "正在审核资料中，请耐心等待", Toast.LENGTH_SHORT).show();
                } else if (pstate2 == 5) {
                    Toast.makeText(getActivity(), "账号已停用", Toast.LENGTH_SHORT).show();
                } else if (pstate2 == 1) {
                    startActivity(new Intent(getActivity(), ErweimamainActivity.class));
                }
                }else{
                    MyApp.getInstance().showToast("此功能暂未开通,敬请期待");
                }
                break;
            case R.id.LinearLayout_second:
                if ("1,1".equals(SPUtils.getString(getActivity(),"mposReceivables"))){
                    //获取登入patate赋值
                    Intent intent5 = new Intent();
                    intent5.putExtra("pState", SPUtils.getInt(getContext(), "pState"));
                    Integer pstate4 = SPUtils.getInt(getContext(), "pState");
                    if (pstate4 == 2) {
                        Toast.makeText(getActivity(), "您认证资料失败，请重新认证", Toast.LENGTH_SHORT).show();
                    } else if (pstate4 == 3) {
                        Toast.makeText(getActivity(), "您还未实名认证，请先认证", Toast.LENGTH_SHORT).show();
                    } else if (pstate4 == 4) {
                        Toast.makeText(getActivity(), "正在审核资料中，请耐心等待", Toast.LENGTH_SHORT).show();
                    } else if (pstate4 == 5) {
                        Toast.makeText(getActivity(), "账号已停用", Toast.LENGTH_SHORT).show();
                    } else if (pstate4 == 1) {
                        //线下
//                        startActivity(new Intent(getActivity(), BbposListActivity.class));

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
                    Intent intent4 = new Intent();
                    intent4.putExtra("pState", SPUtils.getInt(getContext(), "pState"));
                    Integer pstate3 = SPUtils.getInt(getContext(), "pState");
                    if (pstate3 == 2) {
                        Toast.makeText(getActivity(), "您认证资料失败，请重新认证", Toast.LENGTH_SHORT).show();
                    } else if (pstate3 == 3) {
                        Toast.makeText(getActivity(), "您还未实名认证，请先认证", Toast.LENGTH_SHORT).show();
                    } else if (pstate3 == 4) {
                        Toast.makeText(getActivity(), "正在审核资料中，请耐心等待", Toast.LENGTH_SHORT).show();
                    } else if (pstate3 == 5) {
                        Toast.makeText(getActivity(), "账号已停用", Toast.LENGTH_SHORT).show();
                    } else if (pstate3 == 1) {
                        startActivity(new Intent(getActivity(), XinCardActivity.class));
                    }
                }else{
                    MyApp.getInstance().showToast("此功能暂未开通,敬请期待");
                }

                break;
            case R.id.main_one:
                if ("1,1".equals(SPUtils.getString(getActivity(),"qrCodeWater"))){
                    //获取登入patate赋值
                    Intent intent2 = new Intent();
                    intent2.putExtra("pState", SPUtils.getInt(getContext(), "pState"));
                    Integer pstate1 = SPUtils.getInt(getContext(), "pState");
                    if (pstate1 == 2) {
                        Toast.makeText(getActivity(), "您认证资料失败，请重新认证", Toast.LENGTH_SHORT).show();
                    } else if (pstate1 == 3) {
                        Toast.makeText(getActivity(), "您还未实名认证，请先认证", Toast.LENGTH_SHORT).show();
                    } else if (pstate1 == 4) {
                        Toast.makeText(getActivity(), "正在审核资料中，请耐心等待", Toast.LENGTH_SHORT).show();
                    } else if (pstate1 == 5) {
                        Toast.makeText(getActivity(), "账号已停用", Toast.LENGTH_SHORT).show();
                    } else if (pstate1 == 1) {
//                        startActivity(new Intent(getActivity(), WaterSaoActivity.class));
                        startActivity(new Intent(getActivity(),erweimaallActivity.class));
                    }
                }else{
                    MyApp.getInstance().showToast("此功能暂未开通,敬请期待");
                }

                break;
            case R.id.main_two:

                if ("1,1".equals(SPUtils.getString(getActivity(),"mposReceivablesWater"))){
                    //获取登入patate赋值
                    Intent intent6 = new Intent();
                    intent6.putExtra("pState", SPUtils.getInt(getContext(), "pState"));
                    Integer pstate6 = SPUtils.getInt(getContext(), "pState");
                    if (pstate6 == 2) {
                        Toast.makeText(getActivity(), "您认证资料失败，请重新认证", Toast.LENGTH_SHORT).show();
                    } else if (pstate6 == 3) {
                        Toast.makeText(getActivity(), "您还未实名认证，请先认证", Toast.LENGTH_SHORT).show();
                    } else if (pstate6 == 4) {
                        Toast.makeText(getActivity(), "正在审核资料中，请耐心等待", Toast.LENGTH_SHORT).show();
                    } else if (pstate6 == 5) {
                        Toast.makeText(getActivity(), "账号已停用", Toast.LENGTH_SHORT).show();
                    } else if (pstate6 == 1) {
                        //MPOS流水
//                        startActivity(new Intent(getActivity(), MPoswatermoneyActivity.class));
                        startActivity(new Intent(getActivity(), UnionPayActivity.class));
                    }
                }else{
                    MyApp.getInstance().showToast("此功能暂未开通,敬请期待");
                }

                break;
            case R.id.main_three:
                if ("1,1".equals(SPUtils.getString(getActivity(),"noCardShortcutWater"))){
                    //获取登入patate赋值
                    Intent intent7 = new Intent();
                    intent7.putExtra("pState", SPUtils.getInt(getContext(), "pState"));
                    Integer pstate7 = SPUtils.getInt(getContext(), "pState");
                    if (pstate7 == 2) {
                        Toast.makeText(getActivity(), "您认证资料失败，请重新认证", Toast.LENGTH_SHORT).show();
                    } else if (pstate7 == 3) {
                        Toast.makeText(getActivity(), "您还未实名认证，请先认证", Toast.LENGTH_SHORT).show();
                    } else if (pstate7 == 4) {
                        Toast.makeText(getActivity(), "正在审核资料中，请耐心等待", Toast.LENGTH_SHORT).show();
                    } else if (pstate7 == 5) {
                        Toast.makeText(getActivity(), "账号已停用", Toast.LENGTH_SHORT).show();
                    } else if (pstate7 == 1) {
                        startActivity(new Intent(getActivity(), WaterNoCardActivity.class));
                    }
                }else{
                    MyApp.getInstance().showToast("此功能暂未开通,敬请期待");
                }

                break;
            case R.id.main_four:
                if ("1,1".equals(SPUtils.getString(getActivity(),"creditCardApplication"))){
//                    startActivity(new Intent(getActivity(), GetXinCardActivity.class));
                }else{
                    MyApp.getInstance().showToast("此功能暂未开通,敬请期待");
                }

                break;
            case R.id.main_five:
                if ("1,1".equals(SPUtils.getString(getActivity(),"creditCardPayment"))){
                    Toast.makeText(getActivity(), "程序猿正在努力开发中", Toast.LENGTH_SHORT).show();
                }else{
                    MyApp.getInstance().showToast("此功能暂未开通,敬请期待");
                }

                break;
            case R.id.main_six:
                if ("1,1".equals(SPUtils.getString(getActivity(),"quickAndEasyCredit"))){
                    startActivity(new Intent(getActivity(), EastDaiActivity.class));
                }else{
                    MyApp.getInstance().showToast("此功能暂未开通,敬请期待");
                }

                break;
            case R.id.main_seven:
                if ("1,1".equals(SPUtils.getString(getActivity(),"phoneReplenishing"))){
                    MyApp.getInstance().showToast("此功能暂未开通，敬请期待");
                    //startActivity(new Intent(getActivity(), TelmoneyActivity.class));
                }else{
                    MyApp.getInstance().showToast("此功能暂未开通,敬请期待");
                }

                break;
            case R.id.main_eight:
                if ("1,1".equals(SPUtils.getString(getActivity(),"wateFeePaid"))){
                    MyApp.getInstance().showToast("此功能暂未开通，敬请期待");
                    //startActivity(new Intent(getActivity(), WaterActivity.class));
                }else{
                    MyApp.getInstance().showToast("此功能暂未开通,敬请期待");
                }

                break;
            case R.id.main_nine:
                if ("1,1".equals(SPUtils.getString(getActivity(),"payElectricityBill"))){
                    MyApp.getInstance().showToast("此功能暂未开通，敬请期待");
                    //startActivity(new Intent(getActivity(), ElectricActivity.class));
                }else{
                    MyApp.getInstance().showToast("此功能暂未开通,敬请期待");
                }
                break;
            case R.id.main_ten:
                if ("1,1".equals(SPUtils.getString(getActivity(),"auth"))){
                    //获取登入patate赋值
                    Intent intent = new Intent();
                    intent.putExtra("pState", SPUtils.getInt(getContext(), "pState"));
                    Integer pstate = SPUtils.getInt(getContext(), "pState");
                    //获取登入isok值
                    Intent intent1 = new Intent();
                    intent.putExtra("isOK", SPUtils.getBoolean(getContext(), "isOK"));
                    boolean isOK = SPUtils.getBoolean(getContext(), "isOK");
                    if (pstate == 4) {
                        Log.e("pstate++++44+++++", pstate + "");
                        Toast.makeText(getActivity(), "资料认证中,请耐心等待审核", Toast.LENGTH_LONG).show();
                    } else if (pstate == 1) {
                        Log.e("pstate+++11++++++", pstate + "");
                        Toast.makeText(getActivity(), "您已认证成功，请勿重复认证", Toast.LENGTH_LONG).show();
                    } else if (pstate == 2 || pstate == 3) {
                        Log.e("pstate+++++++++", pstate + "");
//                        startActivity(new Intent(getActivity(), Renzheng_base.class));
                        startActivity(new Intent(getActivity(), RenzhengGuideActivity.class));
                    }
                }else{
                    MyApp.getInstance().showToast("此功能暂未开通,敬请期待");
                }

                break;
            case R.id.main_twlever:
                if ("1,1".equals(SPUtils.getString(getActivity(),"myTeam"))){
                    Integer pstate8 = SPUtils.getInt(getContext(), "pState");
                    if (pstate8 == 2) {
                        Toast.makeText(getActivity(), "您认证资料失败，请重新认证", Toast.LENGTH_SHORT).show();
                    } else if (pstate8 == 3) {
                        Toast.makeText(getActivity(), "您还未实名认证，请先认证", Toast.LENGTH_SHORT).show();
                    } else if (pstate8 == 4) {
                        Toast.makeText(getActivity(), "正在审核资料中，请耐心等待", Toast.LENGTH_SHORT).show();
                    } else if (pstate8 == 5) {
                        Toast.makeText(getActivity(), "账号已停用", Toast.LENGTH_SHORT).show();
                    } else if (pstate8 == 1) {
                        startActivity(new Intent(getActivity(), MyTeamAllFragment.class));
                    }
                }else{
                    MyApp.getInstance().showToast("此功能暂未开通,敬请期待");
                }

                break;
            case R.id.main_elever:
                if ("1,1".equals(SPUtils.getString(getActivity(),"sharingCeremony"))){
                    startActivity(new Intent(getActivity(), ShapeActivity.class));
                }else{
                    MyApp.getInstance().showToast("此功能暂未开通,敬请期待");
                }
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
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

    private void loginstate(String phone){
        Map<String,Object>map=new HashMap<>();
        map.put("phone",phone);
        XUtil.Post(Config.LoginStateServlet_URL,map,new MyCallBack<String>(){
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                User userStr = JSON.parseObject(result, User.class);
                if ("00".equals(userStr.getCode())){
                    //保存账号供调用
                    SPUtils.put(getActivity(), "phone", userStr.getMap().getPPhone());
                    SPUtils.put(getActivity(), "AmNumber", userStr.getMap().getAmNumber());
                    SPUtils.put(getActivity(), "pState", userStr.getMap().getPState());
                    SPUtils.put(getActivity(),"amName",userStr.getMap().getAmName());
                    SPUtils.put(getActivity(),"isOK",true);
                    SPUtils.put(getActivity(),"amIdNumber",userStr.getMap().getAmIdNumber());
                    SPUtils.put(getActivity(),"amAddress",userStr.getMap().getAmAddress());
                    SPUtils.put(getActivity(),"amPerson",userStr.getMap().getAmPerson());
                    SPUtils.put(getActivity(),"gradeName",userStr.getMap().getGradeName());
                }else if ("99".equals(userStr.getCode())){
                    MyApp.getInstance().showToast(userStr.getFailMessage());
                }

            }
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);

            }
        });
    }

    private void switchservlet(String mode){
        Map<String,Object> map=new HashMap<>();
        map.put("mode",mode);
        XUtil.Post(Config.SwitchServlet_URL,map,new MyCallBack<String>(){

            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                ControllerBean controllerBean= JSON.parseObject(result,ControllerBean.class);

                if ("00".equals(controllerBean.getCode())){
                    SPUtils.put(getActivity(),"101",controllerBean.getList().get(0).getSwitchName());
                    SPUtils.put(getActivity(),"qrCode",controllerBean.getList().get(0).getSwitchValue());

                    SPUtils.put(getActivity(),"102",controllerBean.getList().get(1).getSwitchName());
                    SPUtils.put(getActivity(),"mposReceivables",controllerBean.getList().get(1).getSwitchValue());

                    SPUtils.put(getActivity(),"103",controllerBean.getList().get(2).getSwitchName());
                    SPUtils.put(getActivity(),"noCardShortcut",controllerBean.getList().get(2).getSwitchValue());

                    SPUtils.put(getActivity(),"104",controllerBean.getList().get(3).getSwitchName());
                    SPUtils.put(getActivity(),"qrCodeWater",controllerBean.getList().get(3).getSwitchValue());

                    SPUtils.put(getActivity(),"105",controllerBean.getList().get(4).getSwitchName());
                    SPUtils.put(getActivity(),"mposReceivablesWater",controllerBean.getList().get(4).getSwitchValue());

                    SPUtils.put(getActivity(),"106",controllerBean.getList().get(5).getSwitchName());
                    SPUtils.put(getActivity(),"noCardShortcutWater",controllerBean.getList().get(5).getSwitchValue());

                    SPUtils.put(getActivity(),"107",controllerBean.getList().get(6).getSwitchName());
                    SPUtils.put(getActivity(),"creditCardApplication",controllerBean.getList().get(6).getSwitchValue());

                    SPUtils.put(getActivity(),"108",controllerBean.getList().get(7).getSwitchName());
                    SPUtils.put(getActivity(),"creditCardPayment",controllerBean.getList().get(7).getSwitchValue());

                    SPUtils.put(getActivity(),"109",controllerBean.getList().get(8).getSwitchName());
                    SPUtils.put(getActivity(),"quickAndEasyCredit",controllerBean.getList().get(8).getSwitchValue());

                    SPUtils.put(getActivity(),"110",controllerBean.getList().get(9).getSwitchName());
                    SPUtils.put(getActivity(),"phoneReplenishing",controllerBean.getList().get(9).getSwitchValue());

                    SPUtils.put(getActivity(),"111",controllerBean.getList().get(10).getSwitchName());
                    SPUtils.put(getActivity(),"wateFeePaid",controllerBean.getList().get(10).getSwitchValue());

                    SPUtils.put(getActivity(),"112",controllerBean.getList().get(11).getSwitchName());
                    SPUtils.put(getActivity(),"payElectricityBill",controllerBean.getList().get(11).getSwitchValue());

                    SPUtils.put(getActivity(),"113",controllerBean.getList().get(12).getSwitchName());
                    SPUtils.put(getActivity(),"auth",controllerBean.getList().get(12).getSwitchValue());

                    SPUtils.put(getActivity(),"114",controllerBean.getList().get(13).getSwitchName());
                    SPUtils.put(getActivity(),"myTeam",controllerBean.getList().get(13).getSwitchValue());

                    SPUtils.put(getActivity(),"115",controllerBean.getList().get(14).getSwitchName());
                    SPUtils.put(getActivity(),"sharingCeremony",controllerBean.getList().get(14).getSwitchValue());
                }
            }
        });

    }


}
