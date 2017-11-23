package com.futeng.happypays.activity.erweimashoukuan;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.futeng.happypays.R;
import com.futeng.happypays.adapter.LogisticAdapter;
import com.futeng.happypays.tools.MyApp;
import com.futeng.happypays.tools.MyCallBack;
import com.futeng.happypays.tools.SPUtils;
import com.futeng.happypays.tools.XUtil;
import com.futeng.happypays.utils.LogicticBean;
import com.mylhyl.circledialog.CircleDialog;
import com.mylhyl.circledialog.params.ProgressParams;

import java.util.HashMap;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class LogisticActivity extends FragmentActivity {

    @InjectView(R.id.logistic_back)
    ImageView logisticBack;
    @InjectView(R.id.textView8)
    TextView textView8;
    @InjectView(R.id.logistic_lv)
    ListView logisticLv;
    @InjectView(R.id.company)
    TextView company;
    @InjectView(R.id.logistic_state)
    TextView logisticState;
    @InjectView(R.id.express_num)
    TextView expressNum;
    @InjectView(R.id.showimg)
    ImageView showimg;

    private LogicticBean logicticBean;
    private LogisticAdapter logisticAdapter;

    private DialogFragment dialogFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logistic);
        ButterKnife.inject(this);
        dialogFragment = new CircleDialog.Builder(this)
                .setProgressText("正在查询，请稍等。。。")
                .setCanceledOnTouchOutside(false)
                .setProgressStyle(ProgressParams.STYLE_SPINNER)
                .show();
        logistic(SPUtils.getString(getApplication(), "expressOrderNo"));
        company.setText(SPUtils.getString(getApplication(), "expname"));
        logisticState.setText(SPUtils.getString(getApplication(), "logistic_state"));
        String expressOrderNo = SPUtils.getString(getApplication(), "expressOrderNo");
        if ("".equals(expressOrderNo)) {
            expressNum.setText("暂无");
        } else {
            expressNum.setText(expressOrderNo);
        }
    }

    //请求快递地址
    private void logistic(String number) {
        Map<String, Object> map = new HashMap<>();
        map.put("appkey", "4fc617294485fc52cc95cc67b1566bae");
        map.put("type", "auto");
        map.put("number", number);

        Log.e("--***---", map + "");

        XUtil.Post("https://way.jd.com/jisuapi/query", map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                dialogFragment.dismiss();
                logicticBean = JSON.parseObject(result, LogicticBean.class);
                if ("10000".equals(logicticBean.getCode()) && logicticBean.isCharge() == true) {
                    if (logicticBean.getResult().getResult().getList() != null) {
                        initView();
                        logisticLv.setVisibility(View.VISIBLE);
                    } else if (logicticBean.getResult().getResult().getList() == null) {
                        MyApp.getInstance().showToast(logicticBean.getResult().getMsg());
                        showimg.setVisibility(View.VISIBLE);
                    }
                } else if ("10000".equals(logicticBean.getCode()) && logicticBean.isCharge() == false) {
                    MyApp.getInstance().showToast(logicticBean.getResult().getMsg());
                    showimg.setVisibility(View.VISIBLE);
                    return;
                } else {
                    MyApp.getInstance().showToast(logicticBean.getMsg());
                    showimg.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
                dialogFragment.dismiss();
            }
        });
    }

    private void initView() {
        logisticAdapter = new LogisticAdapter(this, logicticBean.getResult().getResult().getList());
        logisticLv.setAdapter(logisticAdapter);
    }


    @OnClick(R.id.logistic_back)
    public void onViewClicked() {
        finish();
    }
}
