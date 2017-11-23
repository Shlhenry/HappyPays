package com.futeng.happypays.activity;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

import com.futeng.happypays.R;

public class print_bitmap extends AppCompatActivity {
    private Button bnt_print;
    private ImageView mIVSign;
    private TextView mTVSign;
    private Bitmap mSignBitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_print_bitmap);
        bnt_print=(Button) findViewById(R.id.button_print);
        bnt_print.setOnClickListener(new bntPrintListener());

        mIVSign = (ImageView) findViewById(R.id.imageView);
        mTVSign = (TextView) findViewById(R.id.textView);

        mTVSign.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                WritePadDialog mWritePadDialog = new WritePadDialog(
                        print_bitmap.this, new WriteDialogListener() {

                    @Override
                    public void onPaintDone(Object object) {
                        mSignBitmap = (Bitmap) object;
                        createSignFile();
                        mIVSign.setImageBitmap(mSignBitmap);

//                        mTVSign.setVisibility(View.GONE);
                    }
                });

                mWritePadDialog.show();
            }
        });
    }

    private class bntPrintListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Toast.makeText(getApplicationContext(),"print out",Toast.LENGTH_SHORT).show();
        }
    }
    //创建签名文件
    private void createSignFile() {
    int w = mSignBitmap.getWidth(), h = mSignBitmap.getHeight();
    Log.v("bill","width"+w+" height"+h);
    int[] pixels=new int[w*h];
    mSignBitmap.getPixels(pixels, 0, w, 0, 0, w, h);
    Log.v("bill","pixels="+Arrays.toString(pixels));
    byte[] rgb = addBMP_RGB_888(pixels,w,h);
    byte[] header = addBMPImageHeader(rgb.length);
    byte[] infos = addBMPImageInfosHeader(w, h);


    byte[] buffer = new byte[54 + rgb.length];
    System.arraycopy(header, 0, buffer, 0, header.length);
    System.arraycopy(infos, 0, buffer, 14, infos.length);
    System.arraycopy(rgb, 0, buffer, 54, rgb.length);
    try {
        FileOutputStream fos = new FileOutputStream(Environment.getExternalStorageDirectory().getPath() + "/hello.bmp");
        fos.write(buffer);
    } catch (FileNotFoundException e) {
    // TODO Auto-generated catch block
        e.printStackTrace();
    }
    catch (IOException e) {
    // TODO Auto-generated catch block
        e.printStackTrace();
    }
}
    //BMP文件头
    private byte[] addBMPImageHeader(int size) {
        byte[] buffer = new byte[14];
        buffer[0] = 0x42;
        buffer[1] = 0x4D;
        buffer[2] = (byte) (size >> 0);
        buffer[3] = (byte) (size >> 8);
        buffer[4] = (byte) (size >> 16);
        buffer[5] = (byte) (size >> 24);
        buffer[6] = 0x00;
        buffer[7] = 0x00;
        buffer[8] = 0x00;
        buffer[9] = 0x00;
        buffer[10] = 0x36;// length of header and header info
        buffer[11] = 0x00;
        buffer[12] = 0x00;
        buffer[13] = 0x00;
        return buffer;
    }


    //BMP文件信息头
    private byte[] addBMPImageInfosHeader(int w, int h) {
        byte[] buffer = new byte[40];
        buffer[0] = 0x28;
        buffer[1] = 0x00;
        buffer[2] = 0x00;
        buffer[3] = 0x00;
        buffer[4] = (byte) (w >> 0);
        buffer[5] = (byte) (w >> 8);
        buffer[6] = (byte) (w >> 16);
        buffer[7] = (byte) (w >> 24);
        buffer[8] = (byte) (h >> 0);
        buffer[9] = (byte) (h >> 8);
        buffer[10] = (byte) (h >> 16);
        buffer[11] = (byte) (h >> 24);
        buffer[12] = 0x01;
        buffer[13] = 0x00;
        buffer[14] = 0x18;//有1（单色），4（16色），8（256色），16（64K色，高彩色），24（16M色，真彩色），32（4096M色，增强型真彩色）。1800为0018h=24。
        buffer[15] = 0x00;
        buffer[16] = 0x00;
        buffer[17] = 0x00;
        buffer[18] = 0x00;
        buffer[19] = 0x00;
        buffer[20] = 0x00;
        buffer[21] = 0x00;
        buffer[22] = 0x00;
        buffer[23] = 0x00;
        buffer[24] = (byte) 0xE0;
        buffer[25] = 0x01;
        buffer[26] = 0x00;
        buffer[27] = 0x00;
        buffer[28] = 0x02;
        buffer[29] = 0x03;
        buffer[30] = 0x00;
        buffer[31] = 0x00;
        buffer[32] = 0x00;
        buffer[33] = 0x00;
        buffer[34] = 0x00;
        buffer[35] = 0x00;
        buffer[36] = 0x00;
        buffer[37] = 0x00;
        buffer[38] = 0x00;
        buffer[39] = 0x00;
        return buffer;
    }


    private byte[] addBMP_RGB_888(int[] b,int w, int h) {
        int len = b.length;
        Log.v("bill","length"+b.length);
        byte[] buffer = new byte[w*h * 3];
        int offset=0;
        for (int i = len-1; i>=w; i-=w) {
//DIB文件格式最后一行为第一行，每行按从左到右顺序
            int end=i,start=i-w+1;
            for(int j=start;j<=end;j++){
                buffer[offset]=(byte) Color.blue(b[j]);//buffer[offset]=(byte)(b[j]>>0);
                buffer[offset+1]=(byte) Color.green(b[j]);//buffer[offset+1]=(byte)(b[j]>>8);
                buffer[offset+2]=(byte) Color.red(b[j]);//buffer[offset+2]=(byte)(b[j]>>16);
                offset += 3;
            }
        }
        return buffer;
    }

//private void createSignFile() {
//    ByteArrayOutputStream baos = null;
//    FileOutputStream fos = null;
//    String path = null;
//    File file = null;
//    try {
//        path = Environment.getExternalStorageDirectory() + File.separator + System.currentTimeMillis() + ".jpg";
//        file = new File(path);
//        fos = new FileOutputStream(file);
//        baos = new ByteArrayOutputStream();
//        //如果设置成Bitmap.compress(CompressFormat.JPEG, 100, fos) 图片的背景都是黑色的
//        mSignBitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
//        byte[] b = baos.toByteArray();
//        if (b != null) {
//            fos.write(b);
//        }
//    } catch (IOException e) {
//        e.printStackTrace();
//    } finally {
//        try {
//            if (fos != null) {
//                fos.close();
//            }
//            if (baos != null) {
//                baos.close();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//}
}
