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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import com.futeng.happypays.R;
import com.futeng.happypays.activity.renzheng.listview.BankListActivity;
import com.futeng.happypays.activity.renzheng.listview.CityActivity;
import com.futeng.happypays.activity.renzheng.listview.ProvinceListActivity;
import com.futeng.happypays.activity.webview.FindBankNumActivity;
import com.futeng.happypays.constant.Constant;
import com.futeng.happypays.tools.SPUtils;

/**
 * 实名认证结算信息
 */
public class RenzhengJiesuan extends Activity {
    @InjectView(R.id.rz_js_back)
    ImageView rzJsBack;
    @InjectView(R.id.rz_js_next)
    Button rzJsNext;

    @InjectView(R.id.allbank)
    TextView allbank;
    @InjectView(R.id.jiesuan_province)
    TextView jiesuanProvince;
    @InjectView(R.id.jiesuan_city)
    TextView jiesuanCity;
    @InjectView(R.id.jiesuan_name)
    EditText jiesuanName;
    @InjectView(R.id.jiesuan_bankcard)
    EditText jiesuanBankcard;
    @InjectView(R.id.jiesuan_otherbank)
    EditText jiesuanOtherbank;
    @InjectView(R.id.jiesuan_lianhanghao)
    EditText jiesuanLianhanghao;
    @InjectView(R.id.radioButton1)
    RadioButton radioButton1;
    @InjectView(R.id.radioButton2)
    RadioButton radioButton2;
    @InjectView(R.id.radiogroup)
    RadioGroup radiogroup;
    @InjectView(R.id.findbanknum)
    TextView findbanknum;

    private int ProvinceId = 0;//省份id
    private int CityId = 0;//城市id

    private int Bankid = 0;//银行id

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.renzheng_jiesuan);
        ButterKnife.inject(this);

        radiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == radioButton1.getId()) {
                    SPUtils.put(RenzhengJiesuan.this, "radioButton", 1 + "");
                } else {
                    SPUtils.put(RenzhengJiesuan.this, "radioButton", 0 + "");
                }
                //存账户类型
                Log.e("+++++++++++", SPUtils.getString(getApplicationContext(), "radioButton"));
            }
        });

    }

    @OnClick({R.id.rz_js_back, R.id.allbank, R.id.jiesuan_province, R.id.jiesuan_city, R.id.rz_js_next, R.id.radioButton1, R.id.radioButton2,R.id.findbanknum})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rz_js_back:
                finish();
                break;
            case R.id.allbank:
                Intent intent2 = new Intent(RenzhengJiesuan.this, BankListActivity.class);
                intent2.putExtra(Constant.IN_BANK_ID, Bankid);
                startActivityForResult(intent2, Constant.REQUEST_OK);
                //startActivity(new Intent(this, BankListActivity.class));
                break;
            case R.id.jiesuan_province:
                Intent intent = new Intent(RenzhengJiesuan.this, ProvinceListActivity.class);
                startActivityForResult(intent, Constant.REQUEST_OK);
                break;
            case R.id.jiesuan_city:
                if (ProvinceId == 0) {
                    Toast.makeText(this, "请先选择省份", Toast.LENGTH_LONG).show();
                    return;
                }
                Intent intent1 = new Intent(RenzhengJiesuan.this, CityActivity.class);
                intent1.putExtra(Constant.IN_ID, ProvinceId);
                startActivityForResult(intent1, Constant.REQUEST_OK);
                break;
            case R.id.rz_js_next:
                Baoc();

                if (TextUtils.isEmpty(jiesuanName.getText())) {
                    Toast.makeText(RenzhengJiesuan.this, "结算户名不能为空", Toast.LENGTH_SHORT).show();
                    return;
                } else if (TextUtils.isEmpty(jiesuanBankcard.getText())) {
                    Toast.makeText(RenzhengJiesuan.this, "结算卡号不能为空", Toast.LENGTH_SHORT).show();
                    return;
                } else if (TextUtils.isEmpty(allbank.getText())) {
                    Toast.makeText(RenzhengJiesuan.this, "开户银行不能为空", Toast.LENGTH_SHORT).show();
                    return;
                } else if (TextUtils.isEmpty(jiesuanProvince.getText())) {
                    Toast.makeText(RenzhengJiesuan.this, "开户省份不能为空", Toast.LENGTH_SHORT).show();
                    return;
                } else if (TextUtils.isEmpty(jiesuanCity.getText())) {
                    Toast.makeText(RenzhengJiesuan.this, "开户城市不能为空", Toast.LENGTH_SHORT).show();
                    return;
                } else if (TextUtils.isEmpty(jiesuanOtherbank.getText())) {
                    Toast.makeText(RenzhengJiesuan.this, "开户支行不能为空", Toast.LENGTH_SHORT).show();
                    return;
                } else if (TextUtils.isEmpty(jiesuanLianhanghao.getText())) {
                    Toast.makeText(RenzhengJiesuan.this, "联行号不能为空", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    //提交
                    startActivity(new Intent(RenzhengJiesuan.this, RenzhengPhoto.class));
                }
                break;
            case R.id.findbanknum:
                startActivity(new Intent(this, FindBankNumActivity.class));
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constant.REQUEST_OK) {
            switch (resultCode) {
                case Constant.ProvinceRESULT_OK:
                    ProvinceId = data.getIntExtra(Constant.IN_City_ID, 0);
                    jiesuanProvince.setText(data.getStringExtra(Constant.IN_City_Name));
                    break;
                case Constant.CityRESULT_OK:
                    CityId = data.getIntExtra(Constant.IN_City_ID, 0);
                    jiesuanCity.setText(data.getStringExtra(Constant.IN_City_Name));
                    break;
                case Constant.BankRESULT_OK:
                    Bankid = data.getIntExtra(Constant.IN_BANK_ID, 0);
                    allbank.setText(data.getStringExtra(Constant.IN_BANK_NAME));
                    break;
            }
        }
    }

    private void Baoc() {
        //保存结算户名
        SPUtils.put(RenzhengJiesuan.this, "jiesuanName", jiesuanName.getText().toString());
        //保存银行卡号
        SPUtils.put(RenzhengJiesuan.this, "jiesuanBankcard", jiesuanBankcard.getText().toString());
        //保存联行号
        SPUtils.put(RenzhengJiesuan.this, "jiesuanLianhanghao", jiesuanLianhanghao.getText().toString());
        //保存开户支行
        SPUtils.put(RenzhengJiesuan.this, "jiesuanOtherbank", jiesuanOtherbank.getText().toString());
        //保存省id
        SPUtils.put(RenzhengJiesuan.this, "Province", ProvinceId + "");
        //保存市id
        SPUtils.put(RenzhengJiesuan.this, "City", CityId + "");
        //保存银行id
        SPUtils.put(RenzhengJiesuan.this, "Bankid", Bankid + "");

        Log.e("++++++++++++", SPUtils.getString(getApplicationContext(), "Province"));
        Log.e("++++++++++++", SPUtils.getString(getApplicationContext(), "City"));
        Log.e("+++++++++++++", SPUtils.getString(getApplicationContext(), "Bankid"));
    }

}
