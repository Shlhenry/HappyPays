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
import com.futeng.happypays.adapter.ListviewBankAdapter;
import com.futeng.happypays.constant.Constant;
import com.futeng.happypays.tools.Config;
import com.futeng.happypays.tools.MyApp;
import com.futeng.happypays.tools.MyCallBack;
import com.futeng.happypays.tools.XUtil;
import com.futeng.happypays.utils.BankBean;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class BankListActivity extends Activity {


    @InjectView(R.id.bank_lv_list)
    ListView bankLvList;

    private BankBean bankBean = null;
    private ListviewBankAdapter listviewBankAdapter = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_bank_list);
        ButterKnife.inject(this);
        allbanks();

        bankLvList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent=new Intent();
                intent.putExtra(Constant.IN_BANK_ID,bankBean.getList().get(position).getBankId());
                intent.putExtra(Constant.IN_BANK_NAME,bankBean.getList().get(position).getBankName());
                BankListActivity.this.setResult(Constant.BankRESULT_OK,intent);
                BankListActivity.this.finish();


//                SPUtils.put(getApplicationContext(), "bankName",bankBean.getList().get(position).getBankName());
//                startActivity(new Intent(BankListActivity.this, RenzhengJiesuan.class));

            }
        });
    }


    private void allbanks() {

        XUtil.Post(Config.AllBank_URL, null, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                bankBean = JSON.parseObject(result, BankBean.class);

                if (bankBean.isIsOK()) {
                    initView();
                } else {
                    MyApp.getInstance().showToast(bankBean.getMessage());
                }
            }
        });


    }

    private void initView() {
        listviewBankAdapter = new ListviewBankAdapter(this,bankBean.getList());
        bankLvList.setAdapter(listviewBankAdapter);

    }

}
