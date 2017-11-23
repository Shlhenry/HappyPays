package com.futeng.happypays.activity.bluetoothblt;

/**
 * Created by Administrator on 2017/3/22.
 *
 * 此类废弃 不做引用
 *
 */
import android.annotation.TargetApi;
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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.futeng.happypays.R;
import com.futeng.happypays.activity.MainActivity;
import com.futeng.happypays.tools.SPUtils;
import com.jhl.bluetooth.ibridge.BluetoothIBridgeDevice;
import com.jhl.jhlblueconn.BlueStateListenerCallback;
import com.jhl.jhlblueconn.BluetoothCommmanager;

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


public class Blt extends Activity implements BlueStateListenerCallback {

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

    class MessageHandler extends Handler {
        private long mLogCount = 0;

        public MessageHandler(Looper looper) {
            super(looper);
        }

        @SuppressWarnings("unchecked")
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case R.id.editRecvData:
                    if (mLogCount > 100) {
                        mLogCount = 0;
                        m_editRecvData.setText("");
                    }
                    String messageString = (String) (msg.obj);
                    int cursor = m_editRecvData.getSelectionStart();
                    m_editRecvData.getText().insert(cursor, messageString + "\n");
                    ++mLogCount;
                    break;
                case R.id.btnAPass:
                    m_editRecvData.setText("");
                    break;
                case 0x99:

                    m_editRecvData.setText("");
                    final ArrayList<BluetoothIBridgeDevice> mDevices = (ArrayList<BluetoothIBridgeDevice>) msg.obj;
                    final String[] items = new String[mDevices.size()];

                    for (int i = 0; i < mDevices.size(); i++) {
                        items[i] = mDevices.get(i).getDeviceName();
                    }
                    new AlertDialog.Builder(Blt.this)
                            .setTitle("请选择蓝牙设备")
                            .setIcon(android.R.drawable.ic_dialog_info)
                            .setSingleChoiceItems(items, 0,
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
//                                            //时间
//                                            long end = System.currentTimeMillis();
//                                            SimpleDateFormat formatter = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss:SSS");
//                                            String hms1 = formatter.format(end);
//                                            Log.e("正在连接设备的起始时间==", hms1);
//                                            //显示
//                                            showLogMessage("T1== " + hms1);
//                                            showLogMessage("正在连接:" + mDevices.get(which).getDeviceName() + "==" + mDevices.get(which).getDeviceAddress());
                                            BluetoothComm.ConnectDevice(mDevices.get(which).getDeviceAddress());
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
        setContentView(R.layout.blt);
        mMainMessageHandler = new MessageHandler(Looper.myLooper());
        m_editRecvData = (EditText) findViewById(R.id.editRecvData);
        //搜索断开设备
        BtnSearch = ((Button) findViewById(R.id.btnSearch));
        BtnInterrupt = ((Button) findViewById(R.id.btnInterrupt));
//        WriteMainKey = ((Button) findViewById(R.id.btnWriteMainKey));
//        WriteMainKey.setOnClickListener(btnClick);
//        WriteWKey = ((Button) findViewById(R.id.btnWriteWKey));
//        WriteWKey.setOnClickListener(btnClick);
//        GetMAc = ((Button) findViewById(R.id.btnGetMAc));
//        GetMAc.setOnClickListener(btnClick);

        ((Button) findViewById(R.id.btnAPass)).setOnClickListener(btnClick);
        ((Button) findViewById(R.id.btndispost)).setOnClickListener(btnClick);
 //       ((Button) findViewById(R.id.btnGetBatty)).setOnClickListener(btnClick);

        BtnSearch.setOnClickListener(btnClick);

        BtnInterrupt.setOnClickListener(btnClick);

        //设置当前窗体回调((这个的时候自动启动服务 已经不需要启动了 ))
        BluetoothComm = BluetoothCommmanager.getInstance(this, this);


        //回调搜索方式
        BluetoothComm.ScanDevice(DEVICE_ADDRESS_FILETER, 5, 0);
        showLogMessage("正在搜索蓝牙设备,超时时间5秒");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Inflate the menu; this adds items to the action bar if it is present.
        super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case R.id.menu_find:
                if (!bOpenDevice) {
                    Message updateMessage = mMainMessageHandler.obtainMessage();
                    updateMessage.obj = "";
                    updateMessage.what = R.id.btnAPass;
                    updateMessage.sendToTarget();
                    showLogMessage("正在搜索蓝牙设备,超时时间5秒");
                    BluetoothComm.ScanDevice(DEVICE_ADDRESS_FILETER, 5, 0);


                } else {
                    showLogMessage("请先断开连接");
                }
                break;
            case R.id.menu_disconnect:
                showLogMessage("正在断开连接...");
                BluetoothComm.DisConnectBlueDevice();
                break;

        }
        return true;
    }


    public void showLogMessage(String msg) {
        Message updateMessage = mMainMessageHandler.obtainMessage();
        updateMessage.obj = msg;
        updateMessage.what = R.id.editRecvData;
        updateMessage.sendToTarget();
    }

    public static String hexStr2Str(String hexStr) {
        String str = "0123456789ABCDEF";
        char[] hexs = hexStr.toCharArray();
        byte[] bytes = new byte[hexStr.length() / 2];
        int n;
        for (int i = 0; i < bytes.length; i++) {
            n = str.indexOf(hexs[2 * i]) * 16;
            n += str.indexOf(hexs[2 * i + 1]);
            bytes[i] = (byte) (n & 0xff);
        }
        return new String(bytes);
    }

    public static byte[] strToBcd(String asc, Integer mode) {
        int len = asc.length();
        int mod = len % 2;
        if (mod != 0) {
            if (mode == 1) {
                asc = asc + "0";
            } else {
                asc = "0" + asc;
            }
            len = asc.length();
        }
        byte abt[] = new byte[len];
        if (len >= 2) {
            len = len / 2;
        }
        byte bbt[] = new byte[len];
        abt = asc.getBytes();
        int j, k;
        for (int p = 0; p < asc.length() / 2; p++) {
            if ((abt[2 * p] >= '0') && (abt[2 * p] <= '9')) {
                j = abt[2 * p] - '0';
            } else if ((abt[2 * p] >= 'a') && (abt[2 * p] <= 'z')) {
                j = abt[2 * p] - 'a' + 0x0a;
            } else {
                j = abt[2 * p] - 'A' + 0x0a;
            }

            if ((abt[2 * p + 1] >= '0') && (abt[2 * p + 1] <= '9')) {
                k = abt[2 * p + 1] - '0';
            } else if ((abt[2 * p + 1] >= 'a') && (abt[2 * p + 1] <= 'z')) {
                k = abt[2 * p + 1] - 'a' + 0x0a;
            } else {
                k = abt[2 * p + 1] - 'A' + 0x0a;
            }

            int a = (j << 4) + k;
            byte b = (byte) a;
            bbt[p] = b;
        }
        return bbt;
    }

    public boolean isNumeric(String str) {

        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);

        if (!isNum.matches()) {
            return false;

        }
        return true;

    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DIALOG:
                return buildDialog(Blt.this);
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
                    Toast.makeText(Blt.this, "取消操作", Toast.LENGTH_SHORT).show();
                    dialog.cancel();

                    return;
                }
            }
        });
        builder.create();

        return builder.show();
    }


    private View.OnClickListener btnClick = new View.OnClickListener() {
        @SuppressWarnings("unused")
        @Override
        public void onClick(View v) {
            switch (v.getId()) {

                //二次论证
            /*case R.id.btndispost1:{
		    	 showLogMessage("正在论证IC卡参数..");
				//	String order ="23000000000000";
				String order ="9F26084D2F424C75D65AAC9F2701809F101307010103A0B802010A0100000000008D271C9E9F370451AF329E9F36020" +
								"01A9505008004E8009A031601259C01009F02060000000001005F2" +
									"A02015682027C009F1A0201569F03060000000000009F3303E0E1C0";


					byte[] sendBuf = hexStr2Bytes(order);
					BluetoothComm.ProofIcData(sendBuf.length,sendBuf);
				}

				break;*/


                case R.id.btnSearch: {
                    if (!bOpenDevice) {
                        Message updateMessage = mMainMessageHandler.obtainMessage();
                        updateMessage.obj = "";
                        updateMessage.what = R.id.btnAPass;
                        updateMessage.sendToTarget();
                        showLogMessage("正在搜索蓝牙设备,超时时间5秒");
                        BluetoothComm.ScanDevice(DEVICE_ADDRESS_FILETER, 5, 0);


                    } else {
                        showLogMessage("请先断开连接");
                    }

                }
                break;

                case R.id.btnInterrupt: {
                    showLogMessage("正在断开连接...");
                    BluetoothComm.DisConnectBlueDevice();

                }
                break;

                //有密码有金额
                case R.id.btnAPass: {
                    m_editRecvData.setText("");
                    showLogMessage("请输入金额+请刷卡/插卡+交易密码...");
                    /********************************************************************
                     函 数 名：MagnCard
                     功能描述：MPO 设备上输提 刷卡      无输入金额  无密码（例如信用卡预授权完成等交易）
                     入口参数：
                     long 	timeout 		--刷卡交易超时时间(毫秒)
                     long   lAmount         ---交易金额,如果需要自己传入金额,赋值进入即可(如需要MPOS填写默认0即可)
                     int     nMtype          --刷卡类型
                     0x01  设备上输提示输入金额 刷卡  输入密码
                     0x02 MPO 设备上输提 示输入金额 刷卡  无密码
                     0x03 MPO 设备上输提 刷卡  + 输入密码   无输入金额（例如查询余额）
                     0x04  MPO 设备上输提 刷卡      无输入金额  无密码（例如信用卡预授权完成等交易）
                     返回说明：
                     **********************************************************/


                    //获取登入手机号赋值
                    Intent intent = new Intent();
                    intent.putExtra("textAmount", SPUtils.getString(Blt.this, "textAmount"));
                    String textAmount= SPUtils.getString(Blt.this, "textAmount");

                    int textContent= (int) ((Double.parseDouble(textAmount))*100);


                    //启动刷卡
                    //BluetoothComm.MagnCard(WAIT_TIMEOUT, nAmount, 0x03);
                    BluetoothComm.MagnCard(WAIT_TIMEOUT, textContent, 0x03);


                }
                break;


//                case R.id.btnWriteMainKey: {
//                    String order;
//                    order = "0B8673D9806DFDA720A886F2B510CD1A";
//                    if (order.length() != 32) {
//                        Toast.makeText(Blt.this, "主密钥输入不正确", Toast.LENGTH_SHORT).show();
//                        break;
//                    }
//
//                    byte[] sendBuf = hexStr2Bytes(order);
//                    showLogMessage("正在设置主密钥..");
//                    BluetoothComm.WriteMainKey(sendBuf);
//                }
//                break;
//                case R.id.btnWriteWKey: {
//                    // 工作密钥明文:  60 个字节
//                    String order = "41AD77644A6B8027D9BA3500CC42C1B358BADA0541AD77644A6B8027D9BA3500CC42C1B358BADA0541AD77644A6B8027D9BA3500CC42C1B358BADA05";
//
//
//                    byte[] sendBuf = hexStr2Bytes(order);
//                    int len = order.length();
//                    showLogMessage("正在设置工作密钥..");
//                    BluetoothComm.WriteWorkKey(sendBuf);
//                }
//
//                break;
//                case R.id.btnGetMAc: {
//                    showLogMessage("正在获取MAC..");
//
//                    // String order ="62148302164279888E144A783D5EC85130B2C6362D460D65D4651A86740E2FFDD074D181B9EADB4699256002016110217544210000";
//                    //	String order ="62148302164279886BA9C68F04D6FF1C30B6EE15E06CC909779FF223173D1025A1EC465D4EEC3FA2F720150209120611100001";
//
//                    String order = "0200702404C030C09811166225551646301497003131000000000012000016200702100012376225551646301497D20059B3CFEC6F160CEB000104996225551646301497D1561562942490005203013000000010101020071D000000000000D00000D01010004EC81FA689E32AC8003530303030303034313233343536373839303132333435313536075267A6D37716E226000000000000000012220000010500";
//                    int len;
//                    len = order.length();
//                    if (len % 2 == 0) {
//                        byte[] sendBuf = order.getBytes();
//                    } else {
//                        order = order + "0";
//                        byte[] sendBuf = order.getBytes();
//                    }
//
//
//                    byte[] sendBuf1 = hexStr2Bytes(order);
//                    BluetoothComm.GetMac(sendBuf1);
//                }
//                break;


                case R.id.btndispost: {
                    showLogMessage("正在取消操作..");
                    BluetoothComm.MagnCancel();

                }

                break;

//                case R.id.btnGetBatty: {
//                    showLogMessage("正在获取电池电量..");
//                    BluetoothComm.ReadBattery();
//
//                }
//                break;
                default:
                    break;
            }
        }
    };


    @Override
    protected void onDestroy() {

        //  BluetoothComm.DisConnectBlueDevice();

        //关闭释放相关蓝牙资源
        BluetoothComm.closeResource();
//    	try {
//			Thread.sleep(10);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//
//
//			e.printStackTrace();
//		}
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
        while (iterator.hasNext()) {
            @SuppressWarnings("unchecked")
            Map.Entry<String, String> entry1 = (Map.Entry<String, String>) iterator.next();
            showLogMessage(entry1.getKey() + "==" + entry1.getValue());
            Log.e("+-+-+-+-+", entry1.getKey() + "==" + entry1.getValue());
            //DEBUG_TAG
        }
    }

    @Override

    public void onDeviceFound(ArrayList<BluetoothIBridgeDevice> mDevices) {
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
                        System.out.println(map);
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
        while (iterator.hasNext()) {
            @SuppressWarnings("unchecked")
            Map.Entry<String, String> entry1 = (Map.Entry<String, String>) iterator.next();
            showLogMessage(entry1.getKey() + "==" + entry1.getValue());
            Log.e(DEBUG_TAG, entry1.getKey() + "==" + entry1.getValue());

        }


    }

    @Override
    public void onError(int nResult, String MsgData) {
        // TODO Auto-generated method stub
        String nReusl = String.format("%02x", nResult);
        showLogMessage("错误提示:" + nReusl + ":" + MsgData);

    }

    @Override
    public void onLoadMasterKeySucc(Boolean isSucess) {
        // TODO Auto-generated method stub
        if (isSucess) {
            showLogMessage("主密钥设置成功");
            Log.e("主密钥设置成功","主密钥设置成功");
        }
        else {
            showLogMessage("主密钥设置失败");
            Log.e("主密钥设置失败", "主密钥设置失败");
        }
    }

    @Override
    public void onLoadWorkKeySucc(Boolean isSucess) {
        // TODO Auto-generated method stub
        if (isSucess)
            showLogMessage("工作密钥设置成功");
        else
            showLogMessage("工作密钥设置失败");

    }

    @Override
    public void getMacSucess(String macdata) {
        // TODO Auto-generated method stub
        showLogMessage("MAC:" + macdata.toString());
    }

    @Override
    public void swipCardSucess(String cardNumber) {
        // TODO Auto-generated method stub
        showLogMessage("PAN:" + cardNumber.toString());
        Message updateMessage = mMainMessageHandler.obtainMessage();
        updateMessage.obj = "";
        updateMessage.what = 0x98;
        updateMessage.sendToTarget();
    }

    @Override
    public void onBluetoothIng() {
        // TODO Auto-generated method stub
        showLogMessage("提示:正在连接设备...");
    }

    @Override
    public void onBluetoothConected() {
        // TODO Auto-generated method stub
        showLogMessage("连接蓝牙成功,正在获取SN号...");
        bOpenDevice = true;
        BluetoothComm.GetDeviceInfo();
    }

    @Override
    public void onBluetoothConectedFail() {
        // TODO Auto-generated method stub
        showLogMessage("连接蓝牙设备失败...");
    }

    @Override
    public void onBluetoothDisconnected() {
        // TODO Auto-generated method stub
        showLogMessage("蓝牙已断开,请重新连接...");
        bOpenDevice = false;

    }

    @Override
    public void onScanTimeout() {
        // TODO Auto-generated method stub
        showLogMessage("搜索超时.");
    }

    @Override
    public void onBluetoothPowerOff() {
        showLogMessage("蓝牙关闭.");
    }

    @Override
    public void onBluetoothPowerOn() {
        // TODO Auto-generated method stub
        showLogMessage("蓝牙开启.");
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

}
