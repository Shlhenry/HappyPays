package com.futeng.happypays.activity.erweimashoukuan;

import android.content.Intent;
import android.location.Location;
import android.opengl.Visibility;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.futeng.happypays.R;
import com.futeng.happypays.activity.LoginActivity;
import com.futeng.happypays.activity.aaa.CommonAdapter;
import com.futeng.happypays.activity.aaa.ViewHolder;
import com.futeng.happypays.tools.Config;
import com.futeng.happypays.tools.MyApp;
import com.futeng.happypays.tools.MyCallBack;
import com.futeng.happypays.tools.SPUtils;
import com.futeng.happypays.tools.XUtil;
import com.futeng.happypays.utils.AddressBean;
import com.futeng.happypays.utils.SelectBankcardBean;
import com.mylhyl.circledialog.CircleDialog;
import com.mylhyl.circledialog.params.ProgressParams;

import java.util.HashMap;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class SelectAddressActivity extends FragmentActivity{

    @InjectView(R.id.selectaddress_back)
    ImageView selectaddressBack;
    @InjectView(R.id.new_address)
    LinearLayout newAddress;
    @InjectView(R.id.address_lv)
    ListView addressLv;

    private CommonAdapter<AddressBean.ListBean> adapter;
    private AddressBean addressBean;
    private boolean boo=true;
    private SparseBooleanArray mSelectArray;
    private DialogFragment dialogFragment;
    private String amnumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_address);
        ButterKnife.inject(this);
        amnumber=SPUtils.getString(getApplication(),"AmNumber");
        dialogFragment = new CircleDialog.Builder(SelectAddressActivity.this)
                .setProgressText("正在登入，请稍等。。。")
                .setCanceledOnTouchOutside(false)
                .setProgressStyle(ProgressParams.STYLE_SPINNER)
                .show();
        //请求地址列表
        getaddresslv(amnumber);

        addressLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                SPUtils.put(getApplication(),"riId",addressBean.getList().get(i).getRiId()+"");

                SPUtils.put(getApplication(),"storeName",addressBean.getList().get(i).getRiName());
                SPUtils.put(getApplication(),"textviewAddress","收货人:");
                SPUtils.put(getApplication(),"storeTel",addressBean.getList().get(i).getRiPhone());
                SPUtils.put(getApplication(),"getstoreDetaile","收货地址:" + addressBean.getList().get(i).getRiCity() + addressBean.getList().get(i).getRiAddress());

                finish();

                Log.e("++++rild+++++",addressBean.getList().get(i).getRiId()+"");

            }
        });

    }

    @OnClick({R.id.selectaddress_back, R.id.new_address})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.selectaddress_back:
                finish();
                break;
            case R.id.new_address:
                startActivity(new Intent(SelectAddressActivity.this,NewAddressActivity.class));
                finish();
                break;
        }
    }
    //获取收货地址
    private void getaddresslv(String amNumber){
        Map<String,Object>map=new HashMap<>();
        map.put("mode","1");
        map.put("amNumber",amNumber);
        XUtil.Post(Config.SmReceiptinfoServlet_URL,map,new MyCallBack<String>(){
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                dialogFragment.dismiss();
                addressBean= JSON.parseObject(result,AddressBean.class);
                if ("00".equals(addressBean.getCode())&&addressBean.getList()!=null){
                    initview();
                    Log.e("+++","+++");
                }else if("00".equals(addressBean.getCode())&&addressBean.getList()==null){
                    Log.e("---","---");
                    addressLv.setAdapter(null);
                    return;
                }else{
                    MyApp.getInstance().showToast(addressBean.getFailMessage());
                    return;
                }
            }
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
                dialogFragment.dismiss();
            }
        });

    }

    private void initview(){
        mSelectArray=new SparseBooleanArray();;//储存boolean类型pair(key,value)
        adapter=new CommonAdapter<AddressBean.ListBean>(SelectAddressActivity.this,addressBean.getList()
                ,R.layout.address_item) {
            @Override
            public void convert(final ViewHolder mHolder,final AddressBean.ListBean item,final int position) {
                mHolder.setText(R.id.select_name,item.getRiName());//收货人
                mHolder.setText(R.id.select_tel,item.getRiPhone());//联系号码
                mHolder.setText(R.id.select_address,item.getRiCity()+item.getRiAddress());//收货地址拼接

                if (item.getRiDefault()==1&&boo==true){
                    mHolder.setViewCheckBox(R.id.select_default).setChecked(true);
                    mHolder.setVisibility(R.id.img_default,View.VISIBLE);
                }else{
                    mHolder.setViewCheckBox(R.id.select_default).setChecked(mSelectArray.get(position,false));
                }

                //编辑监听
                mHolder.setonClick(R.id.select_edit, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        SPUtils.put(getApplication(),"RiId",item.getRiId()+"");
                        startActivity(new Intent(SelectAddressActivity.this,EditAddressActivity.class));
                        SPUtils.put(getApplication(),"riPhone",item.getRiPhone());
                        SPUtils.put(getApplication(),"riAddress",item.getRiAddress());
                        SPUtils.put(getApplication(),"riCity",item.getRiCity());
                        SPUtils.put(getApplication(),"riName",item.getRiName());
                        SPUtils.put(getApplication(),"riDefault",item.getRiDefault()+"");
                        finish();
                    }
                });
                //删除监听
                mHolder.setonClick(R.id.select_delete, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialogFragment = new CircleDialog.Builder(SelectAddressActivity.this)
                                .setProgressText("请稍等。。。")
                                .setCanceledOnTouchOutside(false)
                                .setProgressStyle(ProgressParams.STYLE_SPINNER)
                                .show();

                        deleteaddress(amnumber,item.getRiId()+"");
                    }
                });
                //默认框监听
                mHolder.setonClick(R.id.select_default, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        boo=true;
                        mSelectArray.clear();
                        mSelectArray.put(position,mHolder.setViewCheckBox(R.id.select_default).isChecked());
                        setdefaultaddress(amnumber,item.getRiId()+"");
                        notifyDataSetChanged();
                    }
                });

            }
        };
        addressLv.setAdapter(adapter);



    }

    //设置默认地址
    private void setdefaultaddress(String amNumber,String riId){
        Map<String,Object>map=new HashMap<>();
        map.put("mode","1");
        map.put("amNumber",amNumber);
        map.put("riDefault","1");
        map.put("riId",riId);
        Log.e("+++setdefaultaddress+++",map+"");
        XUtil.Post(Config.SmReceiptinfoServlet_URL,map,new MyCallBack<String>(){
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                addressBean=JSON.parseObject(result,AddressBean.class);
                if ("00".equals(addressBean.getCode())){
                    MyApp.getInstance().showToast(addressBean.getSuccessMessage());
                    getaddresslv(amnumber);
                }else{
                    MyApp.getInstance().showToast(addressBean.getFailMessage());
                }
            }
        });
    }

    //删除地址
    private void deleteaddress(String amNumber,String riId){
        Map<String,Object>map=new HashMap<>();
        map.put("mode","4");
        map.put("amNumber",amNumber);
        map.put("riId",riId);

        XUtil.Post(Config.SmReceiptinfoServlet_URL,map,new MyCallBack<String>(){
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                dialogFragment.dismiss();
                addressBean=JSON.parseObject(result,AddressBean.class);
                if ("00".equals(addressBean.getCode())){
                    MyApp.getInstance().showToast(addressBean.getSuccessMessage());
                    getaddresslv(amnumber);
                    adapter.notifyDataSetChanged();
                }else {
                    MyApp.getInstance().showToast(addressBean.getFailMessage());
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
