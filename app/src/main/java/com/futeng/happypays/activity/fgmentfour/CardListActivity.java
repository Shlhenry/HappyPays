package com.futeng.happypays.activity.fgmentfour;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import com.alibaba.fastjson.JSON;
import com.futeng.happypays.R;
import com.futeng.happypays.activity.MainActivity;
import com.futeng.happypays.adapter.CardbangdingAdapter;
import com.futeng.happypays.tools.Config;
import com.futeng.happypays.tools.MyApp;
import com.futeng.happypays.tools.MyCallBack;
import com.futeng.happypays.tools.SPUtils;
import com.futeng.happypays.tools.XUtil;
import com.futeng.happypays.utils.CardBean;
import com.mylhyl.circledialog.CircleDialog;

/***
 * 银行卡管理
 */

public class CardListActivity extends FragmentActivity {

    @InjectView(R.id.cardlist_back)
    ImageView cardlistBack;
    @InjectView(R.id.cardlist_btn)
    LinearLayout cardlistBtn;
    @InjectView(R.id.cardlist_lv_list)
    ListView cardlistLvList;

    private CardBean cardBean = null;
    private CardbangdingAdapter cardbangdingAdapter = null;

    private int modeone=2;
    private int modetwo=3;
    private String accountNumber;
    private List<CardBean.ListBean> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_list);
        ButterKnife.inject(this);

        String amNumber = SPUtils.getString(CardListActivity.this, "AmNumber");
        Log.e("amNumber+++",amNumber);

        getcard(amNumber,modeone);

        cardlistLvList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                accountNumber=list.get(position).getAccountNumber();

                new CircleDialog.Builder(CardListActivity.this)
                        .setCanceledOnTouchOutside(false)
                        .setCancelable(false)
                        .setTitle("银行卡管理")
                        .setText("是否取消绑定")
                        .setNegative("取消", null)
                        .setPositive("确定", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                            //获取商户号
                            Intent intent = new Intent();
                            intent.putExtra("AmNumber", SPUtils.getString(CardListActivity.this, "AmNumber"));
                            String amNumber = SPUtils.getString(CardListActivity.this, "AmNumber");
                            removecard(amNumber,modetwo,accountNumber);
                                cardbangdingAdapter.notifyDataSetChanged();
                            }
                        })
                        .show();
            }
        });
    }

    @OnClick({R.id.cardlist_back, R.id.cardlist_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cardlist_back:
                Intent show = new Intent(CardListActivity.this, MainActivity.class);
                show.putExtra("grxx", 4);
                startActivity(show);
                finish();
                break;
            case R.id.cardlist_btn:
                startActivity(new Intent(this, BangXinCardActivity.class));
                break;
        }
    }

    private void initView() {
        cardbangdingAdapter = new CardbangdingAdapter(this, list);
        cardlistLvList.setAdapter(cardbangdingAdapter);
    }

    private void getcard(String amNumber,int mode) {
        Map<String, Object> map = new HashMap<>();
        map.put("amNumber",amNumber);
        map.put("mode", mode);
        Log.e("+++map+++", map + "");
        XUtil.Post(Config.AMBandCreditServlet_URL, map, new MyCallBack<String>() {

            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                Log.e("+++++result+++++",result);
                cardBean = JSON.parseObject(result, CardBean.class);

                if ("99".equals(cardBean.getCode())){
                    MyApp.getInstance().showToast(cardBean.getFailMessage());
                }else{
                    if (list!=null&&list.size()>0){
                        list.clear();
                    }
                    if(cardBean.getList() == null || cardBean.getList().size() == 0){
                        list = new ArrayList<CardBean.ListBean>();
                    }else{
                        list=cardBean.getList();
                    }
                    cardbangdingAdapter = new CardbangdingAdapter(CardListActivity.this, list);
                    cardlistLvList.setAdapter(cardbangdingAdapter);
                }
            }
        });

    }

    private void removecard(String amNumber,int mode,String accountNumber){
        Map<String, Object> map = new HashMap<>();
        map.put("amNumber",amNumber);
        map.put("mode", mode);
        map.put("accountNumber", accountNumber);

        Log.e("+++map+++", map + "");
        XUtil.Post(Config.AMBandCreditServlet_URL, map, new MyCallBack<String>() {

            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                cardBean = JSON.parseObject(result, CardBean.class);
                if ("99".equals(cardBean.getCode())){
                    MyApp.getInstance().showToast(cardBean.getFailMessage());
                }else if ("00".equals(cardBean.getCode())){
                    MyApp.getInstance().showToast(cardBean.getSuccessMessage());


                    String amNumber = SPUtils.getString(CardListActivity.this, "AmNumber");

                    getcard(amNumber,modeone);

                }
            }
        });
    }

}
