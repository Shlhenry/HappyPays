package com.futeng.happypays.activity.erweimashoukuan;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.net.URLDecoder;
import java.text.SimpleDateFormat;
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
import com.futeng.happypays.utils.Geturl;
import com.futeng.happypays.utils.ScanPayResponseBean;
import com.futeng.happypays.utils.WechatPayBean;
import com.mylhyl.circledialog.CircleDialog;
import com.mylhyl.circledialog.params.ProgressParams;
import com.uuzuche.lib_zxing.activity.CodeUtils;

public class QRcodeActivity extends FragmentActivity {

    @InjectView(R.id.shap_back)
    ImageView shapBack;
    @InjectView(R.id.selectedtyi)
    ImageView selectedtyi;
    @InjectView(R.id.selecteddling)
    ImageView selecteddling;
    @InjectView(R.id.selectback)
    RelativeLayout selectback;


    @InjectView(R.id.alipay_img)
    ImageView alipayImg;
    @InjectView(R.id.wechat_img)
    ImageView wechatImg;
    @InjectView(R.id.child_pays_img)
    ImageView childPaysImg;
    @InjectView(R.id.editText2)
    EditText editText2;
    @InjectView(R.id.qrcode_ok)
    TextView qrcodeOk;
    @InjectView(R.id.qrcode_img)
    ImageView qrcodeImg;
    @InjectView(R.id.findqrcode)
    TextView findqrcode;

    String qrtype="T+1";
    String qrway="alipay";
    int mcc=47;

    private DialogFragment dialogFragment;
    public Bitmap mBitmap = null;
    private String PsUrl;
    boolean i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode);
        ButterKnife.inject(this);

        editText2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                String temp = s.toString();
                int posDot = temp.indexOf(".");
                if (posDot <= 0) return;
                if (temp.length() - posDot - 1 > 2) {
                    s.delete(posDot + 3, posDot + 4);
                } else {
                    return;
                }
            }
        });
        //存交易类型微信/支付宝(微信是1 支付宝是2)
        SPUtils.put(getApplicationContext(), "uptype", "2-1");
    }

    @OnClick({R.id.shap_back, R.id.selectedtyi, R.id.selecteddling, R.id.selectback, R.id.alipay_img, R.id.wechat_img,R.id.qrcode_ok, R.id.findqrcode})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.shap_back:
                finish();
                break;
            case R.id.selectedtyi:
                break;
            case R.id.selecteddling:
                break;
            case R.id.selectback:
                Log.e("+++++++", i + "");
                if (i == false) {
                    selectback.setBackgroundResource(R.mipmap.notselectetyi);
                    selectedtyi.setVisibility(View.GONE);
                    selecteddling.setVisibility(View.VISIBLE);
                    i = true;
                    qrtype="D+0";

                    if (qrtype.equals("T+1")&&qrway.equals("alipay")){
                        childPaysImg.setBackgroundResource(R.mipmap.zhityi);
                        mcc=47;
                        //存交易类型微信/支付宝(微信是1 支付宝是2)
                        SPUtils.put(getApplicationContext(), "uptype", "2-1");
                    }else if (qrtype.equals("D+0")&&qrway.equals("alipay")){
                        childPaysImg.setBackgroundResource(R.mipmap.zhidling);
                        mcc=48;
                        //存交易类型微信/支付宝(微信是1 支付宝是2)
                        SPUtils.put(getApplicationContext(), "uptype", "2-0");
                    }else if (qrtype.equals("T+1")&&qrway.equals("wechat")){
                        childPaysImg.setBackgroundResource(R.mipmap.weityi);
                        mcc=45;
                        //存交易类型微信/支付宝(微信是1 支付宝是2)
                        SPUtils.put(getApplicationContext(), "uptype", "1-1");
                    }else if (qrtype.equals("D+0")&&qrway.equals("wechat")){
                        childPaysImg.setBackgroundResource(R.mipmap.weindling);
                        mcc=46;
                        //存交易类型微信/支付宝(微信是1 支付宝是2)
                        SPUtils.put(getApplicationContext(), "uptype", "1-0");
                    }
                    Log.e("++++",qrtype+"***"+qrway);
                } else {
                    selectback.setBackgroundResource(R.mipmap.notselecteddling);
                    selectedtyi.setVisibility(View.VISIBLE);
                    selecteddling.setVisibility(View.GONE);
                    i = false;
                    qrtype="T+1";
                    if (qrtype.equals("T+1")&&qrway.equals("alipay")){
                        childPaysImg.setBackgroundResource(R.mipmap.zhityi);
                        mcc=47;
                        //存交易类型微信/支付宝(微信是1 支付宝是2)
                        SPUtils.put(getApplicationContext(), "uptype", "2-1");
                    }else if (qrtype.equals("D+0")&&qrway.equals("alipay")){
                        childPaysImg.setBackgroundResource(R.mipmap.zhidling);
                        mcc=48;
                        //存交易类型微信/支付宝(微信是1 支付宝是2)
                        SPUtils.put(getApplicationContext(), "uptype", "2-0");
                    }else if (qrtype.equals("T+1")&&qrway.equals("wechat")){
                        childPaysImg.setBackgroundResource(R.mipmap.weityi);
                        mcc=45;
                        //存交易类型微信/支付宝(微信是1 支付宝是2)
                        SPUtils.put(getApplicationContext(), "uptype", "1-1");
                    }else if (qrtype.equals("D+0")&&qrway.equals("wechat")){
                        childPaysImg.setBackgroundResource(R.mipmap.weindling);
                        mcc=46;
                        //存交易类型微信/支付宝(微信是1 支付宝是2)
                        SPUtils.put(getApplicationContext(), "uptype", "1-0");
                    }

                    Log.e("++++",qrtype+"***"+qrtype);

                }
                break;
            case R.id.alipay_img:
                alipayImg.setImageDrawable(getResources().getDrawable(R.mipmap.selectedalipay));
                wechatImg.setImageDrawable(getResources().getDrawable(R.mipmap.weixin));
                qrway="alipay";
                if (qrtype.equals("T+1")&&qrway.equals("alipay")){
                    childPaysImg.setBackgroundResource(R.mipmap.zhityi);
                    mcc=47;
                    //存交易类型微信/支付宝(微信是1 支付宝是2)
                    SPUtils.put(getApplicationContext(), "uptype", "2-1");
                }else if (qrtype.equals("D+0")&&qrway.equals("alipay")){
                    childPaysImg.setBackgroundResource(R.mipmap.zhidling);
                    mcc=48;
                    //存交易类型微信/支付宝(微信是1 支付宝是2)
                    SPUtils.put(getApplicationContext(), "uptype", "2-0");
                }else if (qrtype.equals("T+1")&&qrway.equals("wechat")){
                    childPaysImg.setBackgroundResource(R.mipmap.weityi);
                    mcc=45;
                    //存交易类型微信/支付宝(微信是1 支付宝是2)
                    SPUtils.put(getApplicationContext(), "uptype", "1-1");
                }else if (qrtype.equals("D+0")&&qrway.equals("wechat")){
                    childPaysImg.setBackgroundResource(R.mipmap.weindling);
                    mcc=46;
                    //存交易类型微信/支付宝(微信是1 支付宝是2)
                    SPUtils.put(getApplicationContext(), "uptype", "1-0");
                }
                Log.e("--------",qrtype+"***"+qrtype);
                break;
            case R.id.wechat_img:
                wechatImg.setImageDrawable(getResources().getDrawable(R.mipmap.selectedweixin));
                alipayImg.setImageDrawable(getResources().getDrawable(R.mipmap.alipay));
                qrway="wechat";
                if (qrtype.equals("T+1")&&qrway.equals("alipay")){
                    childPaysImg.setBackgroundResource(R.mipmap.zhityi);
                    mcc=47;
                    //存交易类型微信/支付宝(微信是1 支付宝是2)
                    SPUtils.put(getApplicationContext(), "uptype", "2-1");
                }else if (qrtype.equals("D+0")&&qrway.equals("alipay")){
                    childPaysImg.setBackgroundResource(R.mipmap.zhidling);
                    mcc=48;
                    //存交易类型微信/支付宝(微信是1 支付宝是2)
                    SPUtils.put(getApplicationContext(), "uptype", "2-0");
                }else if (qrtype.equals("T+1")&&qrway.equals("wechat")){
                    childPaysImg.setBackgroundResource(R.mipmap.weityi);
                    mcc=45;
                    //存交易类型微信/支付宝(微信是1 支付宝是2)
                    SPUtils.put(getApplicationContext(), "uptype", "1-1");
                }else if (qrtype.equals("D+0")&&qrway.equals("wechat")){
                    childPaysImg.setBackgroundResource(R.mipmap.weindling);
                    mcc=46;
                    //存交易类型微信/支付宝(微信是1 支付宝是2)
                    SPUtils.put(getApplicationContext(), "uptype", "1-0");
                }
                Log.e("--------",qrtype+"***"+qrtype);
                break;
            case R.id.qrcode_ok:
                qrcodeok();
                break;
            case R.id.findqrcode:
                dialogFragment = new CircleDialog.Builder(QRcodeActivity.this)
                        .setProgressText("正在查询，请稍等。。。")
//                            .setCanceledOnTouchOutside(false)
                        .setProgressStyle(ProgressParams.STYLE_SPINNER)
                        .show();
                String tran37=SPUtils.getString(getApplication(),"tran37");
                geturl(mcc,tran37);
                break;
        }
    }

    private void qrcodeok(){
        //隐藏软键盘
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
        String qrmoney=editText2.getText().toString();
        if (TextUtils.isEmpty(qrmoney)) {
            MyApp.getInstance().showToast("您输入的金额为空!");
            return;
        } else {

            //输入金额乘以100倍
            String textContent1 = editText2.getText().toString();
            //存金额传给电子小票
            SPUtils.put(getApplicationContext(), "upmoney", textContent1);
            //存时间传给电子小票
            long end = System.currentTimeMillis();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String hms1 = formatter.format(end);
            SPUtils.put(getApplicationContext(), "uptime", hms1);

            int textContent = (int) ((Double.parseDouble(textContent1)) * 100);
            Log.e("double", textContent + "");
            //获取登入商户号赋值
            String mercNo = SPUtils.getString(this, "AmNumber");
            //获取登入商户名称赋值
            String body = SPUtils.getString(this, "amName");

            wechatpay(textContent, mercNo, mcc);
            dialogFragment = new CircleDialog.Builder(QRcodeActivity.this)
                    .setProgressText("正在交易，请稍等。。。")
//                            .setCanceledOnTouchOutside(false)
                    .setProgressStyle(ProgressParams.STYLE_SPINNER)
                    .show();
        }
        }

    private void wechatpay(int tranMoney, String amNumber, int mcc) {
        Map<String, Object> map = new HashMap<>();
        map.put("tranMoney", tranMoney);
        map.put("amNumber", amNumber);
        map.put("mcc", mcc);
        XUtil.Post(Config.QrPayServlet_URL, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                //弹框消失
                dialogFragment.dismiss();

                ScanPayResponseBean scanPayResponseBean = JSON.parseObject(result, ScanPayResponseBean.class);

                SPUtils.put(getApplicationContext(), "tran37", scanPayResponseBean.getTran37());
                SPUtils.put(getApplicationContext(), "ImageUrl", scanPayResponseBean.getImageUrl());
                MyApp.getInstance().showToast(scanPayResponseBean.getMessage());
                //查询按钮显示
                findqrcode.setVisibility(View.VISIBLE);

                //加载二维码图片
                editText2.setText("");
                mBitmap = CodeUtils.createImage(scanPayResponseBean.getImageUrl(), 500, 500, null);
                qrcodeImg.setImageBitmap(mBitmap);
            }
        });
    }

    private void findorderno(String Tran37) {
        String url = SPUtils.getString(this, "findurl");
        String tran37name = SPUtils.getString(this, "tran37name");
        Map<String, Object> map = new HashMap<>();
        map.put(tran37name, Tran37);
        XUtil.Post(url, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                dialogFragment.dismiss();
                WechatPayBean wechatPayBean = JSON.parseObject(result, WechatPayBean.class);
                if ("0000".equals(wechatPayBean.getResultCode())) {
                    startActivity(new Intent(QRcodeActivity.this, UpWaterTicketActivity.class));
                }
                MyApp.getInstance().showToast(wechatPayBean.getMessage());
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
                dialogFragment.dismiss();
            }
        });
    }

    //获取URL地址
    private void geturl(int mcc,String tran37) {
        Map<String, Object> map = new HashMap<>();
        map.put("mcc", mcc);
        map.put("mode", 1);
        map.put("tran37",tran37);
        XUtil.Post( Config.PayResultUrlServlet_URL, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                Geturl geturl = JSON.parseObject(result, Geturl.class);
                if ("00".equals(geturl.getCode())) {
                    try {
                        PsUrl = URLDecoder.decode(geturl.getMap().getPsUrl(), "UTF-8");
                    } catch (Exception e) {
                    }
                    SPUtils.put(getApplication(),"tran37name",geturl.getMap().getTran37());
                    SPUtils.put(getApplicationContext(), "findurl", PsUrl);
                    Log.e("findurl转码前++++++", geturl.getMap().getPsUrl());
                    Log.e("findurl转码后++++++", PsUrl);
                    String Tran37 = SPUtils.getString(getApplicationContext(), "tran37");
                    Log.e("++++++tran37", Tran37);
                    findorderno(Tran37);
                }else{
                    dialogFragment.dismiss();
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
