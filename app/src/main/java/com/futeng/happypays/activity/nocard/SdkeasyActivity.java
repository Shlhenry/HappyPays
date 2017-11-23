package com.futeng.happypays.activity.nocard;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
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
import com.futeng.happypays.activity.UpWaterTicketActivity;
import com.futeng.happypays.tools.Config;
import com.futeng.happypays.tools.MyApp;
import com.futeng.happypays.tools.MyCallBack;
import com.futeng.happypays.tools.SPUtils;
import com.futeng.happypays.tools.XUtil;
import com.futeng.happypays.utils.GettokenBean;
import com.mylhyl.circledialog.CircleDialog;
import com.mylhyl.circledialog.params.ProgressParams;

public class SdkeasyActivity extends FragmentActivity {


    @InjectView(R.id.smokpay_back)
    ImageView smokpayBack;
    @InjectView(R.id.smsdk)
    EditText smsdk;
    @InjectView(R.id.nocardpay)
    Button nocardpay;
    @InjectView(R.id.nocardfind)
    TextView nocardfind;

    private DialogFragment dialogFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sdkeasy);
        ButterKnife.inject(this);

    }

    @OnClick({R.id.smokpay_back, R.id.nocardpay, R.id.nocardfind})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.smokpay_back:
                finish();
                break;
            case R.id.nocardpay:

                dialogFragment = new CircleDialog.Builder(SdkeasyActivity.this)
                        .setProgressText("正在支付，请稍等。。。")
//                            .setCanceledOnTouchOutside(false)
                        .setProgressStyle(ProgressParams.STYLE_SPINNER)
                        .show();

                String accountNumber= SPUtils.getString(SdkeasyActivity.this, "nocardAccountNumber");
                String realName=SPUtils.getString(SdkeasyActivity.this, "nocardAccountName");
                String mercNo=SPUtils.getString(SdkeasyActivity.this, "amNumber");
                String mcc="83";
                String identityCard=SPUtils.getString(getApplication(),"identityCard");
                String phoneNo=SPUtils.getString(getApplication(),"phoneNo");
                String txtAmt=SPUtils.getString(getApplication(),"txtAmt");
                String smsCode=smsdk.getText().toString();
                String orderId=SPUtils.getString(getApplication(),"orderId");

                bcconEasyPay(realName,accountNumber,identityCard,phoneNo,txtAmt,mcc,mercNo,smsCode,orderId);
                break;
            case R.id.nocardfind:
                dialogFragment = new CircleDialog.Builder(SdkeasyActivity.this)
                        .setProgressText("正在查询，请稍等。。。")
//                            .setCanceledOnTouchOutside(false)
                        .setProgressStyle(ProgressParams.STYLE_SPINNER)
                        .show();

                String orderIds=SPUtils.getString(getApplication(),"orderId");
                easyFindOrder(orderIds);
                break;
        }
    }

    //确认支付
    private void bcconEasyPay(String realName,String accountNumber,String identityCard,String phoneNo,String txtAmt,String mcc,String mercNo,String smsCode,String orderId) {
        Map<String,Object>map=new HashMap<>();
        map.put("realName",realName);
        map.put("accountNumber",accountNumber);
        map.put("identityCard",identityCard);
        map.put("phoneNo",phoneNo);
        map.put("txtAmt",txtAmt);
        map.put("mcc",mcc);
        map.put("mercNo",mercNo);
        map.put("smsCode",smsCode);
        map.put("orderId",orderId);

        XUtil.Post(Config.bcconEasyPay_NoCardURL,map,new MyCallBack<String>(){
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);

                dialogFragment.dismiss();

                GettokenBean gettokenBean= JSON.parseObject(result,GettokenBean.class);
                if ("0000".equals(gettokenBean.getResultCode())){
                    nocardfind.setVisibility(View.VISIBLE);
                    MyApp.getInstance().showToast(gettokenBean.getMessage());
                }else {
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

    //查询成功
    private void easyFindOrder(String orderId){

        Map<String,Object>map=new HashMap<>();
        map.put("orderId",orderId);
        XUtil.Post(Config.easyFindOrder_NoCardURL,map,new MyCallBack<String>(){

            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);

                dialogFragment.dismiss();

                GettokenBean gettokenBean= JSON.parseObject(result,GettokenBean.class);
                if ("0000".equals(gettokenBean.getResultCode())){
                        //打印小票0000
                    startActivity(new Intent(SdkeasyActivity.this, UpWaterTicketActivity.class));
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
