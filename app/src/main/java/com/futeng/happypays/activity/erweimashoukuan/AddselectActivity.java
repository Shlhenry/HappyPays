package com.futeng.happypays.activity.erweimashoukuan;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.HashMap;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import com.alibaba.fastjson.JSON;
import com.futeng.happypays.R;
import com.futeng.happypays.activity.MainActivity;
import com.futeng.happypays.tools.Config;
import com.futeng.happypays.tools.DialogLogout;
import com.futeng.happypays.tools.MyApp;
import com.futeng.happypays.tools.MyCallBack;
import com.futeng.happypays.tools.SPUtils;
import com.futeng.happypays.tools.XUtil;
import com.futeng.happypays.utils.Bangkabean;

public class AddselectActivity extends Activity {

    @InjectView(R.id.addselect_back)
    ImageView addselectBack;
    @InjectView(R.id.addbangka_name)
    EditText addbangkaName;
    @InjectView(R.id.addbangka_num)
    EditText addbangkaNum;
    @InjectView(R.id.addbangcard_ok)
    Button addbangcardOk;

    private String mode="1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addselect);
        ButterKnife.inject(this);
    }

    @OnClick({R.id.addselect_back, R.id.addbangcard_ok})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.addselect_back:
                finish();
                break;
            case R.id.addbangcard_ok:
                String accountName=addbangkaName.getText().toString();
                String accountNumber=addbangkaNum.getText().toString();

                String amNumber=SPUtils.getString(getApplication(), "AmNumber");

                Log.e("+++商户号",amNumber+"");

                bangka(amNumber,mode,accountName,accountNumber);
                break;
        }
    }
    private void bangka(String amNumber,String mode,String accountName,String accountNumber){

        Map<String,Object> map=new HashMap<>();
        map.put("amNumber",amNumber);
        map.put("mode",mode);
        map.put("accountName",accountName);
        map.put("accountNumber",accountNumber);

        Log.e("+-+-+-+-+-",map+"");

        XUtil.Post(Config.AMBandCreditServlet_URL,map,new MyCallBack<String>(){
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                Bangkabean bangkabean= JSON.parseObject(result,Bangkabean.class);
                if ("00".equals(bangkabean.getCode())){
                    MyApp.getInstance().showToast(bangkabean.getSuccessMessage());
                    startActivity(new Intent(AddselectActivity.this, QuickTransationActivity.class));
                    finish();
                }else if ("99".equals(bangkabean.getCode())){
                    MyApp.getInstance().showToast(bangkabean.getFailMessage());
                }
            }
        });
    }
}
