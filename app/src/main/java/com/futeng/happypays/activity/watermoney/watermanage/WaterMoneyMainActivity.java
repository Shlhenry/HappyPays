package com.futeng.happypays.activity.watermoney.watermanage;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import com.androidkun.xtablayout.XTabLayout;
import com.futeng.happypays.R;
import com.futeng.happypays.adapter.FragmentAdapter;

public class WaterMoneyMainActivity extends AppCompatActivity {


    List<Fragment> fragments = new ArrayList<>();

    @InjectView(R.id.waterall_back)
    ImageView waterallBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_money_main);
        ButterKnife.inject(this);
        //继承AppCompatActivity  必须用它去标题
        getSupportActionBar().hide();

        initViewPager();
    }

    private void initViewPager() {
        List<String> titles = new ArrayList<>();
        titles.add("D+0清算");
        titles.add("T+1清算");
        titles.add("资金变动");
        titles.add("积分变动");
        for (int i = 0; i < titles.size(); i++) {
            if (i == 0) {
                fragments.add(new T0WaterFragment());
            } else if (i == 1) {
                fragments.add(new T1WaterFragment());
            } else if (i == 2) {
                fragments.add(new MoneyChangeFragment());
            } else if (i == 3) {
                fragments.add(new InteralChangeFragment());
            }

        }
        FragmentAdapter adatper = new FragmentAdapter(getSupportFragmentManager(), fragments, titles);
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.setAdapter(adatper);
        viewPager.setOffscreenPageLimit(4);
        //将TabLayout和ViewPager关联起来。
        XTabLayout tabLayout = (XTabLayout) findViewById(R.id.xTablayout);
        tabLayout.setupWithViewPager(viewPager);
        //给TabLayout设置适配器
        tabLayout.setupWithViewPager(viewPager);
    }

    @OnClick(R.id.waterall_back)
    public void onClick() {
        finish();
    }
}
