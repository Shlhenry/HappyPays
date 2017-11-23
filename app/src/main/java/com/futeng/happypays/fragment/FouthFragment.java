package com.futeng.happypays.fragment;

/**
 * Created by Administrator on 2017/3/24.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import com.alibaba.fastjson.JSON;
import com.futeng.happypays.R;
import com.futeng.happypays.activity.LoginActivity;
import com.futeng.happypays.activity.UpVersionActivity;
import com.futeng.happypays.activity.fgmentfour.CardListActivity;
import com.futeng.happypays.activity.fgmentfour.ClearInfoActivity;
import com.futeng.happypays.activity.fgmentfour.GuildClearInfoActivity;
import com.futeng.happypays.activity.fgmentfour.InfoActivity;
import com.futeng.happypays.activity.fgmentfour.OurActivity;
import com.futeng.happypays.activity.fgmentfour.PasswordAgain;
import com.futeng.happypays.activity.watermoney.watermanage.WaterMoneyMainActivity;
import com.futeng.happypays.tools.Config;
import com.futeng.happypays.tools.MyApp;
import com.futeng.happypays.tools.MyCallBack;
import com.futeng.happypays.tools.SPUtils;
import com.futeng.happypays.tools.XUtil;
import com.futeng.happypays.utils.ControllerBean;
import com.futeng.happypays.utils.InfoBean;
import com.futeng.happypays.utils.JifenBean;
import com.futeng.happypays.utils.JifenguizeBean;
import com.futeng.happypays.utils.User;
import com.mylhyl.circledialog.CircleDialog;
import com.mylhyl.circledialog.callback.ConfigInput;
import com.mylhyl.circledialog.params.InputParams;
import com.mylhyl.circledialog.view.listener.OnInputClickListener;


public class FouthFragment extends Fragment {
    @InjectView(R.id.fg_xincard)
    RelativeLayout fgXincard;
    @InjectView(R.id.fg_pw)
    RelativeLayout fgPw;
    @InjectView(R.id.fg_info)
    RelativeLayout fgInfo;
    @InjectView(R.id.fg_up)
    RelativeLayout fgUp;
    @InjectView(R.id.fg_our)
    RelativeLayout fgOur;

    @InjectView(R.id.fgfour_phone)
    TextView fgfourPhone;
    @InjectView(R.id.fg_zero)
    RelativeLayout fgZero;
    @InjectView(R.id.fgfour_allmoney)
    TextView fgfourAllmoney;
    @InjectView(R.id.fgfour_usemoney)
    TextView fgfourUsemoney;
    @InjectView(R.id.fgfour_alljifen)
    TextView fgfourAlljifen;
    @InjectView(R.id.fgfour_jifenmoney)
    LinearLayout fgfourJifenmoney;
    @InjectView(R.id.fgfour_jifengtixian)
    TextView fgfourJifengtixian;
    @InjectView(R.id.fgfour_allmoneys)
    TextView fgfourAllmoneys;
    @InjectView(R.id.tuichu)
    RelativeLayout tuichu;
    @InjectView(R.id.fg_six)
    RelativeLayout fgSix;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_fours, container, false);
        ButterKnife.inject(this, v);

        switchservlet("1");

        String tel=SPUtils.getString(getActivity(), "phone");
        loginstate(tel);

        //获取总金额
        Intent intent1 = new Intent();
        intent1.putExtra("actAccountMoney", SPUtils.getString(getContext(), "actAccountMoney"));
        fgfourAllmoney.setText(SPUtils.getString(getContext(), "actAccountMoney"));

        //获取可用金额
        Intent intent2 = new Intent();
        intent2.putExtra("accountMoney", SPUtils.getString(getContext(), "accountMoney"));
        fgfourUsemoney.setText(SPUtils.getString(getContext(), "accountMoney"));

        //获取总积分
        Intent intent3 = new Intent();
        intent3.putExtra("accountIntegral", SPUtils.getString(getContext(), "accountIntegral"));
        fgfourAlljifen.setText(SPUtils.getString(getContext(), "accountIntegral"));

        //获取可提现积分
        Intent intent4 = new Intent();
        intent4.putExtra("ableIntegral", SPUtils.getString(getContext(), "ableIntegral"));
        fgfourJifengtixian.setText(SPUtils.getString(getContext(), "ableIntegral"));

        //获取登入手机号赋值
        Intent intent = new Intent();
        intent.putExtra("phone", SPUtils.getString(getContext(), "phone"));
        fgfourPhone.setText(SPUtils.getString(getContext(), "phone"));


        Intent intent5 = new Intent();
        intent5.putExtra("AmNumber", SPUtils.getString(getContext(), "AmNumber"));
        String amNumber = SPUtils.getString(getContext(), "AmNumber");
        //获取登入patate赋值
        Intent intent8 = new Intent();
        intent8.putExtra("pState", SPUtils.getInt(getContext(), "pState"));
        Integer pstate8 = SPUtils.getInt(getContext(), "pState");

        if (pstate8 == 1) {
            Log.e("******", "进入积分接口");
            jifen(amNumber);
        } else {
            Log.e("+++++", "未通过认证");
            fgfourAllmoney.setText(0.0 + "");
            fgfourUsemoney.setText(0.0 + "");
            fgfourAlljifen.setText(0 + "");
            fgfourJifengtixian.setText(0 + "");
        }
        return v;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @Override
    public void onResume() {
        super.onResume();

        Intent intent5 = new Intent();
        intent5.putExtra("AmNumber", SPUtils.getString(getContext(), "AmNumber"));
        String amNumber = SPUtils.getString(getContext(), "AmNumber");

        //获取登入patate赋值
        Intent intent8 = new Intent();
        intent8.putExtra("pState", SPUtils.getInt(getContext(), "pState"));
        Integer pstate8 = SPUtils.getInt(getContext(), "pState");

        if (pstate8 == 1) {
            jifen(amNumber);
        }

    }

    @OnClick({R.id.fg_xincard, R.id.fg_pw, R.id.fg_info, R.id.fg_up, R.id.fg_our, R.id.fg_zero, R.id.fgfour_allmoney, R.id.fgfour_usemoney, R.id.fgfour_alljifen, R.id.fgfour_jifenmoney, R.id.tuichu,R.id.fg_six})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fg_zero:

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
                    //startActivity(new Intent(getActivity(), WaterAllActivity.class));
                    startActivity(new Intent(getActivity(), WaterMoneyMainActivity.class));
                }

                break;
            case R.id.fg_xincard:

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
//                    startActivity(new Intent(getActivity(), XinCardActivity.class));
                    startActivity(new Intent(getActivity(), CardListActivity.class));

                }

                break;
            case R.id.fg_pw:
                startActivity(new Intent(getActivity(), PasswordAgain.class));
                break;
            case R.id.fg_info:
                //个人信息
                //获取登入patate赋值
                Intent intent2 = new Intent();
                intent2.putExtra("pState", SPUtils.getInt(getContext(), "pState"));
                Integer pstate = SPUtils.getInt(getContext(), "pState");
                if (pstate == 1) {
                    String amNumber = SPUtils.getString(getContext(), "AmNumber");
                    info(amNumber);
                } else {
                    Toast.makeText(getActivity(), "您还未进行实名认证或正在审核中，请认证通过再查看", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.fg_up:
                //Toast.makeText(getActivity(), "您当前版本为最新版本", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getActivity(), UpVersionActivity.class));

                break;
            case R.id.fg_our:
                startActivity(new Intent(getActivity(), OurActivity.class));
                break;

            case R.id.fgfour_allmoney:
                break;
            case R.id.fgfour_usemoney:
                break;
            case R.id.fgfour_alljifen:
                break;
            case R.id.tuichu:
                startActivity(new Intent(getActivity(), LoginActivity.class));
                getActivity().finish();
                break;
            case R.id.fgfour_jifenmoney:

                //获取登入patate赋值
                Intent intent8 = new Intent();
                intent8.putExtra("pState", SPUtils.getInt(getContext(), "pState"));
                Integer pstate8 = SPUtils.getInt(getContext(), "pState");

                if (pstate8 == 1) {

                    //获取积分规则
                    Intent intent1 = new Intent();
                    intent1.putExtra("rule", SPUtils.getString(getContext(), "rule"));
                    String Rule = SPUtils.getString(getContext(), "rule");

                    Log.e("积分规则", Rule + "");

                    new CircleDialog.Builder(getActivity())
                            .setCanceledOnTouchOutside(false)
                            .setCancelable(true)
                            .setTitle(Rule)
                            .setInputHeight(120)
                            .setInputHint("请输入你要兑换的积分")
                            .configInput(new ConfigInput() {
                                @Override
                                public void onConfig(InputParams params) {

                                }
                            }).setNegative("取消", null)
                            .setPositiveInput("确定", new OnInputClickListener() {
                                @Override
                                public void onClick(String text, View v) {

                                    Intent intent = new Intent();
                                    intent.putExtra("AmNumber", SPUtils.getString(getContext(), "AmNumber"));
                                    String amNumber = SPUtils.getString(getContext(), "AmNumber");

                                    itsIntegral(amNumber, text, 1 + "");


                                }
                            }).show();
                } else {
                    MyApp.getInstance().showToast("您还未实名认证，请先实名认证");
                }

                break;

            case R.id.fg_six:
                //获取登入patate赋值
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
                    String amNumber = SPUtils.getString(getContext(), "AmNumber");
                    guildinfo(amNumber);
                }
                break;

        }
    }

    private void itsIntegral(String amNumber, String itsIntegral, String mode) {
        Map<String, Object> map = new HashMap<>();
        map.put("amNumber", amNumber);
        map.put("itsIntegral", itsIntegral);
        map.put("mode", mode);

        XUtil.Post(Config.IntegralWithdrawalsServlet_URL, map, new MyCallBack<String>() {

            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);

                JifenguizeBean jifenguizeBean = JSON.parseObject(result, JifenguizeBean.class);

                if ("00".equals(jifenguizeBean.getCode())) {
                    MyApp.getInstance().showToast(jifenguizeBean.getSuccessMessage());

                    Intent intent = new Intent();
                    intent.putExtra("AmNumber", SPUtils.getString(getContext(), "AmNumber"));
                    String amNumber = SPUtils.getString(getContext(), "AmNumber");

                    jifen(amNumber);
                    //获取总金额
                    Intent intent1 = new Intent();
                    intent1.putExtra("actAccountMoney", SPUtils.getString(getContext(), "actAccountMoney"));
                    fgfourAllmoney.setText(SPUtils.getString(getContext(), "actAccountMoney"));

                    //获取可用金额
                    Intent intent2 = new Intent();
                    intent2.putExtra("accountMoney", SPUtils.getString(getContext(), "accountMoney"));
                    fgfourUsemoney.setText(SPUtils.getString(getContext(), "accountMoney"));

                    //获取总积分
                    Intent intent3 = new Intent();
                    intent3.putExtra("accountIntegral", SPUtils.getString(getContext(), "accountIntegral"));
                    fgfourAlljifen.setText(SPUtils.getString(getContext(), "accountIntegral"));

                    //获取可提现积分
                    Intent intent4 = new Intent();
                    intent4.putExtra("ableIntegral", SPUtils.getString(getContext(), "ableIntegral"));
                    fgfourJifengtixian.setText(SPUtils.getString(getContext(), "ableIntegral"));


                } else if ("99".equals(jifenguizeBean.getCode())) {
                    MyApp.getInstance().showToast(jifenguizeBean.getFailMessage());
                }
            }
        });

    }

    private void jifen(String amNumber) {

        Map<String, Object> map = new HashMap<>();
        map.put("amNumber", amNumber);

        XUtil.Post(Config.AMAccountServlet_URL, map, new MyCallBack<String>() {

            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);

                JifenBean jifenBean = JSON.parseObject(result, JifenBean.class);

                SPUtils.put(getContext(), "accountIntegral", jifenBean.getMap().getAccountIntegral());
                SPUtils.put(getContext(), "accountMoney", jifenBean.getMap().getAccountMoney());
                SPUtils.put(getContext(), "actAccountMoney", jifenBean.getMap().getActAccountMoney());
                SPUtils.put(getContext(), "ableIntegral", jifenBean.getMap().getAbleIntegral());

                fgfourAllmoney.setText(jifenBean.getMap().getActAccountMoney());
                fgfourUsemoney.setText(jifenBean.getMap().getAccountMoney());
                fgfourAlljifen.setText(jifenBean.getMap().getAccountIntegral());
                fgfourJifengtixian.setText(jifenBean.getMap().getAbleIntegral());


            }
        });

    }

    private void info(String amNumber){
        Map<String, Object> map = new HashMap<>();
        map.put("amNumber", amNumber);
        XUtil.Post(Config.AMDetailsServlet_URL,map,new MyCallBack<String>(){

            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                InfoBean infoBean=JSON.parseObject(result,InfoBean.class);

                if ("00".equals(infoBean.getCode())){
                    SPUtils.put(getContext(), "infoAccountName", infoBean.getMap().getAccountName());
                    SPUtils.put(getContext(), "infoAccountNumber", infoBean.getMap().getAccountNumber());
                    SPUtils.put(getContext(), "infoAmIdNumber", infoBean.getMap().getAmIdNumber());
                    SPUtils.put(getContext(), "infoAmName", infoBean.getMap().getAmName());
                    SPUtils.put(getContext(), "infoAmPerson", infoBean.getMap().getAmPerson());
                    SPUtils.put(getContext(), "infoAmPersonPhone", infoBean.getMap().getAmPersonPhone());
                    SPUtils.put(getContext(), "infoBankBranchNumber", infoBean.getMap().getBankBranchNumber());
                    SPUtils.put(getContext(), "infoBankName", infoBean.getMap().getBankName());

                    startActivity(new Intent(getActivity(), InfoActivity.class));
                }else {
                    MyApp.getInstance().showToast(infoBean.getFailMessage());
                }
            }
        });
    }

    private void guildinfo(String amNumber) {
        Map<String, Object> map = new HashMap<>();
        map.put("amNumber", amNumber);
        XUtil.Post(Config.AMDetailsServlet_URL, map, new MyCallBack<String>() {

            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                InfoBean infoBean = JSON.parseObject(result, InfoBean.class);

                if ("00".equals(infoBean.getCode())) {
                    SPUtils.put(getContext(), "infoAccountName", infoBean.getMap().getAccountName());
                    SPUtils.put(getContext(), "infoAccountNumber", infoBean.getMap().getAccountNumber());
                    SPUtils.put(getContext(), "infoAmIdNumber", infoBean.getMap().getAmIdNumber());
                    SPUtils.put(getContext(), "infoAmName", infoBean.getMap().getAmName());
                    SPUtils.put(getContext(), "infoAmPerson", infoBean.getMap().getAmPerson());
                    SPUtils.put(getContext(), "infoAmPersonPhone", infoBean.getMap().getAmPersonPhone());
                    SPUtils.put(getContext(), "infoBankBranchNumber", infoBean.getMap().getBankBranchNumber());
                    SPUtils.put(getContext(), "infoBankName", infoBean.getMap().getBankName());

                    startActivity(new Intent(getActivity(), GuildClearInfoActivity.class));
                } else {
                    MyApp.getInstance().showToast(infoBean.getFailMessage());
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
