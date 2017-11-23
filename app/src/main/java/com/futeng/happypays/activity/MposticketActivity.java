package com.futeng.happypays.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Window;
import android.widget.ImageView;

import org.xutils.common.util.DensityUtil;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.HashMap;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;
import com.alibaba.fastjson.JSON;
import com.futeng.happypays.R;
import com.futeng.happypays.tools.Base64utils;
import com.futeng.happypays.tools.Config;
import com.futeng.happypays.tools.MyApp;
import com.futeng.happypays.tools.MyCallBack;
import com.futeng.happypays.tools.SPUtils;
import com.futeng.happypays.tools.XUtil;
import com.futeng.happypays.utils.GetTicketBean;
import com.futeng.happypays.utils.TicketBean;
import com.mylhyl.circledialog.CircleDialog;
import com.mylhyl.circledialog.params.ProgressParams;

public class MposticketActivity extends FragmentActivity {

    @InjectView(R.id.mposticketimg)
    ImageView mposticketimg;

    private DialogFragment dialogFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_mposticket);
        ButterKnife.inject(this);

        dialogFragment = new CircleDialog.Builder(this)
                .setProgressText("正在加载...")
                .setCanceledOnTouchOutside(false)
                .setProgressStyle(ProgressParams.STYLE_SPINNER)
                .show();

        String tran37=SPUtils.getString(this, "mpostran37");
        Log.e("---------+++++++++",tran37);

        getticket(tran37,"");
    }

    private void getticket(String tran37,String ticket){
        Map<String,Object>map=new HashMap<>();
        map.put("tran37",tran37);
        map.put("ticket",ticket);
        map.put("mode",3);

        XUtil.Post(Config.TicketServlet_URL,map,new MyCallBack<String>(){

            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);

                GetTicketBean getTicketBean= JSON.parseObject(result,GetTicketBean.class);
                if ("00".equals(getTicketBean.getCode())){
                    MyApp.getInstance().showToast(getTicketBean.getMessage());

                    String imgUrl = Base64utils.GenerateImage(getTicketBean.getSuccessMessage());

                    if(imgUrl != null){
                        dialogFragment.dismiss();
                        Bitmap bm = BitmapFactory.decodeFile(imgUrl);
                        mposticketimg.setImageBitmap(bm);
                    }else{
                        dialogFragment.dismiss();
                    }
                }else if ("99".equals(getTicketBean.getCode())){
                    MyApp.getInstance().showToast(getTicketBean.getFailMessage());
                    dialogFragment.dismiss();
                }
            }
        });
    }
}
