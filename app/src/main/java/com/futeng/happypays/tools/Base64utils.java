package com.futeng.happypays.tools;

import android.graphics.Bitmap;
import android.os.Environment;
import android.util.Base64;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import Decoder.BASE64Decoder;

/**
 * Created by Administrator on 2016/8/23.
 */
public class Base64utils {

    public static String baseEncript(Bitmap bitmap) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        //将bitmap一字节流输出 Bitmap.CompressFormat.PNG 压缩格式，100：压缩率，baos：字节流
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        try {
            baos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] buffer = baos.toByteArray();
        //将图片的字节流数据加密成base64字符输出
        return Base64.encodeToString(buffer, 0, buffer.length, Base64.NO_WRAP);
    }

    public static String GenerateImage(String imgStr) {
        //对字节数组字符串进行Base64解码并生成图片
        if (imgStr == null) //图像数据为空
            return imgStr;
        BASE64Decoder decoder = new BASE64Decoder();
        try
        {
            //Base64解码
            byte[] b = decoder.decodeBuffer(imgStr);
            for(int i=0;i<b.length;++i)
            {
                if(b[i]<0)
                {//调整异常数据
                    b[i]+=256;
                }
            }
            //生成jpeg图片
            String imgFilePath = Environment.getExternalStorageDirectory()+ "/" + "222.png";//新生成的图片
            OutputStream out = new FileOutputStream(imgFilePath);
            out.write(b);
            out.flush();
            out.close();
            return imgFilePath;
        }
        catch (Exception e)
        {
            return null;
        }
    }
}
