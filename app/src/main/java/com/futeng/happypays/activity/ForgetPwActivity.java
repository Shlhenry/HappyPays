package com.futeng.happypays.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
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
import com.futeng.happypays.tools.TimerButton;
import com.futeng.happypays.tools.XUtil;
import com.futeng.happypays.utils.Fogpw;
import com.futeng.happypays.utils.YanzhengcodeBean;

/**
 * Created by Administrator on 2017/4/5.
 */
public class ForgetPwActivity extends Activity {


    @InjectView(R.id.fog_back)
    ImageView fogBack;
    @InjectView(R.id.fog_tel)
    EditText fogTel;
    @InjectView(R.id.fog_yzm)
    EditText fogYzm;
    @InjectView(R.id.text_yzm)
    TimerButton textYzm;
    @InjectView(R.id.fog_load_pw)
    EditText fogLoadPw;
    @InjectView(R.id.fog_load_pws)
    EditText fogLoadPws;
    @InjectView(R.id.fog_btn_ok)
    Button fogBtnOk;

    private TimerButton mFindCodeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_forgetpw);
        ButterKnife.inject(this);
        mFindCodeBtn= (TimerButton) findViewById(R.id.text_yzm);

    }


    @OnClick({R.id.fog_back, R.id.fog_btn_ok,R.id.text_yzm})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fog_back:
                finish();
                break;
            case R.id.fog_btn_ok:

                String phone = fogTel.getText().toString();
                String code = fogYzm.getText().toString();
                String pwd = fogLoadPw.getText().toString();
                String repwd = fogLoadPws.getText().toString();


                fogpw(phone,pwd,repwd,code,2+"");

                break;
            case R.id.text_yzm:
                if (TextUtils.isEmpty(fogTel.getText())){
                    Toast.makeText(this,"请先输入手机号",Toast.LENGTH_SHORT).show();
                }else{

                    mFindCodeBtn.setCallback(new TimerButton.Callback()
                    {
                        @Override
                        public String getTickerText()
                        {
                            return "重发验证码(%ds)";
                        }
                        @Override
                        public int getMaxTime()
                        {
                            return 60;
                        }
                    });
                    yzm(fogTel.getText().toString(), 2+"");
                    mFindCodeBtn.start();
                }
                break;
        }
    }

    private void fogpw(String phone, String pwd, String repwd, String code, String operator) {

        Map<String, Object> map = new HashMap<>();
        map.put("phone", phone);
        map.put("pwd", pwd);
        map.put("repwd", repwd);
        map.put("code", code);
        map.put("operator", operator);


        XUtil.Post(Config.Regist_URL, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);

                Fogpw fogpw=JSON.parseObject(result,Fogpw.class);
                if ("00".equals(fogpw.getCode())){
                    MyApp.getInstance().showToast(fogpw.getSuccessMessage());
                    startActivity(new Intent(ForgetPwActivity.this,LoginActivity.class));
                }else if ("99".equals(fogpw.getCode())){
                    MyApp.getInstance().showToast(fogpw.getFailMessage());
                }

            }
        });

    }

    private void yzm(String phone, String operator) {
        Map<String, Object> map = new HashMap<>();
        map.put("phone",phone);
        map.put("operator",operator);
        XUtil.Post(Config.Code_URL, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                YanzhengcodeBean Yanzheng = JSON.parseObject(result, YanzhengcodeBean.class);


                if ("00".equals(Yanzheng.getCode())){
                    //EdYzm.setText(Yanzheng.getSuccessMessage());
                    MyApp.getInstance().showToast("验证码已下发到手机，请注意查收");
                }else if ("99".equals(Yanzheng.getCode())){
                    MyApp.getInstance().showToast(Yanzheng.getFailMessage());
                }

                //保存验证码供调用，successMessage为六位手机验证码
                SPUtils.put(getApplicationContext(), "successMessage", Yanzheng.getSuccessMessage());

            }
        });
    }
}
