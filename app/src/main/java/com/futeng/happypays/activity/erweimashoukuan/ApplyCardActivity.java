package com.futeng.happypays.activity.erweimashoukuan;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import com.futeng.happypays.R;

public class ApplyCardActivity extends Activity {

    @InjectView(R.id.mingshengbank)
    LinearLayout mingshengbank;
    @InjectView(R.id.zheshangbank)
    LinearLayout zheshangbank;
    @InjectView(R.id.webview)
    WebView webview;
    @InjectView(R.id.cardshenqing_back)
    ImageView cardshenqingBack;
    @InjectView(R.id.cardleft)
    TextView cardleft;
    @InjectView(R.id.cardright)
    TextView cardright;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply_card);
        ButterKnife.inject(this);
        WebSettings webSettings = webview.getSettings();
        //设置WebView属性，能够执行Javascript脚本
        webSettings.setJavaScriptEnabled(true);
        //设置可以访问文件
        webSettings.setAllowFileAccess(true);
        //设置支持缩放
        webSettings.setBuiltInZoomControls(true);
        //加载需要显示的网页
        webview.loadUrl("https://creditcard.cmbc.com.cn/wsonline/index/index.jhtml?tradeFrom=YX-DGYD18&EnStr=3003210B6225F8B942BE54F0A295C5D1");
        //设置Web视图
        webview.setWebViewClient(new webViewClient());
        cardleft.setVisibility(View.VISIBLE);

    }

    @OnClick({R.id.mingshengbank, R.id.zheshangbank,R.id.cardshenqing_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cardshenqing_back :
                finish();
                break;
            case R.id.mingshengbank:
                cardleft.setVisibility(View.VISIBLE);
                cardright.setVisibility(View.GONE);

                WebSettings webSettings = webview.getSettings();
                //设置WebView属性，能够执行Javascript脚本
                webSettings.setJavaScriptEnabled(true);
                //设置可以访问文件
                webSettings.setAllowFileAccess(true);
                //设置支持缩放
                webSettings.setBuiltInZoomControls(true);
                //加载需要显示的网页
                webview.loadUrl("https://creditcard.cmbc.com.cn/wsonline/index/index.jhtml?tradeFrom=YX-DGYD18&EnStr=3003210B6225F8B942BE54F0A295C5D1");
                //设置Web视图
                webview.setWebViewClient(new webViewClient());
                break;
            case R.id.zheshangbank:

                cardleft.setVisibility(View.GONE);
                cardright.setVisibility(View.VISIBLE);

                WebSettings webSettings1 = webview.getSettings();
                //设置WebView属性，能够执行Javascript脚本
                webSettings1.setJavaScriptEnabled(true);
                //设置可以访问文件
                webSettings1.setAllowFileAccess(true);
                //设置支持缩放
                webSettings1.setBuiltInZoomControls(true);
                //加载需要显示的网页
                webview.loadUrl("https://e.czbank.com/weixinHTML/creditCardInstallment/installNavigation.html?wxRecommenderId=N1343");
                //设置Web视图
                webview.setWebViewClient(new webViewClient());
                break;

        }
    }



    //Web视图
    private class webViewClient extends WebViewClient {
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }


}
