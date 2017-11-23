package com.futeng.happypays.activity.erweimashoukuan;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.futeng.happypays.R;
import com.futeng.happypays.tools.Config;
import com.futeng.happypays.tools.MyApp;
import com.futeng.happypays.tools.MyCallBack;
import com.futeng.happypays.tools.MyDialog;
import com.futeng.happypays.tools.SPUtils;
import com.futeng.happypays.tools.XUtil;
import com.futeng.happypays.utils.AddressBean;
import com.futeng.happypays.utils.StoreConverBean;
import com.futeng.happypays.utils.StoredetailsBean;
import com.mylhyl.circledialog.CircleDialog;
import com.mylhyl.circledialog.params.ProgressParams;

import java.util.HashMap;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class StoreDetailsActivity extends FragmentActivity {

    @InjectView(R.id.set_back)
    ImageView setBack;
    @InjectView(R.id.RelativeLayout_store)
    RelativeLayout RelativeLayoutStore;
    @InjectView(R.id.comName)
    TextView comName;
    @InjectView(R.id.comMoney)
    TextView comMoney;
    @InjectView(R.id.exchangeCount)
    TextView exchangeCount;
    @InjectView(R.id.comCount)
    TextView comCount;
    @InjectView(R.id.comIntegral)
    TextView comIntegral;
    @InjectView(R.id.comBrief)
    TextView comBrief;
    @InjectView(R.id.activitytime)
    TextView activitytime;
    @InjectView(R.id.announcements)
    TextView announcements;
    @InjectView(R.id.storedetailes_img)
    ImageView storedetailesImg;
    @InjectView(R.id.subtract)
    ImageView subtract;
    @InjectView(R.id.add)
    ImageView add;
    @InjectView(R.id.getnum)
    TextView getnum;
    @InjectView(R.id.comCounts)
    TextView comCounts;
    @InjectView(R.id.rmb)
    TextView rmb;
    @InjectView(R.id.textview_address)
    TextView textviewAddress;
    @InjectView(R.id.store_name)
    TextView storeName;
    @InjectView(R.id.store_tel)
    TextView storeTel;
    @InjectView(R.id.RelativeLayout_addressstore)
    RelativeLayout RelativeLayoutAddressstore;
    @InjectView(R.id.getstore_detaile)
    TextView getstoreDetaile;
    @InjectView(R.id.ScrollView_store)
    ScrollView ScrollViewStore;
    @InjectView(R.id.quick_convert)
    TextView quickconvert;
    @InjectView(R.id.getend_num)
    TextView getendNum;
    @InjectView(R.id.getend_integral)
    TextView getendIntegral;
    private StoredetailsBean storedetailsBean;
    private DialogFragment dialogFragment;
    private int comConvertible;
    private AddressBean addressBean;
    private StoreConverBean  storeConverBean;
    private String amnumber;

    private int i = 1;//数量
    private int integ=0;
    //兑换方式
    private String ComExctype;
    private String comId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_details);
        ButterKnife.inject(this);
        amnumber = SPUtils.getString(getApplication(), "AmNumber");
        comId=SPUtils.getString(getApplication(), "comId");
        rmb.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        comMoney.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);

        dialogFragment = new CircleDialog.Builder(StoreDetailsActivity.this)
                .setProgressText("正在登入，请稍等。。。")
                .setCanceledOnTouchOutside(false)
                .setProgressStyle(ProgressParams.STYLE_SPINNER)
                .show();

        StoreDetails(comId);

        if ("1".equals(SPUtils.getString(getApplication(),"judge"))){
            textviewAddress.setText(SPUtils.getString(getApplication(),"textviewAddress"));
            storeName.setText(SPUtils.getString(getApplication(),"storeName"));
            storeTel.setText(SPUtils.getString(getApplication(),"storeTel"));
            getstoreDetaile.setText(SPUtils.getString(getApplication(),"getstoreDetaile"));
        }



    }

    private void StoreDetails(String comId) {
        Map<String, Object> map = new HashMap<>();
        map.put("mode", "2");
        map.put("comId", comId);

        XUtil.Post(Config.SmCommodityServlet_URL, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                dialogFragment.dismiss();
                storedetailsBean = JSON.parseObject(result, StoredetailsBean.class);
                if ("00".equals(storedetailsBean.getCode()) && storedetailsBean.getMap() != null) {

                    comName.setText(storedetailsBean.getMap().getComName() + "");
                    comMoney.setText(storedetailsBean.getMap().getComMoney() + "");
                    exchangeCount.setText(storedetailsBean.getMap().getExchangeCount() + "");
                    comCount.setText(storedetailsBean.getMap().getComCount() + "");
                    comIntegral.setText(storedetailsBean.getMap().getComIntegral() + "");
                    comBrief.setText(storedetailsBean.getMap().getComBrief() + "");
                    comCounts.setText(storedetailsBean.getMap().getComCount() + "");
                    integ=storedetailsBean.getMap().getComIntegral();//积分
                    comConvertible = storedetailsBean.getMap().getComConvertible();

                    //兑换方式 金额or积分
                    ComExctype=storedetailsBean.getMap().getComExctype()+"";

                    if ("1".equals(storedetailsBean.getMap().getActivityType())) {
                        activitytime.setText(storedetailsBean.getMap().getActivityBeginTime() + "至" + storedetailsBean.getMap().getActivityEndTime() + "");
                    } else {
                        activitytime.setText(storedetailsBean.getMap().getActivityBeginTimeDefault() + "至" + storedetailsBean.getMap().getActivityEndTimeDefault() + "");
                    }
                    if ("1".equals(storedetailsBean.getMap().getMattersType())) {
                        String a = storedetailsBean.getMap().getMattersContent();
                        announcements.setText(a.replace(";", "\n") + "");
                    } else {
                        String b = storedetailsBean.getMap().getMattersContentDefault();
                        announcements.setText(b.replace(";", "\n") + "");
                    }

                    Glide.with(StoreDetailsActivity.this)
                            .load(storedetailsBean.getMap().getComImgUrl())
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .dontAnimate().placeholder
                            (R.mipmap.redbull).into(storedetailesImg);

                    getendNum.setText(i+"");
                    getendIntegral.setText(i*integ+"");


                } else {
                    MyApp.getInstance().showToast(storedetailsBean.getFailMessage());
                }
            }
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
                dialogFragment.dismiss();
            }
        });
    }


    @OnClick({R.id.set_back, R.id.subtract, R.id.add, R.id.RelativeLayout_addressstore, R.id.quick_convert})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.set_back:
                finish();
                break;
            case R.id.subtract:
                if (i > 1) {
                    i--;
                } else {
                    return;
                }
                if (i > 1 && i < comConvertible) {
                    subtract.setBackgroundResource(R.mipmap.clickless);
                    add.setBackgroundResource(R.mipmap.clickadd);
                } else {
                    subtract.setBackgroundResource(R.mipmap.less);
                }
                getnum.setText(i + "");
                getendNum.setText(i+"");
                getendIntegral.setText(i*integ+"");

                break;
            case R.id.add:
                Log.e("+++++++++++", ("i:" + i + "comConvertible" + comConvertible) + "");
                if (i < comConvertible) {
                    i++;
                } else {
                    return;
                }
                if (i < comConvertible) {
                    subtract.setBackgroundResource(R.mipmap.clickless);
                    add.setBackgroundResource(R.mipmap.clickadd);
                } else {
                    add.setBackgroundResource(R.mipmap.add);
                }
                getnum.setText(i + "");
                getendNum.setText(i+"");
                getendIntegral.setText(i*integ+"");
                break;

            case R.id.RelativeLayout_addressstore:

                startActivity(new Intent(StoreDetailsActivity.this, SelectAddressActivity.class));
                break;
            case R.id.quick_convert:
                //立即兑换
                String riId= SPUtils.getString(getApplication(),"riId");
                if (riId==null){
                    MyApp.getInstance().showToast("请填写收货地址");
                }else{
                    quickconvert(amnumber,ComExctype,comId,riId,i+"","备注");
                }
                break;
        }
    }

    //立即兑换
    private void quickconvert(String amNumber,String exrcExcType,String comId,String riId,String exrcCount,String paoContent){
        Map<String,Object>map=new HashMap<>();
        map.put("mode","1");
        map.put("amNumber",amNumber);
        map.put("exrcExcType",exrcExcType);
        map.put("comId",comId);
        map.put("riId",riId);
        map.put("exrcCount",exrcCount);
        map.put("paoContent",paoContent);
        Log.e("兑换请求的map+++",map+"");
        XUtil.Post(Config.SmPlaceanOrderServlet_URL,map,new MyCallBack<String>(){
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                storeConverBean=JSON.parseObject(result,StoreConverBean.class);
                if ("00".equals(storeConverBean.getCode())){
                    showCustomizeDialog();
                }else{
                    MyApp.getInstance().showToast(storeConverBean.getFailMessage());
                }

            }
        });

    }

    private void showCustomizeDialog() {
//    /* @setView 装入自定义View ==> R.layout.dialog_customize
//     * 由于dialog_customize.xml只放置了一个EditView，因此和图8一样
//     * dialog_customize.xml可自定义更复杂的View
//     */
//        AlertDialog.Builder customizeDialog = new AlertDialog.Builder(MyTeamAllFragment.this);
//        final View dialogView = LayoutInflater.from(MyTeamAllFragment.this).inflate(R.layout.dialog_rule, null);
//
//        customizeDialog.setView(dialogView);
//        TextView ruleText= (TextView) dialogView.findViewById(R.id.rule_text);
//        ruleText.setMovementMethod(ScrollingMovementMethod.getInstance());
//        ruleText.setText(rules);
//        customizeDialog.show();


        final MyDialog dialog = new MyDialog(StoreDetailsActivity.this);
        LayoutInflater inflater = getLayoutInflater();
        LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.dialog_convert_success, null);

        Button rulebtn = (Button) layout.findViewById(R.id.convert_btn);
        rulebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.getWindow().setDimAmount(0);
        dialog.show();
        dialog.setCancelable(false);
        dialog.setContentView(layout);// show方法要在前面
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        textviewAddress.setText(SPUtils.getString(getApplication(),"textviewAddress"));
        storeName.setText(SPUtils.getString(getApplication(),"storeName"));
        storeTel.setText(SPUtils.getString(getApplication(),"storeTel"));
        getstoreDetaile.setText(SPUtils.getString(getApplication(),"getstoreDetaile"));
    }
}
