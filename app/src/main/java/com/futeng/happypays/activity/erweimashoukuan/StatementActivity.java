package com.futeng.happypays.activity.erweimashoukuan;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import com.futeng.happypays.R;
import com.futeng.happypays.activity.kit.DateUtils;
import com.futeng.happypays.activity.kit.MTools;
import com.futeng.happypays.activity.kit.TransactionResult;
import com.futeng.happypays.activity.macaadapter.MRcvChartHorzAdapter;
import com.futeng.happypays.activity.macaadapter.MRcvChartLeftAdapter;
import com.futeng.happypays.activity.macaadapter.MRcvPayTypeAdapter;
import com.futeng.happypays.tools.MyApp;
import com.futeng.happypays.tools.SPUtils;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import okhttp3.Call;

public class StatementActivity extends Activity {

    @InjectView(R.id.statement_back)
    ImageView statementBack;
    @InjectView(R.id.selectedday)
    ImageView selectedday;
    @InjectView(R.id.selectedmonth)
    ImageView selectedmonth;
    @InjectView(R.id.date_bg)
    RelativeLayout dateBg;
    @InjectView(R.id.beforeday)
    ImageView beforeday;
    @InjectView(R.id.date)
    TextView date;
    @InjectView(R.id.afterday)
    ImageView afterday;
    @InjectView(R.id.deal_num)
    TextView dealNum;
    @InjectView(R.id.deal_money)
    TextView dealMoney;

    @InjectView(R.id.activity_statement_rcv_money)
    RecyclerView activityStatementRcvMoney;
    @InjectView(R.id.activity_statement_rcv_count)
    RecyclerView activityStatementRcvCount;
    @InjectView(R.id.activity_statement_rcv_money_left)
    RecyclerView activityStatementRcvMoneyLeft;
    @InjectView(R.id.activity_statement_rcv_count_left)
    RecyclerView activityStatementRcvCountLeft;
    @InjectView(R.id.activity_statement_rcv_paytype)
    RecyclerView activityStatementRcvPaytype;

    boolean isSelectMonth;

    private DateUtils.MacaDateBean macaDateBean;
    private MRcvChartHorzAdapter mMoneyRcvChartHorzAdapter, mCountRcvChartHorzAdapter;
    private MRcvChartLeftAdapter mMoneyRcvChartLeftAdapter, mCountRcvChartLeftAdapter;
    private MRcvPayTypeAdapter mRcvPayTypeAdapter;
    private double maxMoney = 0.0;
    private int maxCount = 0;

    private String amnumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statement);
        ButterKnife.inject(this);
        amnumber= SPUtils.getString(getApplication(),"AmNumber");
        macaDateBean = DateUtils.getThisDateOfObject();
        date.setText(macaDateBean.getYMDStr());
        getData(amnumber,macaDateBean.getYMDStr());
    }

    @OnClick({R.id.statement_back, R.id.date_bg, R.id.beforeday, R.id.date, R.id.afterday})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.statement_back:
                finish();
                break;
            case R.id.date_bg:
                if (isSelectMonth==false){
                    selectedday.setVisibility(View.GONE);
                    selectedmonth.setVisibility(View.VISIBLE);
                    beforeday.setBackgroundResource(R.mipmap.beforemonth);
                    afterday.setBackgroundResource(R.mipmap.aftermonth);

                    isSelectMonth=true;
                }else{
                    selectedday.setVisibility(View.VISIBLE);
                    selectedmonth.setVisibility(View.GONE);
                    beforeday.setBackgroundResource(R.mipmap.beforeday);
                    afterday.setBackgroundResource(R.mipmap.afterday);

                    isSelectMonth=false;
                }

                break;
            case R.id.beforeday:

                if (isSelectMonth == false) {
                    macaDateBean = DateUtils.getLastNextDate(macaDateBean, -1);
                    date.setText(macaDateBean.getYMDStr());
                    getData(amnumber,macaDateBean.getYMDStr());
                } else {
                    macaDateBean = DateUtils.getLastNextMonth(macaDateBean, -1);
                    date.setText(macaDateBean.getYMDStr());
                    getData(amnumber,macaDateBean.getYMDStr());
                }

                break;
            case R.id.date:

                DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                macaDateBean.setYear(year);
                                macaDateBean.setMonth(month + 1);
                                macaDateBean.setDay(dayOfMonth);
                                date.setText(macaDateBean.getYMDStr());
                                getData(amnumber,macaDateBean.getYMDStr());
                            }
                        },
                        macaDateBean.getYear(), macaDateBean.getMonth() - 1, macaDateBean.getDay());
                datePickerDialog.show();

                break;
            case R.id.afterday:

                if (isSelectMonth == false) {
                    macaDateBean = DateUtils.getLastNextDate(macaDateBean, +1);
                    date.setText(macaDateBean.getYMDStr());

                    getData(amnumber,macaDateBean.getYMDStr());
                } else {
                    macaDateBean = DateUtils.getLastNextMonth(macaDateBean, +1);
                    date.setText(macaDateBean.getYMDStr());
                    getData(amnumber,macaDateBean.getYMDStr());
                }

                break;
        }
    }

    private void setChartBar(TransactionResult transactionResult) {
        //45对应---微信T1 ; 47对应---支付宝T1
        //83对应---无卡快捷T1 ;  84对应---5W无卡支付T1
        // 46对应---微信D0 ;   ;  47对应---支付宝D0  ;
        // 82对应---无卡快捷D0 ; 81对应---2W无卡支付T1;
        List<TransactionResult.MapBean.PayTypeBean> payTypeBeanList = new ArrayList<>();
        if (transactionResult.getMap().get_$45() != null) {
            payTypeBeanList.add(transactionResult.getMap().get_$45());
        } else {
            TransactionResult.MapBean.PayTypeBean payTypeBean = new TransactionResult.MapBean.PayTypeBean();
            payTypeBean.setMcc(45);
            payTypeBean.setMccName("微信T1");
            payTypeBean.setCount(0);
            payTypeBean.setMoney(0);
            payTypeBeanList.add(payTypeBean);
        }
        if (transactionResult.getMap().get_$46() != null) {
            payTypeBeanList.add(transactionResult.getMap().get_$46());
        } else {
            TransactionResult.MapBean.PayTypeBean payTypeBean = new TransactionResult.MapBean.PayTypeBean();
            payTypeBean.setMcc(46);
            payTypeBean.setMccName("微信D0");
            payTypeBean.setCount(0);
            payTypeBean.setMoney(0);
            payTypeBeanList.add(payTypeBean);
        }

        if (transactionResult.getMap().get_$47() != null) {
            payTypeBeanList.add(transactionResult.getMap().get_$47());
        } else {
            TransactionResult.MapBean.PayTypeBean payTypeBean = new TransactionResult.MapBean.PayTypeBean();
            payTypeBean.setMcc(47);
            payTypeBean.setMccName("支付宝T1");
            payTypeBean.setCount(0);
            payTypeBean.setMoney(0);
            payTypeBeanList.add(payTypeBean);
        }

        if (transactionResult.getMap().get_$47() != null) {
            payTypeBeanList.add(transactionResult.getMap().get_$47());
        } else {
            TransactionResult.MapBean.PayTypeBean payTypeBean = new TransactionResult.MapBean.PayTypeBean();
            payTypeBean.setMcc(47);
            payTypeBean.setMccName("支付宝D0");
            payTypeBean.setCount(0);
            payTypeBean.setMoney(0);
            payTypeBeanList.add(payTypeBean);
        }

        if (transactionResult.getMap().get_$83() != null) {
            payTypeBeanList.add(transactionResult.getMap().get_$83());
        } else {
            TransactionResult.MapBean.PayTypeBean payTypeBean = new TransactionResult.MapBean.PayTypeBean();
            payTypeBean.setMcc(83);
            payTypeBean.setMccName("无卡快捷T1");
            payTypeBean.setCount(0);
            payTypeBean.setMoney(0);
            payTypeBeanList.add(payTypeBean);
        }


        if (transactionResult.getMap().get_$82() != null) {
            payTypeBeanList.add(transactionResult.getMap().get_$82());
        } else {
            TransactionResult.MapBean.PayTypeBean payTypeBean = new TransactionResult.MapBean.PayTypeBean();
            payTypeBean.setMcc(82);
            payTypeBean.setMccName("无卡快捷D0");
            payTypeBean.setCount(0);
            payTypeBean.setMoney(0);
            payTypeBeanList.add(payTypeBean);
        }

        if (transactionResult.getMap().get_$84() != null) {
            payTypeBeanList.add(transactionResult.getMap().get_$84());
        } else {
            TransactionResult.MapBean.PayTypeBean payTypeBean = new TransactionResult.MapBean.PayTypeBean();
            payTypeBean.setMcc(84);
            payTypeBean.setMccName("5W无卡支付T1");
            payTypeBean.setCount(0);
            payTypeBean.setMoney(0);
            payTypeBeanList.add(payTypeBean);
        }


        if (transactionResult.getMap().get_$81() != null) {
            payTypeBeanList.add(transactionResult.getMap().get_$81());
        } else {
            TransactionResult.MapBean.PayTypeBean payTypeBean = new TransactionResult.MapBean.PayTypeBean();
            payTypeBean.setMcc(81);
            payTypeBean.setMccName("2W无卡支付T1");
            payTypeBean.setCount(0);
            payTypeBean.setMoney(0);
            payTypeBeanList.add(payTypeBean);
        }
        //价格支付类型
        mRcvPayTypeAdapter = new MRcvPayTypeAdapter(this);
        activityStatementRcvPaytype.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.HORIZONTAL));
        mRcvPayTypeAdapter.setData(payTypeBeanList);
        activityStatementRcvPaytype.setAdapter(mRcvPayTypeAdapter);

        maxCount = -1;
        maxMoney = -1;
        dealNum.setText(transactionResult.getMap().get_$99().getCount() + "");
        dealMoney.setText(transactionResult.getMap().get_$99().getMoney() + "");
        if (transactionResult.getList() == null || transactionResult.getList().size() == 0) {
            transactionResult.setList(new ArrayList<TransactionResult.ListBean>());
            for (int i = 0; i < DateUtils.getDaysOfMonth(macaDateBean.getYear(), macaDateBean.getMonth()); i++) {
                TransactionResult.ListBean dataBean = new TransactionResult.ListBean();
                dataBean.setCount(0);
                dataBean.setMoney(0);
                dataBean.setTranTime(macaDateBean.getYearStr() + "" + macaDateBean.getMonthStr() + ((i + 1) < 10 ? "0" + (i + 1) : (i + 1)));
                transactionResult.getList().add(dataBean);
            }
        }
        for (int i = 0; i < transactionResult.getList().size(); i++) {
            if (transactionResult.getList().get(i).getMoney() > maxMoney)
                maxMoney = transactionResult.getList().get(i).getMoney();
            if (transactionResult.getList().get(i).getCount() > maxCount)
                maxCount = transactionResult.getList().get(i).getCount();
        }
        if (maxMoney==0){
            maxMoney=10000;
        }
        if (maxCount==0){
            maxCount=100;
        }
        //价格柱状图
        mMoneyRcvChartHorzAdapter = new MRcvChartHorzAdapter(this, maxMoney, 0);
        activityStatementRcvMoney.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL));
        mMoneyRcvChartHorzAdapter.setData(transactionResult.getList());
        activityStatementRcvMoney.setAdapter(mMoneyRcvChartHorzAdapter);
        mMoneyRcvChartHorzAdapter.setThisTime(macaDateBean.getYMDStr());
        //价格标尺
        List<String> moneyLeftList = new ArrayList<>();
        for (int i = 7; i >= 0; i--) {
            if (i == 0)
                moneyLeftList.add("0");
            else
                moneyLeftList.add(MTools.getDoubleTwo((maxMoney / 70000) * i) + "万");
        }
        mMoneyRcvChartLeftAdapter = new MRcvChartLeftAdapter(this);
        activityStatementRcvMoneyLeft.setLayoutManager(new LinearLayoutManager(this));
        mMoneyRcvChartLeftAdapter.setData(moneyLeftList);
        activityStatementRcvMoneyLeft.setAdapter(mMoneyRcvChartLeftAdapter);
        //数量
        mCountRcvChartHorzAdapter = new MRcvChartHorzAdapter(this, maxCount, 1);
        activityStatementRcvCount.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL));
        mCountRcvChartHorzAdapter.setData(transactionResult.getList());
        activityStatementRcvCount.setAdapter(mCountRcvChartHorzAdapter);
        mCountRcvChartHorzAdapter.setThisTime(macaDateBean.getYMDStr());
        //数量标尺
        List<String> countLeftList = new ArrayList<>();
        for (int i = 7; i >= 0; i--) {
            countLeftList.add(((int) (MTools.getDoubleTwo((maxCount / 7.0) * i))) + "");
        }
        mCountRcvChartLeftAdapter = new MRcvChartLeftAdapter(this);
        activityStatementRcvCountLeft.setLayoutManager(new LinearLayoutManager(this));
        mCountRcvChartLeftAdapter.setData(countLeftList);
        activityStatementRcvCountLeft.setAdapter(mCountRcvChartLeftAdapter);
    }

    private void getData(String amnumber,String date) {
        OkHttpUtils
                .post()
                .url("http://101.37.168.236:8081/PhonePOSPInterface/StatisticsServlet")
                .addParams("mode", "1")
                .addParams("amNumber", amnumber)
                .addParams("tranTime", date)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("parseNetworkResponse", response);
                        TransactionResult transactionResult = new Gson().fromJson(response, TransactionResult.class);
                        setChartBar(transactionResult);
                    }
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        MyApp.getInstance().showToast("您的网络有问题,请稍后重试!");
                    }
                });
    }
}
