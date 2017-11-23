package com.futeng.happypays.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.futeng.happypays.R;
import com.futeng.happypays.activity.CalculateActivity;
import com.futeng.happypays.activity.ExchangerateActivity;
import com.futeng.happypays.activity.allgridview.EastDaiActivity;
import com.futeng.happypays.activity.erweimashoukuan.ApplyCardActivity;
import com.futeng.happypays.activity.erweimashoukuan.StatementActivity;
import com.futeng.happypays.activity.erweimashoukuan.StoreActivity;
import com.futeng.happypays.activity.sign.SignActivity;
import com.futeng.happypays.tools.Config;
import com.futeng.happypays.tools.MyApp;
import com.futeng.happypays.tools.MyCallBack;
import com.futeng.happypays.tools.MyDialog;
import com.futeng.happypays.tools.SPUtils;
import com.futeng.happypays.tools.XUtil;
import com.futeng.happypays.utils.JifenBean;
import com.futeng.happypays.utils.JifenguizeBean;
import com.futeng.happypays.utils.RealtimeBean;
import com.futeng.happypays.utils.RealtimenextBean;
import com.futeng.happypays.utils.RedPacketBean;
import com.futeng.happypays.utils.WalletBean;
import com.mylhyl.circledialog.CircleDialog;
import com.mylhyl.circledialog.callback.ConfigInput;
import com.mylhyl.circledialog.params.InputParams;
import com.mylhyl.circledialog.view.listener.OnInputClickListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class FirstFragments extends Fragment {

    @InjectView(R.id.bank_integral)
    RelativeLayout bankIntegral;
    @InjectView(R.id.bank_card)
    RelativeLayout bankCard;
    @InjectView(R.id.bank_loans)
    RelativeLayout bankLoans;
    @InjectView(R.id.bank_statement)
    RelativeLayout bankStatement;
    @InjectView(R.id.RelativeLayout_calculate)
    RelativeLayout RelativeLayoutCalculate;
    @InjectView(R.id.RelativeLayout_exchange)
    RelativeLayout RelativeLayoutExchange;
    @InjectView(R.id.toDaySumTranMoeny)
    TextView toDaySumTranMoeny;
    @InjectView(R.id.toDayActExaMoney)
    TextView toDayActExaMoney;
    @InjectView(R.id.accountMoney)
    TextView accountMoney;
    @InjectView(R.id.sumTranMoney)
    TextView sumTranMoney;
    @InjectView(R.id.allCount)
    TextView allCount;
    @InjectView(R.id.RelativeLayout_sign)
    RelativeLayout RelativeLayoutSign;
    @InjectView(R.id.RelativeLayout_store)
    RelativeLayout RelativeLayoutStore;
    private View mView;
    private ViewPager mViewPaper;
    private List<ImageView> images;
    private List<View> dots;
    private int currentItem;
    //记录上一次点的位置
    private int oldPosition = 0;
    //存放图片的id
    private int[] imageIds = new int[]{
            R.mipmap.bannerone,
            R.mipmap.bannertwo,
    };
    //存放图片的标题
    private String[] titles = new String[]{
            " ",
            " ",
    };
    private TextView title;
    private ViewPagerAdapter adapter;
    private ScheduledExecutorService scheduledExecutorService;

    private RealtimeBean realtimeBean = null;
    private RealtimenextBean realtimenextBean = null;
    private RedPacketBean redPacketBean = null;

    private int pstate;
    private WalletBean walletBean = null;

    private String rpOrderNo;
    private String rpType;
    private String rpMoney;
    private String rpId;
    private TextView get_redpacket;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.activity_first_fragments, null);
        setView();
        ButterKnife.inject(this, mView);

        pstate = SPUtils.getInt(getContext(), "pState");

        wallet(SPUtils.getString(getActivity(), "AmNumber"));

        redpacket("1", SPUtils.getString(getActivity(), "AmNumber"));

        if (pstate == 1) {
            toDaySumTranMoeny.setText(SPUtils.getString(getActivity(), "toDaySumTranMoeny"));
            toDayActExaMoney.setText(SPUtils.getString(getActivity(), "toDayActExaMoney"));
            accountMoney.setText(SPUtils.getString(getActivity(), "accountMoney"));
            sumTranMoney.setText(SPUtils.getString(getActivity(), "sumTranMoney"));
            allCount.setText(SPUtils.getString(getActivity(), "allCount"));
        } else {
            toDaySumTranMoeny.setText("0");
            toDayActExaMoney.setText("0");
            accountMoney.setText("0");
            sumTranMoney.setText("0");
            allCount.setText("0");
        }

        return mView;

    }

    private void setView() {
        mViewPaper = (ViewPager) mView.findViewById(R.id.vp);

        //显示的图片
        images = new ArrayList<ImageView>();
        for (int i = 0; i < imageIds.length; i++) {
            ImageView imageView = new ImageView(getActivity());
            imageView.setBackgroundResource(imageIds[i]);
            images.add(imageView);
        }
        //显示的小点
        dots = new ArrayList<View>();
        dots.add(mView.findViewById(R.id.dot_0));
        dots.add(mView.findViewById(R.id.dot_1));
        dots.add(mView.findViewById(R.id.dot_2));
        dots.add(mView.findViewById(R.id.dot_3));
        dots.add(mView.findViewById(R.id.dot_4));

        title = (TextView) mView.findViewById(R.id.title);
        title.setText(titles[0]);

        adapter = new ViewPagerAdapter();
        mViewPaper.setAdapter(adapter);

        mViewPaper.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {


            @Override
            public void onPageSelected(int position) {
                title.setText(titles[position]);
                dots.get(position).setBackgroundResource(R.drawable.dotshape);
                dots.get(oldPosition).setBackgroundResource(R.drawable.dotshape2);

                oldPosition = position;
                currentItem = position;
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @OnClick({R.id.bank_integral, R.id.bank_card, R.id.bank_loans, R.id.bank_statement, R.id.RelativeLayout_calculate, R.id.RelativeLayout_exchange, R.id.RelativeLayout_sign,R.id.RelativeLayout_store})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bank_integral:

                if (pstate == 1) {
                    //获取积分规则
                    String Rule = SPUtils.getString(getContext(), "rule");

                    Log.e("积分规则", Rule + "");

                    new CircleDialog.Builder(getActivity())
                            .setCanceledOnTouchOutside(false)
                            .setCancelable(true)
                            .setTitle("可提现积分:" + SPUtils.getString(getActivity(), "accountInegral") + "\n" + "提现规则:" + Rule)
                            .setInputHeight(120)
                            .setInputHint("请输入你要兑换的积分")
                            .configInput(new ConfigInput() {
                                @Override
                                public void onConfig(InputParams params) {

                                }
                            }).setNegative("取消", null)
                            .setPositiveInput("确定", new OnInputClickListener() {
                                @Override
                                public void onClick(String text, View v) {

                                    Intent intent = new Intent();
                                    intent.putExtra("AmNumber", SPUtils.getString(getContext(), "AmNumber"));
                                    String amNumber = SPUtils.getString(getContext(), "AmNumber");

                                    itsIntegral(amNumber, text, 1 + "");


                                }
                            }).show();
                } else {
                    MyApp.getInstance().showToast("您还未实名认证，请先实名认证");
                }
                break;
            case R.id.bank_card:
                startActivity(new Intent(getActivity(), ApplyCardActivity.class));
                break;
            case R.id.bank_loans:
                startActivity(new Intent(getActivity(), EastDaiActivity.class));
                break;
            case R.id.bank_statement:
                if (pstate == 2) {
                    Toast.makeText(getActivity(), "您认证资料失败，请重新认证", Toast.LENGTH_SHORT).show();
                } else if (pstate == 3) {
                    Toast.makeText(getActivity(), "您还未实名认证，请先认证", Toast.LENGTH_SHORT).show();
                } else if (pstate == 4) {
                    Toast.makeText(getActivity(), "正在审核资料中，请耐心等待", Toast.LENGTH_SHORT).show();
                } else if (pstate == 5) {
                    Toast.makeText(getActivity(), "账号已停用", Toast.LENGTH_SHORT).show();
                } else if (pstate == 1) {
                    startActivity(new Intent(getActivity(), StatementActivity.class));
                }
                break;
            case R.id.RelativeLayout_calculate:
                startActivity(new Intent(getActivity(), CalculateActivity.class));
                break;
            case R.id.RelativeLayout_exchange:
                SPUtils.put(getActivity(), "curName", "菲律宾比索");
                SPUtils.put(getActivity(), "curCode", "PHP");
                SPUtils.put(getActivity(), "curImgUrl", "http://101.37.168.236/currency/PHP.png");
                showexchange("PHP");
                break;
            case R.id.RelativeLayout_sign:
                if (pstate == 2) {
                    Toast.makeText(getActivity(), "您认证资料失败，请重新认证", Toast.LENGTH_SHORT).show();
                } else if (pstate == 3) {
                    Toast.makeText(getActivity(), "您还未实名认证，请先认证", Toast.LENGTH_SHORT).show();
                } else if (pstate == 4) {
                    Toast.makeText(getActivity(), "正在审核资料中，请耐心等待", Toast.LENGTH_SHORT).show();
                } else if (pstate == 5) {
                    Toast.makeText(getActivity(), "账号已停用", Toast.LENGTH_SHORT).show();
                } else if (pstate == 1) {
                    startActivity(new Intent(getActivity(), SignActivity.class));
                }
                break;
            case R.id.RelativeLayout_store:
                if (pstate == 2) {
                    Toast.makeText(getActivity(), "您认证资料失败，请重新认证", Toast.LENGTH_SHORT).show();
                } else if (pstate == 3) {
                    Toast.makeText(getActivity(), "您还未实名认证，请先认证", Toast.LENGTH_SHORT).show();
                } else if (pstate == 4) {
                    Toast.makeText(getActivity(), "正在审核资料中，请耐心等待", Toast.LENGTH_SHORT).show();
                } else if (pstate == 5) {
                    Toast.makeText(getActivity(), "账号已停用", Toast.LENGTH_SHORT).show();
                } else if (pstate == 1) {
                    startActivity(new Intent(getActivity(), StoreActivity.class));
                }
                break;
        }
    }



    /*定义的适配器*/
    public class ViewPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return images.size();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public void destroyItem(ViewGroup view, int position, Object object) {
//          super.destroyItem(container, position, object);
//          view.removeView(view.getChildAt(position));
//          view.removeViewAt(position);
            view.removeView(images.get(position));
        }

        @Override
        public Object instantiateItem(ViewGroup view, int position) {
            view.addView(images.get(position));
            return images.get(position);
        }

    }

    /**
     * 利用线程池定时执行动画轮播
     */
    @Override
    public void onStart() {
        super.onStart();
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleWithFixedDelay(
                new ViewPageTask(),
                2,
                2,
                TimeUnit.SECONDS);
    }

    /**
     * 图片轮播任务
     *
     * @author liuyazhuang
     */
    private class ViewPageTask implements Runnable {

        @Override
        public void run() {
            currentItem = (currentItem + 1) % imageIds.length;
            mHandler.sendEmptyMessage(0);
        }
    }

    /**
     * 接收子线程传递过来的数据
     */
    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            mViewPaper.setCurrentItem(currentItem);
        }

        ;
    };

    @Override
    public void onStop() {
        super.onStop();
        if (scheduledExecutorService != null) {
            scheduledExecutorService.shutdown();
            scheduledExecutorService = null;
        }
    }

    private void itsIntegral(String amNumber, String itsIntegral, String mode) {
        Map<String, Object> map = new HashMap<>();
        map.put("amNumber", amNumber);
        map.put("itsIntegral", itsIntegral);
        map.put("mode", mode);

        XUtil.Post(Config.IntegralWithdrawalsServlet_URL, map, new MyCallBack<String>() {

            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);

                JifenguizeBean jifenguizeBean = JSON.parseObject(result, JifenguizeBean.class);

                if ("00".equals(jifenguizeBean.getCode())) {
                    MyApp.getInstance().showToast(jifenguizeBean.getSuccessMessage());

                    Intent intent = new Intent();
                    intent.putExtra("AmNumber", SPUtils.getString(getContext(), "AmNumber"));
                    String amNumber = SPUtils.getString(getContext(), "AmNumber");

                    jifen(amNumber);
//                    //获取总金额
//                    Intent intent1 = new Intent();
//                    intent1.putExtra("actAccountMoney", SPUtils.getString(getContext(), "actAccountMoney"));
//                    fgfourAllmoney.setText(SPUtils.getString(getContext(), "actAccountMoney"));
//
//                    //获取可用金额
//                    Intent intent2 = new Intent();
//                    intent2.putExtra("accountMoney", SPUtils.getString(getContext(), "accountMoney"));
//                    fgfourUsemoney.setText(SPUtils.getString(getContext(), "accountMoney"));
//
//                    //获取总积分
//                    Intent intent3 = new Intent();
//                    intent3.putExtra("accountIntegral", SPUtils.getString(getContext(), "accountIntegral"));
//                    fgfourAlljifen.setText(SPUtils.getString(getContext(), "accountIntegral"));
//
//                    //获取可提现积分
//                    Intent intent4 = new Intent();
//                    intent4.putExtra("ableIntegral", SPUtils.getString(getContext(), "ableIntegral"));
//                    fgfourJifengtixian.setText(SPUtils.getString(getContext(), "ableIntegral"));


                } else if ("99".equals(jifenguizeBean.getCode())) {
                    MyApp.getInstance().showToast(jifenguizeBean.getFailMessage());
                }
            }
        });

    }

    private void jifen(String amNumber) {

        Map<String, Object> map = new HashMap<>();
        map.put("amNumber", amNumber);

        XUtil.Post(Config.AMAccountServlet_URL, map, new MyCallBack<String>() {

            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);

                JifenBean jifenBean = JSON.parseObject(result, JifenBean.class);

                SPUtils.put(getContext(), "accountIntegral", jifenBean.getMap().getAccountIntegral());
                SPUtils.put(getContext(), "accountMoney", jifenBean.getMap().getAccountMoney());
                SPUtils.put(getContext(), "actAccountMoney", jifenBean.getMap().getActAccountMoney());
                SPUtils.put(getContext(), "ableIntegral", jifenBean.getMap().getAbleIntegral());
            }
        });

    }

    private void showexchange(String codeOne) {
        Map<String, Object> map = new HashMap<>();
        map.put("mode", 2);
        map.put("moneyOne", 100);
        map.put("codeTwo", "CNY");
        map.put("codeOne", codeOne);

        XUtil.Post(Config.ExchangerateServlet_URL, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                realtimenextBean = JSON.parseObject(result, RealtimenextBean.class);
                if ("00".equals(realtimenextBean.getCode())) {
                    if (realtimenextBean.getMap() != null) {
                        SPUtils.put(getActivity(), "ExcValue", realtimenextBean.getMap().getExcValue());
                        Log.e("-----", realtimenextBean.getMap().getExcValue() + "");
                        startActivity(new Intent(getActivity(), ExchangerateActivity.class));
                    } else {
                        return;
                    }
                } else {
                    MyApp.getInstance().showToast("服务器正在维护请稍后!");
                }
            }
        });
    }

    private void wallet(String amnumber) {
        Map<String, Object> map = new HashMap<>();
        map.put("mode", 1);
        map.put("amNumber", amnumber);
        XUtil.Post(Config.WalletServlet_URL, map, new MyCallBack<String>() {

            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                walletBean = JSON.parseObject(result, WalletBean.class);
                if ("00".equals(walletBean.getCode()) && walletBean.getMap() != null) {
                    SPUtils.put(getActivity(), "toDayActExaMoney", walletBean.getMap().getToDayActExaMoney());
                    SPUtils.put(getActivity(), "sumTranMoney", walletBean.getMap().getSumTranMoney());
                    SPUtils.put(getActivity(), "allCount", walletBean.getMap().getAllCount());
                    SPUtils.put(getActivity(), "accountMoney", walletBean.getMap().getAccountMoney());
                    SPUtils.put(getActivity(), "toDaySumTranMoeny", walletBean.getMap().getToDaySumTranMoeny());
                    SPUtils.put(getActivity(), "accountInegral", walletBean.getMap().getAccountInegral());
                } else {
//                    MyApp.getInstance().showToast(walletBean.getMessage());
                }
            }
        });
    }

    //红包方法
    private void redpacket(String mode, String amNumber) {
        Map<String, Object> map = new HashMap<>();
        map.put("mode", mode);
        map.put("amNumber", amNumber);
        XUtil.Post(Config.RedPackageServlet_URL, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                redPacketBean = JSON.parseObject(result, RedPacketBean.class);
                if ("00".equals(redPacketBean.getCode()) && redPacketBean.getList() != null) {
                    rpOrderNo = redPacketBean.getList().get(0).getRpOrderNo();
                    rpType = redPacketBean.getList().get(0).getRpType();
                    rpMoney = redPacketBean.getList().get(0).getRpMoney();
                    rpId = redPacketBean.getList().get(0).getRpId();

                    showCustomizeDialog();
                }

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);

            }
        });

    }

    private void showCustomizeDialog() {
//    /* @setView 装入自定义View ==> R.layout.dialog_customize
//     * 由于dialog_customize.xml只放置了一个EditView，因此和图8一样
//     * dialog_customize.xml可自定义更复杂的View
//     */
//        AlertDialog.Builder customizeDialog = new AlertDialog.Builder(MyTeamAllFragment.this);
//        final View dialogView = LayoutInflater.from(MyTeamAllFragment.this).inflate(R.layout.dialog_rule, null);
//
//        customizeDialog.setView(dialogView);
//        TextView ruleText= (TextView) dialogView.findViewById(R.id.rule_text);
//        ruleText.setMovementMethod(ScrollingMovementMethod.getInstance());
//        ruleText.setText(rules);
//        customizeDialog.show();


        final MyDialog dialog = new MyDialog(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        Log.e("--rpType---", rpType);
        if ("1".equals(rpType)) {
            LinearLayout layoutmoney = (LinearLayout) inflater.inflate(R.layout.dialog_redpacket_money, null);

            TextView redpacket_money = (TextView) layoutmoney.findViewById(R.id.redpacket_money);
            ImageView redpacket_close = (ImageView) layoutmoney.findViewById(R.id.redpacket_close);
            get_redpacket = (TextView) layoutmoney.findViewById(R.id.get_redpacket);
            LinearLayout LinearLayout_get_redpacket = (LinearLayout) layoutmoney.findViewById(R.id.LinearLayout_get_redpacket);
            redpacket_close.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });

            LinearLayout_get_redpacket.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    integral("2", rpId, SPUtils.getString(getActivity(), "AmNumber"));
                }
            });

            redpacket_money.setMovementMethod(ScrollingMovementMethod.getInstance());
            redpacket_money.setText(rpMoney);
            dialog.getWindow().setDimAmount(0);
            dialog.show();
            dialog.setCancelable(false);
            dialog.setContentView(layoutmoney);// show方法要在前面


        } else if ("2".equals(rpType)) {
            LinearLayout layoutmoney = (LinearLayout) inflater.inflate(R.layout.dialog_redpacket_integral, null);
            get_redpacket = (TextView) layoutmoney.findViewById(R.id.get_integral);
            TextView integral_money = (TextView) layoutmoney.findViewById(R.id.integral_money);
            ImageView intergal_close = (ImageView) layoutmoney.findViewById(R.id.intergal_close);
            LinearLayout LinearLayout_integral = (LinearLayout) layoutmoney.findViewById(R.id.LinearLayout_integral);
            intergal_close.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });

            LinearLayout_integral.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    integral("2", rpId, SPUtils.getString(getActivity(), "AmNumber"));
                }
            });

            integral_money.setMovementMethod(ScrollingMovementMethod.getInstance());
            integral_money.setText(rpMoney);
            dialog.getWindow().setDimAmount(0);
            dialog.show();
            dialog.setCancelable(false);
            dialog.setContentView(layoutmoney);// show方法要在前面
        }

    }

    private void integral(String mode, String rpId, String amNumber) {
        Map<String, Object> map = new HashMap<>();
        map.put("mode", mode);
        map.put("rpId", rpId);
        map.put("amNumber", amNumber);
        XUtil.Post(Config.RedPackageServlet_URL, map, new MyCallBack<String>() {

            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                redPacketBean = JSON.parseObject(result, RedPacketBean.class);
                if ("00".equals(redPacketBean.getCode())) {
                    get_redpacket.setText("领取成功");
                } else {
                    MyApp.getInstance().showToast(redPacketBean.getFailMessage());
                }


            }
        });


    }

}
