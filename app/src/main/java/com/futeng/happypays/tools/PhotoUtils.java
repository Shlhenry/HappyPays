package com.futeng.happypays.tools;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.widget.ImageView;
import com.futeng.happypays.tools.Base64utils;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by qjh on 2017/5/11.
 */

public class PhotoUtils  {

    /**
     * 在Activity 的 onActivityResult() 中调用 本方法,  如果是从相册选取的照片,
     * 调用本方法, 获取照片的的路径
     * @param activity
     * @param data
     * @return
     */
    public static String loadImgFromGallery(Activity activity, Intent data, Uri uri, int size, ImageView imageView) {
        if(data == null){
            return null;
        }

        // 外界的程序访问ContentProvider所提供数据 可以通过ContentResolver接口
        ContentResolver resolver = activity.getContentResolver();
        // 此处的用于判断接收的Activity是不是你想要的那个
        try {
            Uri originalUri = data.getData(); // 获得图片的uri
            // 显得到bitmap图片
            //TODO
            Bitmap bitmap = getThumbnail(activity,uri,size);

            // 这里开始的第二部分，获取图片的路径：
            String[] proj = { MediaStore.Images.Media.DATA };
            // 好像是android多媒体数据库的封装接口，具体的看Android文档
            Cursor cursor = activity.managedQuery(originalUri, proj, null, null, null);
            // 按我个人理解 这个是获得用户选择的图片的索引值
            int column_index = cursor
                    .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            // 将光标移至开头 ，这个很重要，不小心很容易引起越界
            cursor.moveToFirst();
            imageView.setImageBitmap(bitmap);
            return Base64utils.baseEncript(bitmap);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * size 宽和高的最大值
     */
    public static Bitmap getThumbnail(Activity activity,Uri uri,int size) throws FileNotFoundException, IOException{
        //通过uri拿取到输入流
        InputStream input = activity.getContentResolver().openInputStream(uri);
        //拿到Option对象, 设置一些优化的属性
        BitmapFactory.Options onlyBoundsOptions = new BitmapFactory.Options();
        onlyBoundsOptions.inJustDecodeBounds = true;
        onlyBoundsOptions.inDither=true;//optional
        onlyBoundsOptions.inPreferredConfig=Bitmap.Config.ARGB_8888;//optional
        BitmapFactory.decodeStream(input, null, onlyBoundsOptions);//从输入流中解码图片,得到图片的option
        input.close();//关闭输入流
        if ((onlyBoundsOptions.outWidth == -1) || (onlyBoundsOptions.outHeight == -1)){
            return null;
        }
        int originalSize = (onlyBoundsOptions.outHeight > onlyBoundsOptions.outWidth) ? onlyBoundsOptions.outHeight : onlyBoundsOptions.outWidth;//取款高的最大值
        //得到给定的大小和原始大小的比值
        double ratio = (originalSize > size) ? (originalSize / size) : 1.0;
        BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
        bitmapOptions.inSampleSize = getPowerOfTwoForSampleRatio(ratio);
        bitmapOptions.inDither=true;//optional
        bitmapOptions.inPreferredConfig=Bitmap.Config.ARGB_8888;//optional
        input = activity.getContentResolver().openInputStream(uri);//再次得到输入流
        //根据缩放后的Option得到bitmap
        Bitmap bitmap = BitmapFactory.decodeStream(input, null, bitmapOptions);
        input.close();
        return bitmap;
    }
    private static int getPowerOfTwoForSampleRatio(double ratio){
        int k = Integer.highestOneBit((int)Math.floor(ratio));
        if(k==0) return 1;
        else return k;
    }

    public static String ChangeBitmap(String imgPath,ImageView imageView){
        imageView.setImageBitmap(getLoacalBitmap(imgPath));
        return Base64utils.baseEncript(getLoacalBitmap(imgPath));
    }

    public static Bitmap getLoacalBitmap(String imgPath) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        options.inPurgeable = true;
        options.inInputShareable = true;
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(imgPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return BitmapFactory.decodeStream(fis, null, options);
    }
}
