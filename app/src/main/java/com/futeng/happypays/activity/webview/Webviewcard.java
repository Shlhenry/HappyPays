package com.futeng.happypays.activity.webview;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import com.futeng.happypays.R;
import com.futeng.happypays.tools.SPUtils;

/**
 * Created by Administrator on 2017/3/29.
 */
public class Webviewcard extends Fragment {

    @InjectView(R.id.quicgold_back)
    ImageView quicgoldBack;
    private WebView webview;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_webview_card, container, false);
        ButterKnife.inject(this, v);
        webview = (WebView)getActivity().findViewById(R.id.webview);
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

        return v;
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }
//
//    @Override
//    //设置回退
//    //覆盖Activity类的onKeyDown(int keyCoder,KeyEvent event)方法
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if ((keyCode == KeyEvent.KEYCODE_BACK) && webview.canGoBack()) {
//            webview.goBack(); //goBack()表示返回WebView的上一页面
//            return true;
//        }
//        finish();//结束退出程序
//        return false;
//    }
//
//    @OnClick(R.id.quicgold_back)
//    public void onViewClicked() {
//        finish();
//    }

    //Web视图
    private class webViewClient extends WebViewClient {
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}
