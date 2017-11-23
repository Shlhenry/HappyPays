package com.futeng.happypays.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import org.xutils.x;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.futeng.happypays.R;
import com.futeng.happypays.tools.SPUtils;
import com.futeng.happypays.tools.XUtil;

public class GuideViewpageActivity extends Activity{

    @InjectView(R.id.viewpager)
    ViewPager viewpager;

    int versionCode;
    PackageInfo info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide_viewpage);
        ButterKnife.inject(this);

        getVersion();

        SPUtils.put(getApplication(),"guide","1");

        LayoutInflater mLi = LayoutInflater.from(this);

        View view1 = mLi.inflate(R.layout.viewpageone, null);
        ImageView guideone= (ImageView) view1.findViewById(R.id.guideone);
        Glide.with(this)
                .load("http://120.27.194.146/guideone.png")
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .dontAnimate().placeholder
                (R.mipmap.guideone).into(guideone);


        View view2 = mLi.inflate(R.layout.viewpagetwo, null);
        ImageView guidetwo= (ImageView) view2.findViewById(R.id.guidetwo);
        Glide.with(this)
                .load("http://120.27.194.146/guidetwo.png")
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .dontAnimate().placeholder
                (R.mipmap.guidetwo).into(guidetwo);


        View view3 = mLi.inflate(R.layout.viewpagethree, null);
        ImageView guidethree= (ImageView) view3.findViewById(R.id.guidethree);
        Glide.with(this)
                .load("http://120.27.194.146/guidethree.png")
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .dontAnimate().placeholder
                (R.mipmap.guidethree).into(guidethree);


        View view4 = mLi.inflate(R.layout.viewpagefour, null);
        ImageView guidefive= (ImageView) view4.findViewById(R.id.guidefive);
        ImageView guidefivebtn= (ImageView) view4.findViewById(R.id.guidefivebtn);

        Glide.with(this)
                .load("http://120.27.194.146/guidefive.png")
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .dontAnimate().placeholder
                (R.mipmap.guidefive).into(guidefive);

        guidefivebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GuideViewpageActivity.this,LoginActivity.class));
                finish();
            }
        });

        //每个页面的Title数据
        final ArrayList<View> views = new ArrayList<View>();
        views.add(view1);
        views.add(view2);
        views.add(view3);
        views.add(view4);

        //填充ViewPager的数据适配器
        PagerAdapter mPagerAdapter = new PagerAdapter() {

            @Override
            public boolean isViewFromObject(View arg0, Object arg1) {
                return arg0 == arg1;
            }

            @Override
            public int getCount() {
                return views.size();
            }

            @Override
            public void destroyItem(View container, int position, Object object) {
                ((ViewPager) container).removeView(views.get(position));
            }

            @Override
            public CharSequence getPageTitle(int position) {
//                return titles.get(position);
                return null;
            }

            @Override
            public Object instantiateItem(View container, int position) {
                ((ViewPager) container).addView(views.get(position));
                return views.get(position);
            }
        };

        viewpager.setAdapter(mPagerAdapter);

    }

    public void getVersion() {
        try {
            info = getPackageManager().getPackageInfo(getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        versionCode = info.versionCode;
        SPUtils.put(getApplication(),"code",versionCode);
        Log.e("登入版本号",versionCode+"");
    }

}
