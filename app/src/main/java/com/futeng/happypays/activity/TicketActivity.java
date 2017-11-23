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
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import com.alibaba.fastjson.JSON;
import com.futeng.happypays.R;
import com.futeng.happypays.activity.mposgather.BbposListActivity;
import com.futeng.happypays.tools.Base64utils;
import com.futeng.happypays.tools.Config;
import com.futeng.happypays.tools.MyApp;
import com.futeng.happypays.tools.MyCallBack;
import com.futeng.happypays.tools.SPUtils;
import com.futeng.happypays.tools.XUtil;
import com.futeng.happypays.utils.TicketBean;

/**
 * Created by Administrator on 2017/4/12.
 */
public class TicketActivity extends Activity {


    @InjectView(R.id.mIVSign)
    ImageView mIVSign;
    @InjectView(R.id.upname)
    TextView upname;

    Bitmap mSignBitmap;
    String imageStr1;

    @InjectView(R.id.ticket_amname)
    TextView ticketAmname;
    @InjectView(R.id.ticket_amnumber)
    TextView ticketAmnumber;
    @InjectView(R.id.ticket_zongduannum)
    TextView ticketZongduannum;
    @InjectView(R.id.ticket_bankcard)
    TextView ticketBankcard;
    @InjectView(R.id.ticket_pingzhenghnum)
    TextView ticketPingzhenghnum;
    @InjectView(R.id.ticket_time)
    TextView ticketTime;
    @InjectView(R.id.ticket_jiaoyicankao)
    TextView ticketJiaoyicankao;
    @InjectView(R.id.ticket_money)
    TextView ticketMoney;
    @InjectView(R.id.ticket_date)
    TextView ticketDate;
    @InjectView(R.id.ticket_shouquannum)
    TextView ticketShouquannum;

    private int Imgnum=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ticket);
        ButterKnife.inject(this);
        try {
            ticketinfo();
        } catch (ParseException e) {
            e.printStackTrace();
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

    @OnClick({R.id.upname, R.id.mIVSign})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.upname:
                if (Imgnum==0){
                    Toast.makeText(this,"您还未签名，请签名后上传",Toast.LENGTH_SHORT).show();
                }else{
                Context context = getApplicationContext();
                View rootView = findViewById(R.id.rootLayout);

                //获取当前屏幕的大小
                int width = getWindow().getDecorView().getRootView().getWidth();
                int height = getWindow().getDecorView().getRootView().getHeight();
                Log.e("屏幕大小",width+""+"//"+height+"");
                mSignBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);

                Canvas canvas = new Canvas(mSignBitmap);

                rootView.draw(canvas);

                File file = new File(Environment.getExternalStorageDirectory() + "/" + "ticket.png");

                FileOutputStream f = null;
                try {
                    f = new FileOutputStream(file);
                } catch (FileNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                boolean b = mSignBitmap.compress(Bitmap.CompressFormat.PNG, 100, f);


                String Tran37s = SPUtils.getString(TicketActivity.this, "Tran37");

                if (b) {
                    //截图成功
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    mSignBitmap.compress(Bitmap.CompressFormat.JPEG, 75, stream);// (0 - 100)压缩文件
                    imageStr1 = Base64utils.baseEncript(mSignBitmap);
                    Log.e("+++++++++imagestr", imageStr1);
                    String tran37 = Tran37s;
                    ticket(tran37, imageStr1);
                }

                }
                break;
            case R.id.mIVSign:
                Imgnum=1;
                WritePadDialog mWritePadDialog = new WritePadDialog(
                        TicketActivity.this, new WriteDialogListener() {
                    @Override
                    public void onPaintDone(Object object) {
                        mSignBitmap = (Bitmap) object;
                        createSignFile();
                        mIVSign.setImageBitmap(mSignBitmap);
                    }
                });
                mWritePadDialog.show();
                break;
        }
    }

    private void ticket(String tran37, String ticket) {
        Map<String, Object> map = new HashMap<>();
        map.put("tran37", tran37);
        map.put("ticket", ticket);
        map.put("mode", 1);
        XUtil.Post(Config.TicketServlet_URL, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);

                TicketBean ticketBean = JSON.parseObject(result, TicketBean.class);
                if ("00".equals(ticketBean.getCode())) {
                    MyApp.getInstance().showToast(ticketBean.getSuccessMessage());
                    startActivity(new Intent(TicketActivity.this, BbposListActivity.class));

                } else if ("99".equals(ticketBean.getCode())) {
                    MyApp.getInstance().showToast(ticketBean.getFailMessage());
                    startActivity(new Intent(TicketActivity.this, BbposListActivity.class));
                }
            }
        });
    }

    private void ticketinfo() throws ParseException {
        //获取Tran2
        Intent intent1 = new Intent();
        intent1.putExtra("Tran2", SPUtils.getString(TicketActivity.this, "Tran2"));
        String Tran2 = SPUtils.getString(TicketActivity.this, "Tran2");
        //获取Tran11
        Intent intent2 = new Intent();
        intent2.putExtra("Tran11", SPUtils.getString(TicketActivity.this, "Tran11"));
        String Tran11 = SPUtils.getString(TicketActivity.this, "Tran11");
        //获取Tran12
        Intent intent3 = new Intent();
        intent3.putExtra("Tran12", SPUtils.getString(TicketActivity.this, "Tran12"));
        String Tran12 = SPUtils.getString(TicketActivity.this, "Tran12");

        String results = Tran12;
        SimpleDateFormat sdf = new SimpleDateFormat("HHmmss");
        SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm:ss");

        String Tran12s = sdf1.format(sdf.parse(results));


        //获取Tran13
        Intent intent4 = new Intent();
        intent4.putExtra("Tran13", SPUtils.getString(TicketActivity.this, "Tran13"));
        String Tran13 = SPUtils.getString(TicketActivity.this, "Tran13");

        String result = Tran13;
        SimpleDateFormat sdfs = new SimpleDateFormat("HHmm");
        SimpleDateFormat sdfs1 = new SimpleDateFormat("HH/mm");
        String Tran13s = sdfs1.format(sdfs.parse(result));


        //获取Tran14
        Intent intent5 = new Intent();
        intent5.putExtra("Tran14", SPUtils.getString(TicketActivity.this, "Tran14"));
        String Tran14 = SPUtils.getString(TicketActivity.this, "Tran14");
        //获取Tran37
        Intent intent6 = new Intent();
        intent6.putExtra("Tran37", SPUtils.getString(TicketActivity.this, "Tran37"));
        String Tran37 = SPUtils.getString(TicketActivity.this, "Tran37");
        //获取Tran38
        Intent intent7 = new Intent();
        intent7.putExtra("Tran38", SPUtils.getString(TicketActivity.this, "Tran38"));
        String Tran38 = SPUtils.getString(TicketActivity.this, "Tran38");
        //获取列表终端号
        Intent intent8 = new Intent();
        intent8.putExtra("ListMacNumber", SPUtils.getString(TicketActivity.this, "ListMacNumber"));
        String MacNumber = SPUtils.getString(TicketActivity.this, "ListMacNumber");
        //获取商户名
        Intent intent9 = new Intent();
        intent9.putExtra("amName", SPUtils.getString(TicketActivity.this, "amName"));
        String amName = SPUtils.getString(TicketActivity.this, "amName");
        //获取商户号
        Intent intent10 = new Intent();
        intent10.putExtra("AmNumber", SPUtils.getString(TicketActivity.this, "AmNumber"));
        String AmNumber = SPUtils.getString(TicketActivity.this, "AmNumber");
        //获取金额
        Intent intent11 = new Intent();
        intent11.putExtra("Amounts", SPUtils.getString(TicketActivity.this, "Amounts"));
        String Amount = SPUtils.getString(TicketActivity.this, "Amounts");

        SimpleDateFormat df = new SimpleDateFormat("yyyy");//设置日期格式
        String year=df.format(new Date());// new Date()为获取当前系统时间


        ticketAmname.setText(amName);
        ticketAmnumber.setText(AmNumber);
        ticketZongduannum.setText(MacNumber);
        ticketBankcard.setText(Tran2);
        ticketPingzhenghnum.setText(Tran11);
        ticketTime.setText(year+"/"+Tran13s + " " + Tran12s);
        ticketJiaoyicankao.setText(Tran37);
        ticketMoney.setText(Amount);
        ticketDate.setText(Tran14);
        ticketShouquannum.setText(Tran38);
    }
}
