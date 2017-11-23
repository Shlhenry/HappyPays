package com.futeng.happypays.activity.watermoney.erweimawater;

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
import com.futeng.happypays.activity.watermoney.UnionPayActivity;
import com.futeng.happypays.activity.watermoney.WaterwechatmoneyActivity;
import com.futeng.happypays.activity.watermoney.waterzfbmoneyActivity;
import com.futeng.happypays.adapter.FragmentAdapter;

public class erweimaallActivity extends AppCompatActivity {

    List<Fragment> fragments = new ArrayList<>();

    @InjectView(R.id.erweimaall_back)
    ImageView erweimaallBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_erweimaall);
        ButterKnife.inject(this);

        //继承AppCompatActivity  必须用它去标题
        getSupportActionBar().hide();

        initViewPager();
    }

    private void initViewPager() {
        List<String> titles = new ArrayList<>();
        titles.add("微信支付流水");
        titles.add("支付宝支付流水");
//        titles.add("银联支付流水");
        for (int i = 0; i < titles.size(); i++) {
//            if(i%2==0){
//                fragments.add(new Fragment2());
//            }else{
//
//                fragments.add(new Fragment1());
//            }

            if (i == 0) {
                fragments.add(new WaterwechatmoneyActivity());
            } else if (i == 1) {
                fragments.add(new waterzfbmoneyActivity());
            }
//            else if (i == 2) {
//                fragments.add(new UnionPayActivity());
//            }

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

    @OnClick(R.id.erweimaall_back)
    public void onViewClicked() {
        finish();
    }
}
