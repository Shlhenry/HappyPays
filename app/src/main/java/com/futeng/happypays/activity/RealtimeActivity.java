package com.futeng.happypays.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.HashMap;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import com.alibaba.fastjson.JSON;
import com.futeng.happypays.R;
import com.futeng.happypays.adapter.RealtimeAdapter;
import com.futeng.happypays.tools.Config;
import com.futeng.happypays.tools.MyCallBack;
import com.futeng.happypays.tools.XUtil;
import com.futeng.happypays.utils.RealtimeBean;

public class RealtimeActivity extends Activity {

    @InjectView(R.id.realtime_back)
    ImageView realtimeBack;
    @InjectView(R.id.realtime_lv)
    ListView realtimeLv;
    private RealtimeBean realtimeBean = null;
    private RealtimeAdapter realtimeAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realtime);
        ButterKnife.inject(this);
        realtime();
    }

    private void initView() {
        realtimeAdapter = new RealtimeAdapter(this, realtimeBean.getList());
        realtimeLv.setAdapter(realtimeAdapter);
    }


    private void realtime() {
        Map<String, Object> map = new HashMap<>();
        map.put("mode", 1);
        XUtil.Post(Config.ExchangerateServlet_URL, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                realtimeBean = JSON.parseObject(result, RealtimeBean.class);
                if ("99".equals(realtimeBean.getCode())) {
                    return;
                }else if ("00".equals(realtimeBean.getCode())) {
                    if (realtimeBean.getList() == null) {
                        return;
                    } else {
                        initView();
                    }
                }
            }
        });
    }

    @OnClick(R.id.realtime_back)
    public void onViewClicked() {
        finish();
    }
}
