package com.futeng.happypays.tools;

import android.util.Log;
import com.futeng.happypays.R;
import org.xutils.common.Callback;

public class MyProgressCallBack<ResultType> implements Callback.ProgressCallback<ResultType> {

    @Override
    public void onSuccess(ResultType result) {
        //根据公司业务需求，处理相应业务逻辑
        Log.e("Success", result.toString());
    }

    @Override
    public void onError(Throwable ex, boolean isOnCallback) {
        //可以根据公司的需求进行统一的请求网络失败的逻辑处理
        //MyApp.getInstance().dismissLoadding();
        Log.e("Error", ex.toString());
        if (ex.toString().contains("java.net.SocketTimeoutException")) {
            MyApp.getInstance().showToast("请求超时");
        }else if (ex.toString().contains("SocketException")) {
            MyApp.getInstance().showToast("请求失败");
        }  else if (ex.toString().contains("Network is unreachable")) {
            MyApp.getInstance().showToast("没有网络连接");
        } else if (ex.toString().contains("No route to host")) {
            MyApp.getInstance().showToast("无法连接到服务器");
        } else if (ex.toString().contains("JSONException")) {
            MyApp.getInstance().showToast("数据解析失败");
        } else if (ex.toString().contains("NullPointerException")) {
            MyApp.getInstance().showToast("数据解析失败");
        } else{
            MyApp.getInstance().showToast("很抱歉，出现了一些错误");
        }
    }

    @Override
    public void onCancelled(CancelledException cex) {
        Log.e("onCancelled", "请求已被取消" + cex.toString());
    }

    @Override
    public void onFinished() {
        Log.e("onFinished", "请求结束");
    }

    @Override
    public void onWaiting() {
        Log.e("onWaiting", "请求等待");
    }

    @Override
    public void onStarted() {
        Log.e("onStarted", "请求开始");
    }

    @Override
    public void onLoading(long total, long current, boolean isDownloading) {
        Log.e("onLoading", "开始上传下载："+"total:"+total+"==>"+"current:"+current);
    }

}
