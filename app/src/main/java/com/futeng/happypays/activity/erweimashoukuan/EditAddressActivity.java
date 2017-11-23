package com.futeng.happypays.activity.erweimashoukuan;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.bigkoo.pickerview.OptionsPickerView;
import com.futeng.happypays.R;
import com.futeng.happypays.tools.Config;
import com.futeng.happypays.tools.GetJsonDataUtil;
import com.futeng.happypays.tools.MyApp;
import com.futeng.happypays.tools.MyCallBack;
import com.futeng.happypays.tools.SPUtils;
import com.futeng.happypays.tools.XUtil;
import com.futeng.happypays.utils.AddressBean;
import com.futeng.happypays.utils.JsonBean;
import com.google.gson.Gson;
import com.mylhyl.circledialog.CircleDialog;
import com.mylhyl.circledialog.params.ProgressParams;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

import static com.futeng.happypays.R.layout.item;

public class EditAddressActivity extends FragmentActivity {

    @InjectView(R.id.editaddress_back)
    ImageView editaddressBack;
    @InjectView(R.id.editaddress_consignee)
    EditText editaddressConsignee;
    @InjectView(R.id.editaddress_tel)
    EditText editaddressTel;
    @InjectView(R.id.editaddress_text)
    TextView editaddressText;
    @InjectView(R.id.imageView13)
    ImageView imageView13;
    @InjectView(R.id.RelativeLayout_newaddress)
    RelativeLayout RelativeLayoutNewaddress;
    @InjectView(R.id.editaddress_particular)
    EditText editaddressParticular;
    @InjectView(R.id.editaddress_default)
    ImageView editaddressDefault;
    @InjectView(R.id.editaddress_delete)
    TextView editaddressDelete;
    @InjectView(R.id.editaddress_save)
    TextView editaddressSave;

    private ArrayList<JsonBean> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();
    private Thread thread;
    private static final int MSG_LOAD_DATA = 0x0001;
    private static final int MSG_LOAD_SUCCESS = 0x0002;
    private static final int MSG_LOAD_FAILED = 0x0003;
    private String city;

    private boolean isLoaded = false;

    private int i=1;
    private String amnumber;

    private int riDefault=0;
    private boolean a;
    private int click=0;

    private AddressBean addressBean;
    private DialogFragment dialogFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_address);
        ButterKnife.inject(this);
        amnumber=SPUtils.getString(getApplication(),"AmNumber");
        riDefault= Integer.parseInt(SPUtils.getString(getApplication(),"riDefault"));
        if (riDefault==1){
            editaddressDefault.setBackgroundResource(R.mipmap.clickquan);
            a=true;
            click=1;
        }
        editaddressConsignee.setText(SPUtils.getString(getApplication(),"riName"));
        editaddressParticular.setText(SPUtils.getString(getApplication(),"riAddress"));
        editaddressTel.setText(SPUtils.getString(getApplication(),"riPhone"));
        editaddressText.setText(SPUtils.getString(getApplication(),"riCity"));
        city=SPUtils.getString(getApplication(),"riCity");

    }

    @OnClick({R.id.editaddress_back, R.id.RelativeLayout_newaddress, R.id.editaddress_default, R.id.editaddress_delete, R.id.editaddress_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.editaddress_back:
                finish();
                break;
            case R.id.RelativeLayout_newaddress:
                if (i==1){
                    mHandler.sendEmptyMessage(MSG_LOAD_DATA);
                }else{
                    if (isLoaded){
                        ShowPickerView();
                        i=2;
                        Log.e("888888",i+"");
                    }else {
                        Toast.makeText(EditAddressActivity.this,"请等待数据解析完成", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case R.id.editaddress_default:
                if (click==1){
                    return;
                }else {
                    Log.e("+++++++",a+"");
                    if (a==false){
                        editaddressDefault.setBackgroundResource(R.mipmap.clickquan);
                        riDefault=1;
                        a=true;
                    }else{
                        editaddressDefault.setBackgroundResource(R.mipmap.quan);
                        riDefault=0;
                        a=false;
                    }
                    Log.e("------",riDefault+"");
                }

                break;
            case R.id.editaddress_delete:
                String riId= SPUtils.getString(getApplication(),"RiId");
                deleteaddress(amnumber,riId);
                break;
            case R.id.editaddress_save:
                String riName= editaddressConsignee.getText().toString();//收货人
                String riPhone= editaddressTel.getText().toString();//联系电话
                String riAddress=editaddressParticular.getText().toString();//详细地址
                String riIds= SPUtils.getString(getApplication(),"RiId");
                if (TextUtils.isEmpty(editaddressConsignee.getText())){
                    MyApp.getInstance().showToast("收货人不能为空");
                    return;
                } else if (TextUtils.isEmpty(editaddressTel.getText())) {
                    MyApp.getInstance().showToast("联系电话不能为空");
                    return;
                }else if (city==null){
                    MyApp.getInstance().showToast("省市区不能为空");
                    return;
                } else if (TextUtils.isEmpty(editaddressParticular.getText())){
                    MyApp.getInstance().showToast("详细地址不能为空");
                    return;
                }else{
                    dialogFragment = new CircleDialog.Builder(EditAddressActivity.this)
                            .setProgressText("正在登入，请稍等。。。")
                            .setCanceledOnTouchOutside(false)
                            .setProgressStyle(ProgressParams.STYLE_SPINNER)
                            .show();
                    saveaddress(amnumber,riIds,riPhone,city,riAddress,riName);
                }
                break;
        }
    }

    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_LOAD_DATA:
                    if (thread==null){//如果已创建就不再重新创建子线程了
                        Log.e("开始解析","开始解析");
                        thread = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                // 写子线程中的操作,解析省市区数据
                                initJsonData();
                            }
                        });
                        thread.start();
                    }
                    break;
                case MSG_LOAD_SUCCESS:
                    Log.e("解析成功","解析成功");
                    isLoaded = true;
                    if (isLoaded){
                        ShowPickerView();
                        i=2;
                    }else {
                        Toast.makeText(EditAddressActivity.this,"请等待数据解析完成", Toast.LENGTH_SHORT).show();
                    }
                    break;
                case MSG_LOAD_FAILED:
                    Toast.makeText(EditAddressActivity.this,"解析失败", Toast.LENGTH_SHORT).show();
                    break;

            }
        }
    };

    private void ShowPickerView() {// 弹出选择器

        OptionsPickerView pvOptions = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                city = options1Items.get(options1).getPickerViewText()+
                        options2Items.get(options1).get(options2)+
                        options3Items.get(options1).get(options2).get(options3);
                editaddressText.setText(city);
//                Toast.makeText(NewAddressActivity.this,tx, Toast.LENGTH_SHORT).show();
            }
        })

                .setTitleText("城市选择")
                .setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setContentTextSize(20)
                .build();

        /*pvOptions.setPicker(options1Items);//一级选择器
        pvOptions.setPicker(options1Items, options2Items);//二级选择器*/
        pvOptions.setPicker(options1Items, options2Items,options3Items);//三级选择器
        pvOptions.show();
    }

    private void initJsonData() {//解析数据

        /**
         * 注意：assets 目录下的Json文件仅供参考，实际使用可自行替换文件
         * 关键逻辑在于循环体
         *
         * */
        String JsonData = new GetJsonDataUtil().getJson(this,"province.json");//获取assets目录下的json文件数据

        ArrayList<JsonBean> jsonBean = parseData(JsonData);//用Gson 转成实体

        /**
         * 添加省份数据
         *
         * 注意：如果是添加的JavaBean实体，则实体类需要实现 IPickerViewData 接口，
         * PickerView会通过getPickerViewText方法获取字符串显示出来。
         */
        options1Items = jsonBean;

        for (int i=0;i<jsonBean.size();i++){//遍历省份
            ArrayList<String> CityList = new ArrayList<>();//该省的城市列表（第二级）
            ArrayList<ArrayList<String>> Province_AreaList = new ArrayList<>();//该省的所有地区列表（第三极）

            for (int c=0; c<jsonBean.get(i).getCityList().size(); c++){//遍历该省份的所有城市
                String CityName = jsonBean.get(i).getCityList().get(c).getName();
                CityList.add(CityName);//添加城市

                ArrayList<String> City_AreaList = new ArrayList<>();//该城市的所有地区列表

                //如果无地区数据，建议添加空字符串，防止数据为null 导致三个选项长度不匹配造成崩溃
                if (jsonBean.get(i).getCityList().get(c).getArea() == null
                        ||jsonBean.get(i).getCityList().get(c).getArea().size()==0) {
                    City_AreaList.add("");
                }else {

                    for (int d=0; d < jsonBean.get(i).getCityList().get(c).getArea().size(); d++) {//该城市对应地区所有数据
                        String AreaName = jsonBean.get(i).getCityList().get(c).getArea().get(d);

                        City_AreaList.add(AreaName);//添加该城市所有地区数据
                    }
                }
                Province_AreaList.add(City_AreaList);//添加该省所有地区数据
            }

            /**
             * 添加城市数据
             */
            options2Items.add(CityList);

            /**
             * 添加地区数据
             */
            options3Items.add(Province_AreaList);
        }

        mHandler.sendEmptyMessage(MSG_LOAD_SUCCESS);

    }

    public ArrayList<JsonBean> parseData(String result) {//Gson 解析
        ArrayList<JsonBean> detail = new ArrayList<>();
        try {
            JSONArray data = new JSONArray(result);
            Gson gson = new Gson();
            for (int i = 0; i < data.length(); i++) {
                JsonBean entity = gson.fromJson(data.optJSONObject(i).toString(), JsonBean.class);
                detail.add(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
            mHandler.sendEmptyMessage(MSG_LOAD_FAILED);
        }
        return detail;
    }

    private void deleteaddress(String amNumber,String riId){
        Map<String,Object>map=new HashMap<>();
        map.put("mode","4");
        map.put("amNumber",amNumber);
        map.put("riId",riId);
        XUtil.Post(Config.SmReceiptinfoServlet_URL,map,new MyCallBack<String>(){
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                addressBean= JSON.parseObject(result,AddressBean.class);
                if ("00".equals(addressBean.getCode())){
                    MyApp.getInstance().showToast(addressBean.getSuccessMessage());
                    startActivity(new Intent(EditAddressActivity.this,SelectAddressActivity.class));
                    finish();
                }else{
                    MyApp.getInstance().showToast(addressBean.getFailMessage());
                }
            }
        });
    }

    private void saveaddress(String amNumber,String riId,String riPhone,String riCity,String riAddress,String riName){
        Map<String,Object>map=new HashMap<>();
        map.put("mode","3");
        map.put("amNumber",amNumber);
        map.put("riId",riId);
        map.put("riPhone",riPhone);
        map.put("riCity",riCity);
        map.put("riAddress",riAddress);
        map.put("riName",riName);
        map.put("riDefault",riDefault);

        XUtil.Post(Config.SmReceiptinfoServlet_URL,map,new MyCallBack<String>(){
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                dialogFragment.dismiss();
                addressBean= JSON.parseObject(result,AddressBean.class);
                if ("00".equals(addressBean.getCode())){
                    MyApp.getInstance().showToast(addressBean.getSuccessMessage());
                    startActivity(new Intent(EditAddressActivity.this,SelectAddressActivity.class));
                    finish();
                }else{
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
