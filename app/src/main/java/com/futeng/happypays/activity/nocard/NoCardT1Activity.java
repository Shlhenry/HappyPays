package com.futeng.happypays.activity.nocard;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import com.alibaba.fastjson.JSON;
import com.futeng.happypays.R;
import com.futeng.happypays.activity.webview.WebviewNoCard;
import com.futeng.happypays.tools.Config;
import com.futeng.happypays.tools.MyApp;
import com.futeng.happypays.tools.MyCallBack;
import com.futeng.happypays.tools.SPUtils;
import com.futeng.happypays.tools.TimerButton;
import com.futeng.happypays.tools.XUtil;
import com.futeng.happypays.utils.GettokenBean;
import com.mylhyl.circledialog.CircleDialog;
import com.mylhyl.circledialog.params.ProgressParams;

public class NoCardT1Activity extends FragmentActivity {

    @InjectView(R.id.wukaokpay_back)
    ImageView wukaokpayBack;
    @InjectView(R.id.wuka_cardnum)
    TextView wukaCardnum;
    @InjectView(R.id.wuka_tel)
    EditText wukaTel;
    @InjectView(R.id.wuka_id)
    EditText wukaId;
    @InjectView(R.id.wuka_money)
    EditText wukaMoney;
    @InjectView(R.id.okpay)
    TimerButton okpay;

    private DialogFragment dialogFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_card_t1);
        ButterKnife.inject(this);

        wukaCardnum.setText(SPUtils.getString(NoCardT1Activity.this, "nocardAccountNumber"));


    }

    @OnClick({R.id.wukaokpay_back, R.id.okpay})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.wukaokpay_back:
                finish();
                break;
            case R.id.okpay:
                dialogFragment = new CircleDialog.Builder(NoCardT1Activity.this)
                        .setProgressText("正在交易，请稍等。。。")
//                            .setCanceledOnTouchOutside(false)
                        .setProgressStyle(ProgressParams.STYLE_SPINNER)
                        .show();

                String accountNumber=SPUtils.getString(NoCardT1Activity.this, "nocardAccountNumber");
                String realName=SPUtils.getString(NoCardT1Activity.this, "nocardAccountName");
                String mercNo=SPUtils.getString(NoCardT1Activity.this, "amNumber");
                String mcc="83";
                String identityCard=wukaId.getText().toString();
                SPUtils.put(getApplication(),"identityCard",identityCard);

                String phoneNo=wukaTel.getText().toString();
                SPUtils.put(getApplication(),"phoneNo",phoneNo);

                String txtAmt=wukaMoney.getText().toString();
                SPUtils.put(getApplication(),"txtAmt",txtAmt);
                SPUtils.put(getApplication(),"upmoney",txtAmt);


                sdkEasy_NoCardURL(realName,accountNumber,identityCard,phoneNo,txtAmt,mcc,mercNo);

                break;
        }
    }
    //下单获取验证码
    private void sdkEasy_NoCardURL(String realName,String accountNumber,String identityCard,String phoneNo,String txtAmt,String mcc,String mercNo){
        Map<String,Object>map=new HashMap<>();
        map.put("realName",realName);
        map.put("accountNumber",accountNumber);
        map.put("identityCard",identityCard);
        map.put("phoneNo",phoneNo);
        map.put("txtAmt",txtAmt);
        map.put("mcc",mcc);
        map.put("mercNo",mercNo);

        XUtil.Post(Config.sdkEasy_NoCardURL,map,new MyCallBack<String>(){
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                dialogFragment.dismiss();
                GettokenBean gettokenBean= JSON.parseObject(result,GettokenBean.class);
                if ("true".equals(gettokenBean.getResultCode())){
                    SPUtils.put(getApplication(),"HtmlContent",gettokenBean.getHtmlContent());
                    startActivity(new Intent(NoCardT1Activity.this, WebviewNoCard.class));
                }else if ("0000".equals(gettokenBean.getResultCode())){
                    SPUtils.put(getApplication(),"orderId",gettokenBean.getOrderId());
                    SPUtils.put(getApplication(),"tran37",gettokenBean.getOrderId());
                    startActivity(new Intent(NoCardT1Activity.this,SdkeasyActivity.class));
                }else{
                    MyApp.getInstance().showToast(gettokenBean.getMessage());
                }
            }
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
                dialogFragment.dismiss();
            }
        });
    }

}
