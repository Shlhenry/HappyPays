package com.futeng.happypays.activity.nocard;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

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
import com.futeng.happypays.tools.XUtil;
import com.futeng.happypays.utils.GettokenBean;
import com.mylhyl.circledialog.CircleDialog;

public class WukabeforeActivity extends FragmentActivity {

    @InjectView(R.id.wuka_before_back)
    ImageView wukaBeforeBack;
    @InjectView(R.id.wk_before_one)
    LinearLayout wkBeforeOne;
    @InjectView(R.id.wk_before_two)
    LinearLayout wkBeforeTwo;

    private String mcc = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_wukabefore);
        ButterKnife.inject(this);
    }

    @OnClick({R.id.wuka_before_back, R.id.wk_before_one, R.id.wk_before_two})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.wuka_before_back:
                    finish();
                    break;

            case R.id.wk_before_one:
                    mcc=83+"";//T1传83
                    SPUtils.put(getApplication(),"wukamcc",mcc);
                    startActivity(new Intent(this, NoCardT1Activity.class));
                    Log.e("无卡T1mcc",mcc);
                    //存类型，来加载对应的小票图片
                    SPUtils.put(getApplicationContext(), "uptype", "4-1");
                break;
            case R.id.wk_before_two:
                //D0判断是否需要开卡
                String nocardAccountNumber=SPUtils.getString(getApplication(),"nocardAccountNumber");
                gettoken(nocardAccountNumber);
                break;
        }
    }

    private void gettoken(String accountNumber){
        Map<String,Object> map=new HashMap<>();
        map.put("accountNumber",accountNumber);

        XUtil.Post(Config.getToken_NoCardURL,map,new MyCallBack<String>(){

            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                final GettokenBean gettokenBean= JSON.parseObject(result,GettokenBean.class);
                if ("0000".equals(gettokenBean.getResultCode())){
                    //已开通快捷业务直接跳转
                    mcc=82+"";//D0传82
                    SPUtils.put(getApplication(),"wukamcc",mcc);
                    Log.e("无卡mcc",mcc);
                    //存类型，来加载对应的小票图片
                    SPUtils.put(getApplicationContext(), "uptype", "4-0");
                    startActivity(new Intent(WukabeforeActivity.this,WukaPayActivity.class));
                }else if ("true".equals(gettokenBean.getResultCode())){
                    new CircleDialog.Builder(WukabeforeActivity.this)
                            .setCanceledOnTouchOutside(false)
                            .setCancelable(false)
                            .setTitle("此卡暂未开通")
                            .setText("是否开通银联快捷无卡业务")
                            .setNegative("取消", null)
                            .setPositive("确定", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    SPUtils.put(WukabeforeActivity.this,"HtmlContent",gettokenBean.getHtmlContent());
                                    //未开通快捷业务，跳转html
                                    startActivity(new Intent(WukabeforeActivity.this,WebviewNoCard.class));
                                }
                            })
                            .show();
                }else {
                    MyApp.getInstance().showToast(gettokenBean.getMessage());
                }
            }
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
                MyApp.getInstance().showToast("网络异常!");
            }
        });
    }
}
