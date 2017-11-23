package com.futeng.happypays.activity.fgmentfour;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import com.futeng.happypays.R;
import com.futeng.happypays.tools.SPUtils;

/**
 * 个人信息
 */
public class InfoActivity extends Activity {

    @InjectView(R.id.info_back)
    ImageView infoBack;
    @InjectView(R.id.amName)
    TextView amName;
    @InjectView(R.id.amPerson)
    TextView amPerson;
    @InjectView(R.id.accountName)
    TextView accountName;
    @InjectView(R.id.amPersonPhone)
    TextView amPersonPhone;
    @InjectView(R.id.amIdNumber)
    TextView amIdNumber;
    @InjectView(R.id.bankName)
    TextView bankName;
    @InjectView(R.id.accountNumber)
    TextView accountNumber;
    @InjectView(R.id.bankBranchNumber)
    TextView bankBranchNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_info);
        ButterKnife.inject(this);
        info();
    }

    @OnClick(R.id.info_back)
    public void onClick() {
        finish();
    }

    private void info(){
        amName.setText(SPUtils.getString(this,"infoAmName"));
        amPerson.setText(SPUtils.getString(this,"infoAmPerson"));
        accountName.setText(SPUtils.getString(this,"infoAccountName"));
        amPersonPhone.setText(SPUtils.getString(this,"infoAmPersonPhone"));
        amIdNumber.setText(SPUtils.getString(this,"infoAmIdNumber"));
        bankName.setText(SPUtils.getString(this,"infoBankName"));
        accountNumber.setText(SPUtils.getString(this,"infoAccountNumber"));
        bankBranchNumber.setText(SPUtils.getString(this,"infoBankBranchNumber"));
    }

}
