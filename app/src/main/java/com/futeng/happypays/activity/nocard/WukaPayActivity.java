package com.futeng.happypays.activity.nocard;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import com.alibaba.fastjson.JSON;
import com.futeng.happypays.R;
import com.futeng.happypays.activity.LoginActivity;
import com.futeng.happypays.activity.UpWaterTicketActivity;
import com.futeng.happypays.tools.Base64;
import com.futeng.happypays.tools.Config;
import com.futeng.happypays.tools.MyApp;
import com.futeng.happypays.tools.MyCallBack;
import com.futeng.happypays.tools.SPUtils;
import com.futeng.happypays.tools.TimerButton;
import com.futeng.happypays.tools.XUtil;
import com.futeng.happypays.utils.NoCardBean;
import com.futeng.happypays.utils.NoCardOkBean;
import com.mylhyl.circledialog.CircleDialog;
import com.mylhyl.circledialog.params.ProgressParams;

public class WukaPayActivity extends FragmentActivity {

    @InjectView(R.id.wukapay_back)
    ImageView wukapayBack;
    @InjectView(R.id.wuka_ok)
    Button wukaOk;
    @InjectView(R.id.wuka_transAmt)
    EditText wukaTransAmt;
    @InjectView(R.id.wuka_phoneNo)
    EditText wukaPhoneNo;
    @InjectView(R.id.wuka_cerdId)
    EditText wukaCerdId;
    @InjectView(R.id.nocard_cardnum)
    TextView nocardCardnum;
    @InjectView(R.id.edit_wuka_yzm)
    EditText editWukaYzm;
    @InjectView(R.id.wuka_endok_btn)
    TimerButton wukaEndokBtn;

    private TimerButton mFindCodeBtn;
    private DialogFragment dialogFragment;
    private static boolean i=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_wuka_pay);
        ButterKnife.inject(this);

        nocardCardnum.setText(SPUtils.getString(WukaPayActivity.this, "nocardAccountNumber"));
        mFindCodeBtn = (TimerButton) findViewById(R.id.wuka_ok);
    }

    @OnClick({R.id.wukapay_back, R.id.wuka_ok,R.id.wuka_endok_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.wukapay_back:
                finish();
                break;
            case R.id.wuka_ok:
                String money = wukaTransAmt.getText().toString();
                String phone = wukaPhoneNo.getText().toString();
                String cerdNo = wukaCerdId.getText().toString();
                String mcc = SPUtils.getString(getApplication(), "wukamcc");
                //获取登入商户号赋值
                String mercNo = SPUtils.getString(WukaPayActivity.this, "AmNumber");
                //获取姓名
                String accountName1 = SPUtils.getString(WukaPayActivity.this, "nocardAccountName");
                //utf-8转码
                String accountName = Base64.encodeBytes(accountName1.getBytes());
                //获取银行卡卡号
                String bankCard = SPUtils.getString(WukaPayActivity.this, "nocardAccountNumber");
                if (TextUtils.isEmpty(wukaTransAmt.getText())) {
                    Toast.makeText(WukaPayActivity.this, "交易金额不能为空", Toast.LENGTH_SHORT).show();
                    return;
                } else if (TextUtils.isEmpty(wukaPhoneNo.getText())) {
                    Toast.makeText(WukaPayActivity.this, "手机号不能为空", Toast.LENGTH_SHORT).show();
                    return;
                } else if (TextUtils.isEmpty(wukaCerdId.getText())) {
                    Toast.makeText(WukaPayActivity.this, "身份证不能为空", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    nocard(cerdNo, money, phone, mercNo, mcc, bankCard);
                    mFindCodeBtn.setCallback(new TimerButton.Callback() {
                        @Override
                        public String getTickerText() {
                            return "已确认(%ds)";
                        }
                        @Override
                        public int getMaxTime() {
                            return 5;
                        }
                    });
                    mFindCodeBtn.start();
                }
                break;
            case R.id.wuka_endok_btn:
                if (TextUtils.isEmpty(editWukaYzm.getText())){
                    Toast.makeText(WukaPayActivity.this,"请输入验证码",Toast.LENGTH_LONG).show();
                }else{

                    dialogFragment = new CircleDialog.Builder(WukaPayActivity.this)
                            .setProgressText("正在交易，请稍等。。。")
//                            .setCanceledOnTouchOutside(false)
                            .setProgressStyle(ProgressParams.STYLE_SPINNER)
                            .show();

                    String orderId = SPUtils.getString(this, "orderId");
                    String smsCode=editWukaYzm.getText().toString();
                    //无卡消费请求
                    wukaok(orderId,smsCode);

                }
                break;
        }
    }
    //获取SIM码请求
    private void nocard(String cerdNo, String money, String phone,
                        String mercNo, String mcc, String bankCard) {
        Map<String, Object> map = new HashMap<>();
        map.put("identityCard", cerdNo);//身份证号
        map.put("txnAmt", money);//金额
        map.put("phoneNo", phone);//手机号
        map.put("mercNo", mercNo);//商户号
        map.put("mcc", mcc);//mcc
        map.put("accountNumber", bankCard);//卡号

        Log.e("无卡map+++", map + "");

        XUtil.Post(Config.getSmsCode_NoCardURL, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                NoCardBean nocardbean = JSON.parseObject(result, NoCardBean.class);
                if ("0000".equals(nocardbean.getResultCode())) {
                    SPUtils.put(getApplication(),"orderId",nocardbean.getOrderId());
                    MyApp.getInstance().showToast(nocardbean.getMessage());
                } else {
                    MyApp.getInstance().showToast(nocardbean.getMessage());
                }
            }
        });
    }

    //无卡交易消费请求
    private void wukaok(String orderId, String smsCode) {
        Map<String, Object> map = new HashMap<>();
        map.put("orderId", orderId);
        map.put("smsCode", smsCode);

        XUtil.Post(Config.bcconServlet_NoCardURL, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                NoCardBean nocardbean = JSON.parseObject(result, NoCardBean.class);
                MyApp.getInstance().showToast(nocardbean.getMessage());
                SPUtils.put(getApplicationContext(), "orderNo", nocardbean.getOrderId());

                //存订单号，来加载对应的小票图片
                SPUtils.put(getApplication(),"tran37",nocardbean.getOrderId());

                if ("0000".equals(nocardbean.getResultCode())) {
                    //交易成功发起查询
                    new Thread() {
                        public void run() {
                            try {
                                sleep(2000);
                                findwuka(SPUtils.getString(getApplication(),"orderNo"));
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }.start();
                }else{
                    //交易失败弹框取消
                    dialogFragment.dismiss();
                    MyApp.getInstance().showToast(nocardbean.getMessage());
                }
            }
        });
    }
    //无卡交易查询
    private void findwuka(String orderId ){
        Map<String,Object>map=new HashMap<>();
        map.put("orderId",orderId );
        Log.e("+++orderId ++++",map+"");
        XUtil.Post(Config.query_NoCardURL,map,new MyCallBack<String>(){
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                NoCardBean noCardOkBean=JSON.parseObject(result,NoCardBean.class);
                if ("0000".equals(noCardOkBean.getResultCode())){
                    //查询成功 弹框取消 跳转上传小票界面

                    //存金额传给电子小票
                    SPUtils.put(getApplicationContext(), "upmoney", wukaTransAmt.getText().toString());
                    //存时间传给电子小票
                    long end = System.currentTimeMillis();
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String hms1 = formatter.format(end);
                    SPUtils.put(getApplicationContext(), "uptime", hms1);

                    dialogFragment.dismiss();
                    startActivity(new Intent(WukaPayActivity.this, UpWaterTicketActivity.class));

                }else {
                    //查询失败 弹框取消
                    dialogFragment.dismiss();
                    MyApp.getInstance().showToast(noCardOkBean.getMessage());
                }
            }
        });
    }

}
