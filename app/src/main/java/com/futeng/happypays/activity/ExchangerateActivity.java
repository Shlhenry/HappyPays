package com.futeng.happypays.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
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
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.futeng.happypays.R;
import com.futeng.happypays.tools.Config;
import com.futeng.happypays.tools.MyCallBack;
import com.futeng.happypays.tools.SPUtils;
import com.futeng.happypays.tools.XUtil;
import com.futeng.happypays.utils.RealtimenextBean;

public class ExchangerateActivity extends FragmentActivity {

    @InjectView(R.id.exchange_back)
    ImageView exchangeBack;
    @InjectView(R.id.rela_time)
    TextView relaTime;
    @InjectView(R.id.RelativeLayout_quicktrans)
    RelativeLayout RelativeLayoutQuicktrans;
    @InjectView(R.id.national_flag)
    ImageView nationalFlag;
    @InjectView(R.id.national)
    TextView national;
    @InjectView(R.id.LinearLayout_exchangerate)
    LinearLayout LinearLayoutExchangerate;
    @InjectView(R.id.textView3)
    TextView textView3;
    @InjectView(R.id.textView4)
    TextView textView4;
    @InjectView(R.id.national_easy)
    TextView nationalEasy;
    @InjectView(R.id.rate)
    TextView rate;
    @InjectView(R.id.rates)
    TextView rates;
    @InjectView(R.id.EditText_exchange)
    EditText EditTextExchange;

    private RealtimenextBean realtimenextBean=null;
    private String ExcValue=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exchangerate);
        ButterKnife.inject(this);


        ExcValue=SPUtils.getString(getApplication(),"ExcValue");

        Log.e("***",ExcValue+"");
        defaultrate();
        EditTextExchange.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.e("+++++++",s+"");
                showexchange(s.toString(),SPUtils.getString(getApplicationContext(), "curCode"));

            }
        });




    }

    @OnClick({R.id.exchange_back, R.id.rela_time, R.id.LinearLayout_exchangerate})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.exchange_back:
                finish();
                break;
            case R.id.rela_time:
                startActivity(new Intent(ExchangerateActivity.this, RealtimeActivity.class));
                break;
            case R.id.LinearLayout_exchangerate:
                startActivity(new Intent(ExchangerateActivity.this, IndexsActivity.class));
                finish();
                break;
        }
    }

    private void defaultrate() {

        Glide.with(this)
                .load(SPUtils.getString(getApplicationContext(), "curImgUrl"))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .dontAnimate().placeholder
                (R.mipmap.flagtongyong).into(nationalFlag);
        national.setText(SPUtils.getString(getApplicationContext(), "curName"));
        nationalEasy.setText(SPUtils.getString(getApplicationContext(), "curCode"));
        rate.setText(ExcValue);
        rates.setText(ExcValue);
        Log.e("123456",ExcValue+"");
    }

    private void showexchange(String moneyOne,String codeOne){
        Map<String,Object> map=new HashMap<>();
        map.put("mode",2);
        map.put("moneyOne",moneyOne);
        map.put("codeTwo","CNY");
        map.put("codeOne",codeOne);

        XUtil.Post(Config.ExchangerateServlet_URL,map,new MyCallBack<String>(){
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                realtimenextBean= JSON.parseObject(result,RealtimenextBean.class);
                if ("00".equals(realtimenextBean.getCode())){
                    if (realtimenextBean.getMap()!=null){
                        ExcValue=realtimenextBean.getMap().getExcValue();
                        Log.e("*******",ExcValue);
                        rate.setText(ExcValue);
                        rates.setText(ExcValue);
                    }else{
                        return;
                    }
                }
            }
        });
    }




}
