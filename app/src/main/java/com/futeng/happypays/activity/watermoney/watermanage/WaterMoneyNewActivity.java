package com.futeng.happypays.activity.watermoney.watermanage;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.futeng.happypays.R;
import com.futeng.happypays.utils.TabEntity;
import com.futeng.happypays.utils.ViewFindUtils;

import java.util.ArrayList;
import java.util.Random;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class WaterMoneyNewActivity extends FragmentActivity {

    @InjectView(R.id.waterall_back)
    ImageView waterallBack;
    private String[] mTitles = {"T1清算", "D0清算", "资金变动", "积分变动", "红包流水", "团队分润"};
    private int[] mIconUnselectIds = {
            R.mipmap.tyiqingsuan, R.mipmap.dlingqingsuan,
            R.mipmap.zijinbiandong, R.mipmap.jifenbiandong,
            R.mipmap.hongbaojilu, R.mipmap.fenrunliushui};
    private int[] mIconSelectIds = {
            R.mipmap.tyiqingsuan, R.mipmap.dlingqingsuan,
            R.mipmap.zijinbiandong, R.mipmap.jifenbiandong,
            R.mipmap.hongbaojilu, R.mipmap.fenrunliushui};

    private CommonTabLayout mTabLayout_2;

    private View mDecorView;

    private ViewPager mViewPager;
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();

    Random mRandom = new Random();

    private ArrayList<Fragment> mFragments = new ArrayList<>();

    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_water_money_new);
        ButterKnife.inject(this);


        for (int i = 0; i < mTitles.length; i++) {
            if (i == 0) {
                mFragments.add(new T1WaterFragment());
            } else if (i == 1) {
                mFragments.add(new T0WaterFragment());
            } else if (i == 2) {
                mFragments.add(new MoneyChangeFragment());
            } else if (i == 3) {
                mFragments.add(new InteralChangeFragment());
            } else if (i == 4) {
                mFragments.add(new RedPacketFragment());
            } else if (i == 5) {
                mFragments.add(new ShareProfitFragment());
            }

        }

        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }
        mDecorView = getWindow().getDecorView();
        mViewPager = ViewFindUtils.find(mDecorView, R.id.xxxvp_2);

        mTabLayout_2 = ViewFindUtils.find(mDecorView, R.id.xxxtl_2);

        mViewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));

        tl_2();

    }

    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    private void tl_2() {
        mTabLayout_2.setTabData(mTabEntities);
        mTabLayout_2.setOnTabSelectListener(new OnTabSelectListener() {
            @TargetApi(Build.VERSION_CODES.GINGERBREAD)
            @Override
            public void onTabSelect(int position) {
                mViewPager.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {
                if (position == 0) {
                    mTabLayout_2.showMsg(0, mRandom.nextInt(100) + 1);
//                    UnreadMsgUtils.show(mTabLayout_2.getMsgView(0), mRandom.nextInt(100) + 1);
                }
            }
        });

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mTabLayout_2.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mViewPager.setCurrentItem(1);
    }

    @OnClick(R.id.waterall_back)
    public void onViewClicked() {
        finish();
    }

    private class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }
    }

}
