package com.futeng.happypays.activity.renzheng.listview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;
import com.alibaba.fastjson.JSON;
import com.futeng.happypays.R;
import com.futeng.happypays.adapter.ListviewAresAdapter;
import com.futeng.happypays.constant.Constant;
import com.futeng.happypays.tools.Config;
import com.futeng.happypays.tools.MyApp;
import com.futeng.happypays.tools.MyCallBack;
import com.futeng.happypays.tools.XUtil;
import com.futeng.happypays.utils.CityBean;
import java.util.HashMap;
import java.util.Map;
import butterknife.ButterKnife;
import butterknife.InjectView;

public class AreaActivity extends Activity {

    @InjectView(R.id.arealist_lv_list)
    ListView arealistLvList;

    private int cityIdtwo = 0;
    private CityBean cityBean = null;
    private ListviewAresAdapter listviewAresAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_area);
        ButterKnife.inject(this);
        setData();



        arealistLvList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent= new Intent();
                intent.putExtra(Constant.IN_City_ID,cityBean.getList().get(position).getCityId());
                intent.putExtra(Constant.IN_City_Name,cityBean.getList().get(position).getCityName());
                AreaActivity.this.setResult(Constant.AreaRESULT_OK,intent);
                AreaActivity.this.finish();
            }
        });

    }

    private void setData() {
        cityIdtwo = getIntent().getIntExtra(Constant.IN_ID, 0);
        getAreaBean();

    }
    private void initView() {
        listviewAresAdapter = new ListviewAresAdapter(this, cityBean.getList());
        arealistLvList.setAdapter(listviewAresAdapter);
    }

    private void getAreaBean() {
        Map<String, Object> map = new HashMap<>();
        map.put("cityId", cityIdtwo);
        XUtil.Post(Config.City_URL, map, new MyCallBack<String>() {

            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                cityBean = JSON.parseObject(result, CityBean.class);
                if (cityBean.isIsOK()) {
                    initView();
                } else {
                    MyApp.getInstance().showToast(cityBean.getMessage());
                }
            }
        });

    }

}
