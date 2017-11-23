package com.futeng.happypays.activity;

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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
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
import com.futeng.happypays.tools.TimerButton;
import com.futeng.happypays.tools.XUtil;
import com.futeng.happypays.utils.RegisterBean;
import com.futeng.happypays.utils.YanzhengcodeBean;
import com.mylhyl.circledialog.CircleDialog;
import com.mylhyl.circledialog.params.ProgressParams;

/**
 * Created by Administrator on 2017/2/22.
 */
public class RegisterActivity extends FragmentActivity {
    @InjectView(R.id.Np_back)
    ImageView NpBack;
    @InjectView(R.id.Ed_tel)
    EditText EdTel;
    @InjectView(R.id.Ed_load_pw)
    EditText EdLoadPw;
    @InjectView(R.id.Ed_load_pws)
    EditText EdLoadPws;
    @InjectView(R.id.Cbox)
    CheckBox Cbox;
    @InjectView(R.id.xieyi)
    TextView xieyi;
    @InjectView(R.id.Np_btn_ok)
    Button NpBtnOk;
    @InjectView(R.id.Ed_yzm)
    EditText EdYzm;
    @InjectView(R.id.Ed_tuijian_tel)
    EditText EdTuijianTel;

    private TimerButton mFindCodeBtn;
    private DialogFragment dialogFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_reg);
        ButterKnife.inject(this);

        mFindCodeBtn = (TimerButton) findViewById(R.id.text_yzm);

        NpBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        NpBtnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (TextUtils.isEmpty(EdTel.getText())) {
                    Toast.makeText(RegisterActivity.this, "手机号码不能为空", Toast.LENGTH_LONG).show();
                    return;
                } else if (TextUtils.isEmpty(EdYzm.getText())) {
                    Toast.makeText(RegisterActivity.this, "验证码不能为空", Toast.LENGTH_LONG).show();
                    return;
                } else if (TextUtils.isEmpty(EdLoadPw.getText())) {
                    Toast.makeText(RegisterActivity.this, "登入不能为空", Toast.LENGTH_LONG).show();
                    return;
                } else if (TextUtils.isEmpty(EdLoadPws.getText())) {
                    Toast.makeText(RegisterActivity.this, "登入不能为空", Toast.LENGTH_LONG).show();
                    return;
                } else if (!Cbox.isChecked()) {
                    Toast.makeText(RegisterActivity.this, "请同意服务协议", Toast.LENGTH_LONG).show();
                    return;
                } else {
                    //提交
                    String phone = EdTel.getText().toString();
                    String pwd = EdLoadPw.getText().toString();
                    String repwd = EdLoadPws.getText().toString();
                    String code = EdYzm.getText().toString();
                    String recomPhone = EdTuijianTel.getText().toString();
                    String operator = 1 + "";

                    dialogFragment = new CircleDialog.Builder(RegisterActivity.this)
                            .setProgressText("正在注册，请稍等。。。")
                            .setCanceledOnTouchOutside(false)
                            .setProgressStyle(ProgressParams.STYLE_SPINNER)
                            .show();

                    registerok(phone, pwd, repwd, code, operator,recomPhone);
                }
            }
        });
    }

    @OnClick({R.id.text_yzm, R.id.xieyi})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.text_yzm:

                if (TextUtils.isEmpty(EdTel.getText())) {
                    Toast.makeText(this, "请先输入手机号", Toast.LENGTH_SHORT).show();
                } else {

                    mFindCodeBtn.setCallback(new TimerButton.Callback() {
                        @Override
                        public String getTickerText() {
                            return "重发验证码(%ds)";
                        }

                        @Override
                        public int getMaxTime() {
                            return 60;
                        }
                    });
                    yzm(EdTel.getText().toString(), 1 + "");
                    mFindCodeBtn.start();
                }

                break;
            case R.id.xieyi:
                startActivity(new Intent(this, AgreementActivity.class));
                break;
        }
    }

    private void yzm(String phone, String operator) {
        Map<String, Object> map = new HashMap<>();
        map.put("phone", phone);
        map.put("operator", operator);
        XUtil.Post(Config.Code_URL, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                YanzhengcodeBean Yanzheng = JSON.parseObject(result, YanzhengcodeBean.class);


                if ("00".equals(Yanzheng.getCode())) {
                    MyApp.getInstance().showToast("验证码已下发到手机，请注意查收");
                    Log.e("验证码",Yanzheng.getSuccessMessage());
                } else if ("99".equals(Yanzheng.getCode())) {
                    MyApp.getInstance().showToast(Yanzheng.getFailMessage());
                }

                //保存验证码供调用，successMessage为六位手机验证码
                SPUtils.put(getApplicationContext(), "successMessage", Yanzheng.getSuccessMessage());

            }
        });
    }

    private void registerok(String phone, String pwd, String repwd, String code, String operator,String recomPhone) {

        Map<String, Object> map = new HashMap<>();
        map.put("phone", phone);
        map.put("pwd", pwd);
        map.put("repwd", repwd);
        map.put("code", code);
        map.put("operator", operator);
        map.put("recomPhone", recomPhone);


        XUtil.Post(Config.Regist_URL, map, new MyCallBack<String>() {

            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                dialogFragment.dismiss();
                RegisterBean regbean = JSON.parseObject(result, RegisterBean.class);

                if("00".equals(regbean.getCode())){
                    startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                    MyApp.getInstance().showToast(regbean.getSuccessMessage());
                }else if ("99".equals(regbean.getCode())){
                    MyApp.getInstance().showToast(regbean.getFailMessage());
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
                dialogFragment.dismiss();
                MyApp.getInstance().showToast("通讯异常！请重新请求");
            }
        });
    }
}
