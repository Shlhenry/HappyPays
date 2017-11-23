package com.futeng.happypays.activity.erweima;

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
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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
/***
 * 二维码收款---支付宝D0
 */
public class AlipayD0Activity extends FragmentActivity {

    public EditText editText = null;
    public Button button = null;
    public Button button1 = null;
    public TextView button2 = null;
    public ImageView imageView = null;

    public Bitmap mBitmap = null;
    @InjectView(R.id.Zfbone_back)
    ImageView ZfboneBack;
    @InjectView(R.id.edit_content)
    EditText editContent;

    private DialogFragment dialogFragment;
    private String PsUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_zfbone);
        ButterKnife.inject(this);
        initView();
        editContent.addTextChangedListener(new TextWatcher() {
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
    }

    /**
     * 初始化组件
     */
    private void initView() {
        editText = (EditText) findViewById(R.id.edit_content);
        button = (Button) findViewById(R.id.button_content);
        button1 = (Button) findViewById(R.id.button1_content);
        button2 = (TextView) findViewById(R.id.button2_content);
        imageView = (ImageView) findViewById(R.id.image_content);

        /**
         * 生成不带logo的二维码图片
         */
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //隐藏软键盘
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);

                String textContent2 = editText.getText().toString();
                if (TextUtils.isEmpty(textContent2)) {
                    Toast.makeText(AlipayD0Activity.this, "您的输入为空!", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    //输入金额乘以100倍
                    String textContent1 = editText.getText().toString();
                    //存金额传给电子小票
                    SPUtils.put(getApplicationContext(), "upmoney", textContent1);
                    //存时间传给电子小票
                    long end = System.currentTimeMillis();
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String hms1 = formatter.format(end);
                    SPUtils.put(getApplicationContext(), "uptime", hms1);
                    //存交易类型微信/支付宝(微信是1 支付宝是2)
                    SPUtils.put(getApplicationContext(), "uptype", "2");
                    int textContent = (int) ((Double.parseDouble(textContent1)) * 100);
                    Log.e("double", textContent + "");
                    //获取登入商户号赋值
                    Intent intent = new Intent();
                    intent.putExtra("AmNumber", SPUtils.getString(AlipayD0Activity.this, "AmNumber"));
                    String mercNo = SPUtils.getString(AlipayD0Activity.this, "AmNumber");
                    //获取登入商户名称赋值
                    Intent intent1 = new Intent();
                    intent.putExtra("amName", SPUtils.getString(AlipayD0Activity.this, "amName"));
                    String body = SPUtils.getString(AlipayD0Activity.this, "amName");

                    String mcc = 48 + "";
                    wechatpay(textContent, mercNo, mcc);

                    dialogFragment = new CircleDialog.Builder(AlipayD0Activity.this)
                            .setProgressText("正在交易，请稍等。。。")
//                            .setCanceledOnTouchOutside(false)
                            .setProgressStyle(ProgressParams.STYLE_SPINNER)
                            .show();
                }
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogFragment = new CircleDialog.Builder(AlipayD0Activity.this)
                        .setProgressText("正在查询，请稍等。。。")
//                            .setCanceledOnTouchOutside(false)
                        .setProgressStyle(ProgressParams.STYLE_SPINNER)
                        .show();
                String tran37=SPUtils.getString(getApplication(),"tran37");
                geturl(48 + "",tran37);
            }
        });
    }

    private void wechatpay(int tranMoney, String amNumber, String mcc) {

        Map<String, Object> map = new HashMap<>();
        map.put("tranMoney", tranMoney);
        map.put("amNumber", amNumber);
        map.put("mcc", mcc);

        Log.e("+++++zfbT+1+++++", map + "");

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
                button2.setVisibility(View.VISIBLE);

                //加载二维码图片
                editText.setText("");
                mBitmap = CodeUtils.createImage(scanPayResponseBean.getImageUrl(), 500, 500, null);
                imageView.setImageBitmap(mBitmap);

            }
        });
    }

    private void findorderno(String tran37) {
        String url = SPUtils.getString(AlipayD0Activity.this, "findurl");
        String tran37name = SPUtils.getString(AlipayD0Activity.this, "tran37name");
        Map<String, Object> map = new HashMap<>();
        map.put(tran37name, tran37);
        XUtil.Post(url, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                dialogFragment.dismiss();
                WechatPayBean wechatPayBean = JSON.parseObject(result, WechatPayBean.class);
                if ("0000".equals(wechatPayBean.getResultCode())) {
                    // 等于0000交易成功，跳转小票
                    startActivity(new Intent(AlipayD0Activity.this, UpWaterTicketActivity.class));
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

    @OnClick(R.id.Zfbone_back)
    public void onClick() {
        finish();
    }

    //获取URL地址
    private void geturl(String mcc,String tran37) {
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
                    String Tran37 = SPUtils.getString(AlipayD0Activity.this, "tran37");
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

