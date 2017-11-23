package com.futeng.happypays.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import com.futeng.happypays.R;
import com.futeng.happypays.tools.CalculateUtil;

public class CalculateActivity extends Activity {


    @InjectView(R.id.quicktrans_back)
    ImageView quicktransBack;
    @InjectView(R.id.RelativeLayout_quicktrans)
    RelativeLayout RelativeLayoutQuicktrans;
    @InjectView(R.id.edittext_calculate)
    TextView edittextCalculate;
    @InjectView(R.id.AC)
    TextView AC;
    @InjectView(R.id.fu)
    TextView fu;
    @InjectView(R.id.baifen)
    TextView baifen;
    @InjectView(R.id.chu)
    TextView chu;
    @InjectView(R.id.seven)
    TextView seven;
    @InjectView(R.id.eight)
    TextView eight;
    @InjectView(R.id.nine)
    TextView nine;
    @InjectView(R.id.cheng)
    TextView cheng;
    @InjectView(R.id.four)
    TextView four;
    @InjectView(R.id.five)
    TextView five;
    @InjectView(R.id.six)
    TextView six;
    @InjectView(R.id.jia)
    TextView jia;
    @InjectView(R.id.one)
    TextView one;
    @InjectView(R.id.two)
    TextView two;
    @InjectView(R.id.three)
    TextView three;
    @InjectView(R.id.jian)
    TextView jian;
    @InjectView(R.id.zero)
    TextView zero;
    @InjectView(R.id.dian)
    TextView dian;
    @InjectView(R.id.dengyu)
    TextView dengyu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate);
        ButterKnife.inject(this);

    }


    @OnClick({R.id.quicktrans_back, R.id.AC, R.id.fu, R.id.baifen, R.id.chu, R.id.seven, R.id.eight, R.id.nine, R.id.cheng, R.id.four, R.id.five, R.id.six, R.id.jia, R.id.one, R.id.two, R.id.three, R.id.jian, R.id.zero, R.id.dian, R.id.dengyu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.quicktrans_back:
                finish();
                break;
            case R.id.AC:
                CalculateUtil.clear();
                edittextCalculate.setText("0");
                break;
            case R.id.fu:
                String jiajian = CalculateUtil.execOpera(edittextCalculate.getText().toString());
                edittextCalculate.setText(jiajian);
                break;
            case R.id.baifen:
                double baifen = Double.parseDouble(edittextCalculate.getText().toString());
                String baifenvalue= CalculateUtil.execOperabaifen(baifen);
                edittextCalculate.setText(baifenvalue);
                break;
            case R.id.chu:
                String chu = CalculateUtil.execOperation("/");
                if(!"".equals(chu)){
                    edittextCalculate.setText(chu);
                }
                break;
            case R.id.seven:
                String seven= CalculateUtil.execNumber("7");
                edittextCalculate.setText(seven);
                break;
            case R.id.eight:
                String eight= CalculateUtil.execNumber("8");
                edittextCalculate.setText(eight);
                break;
            case R.id.nine:
                String nine= CalculateUtil.execNumber("9");
                edittextCalculate.setText(nine);
                break;
            case R.id.cheng:
                String chen = CalculateUtil.execOperation("*");
                if(!"".equals(chen)){
                    edittextCalculate.setText(chen);
                }
                break;
            case R.id.four:
                String four= CalculateUtil.execNumber("4");
                edittextCalculate.setText(four);
                break;
            case R.id.five:
                String five= CalculateUtil.execNumber("5");
                edittextCalculate.setText(five);
                break;
            case R.id.six:
                String six= CalculateUtil.execNumber("6");
                edittextCalculate.setText(six);
                break;
            case R.id.jia:
               String jia= CalculateUtil.execOperation("+");
                if(!"".equals(jia)){
                    edittextCalculate.setText(jia);
                }
                break;
            case R.id.one:
                String one= CalculateUtil.execNumber("1");
                edittextCalculate.setText(one);
                break;
            case R.id.two:
                String two= CalculateUtil.execNumber("2");
                edittextCalculate.setText(two);
                break;
            case R.id.three:
                String three= CalculateUtil.execNumber("3");
                edittextCalculate.setText(three);
                break;
            case R.id.jian:
               String jian = CalculateUtil.execOperation("-");
                if(!"".equals(jian)){
                    edittextCalculate.setText(jian);
                }
                break;
            case R.id.zero:
                String zero= CalculateUtil.execNumber("0");
                edittextCalculate.setText(zero);
                break;
            case R.id.dian:
                String dian= CalculateUtil.execNumber(".");
                edittextCalculate.setText(dian);
                break;
            case R.id.dengyu:
                String result= CalculateUtil.execResult();
                edittextCalculate.setText(result);
                break;
        }
    }
}
