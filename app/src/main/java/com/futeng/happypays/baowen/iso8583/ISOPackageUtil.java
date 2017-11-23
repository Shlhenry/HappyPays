package com.futeng.happypays.baowen.iso8583;

import com.futeng.happypays.baowen.socket.SocketConnectionUtil;
import com.futeng.happypays.baowen.tool.ByteUtil;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

//import com.cn.util.iso8583.ISO8583Util.Response8583;
//import com.cn.util.socket.SocketConnectionUtil;
//import com.cn.util.tool.ByteUtil;

/**
 * ISO8583
 * @author
 */
public class ISOPackageUtil {

	/**
	 * 银行卡消费报文      IC卡
	 * @param iso8583
	 * @return
	 */
	public static ISO8583 iso8583IC(ISO8583Entity iso8583){
		ISO8583Util util = new ISO8583Util();
		util.putString(0, iso8583.getTran0());
		util.putString(2, iso8583.getTran2());
		util.putString(3, iso8583.getTran3());
		util.putString(4, iso8583.getTran4());
		util.putString(11, iso8583.getTran11());
		util.putString(25, iso8583.getTran25());
		util.putString(41, iso8583.getTran41());
		util.putString(42, iso8583.getTran42());
		util.putString(49, iso8583.getTran49());
		util.putString(60, iso8583.getTran60());
		if (iso8583.getTran14() != null && iso8583.getTran14().length() > 0) {
			util.putString(14, iso8583.getTran14().substring(0, 4).trim());
		}
		if (iso8583.getTran52() != null && iso8583.getTran52().length() > 0) {
			util.putString(26, iso8583.getTran26());
			util.putString(52, iso8583.getTran52());
			util.putString(53, iso8583.getTran53());
		} else {
			util.putString(53, iso8583.getTran53());
		}
		// 35域必须
		if (iso8583.getTran35() != null && iso8583.getTran35().length() > 0) {
			util.putString(35, iso8583.getTran35());
		}
		// T1 : 不传   T0：06
		if (iso8583.getTran47() != null && iso8583.getTran47().length() > 0) {
			util.putString(47, iso8583.getTran47());
		}
		if (iso8583.getTran36() != null && iso8583.getTran36().length() > 0) {
			util.putString(36, iso8583.getTran36());
		}
		if(iso8583.getTran55() != null && iso8583.getTran55().length() > 0){
			util.putString(22, iso8583.getTran22());// 之前放52
			util.putString(23, iso8583.getTran23());
			util.putString(55, iso8583.getTran55());
		}else{
			util.putString(22, iso8583.getTran22());// 之前放52
		}
		try {
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			byte[] iso8583b = util.toISO8583_64();
			out.write(iso8583b, 13, iso8583b.length - 13);
			ByteUtil.printHexStr(out.toByteArray(),"MAC:");
			String mac = ISO8583Util.macEncrypt(out.toByteArray(), iso8583.getMacZak());
			util.putString(58,iso8583.getTran58());
			util.putString(64,  mac);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		byte[] data = null;
		try {
			data = util.toISO8583();
		} catch (IOException e) {
			e.printStackTrace();
		}
		ByteUtil.printHexStr(data,"组装MAC:");
		ISO8583Util.Response8583 response = SocketConnectionUtil.soketconnection(data);
		ISO8583 result = new ISO8583();
		if (response.getIsSuccess()) {
			if(response.getResponse().get(39).equals("00"))
				result.setMessage("交易成功");
			else if(response.getResponse().get(39).equals("45"))
				result.setMessage("不允许降级交易");
			else if(response.getResponse().get(39).equals("13"))
				result.setMessage("无效金额");
			else if(response.getResponse().get(39).equals("51"))
				result.setMessage("余额不足");
			else if(response.getResponse().get(39).equals("55"))
				result.setMessage("密码错误");
			else if(response.getResponse().get(39).equals("57")||response.getResponse().get(39).equals("56")||response.getResponse().get(39).equals("58")||response.getResponse().get(39).equals("59"))
				result.setMessage("交易失败，请联系发卡行");
			else if(response.getResponse().get(39).equals("96"))
				result.setMessage("发卡行响应超时");
			else if(response.getResponse().get(39).equals("12"))
				result.setMessage("无效交易");
			else if(response.getResponse().get(39).equals("14"))
				result.setMessage("无效卡号");
			else if(response.getResponse().get(39).equals("33"))
				result.setMessage("过期的卡");
			else if(response.getResponse().get(39).equals("30"))
				result.setMessage("报文格式错误");
			else if(response.getResponse().get(39).equals("61"))
				result.setMessage("超出取款金额限制");
			else if(response.getResponse().get(39).equals("75"))
				result.setMessage("密码输入次数超限");
			else if(response.getResponse().get(39).equals("94"))
				result.setMessage("流水号重复");
			else if(response.getResponse().get(39).equals("90"))
				result.setMessage("日期切换正在处理");
			else if(response.getResponse().get(39).equals("97"))
				result.setMessage("POS终端号找不到");
			else if(response.getResponse().get(39).equals("99"))
				result.setMessage("PIN格式错误");
			else if(response.getResponse().get(39).equals("A0"))
				result.setMessage("MAC校验错");
			result.setTran2(response.getResponse().get(2));
			result.setTran4(response.getResponse().get(4));
			result.setTran11(response.getResponse().get(11));
			result.setTran12(response.getResponse().get(12));
			result.setTran13(response.getResponse().get(13));
			result.setTran14(response.getResponse().get(14));
			result.setTran41(response.getResponse().get(41));
			result.setTran42(response.getResponse().get(42));
			result.setTran37(response.getResponse().get(37));
			result.setTran38(response.getResponse().get(38));
			result.setResultCode(response.getResponse().get(39));
			result.setTran44(response.getResponse().get(44));
			result.setName(response.getResponse().get(47));
			String pici = response.getResponse().get(60).substring(2, 8);
			result.setPici(pici);
		}else{
			result.setResultCode("-999999");
			result.setMessage("通讯失败!");
		}
		return result;
	}

	/**
	 * 银行卡消费报文      磁条卡
	 * @param iso8583
	 * @return
	 */
	public static ISO8583 iso8583MS(ISO8583Entity iso8583){
		ISO8583Util util = new ISO8583Util();
		SignInfoEntity entity = new SignInfoEntity();
		util.putString(0, iso8583.getTran0());
		util.putString(2, iso8583.getTran2());
		util.putString(3, iso8583.getTran3());
		util.putString(4, iso8583.getTran4());
		util.putString(11, iso8583.getTran11());
		util.putString(25, iso8583.getTran25());
		util.putString(41, iso8583.getTran41());
		util.putString(42, iso8583.getTran42());
		util.putString(49, iso8583.getTran49());
		util.putString(60, iso8583.getTran60());
		if (iso8583.getTran14() != null && iso8583.getTran14().length() > 0) {
			util.putString(14, iso8583.getTran14().substring(0, 4).trim());
		}
		if (iso8583.getTran52() != null && iso8583.getTran52().length() > 0) {
			util.putString(26, iso8583.getTran26());
			util.putString(52, iso8583.getTran52());
			util.putString(53, iso8583.getTran53());
		} else {
			util.putString(53, iso8583.getTran53());
		}
		if (iso8583.getTran35() != null && iso8583.getTran35().length() > 0) {
			util.putString(35, iso8583.getTran35());
		}
		if (iso8583.getTran47() != null && iso8583.getTran47().length() > 0) {
			util.putString(47, iso8583.getTran47());
		}
		if (iso8583.getTran36() != null && iso8583.getTran36().length() > 0) {
			util.putString(36, iso8583.getTran36());
		}
		if(iso8583.getTran55() != null && iso8583.getTran55().length() > 0){
			util.putString(22, iso8583.getTran52());
		}else{
			util.putString(22, iso8583.getTran52());
		}
		try {
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			byte[] iso8583b = util.toISO8583_64();
			out.write(iso8583b, 13, iso8583b.length-13);
			ByteUtil.printHexStr(out.toByteArray(),"=======================MAC=======================");
			String mac = ISO8583Util.macEncrypt(out.toByteArray(),entity.getMackey());
			util.putString(64,  mac);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		byte[] data = null;
		try {
			data = util.toISO8583();
		} catch (IOException e) {
			e.printStackTrace();
		}
		ByteUtil.printHexStr(data,"组装mac的内容:");
		ISO8583Util.Response8583 response = SocketConnectionUtil.soketconnection(data);
		ISO8583 result = new ISO8583();
		if (response.getIsSuccess()) {
			if(response.getResponse().get(39).equals("00"))
				result.setMessage("交易成功");
			else if(response.getResponse().get(39).equals("45"))
				result.setMessage("不允许降级交易");
			else if(response.getResponse().get(39).equals("13"))
				result.setMessage("无效金额");
			else if(response.getResponse().get(39).equals("51"))
				result.setMessage("余额不足");
			else if(response.getResponse().get(39).equals("55"))
				result.setMessage("密码错误");
			else if(response.getResponse().get(39).equals("57")||response.getResponse().get(39).equals("56")||response.getResponse().get(39).equals("58")||response.getResponse().get(39).equals("59"))
				result.setMessage("交易失败，请联系发卡行");
			else if(response.getResponse().get(39).equals("96"))
				result.setMessage("发卡行响应超时");
			result.setTran2(response.getResponse().get(2));
			result.setTran4(response.getResponse().get(4));
			result.setTran11(response.getResponse().get(11));
			result.setTran12(response.getResponse().get(12));
			result.setTran13(response.getResponse().get(13));
			result.setTran14(response.getResponse().get(14));
			result.setTran41(response.getResponse().get(41));
			result.setTran42(response.getResponse().get(42));
			result.setTran37(response.getResponse().get(37));
			result.setTran38(response.getResponse().get(38));
			result.setResultCode(response.getResponse().get(39));
			result.setTran44(response.getResponse().get(44));
			result.setName(response.getResponse().get(47));
			String pici = response.getResponse().get(60).substring(2, 8);
			result.setPici(pici);
		}else{
			result.setResultCode("-999999");
			result.setMessage("通讯失败!");
		}
		return result;
	}



	public static void main(String[] args) {
		StringBuilder sb = new StringBuilder();
		byte[] data = new byte[]{'0','1','5','7'};
		System.out.println(data);
		String str = ByteUtil.getHexStr(data);
		System.out.println(str);
	}
}
