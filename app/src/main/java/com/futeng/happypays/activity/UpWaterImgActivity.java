package com.futeng.happypays.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.widget.ImageView;

import java.util.HashMap;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import com.alibaba.fastjson.JSON;
import com.futeng.happypays.R;
import com.futeng.happypays.tools.Base64utils;
import com.futeng.happypays.tools.Config;
import com.futeng.happypays.tools.MyApp;
import com.futeng.happypays.tools.MyCallBack;
import com.futeng.happypays.tools.SPUtils;
import com.futeng.happypays.tools.XUtil;
import com.futeng.happypays.utils.GetTicketBean;
import com.mylhyl.circledialog.CircleDialog;
import com.mylhyl.circledialog.params.ProgressParams;

public class UpWaterImgActivity extends FragmentActivity {

    @InjectView(R.id.upwaterimg)
    ImageView upwaterimg;
    @InjectView(R.id.ups_back)
    ImageView upsBack;

    private DialogFragment dialogFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_up_water_img);
        ButterKnife.inject(this);
        getticket();
    }

    private void getticket() {
        String ticketbase64=SPUtils.getString(getApplicationContext(),"ticketbase64");
        String imgUrl = Base64utils.GenerateImage(ticketbase64);

        if (imgUrl != null) {
            Bitmap bm = BitmapFactory.decodeFile(imgUrl);
            upwaterimg.setImageBitmap(bm);
        }
    }

    @OnClick(R.id.ups_back)
    public void onClick() {
        finish();
    }
}
