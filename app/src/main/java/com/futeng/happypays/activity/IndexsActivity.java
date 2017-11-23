package com.futeng.happypays.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import com.alibaba.fastjson.JSON;
import com.futeng.happypays.R;
import com.futeng.happypays.adapter.IndexAdapter;
import com.futeng.happypays.tools.Config;
import com.futeng.happypays.tools.DigitalUtil;
import com.futeng.happypays.tools.IndexBar;
import com.futeng.happypays.tools.MyApp;
import com.futeng.happypays.tools.MyCallBack;
import com.futeng.happypays.tools.SPUtils;
import com.futeng.happypays.tools.XUtil;
import com.futeng.happypays.utils.Girl;
import com.futeng.happypays.utils.RealtimeBean;
import com.futeng.happypays.utils.RealtimenextBean;
import com.mylhyl.circledialog.CircleDialog;
import com.mylhyl.circledialog.params.ProgressParams;

public class IndexsActivity extends FragmentActivity {

    @InjectView(R.id.lv)
    ListView lv;
    @InjectView(R.id.index_bar)
    IndexBar indexBar;
    @InjectView(R.id.tv_letter)
    TextView tvLetter;
    @InjectView(R.id.index_back)
    ImageView indexBack;

    private RealtimeBean realtimeBean = null;
    private RealtimenextBean realtimenextBean = null;

    private ArrayList<Girl> mPersons = new ArrayList<>();
    private ArrayList<String> letters = new ArrayList<>();

    private DialogFragment dialogFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indexs);
        ButterKnife.inject(this);

        dialogFragment = new CircleDialog.Builder(IndexsActivity.this)
                .setProgressText("正在加载汇率，请稍等。。。")
                .setCanceledOnTouchOutside(false)
                .setProgressStyle(ProgressParams.STYLE_SPINNER)
                .show();

        realtime();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Log.e("++++++",mPersons.get(position).getHuilvValue()+"");//100人民币->外币
//                Log.e("------",mPersons.get(position).getHuilv()+"");//国家的英文简称
//                Log.e("******",mPersons.get(position).getName()+"");//美元
//                Log.e("//////",mPersons.get(position).getImgUrl()+"");//国旗图片的url

                SPUtils.put(getApplicationContext(), "curImgUrl", mPersons.get(position).getImgUrl());
                SPUtils.put(getApplicationContext(), "curCode", mPersons.get(position).getHuilv());
                SPUtils.put(getApplicationContext(), "curName", mPersons.get(position).getName());

                showexchange(mPersons.get(position).getHuilv());

            }
        });
    }

    private void realtime() {
        Map<String, Object> map = new HashMap<>();
        map.put("mode", 1);
        XUtil.Post(Config.ExchangerateServlet_URL, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                dialogFragment.dismiss();
                realtimeBean = JSON.parseObject(result, RealtimeBean.class);
                if ("99".equals(realtimeBean.getCode())) {
                    return;
                } else if ("00".equals(realtimeBean.getCode())) {
                    if (realtimeBean.getList() == null) {
                        return;
                    } else {
                        fillNameAndSort();
                        lv.setAdapter(new IndexAdapter(mPersons));
                        indexBar.setLetters(letters);
                        indexBar.setOnLetterChangeListener(new IndexBar.OnLetterChangeListener() {
                            @Override
                            public void onLetterChange(int position, String letter) {
                                showTextView(letter);
                                if ("#".equals(letter)) {
                                    lv.setSelection(0);
                                    return;
                                }
                                for (int i = 0; i < mPersons.size(); i++) {
                                    Girl girl = mPersons.get(i);
                                    String pinyin = girl.getPinyin();
                                    String firstPinyin = String.valueOf(TextUtils.isEmpty(pinyin) ? girl.getName().charAt(0) : pinyin.charAt(0));
                                    if (letter.compareToIgnoreCase(firstPinyin) == 0) {
                                        lv.setSelection(i);
                                        break;
                                    }
                                }
                            }
                        });
                    }
                }

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
                dialogFragment.dismiss();
                MyApp.getInstance().showToast("服务器在升级,请稍后");
            }
        });
    }

    // 填充拼音, 排序
    private void fillNameAndSort() {
        for (int i = 0; i < realtimeBean.getList().size(); i++) {
            Girl girl = new Girl(realtimeBean.getList().get(i).getCurName(), realtimeBean.getList().get(i).getCurImgUrl(), i, realtimeBean.getList().get(i).getCurCode(), realtimeBean.getList().get(i).getExcValue());
            mPersons.add(girl);
            if (DigitalUtil.isDigital(girl.getName())) {
                if (!letters.contains("#")) {
                    letters.add("#");
                }
                continue;
            }
            String pinyin = girl.getPinyin();
            String letter;
            if (!TextUtils.isEmpty(pinyin)) {
                letter = pinyin.substring(0, 1).toUpperCase();
            } else {
                letter = girl.getName().substring(0, 1).toUpperCase();
            }
            if (!letters.contains(letter)) {
                letters.add(letter);
            }
        }
        Collections.sort(mPersons);
        Collections.sort(letters);
    }

    private Handler mHander = new Handler();

    protected void showTextView(String letter) {
        tvLetter.setVisibility(View.VISIBLE);
        tvLetter.setText(letter);
        mHander.removeCallbacksAndMessages(null);
        mHander.postDelayed(new Runnable() {
            @Override
            public void run() {
                tvLetter.setVisibility(View.INVISIBLE);
            }
        }, 600);
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
                        SPUtils.put(getApplicationContext(), "ExcValue", realtimenextBean.getMap().getExcValue());
                        startActivity(new Intent(IndexsActivity.this, ExchangerateActivity.class));
                        finish();
                    } else {
                        return;
                    }
                }
            }
        });
    }

    @OnClick(R.id.index_back)
    public void onViewClicked() {
        finish();
    }
}
