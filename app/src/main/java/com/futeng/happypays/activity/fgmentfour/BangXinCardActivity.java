package com.futeng.happypays.activity.fgmentfour;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
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
import com.futeng.happypays.tools.MyApp;
import com.futeng.happypays.tools.MyCallBack;
import com.futeng.happypays.tools.SPUtils;
import com.futeng.happypays.tools.XUtil;
import com.futeng.happypays.utils.Bangkabean;

/**
 * 绑定银行卡
 */
public class BangXinCardActivity extends Activity {


    @InjectView(R.id.bangcardnext_back)
    ImageView bangcardnextBack;
    @InjectView(R.id.bangka_name)
    EditText bangkaName;
    @InjectView(R.id.bangka_num)
    EditText bangkaNum;
    @InjectView(R.id.bangcard_ok)
    Button bangcardOk;

    private String mode="1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.avtivity_bangcard);
        ButterKnife.inject(this);
    }


    @OnClick({R.id.bangcardnext_back, R.id.bangcard_ok})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bangcardnext_back:
                finish();
                break;
            case R.id.bangcard_ok:

                String accountName=bangkaName.getText().toString();
                String accountNumber=bangkaNum.getText().toString();

                Intent intent = new Intent();
                intent.putExtra("AmNumber", SPUtils.getString(BangXinCardActivity.this, "AmNumber"));
                String amNumber=SPUtils.getString(BangXinCardActivity.this, "AmNumber");

                Log.e("+++商户号",amNumber+"");

                bangka(amNumber,mode,accountName,accountNumber);
                break;
        }
    }

    private void bangka(String amNumber,String mode,String accountName,String accountNumber){

        Map<String,Object>map=new HashMap<>();
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
                    Intent show = new Intent(BangXinCardActivity.this, MainActivity.class);
                    show.putExtra("grxx", 4);
                    startActivity(show);
                    finish();
                }else if ("99".equals(bangkabean.getCode())){
                    MyApp.getInstance().showToast(bangkabean.getFailMessage());
                }
            }
        });
    }

}
