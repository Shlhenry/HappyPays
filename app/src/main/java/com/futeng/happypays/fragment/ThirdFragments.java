package com.futeng.happypays.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

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
import com.futeng.happypays.activity.allgridview.MyTeamFragment.MyTeamFragments;
import com.futeng.happypays.activity.allgridview.MyTeamFragment.MyTeamSecondfragment;
import com.futeng.happypays.activity.allgridview.MyTeamFragment.MyteamFirstfragment;
import com.futeng.happypays.adapter.FragmentAdapter;
import com.futeng.happypays.tools.Config;
import com.futeng.happypays.tools.MyApp;
import com.futeng.happypays.tools.MyCallBack;
import com.futeng.happypays.tools.MyDialog;
import com.futeng.happypays.tools.SPUtils;
import com.futeng.happypays.tools.XUtil;
import com.futeng.happypays.utils.ProfitBean;
import com.futeng.happypays.utils.RuleBean;

public class ThirdFragments extends Fragment {
    List<Fragment> fragments = new ArrayList<>();
    @InjectView(R.id.rule)
    TextView rule;
    @InjectView(R.id.getintergral)
    TextView getintergral;
    @InjectView(R.id.allintergral)
    TextView allintergral;

    private String rules;
    private ProfitBean profitBean = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_third_fragments, container, false);
        initViewPager(v);
        ButterKnife.inject(this, v);

        String amnumber= SPUtils.getString(getActivity(),"AmNumber");
        profit(amnumber);

        return v;
    }

    private void initViewPager(View view) {
        List<String> titles = new ArrayList<>();
        titles.add("我推荐的人");
        titles.add("我");
        titles.add("我的推荐人");
        for (int i = 0; i < titles.size(); i++) {
            if (i == 0) {
                fragments.add(new MyTeamFragments());
            } else if (i == 1) {
                fragments.add(new MyteamFirstfragment());
            } else if (i == 2) {
                fragments.add(new MyTeamSecondfragment());
            }
        }
        FragmentAdapter adapter = new FragmentAdapter(getChildFragmentManager(), fragments, titles);
        ViewPager viewPager = (ViewPager) view.findViewById(R.id.viewPager);

        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(3);
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

    @OnClick(R.id.rule)
    public void onViewClicked() {
        getrule();
    }
    //规则弹框
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


        final MyDialog dialog = new MyDialog(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.dialog_rule, null);

        TextView ruleText = (TextView) layout.findViewById(R.id.rule_text);
        Button rulebtn = (Button) layout.findViewById(R.id.rule_btn);
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

    private void getrule() {
        Map<String, Object> map = new HashMap<>();
        map.put("system", "Android");
        XUtil.Post(Config.ExplainServlet_URL, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                RuleBean ruleBean = JSON.parseObject(result, RuleBean.class);

                if ("00".equals(ruleBean.getCode())) {
                    rules = ruleBean.getMap().getExpExplain();
                    showCustomizeDialog();
                } else {
                    MyApp.getInstance().showToast(ruleBean.getMessage());
                }

            }
        });
    }

    //收益
    private void profit(String amnumber) {
        Map<String, Object> map = new HashMap<>();
        map.put("mode", 1);
        map.put("awtIds", "15,16,18,19,21");
        map.put("amNumber", amnumber);
        XUtil.Post(Config.ProfitServlet_URL, map, new MyCallBack<String>() {

            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                profitBean = JSON.parseObject(result, ProfitBean.class);
                if ("00".equals(profitBean.getCode()) && profitBean.getMap() != null) {
                    getintergral.setText(profitBean.getMap().getRecomIntegral()+"");
                    allintergral.setText(profitBean.getMap().getSumIntegral()+"");
                } else {
                    getintergral.setText("0");
                    allintergral.setText("0");
                    MyApp.getInstance().showToast(profitBean.getFailMessage());
                    return;
                }
            }
        });
    }


}
