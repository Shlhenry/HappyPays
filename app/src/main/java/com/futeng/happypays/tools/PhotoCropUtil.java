package com.futeng.happypays.tools;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * 拍照裁剪工具类
 * https://github.com/ryanhoo/PhotoCropper
 * <p/>
 * Created by xw on 2016-8-12.
 */
public class PhotoCropUtil
{
    public static final String TAG = "PhotoCropUtil";

    /**
     * request code of Activities or Fragments
     * You will have to change the values of the request codes below if they conflict with your own.
     */
    public static final int REQUEST_CROP = 127;
    public static final int REQUEST_CAMERA = 128;
    public static final int REQUEST_PICK = 129;

    public static final String CROP_CACHE_FOLDER = "PhotoCropper";

    /**
     * 生成Uri地址
     *
     * @return
     */
    public static Uri generateUri()
    {
        File cacheFolder = new File(Environment.getExternalStorageDirectory() + File.separator + CROP_CACHE_FOLDER);
        if (!cacheFolder.exists())
        {
            try
            {
                boolean result = cacheFolder.mkdir();
                Log.d(TAG, "generateUri " + cacheFolder + " result: " + (result ? "succeeded" : "failed"));
            } catch (Exception e)
            {
                Log.e(TAG, "generateUri failed: " + cacheFolder);
            }
        }
        String name = String.format("image-%d.jpg", System.currentTimeMillis());
        return Uri
                .fromFile(cacheFolder)
                .buildUpon()
                .appendPath(name)
                .build();
    }

    /**
     * 是否已裁剪
     *
     * @param uri
     * @return
     */
    public static boolean isPhotoReallyCropped(Uri uri)
    {
        File file = new File(uri.getPath());
        long length = file.length();
        return length > 0;
    }

    /**
     * 处理返回结果
     *
     * @param handler
     * @param requestCode
     * @param resultCode
     * @param data
     */
    public static void handleResult(CropHandler handler, int requestCode, int resultCode, Intent data)
    {
        if (handler == null) return;

        if (resultCode == Activity.RESULT_CANCELED)
        {
            handler.onCancel();
        } else if (resultCode == Activity.RESULT_OK)
        {
            CropParams cropParams = handler.getCropParams();
            if (cropParams == null)
            {
                handler.onFailed("CropHandler's params MUST NOT be null!");
                return;
            }
            switch (requestCode)
            {
                case REQUEST_PICK:
                case REQUEST_CROP:
                    if (isPhotoReallyCropped(cropParams.uri))
                    {
                        Log.d(TAG, "Photocropped!");
                        onPhotoCropped(handler, cropParams);
                        break;
                    } else
                    {
                        Context context = handler.getCropParams().context;
                        if (context != null)
                        {
                            if (data != null && data.getData() != null)
                            {
                                String path = CropFileUtil.getSmartFilePath(context, data.getData());
                                boolean result = CropFileUtil.copyFile(path, cropParams.uri.getPath());
                                if (!result)
                                {
                                    handler.onFailed("Copy file to cached folder failed");
                                    break;
                                }
                            } else
                            {
                                handler.onFailed("Returned data is null " + data);
                                break;
                            }
                        } else
                        {
                            handler.onFailed("CropHandler's context MUST NOT be null!");
                        }
                    }
                case REQUEST_CAMERA:
                    if (cropParams.enable)
                    {
                        // Send this Uri to Crop
                        Intent intent = buildCropFromUriIntent(cropParams);
                        handler.handleIntent(intent, REQUEST_CROP);
                    } else
                    {
                        Log.d(TAG, "Photocropped!");
                        onPhotoCropped(handler, cropParams);
                    }
                    break;
            }
        }
    }

    /**
     * 裁剪完成
     *
     * @param handler
     * @param cropParams
     */
    private static void onPhotoCropped(CropHandler handler, CropParams cropParams)
    {
        if (cropParams.compress)
        {
            Uri originUri = cropParams.uri;
            Uri compressUri = PhotoCropUtil.generateUri();
            CompressImageUtil.compressImageFile(cropParams, originUri, compressUri);
            handler.onCompressed(compressUri);
        } else
        {
            handler.onPhotoCropped(cropParams.uri);
        }
    }

    /**
     * 生成图库Intent
     *
     * @param params
     * @return
     */
    public static Intent buildGalleryIntent(CropParams params)
    {
        Intent  intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_PICK);;
        return intent;
    }

    /**
     * 生成拍照Intent
     *
     * @param params
     * @return
     */
    public static Intent buildCameraIntent(CropParams params)
    {
        return new Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                .putExtra(MediaStore.EXTRA_OUTPUT, params.uri);
    }


    /**
     * 生成裁剪（从Uri）Intent
     *
     * @param params
     * @return
     */
    private static Intent buildCropFromUriIntent(CropParams params)
    {
        return buildCropIntent("com.android.camera.action.CROP", params);
    }

    /**
     * 生成裁剪Intent
     *
     * @param action
     * @param params
     * @return
     */
    private static Intent buildCropIntent(String action, CropParams params)
    {
        return new Intent(action)
                .setDataAndType(params.uri, params.type)
                .putExtra("crop", "true")
                .putExtra("scale", params.scale)
                .putExtra("aspectX", params.aspectX)
                .putExtra("aspectY", params.aspectY)
                .putExtra("outputX", params.outputX)
                .putExtra("outputY", params.outputY)
                .putExtra("return-data", params.returnData)
                .putExtra("outputFormat", params.outputFormat)
                .putExtra("noFaceDetection", params.noFaceDetection)
                .putExtra("scaleUpIfNeeded", params.scaleUpIfNeeded)
                .putExtra(MediaStore.EXTRA_OUTPUT, params.uri);
    }

    /**
     * 清空缓存目录
     *
     * @return
     */
    public static boolean clearCacheDir()
    {
        File cacheFolder = new File(Environment.getExternalStorageDirectory() + File.separator + CROP_CACHE_FOLDER);
        if (cacheFolder.exists() && cacheFolder.listFiles() != null)
        {
            for (File file : cacheFolder.listFiles())
            {
                boolean result = file.delete();
                Log.d(TAG, "Delete " + file.getAbsolutePath() + (result ? " succeeded" : " failed"));
            }
            return true;
        }
        return false;
    }

    /**
     * 清空裁剪缓存文件
     *
     * @param uri Uri
     * @return
     */
    public static boolean clearCachedCropFile(Uri uri)
    {
        if (uri == null) return false;

        File file = new File(uri.getPath());
        if (file.exists())
        {
            boolean result = file.delete();
            Log.d(TAG, "Delete " + file.getAbsolutePath() + (result ? " succeeded" : " failed"));
            return result;
        }
        return false;
    }

    public static Bitmap decodeUriAsBitmap(Context context, Uri uri)
    {
        if (context == null || uri == null) return null;

        Bitmap bitmap;
        try
        {
            bitmap = BitmapFactory.decodeStream(context.getContentResolver().openInputStream(uri));
        } catch (FileNotFoundException e)
        {
            Log.e(TAG, "decode uri as bitmap fail");
            return null;
        }
        return bitmap;
    }


}
