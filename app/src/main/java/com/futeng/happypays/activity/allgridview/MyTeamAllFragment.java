package com.futeng.happypays.activity.allgridview;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.text.method.ScrollingMovementMethod;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import com.alibaba.fastjson.JSON;
import com.futeng.happypays.R;
import com.futeng.happypays.activity.allgridview.MyTeamFragment.MyTeamSecondfragment;
import com.futeng.happypays.activity.allgridview.MyTeamFragment.MyTeanThirldfragment;
import com.futeng.happypays.activity.allgridview.MyTeamFragment.MyteamFirstfragment;
import com.futeng.happypays.tools.Config;
import com.futeng.happypays.tools.MyApp;
import com.futeng.happypays.tools.MyCallBack;
import com.futeng.happypays.tools.MyDialog;
import com.futeng.happypays.tools.UserInfo;
import com.futeng.happypays.tools.XUtil;
import com.futeng.happypays.utils.RuleBean;

/**
 * 我的团队 Fragment
 */
public class MyTeamAllFragment extends FragmentActivity {
    @InjectView(R.id.team_back)
    ImageView teamBack;
    @InjectView(R.id.img_fgone)
    ImageView imgFgone;
    @InjectView(R.id.txt_fgone)
    TextView txtFgone;
    @InjectView(R.id.fgone)
    RelativeLayout fgone;
    @InjectView(R.id.img_fgtwo)
    ImageView imgFgtwo;
    @InjectView(R.id.txt_fgtwo)
    TextView txtFgtwo;
    @InjectView(R.id.fgtwo)
    RelativeLayout fgtwo;
    @InjectView(R.id.img_fgthree)
    ImageView imgFgthree;
    @InjectView(R.id.txt_fgthree)
    TextView txtFgthree;
    @InjectView(R.id.fgthree)
    RelativeLayout fgthree;
    @InjectView(R.id.cont)
    FrameLayout cont;
    @InjectView(R.id.rule)
    TextView rule;


    private MyteamFirstfragment team1;
    private MyTeamSecondfragment team2;
    private MyTeanThirldfragment team3;

    private UserInfo userInfo;

    private String rules;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.team);
        setDefaultFragment();
        ButterKnife.inject(this);
    }

    @OnClick({R.id.team_back, R.id.fgone, R.id.fgtwo, R.id.fgthree, R.id.cont, R.id.rule})
    public void onClick(View view) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        switch (view.getId()) {
            case R.id.team_back:
                finish();
                break;
            case R.id.fgone:
                if (team1 == null) {
                    team1 = new MyteamFirstfragment();
                }
                // 使用当前Fragment的布局替代id_content的控件
                // coo就是你在布局里放的fragment
                transaction.replace(R.id.cont, new MyteamFirstfragment());

                imgFgone.setImageDrawable(getResources().getDrawable(R.mipmap.myteamones));
                imgFgtwo.setImageDrawable(getResources().getDrawable(R.mipmap.myteamtwo));
                imgFgthree.setImageDrawable(getResources().getDrawable(R.mipmap.myteamthree));

                txtFgone.setTextColor(getResources().getColor(R.color.home));
                txtFgtwo.setTextColor(getResources().getColor(R.color.huis));
                txtFgthree.setTextColor(getResources().getColor(R.color.huis));
                break;
            case R.id.fgtwo:

                if (team2 == null) {
                    team2 = new MyTeamSecondfragment();
                }
                transaction.replace(R.id.cont, new MyTeamSecondfragment());

                imgFgone.setImageDrawable(getResources().getDrawable(R.mipmap.myteamone));
                imgFgtwo.setImageDrawable(getResources().getDrawable(R.mipmap.myteamtwos));
                imgFgthree.setImageDrawable(getResources().getDrawable(R.mipmap.myteamthree));

                txtFgone.setTextColor(getResources().getColor(R.color.huis));
                txtFgtwo.setTextColor(getResources().getColor(R.color.red));
                txtFgthree.setTextColor(getResources().getColor(R.color.huis));

                break;
            case R.id.fgthree:
                if (team3 == null) {
                    team3 = new MyTeanThirldfragment();
                }
                transaction.replace(R.id.cont, new MyTeanThirldfragment());

                imgFgone.setImageDrawable(getResources().getDrawable(R.mipmap.myteamone));
                imgFgtwo.setImageDrawable(getResources().getDrawable(R.mipmap.myteamtwo));
                imgFgthree.setImageDrawable(getResources().getDrawable(R.mipmap.myteamthrees));

                txtFgone.setTextColor(getResources().getColor(R.color.huis));
                txtFgtwo.setTextColor(getResources().getColor(R.color.huis));
                txtFgthree.setTextColor(getResources().getColor(R.color.orange));
                break;
            case R.id.cont:

                break;
            case R.id.rule:
                getrule();

                break;
        }
        //事物提交
        transaction.commit();
    }

    private void setDefaultFragment() {
        //开启
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        team1 = new MyteamFirstfragment();
        transaction.replace(R.id.cont, new MyteamFirstfragment());
        transaction.commit();

        imgFgone = (ImageView) findViewById(R.id.img_fgone);
        txtFgone = (TextView) findViewById(R.id.txt_fgone);

        imgFgone.setImageDrawable(getResources().getDrawable(R.mipmap.myteamones));
        txtFgone.setTextColor(getResources().getColor(R.color.home));


        Intent intert = getIntent();
        int id = intert.getIntExtra("grxx", -1);
        if (id > 0) {
            System.out.println("aaa" + id);
            if (id == 2) {
                transaction.replace(R.id.cont, new MyTeamSecondfragment()); //这里是指定跳转到指定的fragment
            } else if (id == 3) {
                transaction.replace(R.id.cont, new MyTeanThirldfragment()); //这里是指定跳转到指定的fragment
            }
        }
    }

    private void showCustomizeDialog() {
//    /* @setView 装入自定义View ==> R.layout.dialog_customize
//     * 由于dialog_customize.xml只放置了一个EditView，因此和图8一样
//     * dialog_customize.xml可自定义更复杂的View
//     */
//        AlertDialog.Builder customizeDialog = new AlertDialog.Builder(MyTeamAllFragment.this);
//        final View dialogView = LayoutInflater.from(MyTeamAllFragment.this).inflate(R.layout.dialog_rule, null);
//
//        customizeDialog.setView(dialogView);
//        TextView ruleText= (TextView) dialogView.findViewById(R.id.rule_text);
//        ruleText.setMovementMethod(ScrollingMovementMethod.getInstance());
//        ruleText.setText(rules);
//        customizeDialog.show();


        final MyDialog dialog = new MyDialog(this);
        LayoutInflater inflater = getLayoutInflater();
        LinearLayout layout = (LinearLayout)inflater.inflate(R.layout.dialog_rule, null);

        TextView ruleText= (TextView) layout.findViewById(R.id.rule_text);
        Button rulebtn= (Button) layout.findViewById(R.id.rule_btn);
        rulebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        ruleText.setMovementMethod(ScrollingMovementMethod.getInstance());
        ruleText.setText(rules);

        dialog.getWindow().setDimAmount(0);
        dialog.show();
        dialog.setCancelable(false);
        dialog.setContentView(layout);// show方法要在前面
    }

    private void getrule(){
        Map<String,Object>map=new HashMap<>();
        map.put("system","Android");
        XUtil.Post(Config.ExplainServlet_URL,map,new MyCallBack<String>(){
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                RuleBean ruleBean= JSON.parseObject(result,RuleBean.class);

                if ("00".equals(ruleBean.getCode())){
                    rules=ruleBean.getMap().getExpExplain();
                    showCustomizeDialog();
                }else {
                    MyApp.getInstance().showToast(ruleBean.getMessage());
                }

            }
        });
    }


}
