package com.futeng.happypays.activity.fgmentfour;

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

import com.alibaba.fastjson.JSON;
import com.futeng.happypays.R;
import com.futeng.happypays.activity.LoginActivity;
import com.futeng.happypays.activity.MainActivity;
import com.futeng.happypays.tools.Config;
import com.futeng.happypays.tools.MyApp;
import com.futeng.happypays.tools.MyCallBack;
import com.futeng.happypays.tools.SPUtils;
import com.futeng.happypays.tools.XUtil;
import com.futeng.happypays.utils.PwAgain;

import java.util.HashMap;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * 密码管理
 */
public class PasswordAgain extends Activity {
    @InjectView(R.id.pwagain_back)
    ImageView pwagainBack;
    @InjectView(R.id.pw_again)
    Button pwAgain;
    @InjectView(R.id.pwagain_oldpwd)
    EditText pwagainOldpwd;
    @InjectView(R.id.pwagain_newpwd)
    EditText pwagainNewpwd;
    @InjectView(R.id.pwagain_newpwds)
    EditText pwagainNewpwds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_pwagain);
        ButterKnife.inject(this);
    }

    @OnClick({R.id.pwagain_back, R.id.pw_again})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.pwagain_back:
                finish();
                break;
            case R.id.pw_again:

                String pwd = pwagainOldpwd.getText().toString();
                String newPwd = pwagainNewpwd.getText().toString();
                String reNewPwd = pwagainNewpwds.getText().toString();

                if (TextUtils.isEmpty(pwd)) {
                    Toast.makeText(PasswordAgain.this, "旧密码不能为空", Toast.LENGTH_LONG).show();
                    return;
                } else if (TextUtils.isEmpty(newPwd)) {
                    Toast.makeText(PasswordAgain.this, "新不能为空", Toast.LENGTH_LONG).show();
                } else if (TextUtils.isEmpty(reNewPwd)) {
                    Toast.makeText(PasswordAgain.this, "新不能为空", Toast.LENGTH_LONG).show();
                } else {
                    String phone= SPUtils.getString(getApplicationContext(),"phone");
                    PwAgain(phone, pwd, newPwd, reNewPwd);
                }
                break;
        }
    }

    private void PwAgain(String phone, String pwd, String newPwd, String reNewPwd) {
        final Map<String, Object> map = new HashMap<>();
        map.put("phone", phone);
        map.put("pwd", pwd);
        map.put("newPwd", newPwd);
        map.put("reNewPwd", reNewPwd);
        XUtil.Post(Config.UpPw_URL, map, new MyCallBack<String>() {

            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                PwAgain pwagain = JSON.parseObject(result, PwAgain.class);

                if ("00".equals(pwagain.getCode())){
                    MyApp.getInstance().showToast(pwagain.getSuccessMessage());
                    startActivity(new Intent(PasswordAgain.this, LoginActivity.class));
                    finish();
                }else{
                    MyApp.getInstance().showToast(pwagain.getFailMessage());
                }
            }
        });
    }
}
