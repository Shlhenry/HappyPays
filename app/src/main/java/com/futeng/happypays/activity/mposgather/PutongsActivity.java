package com.futeng.happypays.activity.mposgather;

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
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
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
import com.futeng.happypays.activity.TicketActivity;
import com.futeng.happypays.baowen.iso8583.ISO8583;
import com.futeng.happypays.baowen.iso8583.ISO8583Entity;
import com.futeng.happypays.baowen.iso8583.ISO8583Util;
import com.futeng.happypays.baowen.iso8583.ISOPackageUtil;
import com.futeng.happypays.tools.MyApp;
import com.futeng.happypays.tools.SPUtils;
import com.jhl.bluetooth.ibridge.BluetoothIBridgeDevice;
import com.jhl.jhlblueconn.BlueStateListenerCallback;
import com.jhl.jhlblueconn.BluetoothCommmanager;
import com.mylhyl.circledialog.CircleDialog;
import com.mylhyl.circledialog.callback.ConfigText;
import com.mylhyl.circledialog.params.ProgressParams;
import com.mylhyl.circledialog.params.TextParams;

public class PutongsActivity extends FragmentActivity implements BlueStateListenerCallback {

    @InjectView(R.id.putong_money)
    ImageView putongMoney;
    @InjectView(R.id.edit_pos)
    EditText editPos;
    @InjectView(R.id.text_money)
    TextView textMoney;
    @InjectView(R.id.put_ok)
    Button putOk;
    @InjectView(R.id.parent_putong)
    LinearLayout parentPutong;
    @InjectView(R.id.checkone)
    ImageView checkone;
    @InjectView(R.id.checktwo)
    ImageView checktwo;
    //单击传给消费
    private String type = null;
    //输入的交易金额
    private String posmoney;
    //判断是否单击按钮
    private int change = 0;


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

    static boolean bConnDevice = false;


    //定义Toast显示内容
    final String s1 = "密码为空 ，请输入密码";
    final String s2 = "请输入六位及以上密码";


    public static final String DEVICE_ADDRESS = "device_address";
    public static final String[] DEVICE_ADDRESS_FILETER = null;//new String[]{"liu","a60"}; //null;//;new String[]{""};搜索设备的时候的一个过滤器，为null时表示搜索所有类型

    BluetoothCommmanager BluetoothComm = null;
    private static final long WAIT_TIMEOUT = 15000; //超时时间  单位 毫秒
    private static final int nAmount = 1000; //默认传入金额 1.23元==123
    String message;

    //判断是否连接设备
    int condevice=0;

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
                    new AlertDialog.Builder(PutongsActivity.this)
                            .setTitle("请选择蓝牙设备")
                            .setIcon(android.R.drawable.ic_dialog_info)
                            .setSingleChoiceItems(items, 0,
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                            sousuo.dismiss();

                                            Intent intent = new Intent();
                                            intent.putExtra("ListItemMacsn", SPUtils.getString(PutongsActivity.this, "ListItemMacsn"));
                                            String ListItemMacsn = SPUtils.getString(PutongsActivity.this, "ListItemMacsn");

                                            Log.e("2222////****----", ListItemMacsn);

                                            if (ListItemMacsn.equals(mDevices.get(which).getDeviceName())) {
                                                Log.e("111111////****----", mDevices.get(which).getDeviceAddress() + "666" + mDevices.get(which).getDeviceName());
                                                BluetoothComm.ConnectDevice(mDevices.get(which).getDeviceAddress());
                                            } else {
                                                MyApp.getInstance().showToast("您选择的SN与连接的SN不同，请检查后交易");
                                            }

                                            Log.e("++++////****----", mDevices.get(which).getDeviceAddress() + "666" + mDevices.get(which).getDeviceName());

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
        setContentView(R.layout.activity_putongs);
        ButterKnife.inject(this);

        editPos.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                textMoney.setText(editPos.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                String temp = s.toString();
                int posDot = temp.indexOf(".");
                if (posDot <= 0) return;
                if (temp.length() - posDot - 1 > 2) {
                    s.delete(posDot + 3, posDot + 4);
                } else {
                    return;
                }
            }
        });

        mMainMessageHandler = new MessageHandler(Looper.myLooper());

        //设置当前窗体回调((这个的时候自动启动服务 已经不需要启动了 ))
        BluetoothComm = BluetoothCommmanager.getInstance(this, this);
    }

    @OnClick({R.id.putong_money, R.id.edit_pos, R.id.text_money, R.id.put_ok, R.id.parent_putong, R.id.checkone, R.id.checktwo})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.putong_money:
                finish();
                break;
            case R.id.edit_pos:
                break;
            case R.id.text_money:
                break;
            case R.id.put_ok:
                posmoney = editPos.getText().toString();
                if (TextUtils.isEmpty(posmoney)) {
                    MyApp.getInstance().showToast("请输入交易金额");
                } else if (change == 0) {
                    MyApp.getInstance().showToast("请选择结算类型");
                } else {
                    //确认交易连接蓝牙
                    if(!bOpenDevice){
                        sousuo = new CircleDialog.Builder(this)
                                .setProgressText("正在搜索...")
                                .setProgressStyle(ProgressParams.STYLE_SPINNER)
                                .show();
                        //回调搜索方式
                        BluetoothComm.ScanDevice(DEVICE_ADDRESS_FILETER, 5, 0);
                        Log.e("正在搜索蓝牙设备,超时时间5秒", "正在搜索蓝牙设备,超时时间5秒");
                    }else {
                        MyApp.getInstance().showToast("设备已连接蓝牙，请直接交易");
                    }

                }
                break;
            case R.id.checkone:
                checkone.setImageDrawable(getResources().getDrawable(R.mipmap.checks));
                checktwo.setImageDrawable(getResources().getDrawable(R.mipmap.check));
                change = 1;
                break;
            case R.id.checktwo:
                checktwo.setImageDrawable(getResources().getDrawable(R.mipmap.checks));
                checkone.setImageDrawable(getResources().getDrawable(R.mipmap.check));
                change = 1;
                type = "06";
                break;
            case R.id.parent_putong://单击父控件变换隐藏键盘
                InputMethodManager imm = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(parentPutong.getWindowToken(), 0);
                break;
        }
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
                        // Log.e("对话窗口输入的密码值是+++++", message);

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
                    Toast.makeText(PutongsActivity.this, "取消操作", Toast.LENGTH_SHORT).show();
                    dialog.cancel();

                    return;
                }
            }
        });
        builder.create();

        return builder.show();
    }
    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DIALOG:
                return buildDialog(PutongsActivity.this);
        }
        return null;
    }
    public boolean isNumeric(String str) {

        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);

        if (!isNum.matches()) {
            return false;

        }
        return true;

    }
    //A80机器弹框

    //+++++++++++++所有回调函数+++++++++++++//
    private DialogFragment incard;
    private DialogFragment connectblt;
    private DialogFragment sousuo;

    @Override
    public void onDeviceInfo(Map<String, String> info) {
        //TODO 设备信息
        Message updateMessage = mMainMessageHandler.obtainMessage();
        updateMessage.obj = "";
        updateMessage.what = R.id.btnAPass;
        updateMessage.sendToTarget();
        Log.e("设备信息", "设备信息");
        Set<?> set = info.entrySet();
        Iterator<?> iterator = set.iterator();
        while (iterator.hasNext()) {
            @SuppressWarnings("unchecked")
            Map.Entry<String, String> entry1 = (Map.Entry<String, String>) iterator.next();
            Log.e("+++onDeviceInfo+++", entry1.getKey() + "==" + entry1.getValue());
        }

        //交易的金额传入pos机
        String posmoney = editPos.getText().toString();
        int textContent = (int) ((Double.parseDouble(posmoney)) * 100);
        //启动刷卡
        BluetoothComm.MagnCard(WAIT_TIMEOUT, textContent, 0x03);
    }

    @Override
    public void onTimeout() {
        //TODO 指令超时时间到了
        Log.e("接收超时", "接收超时");
    }

    @Override
    public void onError(int nResult, String MsgData) {
        //TODO 设备信息
        String nReusl = String.format("%02x", nResult);
        Log.e("错误提示", nReusl + "//" + MsgData);
    }

    /*********************************************************************
     * 刷卡加密后返回的结果,
     * 所有银联要求的数据全部返回在当前MAP里面
     *******************************************************************/
    @Override
    public void onReadCardData(Map hashcard) {

        //获取列表终端号
        Intent intent3 = new Intent();
        intent3.putExtra("ListMacNumber", SPUtils.getString(PutongsActivity.this, "ListMacNumber"));
        String MacNumber = SPUtils.getString(PutongsActivity.this, "ListMacNumber");
        //获取登入商户号赋值
        Intent intent4 = new Intent();
        intent4.putExtra("AmNumber", SPUtils.getString(PutongsActivity.this, "AmNumber"));
        String AmNumber = SPUtils.getString(PutongsActivity.this, "AmNumber");

        //TODO 设备信息
        Message updateMessage = mMainMessageHandler.obtainMessage();
        updateMessage.obj = "";
        updateMessage.what = R.id.btnAPass;
        updateMessage.sendToTarget();
        Log.e("加密信息", "加密信息");
        mapcard = (Map<String, String>) hashcard;
        Set<?> set = hashcard.entrySet();
        Iterator<?> iterator = set.iterator();

        Map<String, String> map = new HashMap<String, String>();
        map.put("macSn", "");
        map.put("amNumber", "");
        while (iterator.hasNext()) {
            @SuppressWarnings("unchecked")
            Map.Entry<String, String> entry1 = (Map.Entry<String, String>) iterator.next();
//            showLogMessage(entry1.getKey() + "==" + entry1.getValue());
            map.put(entry1.getKey(), entry1.getValue());
            SPUtils.put(this, "All", entry1.getKey() + "==" + entry1.getValue());
            Intent intent5 = new Intent();
            intent5.putExtra("All", SPUtils.getString(PutongsActivity.this, "All"));
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
        SPUtils.put(this, "Amounts", Amount);
        String ExpireDate = map.get("ExpireDate");        // 卡号有效日期
        String Track55 = map.get("Track55");                // IC卡数据
        String Encrytrack3 = map.get("Encrytrack3");        // 加密三磁道数据
        String PanSeqNo = map.get("PanSeqNo");            // IC卡卡片序列号
        String mcc = "";// 没有自己传，更加大秦那边定义，目前是：T0传06 T1不传
//		String track2 = requestMap.get("track2");				// 2磁道
        String tran0 = ISO8583Util.getTran0();
        Log.e("MSG", "==================2======================");
        String tran2 = PAN; //ISO8583Util.getTran2(Encrytrack2, macZpk);// 得到卡号
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
        String tran41 = ISO8583Util.getTran41(MacNumber);//终端号 8位  // 接口提供00000148
        String tran42 = ISO8583Util.getTran42(AmNumber);//商户号883000000000105
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
        String Tran2 = result.getTran2();
        String Tran11 = result.getTran11();
        String Tran12 = result.getTran12();
        String Tran13 = result.getTran13();
        String Tran14 = result.getTran14();
        String Tran37 = result.getTran37();
        String Tran38 = result.getTran38();

        SPUtils.put(this, "Tran2", Tran2);
        SPUtils.put(this, "Tran11", Tran11);
        SPUtils.put(this, "Tran12", Tran12);
        SPUtils.put(this, "Tran13", Tran13);
        SPUtils.put(this, "Tran14", Tran14);
        SPUtils.put(this, "Tran37", Tran37);
        SPUtils.put(this, "Tran38", Tran38);


        try {
            if (result == null) {
                Log.e("MSG", "获取卡类型失败");
                return;
            } else if (result.getResultCode() != null && "A0".equalsIgnoreCase(result.getResultCode())) {
                result.setMessage("获取验证码失败,请重新签到!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if ("00".equals(result.getResultCode())) {      // 消费成功准备电子小票
            //交易成功断开蓝牙连接跳转电子小票
            startActivity(new Intent(PutongsActivity.this, TicketActivity.class));
            bConnDevice=false;
            Log.e("交易+++成功","交易+++成功");
            //交易成功断开蓝牙连接
            BluetoothComm.DisConnectBlueDevice();
            //释放蓝牙资源
            BluetoothComm.closeResource();
            Log.e("交易---成功","交易---成功");

            Log.e("交易成功", "交易成功");
            Log.e("MSG", result.getMessage());
            Looper.prepare();
            Toast.makeText(PutongsActivity.this, result.getMessage() + "", Toast.LENGTH_SHORT).show();
            Looper.loop();

        } else {
            bOpenDevice=false;
            //  消费失败的话就直接弹出Message信息
            Log.e("交易+++失败","交易+++失败");
            //交易成功断开蓝牙连接
            BluetoothComm.DisConnectBlueDevice();
            //释放蓝牙资源
            BluetoothComm.closeResource();
            Log.e("交易+++失败","交易+++失败");
            Looper.prepare();
            Toast.makeText(this, result.getMessage(), Toast.LENGTH_SHORT).show();
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





        //获取SN赋值
        Intent intent1 = new Intent();
        intent1.putExtra("SN", SPUtils.getString(PutongsActivity.this, "SN"));
        String SN = SPUtils.getString(PutongsActivity.this, "SN");
    }

    //蓝牙搜素回调列表+++++++++++++++++++++++++++++//
    @Override
    public void onDeviceFound(ArrayList<BluetoothIBridgeDevice> mDevices) {
        //TODO 设备信息
        if (Build.VERSION.SDK_INT > 10) {
            if (!isDestroyed()) {
                ArrayList<BluetoothIBridgeDevice> ListName = new ArrayList<BluetoothIBridgeDevice>();
                if (mDevices.size() == 0) {

                    new CircleDialog.Builder(this)
                            .setTitle("查找到无设备")
//                            .configText(new ConfigText() {
//                                @Override
//                                public void onConfig(TextParams params) {
//                                    params.gravity = Gravity.LEFT;
//                                    params.padding = new int[]{50, 50, 50, 50};
//                                }
//                            })
                            .setPositive("确定", null)
                            .show();

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
                        Log.e("onDeviceFound+map", map + "");
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
    public void onLoadMasterKeySucc(Boolean isSucess) {
        //TODO 设备信息
        if (isSucess) {
            Log.e("主密钥设置成功", "主密钥设置成功");

//            String order1 = "41AD77644A6B8027D9BA3500CC42C1B3F41FDF64C2CFE2FDB060EFDD57C3119B25A66C34C987ECE22B36C451C208E0F6BC8E79046DDB06F475298648";
//            byte[] sendBuf1 = hexStr2Bytes(order1);
//            int len = order1.length();
//            Toast.makeText(this,"设置工作密钥",Toast.LENGTH_SHORT).show();
//            BluetoothComm.WriteWorkKey(sendBuf1);
//            Log.e("正在设置工作密钥", "正在设置工作密钥");

        } else {
            Log.e("主密钥设置失败", "主密钥设置失败");
        }
    }

    @Override
    public void onLoadWorkKeySucc(Boolean isSucess) {
        //TODO 设备信息
        if (isSucess) {
            Log.e("工作密钥设置成功", "工作密钥设置成功");
        } else {
            Log.e("工作密钥设置失败", "工作密钥设置失败");
        }
    }

    @Override
    public void getMacSucess(String macdata) {
        //TODO 设备信息设备信息
        Log.e("MAC:", macdata.toString());
    }

    @Override
    public void getBatSucess(String batdata) {
        //TODO 设备信息
        Log.e("电量", batdata.toString());
    }

    @Override
    public void swipCardSucess(String cardNumber) {
        //TODO 设备信息
        Log.e("卡号+PAN:", cardNumber.toString() + "");

        Message updateMessage = mMainMessageHandler.obtainMessage();
        updateMessage.obj = "";
        updateMessage.what = 0x98;
        updateMessage.sendToTarget();
    }

    //===========start蓝牙状态回调================================//
    @Override
    public void onBluetoothIng() {
        //TODO 设备信息
        Log.e("提示:正在连接设备...", "提示:正在连接设备...");
        connectblt = new CircleDialog.Builder(this)
                .setProgressText("正在连接设备...")
                .setProgressStyle(ProgressParams.STYLE_SPINNER)
                .show();
    }

    @Override
    public void onBluetoothConected() {
        //TODO 设备信息
        Log.e("连接蓝牙成功,正在获取SN号...", "连接蓝牙成功,正在获取SN号...");
        connectblt.dismiss();

//        //交易的金额传入pos机
//        String posmoney = editPos.getText().toString();
//        int textContent = (int) ((Double.parseDouble(posmoney)) * 100);
//        //启动刷卡
//        BluetoothComm.MagnCard(WAIT_TIMEOUT, textContent, 0x03);

        bOpenDevice = true;
        BluetoothComm.GetDeviceInfo();

    }

    @Override
    public void onBluetoothConectedFail() {
        //TODO 设备信息
        Log.e("连接蓝牙设备失败...", "连接蓝牙设备失败...");

        new CircleDialog.Builder(this)
                .setTitle("连接蓝牙失败")
                .configText(new ConfigText() {
                    @Override
                    public void onConfig(TextParams params) {
                        params.gravity = Gravity.LEFT;
                        params.padding = new int[]{50, 50, 50, 50};
                    }
                })
                .setPositive("确定", null)
                .show();
    }

    @Override
    public void onBluetoothDisconnected() {
        //TODO 设备信息
        Log.e("蓝牙已断开,请重新连接...", "蓝牙已断开,请重新连接...");
        bOpenDevice = false;
        //蓝牙断开设置为0
        condevice=0;

        BluetoothComm.closeResource();
        new CircleDialog.Builder(this)
                .setTitle("蓝牙已断开")
                .configText(new ConfigText() {
                    @Override
                    public void onConfig(TextParams params) {
                        params.gravity = Gravity.LEFT;
                        params.padding = new int[]{50, 50, 50, 50};
                    }
                })
                .setPositive("确定", null)
                .show();


        Log.e("蓝牙断开bOpenDevice值",bOpenDevice+"");
    }

    @Override
    public void onScanTimeout() {
        //TODO 设备信息
        Log.e("搜索超时", "搜索超时");
        Toast.makeText(this, "搜索超时...", Toast.LENGTH_SHORT).show();
        new CircleDialog.Builder(this)
                .setTitle("搜索超时")
                .configText(new ConfigText() {
                    @Override
                    public void onConfig(TextParams params) {
                        params.gravity = Gravity.LEFT;
                        params.padding = new int[]{50, 50, 50, 50};
                    }
                })
                .setPositive("确定", null)
                .show();
    }

    @Override
    public void onBluetoothPowerOff() {
        //TODO 设备信息
        Log.e("蓝牙关闭", "蓝牙关闭");
        new CircleDialog.Builder(this)
                .setTitle("蓝牙关闭")
                .configText(new ConfigText() {
                    @Override
                    public void onConfig(TextParams params) {
                        params.gravity = Gravity.LEFT;
                        params.padding = new int[]{50, 50, 50, 50};
                    }
                })
                .setPositive("确定", null)
                .show();
    }

    @Override
    public void onBluetoothPowerOn() {
        //TODO 设备信息
        Log.e("蓝牙开启", "蓝牙开启");
        Toast.makeText(this, "蓝牙开启...", Toast.LENGTH_SHORT).show();
    }

    //===========end蓝牙状态回调================================//

    /**
     * 检测到刷卡插入IC卡回调
     **/
    @Override
    public void onDetectIC() {
        //TODO 设备信息
        Log.e("IC卡插入", "IC卡插入");
        incard.dismiss();
    }

    /**
     * 等待刷卡、插卡、挥卡回调
     **/
    @Override
    public void onWaitingForCardSwipe() {
        //TODO 设备信息
        Log.e("请刷卡/插卡/挥卡...", "请刷卡/插卡/挥卡...");
        incard = new CircleDialog.Builder(this)
                .setProgressText("请刷卡/插卡/挥卡......")
                .setProgressStyle(ProgressParams.STYLE_SPINNER)
                .show();
    }

//    @Override
//    protected void onDestroy() {
//
//        BluetoothComm.DisConnectBlueDevice();
//        //关闭释放相关蓝牙资源
//        BluetoothComm.closeResource();
//    	try {
//			Thread.sleep(10);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//        super.onDestroy();
//
//    }
}
