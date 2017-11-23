package com.futeng.happypays.activity;

import android.content.Intent;
import android.os.Build;
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
import com.futeng.happypays.tools.XUtil;
import com.futeng.happypays.utils.ControllerBean;
import com.futeng.happypays.utils.User;
import com.mylhyl.circledialog.CircleDialog;
import com.mylhyl.circledialog.params.ProgressParams;

/**
 * Created by Administrator on 2017/4/5.
 */
public class LoginActivity extends FragmentActivity {

    @InjectView(R.id.login_editTextone)
    EditText loginEditTextone;
    @InjectView(R.id.login_editTexttwo)
    EditText loginEditTexttwo;
    @InjectView(R.id.rember)
    CheckBox rember;
    @InjectView(R.id.forget_pw)
    TextView forget_pw;
    @InjectView(R.id.login_btn_login)
    Button loginBtnLogin;
    @InjectView(R.id.login_newpeople)
    Button loginNewpeople;
    @InjectView(R.id.clean_tel)
    ImageView cleanTel;
    @InjectView(R.id.clean_pwd)
    ImageView cleanPwd;

    private DialogFragment dialogFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);
        ButterKnife.inject(this);

        loginEditTextone.setText(SPUtils.getString(getApplication(), "username"));
        loginEditTexttwo.setText(SPUtils.getString(getApplication(), "password"));

        if ("1".equals(SPUtils.getString(getApplication(), "rember"))) {
            rember.setChecked(true);
            SPUtils.put(getApplication(), "username", loginEditTextone.getText().toString());
            SPUtils.put(getApplication(), "password", loginEditTexttwo.getText().toString());
        }

    }

    @OnClick({R.id.login_editTextone, R.id.login_editTexttwo, R.id.rember, R.id.forget_pw, R.id.login_btn_login, R.id.login_newpeople,R.id.clean_tel, R.id.clean_pwd})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_editTextone:
                break;
            case R.id.login_editTexttwo:
                break;
            case R.id.rember:
                if (rember.isChecked()) {
                    SPUtils.put(getApplication(), "username", loginEditTextone.getText().toString());
                    SPUtils.put(getApplication(), "password", loginEditTexttwo.getText().toString());
                    SPUtils.put(getApplication(), "rember", "1");
                }
                break;
            case R.id.forget_pw:
                //忘记密码
                startActivity(new Intent(LoginActivity.this, ForgetPwActivity.class));
                break;
            case R.id.login_btn_login:
                String username = loginEditTextone.getText().toString();
                String password = loginEditTexttwo.getText().toString();

                if (TextUtils.isEmpty(username)) {
                    Toast.makeText(LoginActivity.this, "账号不能为空", Toast.LENGTH_LONG).show();
                    return;
                } else if (TextUtils.isEmpty(password)) {
                    Toast.makeText(LoginActivity.this, "密码不能为空", Toast.LENGTH_LONG).show();
                    return;
                } else {
                    dialogFragment = new CircleDialog.Builder(LoginActivity.this)
                            .setProgressText("正在登入，请稍等。。。")
                            .setCanceledOnTouchOutside(false)
                            .setProgressStyle(ProgressParams.STYLE_SPINNER)
                            .show();
                    login(username, password);
                }
                break;
            case R.id.login_newpeople:
                //注册新用户
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));

                //添加界面切换效果，注意只有Android的2.0(SdkVersion版本号为5)以后的版本才支持
                int version = Integer.valueOf(Build.VERSION.SDK);
                if (version >= 5) {
                    //overridePendingTransition(R.animator.left_in,R.animator.left_out);  //此为自定义的动画效果，下面两个为系统的动画效果
                    overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                }
                break;
            case R.id.clean_tel:
                loginEditTextone.setText("");
                break;
            case R.id.clean_pwd:
                loginEditTexttwo.setText("");
                break;
        }
    }

    private void login(String phone, String password) {
        Map<String, Object> map = new HashMap<>();
        map.put("phone", phone);
        map.put("pwd", password);
        XUtil.Post(Config.Login__URL, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                User userStr = JSON.parseObject(result, User.class);
                dialogFragment.dismiss();

                if ("00".equals(userStr.getCode())) {
                    switchservlet("1");
                    //保存账号供调用
                    SPUtils.put(getApplicationContext(), "phone", "");
                    SPUtils.put(getApplicationContext(), "AmNumber", "");
                    SPUtils.put(getApplicationContext(), "pState", "");
                    SPUtils.put(getApplicationContext(), "amName", "");
                    SPUtils.put(getApplicationContext(), "isOK", true);
                    SPUtils.put(getApplicationContext(), "amIdNumber", "");
                    SPUtils.put(getApplicationContext(), "amAddress", "");
                    SPUtils.put(getApplicationContext(), "amPerson", "");
                    SPUtils.put(getApplicationContext(), "gradeName", "");
                    //保存账号供调用
                    SPUtils.put(getApplicationContext(), "phone", userStr.getMap().getPPhone());
                    SPUtils.put(getApplicationContext(), "AmNumber", userStr.getMap().getAmNumber());
                    SPUtils.put(getApplicationContext(), "pState", userStr.getMap().getPState());
                    SPUtils.put(getApplicationContext(), "amName", userStr.getMap().getAmName());
                    SPUtils.put(getApplicationContext(), "isOK", true);
                    SPUtils.put(getApplicationContext(), "amIdNumber", userStr.getMap().getAmIdNumber());
                    SPUtils.put(getApplicationContext(), "amAddress", userStr.getMap().getAmAddress());
                    SPUtils.put(getApplicationContext(), "amPerson", userStr.getMap().getAmPerson());
                    SPUtils.put(getApplicationContext(), "gradeName", userStr.getMap().getGradeName());

                    SPUtils.put(getApplication(), "username", loginEditTextone.getText().toString());
                    SPUtils.put(getApplication(), "password", loginEditTexttwo.getText().toString());

                    Log.e("--------", SPUtils.getString(getApplication(), "AmNumber") + "");
                } else if ("99".equals(userStr.getCode())) {
                    MyApp.getInstance().showToast(userStr.getFailMessage());
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
                dialogFragment.dismiss();
            }
        });
    }

    private void switchservlet(String mode) {
        Map<String, Object> map = new HashMap<>();
        map.put("mode", mode);
        XUtil.Post(Config.SwitchServlet_URL, map, new MyCallBack<String>() {

            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                ControllerBean controllerBean = JSON.parseObject(result, ControllerBean.class);

                if ("00".equals(controllerBean.getCode())) {
                    SPUtils.put(getApplication(), "101", controllerBean.getList().get(0).getSwitchName());
                    SPUtils.put(getApplication(), "qrCode", controllerBean.getList().get(0).getSwitchValue());

                    SPUtils.put(getApplication(), "102", controllerBean.getList().get(1).getSwitchName());
                    SPUtils.put(getApplication(), "mposReceivables", controllerBean.getList().get(1).getSwitchValue());

                    SPUtils.put(getApplication(), "103", controllerBean.getList().get(2).getSwitchName());
                    SPUtils.put(getApplication(), "noCardShortcut", controllerBean.getList().get(2).getSwitchValue());

                    SPUtils.put(getApplication(), "104", controllerBean.getList().get(3).getSwitchName());
                    SPUtils.put(getApplication(), "qrCodeWater", controllerBean.getList().get(3).getSwitchValue());

                    SPUtils.put(getApplication(), "105", controllerBean.getList().get(4).getSwitchName());
                    SPUtils.put(getApplication(), "mposReceivablesWater", controllerBean.getList().get(4).getSwitchValue());

                    SPUtils.put(getApplication(), "106", controllerBean.getList().get(5).getSwitchName());
                    SPUtils.put(getApplication(), "noCardShortcutWater", controllerBean.getList().get(5).getSwitchValue());

                    SPUtils.put(getApplication(), "107", controllerBean.getList().get(6).getSwitchName());
                    SPUtils.put(getApplication(), "creditCardApplication", controllerBean.getList().get(6).getSwitchValue());

                    SPUtils.put(getApplication(), "108", controllerBean.getList().get(7).getSwitchName());
                    SPUtils.put(getApplication(), "creditCardPayment", controllerBean.getList().get(7).getSwitchValue());

                    SPUtils.put(getApplication(), "109", controllerBean.getList().get(8).getSwitchName());
                    SPUtils.put(getApplication(), "quickAndEasyCredit", controllerBean.getList().get(8).getSwitchValue());

                    SPUtils.put(getApplication(), "110", controllerBean.getList().get(9).getSwitchName());
                    SPUtils.put(getApplication(), "phoneReplenishing", controllerBean.getList().get(9).getSwitchValue());

                    SPUtils.put(getApplication(), "111", controllerBean.getList().get(10).getSwitchName());
                    SPUtils.put(getApplication(), "wateFeePaid", controllerBean.getList().get(10).getSwitchValue());

                    SPUtils.put(getApplication(), "112", controllerBean.getList().get(11).getSwitchName());
                    SPUtils.put(getApplication(), "payElectricityBill", controllerBean.getList().get(11).getSwitchValue());

                    SPUtils.put(getApplication(), "113", controllerBean.getList().get(12).getSwitchName());
                    SPUtils.put(getApplication(), "auth", controllerBean.getList().get(12).getSwitchValue());

                    SPUtils.put(getApplication(), "114", controllerBean.getList().get(13).getSwitchName());
                    SPUtils.put(getApplication(), "myTeam", controllerBean.getList().get(13).getSwitchValue());

                    SPUtils.put(getApplication(), "115", controllerBean.getList().get(14).getSwitchName());
                    SPUtils.put(getApplication(), "sharingCeremony", controllerBean.getList().get(14).getSwitchValue());

                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    finish();
                }
            }
        });

    }


}
