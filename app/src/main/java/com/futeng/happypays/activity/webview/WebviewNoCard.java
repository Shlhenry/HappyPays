package com.futeng.happypays.activity.webview;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import com.futeng.happypays.R;
import com.futeng.happypays.tools.SPUtils;

public class WebviewNoCard extends Activity {

    @InjectView(R.id.webview_nocard)
    WebView webviewNocard;
    @InjectView(R.id.nocard_back)
    ImageView nocardBack;

    private String htmlcontent;
    public static DisplayMetrics displayMetrics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview_no_card);
        ButterKnife.inject(this);

        displayMetrics = getApplicationContext().getResources().getDisplayMetrics();
        htmlcontent = SPUtils.getString(getApplication(), "HtmlContent");

        if (htmlcontent.contains("src=\"/p")) {
            htmlcontent = htmlcontent.replace("src=\"/p", "src=\"http://car.zhuji.net/p");
        }
        Log.i("info", "=html2=" + htmlcontent.contains("src=\"/p") + "");
        showWebView();

    }

    private void showWebView() {
        // 设置WevView要显示的网页
        webviewNocard.loadDataWithBaseURL(null, htmlcontent, "text/html", "utf-8",
                null);
        webviewNocard.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webviewNocard.getSettings().setJavaScriptEnabled(true); //设置支持Javascript
        webviewNocard.requestFocus(); //触摸焦点起作用.如果不设置，则在点击网页文本输入框时，不能弹出软键盘及不响应其他的一些事件。
        //        webviewNocard.getSettings().setBuiltInZoomControls(true); //页面添加缩放按钮
        //                webviewNocard.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);   //取消滚动条

        //                点击链接由自己处理，而不是新开Android的系统browser响应该链接。
        webviewNocard.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //设置点击网页里面的链接还是在当前的webview里跳转
                view.loadUrl(url);
                return true;
            }
        });
        //        webviewNocard.setOnFocusChangeListener(new View.OnFocusChangeListener() {
        //            @Override
        //            public void onFocusChange(View v, boolean hasFocus) {
        //                if (hasFocus) {
        //                    try {
        //                        // 禁止网页上的缩放
        //                        Field defaultScale = WebView.class
        //                                .getDeclaredField("mDefaultScale");
        //                        defaultScale.setAccessible(true);
        //                        defaultScale.setFloat(webviewNocard, 1.0f);
        //                    } catch (SecurityException e) {
        //                        e.printStackTrace();
        //                    } catch (IllegalArgumentException e) {
        //                        e.printStackTrace();
        //                    } catch (IllegalAccessException e) {
        //                        e.printStackTrace();
        //                    } catch (NoSuchFieldException e) {
        //                        e.printStackTrace();
        //                    }
        //                }
        //            }
        //        });
    }

//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if ((keyCode == KeyEvent.KEYCODE_BACK) && webviewNocard.canGoBack()) {
//            webviewNocard.goBack();//返回webView的上一页面
//            return true;
//        }
//        return super.onKeyDown(keyCode, event);
//    }

    @OnClick(R.id.nocard_back)
    public void onViewClicked() {
        finish();
    }
}
