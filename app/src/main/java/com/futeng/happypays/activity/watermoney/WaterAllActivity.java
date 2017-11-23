package com.futeng.happypays.activity.watermoney;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import com.futeng.happypays.R;
import com.futeng.happypays.activity.MainActivity;

public class WaterAllActivity extends Activity {

    @InjectView(R.id.waterall)
    ImageView waterall;
    @InjectView(R.id.water_balance)
    LinearLayout waterBalance;
    @InjectView(R.id.water_moneychange)
    LinearLayout waterMoneychange;
    @InjectView(R.id.waterother_balance)
    LinearLayout waterotherBalance;
    @InjectView(R.id.water_jifen)
    LinearLayout waterJifen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_water_all);
        ButterKnife.inject(this);

    }

    @OnClick({R.id.waterall, R.id.water_balance, R.id.water_moneychange, R.id.waterother_balance,R.id.water_jifen})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.waterall:
                Intent show = new Intent(WaterAllActivity.this, MainActivity.class);
                show.putExtra("grxx", 4);
                startActivity(show);
                finish();
                break;
            case R.id.water_balance:
                startActivity(new Intent(WaterAllActivity.this, WaterBalanceActivity.class));
                break;
            case R.id.waterother_balance:
                startActivity(new Intent(WaterAllActivity.this, waterotherActivity.class));
                break;
            case R.id.water_moneychange:
                startActivity(new Intent(WaterAllActivity.this, WaterChangemoneyActivity.class));
                break;
            case R.id.water_jifen:
                startActivity(new Intent(WaterAllActivity.this, IntegralChangeActivity.class));
                break;
        }
    }


}
