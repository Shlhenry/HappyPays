package com.futeng.happypays.activity.erweimashoukuan;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.futeng.happypays.R;
import com.futeng.happypays.adapter.LookLogisticAdapter;
import com.futeng.happypays.tools.DigitalUtil;
import com.futeng.happypays.tools.IndexBar;
import com.futeng.happypays.tools.MyApp;
import com.futeng.happypays.tools.MyCallBack;
import com.futeng.happypays.tools.SPUtils;
import com.futeng.happypays.tools.XUtil;
import com.futeng.happypays.utils.LogisticListBean;
import com.futeng.happypays.utils.LosisticNameBean;
import com.mylhyl.circledialog.CircleDialog;
import com.mylhyl.circledialog.params.ProgressParams;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class LookLogisticActivity extends FragmentActivity {

    @InjectView(R.id.looklogistic_back)
    ImageView looklogisticBack;
    @InjectView(R.id.textView8)
    TextView textView8;
    @InjectView(R.id.lv)
    ListView lv;
    @InjectView(R.id.index_bar)
    IndexBar indexBar;
    @InjectView(R.id.tv_letter)
    TextView tvLetter;
    @InjectView(R.id.find_logistic)
    LinearLayout findLogistic;
    @InjectView(R.id.EditText_logistic)
    EditText EditTextLogistic;
    @InjectView(R.id.showlogisticimg)
    ImageView showlogisticimg;
    @InjectView(R.id.showlogisticname)
    TextView showlogisticname;

    private LogisticListBean logisticListBean;
    private ArrayList<LosisticNameBean> mPersons = new ArrayList<>();
    private ArrayList<String> letters = new ArrayList<>();

    private DialogFragment dialogFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_look_logistic);
        ButterKnife.inject(this);

        Glide.with(LookLogisticActivity.this)
                .load("http://120.27.194.146/express/AAEWEB.png")
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .dontAnimate().placeholder
                (R.mipmap.moren).into(showlogisticimg);

                showlogisticname.setText("AAE");

        SPUtils.put(getApplication(),"showlogisticname","AAE");
        SPUtils.put(getApplication(),"showlogisticimg","http://120.27.194.146/express/AAEWEB.png");

        dialogFragment = new CircleDialog.Builder(this)
                .setProgressText("正在加载，请稍等。。。")
                .setCanceledOnTouchOutside(false)
                .setProgressStyle(ProgressParams.STYLE_SPINNER)
                .show();

        getLogistic();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                showlogisticname.setText(mPersons.get(i).getName());

                Glide.with(LookLogisticActivity.this)
                        .load("http://120.27.194.146/express/"+mPersons.get(i).getType()+".png")
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .dontAnimate().placeholder
                        (R.mipmap.moren).into(showlogisticimg);
                SPUtils.put(getApplication(),"showlogisticname",mPersons.get(i).getName());
                SPUtils.put(getApplication(),"showlogisticimg","http://120.27.194.146/express/"+mPersons.get(i).getType()+".png");
            }
        });
    }

    //获取快递列表
    private void getLogistic() {
        Map<String, Object> map = new HashMap<>();
        map.put("appkey", "4fc617294485fc52cc95cc67b1566bae");
        XUtil.Post("https://way.jd.com/jisuapi/type", map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                dialogFragment.dismiss();
                logisticListBean = JSON.parseObject(result, LogisticListBean.class);
                if ("10000".equals(logisticListBean.getCode())) {
                    if (logisticListBean.getResult().getResult() == null) {
                        return;
                    } else {
                        fillNameAndSort();
                        lv.setAdapter(new LookLogisticAdapter(mPersons));
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
                                    LosisticNameBean girl = mPersons.get(i);
                                    String pinyin = girl.getmPinyin();
                                    String firstPinyin = String.valueOf(TextUtils.isEmpty(pinyin) ? girl.getName().charAt(0) : pinyin.charAt(0));
                                    if (letter.compareToIgnoreCase(firstPinyin) == 0) {
                                        lv.setSelection(i);
                                        break;
                                    }
                                }
                            }
                        });


                    }
                } else {
                    return;
                }

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
                dialogFragment.dismiss();
            }
        });
    }

    private Handler mHander = new Handler();

    // 填充拼音, 排序
    private void fillNameAndSort() {
        for (int i = 0; i < logisticListBean.getResult().getResult().size(); i++) {
            LosisticNameBean girl = new LosisticNameBean(
                    logisticListBean.getResult().getResult().get(i).getNumber()
                    , logisticListBean.getResult().getResult().get(i).getLetter()
                    , logisticListBean.getResult().getResult().get(i).getName()
                    , logisticListBean.getResult().getResult().get(i).getTel()
                    , logisticListBean.getResult().getResult().get(i).getType());

            mPersons.add(girl);
            if (DigitalUtil.isDigital(girl.getName())) {
                if (!letters.contains("#")) {
                    letters.add("#");
                }
                continue;
            }
            String pinyin = girl.getmPinyin();
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

    @OnClick({R.id.looklogistic_back, R.id.find_logistic})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.looklogistic_back:
                finish();
                break;
            case R.id.find_logistic:
                if (TextUtils.isEmpty(EditTextLogistic.getText().toString())) {
                    MyApp.getInstance().showToast("请输入运单号查询!");
                } else {
                    SPUtils.put(getApplication(), "logisticallnumber", EditTextLogistic.getText().toString());
                    startActivity(new Intent(this, LogisticAllActivity.class));
                }
                break;
        }
    }
}