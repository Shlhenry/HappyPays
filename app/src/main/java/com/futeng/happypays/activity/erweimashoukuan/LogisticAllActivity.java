package com.futeng.happypays.activity.erweimashoukuan;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
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

public class LogisticAllActivity extends FragmentActivity {

    @InjectView(R.id.logisticall_back)
    ImageView logisticallBack;
    @InjectView(R.id.textView8)
    TextView textView8;
    @InjectView(R.id.logistic_name)
    TextView logisticName;
    @InjectView(R.id.logistic_num)
    TextView logisticNum;
    @InjectView(R.id.logisticall_lv)
    ListView logisticallLv;
    @InjectView(R.id.logistilall_img)
    ImageView logistilallImg;
    @InjectView(R.id.logisticall_img)
    ImageView logisticallImg;

    private LogicticBean logicticBean;
    private LogisticAdapter logisticAdapter;

    private DialogFragment dialogFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logistic_all);
        ButterKnife.inject(this);

        dialogFragment = new CircleDialog.Builder(this)
                .setProgressText("正在查询，请稍等。。。")
                .setCanceledOnTouchOutside(false)
                .setProgressStyle(ProgressParams.STYLE_SPINNER)
                .show();

        looklogistic(SPUtils.getString(getApplication(), "logisticallnumber"));

        Glide.with(LogisticAllActivity.this)
                .load(SPUtils.getString(getApplication(), "showlogisticimg"))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .dontAnimate().placeholder
                (R.mipmap.moren).into(logistilallImg);
        logisticName.setText(SPUtils.getString(getApplication(), "showlogisticname"));
    }

    @OnClick(R.id.logisticall_back)
    public void onViewClicked() {
        finish();
    }

    private void initView() {
        logisticAdapter = new LogisticAdapter(this, logicticBean.getResult().getResult().getList());
        logisticallLv.setAdapter(logisticAdapter);
    }

    //查询快递
    private void looklogistic(String number) {
        Map<String, Object> map = new HashMap<>();
        map.put("type", "auto");
        map.put("number", number);
        map.put("appkey", "4fc617294485fc52cc95cc67b1566bae");

        XUtil.Post("https://way.jd.com/jisuapi/query", map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                dialogFragment.dismiss();
                logicticBean = JSON.parseObject(result, LogicticBean.class);
                if ("10000".equals(logicticBean.getCode()) && logicticBean.isCharge() == true) {
                    if (logicticBean.getResult().getResult().getList() != null) {
                        initView();
                        logisticallLv.setVisibility(View.VISIBLE);
                        logisticNum.setText(SPUtils.getString(getApplication(), "logisticallnumber"));
                    } else {
                        logisticallImg.setVisibility(View.VISIBLE);
                        MyApp.getInstance().showToast("暂无物流信息!");
                        return;
                    }
                } else if ("10000".equals(logicticBean.getCode()) && logicticBean.isCharge() == false){
                    logisticallImg.setVisibility(View.VISIBLE);
                    MyApp.getInstance().showToast(logicticBean.getResult().getMsg());
                    return;
                }else{
                    logisticallImg.setVisibility(View.VISIBLE);
                    MyApp.getInstance().showToast(logicticBean.getMsg());
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
