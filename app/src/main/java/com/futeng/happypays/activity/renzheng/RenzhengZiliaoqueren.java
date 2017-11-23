package com.futeng.happypays.activity.renzheng;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.futeng.happypays.R;
import com.futeng.happypays.activity.LoginActivity;
import com.futeng.happypays.activity.MainActivity;
import com.futeng.happypays.constant.Constant;
import com.futeng.happypays.tools.Config;
import com.futeng.happypays.tools.MyApp;
import com.futeng.happypays.tools.MyCallBack;
import com.futeng.happypays.tools.SPUtils;
import com.futeng.happypays.tools.XUtil;
import com.futeng.happypays.utils.AuthonBean;

import java.util.HashMap;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import com.futeng.happypays.utils.User;
import com.mylhyl.circledialog.CircleDialog;
import com.mylhyl.circledialog.params.ProgressParams;

/**
 * 实名认证资料确认
 */
public class RenzhengZiliaoqueren extends FragmentActivity {

    @InjectView(R.id.rz_queren_back)
    ImageView rzQuerenBack;
    @InjectView(R.id.rz_quren_next)
    Button rzQurenNext;
    @InjectView(R.id.ziliaoqueren_phone)
    TextView ziliaoquerenPhone;
    @InjectView(R.id.ziliaoqueren_name)
    TextView ziliaoquerenName;
    @InjectView(R.id.ziliaoqueren_address)
    TextView ziliaoquerenAddress;
    @InjectView(R.id.ziliaoqueren_idcard)
    TextView ziliaoquerenIdcard;
    @InjectView(R.id.ziliaoqueren_shopid)
    TextView ziliaoquerenShopid;

    private String phone;
    private String amName;
    private String amPerson;
    private String amIdNumber;
    private String proId;

    private String cityId;
    private String areaId;
    private String amAddress;
    private String accountName;
    private String accountNumber;

    private String accountType;
    private String bankBranchName;
    private String bankBranchNumber ;
    private String prov;
    private String city;
    private String bank;

    private String imageStr1;
    private String imageStr2;
    private String imageStr3;
    private String imageStr4;
    private String imageStr5;
    private String imageStr6;

    private DialogFragment dialogFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.renzheng_ziliaoqueren);
        ButterKnife.inject(this);



        //获取登入手机号赋值
        Intent intent = new Intent();
        intent.putExtra("phone", SPUtils.getString(RenzhengZiliaoqueren.this, "phone"));
        ziliaoquerenPhone.setText(SPUtils.getString(RenzhengZiliaoqueren.this, "phone"));

        //获取商户名称
        Intent intent1 = new Intent();
        intent.putExtra("renzhengShopname", SPUtils.getString(RenzhengZiliaoqueren.this, "renzhengShopname"));
        ziliaoquerenShopid.setText(SPUtils.getString(RenzhengZiliaoqueren.this, "renzhengShopname"));

        //获取姓名
        Intent intent2 = new Intent();
        intent.putExtra("renzhengLianxiren", SPUtils.getString(RenzhengZiliaoqueren.this, "renzhengLianxiren"));
        ziliaoquerenName.setText(SPUtils.getString(RenzhengZiliaoqueren.this, "renzhengLianxiren"));

        //获取商户身份证
        Intent intent3 = new Intent();
        intent.putExtra("renzhengPeoplecard", SPUtils.getString(RenzhengZiliaoqueren.this, "renzhengPeoplecard"));
        ziliaoquerenIdcard.setText(SPUtils.getString(RenzhengZiliaoqueren.this, "renzhengPeoplecard"));

        //获取商户地址
        Intent intent4 = new Intent();
        intent.putExtra("renzhengAddress", SPUtils.getString(RenzhengZiliaoqueren.this, "renzhengAddress"));
        ziliaoquerenAddress.setText(SPUtils.getString(RenzhengZiliaoqueren.this, "renzhengAddress"));

    }

    /***
     * @param view
     */

    @OnClick({R.id.rz_queren_back, R.id.rz_quren_next})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rz_queren_back:
                finish();
                break;
            case R.id.rz_quren_next:
                phone=SPUtils.getString(RenzhengZiliaoqueren.this, "phone");

                amName=SPUtils.getString(RenzhengZiliaoqueren.this, "renzhengShopname");

                amPerson=SPUtils.getString(RenzhengZiliaoqueren.this, "renzhengLianxiren");

                amIdNumber=SPUtils.getString(RenzhengZiliaoqueren.this, "renzhengPeoplecard");

                proId=SPUtils.getString(RenzhengZiliaoqueren.this, "ProvinceId");

                cityId=SPUtils.getString(RenzhengZiliaoqueren.this, "CityId");

                areaId=SPUtils.getString(RenzhengZiliaoqueren.this, "AreaId");

                amAddress=SPUtils.getString(RenzhengZiliaoqueren.this, "renzhengAddress");

                accountName=SPUtils.getString(RenzhengZiliaoqueren.this, "jiesuanName");

                accountNumber=SPUtils.getString(RenzhengZiliaoqueren.this, "jiesuanBankcard");

                bankBranchNumber=SPUtils.getString(RenzhengZiliaoqueren.this, "jiesuanLianhanghao");

                bankBranchName=SPUtils.getString(RenzhengZiliaoqueren.this, "jiesuanOtherbank");

                prov=SPUtils.getString(RenzhengZiliaoqueren.this, "Province");

                city=SPUtils.getString(RenzhengZiliaoqueren.this, "City");

                bank=SPUtils.getString(RenzhengZiliaoqueren.this, "Bankid");

                accountType=SPUtils.getString(RenzhengZiliaoqueren.this, "radioButton");

                imageStr1=SPUtils.getString(RenzhengZiliaoqueren.this, "imageStr1");

                imageStr2=SPUtils.getString(RenzhengZiliaoqueren.this, "imageStr2");

                imageStr4=SPUtils.getString(RenzhengZiliaoqueren.this, "imageStr4");

                imageStr5=SPUtils.getString(RenzhengZiliaoqueren.this, "imageStr5");

                dialogFragment = new CircleDialog.Builder(RenzhengZiliaoqueren.this)
                        .setProgressText("正在提交信息，请稍等。。。")
                        .setCanceledOnTouchOutside(false)
                        .setProgressStyle(ProgressParams.STYLE_SPINNER)
                        .show();

                shenhe( phone, amName, amPerson, amIdNumber, proId,
                         cityId, areaId, amAddress, accountName, accountNumber,
                         accountType, bankBranchName, bankBranchNumber, prov,city,bank
                       ,imageStr4,imageStr5,imageStr2, imageStr1
                );
                break;
        }
    }

    private void shenhe(String phone,String amName,String amPerson,String amIdNumber,String proId,
                        String cityId,String areaId,String amAddress,String accountName,String accountNumber,
                        String accountType,String bankBranchName,String bankBranchNumber,String prov,
                        String city,String bank
                        ,String imageStr1,String imageStr2,String imageStr4,String imageStr5
    ){
        Map<String,Object>map=new HashMap<>();
        map.put("phone",phone);
        map.put("amName",amName);
        map.put("amPerson",amPerson);
        map.put("amIdNumber",amIdNumber);
        map.put("proId",proId);
        map.put("cityId",cityId);
        map.put("areaId",areaId);
        map.put("amAddress",amAddress);
        map.put("accountName",accountName);
        map.put("accountNumber",accountNumber);
        map.put("accountType",accountType);
        map.put("bankBranchName",bankBranchName);
        map.put("bankBranchNumber",bankBranchNumber);
        map.put("prov",prov);
        map.put("city",city);
        map.put("bank",bank);
        map.put("handidcard",imageStr1);
        map.put("positivecard",imageStr2);
        map.put("reverseidcard",imageStr4);
        map.put("positiveidcard",imageStr5);

        Log.e("++++++++++++",map+"");

        XUtil.Post(Config.Authen_URL,map,new MyCallBack<String>(){

            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                dialogFragment.dismiss();
                AuthonBean authon= JSON.parseObject(result, AuthonBean.class);
                if ("00".equals(authon.getCode())){
                    Toast.makeText(RenzhengZiliaoqueren.this,authon.getSuccessMessage(),Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(RenzhengZiliaoqueren.this, MainActivity.class));
                    String tel=SPUtils.getString(RenzhengZiliaoqueren.this, "phone");
                    loginstate(tel);
                }else {
                    Toast.makeText(RenzhengZiliaoqueren.this,authon.getFailMessage(),Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(RenzhengZiliaoqueren.this, Renzheng_base.class));
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
                dialogFragment.dismiss();
            }
        });
    }

    private void loginstate(String phone){
        Map<String,Object>map=new HashMap<>();
        map.put("phone",phone);
        XUtil.Post(Config.LoginStateServlet_URL,map,new MyCallBack<String>(){
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                User userStr = JSON.parseObject(result, User.class);
                if ("00".equals(userStr.getCode())){
                    //保存账号供调用
                    SPUtils.put(getApplicationContext(), "phone", userStr.getMap().getPPhone());
                    SPUtils.put(getApplicationContext(), "AmNumber", userStr.getMap().getAmNumber());
                    SPUtils.put(getApplicationContext(), "pState", userStr.getMap().getPState());
                    SPUtils.put(getApplicationContext(),"amName",userStr.getMap().getAmName());
                    SPUtils.put(getApplicationContext(),"isOK",true);
                    SPUtils.put(getApplicationContext(),"amIdNumber",userStr.getMap().getAmIdNumber());
                    SPUtils.put(getApplicationContext(),"amAddress",userStr.getMap().getAmAddress());
                    SPUtils.put(getApplicationContext(),"amPerson",userStr.getMap().getAmPerson());
                    SPUtils.put(getApplicationContext(),"gradeName",userStr.getMap().getGradeName());
                }else if ("99".equals(userStr.getCode())){
                    MyApp.getInstance().showToast(userStr.getFailMessage());
                }

            }
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);

            }
        });
    }
}
