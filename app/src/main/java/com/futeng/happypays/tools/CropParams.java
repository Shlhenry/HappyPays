package com.futeng.happypays.tools;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;

/**
 * 裁剪参数
 * <p/>
 * Created by xw on 2016-8-12.
 */
public class CropParams
{
    /**
     * 裁剪图片类型
     */
    public static final String CROP_TYPE = "image/*";
    /**
     * 输出格式
     */
    public static final String OUTPUT_FORMAT = Bitmap.CompressFormat.JPEG.toString();

    public static final int DEFAULT_ASPECT = 1;
    public static final int DEFAULT_OUTPUT = 300;
    public static final int DEFAULT_COMPRESS_WIDTH = 640;
    public static final int DEFAULT_COMPRESS_HEIGHT = 854;
    public static final int DEFAULT_COMPRESS_QUALITY = 90;

    /**
     * Uri
     */
    public Uri uri;

    public String type;
    public String outputFormat;
    public String crop;

    public boolean scale;
    public boolean returnData;
    public boolean noFaceDetection;
    public boolean scaleUpIfNeeded;

    /**
     * 默认是“True”Flas不裁剪.
     * it will only pick up images from gallery or take pictures from camera.
     */
    public boolean enable;

    /**
     * Default is false, if it is from capture and without crop, the image could be large
     * enough to trigger OOM, it is better to compress image while enable is false
     */
    public boolean compress;

    public boolean rotateToCorrectDirection;

    public int compressWidth;
    public int compressHeight;
    public int compressQuality;

    public int aspectX;
    public int aspectY;

    public int outputX;
    public int outputY;

    public Context context;

    /**
     * 构造函数 初始化参数
     *
     * @param context
     */
    public CropParams(Context context)
    {
        this.context = context;
        type = CROP_TYPE;
        outputFormat = OUTPUT_FORMAT;
        crop = "true";
        scale = true;
        returnData = false;
        noFaceDetection = true;
        scaleUpIfNeeded = true;
        enable = true;
        rotateToCorrectDirection = false;
        compress = false;
        compressQuality = DEFAULT_COMPRESS_QUALITY;
        compressWidth = DEFAULT_COMPRESS_WIDTH;
        compressHeight = DEFAULT_COMPRESS_HEIGHT;
        aspectX = DEFAULT_ASPECT;
        aspectY = DEFAULT_ASPECT;
        outputX = DEFAULT_OUTPUT;
        outputY = DEFAULT_OUTPUT;
        refreshUri();
    }

    /**
     * 更新Uri
     */
    public void refreshUri()
    {
        uri = PhotoCropUtil.generateUri();
    }
}
