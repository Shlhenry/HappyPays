package com.futeng.happypays.activity.nocard;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import com.alibaba.fastjson.JSON;
import com.futeng.happypays.R;
import com.futeng.happypays.activity.webview.WebviewNoCard;
import com.futeng.happypays.adapter.CardbangdingAdapter;
import com.futeng.happypays.tools.Config;
import com.futeng.happypays.tools.MyApp;
import com.futeng.happypays.tools.MyCallBack;
import com.futeng.happypays.tools.SPUtils;
import com.futeng.happypays.tools.XUtil;
import com.futeng.happypays.utils.CardBean;
import com.futeng.happypays.utils.GettokenBean;
import com.mylhyl.circledialog.CircleDialog;

/**
 * Created by Administrator on 2017/4/5.
 * 无卡消费的银行卡列表
 */
public class XinCardActivity extends FragmentActivity {

    @InjectView(R.id.chucard_back)
    ImageView chucardBack;
    @InjectView(R.id.chucard_btn)
    LinearLayout chucardBtn;
    @InjectView(R.id.xinyongka_lv_list)
    ListView xinyongkaLvList;
    @InjectView(R.id.cardhint)
    LinearLayout cardhint;

    private CardBean cardBean = null;
    private CardbangdingAdapter cardbangdingAdapter = null;

    private int mode = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_xincard);
        ButterKnife.inject(this);

        //获取商户号
        String amNumber = SPUtils.getString(XinCardActivity.this, "AmNumber");

        getcard(amNumber, mode);

        xinyongkaLvList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                SPUtils.put(getApplicationContext(),"nocardAccountName", cardBean.getList().get(position).getAccountName());
                SPUtils.put(getApplicationContext(),"nocardAccountNumber", cardBean.getList().get(position).getAccountNumber());
                SPUtils.put(getApplicationContext(),"amNumber",cardBean.getList().get(position).getAmNumber());

                startActivity(new Intent(XinCardActivity.this,WukabeforeActivity.class));
            }
        });

        /**
         * 要跳转的Activity接收
         * intent.getStringErxg("aa")
         */
    }

    private void initView() {
        cardbangdingAdapter = new CardbangdingAdapter(this, cardBean.getList());
        xinyongkaLvList.setAdapter(cardbangdingAdapter);
    }

    private void getcard(String amNumber, int mode) {
        Map<String, Object> map = new HashMap<>();
        map.put("amNumber", amNumber);
        map.put("mode", mode);
        Log.e("+++map+++", map + "");
        XUtil.Post(Config.AMBandCreditServlet_URL, map, new MyCallBack<String>() {

            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                cardBean = JSON.parseObject(result, CardBean.class);
                if ("99".equals(cardBean.getCode())) {
                    MyApp.getInstance().showToast(cardBean.getFailMessage());
                } else {
                    if (cardBean.getList() == null) {
                        cardhint.setVisibility(View.VISIBLE);
                        return;
                    } else {
                        initView();
                    }
                }
            }
        });
    }

    @OnClick({R.id.chucard_back, R.id.chucard_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.chucard_back:
                finish();
                break;
            case R.id.chucard_btn:
                break;
        }
    }

    private void gettoken(String accountNumber){
        Map<String,Object>map=new HashMap<>();
        map.put("accountNumber",accountNumber);

        XUtil.Post(Config.getToken_NoCardURL,map,new MyCallBack<String>(){

            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                final GettokenBean gettokenBean=JSON.parseObject(result,GettokenBean.class);
                if ("0000".equals(gettokenBean.getResultCode())){
                    //已开通快捷业务直接跳转
                    startActivity(new Intent(XinCardActivity.this,WukabeforeActivity.class));
                }else if ("true".equals(gettokenBean.getResultCode())){


                    new CircleDialog.Builder(XinCardActivity.this)
                            .setCanceledOnTouchOutside(false)
                            .setCancelable(false)
                            .setTitle("此卡暂未开通")
                            .setText("是否开通银联快捷无卡业务")
                            .setNegative("取消", null)
                            .setPositive("确定", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    SPUtils.put(XinCardActivity.this,"HtmlContent",gettokenBean.getHtmlContent());
                                    //未开通快捷业务，跳转html
                                    startActivity(new Intent(XinCardActivity.this,WebviewNoCard.class));
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
