package com.futeng.happypays.activity.renzheng.listview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.futeng.happypays.R;
import com.futeng.happypays.adapter.ListviewProvinceAdapter;
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

public class ProvinceListActivity extends Activity {


    @InjectView(R.id.provincelist_lv_list)
    ListView provincelist_lv_list;

    private CityBean cityBean = null;
    private ListviewProvinceAdapter ListviewProvinceadapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_province_list);
        ButterKnife.inject(this);
        getCityBean();

        provincelist_lv_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //Intent intent=new Intent(ProvinceListActivity.this,CityActivity.class);
               /* Intent intent=new Intent(ProvinceListActivity.this,Renzheng_base.class);
                intent.putExtra(Constant.IN_Cityone,cityBean.getList().get(position).getCityId());
                startActivity(intent);*/

                Intent intent= new Intent();
                intent.putExtra(Constant.IN_City_ID,cityBean.getList().get(position).getCityId());
                intent.putExtra(Constant.IN_City_Name,cityBean.getList().get(position).getCityName());
                ProvinceListActivity.this.setResult(Constant.ProvinceRESULT_OK,intent);
                ProvinceListActivity.this.finish();
            }
        });
    }

    private void initView() {
        ListviewProvinceadapter = new ListviewProvinceAdapter(this, cityBean.getList());
        provincelist_lv_list.setAdapter(ListviewProvinceadapter);
    }

    private void getCityBean() {
        Map<String, Object> map = new HashMap<>();
        map.put("cityId", 0);
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
