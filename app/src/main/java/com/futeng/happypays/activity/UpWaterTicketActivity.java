package com.futeng.happypays.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import com.alibaba.fastjson.JSON;
import com.futeng.happypays.R;
import com.futeng.happypays.tools.Base64utils;
import com.futeng.happypays.tools.Config;
import com.futeng.happypays.tools.MyApp;
import com.futeng.happypays.tools.MyCallBack;
import com.futeng.happypays.tools.SPUtils;
import com.futeng.happypays.tools.XUtil;
import com.futeng.happypays.utils.TicketBean;

public class UpWaterTicketActivity extends Activity {

    @InjectView(R.id.LinearLayoutone_upwawter)
    LinearLayout LinearLayoutoneUpwawter;
    @InjectView(R.id.upwater_money)
    TextView upwaterMoney;
    @InjectView(R.id.upwater_linone)
    LinearLayout upwaterLinone;
    @InjectView(R.id.jiaoyinumber)
    TextView jiaoyinumber;
    @InjectView(R.id.jiaoyitime)
    TextView jiaoyitime;
    @InjectView(R.id.zhifufangshi)
    TextView zhifufangshi;
    @InjectView(R.id.upwaterimag)
    ImageView upwaterimag;
    @InjectView(R.id.uowatertext)
    TextView uowatertext;

    Bitmap mSignBitmap;
    String imageStr1;

    @InjectView(R.id.ScrollViews)
    ScrollView ScrollViews;
    @InjectView(R.id.up_back)
    ImageView upBack;
    @InjectView(R.id.upwater)
    LinearLayout upwater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_up_water_ticket);
        ButterKnife.inject(this);
        //获取交易金额
        String upmoney = SPUtils.getString(this, "upmoney");
        //获取交易时间
        String uptime = SPUtils.getString(this, "uptime");
        //获取订单号
        String Tran37 = SPUtils.getString(this, "tran37");
        //获取交易类型
        String uptype = SPUtils.getString(this, "uptype");

        Log.e("*********",uptype+"");

        Log.e("小票订单号",Tran37+"");

        upwaterMoney.setText(upmoney);
        jiaoyitime.setText(uptime);
        jiaoyinumber.setText(Tran37);

        if ("1-0".equals(uptype)) {
            zhifufangshi.setText("微信D0");
            uowatertext.setText("微信支付");
            upwaterimag.setImageDrawable(getResources().getDrawable(R.mipmap.wechat));
        } else if ("1-1".equals(uptype)) {
            zhifufangshi.setText("微信T1");
            uowatertext.setText("微信支付");
            upwaterimag.setImageDrawable(getResources().getDrawable(R.mipmap.wechat));
        }else if ("2-0".equals(uptype)) {
            zhifufangshi.setText("支付宝D0");
            uowatertext.setText("支付宝支付");
            upwaterimag.setImageDrawable(getResources().getDrawable(R.mipmap.zfb));
        } else if ("2-1".equals(uptype)) {
            zhifufangshi.setText("支付宝T1");
            uowatertext.setText("支付宝支付");
            upwaterimag.setImageDrawable(getResources().getDrawable(R.mipmap.zfb));
        } else if ("3".equals(uptype)) {
            zhifufangshi.setText("银联支付");
            uowatertext.setText("银联支付");
            upwaterimag.setImageDrawable(getResources().getDrawable(R.mipmap.yinlian));
        }else if ("4-0".equals(uptype)) {
            zhifufangshi.setText("无卡快捷D0");
            uowatertext.setText("无卡快捷");
            upwaterimag.setImageDrawable(getResources().getDrawable(R.mipmap.yinlian));
        }else if ("4-1".equals(uptype)) {
            zhifufangshi.setText("无卡快捷T1");
            uowatertext.setText("无卡快捷");
            upwaterimag.setImageDrawable(getResources().getDrawable(R.mipmap.yinlian));
        }else if ("4".equals(uptype)) {
            zhifufangshi.setText("无卡快捷");
            uowatertext.setText("无卡快捷");
            upwaterimag.setImageDrawable(getResources().getDrawable(R.mipmap.yinlian));
        }
    }

    private void upticket() {

        Context context = getApplicationContext();
        View rootView = findViewById(R.id.ScrollViews);

        WindowManager wm = this.getWindowManager();
        int width = wm.getDefaultDisplay().getWidth();
        int height = wm.getDefaultDisplay().getHeight();


//        //获取当前屏幕的大小
//        int width = getWindow().getDecorView().getRootView().getWidth();
//        int height = getWindow().getDecorView().getRootView().getHeight();
        Log.e("屏幕大小", width + "" + "//" + height + "");
        mSignBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(mSignBitmap);

        rootView.draw(canvas);

        File file = new File(Environment.getExternalStorageDirectory() + "/" + "upticket.png");

        FileOutputStream f = null;
        try {
            f = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        boolean b = mSignBitmap.compress(Bitmap.CompressFormat.PNG, 100, f);

        //获取Tran37
        String tran37 = SPUtils.getString(UpWaterTicketActivity.this, "tran37");
        //获取登入商户号赋值
        String amNumber = SPUtils.getString(UpWaterTicketActivity.this, "AmNumber");

        if (b) {
            //截图成功
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            mSignBitmap.compress(Bitmap.CompressFormat.JPEG, 75, stream);// (0 - 100)压缩文件
            imageStr1 = Base64utils.baseEncript(mSignBitmap);
            Log.e("+++++++++imagestr", imageStr1);
            ticket(tran37, imageStr1, amNumber);
        }

    }

    //创建签名文件
    private void createSignFile() {
        int w = mSignBitmap.getWidth(), h = mSignBitmap.getHeight();
        Log.v("bill", "width" + w + " height" + h);
        int[] pixels = new int[w * h];
        mSignBitmap.getPixels(pixels, 0, w, 0, 0, w, h);
        Log.v("bill", "pixels=" + Arrays.toString(pixels));
        byte[] rgb = addBMP_RGB_888(pixels, w, h);
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
        } catch (IOException e) {
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

    private byte[] addBMP_RGB_888(int[] b, int w, int h) {
        int len = b.length;
        Log.v("bill", "length" + b.length);
        byte[] buffer = new byte[w * h * 3];
        int offset = 0;
        for (int i = len - 1; i >= w; i -= w) {
            //DIB文件格式最后一行为第一行，每行按从左到右顺序
            int end = i, start = i - w + 1;
            for (int j = start; j <= end; j++) {
                buffer[offset] = (byte) Color.blue(b[j]);//buffer[offset]=(byte)(b[j]>>0);
                buffer[offset + 1] = (byte) Color.green(b[j]);//buffer[offset+1]=(byte)(b[j]>>8);
                buffer[offset + 2] = (byte) Color.red(b[j]);//buffer[offset+2]=(byte)(b[j]>>16);
                offset += 3;
            }
        }
        return buffer;
    }

    private void ticket(String tran37, String ticket, String amNumber) {
        Map<String, Object> map = new HashMap<>();
        map.put("tran37", tran37);
        map.put("ticket", ticket);
        map.put("amNumber", amNumber);
        map.put("mode", 2);
        XUtil.Post(Config.TicketServlet_URL, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                TicketBean ticketBean = JSON.parseObject(result, TicketBean.class);
                if ("00".equals(ticketBean.getCode())) {
//                    MyApp.getInstance().showToast(ticketBean.getSuccessMessage());
                } else if ("99".equals(ticketBean.getCode())) {
//                    MyApp.getInstance().showToast(ticketBean.getFailMessage());
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        upticket();
        //界面销毁上传小票
        Log.e("界面已销毁", "界面已销毁");
    }

    @OnClick(R.id.up_back)
    public void onClick() {
        finish();
    }
}
