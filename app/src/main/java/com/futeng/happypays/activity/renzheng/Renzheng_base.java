package com.futeng.happypays.activity.renzheng;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.futeng.happypays.R;
import com.futeng.happypays.activity.renzheng.listview.AreaActivity;
import com.futeng.happypays.activity.renzheng.listview.CityActivity;
import com.futeng.happypays.activity.renzheng.listview.ProvinceListActivity;
import com.futeng.happypays.activity.MainActivity;
import com.futeng.happypays.constant.Constant;
import com.futeng.happypays.tools.SPUtils;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * 实名认证基本资料
 */
public class Renzheng_base extends Activity {

    @InjectView(R.id.rz_base_back)
    ImageView rzBaseBack;
    @InjectView(R.id.rz_base_next)
    Button rzBaseNext;

    @InjectView(R.id.renzheng_shopname)
    EditText renzhengShopname;
    @InjectView(R.id.renzheng_lianxiren)
    EditText renzhengLianxiren;
    @InjectView(R.id.renzheng_peoplecard)
    EditText renzhengPeoplecard;
    @InjectView(R.id.rengzhen_province)
    TextView rengzhenProvince;
    @InjectView(R.id.renzheng_city)
    TextView renzhengCity;
    @InjectView(R.id.renzheng_area)
    TextView renzhengArea;
    @InjectView(R.id.renzheng_address)
    EditText renzhengAddress;

    private int ProvinceId=0;//省份id
    private int CityId=0;//城市id
    private int AreaId=0;//区id

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.renzheng_base);
        ButterKnife.inject(this);

    }

    @OnClick({R.id.rz_base_back, R.id.rengzhen_province, R.id.renzheng_city, R.id.renzheng_area, R.id.rz_base_next,R.id.renzheng_shopname})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rz_base_back:
                finish();
                break;
            case R.id.rengzhen_province:
                Intent intent=new Intent(Renzheng_base.this, ProvinceListActivity.class);
                startActivityForResult(intent, Constant.REQUEST_OK);
                break;
            case R.id.renzheng_city:
                if (ProvinceId==0){
                    Toast.makeText(this,"请先选择省份",Toast.LENGTH_LONG).show();
                    return;
                }
                Intent intent1=new Intent(Renzheng_base.this, CityActivity.class);
                intent1.putExtra(Constant.IN_ID,ProvinceId);
                startActivityForResult(intent1, Constant.REQUEST_OK);
                break;
            case R.id.renzheng_area:
                if (CityId==0){
                    Toast.makeText(this,"请先选择城市",Toast.LENGTH_LONG).show();
                    return;
                }
                Intent intent2=new Intent(Renzheng_base.this, AreaActivity.class);
                intent2.putExtra(Constant.IN_ID,CityId);
                startActivityForResult(intent2, Constant.REQUEST_OK);
                break;
            case R.id.rz_base_next:
                baocun();

                if (TextUtils.isEmpty(renzhengShopname.getText())) {
                    Toast.makeText(Renzheng_base.this, "商户名称不能为空", Toast.LENGTH_SHORT).show();
                    return;
                } else if (TextUtils.isEmpty(renzhengLianxiren.getText())) {
                    Toast.makeText(Renzheng_base.this, "联系人不能为空", Toast.LENGTH_SHORT).show();
                    return;
                } else if (TextUtils.isEmpty(renzhengPeoplecard.getText())) {
                    Toast.makeText(Renzheng_base.this, "身份证号不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }else if (TextUtils.isEmpty(rengzhenProvince.getText())) {
                    Toast.makeText(Renzheng_base.this, "经营省份不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }else if (TextUtils.isEmpty(renzhengCity.getText())) {
                    Toast.makeText(Renzheng_base.this, "经营城市不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }else if (TextUtils.isEmpty(renzhengArea.getText())) {
                    Toast.makeText(Renzheng_base.this, "经营地区不能为空", Toast.LENGTH_SHORT).show();
                    return;
                } else if (TextUtils.isEmpty(renzhengAddress.getText())) {
                    Toast.makeText(Renzheng_base.this, "经营地址不能为空", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    //提交
                    startActivity(new Intent(Renzheng_base.this, RenzhengJiesuan.class));
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==Constant.REQUEST_OK){
            switch (resultCode){
                case Constant.ProvinceRESULT_OK:
                    ProvinceId=data.getIntExtra(Constant.IN_City_ID,0);
                    rengzhenProvince.setText(data.getStringExtra(Constant.IN_City_Name));
                    break;
                case Constant.CityRESULT_OK:
                    CityId=data.getIntExtra(Constant.IN_City_ID,0);
                    renzhengCity.setText(data.getStringExtra(Constant.IN_City_Name));
                    break;
                case Constant.AreaRESULT_OK:
                    AreaId=data.getIntExtra(Constant.IN_City_ID,0);
                    renzhengArea.setText(data.getStringExtra(Constant.IN_City_Name));
                    break;



            }
        }

    }

    private void baocun(){
        //保存商户名称供调用
        SPUtils.put(Renzheng_base.this, "renzhengShopname", renzhengShopname.getText().toString());
        //保存联系人供调用
        SPUtils.put(Renzheng_base.this, "renzhengLianxiren", renzhengLianxiren.getText().toString());
        //保存身份证号供调用
        SPUtils.put(Renzheng_base.this, "renzhengPeoplecard", renzhengPeoplecard.getText().toString());
        //保存经营地址供调用
        SPUtils.put(Renzheng_base.this, "renzhengAddress", renzhengAddress.getText().toString());
        //保存省id
        SPUtils.put(Renzheng_base.this, "ProvinceId",ProvinceId+"");
        //保存市id
        SPUtils.put(Renzheng_base.this, "CityId", CityId+"");
        //保存区id
        SPUtils.put(Renzheng_base.this, "AreaId", AreaId+"");

        //这是取值、前面是content、后者是键名
        // SPUtils.getString(getApplicationContext(),"renzhengShopname");
        //System.out.print("------------------"+SPUtils.getString(getApplicationContext(),"ProvinceId"));

        Log.e("++++++++++++",SPUtils.getString(getApplicationContext(),"ProvinceId"));
        Log.e("++++++++++++",SPUtils.getString(getApplicationContext(),"CityId"));
        Log.e("++++++++++++",SPUtils.getString(getApplicationContext(),"AreaId"));
    }
}
