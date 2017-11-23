package com.futeng.happypays.activity.webview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import butterknife.ButterKnife;
import butterknife.InjectView;
import com.futeng.happypays.R;

public class ZheShangCardActivity extends Fragment {

    @InjectView(R.id.webview_zheshang)
    WebView webviewZheshang;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_zhe_shang_card, container, false);
        ButterKnife.inject(this, v);
        WebSettings webSettings = webviewZheshang.getSettings();
        //设置WebView属性，能够执行Javascript脚本
        webSettings.setJavaScriptEnabled(true);
        //设置可以访问文件
        webSettings.setAllowFileAccess(true);
        //设置支持缩放
        webSettings.setBuiltInZoomControls(true);
        //加载需要显示的网页
        webviewZheshang.loadUrl("https://creditcard.cmbc.com.cn/wsonline/index/index.jhtml?tradeFrom=YX-DGYD18&EnStr=3003210B6225F8B942BE54F0A295C5D1");
        //设置Web视图
        webviewZheshang.setWebViewClient(new webViewClient());
        return v;
    }


    //Web视图
    private class webViewClient extends WebViewClient {
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }


}
