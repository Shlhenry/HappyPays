package com.futeng.happypays.tools;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.io.PipedReader;
import java.util.HashMap;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import com.alibaba.fastjson.JSON;
import com.futeng.happypays.R;
import com.futeng.happypays.activity.erweimashoukuan.AddselectActivity;
import com.futeng.happypays.activity.erweimashoukuan.QuickTransationActivity;
import com.futeng.happypays.activity.fgmentfour.BangXinCardActivity;
import com.futeng.happypays.adapter.RealtimeAdapter;
import com.futeng.happypays.adapter.SelectBankcardAdapter;
import com.futeng.happypays.utils.SelectBankcardBean;
import com.mylhyl.circledialog.BaseCircleDialog;

public class DialogLogout extends BaseCircleDialog implements View.OnClickListener {

    public String aaa;

    public static DialogLogout getInstance() {
        DialogLogout dialogFragment = new DialogLogout();
        dialogFragment.setCanceledBack(false);
        dialogFragment.setCanceledOnTouchOutside(false);
        dialogFragment.setGravity(Gravity.BOTTOM);
        dialogFragment.setWidth(1f);
        return dialogFragment;
    }

    private SelectBankcardAdapter selectBankcardAdapter=null;
    private SelectBankcardBean selectBankcardBean=null;

    private ListView lv;

    @Override
    public View createView(Context context, LayoutInflater inflater, ViewGroup container) {

        return inflater.inflate(R.layout.dialog_logout, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        View view = getView();
        view.findViewById(R.id.close_selectcard).setOnClickListener(this);
        view.findViewById(R.id.add_card).setOnClickListener(this);
        lv= (ListView) view.findViewById(R.id.selectbankcard_lv);

        selectbankcard();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.e("++++++++",position+"");
                SPUtils.put(getContext(),"selectcardname",selectBankcardBean.getList().get(position).getAccountName());
                SPUtils.put(getContext(),"selectcardnum",selectBankcardBean.getList().get(position).getAccountNumber());
                SPUtils.put(getContext(),"selectshowbank",selectBankcardBean.getList().get(position).getAbcRemark());
                aaa=selectBankcardBean.getList().get(position).getAbcRemark();

                dismiss();
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.close_selectcard:
                dismiss();
                break;
            case R.id.add_card:
                startActivity(new Intent(getActivity(), AddselectActivity.class));
                break;
        }
    }

    private void initView() {
        selectBankcardAdapter = new SelectBankcardAdapter(getContext(), selectBankcardBean.getList());
        lv.setAdapter(selectBankcardAdapter);
    }

    private void selectbankcard(){
        Map<String,Object>map=new HashMap<>();
        map.put("mode",2);
        map.put("amNumber","883000000000105");
        XUtil.Post(Config.AMBandCreditServlet_URL,map,new MyCallBack<String>(){
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                selectBankcardBean=JSON.parseObject(result,SelectBankcardBean.class);
                if ("00".equals(selectBankcardBean.getCode())){
                    if (selectBankcardBean.getList()!=null){
                        lv.setVisibility(View.VISIBLE);
                        initView();
                    }else {
                        return;
                    }
                }else {
                    MyApp.getInstance().showToast(selectBankcardBean.getMessage());
                }
            }
        });
    }

}
