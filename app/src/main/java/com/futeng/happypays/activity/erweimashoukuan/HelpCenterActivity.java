package com.futeng.happypays.activity.erweimashoukuan;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import com.futeng.happypays.R;

public class HelpCenterActivity extends Activity {

    @InjectView(R.id.helpcenter_back)
    ImageView helpcenterBack;
    @InjectView(R.id.helpone)
    LinearLayout helpone;
    @InjectView(R.id.helpones)
    LinearLayout helpones;
    @InjectView(R.id.helptwo)
    LinearLayout helptwo;
    @InjectView(R.id.helptwos)
    LinearLayout helptwos;
    @InjectView(R.id.helpthree)
    LinearLayout helpthree;
    @InjectView(R.id.helpthrees)
    LinearLayout helpthrees;
    @InjectView(R.id.helpfour)
    LinearLayout helpfour;
    @InjectView(R.id.helpfours)
    LinearLayout helpfours;
    @InjectView(R.id.five)
    LinearLayout five;
    @InjectView(R.id.helpfives)
    LinearLayout helpfives;
    @InjectView(R.id.helpsix)
    LinearLayout helpsix;
    @InjectView(R.id.helpsixs)
    LinearLayout helpsixs;

    private boolean one;
    private boolean two;
    private boolean three;
    private boolean four;
    private boolean fives;
    private boolean six;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_center);
        ButterKnife.inject(this);
    }

    @OnClick({R.id.helpcenter_back, R.id.helpone, R.id.helptwo, R.id.helpthree, R.id.helpfour, R.id.five, R.id.helpsix})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.helpcenter_back:
                finish();
                break;
            case R.id.helpone:
                if (one==false){
                    helpones.setVisibility(View.VISIBLE);
                    one=true;
                }else{
                    helpones.setVisibility(View.GONE);
                    one=false;
                }
                break;
            case R.id.helptwo:

                if (two==false){
                    helptwos.setVisibility(View.VISIBLE);
                    two=true;
                }else{
                    helptwos.setVisibility(View.GONE);
                    two=false;
                }

                break;
            case R.id.helpthree:

                if (three==false){
                    helpthrees.setVisibility(View.VISIBLE);
                    three=true;
                }else{
                    helpthrees.setVisibility(View.GONE);
                    three=false;
                }

                break;
            case R.id.helpfour:

                if (four==false){
                    helpfours.setVisibility(View.VISIBLE);
                    four=true;
                }else{
                    helpfours.setVisibility(View.GONE);
                    four=false;
                }

                break;
            case R.id.five:

                if (fives==false){
                    helpfives.setVisibility(View.VISIBLE);
                    fives=true;
                }else{
                    helpfives.setVisibility(View.GONE);
                    fives=false;
                }

                break;
            case R.id.helpsix:

                if (six==false){
                    helpsixs.setVisibility(View.VISIBLE);
                    six=true;
                }else{
                    helpsixs.setVisibility(View.GONE);
                    six=false;
                }

                break;
        }
    }
}
