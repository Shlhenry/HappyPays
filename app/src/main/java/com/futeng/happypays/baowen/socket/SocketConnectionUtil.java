package com.futeng.happypays.baowen.socket;

import android.util.Log;

import com.futeng.happypays.baowen.iso8583.ISO8583Util;
import com.futeng.happypays.baowen.tool.ByteUtil;
import com.futeng.happypays.baowen.tool.PropertiesUtil;
import com.futeng.happypays.tools.Config;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.Map;

//import com.cn.util.iso8583.ISO8583Util;
//import com.cn.util.iso8583.ISO8583Util.Response8583;
//import com.cn.util.tool.ByteUtil;
//import com.cn.util.tool.PropertiesUtil;

public class SocketConnectionUtil {

	public static ISO8583Util.Response8583 soketconnection(byte[] data){

		ISO8583Util utils = new ISO8583Util();
		ISO8583Util.Response8583 response = new ISO8583Util.Response8583();
		ByteArrayOutputStream outAr = null;
		try {
//			PropertiesUtil util = new PropertiesUtil(SocketConnectionUtil.class.getResource("/pospconfig.properties").getFile());
			Log.e("SOCKET","==================连接SOCKET==================pospIP="+Config.POSP_IP+",pospPort="+Config.POSP_PORT);
			Socket socket = new Socket(Config.POSP_IP,Integer.parseInt(Config.POSP_PORT));
			socket.setSoTimeout(600000);
			OutputStream stream = socket.getOutputStream();
			stream.write(data);
			stream.flush();
			InputStream input = socket.getInputStream();
			outAr = new ByteArrayOutputStream();
			byte[] bytes = new byte[1024];
			input.read(bytes);
			ByteUtil.printHexStr(bytes,"fuck 11 ============??>>>>>>>>>>>>");
			byte[] Datalen =new byte[]{bytes[0],bytes[1]};//获取长度
			int readLen = ByteUtil.bytesToInt2B(Datalen);
			String Lenstr = ByteUtil.getHexStr(Datalen);
			readLen = Integer.parseInt(Lenstr.replace(" ", ""), 16);
			outAr.write(ByteUtil.subArray(bytes,0, readLen));
			ByteUtil.printHexStr(outAr.toByteArray(),"fuck 12 ============??>>>>>>>>>>>>");
			socket.close();
			byte[] result = ByteUtil.subArray(outAr.toByteArray(), 13, -1);			// 去除长度和TPDU
			Map<Integer, String> str = utils.fromISO8583(result);
			response.setResponse(str);
			response.setIsSuccess(true);
		} catch (UnknownHostException e) {
			e.printStackTrace();
			response.setIsSuccess(false);
			response.setMessage("地址无法解析");
		} catch(SocketTimeoutException e){
			e.printStackTrace();
			response.setIsSuccess(false);
			response.setMessage("Socket通信超时");
		}catch (IOException e) {
			e.printStackTrace();
			response.setIsSuccess(false);
			response.setMessage("IO写入错误");
		}
		return response;
	}

	public static void main(String[] args) throws Exception {
		byte[] Datalen = new byte[]{0x01,0x0c};		// 获取长度
		String str = ByteUtil.getHexStr(Datalen);
		int i = Integer.parseInt(str.replace(" ", ""), 16);
		System.out.println("readLen:=>"+ i);
	}
}
