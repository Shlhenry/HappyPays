package com.futeng.happypays.tools;

import android.content.Intent;
import android.net.Uri;

/**
 * 裁剪回调处理
 * <p/>
 * Created by xw on 2016-8-12.
 */
public interface CropHandler
{
    /**
     * 拍照裁剪完成
     *
     * @param uri
     */
    void onPhotoCropped(Uri uri);

    /**
     * 压缩完成
     *
     * @param uri
     */
    void onCompressed(Uri uri);

    /**
     * 取消
     */
    void onCancel();

    /**
     * 失败
     *
     * @param message
     */
    void onFailed(String message);

    /**
     * 处理Intent　回传
     *
     * @param intent
     * @param requestCode
     */
    void handleIntent(Intent intent, int requestCode);

    /**
     * 获取裁剪参数
     *
     * @return
     */
    CropParams getCropParams();
}
