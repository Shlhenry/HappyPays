//package com.futeng.happypays.activity.allgridview;
//
//import android.app.Activity;
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.view.Window;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.Toast;
//
//import com.futeng.happypays.R;
//import com.futeng.happypays.activity.MainActivity;
//import com.futeng.happypays.activity.webview.Webviewcard;
//
//import butterknife.ButterKnife;
//import butterknife.InjectView;
//import butterknife.OnClick;
//
///**
// * 信用卡申请
// */
//public class GetXinCardActivity extends Activity {
//
//
//    @InjectView(R.id.linearLayout_bank_one)
//    LinearLayout linearLayoutBankOne;
//    @InjectView(R.id.linearLayout_bank_two)
//    LinearLayout linearLayoutBankTwo;
//    @InjectView(R.id.linearLayout_bank_three)
//    LinearLayout linearLayoutBankThree;
//    @InjectView(R.id.linearLayout_bank_four)
//    LinearLayout linearLayoutBankFour;
//    @InjectView(R.id.linearLayout_bank_five)
//    LinearLayout linearLayoutBankFive;
//    @InjectView(R.id.linearLayout_bank_six)
//    LinearLayout linearLayoutBankSix;
//    @InjectView(R.id.cardshenqing_back)
//    ImageView cardshenqingBack;
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        setContentView(R.layout.activity_cardlist);
//        ButterKnife.inject(this);
//
//    }
//
//    @OnClick({R.id.linearLayout_bank_one, R.id.linearLayout_bank_two, R.id.linearLayout_bank_three, R.id.linearLayout_bank_four, R.id.linearLayout_bank_five, R.id.linearLayout_bank_six,R.id.cardshenqing_back})
//    public void onClick(View view) {
//        switch (view.getId()) {
//            case R.id.linearLayout_bank_one:
//                startActivity(new Intent(this, Webviewcard.class));
//                break;
//            case R.id.cardshenqing_back:
////                Intent show=new Intent(GetXinCardActivity.this,MainActivity.class);
////                show.putExtra("grxx",3);
////                startActivity(show);
//                finish();
//                break;
//            case R.id.linearLayout_bank_two:
//                Toast.makeText(this, "程序猿正在和银行讨论中，敬请期待!", Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.linearLayout_bank_three:
//                Toast.makeText(this, "程序猿正在和银行讨论中，敬请期待!", Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.linearLayout_bank_four:
//                Toast.makeText(this, "程序猿正在和银行讨论中，敬请期待!", Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.linearLayout_bank_five:
//                Toast.makeText(this, "程序猿正在和银行讨论中，敬请期待!", Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.linearLayout_bank_six:
//                Toast.makeText(this, "程序猿正在和银行讨论中，敬请期待!", Toast.LENGTH_SHORT).show();
//                break;
//
//        }
//    }
//
//    @OnClick(R.id.cardshenqing_back)
//    public void onClick() {
//    }
//}
