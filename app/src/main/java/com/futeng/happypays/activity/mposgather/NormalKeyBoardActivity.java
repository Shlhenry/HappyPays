package com.futeng.happypays.activity.mposgather;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.Editable;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import com.futeng.happypays.R;
import com.futeng.happypays.baowen.iso8583.ISO8583;
import com.futeng.happypays.baowen.iso8583.ISO8583Entity;
import com.futeng.happypays.baowen.iso8583.ISO8583Util;
import com.futeng.happypays.baowen.iso8583.ISOPackageUtil;
import com.futeng.happypays.tools.SPUtils;
import com.futeng.happypays.view.VirtualKeyboardView;
import com.jhl.bluetooth.ibridge.BluetoothIBridgeDevice;
import com.jhl.jhlblueconn.BlueStateListenerCallback;
import com.jhl.jhlblueconn.BluetoothCommmanager;

//import java.beans.IntrospectionException;

public class NormalKeyBoardActivity extends Activity implements BlueStateListenerCallback {

    @InjectView(R.id.btn_zhumy)
    Button btnZhumy;
    @InjectView(R.id.btn_gongzuo)
    Button btnGongzuo;
    private VirtualKeyboardView virtualKeyboardView;

    private GridView gridView;

    private ArrayList<Map<String, String>> valueList;

    private EditText textAmount;

    private Animation enterAnim;

    private Animation exitAnim;

    private TextView keyboard_ok;

    private byte MF_KEY_IND = 0;
    private byte MKeyIndex = 0;

    private Map<String, String> mapcard = new HashMap<String, String>();

    private Spinner BtnTrace;
    private ArrayAdapter<String> adapter;
    private static final String[] m = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};

    //选取数组的值，把其传递到需要的界面中去
    private int Track_Encry_Model;

    private static final String DEBUG_TAG = "BluetoothTest";
    private static final int DIALOG = 3;

    private boolean bOpenDevice = false;
    private Handler mMainMessageHandler;

    private EditText m_editRecvData, editText;
    Button WriteMainKey, WriteWKey, SetTerid, GetMAc, AddOrder, BtnCiDao, BtnPIN, BtnMAC, sendButton, BtnSearch, BtnInterrupt;

    //定义Toast显示内容
    final String s1 = "密码为空 ，请输入密码";
    final String s2 = "请输入六位及以上密码";


    public static final String DEVICE_ADDRESS = "device_address";
    public static final String[] DEVICE_ADDRESS_FILETER = null;//new String[]{"liu","a60"}; //null;//;new String[]{""};搜索设备的时候的一个过滤器，为null时表示搜索所有类型

    BluetoothCommmanager BluetoothComm = null;
    private static final long WAIT_TIMEOUT = 15000; //超时时间  单位 毫秒
    private static final int nAmount = 1000; //默认传入金额 1.23元==123
    String message;

    /**
     * 十六进制字符串转换成bytes
     */
    private static byte uniteBytes(String src0, String src1) {
        byte b0 = Byte.decode("0x" + src0).byteValue();
        b0 = (byte) (b0 << 4);
        byte b1 = Byte.decode("0x" + src1).byteValue();
        byte ret = (byte) (b0 | b1);
        return ret;
    }

    public static byte[] hexStr2Bytes(String src) {
        int m = 0, n = 0;
        int l = src.length() / 2;
        byte[] ret = new byte[l];
        for (int i = 0; i < l; i++) {
            m = i * 2 + 1;
            n = m + 1;
            ret[i] = uniteBytes(src.substring(i * 2, m), src.substring(m, n));
        }
        return ret;
    }

    private byte[] getBytes(char[] chars) {
        Charset cs = Charset.forName("UTF-8");
        CharBuffer cb = CharBuffer.allocate(chars.length);
        cb.put(chars);
        cb.flip();
        ByteBuffer bb = cs.encode(cb);
        return bb.array();

    }

    // 转化十六进制编码为字符串
    public static String toStringHex(String s) {
        byte[] baKeyword = new byte[s.length() / 2];
        for (int i = 0; i < baKeyword.length; i++) {
            try {
                baKeyword[i] = (byte) (0xff & Integer.parseInt(s.substring(
                        i * 2, i * 2 + 2), 16));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            s = new String(baKeyword, "utf-8");// UTF-16le:Not
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return s;
    }

    //两个密钥的按钮监听
   @OnClick({R.id.btn_zhumy, R.id.btn_gongzuo})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_zhumy:
                String order;
                order = "FD9D0C024B6D001545E5AFB90BDE72CE";
                if (order.length() != 32) {
                    Toast.makeText(NormalKeyBoardActivity.this, "主密钥输入不正确", Toast.LENGTH_SHORT).show();
                }

                byte[] sendBuf = hexStr2Bytes(order);
                Toast.makeText(this,"设置主密钥",Toast.LENGTH_SHORT).show();
                Log.e("主密钥", "设置主密钥上");
                Log.e("+++sendBuf+++", sendBuf + "");
                BluetoothComm.WriteMainKey(sendBuf);
                Log.e("主密钥", "设置主密钥上");
                break;
            case R.id.btn_gongzuo:
                String order1 = "41AD77644A6B8027D9BA3500CC42C1B3F41FDF64C2CFE2FDB060EFDD57C3119B25A66C34C987ECE22B36C451C208E0F6BC8E79046DDB06F475298648";
                byte[] sendBuf1 = hexStr2Bytes(order1);
                int len = order1.length();
                Toast.makeText(this,"设置工作密钥",Toast.LENGTH_SHORT).show();
                BluetoothComm.WriteWorkKey(sendBuf1);
                Log.e("正在设置工作密钥", "正在设置工作密钥");
                break;
        }
    }



    class MessageHandler extends Handler {
        private long mLogCount = 0;

        public MessageHandler(Looper looper) {
            super(looper);
        }

        @SuppressWarnings("unchecked")
        @Override
        public void handleMessage(Message msg) {

            switch (msg.what) {

                case 0x99:
                    final ArrayList<BluetoothIBridgeDevice> mDevices = (ArrayList<BluetoothIBridgeDevice>) msg.obj;
                    final String[] items = new String[mDevices.size()];

                    for (int i = 0; i < mDevices.size(); i++) {
                        items[i] = mDevices.get(i).getDeviceName();
                    }
                    new AlertDialog.Builder(NormalKeyBoardActivity.this)
                            .setTitle("请选择蓝牙设备")
                            .setIcon(android.R.drawable.ic_dialog_info)
                            .setSingleChoiceItems(items, 0,
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                            //时间
                                            long end = System.currentTimeMillis();
                                            SimpleDateFormat formatter = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss:SSS");
                                            String hms1 = formatter.format(end);
                                            Log.e("正在连接设备的起始时间==", hms1);
                                            //显示
                                            showLogMessage("T1== " + hms1);
                                            showLogMessage("正在连接:" + mDevices.get(which).getDeviceName() + "==" + mDevices.get(which).getDeviceAddress());

                                            BluetoothComm.ConnectDevice(mDevices.get(which).getDeviceAddress());
                                            Log.e("++++////****----", mDevices.get(which).getDeviceAddress());

//                                            String order;
//                                            //order = "C2087004C5673F4DB55484DAB21C8F5E";
//                                            order = "FD9D0C024B6D001545E5AFB90BDE72CE";
//                                            if (order.length() != 32) {
//                                                Toast.makeText(NormalKeyBoardActivity.this, "主密钥输入不正确", Toast.LENGTH_SHORT).show();
//                                            }
//                                            byte[] sendBuf = hexStr2Bytes(order);
//                                            showLogMessage("正在设置主密钥..");
//                                            Log.e("主密钥", "设置主密钥上");
//                                            Log.e("+++sendBuf+++", sendBuf + "");
//                                            BluetoothComm.WriteMainKey(sendBuf);
//                                            Log.e("主密钥", "设置主密钥上");
                                        }
                                    }
                            )
                            .setNegativeButton("取消",  /*null*/new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    // TODO Auto-generated method stub
                                    dialog.cancel();
                                }
                            })
                            .create()
                            .show();//AlertDialog.Builder.create().show()相当于 Dialog.show()
                    break;
                case 0x98:
                    showDialog(DIALOG);
                    break;
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_normal_key_board);
        ButterKnife.inject(this);

        initAnim();

        initView();

        valueList = virtualKeyboardView.getValueList();

        mMainMessageHandler = new MessageHandler(Looper.myLooper());

        //设置当前窗体回调((这个的时候自动启动服务 已经不需要启动了 ))
        BluetoothComm = BluetoothCommmanager.getInstance(this, this);

        //回调搜索方式
        BluetoothComm.ScanDevice(DEVICE_ADDRESS_FILETER, 5, 0);
        showLogMessage("正在搜索蓝牙设备,超时时间5秒");

    }

    /**
     * 数字键盘显示动画
     */
    private void initAnim() {

        enterAnim = AnimationUtils.loadAnimation(this, R.anim.push_bottom_in);
        exitAnim = AnimationUtils.loadAnimation(this, R.anim.push_bottom_out);
    }

    private void initView() {

        textAmount = (EditText) findViewById(R.id.textAmount);
        // 设置不调用系统键盘
        if (Build.VERSION.SDK_INT <= 10) {
            textAmount.setInputType(InputType.TYPE_NULL);
        } else {
            this.getWindow().setSoftInputMode(
                    WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
            try {
                Class<EditText> cls = EditText.class;
                Method setShowSoftInputOnFocus;
                setShowSoftInputOnFocus = cls.getMethod("setShowSoftInputOnFocus",
                        boolean.class);
                setShowSoftInputOnFocus.setAccessible(true);
                setShowSoftInputOnFocus.invoke(textAmount, false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        virtualKeyboardView = (VirtualKeyboardView) findViewById(R.id.virtualKeyboardView);
        virtualKeyboardView.getLayoutBack().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                virtualKeyboardView.startAnimation(exitAnim);
                virtualKeyboardView.setVisibility(View.GONE);
            }
        });
        virtualKeyboardView.getKeyboard_ok().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if ("".equals(textAmount.getText().toString())) {
                    Toast.makeText(NormalKeyBoardActivity.this, "请先输入金额，再启动刷卡", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(NormalKeyBoardActivity.this, "正在启动，请等待5秒", Toast.LENGTH_LONG).show();
                    SPUtils.put(getApplicationContext(), "textAmount", textAmount.getText().toString());

                    Log.e("MSG", "++++++" + textAmount.getText().toString());

                    //获取登入手机号赋值
                    Intent intent = new Intent();
                    intent.putExtra("textAmount", SPUtils.getString(NormalKeyBoardActivity.this, "textAmount"));
                    String textAmount = SPUtils.getString(NormalKeyBoardActivity.this, "textAmount");

                    int textContent = (int) ((Double.parseDouble(textAmount)) * 100);

                    //启动刷卡
                    //BluetoothComm.MagnCard(WAIT_TIMEOUT, nAmount, 0x03);
                    BluetoothComm.MagnCard(WAIT_TIMEOUT, textContent, 0x03);

                }
            }
        });

        gridView = virtualKeyboardView.getGridView();
        gridView.setOnItemClickListener(onItemClickListener);

        textAmount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                virtualKeyboardView.setFocusable(true);
                virtualKeyboardView.setFocusableInTouchMode(true);

                virtualKeyboardView.startAnimation(enterAnim);
                virtualKeyboardView.setVisibility(View.VISIBLE);
            }
        });

    }

    private AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

            if (position < 11 && position != 9) {    //点击0~9按钮

                String amount = textAmount.getText().toString().trim();
                amount = amount + valueList.get(position).get("name");

                textAmount.setText(amount);

                Editable ea = textAmount.getText();
                textAmount.setSelection(ea.length());
            } else {

                if (position == 9) {      //点击退格键
                    String amount = textAmount.getText().toString().trim();
                    if (!amount.contains(".")) {
                        amount = amount + valueList.get(position).get("name");
                        textAmount.setText(amount);

                        Editable ea = textAmount.getText();
                        textAmount.setSelection(ea.length());
                    }
                }

                if (position == 11) {      //点击退格键
                    String amount = textAmount.getText().toString().trim();
                    if (amount.length() > 0) {
                        amount = amount.substring(0, amount.length() - 1);
                        textAmount.setText(amount);

                        Editable ea = textAmount.getText();
                        textAmount.setSelection(ea.length());
                    }
                }
            }
        }
    };

    public void showLogMessage(String msg) {
        Message updateMessage = mMainMessageHandler.obtainMessage();
        updateMessage.obj = msg;
        updateMessage.what = R.id.editRecvData;
        updateMessage.sendToTarget();
    }

    @Override
    protected void onDestroy() {

        //关闭释放相关蓝牙资源
        BluetoothComm.closeResource();

        super.onDestroy();
    }

    @Override
    public void onTimeout() {
        // TODO Auto-generated method stub
        showLogMessage("接收超时");
    }

    @Override
    public void onReadCardData(Map hashcard) {
        Message updateMessage = mMainMessageHandler.obtainMessage();
        updateMessage.obj = "";
        updateMessage.what = R.id.btnAPass;
        updateMessage.sendToTarget();

        showLogMessage("加密信息：");
        mapcard = (Map<String, String>) hashcard;
        Set<?> set = hashcard.entrySet();
        Iterator<?> iterator = set.iterator();

        Map<String, String> map = new HashMap<String, String>();
        map.put("macSn", "");
        map.put("amNumber", "");
        while (iterator.hasNext()) {
            @SuppressWarnings("unchecked")
            Map.Entry<String, String> entry1 = (Map.Entry<String, String>) iterator.next();
            showLogMessage(entry1.getKey() + "==" + entry1.getValue());
            map.put(entry1.getKey(), entry1.getValue());
            SPUtils.put(this, "All", entry1.getKey() + "==" + entry1.getValue());
            Intent intent = new Intent();
            intent.putExtra("All", SPUtils.getString(NormalKeyBoardActivity.this, "All"));
        }
        Log.e("++*/+", map.get("Pinblock"));
        Log.e("map", map.toString());

        // TODO  =================================================
        String macZpk = "5202B6CB9D16515D40ECA2D5B55B1C37";// ZPK   // 接口提供
        String macZak = "5202B6CB9D16515D40ECA2D5B55B1C37";//ZAK    // 接口提供
        //		String workKey = requestMap.get("workKey");				// 磁道随机数 IC卡
        String PAN = map.get("PAN");                        // 卡号
        String Encrytrack2 = map.get("Encrytrack2");        // 加密二磁道数据
        String Pinblock = map.get("Pinblock");            // PLOCK （8个字节数据 ） 52域
//		String Downgrad = map.get("Downgrad");			// 卡模式 ==0 正常  1:降级
        String CardType = map.get("CardType");            // 卡类型 ==0:磁条卡 1：IC卡 2：挥卡
        String Amount = map.get("Amount");                // 交易金额
        String ExpireDate = map.get("ExpireDate");        // 卡号有效日期
        String Track55 = map.get("Track55");                // IC卡数据
        String Encrytrack3 = map.get("Encrytrack3");        // 加密三磁道数据
        String PanSeqNo = map.get("PanSeqNo");            // IC卡卡片序列号
        String mcc = "";// 没有自己传，更加大秦那边定义，目前是：T0传06

//		String track2 = requestMap.get("track2");				// 2磁道
        String tran0 = ISO8583Util.getTran0();
        Log.e("MSG", "==================2======================");
        String tran2 =  PAN; //ISO8583Util.getTran2(Encrytrack2, macZpk);// 得到卡号
        Log.e("MSG", "PAN:" + PAN);
        Log.e("MSG", "卡号:" + tran2);
        if (PAN.equals(tran2)) {
            Log.e("MSG", "卡号解密结果：true");
        }
        Log.e("MSG", "==================2======================");
        String tran3 = ISO8583Util.getTran3();
        String tran4 = ISO8583Util.getTran4(Amount);            // 消费金额
        String tran11 = ISO8583Util.getTran11("000001");        // 流水 6位   接口提供
        String tran14 = ISO8583Util.getTran14(ExpireDate);                // 卡号有效日期（ExpireDate）
        String tran23 = ISO8583Util.getTran23(PanSeqNo);                // IC卡卡片序列号（PanSeqNo）

        String tran25 = ISO8583Util.getTran25();
        String tran26 = ISO8583Util.getTran26();
        String tran35 = Encrytrack2;//ISO8583Util.getTran35(Encrytrack2);            // 2磁道
        String tran36 = Encrytrack3;//ISO8583Util.getTran36(Encrytrack3);                // 3磁道
        String tran41 = ISO8583Util.getTran41("00000102");//终端号 8位  // 接口提供
        String tran42 = ISO8583Util.getTran42("883000000000105");//商户号
        String tran47 = ISO8583Util.getTran47(mcc);//mcc
        String tran49 = ISO8583Util.getTran49();//
        Log.e("MSG", "==================52======================");
        String tran52 = Pinblock;//ISO8583Util.getTran52(macZpk, Pinblock, tran2);    // 密码
        Log.e("MSG", "==================52======================" + tran52);
        String tran53 = ISO8583Util.getTran53(tran52);
        String tran55 = ISO8583Util.getTran55(Track55);                    // IC卡数据(Track55)
        String tran22 = ISO8583Util.getTran22(tran52, tran55);

        Log.e("MSG", "==================22======================" + tran22);
        String tran58 = ISO8583Util.getTran58();
        String tran60 = ISO8583Util.getTran60(tran11, CardType);

        ISO8583Entity entity = new ISO8583Entity();
        entity.setTran0(tran0);
        entity.setTran2(tran2);
        entity.setTran3(tran3);
        entity.setTran4(tran4);
        entity.setTran11(tran11);
        entity.setTran14(tran14);
        entity.setTran23(tran23);
        entity.setTran22(tran22);
        entity.setTran25(tran25);
        entity.setTran26(tran26);
        entity.setTran35(tran35);
        entity.setTran36(tran36);
        entity.setTran41(tran41);
        entity.setTran42(tran42);
        entity.setTran47(tran47);
        entity.setTran49(tran49);
        entity.setTran52(Pinblock);
        entity.setTran53(tran53);
        entity.setTran55(tran55);
        entity.setTran58(tran58);
        entity.setTran60(tran60);
        entity.setMacZak(macZak);
        ISO8583 result = null;
        result = ISOPackageUtil.iso8583IC(entity);
        try {
            if (result == null) {
                Log.e("MSG", "获取卡类型失败");
                return ;
            }
            else if (result.getResultCode() != null && "A0".equalsIgnoreCase(result.getResultCode())) {
                result.setMessage("获取验证码失败,请重新签到!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if ("00".equals(result.getResultCode())) {      // 消费成功准备电子小票
            Log.e("交易成功","交易成功");
            Log.e("MSG", result.getMessage());
            Looper.prepare();
            Toast.makeText(NormalKeyBoardActivity.this,result.getMessage()+"",Toast.LENGTH_SHORT).show();
            Looper.loop();
            // 2(result.getTran2())：卡号 37(result.getTran37())：参考号

            //  catch (InvocationTargetException e) {
            //    e.printStackTrace();
            //  }
            //catch (IntrospectionException e) {
            //  e.printStackTrace();
            // }
        } else {    //  消费失败的话就直接弹出Message信息
            Looper.prepare();
            Toast.makeText(this,result.getMessage(),Toast.LENGTH_SHORT).show();
            Looper.loop();
        }
        // TODO  =================================================
        SPUtils.put(this, "Pinblock", map.get("Pinblock"));
        SPUtils.put(this, "Downgrad", map.get("Downgrad"));
        SPUtils.put(this, "PAN", map.get("PAN"));
        SPUtils.put(this, "Encrytrack2", map.get("Encrytrack2"));
        SPUtils.put(this, "CardType", map.get("CardType"));
        SPUtils.put(this, "Amount", map.get("Amount"));
        SPUtils.put(this, "ExpireDate", map.get("ExpireDate"));
        SPUtils.put(this, "Encrytrack3", map.get("Encrytrack3"));
        SPUtils.put(this, "Track55", map.get("Track55"));
        SPUtils.put(this, "PanSeqNo", map.get("PanSeqNo"));

        //获取登入商户号赋值
        Intent intent = new Intent();
        intent.putExtra("AmNumber", SPUtils.getString(NormalKeyBoardActivity.this, "AmNumber"));
        String AmNumber = SPUtils.getString(NormalKeyBoardActivity.this, "AmNumber");
        //获取SN赋值
        Intent intent1 = new Intent();
        intent.putExtra("SN", SPUtils.getString(NormalKeyBoardActivity.this, "SN"));
        String SN = SPUtils.getString(NormalKeyBoardActivity.this, "SN");
    }



    @Override

    public void onDeviceFound(ArrayList<BluetoothIBridgeDevice> mDevices) {//

        if (Build.VERSION.SDK_INT > 10) {


            if (!isDestroyed()) {
                ArrayList<BluetoothIBridgeDevice> ListName = new ArrayList<BluetoothIBridgeDevice>();

                if (mDevices.size() == 0) {
                    showLogMessage("查找到无设备");
                    return;
                }


                synchronized (mDevices) {


                    for (BluetoothIBridgeDevice device : mDevices) {
                        String map;
                        if (device.getDeviceName() != null) {
                            map = device.getDeviceName() + "=" + device.getDeviceAddress();
                        } else {
                            map = "unknown" + "=" + device.getDeviceAddress();
                        }
                        showLogMessage(map);
                        Log.e("MSG", map);
                        ListName.add(device);
                    }

                    //弹出选择对话框
                    if (mDevices.size() == 0)
                        return;

                    Message updateMessage = mMainMessageHandler.obtainMessage();
                    updateMessage.obj = ListName;
                    updateMessage.what = 0x99;
                    updateMessage.sendToTarget();
                }

            }

        } else {
            Log.d("onDeviceFound", "is Destroyed");
        }
    }

    @Override
    public void onDeviceInfo(Map<String, String> info) {
        // TODO Auto-generated method stub
        Message updateMessage = mMainMessageHandler.obtainMessage();
        updateMessage.obj = "";
        updateMessage.what = R.id.btnAPass;
        updateMessage.sendToTarget();

        showLogMessage("设备信息:");

        Set<?> set = info.entrySet();
        Iterator<?> iterator = set.iterator();

        Map<String, String> map1 = new HashMap<String, String>();


        while (iterator.hasNext()) {
            @SuppressWarnings("unchecked")
            Map.Entry<String, String> entry1 = (Map.Entry<String, String>) iterator.next();
            map1.put(entry1.getKey(), entry1.getValue());
            showLogMessage(entry1.getKey() + "==" + entry1.getValue());
            Log.e(DEBUG_TAG, entry1.getKey() + "==" + entry1.getValue());
        }
        Log.e("+++++-*/-*///-*-**/+", map1.get("SN"));
        SPUtils.put(this, "SN", map1.get("SN"));
    }

    @Override
    public void onError(int nResult, String MsgData) {
        // TODO Auto-generated method stub
        String nReusl = String.format("%02x", nResult);
        Toast.makeText(this,"错误提示:" + nReusl + ":" + MsgData,Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onLoadMasterKeySucc(Boolean isSucess) {
        if (isSucess){
            Log.e("主密钥设置成功", "主密钥设置完成，正准备设置工作密钥");
            //String order1 = "3A0FDD509179A971B63AC16AA16F467E248EB466E10A5673D6721FC6DC09CE5612CCCDC8545007053EA440AEAB458C8556C9FB3EB61E35742FE0DFEF";
//            String order1 = "41AD77644A6B8027D9BA3500CC42C1B3F41FDF64C2CFE2FDB060EFDD57C3119B25A66C34C987ECE22B36C451C208E0F6BC8E79046DDB06F475298648";
//            byte[] sendBuf1 = hexStr2Bytes(order1);
//            int len = order1.length();
//            Toast.makeText(this,"设置工作密钥",Toast.LENGTH_SHORT).show();
//            BluetoothComm.WriteWorkKey(sendBuf1);
//            Log.e("开始设置工作密钥", "开始设置工作密钥");
        }else {
            Log.e("主密钥设置失败", "主密钥设置失败");
        }
    }

    @Override
    public void onLoadWorkKeySucc(Boolean isSucess) {
        if (isSucess)
            Log.e("工作密钥设置成功", "工作密钥设置成功");
        else
            Log.e("工作密钥设置失败", "工作密钥设置失败");
    }

    @Override
    public void getMacSucess(String macdata) {
        // TODO Auto-generated method stub
        Toast.makeText(this,"MAC"+macdata.toString(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void swipCardSucess(String cardNumber) {
        // TODO Auto-generated method stub
        Toast.makeText(this,"PAN"+cardNumber.toString(),Toast.LENGTH_SHORT).show();
        Message updateMessage = mMainMessageHandler.obtainMessage();
        updateMessage.obj = "";
        updateMessage.what = 0x98;
        updateMessage.sendToTarget();
    }

    @Override
    public void onBluetoothIng() {
        // TODO Auto-generated method stub
        Toast.makeText(this,"提示：正在连接设备",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBluetoothConected() {
        // TODO Auto-generated method stub
        Toast.makeText(this,"连接蓝牙成功，正在获取SN号",Toast.LENGTH_SHORT).show();
        bOpenDevice = true;
        BluetoothComm.GetDeviceInfo();
    }

    @Override
    public void onBluetoothConectedFail() {
        // TODO Auto-generated method stub
        Toast.makeText(this,"连接蓝牙设备失败",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBluetoothDisconnected() {
        // TODO Auto-generated method stub
        Toast.makeText(this,"蓝牙已断开，请重新连接。。。",Toast.LENGTH_SHORT).show();
        bOpenDevice = false;
    }

    @Override
    public void onScanTimeout() {
        // TODO Auto-generated method stub
        Toast.makeText(this,"搜索超时",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBluetoothPowerOff() {
        Toast.makeText(this,"蓝牙关闭",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBluetoothPowerOn() {
        // TODO Auto-generated method stub
        Toast.makeText(this,"蓝牙开启",Toast.LENGTH_SHORT).show();
    }

    /**
     * 等待刷卡、插卡、挥卡回调
     **/
    @Override
    public void onWaitingForCardSwipe() {
        showLogMessage("请刷卡/插卡/挥卡...");
    }

    /**
     * 检测到刷卡插入IC卡回调
     **/
    @Override
    public void onDetectIC() {
        showLogMessage("IC卡插入...");
    }

    @Override
    public void getBatSucess(String batdata) {
        // TODO Auto-generated method stub
        showLogMessage("电量:" + batdata.toString());
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DIALOG:
                return buildDialog(NormalKeyBoardActivity.this);
        }
        return null;
    }

    //A80机器弹框
    private Dialog buildDialog(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        final View textEntryView = inflater.inflate(
                R.layout.main, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setIcon(R.drawable.alert_dialog_icon);
        builder.setTitle(R.string.alert_dialog_text_entry);
        builder.setView(textEntryView);
        builder.setPositiveButton(R.string.alert_dialog_ok,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        //setTitle("点击了对话框上的确定按钮");
                        //获取数据
                        final EditText password = (EditText) textEntryView.findViewById(R.id.password);//曾经出现空指针，加上textEntryView.指明是哪个XML文件中的值
                        message = password.getText().toString();

                        if (!isNumeric(message)) {
                            Toast.makeText(getApplicationContext(), "密码非法,请重新输入密码",
                                    Toast.LENGTH_SHORT).show();

                            Message updateMessage = mMainMessageHandler.obtainMessage();
                            updateMessage.obj = "";
                            updateMessage.what = 0x98;
                            updateMessage.sendToTarget();
                            return;
                        } else if (message.length() < 6) {
                            Toast t1 = Toast.makeText(getApplicationContext(), s2, Toast.LENGTH_SHORT);
                            t1.show();
                            password.setText("");
                            return;
                        } else if (message == null) {
                            Toast t1 = Toast.makeText(getApplicationContext(), s1, Toast.LENGTH_SHORT);
                            t1.show();

                            password.setText("");

                            return;
                        } else if (message.length() > 5 && message.length() < 13) {
                            //输入密码后,进行数据加密
                            BluetoothComm.InputPassword(message, message.length());
                            password.setText("");

                        }
                        // Log.e("MSG","对话窗口输入的密码值是+++++", message);

                    }
                });
        builder.setNegativeButton(R.string.alert_dialog_cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                final EditText password = (EditText) textEntryView.findViewById(R.id.password);//曾经出现空指针，加上textEntryView.指明是哪个XML文件中的值
                message = password.getText().toString();
                if (message == null) {
                    // Toast.makeText(this, "密码为空，请输入密码", Toast.LENGTH_SHORT).show();
                    Toast t1 = Toast.makeText(getApplicationContext(), "取消操作", Toast.LENGTH_SHORT);
                    t1.show();
                    dialog.cancel();

                    password.setText("");

                    return;
                } else {
                    password.setText("");
                    Toast.makeText(NormalKeyBoardActivity.this, "取消操作", Toast.LENGTH_SHORT).show();
                    dialog.cancel();

                    return;
                }
            }
        });
        builder.create();

        return builder.show();
    }

    public boolean isNumeric(String str) {

        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);

        if (!isNum.matches()) {
            return false;

        }
        return true;

    }
}
