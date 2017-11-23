package com.futeng.happypays.activity.fgmentfour;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import com.futeng.happypays.R;
import com.futeng.happypays.tools.SPUtils;
/*
*修改结算卡信息 引导页
 */
public class GuildClearInfoActivity extends Activity {

    @InjectView(R.id.guildclear_back)
    ImageView guildclearBack;
    @InjectView(R.id.guildname)
    TextView guildname;
    @InjectView(R.id.guildbank)
    TextView guildbank;
    @InjectView(R.id.guildcardnum)
    TextView guildcardnum;
    @InjectView(R.id.guildbanknum)
    TextView guildbanknum;
    @InjectView(R.id.guildclear_next)
    Button guildclearNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guild_clear_info);
        ButterKnife.inject(this);

        guildname.setText(SPUtils.getString(this,"infoAccountName"));
        guildbank.setText(SPUtils.getString(this,"infoBankName"));
        guildcardnum.setText(SPUtils.getString(this,"infoAccountNumber"));
        guildbanknum.setText(SPUtils.getString(this,"infoBankBranchNumber"));
    }

    @OnClick({R.id.guildclear_back, R.id.guildclear_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.guildclear_back:
                finish();
                break;
            case R.id.guildclear_next:
                startActivity(new Intent(this,ClearInfoActivity.class));
                break;
        }
    }
}
