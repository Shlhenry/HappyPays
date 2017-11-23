package com.futeng.happypays.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.futeng.happypays.R;
import com.futeng.happypays.activity.LoginActivity;
import com.futeng.happypays.activity.UpVersionActivity;
import com.futeng.happypays.activity.erweimashoukuan.HelpCenterActivity;
import com.futeng.happypays.activity.erweimashoukuan.LookLogisticActivity;
import com.futeng.happypays.activity.fgmentfour.CardListActivity;
import com.futeng.happypays.activity.fgmentfour.GuildClearInfoActivity;
import com.futeng.happypays.activity.fgmentfour.InfoActivity;
import com.futeng.happypays.activity.fgmentfour.OurActivity;
import com.futeng.happypays.activity.fgmentfour.PasswordAgain;
import com.futeng.happypays.activity.renzheng.RenzhengGuideActivity;
import com.futeng.happypays.activity.watermoney.watermanage.WaterMoneyNewActivity;
import com.futeng.happypays.tools.Config;
import com.futeng.happypays.tools.MyApp;
import com.futeng.happypays.tools.MyCallBack;
import com.futeng.happypays.tools.SPUtils;
import com.futeng.happypays.tools.XUtil;
import com.futeng.happypays.utils.InfoBean;

import java.util.HashMap;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class FouthFragments extends Fragment {

    @InjectView(R.id.customname)
    TextView customname;
    @InjectView(R.id.customtel)
    TextView customtel;
    @InjectView(R.id.pwd_grade)
    ImageView pwdGrade;
    @InjectView(R.id.recommend_people)
    TextView recommendPeople;
    @InjectView(R.id.identification_declare)
    TextView identificationDeclare;
    @InjectView(R.id.relativeLayout_identification)
    RelativeLayout relativeLayoutIdentification;
    @InjectView(R.id.relativeLayout_bankcardmanage)
    RelativeLayout relativeLayoutBankcardmanage;
    @InjectView(R.id.relativeLayout_updatecard)
    RelativeLayout relativeLayoutUpdatecard;
    @InjectView(R.id.relativeLayout_waterdetail)
    RelativeLayout relativeLayoutWaterdetail;
    @InjectView(R.id.relativeLayout_helpcenter)
    RelativeLayout relativeLayoutHelpcenter;
    @InjectView(R.id.relativeLayout_contactcustomer)
    LinearLayout relativeLayoutContactcustomer;
    @InjectView(R.id.relativeLayout_aboutus)
    RelativeLayout relativeLayoutAboutus;
    @InjectView(R.id.relativeLayout_certificate)
    RelativeLayout relativeLayoutCertificate;
    @InjectView(R.id.relativeLayout_set)
    RelativeLayout relativeLayoutSet;
    @InjectView(R.id.identification_img)
    ImageView identificationImg;
    @InjectView(R.id.relativeLayout_up)
    RelativeLayout relativeLayoutUp;
    @InjectView(R.id.relativeLayout_exit)
    RelativeLayout relativeLayoutExit;
    @InjectView(R.id.relativeLayout_logistic)
    RelativeLayout relativeLayoutLogistic;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_fouth_fragments, container, false);
        ButterKnife.inject(this, v);


        Integer pstate = SPUtils.getInt(getContext(), "pState");
        Log.e("-------", pstate + "");
        if (pstate == 1) {
            identificationDeclare.setText("已认证");
            identificationImg.setBackgroundResource(R.mipmap.verifiedorange);
        } else if (pstate == 2) {
            identificationDeclare.setText("认证失败，请重新认证");
            identificationDeclare.setTextColor(Color.RED);
            identificationImg.setBackgroundResource(R.mipmap.noverifie);
        } else if (pstate == 3) {
            identificationDeclare.setText("尚未认证");
            identificationDeclare.setTextColor(Color.RED);
            identificationImg.setBackgroundResource(R.mipmap.noverifie);
        } else if (pstate == 4) {
            identificationDeclare.setText("审核资料中，请耐心等待");
            identificationDeclare.setTextColor(Color.RED);
            identificationImg.setBackgroundResource(R.mipmap.renzhengzhong);
        } else if (pstate == 5) {
            identificationDeclare.setText("账号已停用");
            identificationDeclare.setTextColor(Color.RED);
            identificationImg.setBackgroundResource(R.mipmap.noverifie);
        }
        //获取登入手机号赋值
        customtel.setText(SPUtils.getString(getContext(), "phone"));
        customname.setText(SPUtils.getString(getContext(), "amName"));

        String a = passwordStrong(SPUtils.getString(getContext(), "password"));
        if ("1".equals(a)) {
            pwdGrade.setBackgroundResource(R.mipmap.low);
        } else if ("2".equals(a)) {
            pwdGrade.setBackgroundResource(R.mipmap.middle);
        } else if ("3".equals(a)) {
            pwdGrade.setBackgroundResource(R.mipmap.high);
        }

        return v;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @OnClick({R.id.relativeLayout_identification, R.id.relativeLayout_bankcardmanage, R.id.relativeLayout_updatecard, R.id.relativeLayout_waterdetail, R.id.relativeLayout_helpcenter, R.id.relativeLayout_contactcustomer, R.id.relativeLayout_aboutus, R.id.relativeLayout_certificate, R.id.relativeLayout_set, R.id.relativeLayout_up, R.id.relativeLayout_exit,R.id.relativeLayout_logistic})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.relativeLayout_identification:

                Integer pstate = SPUtils.getInt(getContext(), "pState");
                if (pstate == 4) {
                    Toast.makeText(getActivity(), "资料认证中,请耐心等待审核", Toast.LENGTH_LONG).show();
                } else if (pstate == 1) {
                    Toast.makeText(getActivity(), "您已认证成功，请勿重复认证", Toast.LENGTH_LONG).show();
                } else if (pstate == 2 || pstate == 3) {
                    startActivity(new Intent(getActivity(), RenzhengGuideActivity.class));
                } else {
                    MyApp.getInstance().showToast("此功能暂未开通,敬请期待");
                }

                break;
            case R.id.relativeLayout_bankcardmanage:
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
                    startActivity(new Intent(getActivity(), CardListActivity.class));
                }
                break;
            case R.id.relativeLayout_updatecard:

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
            case R.id.relativeLayout_waterdetail:
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
                    startActivity(new Intent(getActivity(), WaterMoneyNewActivity.class));
                }
                break;
            case R.id.relativeLayout_helpcenter:
                startActivity(new Intent(getActivity(), HelpCenterActivity.class));
                break;
            case R.id.relativeLayout_contactcustomer:
                break;
            case R.id.relativeLayout_aboutus:
                startActivity(new Intent(getActivity(), OurActivity.class));
                break;
            case R.id.relativeLayout_certificate:
                //个人信息
                //获取登入patate赋值
                Integer pstate1 = SPUtils.getInt(getContext(), "pState");
                if (pstate1 == 1) {
                    String amNumber = SPUtils.getString(getContext(), "AmNumber");
                    info(amNumber);
                } else {
                    Toast.makeText(getActivity(), "您还未进行实名认证或正在审核中，请认证通过再查看", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.relativeLayout_set:
                startActivity(new Intent(getActivity(), PasswordAgain.class));
                break;
            case R.id.relativeLayout_up:
                startActivity(new Intent(getActivity(), UpVersionActivity.class));
                break;
            case R.id.relativeLayout_exit:
                startActivity(new Intent(getActivity(), LoginActivity.class));
                getActivity().finish();
                break;
            case R.id.relativeLayout_logistic:
                startActivity(new Intent(getActivity(), LookLogisticActivity.class));
                Log.e("+++++","++++++++");
                break;


        }
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

    /**
     * 判断密码强度
     *
     * @return Z = 字母 S = 数字 T = 特殊字符
     */
    private String passwordStrong(String passwordStr) {
        if (TextUtils.equals("", passwordStr)) {
            return "出现故障";
        }
        String regexZ = "\\d*";
        String regexS = "[a-zA-Z]+";
        String regexT = "\\W+$";
        String regexZT = "\\D*";
        String regexST = "[\\d\\W]*";
        String regexZS = "\\w*";
        String regexZST = "[\\w\\W]*";
        //1-弱   2-中  3-强
        if (passwordStr.matches(regexZ)) {
            return "1";
        }
        if (passwordStr.matches(regexS)) {
            return "1";
        }
        if (passwordStr.matches(regexT)) {
            return "1";
        }
        if (passwordStr.matches(regexZT)) {
            return "2";
        }
        if (passwordStr.matches(regexST)) {
            return "2";
        }
        if (passwordStr.matches(regexZS)) {
            return "2";
        }
        if (passwordStr.matches(regexZST)) {
            return "3";
        }
        return passwordStr;
    }

    private void info(String amNumber) {
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

                    startActivity(new Intent(getActivity(), InfoActivity.class));
                } else {
                    MyApp.getInstance().showToast(infoBean.getFailMessage());
                }
            }
        });
    }


}
