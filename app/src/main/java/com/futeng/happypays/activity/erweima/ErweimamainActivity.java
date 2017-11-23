package com.futeng.happypays.activity.erweima;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import com.alibaba.fastjson.JSON;
import com.futeng.happypays.R;
import com.futeng.happypays.tools.Config;
import com.futeng.happypays.tools.MyApp;
import com.futeng.happypays.tools.MyCallBack;
import com.futeng.happypays.tools.SPUtils;
import com.futeng.happypays.tools.XUtil;
import com.futeng.happypays.utils.UnionBean;
/**
 * 二维码收款
 */
public class ErweimamainActivity extends Activity {


    @InjectView(R.id.checkone)
    ImageView checkone;
    @InjectView(R.id.checktwo)
    ImageView checktwo;
    @InjectView(R.id.zfberweima)
    ImageView zfberweima;
    @InjectView(R.id.wechaterweima)
    ImageView wechaterweima;
    @InjectView(R.id.yinlianerweima)
    ImageView yinlianerweima;
    @InjectView(R.id.erweimamain_back)
    ImageView erweimamainBack;

    private String type="";
    private String mcc="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_erweimamain);
        ButterKnife.inject(this);

        checkone.setImageDrawable(getResources().getDrawable(R.mipmap.checks));
        checktwo.setImageDrawable(getResources().getDrawable(R.mipmap.check));
        type="T+1";

    }

    @OnClick({R.id.erweimamain_back, R.id.checkone, R.id.checktwo, R.id.zfberweima, R.id.wechaterweima, R.id.yinlianerweima})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.erweimamain_back:
//                Intent show = new Intent(this, MainActivity.class);
//                show.putExtra("grxx", 3);
//                startActivity(show);
                finish();
                break;
            case R.id.checkone:
                checkone.setImageDrawable(getResources().getDrawable(R.mipmap.checks));
                checktwo.setImageDrawable(getResources().getDrawable(R.mipmap.check));
                type="T+1";
                break;
            case R.id.checktwo:
                checktwo.setImageDrawable(getResources().getDrawable(R.mipmap.checks));
                checkone.setImageDrawable(getResources().getDrawable(R.mipmap.check));
                type="D+0";
                break;
            case R.id.zfberweima:
                if (type=="T+1"){
                    mcc=47+"";
                    SPUtils.put(getApplicationContext(), "zfbT1",mcc);
                    startActivity(new Intent(this, AlipayT1Activity.class));
                    Log.e("支付宝T1",mcc+"");
                }else if (type=="D+0"){
                    mcc=48+"";
                    SPUtils.put(getApplicationContext(), "zfbT0",mcc);
                    startActivity(new Intent(this, AlipayD0Activity.class));
                    Log.e("支付宝T0",mcc+"");
                }else if (type==""){
                    Toast.makeText(this,"请选择结算类型",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.wechaterweima:
                if (type=="T+1"){
                    mcc=45+"";
                    SPUtils.put(getApplicationContext(), "wechatT1",mcc);
                    startActivity(new Intent(this, WechatT1Activity.class));
                    Log.e("微信T1",mcc+"");
                }else if (type=="D+0"){
                    mcc=46+"";
                    SPUtils.put(getApplicationContext(), "wechatT0",mcc);
                    startActivity(new Intent(this, WechatD0Activity.class));
                    Log.e("微信T0",mcc+"");
                }else if (type==""){
                    Toast.makeText(this,"请选择结算类型",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.yinlianerweima:
                if (type=="D+0"){
                    MyApp.getInstance().showToast("暂不支持D+0交易");
                }else if(type=="T+1"){
                    String amNumber=SPUtils.getString(ErweimamainActivity.this, "AmNumber");
                    union(amNumber);
                }else if (type==""){
                    Toast.makeText(this,"请选择结算类型",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
    private void union(String amNumber){
        Map<String,Object> map=new HashMap<>();
        map.put("amNumber",amNumber);
        map.put("mcc",81);
        map.put("mode",2);
        XUtil.Post(Config.PayResultServlet_URL,map,new MyCallBack<String>(){
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);

                UnionBean union= JSON.parseObject(result,UnionBean.class);
                if ("00".equals(union.getCode())){
                    SPUtils.put(getApplicationContext(), "PsUrl", union.getMap().getPsUrl());
                    startActivity(new Intent(ErweimamainActivity.this,UnionActivity.class));

                }else if("99".equals(union.getCode())){
                    MyApp.getInstance().showToast(union.getFailMessage());
                }
            }
        });

    }
}
