package com.futeng.happypays.activity.erweimashoukuan;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.futeng.happypays.R;
import com.futeng.happypays.activity.UpWaterTicketActivity;
import com.futeng.happypays.activity.aaa.CommonAdapter;
import com.futeng.happypays.activity.aaa.ViewHolder;
import com.futeng.happypays.activity.fgmentfour.BangXinCardActivity;
import com.futeng.happypays.activity.nocard.SdkeasyActivity;
import com.futeng.happypays.activity.nocard.WukaPayActivity;
import com.futeng.happypays.activity.webview.WebviewNoCard;
import com.futeng.happypays.tools.Base64;
import com.futeng.happypays.tools.Config;
import com.futeng.happypays.tools.DialogLogout;
import com.futeng.happypays.tools.MyApp;
import com.futeng.happypays.tools.MyCallBack;
import com.futeng.happypays.tools.SPUtils;
import com.futeng.happypays.tools.TimerButton;
import com.futeng.happypays.tools.XUtil;
import com.futeng.happypays.utils.GettokenBean;
import com.futeng.happypays.utils.NoCardBean;
import com.futeng.happypays.utils.SelectBankcardBean;
import com.mylhyl.circledialog.CircleDialog;
import com.mylhyl.circledialog.params.ProgressParams;

import static com.futeng.happypays.R.id.quick_ok;

public class QuickTransationActivity extends FragmentActivity {

    @InjectView(R.id.quicktrans_back)
    ImageView quicktransBack;
    @InjectView(R.id.RelativeLayout_quicktrans)
    RelativeLayout RelativeLayoutQuicktrans;
    @InjectView(R.id.quicktyi)
    ImageView quicktyi;
    @InjectView(R.id.quickdlin)
    ImageView quickdlin;
    @InjectView(R.id.quickback)
    RelativeLayout quickback;
    @InjectView(R.id.quickchild)
    ImageView quickchild;
    @InjectView(R.id.EditText_quickmoney)
    EditText EditTextQuickmoney;
    @InjectView(R.id.imageView11)
    ImageView imageView11;
    @InjectView(R.id.quick_selectcard)
    RelativeLayout quickSelectcard;
    @InjectView(R.id.trans_LinearLayout)
    LinearLayout transLinearLayout;
    @InjectView(R.id.quick_bankcard)
    TextView quickBankcard;
    @InjectView(R.id.quick_tel)
    EditText quickTel;
    @InjectView(R.id.quick_id)
    EditText quickId;
    @InjectView(R.id.LinearLayout_one)
    LinearLayout LinearLayoutOne;
    @InjectView(R.id.EditText_yzm)
    EditText EditTextYzm;

    @InjectView(R.id.yzm_linear)
    LinearLayout yzmLinear;
    @InjectView(quick_ok)
    TextView quickOk;

    String quicktype = "T+1";
    int mcc;

    private String accountNumber;
    private String accountName;
    private String showbank;

    private DialogFragment dialogFragment;
    private TimerButton mFindCodeBtn;

    boolean i;
    @InjectView(R.id.selectshowbank)
    TextView selectshowbank;
    private Dialog builder;
    private CommonAdapter<SelectBankcardBean.ListBean> adapter;
    private SelectBankcardBean selectBankcardBean;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quick_transation);
        ButterKnife.inject(this);

        mFindCodeBtn = (TimerButton) findViewById(R.id.btn_yzm);

        mcc=83;

        accountNumber=SPUtils.getString(getApplication(),"AccountNumber");
        accountName=SPUtils.getString(getApplication(),"AccountName");
        showbank=SPUtils.getString(getApplication(),"AbcRemark");

        selectshowbank.setText(showbank);
        quickBankcard.setText(accountNumber);
        String amnumber=SPUtils.getString(getApplicationContext(), "AmNumber");
        selectbankcard(amnumber);

        EditTextQuickmoney.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                String temp = s.toString();
                int posDot = temp.indexOf(".");
                if (posDot <= 0) return;
                if (temp.length() - posDot - 1 > 2) {
                    s.delete(posDot + 3, posDot + 4);
                } else {
                    return;
                }
            }
        });


    }

    @OnClick({R.id.quicktrans_back, R.id.RelativeLayout_quicktrans, R.id.quicktyi, R.id.quickdlin, R.id.quickback, R.id.quick_selectcard, R.id.btn_yzm, quick_ok})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.quicktrans_back:
                finish();
                break;
            case R.id.RelativeLayout_quicktrans:
                break;
            case R.id.quicktyi:
                break;
            case R.id.quickdlin:
                break;
            case R.id.quickback:
                Log.e("+++++++", i + "");
                if (i == false) {
                    quickback.setBackgroundResource(R.mipmap.notselectetyi);
                    quicktyi.setVisibility(View.GONE);
                    quickdlin.setVisibility(View.VISIBLE);
                    i = true;
                    quicktype = "D+0";
                    mcc=82;
                    if ("D+0".equals(quicktype)) {
                        quickchild.setBackgroundResource(R.mipmap.dling);
//                        yzmLinear.setVisibility(View.VISIBLE);
                    } else if ("T+1".equals(quicktype)) {
                        quickchild.setBackgroundResource(R.mipmap.tyi);
//                        yzmLinear.setVisibility(View.GONE);
                    }

                } else {
                    quickback.setBackgroundResource(R.mipmap.notselecteddling);
                    quicktyi.setVisibility(View.VISIBLE);
                    quickdlin.setVisibility(View.GONE);
                    i = false;
                    quicktype = "T+1";
                    mcc=83;
                    if ("D+0".equals(quicktype)) {
                        quickchild.setBackgroundResource(R.mipmap.dling);
//                        yzmLinear.setVisibility(View.VISIBLE);
                    } else if ("T+1".equals(quicktype)) {
                        quickchild.setBackgroundResource(R.mipmap.tyi);
//                        yzmLinear.setVisibility(View.GONE);
                    }
                }

                Log.e("++++++++", quicktype + "");

                break;
            case R.id.quick_selectcard:
                if (boostat==true){
                    popup.showAtLocation(QuickTransationActivity.this.getWindow().getDecorView(), Gravity.BOTTOM, 0, 0);
                    setBackgroundAlpha(0.5f);
                }
                //DialogLogout.getInstance().show(getSupportFragmentManager(), "DialogLogout");

                break;
            case R.id.btn_yzm:
                if (TextUtils.isEmpty(EditTextQuickmoney.getText())){
                    MyApp.getInstance().showToast("消费金额不能为空");
                }else if (TextUtils.isEmpty(quickTel.getText())){
                    MyApp.getInstance().showToast("手机号不能为空");
                }else if (TextUtils.isEmpty(quickId.getText())){
                    MyApp.getInstance().showToast("身份证不能为空");
                }else{
                    //判断是否开卡其余在回调处理
                    gettoken(accountNumber);
                }
                break;
            case quick_ok:
                if (TextUtils.isEmpty(EditTextQuickmoney.getText())){
                    MyApp.getInstance().showToast("消费金额不能为空");
                }else if (TextUtils.isEmpty(quickTel.getText())){
                    MyApp.getInstance().showToast("手机号不能为空");
                }else if (TextUtils.isEmpty(quickId.getText())){
                    MyApp.getInstance().showToast("身份证不能为空");
                }else{
                    if (mcc==82){
                        if (TextUtils.isEmpty(EditTextYzm.getText())){
                            MyApp.getInstance().showToast("请输入验证码");
                        }else{
                            dialogFragment = new CircleDialog.Builder(this)
                                    .setProgressText("正在交易，请稍等。。。")
                                    .setProgressStyle(ProgressParams.STYLE_SPINNER)
                                    .show();
                            SPUtils.put(getApplication(),"uptype","4-0");
                            selecttype();
                        }
                    }else if (mcc==83){
                        if (TextUtils.isEmpty(EditTextYzm.getText())){
                            MyApp.getInstance().showToast("请输入验证码");
                        }else{
                            dialogFragment = new CircleDialog.Builder(this)
                                    .setProgressText("正在交易，请稍等。。。")
//                            .setCanceledOnTouchOutside(false)
                                    .setProgressStyle(ProgressParams.STYLE_SPINNER)
                                    .show();
                            SPUtils.put(getApplication(),"uptype","4-1");
                            selecttype();
                        }
                    }else{
                        return;
                    }
                }
                break;
        }
    }
    private  boolean boostat=false;
    private void selectbankcard(String amnumber){
        Map<String,Object> map=new HashMap<>();
        map.put("mode",2);
        map.put("amNumber",amnumber);
        XUtil.Post(Config.AMBandCreditServlet_URL,map,new MyCallBack<String>(){
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                selectBankcardBean= JSON.parseObject(result,SelectBankcardBean.class);
                if ("00".equals(selectBankcardBean.getCode())){
                    if (selectBankcardBean.getList()!=null){
                        boostat=true;
                        //OK
                        setPopup();
                    }else {
                        return;
                    }
                }else {
                    MyApp.getInstance().showToast(selectBankcardBean.getMessage());
                }
            }
        });
    }


    private int  CheckedPosition;//记录位置

    private void updefaultbankcard(String cardnum,String amnumber){
        Map<String,Object> map=new HashMap<>();
        map.put("mode",4);
        map.put("amNumber",amnumber);
        map.put("accountNumber",cardnum);
        XUtil.Post(Config.AMBandCreditServlet_URL,map,new MyCallBack<String>(){
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
            }
        });
    }

    //下单获取验证码------T1
    private void sdkEasy_NoCardURL(String realName,String accountNumber,String identityCard,String phoneNo,String txtAmt,int mcc,String mercNo){
        Map<String,Object>map=new HashMap<>();
        map.put("realName",realName);
        map.put("accountNumber",accountNumber);
        map.put("identityCard",identityCard);
        map.put("phoneNo",phoneNo);
        map.put("txtAmt",txtAmt);
        map.put("mcc",mcc);
        map.put("mercNo",mercNo);
        Log.e("无卡T1获取验证码",map+"");
        XUtil.Post(Config.sdkEasy_NoCardURL,map,new MyCallBack<String>(){
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                dialogFragment.dismiss();
                GettokenBean gettokenBean= JSON.parseObject(result,GettokenBean.class);
                if ("true".equals(gettokenBean.getResultCode())){
                    SPUtils.put(getApplication(),"HtmlContent",gettokenBean.getHtmlContent());
                    startActivity(new Intent(getApplicationContext(), WebviewNoCard.class));
                }else if ("0000".equals(gettokenBean.getResultCode())){
                    SPUtils.put(getApplication(),"orderId",gettokenBean.getOrderId());
                    SPUtils.put(getApplication(),"tran37",gettokenBean.getOrderId());
                    startActivity(new Intent(getApplicationContext(),SdkeasyActivity.class));
                }else{
                    MyApp.getInstance().showToast(gettokenBean.getMessage());
                }
            }
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
                dialogFragment.dismiss();
            }
        });
    }

    //-----D0判断开卡-----
    private void gettoken(final String accountNumber){
        Map<String,Object> map=new HashMap<>();
        map.put("accountNumber",accountNumber);

        XUtil.Post(Config.getToken_NoCardURL,map,new MyCallBack<String>(){

            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                final GettokenBean gettokenBean= JSON.parseObject(result,GettokenBean.class);
                if ("0000".equals(gettokenBean.getResultCode())){
                    //已开通快捷业务直接进去消费接口
                    //存类型，来加载对应的小票图片
                    //获取sim码
//                    SPUtils.put(getApplicationContext(), "uptype", "4-0");
                    String money = EditTextQuickmoney.getText().toString();
                    String phone = quickTel.getText().toString();
                    String cerdNo = quickId.getText().toString();

                    //获取登入商户号赋值
                    String mercNo = SPUtils.getString(getApplication(), "AmNumber");
                    //获取姓名
                    String accountName1 = accountName;
                    //utf-8转码
                    String accountName = Base64.encodeBytes(accountName1.getBytes());
                    //获取银行卡卡号
                    String bankCard = accountNumber;

                    if (TextUtils.isEmpty(EditTextQuickmoney.getText())) {
                        Toast.makeText(QuickTransationActivity.this, "交易金额不能为空", Toast.LENGTH_SHORT).show();
                        return;
                    } else if (TextUtils.isEmpty(quickTel.getText())) {
                        Toast.makeText(QuickTransationActivity.this, "手机号不能为空", Toast.LENGTH_SHORT).show();
                        return;
                    } else if (TextUtils.isEmpty(quickId.getText())) {
                        Toast.makeText(QuickTransationActivity.this, "身份证不能为空", Toast.LENGTH_SHORT).show();
                        return;
                    } else {
                        nocard(cerdNo, money, phone, mercNo, mcc, bankCard);
                        mFindCodeBtn.setCallback(new TimerButton.Callback() {
                            @Override
                            public String getTickerText() {
                                return "已确认(%ds)";
                            }
                            @Override
                            public int getMaxTime() {
                                return 60;
                            }
                        });
                        mFindCodeBtn.start();
                    }


                } else if ("true".equals(gettokenBean.getResultCode())){
                    new CircleDialog.Builder(QuickTransationActivity.this)
                            .setCanceledOnTouchOutside(false)
                            .setCancelable(false)
                            .setTitle("此卡暂未开通")
                            .setText("是否开通银联快捷无卡业务")
                            .setNegative("取消", null)
                            .setPositive("确定", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    SPUtils.put(getApplicationContext(),"HtmlContent",gettokenBean.getHtmlContent());
                                    //未开通快捷业务，跳转html
                                    startActivity(new Intent(getApplicationContext(),WebviewNoCard.class));
                                }
                            })
                            .show();
                }else {
                    MyApp.getInstance().showToast(gettokenBean.getMessage());
                }
            }
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
                MyApp.getInstance().showToast("网络异常!");
            }
        });
    }

    //-----D0消费-----

    //获取SIM码请求
    private void nocard(String cerdNo, String money, String phone,
                        String mercNo, int mcc, String bankCard) {
        Map<String, Object> map = new HashMap<>();
        map.put("identityCard", cerdNo);//身份证号
        map.put("txnAmt", money);//金额
        map.put("phoneNo", phone);//手机号
        map.put("mercNo", mercNo);//商户号
        map.put("mcc", mcc);//mcc
        map.put("accountNumber", bankCard);//卡号

        Log.e("无卡map+++", map + "");

        XUtil.Post(Config.getSmsCode_NoCardURL, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                NoCardBean nocardbean = JSON.parseObject(result, NoCardBean.class);
                if ("0000".equals(nocardbean.getResultCode())) {
                    SPUtils.put(getApplication(),"orderId",nocardbean.getOrderId());
                    MyApp.getInstance().showToast(nocardbean.getMessage());
                } else {
                    MyApp.getInstance().showToast(nocardbean.getMessage());
                }
            }
        });
    }

    //无卡交易消费请求
    private void wukaok(String orderId, String smsCode) {
        Map<String, Object> map = new HashMap<>();
        map.put("orderId", orderId);
        map.put("smsCode", smsCode);

        XUtil.Post(Config.bcconServlet_NoCardURL, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                NoCardBean nocardbean = JSON.parseObject(result, NoCardBean.class);
                MyApp.getInstance().showToast(nocardbean.getMessage());
                SPUtils.put(getApplicationContext(), "orderNo", nocardbean.getOrderId());

                //存订单号，来加载对应的小票图片
                SPUtils.put(getApplication(),"tran37",nocardbean.getOrderId());

                if ("0000".equals(nocardbean.getResultCode())) {
                    //交易成功发起查询
                    new Thread() {
                        public void run() {
                            try {
                                sleep(2000);
                                findwuka(SPUtils.getString(getApplication(),"orderNo"));
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }.start();
                }else{
                    //交易失败弹框取消
                    dialogFragment.dismiss();
                    MyApp.getInstance().showToast(nocardbean.getMessage());
                }
            }
        });
    }
    
    //无卡交易查询
    private void findwuka(String orderId ){
        Map<String,Object>map=new HashMap<>();
        map.put("orderId",orderId );
        Log.e("+++orderId ++++",map+"");
        XUtil.Post(Config.query_NoCardURL,map,new MyCallBack<String>(){
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                NoCardBean noCardOkBean=JSON.parseObject(result,NoCardBean.class);
                if ("0000".equals(noCardOkBean.getResultCode())){
                    //查询成功 弹框取消 跳转上传小票界面

                    //存金额传给电子小票
                    SPUtils.put(getApplicationContext(), "upmoney", EditTextQuickmoney.getText().toString());
                    //存时间传给电子小票
                    long end = System.currentTimeMillis();
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String hms1 = formatter.format(end);
                    SPUtils.put(getApplicationContext(), "uptime", hms1);

                    dialogFragment.dismiss();
                    startActivity(new Intent(getApplicationContext(), UpWaterTicketActivity.class));

                }else {
                    //查询失败 弹框取消
                    dialogFragment.dismiss();
                    MyApp.getInstance().showToast(noCardOkBean.getMessage());
                }
            }
        });
    }

    private void selecttype(){
        if (mcc==83){
//            String accountNumber1=accountNumber;
//            String realName=accountName;
//            String mercNo=SPUtils.getString(getApplicationContext(), "AmNumber");
//            String identityCard=quickId.getText().toString();
//            SPUtils.put(getApplication(),"identityCard",identityCard);
//
//            String phoneNo=quickTel.getText().toString();
//            SPUtils.put(getApplication(),"phoneNo",phoneNo);
//
//            String txtAmt=EditTextQuickmoney.getText().toString();
//            SPUtils.put(getApplication(),"txtAmt",txtAmt);
//            SPUtils.put(getApplication(),"upmoney",txtAmt);
//            sdkEasy_NoCardURL(realName,accountNumber1,identityCard,phoneNo,txtAmt,mcc,mercNo);

            String orderId = SPUtils.getString(this, "orderId");
            String smsCode=EditTextYzm.getText().toString();
            //无卡消费请求
            wukaok(orderId,smsCode);
        }else if (mcc==82){

            String orderId = SPUtils.getString(this, "orderId");
            String smsCode=EditTextYzm.getText().toString();
            //无卡消费请求
            wukaok(orderId,smsCode);
            
        }else{
            return;
        }
    }


    private PopupWindow popup;
    private View popupView;
    private ListView selectbankcard_lv;
    private boolean boo=true;
    private SparseBooleanArray mSelectArray;
    private  void setPopup(){
        mSelectArray=new SparseBooleanArray();;//储存boolean类型pair(key,value)
        popupView=LayoutInflater.from(QuickTransationActivity.this).inflate(R.layout.dialog_logout,null);
        selectbankcard_lv= (ListView) popupView.findViewById(R.id.selectbankcard_lv);
        popup = new PopupWindow(popupView, ViewGroup.LayoutParams.MATCH_PARENT, (int) (setScreenHeight() / 1.5), true);
        popup.setTouchable(true);
        popup.setFocusable(true);
        popup.setOutsideTouchable(true);
        popup.setBackgroundDrawable(new BitmapDrawable());
        popup.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                setBackgroundAlpha(1f);
            }
        });
        popupView.findViewById(R.id.close_selectcard).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popup.dismiss();
            }
        });
        popupView.findViewById(R.id.add_card).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(QuickTransationActivity.this, AddselectActivity.class));
            }
        });

        adapter=new CommonAdapter<SelectBankcardBean.ListBean>(QuickTransationActivity.this,selectBankcardBean.getList()
                ,R.layout.selectbank_item) {
            @Override
            public void convert(final ViewHolder mHolder, final SelectBankcardBean.ListBean item, final int position) {
                mHolder.setText(R.id.selectbank_name,item.getAbcRemark());
                mHolder.setImageByUrl(mHolder.setImageView(R.id.selectbank_img)
                        ,item.getAbcPicUrl()
                        ,QuickTransationActivity.this);
                if (item.getAbcDefaultNum()==1&&boo==true){
                    mHolder.setViewCheckBox(R.id.quan_default).setChecked(true);
                }else {
                    mHolder.setViewCheckBox(R.id.quan_default).setChecked(mSelectArray.get(position, false));
                }
                mHolder.setonClick(R.id.quan_default, new View.OnClickListener() {
                    String amnumber=SPUtils.getString(getApplication(),"AmNumber");
                    @Override
                    public void onClick(View v) {
                        updefaultbankcard(item.getAccountNumber(),amnumber);
                        boo=false;
                        mSelectArray.clear();
                        CheckedPosition=position;
                        mSelectArray.put(position,mHolder.setViewCheckBox(R.id.quan_default).isChecked());
                        notifyDataSetChanged();
                    }
                });
            }
        };
        selectbankcard_lv.setAdapter(adapter);
        selectbankcard_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                showbank=selectBankcardBean.getList().get(position).getAbcRemark();
                accountNumber=selectBankcardBean.getList().get(position).getAccountNumber();
                accountName=selectBankcardBean.getList().get(position).getAccountName();

                selectshowbank.setText(showbank);
                quickBankcard.setText(accountNumber);
                popup.dismiss();
            }
        });
    }



    /*
       * 设置添加屏幕的背景透明度
      *
              * @param bgAlpha
      *            屏幕透明度0.0-1.0 1表示完全不透明
      */
    private void setBackgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = this.getWindow()
                .getAttributes();
        lp.alpha = bgAlpha;
        if (bgAlpha == 1) {
            this.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);//不移除该Flag的话，在有视频的页面上的视频会出现黑屏的BUG
        } else {
            this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);//此行代码主要是解决在华为手机上的白透明效果无效BUG
        }
        this.getWindow().setAttributes(lp);
    }

    private int setScreenHeight() {
        WindowManager wm = this.getWindowManager();
        int width = wm.getDefaultDisplay().getWidth();
        int height = wm.getDefaultDisplay().getHeight();
        return height;
    }

}
