package com.futeng.happypays.activity.mposgather;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.HashMap;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import com.alibaba.fastjson.JSON;
import com.futeng.happypays.R;
import com.futeng.happypays.activity.MainActivity;
import com.futeng.happypays.activity.boothActivity;
import com.futeng.happypays.adapter.SnbangdingAdapter;
import com.futeng.happypays.tools.Config;
import com.futeng.happypays.tools.MyApp;
import com.futeng.happypays.tools.MyCallBack;
import com.futeng.happypays.tools.SPUtils;
import com.futeng.happypays.tools.XUtil;
import com.futeng.happypays.utils.SnBangdingBean;
import com.futeng.happypays.utils.SnBean;
import com.mylhyl.circledialog.CircleDialog;
import com.mylhyl.circledialog.callback.ConfigInput;
import com.mylhyl.circledialog.params.InputParams;
import com.mylhyl.circledialog.view.listener.OnInputClickListener;


public class BbposListActivity extends FragmentActivity {


    @InjectView(R.id.bbpos_back)
    ImageView bbposBack;
    @InjectView(R.id.sn_listview)
    ListView snListview;
    @InjectView(R.id.tianjia_bpos)
    LinearLayout tianjiaBpos;


    private SnBean snBean = null;
    private SnbangdingAdapter snbangdingAdapter = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_bbpos_list);
        ButterKnife.inject(this);

        Intent intent = new Intent();
        intent.putExtra("AmNumber", SPUtils.getString(BbposListActivity.this, "AmNumber"));
        String amNumber = SPUtils.getString(BbposListActivity.this, "AmNumber");

        getsn(amNumber,2+"");

        snListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                SPUtils.put(getApplicationContext(),"ListItemMacsn",snBean.getList().get(position).getMacSn());
                SPUtils.put(getApplicationContext(),"ListMacNumber",snBean.getList().get(position).getMacNumber());
                SPUtils.put(getApplicationContext(),"macSerial",snBean.getList().get(position).getMacSerial());
                //startActivity(new Intent(BbposListActivity.this,PutongsActivity.class));
                startActivity(new Intent(BbposListActivity.this,boothActivity.class));

            }
        });
    }

    private void initView() {
        snbangdingAdapter = new SnbangdingAdapter(this, snBean.getList());
        snListview.setAdapter(snbangdingAdapter);
    }
    private void getsn(String amNumber,String mode) {
        Map<String, Object> map = new HashMap<>();
        map.put("amNumber", amNumber);
        map.put("mode",mode);
        Log.e("+++map+++", map + "");
        XUtil.Post(Config.BindServlet__URL, map, new MyCallBack<String>() {

            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                snBean = JSON.parseObject(result, SnBean.class);
                if(snBean.getList()==null){
                    return ;
                }

                MyApp.getInstance().showToast(snBean.getMessage());
                Log.e("获取SN+++", snBean.getMessage());
                initView();
            }
        });
    }

    @OnClick({R.id.tianjia_bpos, R.id.bbpos_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tianjia_bpos:
                new CircleDialog.Builder(this)
                        .setCanceledOnTouchOutside(false)
                        .setCancelable(true)
                        .setTitle("绑定SN")
                        .setInputHint("请输入SN号")
                        .setInputHeight(120)
                        .configInput(new ConfigInput() {
                            @Override
                            public void onConfig(InputParams params) {
                                //params.inputBackgroundResourceId = R.drawable.bg_input;
                            }
                        })
                        .setNegative("取消", null)
                        .setPositiveInput("确定", new OnInputClickListener() {
                            @Override
                            public void onClick(String macSn, View v) {

                                Intent intent = new Intent();
                                intent.putExtra("AmNumber", SPUtils.getString(BbposListActivity.this, "AmNumber"));
                                String amNumber = SPUtils.getString(BbposListActivity.this, "AmNumber");

                                snbangding(amNumber,macSn);

                            }
                        }).show();
                break;
            case R.id.bbpos_back:
                Intent show = new Intent(BbposListActivity.this, MainActivity.class);
                show.putExtra("grxx", 3);
                startActivity(show);
                finish();
                break;
        }
    }

    private void snbangding(String amNumber,String macSn){
        Map<String,Object>map=new HashMap<>();
        map.put("amNumber",amNumber);
        map.put("macSn",macSn);

        Log.e("amNumber+macSn",map+"");

        XUtil.Post(Config.BindServlet__URL,map,new MyCallBack<String>(){

            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);

                SnBangdingBean snBangdingBean=JSON.parseObject(result,SnBangdingBean.class);

                if ("99".equals(snBangdingBean.getCode())){
                    MyApp.getInstance().showToast(snBangdingBean.getFailMessage());
                }else{
                    MyApp.getInstance().showToast(snBangdingBean.getMessage());

                    Intent intent = new Intent();
                    intent.putExtra("AmNumber", SPUtils.getString(BbposListActivity.this, "AmNumber"));
                    String amNumber = SPUtils.getString(BbposListActivity.this, "AmNumber");
                    getsn(amNumber,2+"");
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
                MyApp.getInstance().showToast("网络异常。");
            }
        });
    }
}