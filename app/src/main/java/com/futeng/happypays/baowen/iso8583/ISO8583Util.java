package com.futeng.happypays.baowen.iso8583;

import android.util.Log;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.futeng.happypays.utils.BitArrays;

import com.futeng.happypays.baowen.secret.DESedeUtil;
import com.futeng.happypays.baowen.secret.DesUtil;
import com.futeng.happypays.baowen.tool.ByteUtil;
import com.futeng.happypays.baowen.tool.ConvertUtil;

public class ISO8583Util {

	public static void main(String[] args) {
		// 6222520214996510
//		String zmk = "F463AC1FF7C418612DC5BF13F9DE9836";	// 主秘钥
//		String zpk = "41AD77644A6B8027D9BA3500CC42C1B3";	// PinKey密文
//		String zpkMW = DESedeUtil.DES_3_new(zpk, zmk,1);
//		String Encrytrack2 = "622252021455ce0d0bbc8d5f0c40";
//		String track2MW = Encrytrack2.substring(Encrytrack2.length()-18, Encrytrack2.length()-2);
//		String track2 = Encrytrack2.substring(0, Encrytrack2.length()-18);
//		System.out.println(DESedeUtil.DES_3_new(track2MW, zpkMW,1));

		// 2
//		String Encrytrack2 = "6230200022520279c351cc33ba3ed89c00";
//		String macZpk = "2C1123BEFBA8B55C551CE337083F8F25";
//		System.out.println(getTran2(Encrytrack2,macZpk));
		// 2
//		String Encrytrack2 = "6230200022520279c351cc33ba3ed89c00";ss
//		String macZmk = "FD9D0C024B6D001545E5AFB90BDE72CE";
//		String macZpk = "2C1123BEFBA8B55C551CE337083F8F25";
//		String Pinblock = "bb31e9a5156d7770".toUpperCase();
//		System.out.println(DESedeUtil.DES_3_new(Pinblock, macZpk,1));
//		System.out.println(getTran52(macZpk,Pinblock,"6230200022520279"));

		System.out.println(Integer.parseInt("0000", 16));
	}

	/**
	 * ISO8583   0域
	 * @return
	 */
	public static String getTran0(){
		return "0200";
	}

	/**
	 * ISO8583   2域   卡号
	 * @param Encrytrack2	二磁道
	 * @param macZpk		ZPK
	 * @return
	 */
	public static String getTran2(String Encrytrack2, String macZpk){
		String track2MW = Encrytrack2.substring(Encrytrack2.length()-18, Encrytrack2.length()-2);
		return Encrytrack2.substring(0, Encrytrack2.length()-18)+DESedeUtil.DES_3_new(track2MW, macZpk, 1).split("D")[0];
	}

	/**
	 * ISO8583   3域
	 * @return
	 */
	public static String getTran3(){
		return "000000";
	}

	/**
	 * ISO8583   4域
	 * @param tranMoney		消费金额
	 * @return
	 */
	public static String getTran4(String tranMoney){
		return String.format("%12s", new DecimalFormat(".00").format(Double.parseDouble(tranMoney)).replace(".", "")).replace(" ", "0");
	}


	/**
	 * ISO8583   11域
	 * @param macSerial		终端流水号
	 * @return
	 */
	public static String getTran11(String macSerial){
		return String.format("%"+macSerial.length()+"s", Integer.parseInt(macSerial) + 1).replace(" ", "0");
	}

	/**
	 * ISO8583   14域
	 * @param
	 * @return
	 */
	public static String getTran14(String Encrytrack3){
		return Encrytrack3;
	}

	/**
	 * ISO8583   23域
	 * @param
	 * @return
	 */
	public static String getTran23(String PanSeqNo){
		return PanSeqNo;
	}

	/**
	 * ISO8583   22域
	 * @return
	 */
	public static String getTran22(String tran52,String tran55){
		if(tran55 != null && tran55.length() > 0){
			return tran52 != null && tran52.length() > 0 ? "051" : "052";
		}else{// 071 072
			return tran52 != null && tran52.length() > 0 ? "021" : "022";
		}
	}

	/**
	 * ISO8583   25域
	 * @return
	 */
	public static String getTran25(){
		return "00";
	}

	/**
	 * ISO8583   26域
	 * @return
	 */
	public static String getTran26(){
		return "12";
	}

	/**
	 * ISO8583   35域
	 * @param
	 * @return
	 */
	public static String getTran35(String Encrytrack2){
		return Encrytrack2;
	}

	/**
	 * ISO8583   36域
	 * @param tran36	    3磁道数据
	 * @return
	 */
	public static String getTran36(String tran36){
		return tran36;
	}


	/**
	 * ISO8583   41域
	 * @param macNumber	 终端号
	 * @return
	 */
	public static String getTran41(String macNumber){
		return macNumber;
	}

	/**
	 * ISO8583   42域
	 * @param amNumber	    商户号
	 * @return
	 */
	public static String getTran42(String amNumber){
		return amNumber;
	}

	/**
	 * ISO8583   47域
	 * @param mcc		MCC
	 * @return
	 */
	public static String getTran47(String mcc){
		if(mcc == null || "".equals(mcc)){
			return "";
		}else{
			return mcc;
		}
	}

	/**
	 * ISO8583   47域
	 * 156：人民币
	 * @return
	 */
	public static String getTran49(){
		return "156";
	}

	/**
	 * ISO8583   52域
	 * @param macZpk		ZPK
	 * @param Pinblock		Pinblock
	 * @param tran2			tran2
	 * @return
	 */
	public static String getTran52(String macZpk, String Pinblock, String tran2){
		String source = DESedeUtil.DES_3_new(Pinblock, macZpk,1);
		String pan = "0000" + tran2.substring(tran2.length() - 1 - 12, tran2.length() - 1);
		byte[] panAr = ConvertUtil.strToBcd(pan, 0);
		byte[] pinAr = ConvertUtil.strToBcd(source, 0);
		ByteUtil.printHexStr(panAr);
		ByteUtil.printHexStr(pinAr);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		for (int i = 0; i < pinAr.length; i++) {
			out.write(pinAr[i] ^ panAr[i]);
		}
		return ByteUtil.getHexStrNoSpli(out.toByteArray()).substring(2, 8);
	}

	/**
	 * ISO8583   53域
	 * @param tran52
	 * @return
	 */
	public static String getTran53(String tran52){
		if(tran52 != null && tran52.length() > 0){
			return "2600000000000000";
		}else{
			return "0600000000000000";
		}
	}

	/**
	 * ISO8583   55域
	 * @param Track55
	 * @return
	 */
	public static String getTran55(String Track55){
		return Track55;
	}

	/**
	 * ISO8583   58域
	 * @return
     */
	public static String getTran58(){
		return "99";
	}
	/**
	 * ISO8583   60域
	 * 消费22 + 批次号   + 0005
	 * @param macBatch		终端批次号
	 * @param CardType		// 0:磁条卡 1:IC卡
	 * @return
	 */
	public static String getTran60(String macBatch,String CardType){
		String tran60 = "";
		if("1".equals(CardType)){
			tran60 = "22"+macBatch+"000500";
		}else if("0".equals(CardType)){
			tran60 = "22"+macBatch+"0005";
		}else{
			tran60 = "";
		}
		return tran60;
	}

	public static class Response8583{

		private Map<Integer, String> response;

		private Boolean isSuccess;

		private String message;

		public Map<Integer, String> getResponse() {
			return response;
		}
		public void setResponse(Map<Integer, String> response) {
			this.response = response;
		}
		public Boolean getIsSuccess() {
			return isSuccess;
		}
		public void setIsSuccess(Boolean isSuccess) {
			this.isSuccess = isSuccess;
		}
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}
	}

	private static List<ISO8583Entity> ios = new ArrayList<ISO8583Entity>();
	static { // 初始化ios数组
		ios.add(new ISO8583Entity(8, PatternType.L_BCD, LengthType.FIX_LEN)); // 1
		ios.add(new ISO8583Entity(19, PatternType.L_BCD, LengthType.LLVAR_LEN)); // 2
		ios.add(new ISO8583Entity(6, PatternType.L_BCD, LengthType.FIX_LEN)); // 3
		ios.add(new ISO8583Entity(12, PatternType.R_BCD, LengthType.FIX_LEN)); // 4
		ios.add(new ISO8583Entity(12, PatternType.L_BCD, LengthType.FIX_LEN)); // 5
		ios.add(new ISO8583Entity(12, PatternType.L_BCD, LengthType.FIX_LEN)); // 6
		ios.add(new ISO8583Entity(10, PatternType.R_BCD, LengthType.FIX_LEN));// 7
		ios.add(new ISO8583Entity(8, PatternType.R_BCD, LengthType.FIX_LEN)); // 8
		ios.add(new ISO8583Entity(8, PatternType.R_BCD, LengthType.FIX_LEN)); // 9
		ios.add(new ISO8583Entity(8, PatternType.R_BCD, LengthType.FIX_LEN)); // 10
		ios.add(new ISO8583Entity(6, PatternType.L_BCD, LengthType.FIX_LEN)); // 11
		ios.add(new ISO8583Entity(6, PatternType.L_BCD, LengthType.FIX_LEN)); // 12
		ios.add(new ISO8583Entity(4, PatternType.L_BCD, LengthType.FIX_LEN)); // 13
		ios.add(new ISO8583Entity(4, PatternType.L_BCD, LengthType.FIX_LEN)); // 14
		ios.add(new ISO8583Entity(4, PatternType.L_BCD, LengthType.FIX_LEN)); // 15
		ios.add(new ISO8583Entity(4, PatternType.R_BCD, LengthType.FIX_LEN)); // 16
		ios.add(new ISO8583Entity(4, PatternType.R_BCD, LengthType.FIX_LEN)); // 17
		ios.add(new ISO8583Entity(4, PatternType.R_BCD, LengthType.FIX_LEN)); // 18
		ios.add(new ISO8583Entity(3, PatternType.R_BCD, LengthType.FIX_LEN)); // 19
		ios.add(new ISO8583Entity(3, PatternType.R_BCD, LengthType.FIX_LEN)); // 20
		ios.add(new ISO8583Entity(3, PatternType.R_BCD, LengthType.FIX_LEN)); // 21
		ios.add(new ISO8583Entity(3, PatternType.L_BCD, LengthType.FIX_LEN)); // 22
		ios.add(new ISO8583Entity(3, PatternType.R_BCD, LengthType.FIX_LEN)); // 23
		ios.add(new ISO8583Entity(3, PatternType.R_BCD, LengthType.FIX_LEN)); // 24
		ios.add(new ISO8583Entity(2, PatternType.L_BCD, LengthType.FIX_LEN)); // 25
		ios.add(new ISO8583Entity(2, PatternType.L_BCD, LengthType.FIX_LEN)); // 26
		ios.add(new ISO8583Entity(1, PatternType.L_BCD, LengthType.FIX_LEN)); // 27
		ios.add(new ISO8583Entity(6, PatternType.L_BCD, LengthType.FIX_LEN)); // 28
		ios.add(new ISO8583Entity(8, PatternType.L_BCD, LengthType.FIX_LEN)); // 29
		ios.add(new ISO8583Entity(8, PatternType.L_BCD, LengthType.FIX_LEN)); // 30
		ios.add(new ISO8583Entity(8, PatternType.L_BCD, LengthType.FIX_LEN)); // 31
		ios.add(new ISO8583Entity(11, PatternType.L_BCD, LengthType.LLVAR_LEN)); // 32
		ios.add(new ISO8583Entity(11, PatternType.L_BCD, LengthType.LLVAR_LEN)); // 33
		ios.add(new ISO8583Entity(512, PatternType.L_BCD, LengthType.LLLVAR_LEN)); // 34
		ios.add(new ISO8583Entity(99, PatternType.L_BCD, LengthType.LLVAR_LEN)); // 35
		ios.add(new ISO8583Entity(200, PatternType.L_BCD, LengthType.LLLVAR_LEN)); // 36
		ios.add(new ISO8583Entity(12, PatternType.L_ASC, LengthType.FIX_LEN)); // 37
		ios.add(new ISO8583Entity(6, PatternType.L_ASC, LengthType.FIX_LEN)); // 38
		ios.add(new ISO8583Entity(2, PatternType.L_ASC, LengthType.FIX_LEN)); // 39
		ios.add(new ISO8583Entity(3, PatternType.L_BCD, LengthType.FIX_LEN)); // 40
		ios.add(new ISO8583Entity(8, PatternType.R_ASC, LengthType.FIX_LEN)); // 41
		ios.add(new ISO8583Entity(15, PatternType.R_ASC, LengthType.FIX_LEN)); // 42
		ios.add(new ISO8583Entity(40, PatternType.L_BCD, LengthType.FIX_LEN)); // 43
		ios.add(new ISO8583Entity(25, PatternType.L_ASC, LengthType.LLVAR_LEN)); // 44
		ios.add(new ISO8583Entity(76, PatternType.L_BCD, LengthType.LLVAR_LEN)); // 45
		ios.add(new ISO8583Entity(999, PatternType.L_ASC, LengthType.LLLVAR_LEN)); // 46
		ios.add(new ISO8583Entity(999, PatternType.R_BCD, LengthType.LLLVAR_LEN)); // 47
		ios.add(new ISO8583Entity(322, PatternType.L_BCD, LengthType.LLLVAR_LEN)); // 48
		ios.add(new ISO8583Entity(3, PatternType.R_ASC, LengthType.FIX_LEN)); // 49
		ios.add(new ISO8583Entity(3, PatternType.R_BCD, LengthType.FIX_LEN)); // 50
		ios.add(new ISO8583Entity(3, PatternType.R_BCD, LengthType.FIX_LEN)); // 51
		ios.add(new ISO8583Entity(8, PatternType.BINARY, LengthType.FIX_LEN)); // 52
		ios.add(new ISO8583Entity(16, PatternType.L_BCD, LengthType.FIX_LEN)); // 53
		ios.add(new ISO8583Entity(20, PatternType.R_ASC, LengthType.LLLVAR_LEN)); // 54
		ios.add(new ISO8583Entity(510, PatternType.BINARY, LengthType.LLLVAR_LEN)); // 55
		ios.add(new ISO8583Entity(48, PatternType.R_ASC, LengthType.LLLVAR_LEN)); // 56
		ios.add(new ISO8583Entity(999, PatternType.R_BCD, LengthType.LLLVAR_LEN)); // 57
		ios.add(new ISO8583Entity(100, PatternType.R_ASC, LengthType.LLLVAR_LEN)); // 58
		ios.add(new ISO8583Entity(999, PatternType.R_BCD, LengthType.LLLVAR_LEN)); // 59
		ios.add(new ISO8583Entity(17, PatternType.L_BCD, LengthType.LLLVAR_LEN)); // 60
		ios.add(new ISO8583Entity(29, PatternType.L_BCD, LengthType.LLLVAR_LEN)); // 61
		ios.add(new ISO8583Entity(512, PatternType.R_ASC, LengthType.LLLVAR_LEN)); // 62
		ios.add(new ISO8583Entity(3, PatternType.R_ASC, LengthType.LLLVAR_LEN)); // 63
		ios.add(new ISO8583Entity(8, PatternType.BINARY, LengthType.FIX_LEN)); // 64
		ios.add(new ISO8583Entity(8, PatternType.R_BCD, LengthType.FIX_LEN));
		ios.add(new ISO8583Entity(1, PatternType.R_ASC, LengthType.FIX_LEN));
		ios.add(new ISO8583Entity(2, PatternType.R_ASC, LengthType.FIX_LEN));
		ios.add(new ISO8583Entity(3, PatternType.R_ASC, LengthType.FIX_LEN));
		ios.add(new ISO8583Entity(3, PatternType.R_ASC, LengthType.FIX_LEN));
		ios.add(new ISO8583Entity(3, PatternType.R_ASC, LengthType.FIX_LEN));
		ios.add(new ISO8583Entity(4, PatternType.R_ASC, LengthType.FIX_LEN));
		ios.add(new ISO8583Entity(4, PatternType.R_ASC, LengthType.FIX_LEN));
		ios.add(new ISO8583Entity(6, PatternType.R_ASC, LengthType.FIX_LEN));
		ios.add(new ISO8583Entity(10, PatternType.R_ASC, LengthType.FIX_LEN));
		ios.add(new ISO8583Entity(10, PatternType.R_ASC, LengthType.FIX_LEN));
		ios.add(new ISO8583Entity(10, PatternType.R_ASC, LengthType.FIX_LEN));
		ios.add(new ISO8583Entity(10, PatternType.R_ASC, LengthType.FIX_LEN));
		ios.add(new ISO8583Entity(10, PatternType.R_ASC, LengthType.FIX_LEN));
		ios.add(new ISO8583Entity(10, PatternType.R_ASC, LengthType.FIX_LEN));
		ios.add(new ISO8583Entity(10, PatternType.R_ASC, LengthType.FIX_LEN));
		ios.add(new ISO8583Entity(10, PatternType.R_ASC, LengthType.FIX_LEN));
		ios.add(new ISO8583Entity(12, PatternType.R_ASC, LengthType.FIX_LEN));
		ios.add(new ISO8583Entity(12, PatternType.R_ASC, LengthType.FIX_LEN));
		ios.add(new ISO8583Entity(12, PatternType.R_ASC, LengthType.FIX_LEN));
		ios.add(new ISO8583Entity(12, PatternType.R_ASC, LengthType.FIX_LEN));
		ios.add(new ISO8583Entity(16, PatternType.R_ASC, LengthType.FIX_LEN));
		ios.add(new ISO8583Entity(16, PatternType.R_ASC, LengthType.FIX_LEN));
		ios.add(new ISO8583Entity(16, PatternType.R_ASC, LengthType.FIX_LEN));
		ios.add(new ISO8583Entity(16, PatternType.R_ASC, LengthType.FIX_LEN));
		ios.add(new ISO8583Entity(42, PatternType.R_ASC, LengthType.FIX_LEN));
		ios.add(new ISO8583Entity(1, PatternType.R_BCD, LengthType.FIX_LEN));
		ios.add(new ISO8583Entity(2, PatternType.R_BCD, LengthType.FIX_LEN));
		ios.add(new ISO8583Entity(5, PatternType.R_BCD, LengthType.FIX_LEN));
		ios.add(new ISO8583Entity(7, PatternType.R_BCD, LengthType.FIX_LEN));
		ios.add(new ISO8583Entity(42, PatternType.R_BCD, LengthType.FIX_LEN));
		ios.add(new ISO8583Entity(8, PatternType.R_BCD, LengthType.FIX_LEN));
		ios.add(new ISO8583Entity(16, PatternType.R_BCD, LengthType.FIX_LEN));
		ios.add(new ISO8583Entity(25, PatternType.R_BCD, LengthType.FIX_LEN));
		ios.add(new ISO8583Entity(11, PatternType.R_ASC, LengthType.LLVAR_LEN));
		ios.add(new ISO8583Entity(11, PatternType.R_ASC, LengthType.LLVAR_LEN));
		ios.add(new ISO8583Entity(17, PatternType.R_BCD, LengthType.LLVAR_LEN));
		ios.add(new ISO8583Entity(28, PatternType.R_BCD, LengthType.LLVAR_LEN));
		ios.add(new ISO8583Entity(28, PatternType.R_BCD, LengthType.LLVAR_LEN));
		ios.add(new ISO8583Entity(10, PatternType.R_BCD, LengthType.LLVAR_LEN));
		ios.add(new ISO8583Entity(999, PatternType.R_BCD, LengthType.LLVAR_LEN));
		ios.add(new ISO8583Entity(999, PatternType.R_BCD, LengthType.LLVAR_LEN));
		ios.add(new ISO8583Entity(999, PatternType.R_BCD, LengthType.LLVAR_LEN));
		ios.add(new ISO8583Entity(999, PatternType.R_BCD, LengthType.LLVAR_LEN));
		ios.add(new ISO8583Entity(999, PatternType.R_BCD, LengthType.LLVAR_LEN));
		ios.add(new ISO8583Entity(999, PatternType.R_BCD, LengthType.LLVAR_LEN));
		ios.add(new ISO8583Entity(999, PatternType.R_BCD, LengthType.LLVAR_LEN));
		ios.add(new ISO8583Entity(999, PatternType.R_BCD, LengthType.LLVAR_LEN));
		ios.add(new ISO8583Entity(999, PatternType.R_BCD, LengthType.LLVAR_LEN));
		ios.add(new ISO8583Entity(999, PatternType.R_BCD, LengthType.LLVAR_LEN));
		ios.add(new ISO8583Entity(999, PatternType.R_BCD, LengthType.LLVAR_LEN));
		ios.add(new ISO8583Entity(999, PatternType.R_BCD, LengthType.LLVAR_LEN));
		ios.add(new ISO8583Entity(999, PatternType.R_BCD, LengthType.LLVAR_LEN));
		ios.add(new ISO8583Entity(999, PatternType.R_BCD, LengthType.LLVAR_LEN));
		ios.add(new ISO8583Entity(999, PatternType.R_BCD, LengthType.LLVAR_LEN));
		ios.add(new ISO8583Entity(999, PatternType.R_BCD, LengthType.LLVAR_LEN));
		ios.add(new ISO8583Entity(999, PatternType.R_BCD, LengthType.LLVAR_LEN));
		ios.add(new ISO8583Entity(999, PatternType.R_BCD, LengthType.LLVAR_LEN));
		ios.add(new ISO8583Entity(999, PatternType.R_BCD, LengthType.LLVAR_LEN));
		ios.add(new ISO8583Entity(999, PatternType.R_BCD, LengthType.LLVAR_LEN));
		ios.add(new ISO8583Entity(999, PatternType.R_BCD, LengthType.LLVAR_LEN));
		ios.add(new ISO8583Entity(999, PatternType.R_BCD, LengthType.LLVAR_LEN));
		ios.add(new ISO8583Entity(999, PatternType.R_BCD, LengthType.LLVAR_LEN));
		ios.add(new ISO8583Entity(8, PatternType.R_BCD, LengthType.FIX_LEN));
	}

	private Map<Integer, String> context = new TreeMap<Integer, String>();

	public Map<Integer, String> getContext() {
		return context;
	}

	/**
	 * 放入字符串域
	 * @param position 域
	 * @param context 内容
	 */
	public void putString(Integer position, String context) {
		this.context.put(position, context);
	}

	/**
	 * 获取字符串的字节长度 (汉字占两个字节)
	 * @param s	字符串
	 * @return	字节长度
	 */
	public static int getWordCount(String s) {
		int length = 0;
		for (int i = 0; i < s.length(); i++) {
			int ascii = Character.codePointAt(s, i);
			if (ascii >= 0 && ascii <= 255)
				length++;
			else
				length += 2;
		}
		return length;
	}


	/**
	 *
	 * @param data
	 * @return
	 */
	public Map<Integer, String> fromISO8583(byte[] data) {
		Map<Integer, String> mp = new HashMap<Integer, String>();
		ByteArrayInputStream datas = new ByteArrayInputStream(data);
		byte[] bitmapArray = new byte[8];
		datas.read(bitmapArray, 0, 2);// 忽略两个字节的类型
		String bbbb=ByteUtil.getHexStrNoSpli(bitmapArray).substring(0, 4);
		System.out.println(bbbb);
		datas.read(bitmapArray, 0, 8);
		BitArrays bitmap = new BitArrays(64, bitmapArray);// 位图
		for (int i = 0; i < 64; i++) { // 遍历位图
			boolean bitHave = bitmap.get(i);
			if (bitHave) {
				System.out.println();
				System.out.print("we have the :" + (i + 1));
				ISO8583Entity entity = ios.get(i);
				System.out.println("长度类型:" + entity.getLengthType() + " 内容类型:" + entity.getPatternType() + "  类型长度" + entity.getLength());
				LengthType lentype = entity.getLengthType();
				int len = 0;
				int oldLen = 0;
				switch (lentype) {
				case FIX_LEN:
					len = entity.getLength();
					oldLen = len;
					System.out.print("原始长度:" + len);
					if (entity.getPatternType() == PatternType.L_BCD || entity.getPatternType() == PatternType.R_BCD) {
						if (len % 2 != 0) {
							len = len + 1;
						}
						len = len / 2;
					}
					;
					System.out.println("转换后长度" + len);
					break;
				case LLVAR_LEN:
					byte[] ll = new byte[1];
					try {
						datas.read(ll);
					} catch (IOException e) {
						e.printStackTrace();
					}
					len = ByteUtil.bytesToInt(ll);
					oldLen = len;
					System.out.println("原始长度:" + len + "转换后的hex:" + ByteUtil.getHexStr(ll));
					if (entity.getPatternType() == PatternType.L_BCD || entity.getPatternType() == PatternType.R_BCD) {
						if (len % 2 != 0) {
							len = len + 1;
						}
						len = len / 2;
					}
					;
					break;
				case LLLVAR_LEN:
					byte[] lll = new byte[2];
					try {
						datas.read(lll);
					} catch (IOException e) {
						e.printStackTrace();
					}
					len = ByteUtil.bytesToInt(lll);
					oldLen = len;
					System.out.println("原始长度:" + len + "转换后的hex:"
							+ ByteUtil.getHexStr(lll));
					if (entity.getPatternType() == PatternType.L_BCD
							|| entity.getPatternType() == PatternType.R_BCD) {
						if (len % 2 != 0) {
							len = len + 1;
						}
						len = len / 2;
					}
					;
					break;
				}
				PatternType patternType = entity.getPatternType();
				byte[] contextByte = new byte[len];
				datas.read(contextByte, 0, len);
				String contextStr = "";
				System.out.println("获取的长度" + len + "      原始长度" + oldLen);
				if (patternType == PatternType.L_ASC) { // 左对齐ASCII
					contextStr = new String(contextByte);
				} else if (patternType == PatternType.R_ASC) { // 右对齐ASCII
					try {
						if(i==61 && bbbb.equals("0810")){
							contextStr=ByteUtil.getHexStr(contextByte);
						}else{
							contextStr = new String(contextByte,"GBK");
						}
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
				} else if (patternType == PatternType.L_BCD) { // 左对齐BCD
					System.out.println("HEX:"+ByteUtil.getHexStr(contextByte));
					contextStr = ConvertUtil.BCD2ASC(contextByte);
					System.out.println(contextStr+" old Len:"+oldLen+" ");
					int start=lentype==LengthType.FIX_LEN?0:(contextStr.length()==oldLen?0:contextStr.length()-oldLen-1);
					contextStr = contextStr.substring(start, oldLen); // 对BCD码进行截取
				} else if (patternType == PatternType.R_BCD) { // 右对齐BCD
					contextStr = ConvertUtil.BCD2ASC(contextByte);
					contextStr = contextStr.substring(0, oldLen); // 对BCD码进行截取

				}else if(patternType==PatternType.BINARY){
					System.out.println(ByteUtil.getHexStr(contextByte));
					contextStr=ByteUtil.getHexStr(contextByte);
				}
				System.out.println("获取的内容:" + contextStr);
				mp.put(i + 1, contextStr);
			}
		}
		return mp;
	}

	/**
	 * 转化8583报文
	 *
	 * @return 2字节长度+ 11字节TPDU + 报文体(命令 + 位图 + 域)
	 * @throws IOException
	 */
	public byte[] toISO8583() throws IOException {
		BitArrays bitmap = new BitArrays(64);// 位图
		ByteArrayOutputStream output = new ByteArrayOutputStream();// 存放结果的
		ByteArrayOutputStream data = new ByteArrayOutputStream(); // 存放报文体的
		// 生成位图
		for (Map.Entry<Integer, String> entry : context.entrySet()) {
			if (entry.getKey() == 0) { // 如果是命令直接在结果最前面增加
				output.write(ByteUtil.getByteByNoSpli(entry.getValue()));
				continue;
			}
			int len = getWordCount(entry.getValue());// 获取内容长度
			ISO8583Entity entity = ios.get(entry.getKey().intValue() - 1); // 获取报文内容
			bitmap.set(entry.getKey().intValue() - 1, true); // 生成位图
			String value = entry.getValue();
			if (entity.getLengthType() == LengthType.FIX_LEN) { // 如果长度是固定的
				len = entity.getLength();
				if (entity.getPatternType() == PatternType.L_BCD) {
					int zero = len - entry.getValue().length();
					value = entry.getValue() + getZero(zero);
				} else if (entity.getPatternType() == PatternType.R_BCD) {
					int zero = len - entry.getValue().length();
					value = getZero(zero) + entry.getValue();
				}
			} else if (entity.getLengthType() == LengthType.LLVAR_LEN) {
				if (entity.getPatternType()==PatternType.BINARY) {
					data.write(ConvertUtil.strToBcdByNum(len/2 + "", 1));
				}else{
					data.write(ConvertUtil.strToBcdByNum(len + "", 1));
				}
			} else if (entity.getLengthType() == LengthType.LLLVAR_LEN) {
				// data.write(ByteUtil.intToByte(len, 2));
				if (entity.getPatternType()==PatternType.BINARY) {
					data.write(ConvertUtil.strToBcdByNum(len/2 + "", 2));
				}else{
					data.write(ConvertUtil.strToBcdByNum(len + "", 2));
				}
			}
			System.out.println(entry.getKey()+"域："+"长度:" + len + " currentLen:"
					+ entry.getValue().length() + ",type:"
					+ entity.getPatternType() + ",lenType:"
					+ entity.getLengthType() + ",oldValue:" + entry.getValue() + ",result:"
					+ ConvertUtil.strToBcd(entry.getValue(), 1).length);
			int entryLength = value.getBytes("GBK").length;
			switch (entity.getPatternType()) {
				case L_BCD: /* 左对齐BCD码 */
					data.write(ConvertUtil.strToBcd(value, 1));// 转换为bcd
					addZero(data, len - entryLength);
					break;
				case L_ASC: /* 左对齐ASC码 */
					data.write(value.getBytes("GBK"));
					addZero(data, len - entryLength);
					break;
				case R_BCD: /* 右对齐BCD码 */
					addZero(data, len - entryLength);
					data.write(ConvertUtil.strToBcd(value, 2));// 转换为bcd
					break;
				case R_ASC: /* 右对齐ASC码 */
					addZero(data, len - entryLength);
					data.write(value.getBytes("GBK"));
					break;
				case BINARY: /* 右对齐ASC码 */
					addZero(data, len - entryLength);
	//				data.write(value.getBytes());
					data.write(ByteUtil.getByteByNoSpli(value));
					break;
			}
			System.out.println(ByteUtil.getHexStr(data.toByteArray()));
			if (data.toByteArray().length>1024) {
				System.out.println("报文过长");
				return null;
			}
		}
		output.write(bitmap.toByteArray());// 位图
		output.write(data.toByteArray());
		int resultLen = output.toByteArray().length + 11;
		ByteArrayOutputStream result = new ByteArrayOutputStream();
		byte[] lengthRes = { (byte) ((resultLen >> 8) & 0xFF),
				(byte) ((resultLen) & 0xFF) };
		result.write(lengthRes);
		byte[] tpdu = { 0x60, 0x00, 0x00, 0x00, 0x03, 0x60, 0x31, 0x00, 0x11,
				0x43, 0x00 };
		result.write(tpdu);
		result.write(output.toByteArray());
		System.out.println("封装报文:\n" + ByteUtil.getHexStr(result.toByteArray()));
		return result.toByteArray();
	}

	/**
	 * 转化8583报文
	 * @return 2字节长度+ 11字节TPDU + 报文体(命令 + 位图 + 域)
	 * @throws IOException
	 */
	public byte[] toISO8583_64() throws IOException {
		BitArrays bitmap = new BitArrays(64);// 位图
		bitmap.set(64 - 1, true); // 生成位图
		ByteArrayOutputStream output = new ByteArrayOutputStream();// 存放结果的
		ByteArrayOutputStream data = new ByteArrayOutputStream(); // 存放报文体的
		// 生成位图
		for (Map.Entry<Integer, String> entry : context.entrySet()) {
			if (entry.getKey() == 0) { // 如果是命令直接在结果最前面增加
				output.write(ByteUtil.getByteByNoSpli(entry.getValue()));
				continue;
			}
			System.out.println(entry.getKey()+" : "+entry.getValue());
			int len = getWordCount(entry.getValue());// 获取内容长度
			ISO8583Entity entity = ios.get(entry.getKey().intValue() - 1); // 获取报文内容
			bitmap.set(entry.getKey().intValue() - 1, true); // 生成位图
			String value = entry.getValue();
			if (entity.getLengthType() == LengthType.FIX_LEN) { // 如果长度是固定的
				len = entity.getLength();
				if (entity.getPatternType() == PatternType.L_BCD) {
					int zero = len - entry.getValue().length();
					value = entry.getValue() + getZero(zero);
				} else if (entity.getPatternType() == PatternType.R_BCD) {
					int zero = len - entry.getValue().length();
					value = getZero(zero) + entry.getValue();
				}
			} else if (entity.getLengthType() == LengthType.LLVAR_LEN) {
				if (entity.getPatternType()==PatternType.BINARY) {
					data.write(ConvertUtil.strToBcdByNum(len/2 + "", 1));
				}else{
					data.write(ConvertUtil.strToBcdByNum(len + "", 1));
				}
			} else if (entity.getLengthType() == LengthType.LLLVAR_LEN) {
				// data.write(ByteUtil.intToByte(len, 2));
				if (entity.getPatternType()==PatternType.BINARY) {
					data.write(ConvertUtil.strToBcdByNum(len/2 + "", 2));
				}else{
					data.write(ConvertUtil.strToBcdByNum(len + "", 2));
				}
			}
			int entryLength = value.getBytes("GBK").length;
			switch (entity.getPatternType()) {
			case L_BCD: /* 左对齐BCD码 */
				data.write(ConvertUtil.strToBcd(value, 1));// 转换为bcd
				addZero(data, len - entryLength);
				break;
			case L_ASC: /* 左对齐ASC码 */
				data.write(value.getBytes("GBK"));
				addZero(data, len - entryLength);
				break;
			case R_BCD: /* 右对齐BCD码 */
				addZero(data, len - entryLength);
				data.write(ConvertUtil.strToBcd(value, 2));// 转换为bcd
				break;
			case R_ASC: /* 右对齐ASC码 */
				addZero(data, len - entryLength);
				data.write(value.getBytes("GBK"));
				break;
			case BINARY: /* 右对齐ASC码 */
				addZero(data, len - entryLength);
//				data.write(value.getBytes());
				data.write(ByteUtil.getByteByNoSpli(value));
				break;
			}
			if (data.toByteArray().length>1024) {
				System.out.println("报文过长");
				return null;
			}
		}
		output.write(bitmap.toByteArray());// 位图
		output.write(data.toByteArray());
		int resultLen = output.toByteArray().length + 11;
		ByteArrayOutputStream result = new ByteArrayOutputStream();
		byte[] lengthRes = { (byte) ((resultLen >> 8) & 0xFF), (byte) ((resultLen) & 0xFF) };
		result.write(lengthRes);
		byte[] tpdu = { 0x60, 0x00, 0x00, 0x00, 0x03, 0x60, 0x31, 0x00, 0x11, 0x43, 0x00 };
		result.write(tpdu);
		result.write(output.toByteArray());
		System.out.println("封装的报文:\n" + ByteUtil.getHexStr(result.toByteArray()));
		return result.toByteArray();
	}

	/**
	 * 获取0
	 * @param i		0的个数
	 * @return
	 */
	public String getZero(int i) {
		if (i == 0)
			return "";
		return String.format("%" + i + "s", "0").replace(" ", "0");
	}

	/**
	 * 补位
	 * @param output
	 * @param num
	 */
	public void addZero(ByteArrayOutputStream output, int num) {
		for (int i = 0; i < num; i++)
			output.write(0);
	}

	public static String printBinary(String str,Integer length) {
		return String.format("%"+length+"s", str).replace(" ", "0");
	}

	/**
	 * 计算报文mac
	 * @param iso8583
	 * @param pass
	 * @return
	 */
	public static String macEncrypt(byte[] iso8583, String pass){
		System.out.println("开始计算mac："+ByteUtil.getHexStr(iso8583));
		System.out.println("开始计算macPWD："+pass);
		StringBuilder s = new StringBuilder(ByteUtil.getHexStrNoSpli(iso8583));
		int cha = s.length() % 16;
		cha = 16 - cha;
		for (int i = 0; i < cha; i++) {
			s.append('0');
		}
		int loop = s.length() / 16;
		byte[] entry = null;
		for (int i = 0; i < loop; i++) {
			byte[] part = ConvertUtil.strToBcd(s.substring(i * 16, (i + 1) * 16),
					0);
			if (entry != null) {
				for (int j = 0; j < part.length; j++) {
					entry[j] = (byte) (entry[j] ^ part[j]);
				}
			} else {
				entry = part;
			}
		}
		String outdata=ByteUtil.getHexStrNoSpli(ByteUtil.getHexStrNoSpli(entry).toUpperCase().getBytes());
		String tempStr=outdata.substring(0, 16);
		Log.e("1111111",tempStr+"||"+pass);
		String desDemp=DESedeUtil.DES_3(tempStr, pass, 0);
		Log.e("222222222","111122"+desDemp);
		byte[] enptryAr=ConvertUtil.strToBcd(desDemp, 0);
		byte[] resultTemp=ConvertUtil.strToBcd(outdata.substring(16, 32),0);
		for (int i = 0; i < enptryAr.length; i++) {
			enptryAr[i]^=resultTemp[i];
		}
		tempStr=outdata.substring(16, 32).toUpperCase();
		desDemp=DESedeUtil.DES_3(ByteUtil.getHexStrNoSpli(enptryAr), pass, 0);
		System.out.println("desDemp:"+desDemp);
		String result=ByteUtil.getHexStrNoSpli(desDemp.substring(0, 16).getBytes()).substring(0, 16);
		System.out.println("计算结束:"+result);
		return result;
	}

	/**
	 * 计算报文mac
	 * @param iso8583
	 * @param pass
	 * @return
	 */
	public static String macEncryptStandard(byte[] iso8583, String pass){
//		byte[] key=ConvertUtil.strToBcd("20B3644C297032C7", 0);
//		byte[] data=ConvertUtil.strToBcd("7AB39DE7CE50A7E2", 0);
//		ByteUtil.printHexStr(DesUtil.decrypt(data, key));
		StringBuilder s = new StringBuilder(ByteUtil.getHexStrNoSpli(iso8583));
		String miyao = pass;
		String[] miyaoA = new String[2];
		miyaoA[0] = miyao.substring(0, miyao.length() / 2);
		miyaoA[1] = miyao.substring(miyao.length() / 2, miyao.length());
		System.out.println("秘钥"+miyao+" 秘钥A0   "+miyaoA[0]+" 秘钥A0  "+miyaoA[1]);
		byte[] miyaoOne=ConvertUtil.strToBcd(miyaoA[0], 0);
//		ByteUtil.printHexStr(miyaoOne,"左秘钥:");
		byte[] miyaoTwo=ConvertUtil.strToBcd(miyaoA[1], 0);
//		ByteUtil.printHexStr(miyaoTwo,"右秘钥");
		int cha = s.length() % 16;
		cha = 16 - cha;
		for (int i = 0; i < cha; i++) {
			s.append('0');
		}
		int loop = s.length() / 16;
		byte[] entry = null;
		for (int i = 0; i < loop; i++) {
			byte[] part = ConvertUtil.strToBcd(s.substring(i * 16, (i + 1) * 16),
					0);
//			ByteUtil.printHexStr(part,"获取的数据:");
			if (entry != null) {
				for (int j = 0; j < part.length; j++) {
					entry[j] = (byte) (entry[j] ^ part[j]);
				}
//				ByteUtil.printHexStr(entry,"异或的数据:");
			} else {
				entry = part;
			}
			entry=DesUtil.encrypt(entry,miyaoOne);
//			ByteUtil.printHexStr(entry,"加密的数据:");
		}
		try {
			entry = DesUtil.decrypt(entry, miyaoTwo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		entry = DesUtil.encrypt(entry, miyaoOne);
//		ByteUtil.printHexStr( entry,"最终数据:");
		ByteArrayInputStream input=new ByteArrayInputStream(entry);
		byte[] result=new byte[4];
		input.read(result, 0, 4);
		ByteUtil.printHexStr(result);
		String str=ConvertUtil.BCD2ASC(result);





		ByteUtil.printHexStr(str.toUpperCase().getBytes());
		return str.toUpperCase();
	}


	/**
	 * 计算pin
	 * @param card
	 * @param pass
	 * @param pinkey
	 * @return
	 */
	public static String pinEncrypt(String card, String pass, String pinkey) {
		String pan = card.split("D")[0];
		pan = "0000" + pan.substring(pan.length() - 1 - 12, pan.length() - 1);
		byte[] panAr = ConvertUtil.strToBcd(pan, 0);
		ByteUtil.printHexStr(panAr);
		pass = "0" + pass.length() + pass;
		pass = String.format("%-16s", pass).replace(" ", "F");
		System.out.println("pass:"+pass);
		byte[] pinAr = ConvertUtil.strToBcd(pass, 0);
		ByteUtil.printHexStr(pinAr);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		for (int i = 0; i < pinAr.length; i++) {
			out.write(pinAr[i] ^ panAr[i]);
		}
		return DESedeUtil.DES_3(ByteUtil.getHexStrNoSpli(out.toByteArray()), pinkey, 0);
	}

	/*
	 * 还原卡号
	 */
	public static String pinDecrypt(String card, String pass) {
		String pan = card.split("D")[0];
		pan = "0000" + pan.substring(pan.length() - 1 - 12, pan.length() - 1);
		byte[] panAr = ConvertUtil.strToBcd(pan, 0);
		ByteUtil.printHexStr(panAr);
		byte[] pinAr = ConvertUtil.strToBcd(pass, 0);
		ByteUtil.printHexStr(pinAr);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		for (int i = 0; i < pinAr.length; i++) {
			out.write(pinAr[i] ^ panAr[i]);
		}
		return ByteUtil.getHexStrNoSpli(out.toByteArray());
	}

	/**
	 * 卡磁道加密方法
	 * @param key
	 * @param magnetic
	 * @return
	 */
	public static String magneticEncrypt(String key, String magnetic) {
		int cha = magnetic.length() % 16;
		cha = 16 - cha;
		if (cha > 0) {
			magnetic = String.format("%-" + (magnetic.length() / 16 + 1) * 16 + "s", magnetic).replace(" ", "F");
		}
		StringBuilder sb = new StringBuilder();
		int time = magnetic.length() / 16;
		for (int i = 0; i < time; i++) {
			String encryptStr = magnetic.substring(i * 16, (i + 1) * 16);
			sb.append(DESedeUtil.DES_3(encryptStr, key, 0));
		}
		return sb.toString();
	}


	/**
	 * 艾创  获得密码
	 * @param psamno
	 * @param password
	 * @param random1
	 * @param zmkk
	 * @return
	 */
	public static String getACPass(String psamno,String password,String random1,String zmkk){
		//String zmkk = "11111111111111111111111111111111";//约定好的
		String random = random1;
		System.out.println("random = " + random);
		// DESedeTool.DES_1(source, key, type)
		String enc = DESedeUtil.DES_3(random, zmkk, 0);
		// ab9066e9a3f9666c0c30
		System.out.println("sub_key_zuo = " + enc);
		byte[] yh1 = ConvertUtil.strToBcd(random, 0);
		byte[] yh2 = ConvertUtil.strToBcd("FFFFFFFFFFFFFFFF", 0);
		ByteArrayOutputStream out2 = new ByteArrayOutputStream();
		for (int i = 0; i < yh1.length; i++) {
			out2.write(yh1[i] ^ yh2[i]);
		}
		System.out.println("亦或后结果：" + ByteUtil.getHexStrNoSpli(out2.toByteArray()));
		String jieguo = ByteUtil.getHexStrNoSpli(out2.toByteArray());
		String jieguo1 = DESedeUtil.DES_3(jieguo, zmkk, 0);
		System.out.println("sub_key_you = " + jieguo1);
		String zmk = enc + jieguo1;
		System.out.println("sub_key =" + zmk);
		// String zmk = zmkk;
		// String password = "3f379b93e2cec2c4";
		System.out.println("password =" + password);
		int loop = password.length() / 16;
		String track = "";
		for (int i = 0; i < loop; i++) {
			track += DESedeUtil.DES_3(password.substring(i * 16, (i + 1) * 16), zmk, 1);
		}
		System.out.println("password_mingwen =" + track);
		return track;
	}


	/**
	 * 艾创蓝牙 获得密码
	 * @param psamno
	 * @param password
	 * @param random1
	 * @param zmkk
	 * @return
	 */
	public static String getACLYPass(String psamno,String password,String random1,String zmkk){
		//String zmkk = "11111111111111111111111111111111";//约定好的
		String random = "ff"+random1+"313233";
		// DESedeTool.DES_1(source, key, type)
		String enc = DESedeUtil.DES_3(random, zmkk, 0);
		// ab9066e9a3f9666c0c30
		System.out.println("sub_key_zuo = " + enc);
		byte[] yh1 = ConvertUtil.strToBcd(random, 0);
		byte[] yh2 = ConvertUtil.strToBcd("FFFFFFFFFFFFFFFF", 0);
		ByteArrayOutputStream out2 = new ByteArrayOutputStream();
		for (int i = 0; i < yh1.length; i++) {
			out2.write(yh1[i] ^ yh2[i]);
		}
		String jieguo = ByteUtil.getHexStrNoSpli(out2.toByteArray());
		String jieguo1 = DESedeUtil.DES_3(jieguo, zmkk, 0);
		String zmk = enc + jieguo1;
		int loop = password.length() / 16;
		String track = "";
		for (int i = 0; i < loop; i++) {
			track += DESedeUtil.DES_3(password.substring(i * 16, (i + 1) * 16), zmk, 1);
		}
		System.out.println("password_mingwen =" + track);
		return track;
	}

	public static String getBBPosDes(String random,String enTrack,String psamNo,String zmkk){
		String zmk = zmkk;
		byte[] panAr = ConvertUtil.strToBcd(random, 0);
		byte[] pinAr = ConvertUtil.strToBcd("FFFFFFFFFFFFFFFF", 0);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		for (int i = 0; i < pinAr.length; i++) {
			out.write(pinAr[i] ^ panAr[i]);
		}
		String randomf = ByteUtil.getHexStrNoSpli(out.toByteArray());
		String wk = "";
		wk +=DESedeUtil.DES_3(random, zmk, 0);
		wk +=DESedeUtil.DES_3(randomf, zmk, 0);
		int loop = enTrack.length()/16;
		String track="";
		for (int i = 0; i < loop; i++) {
			track+=DESedeUtil.DES_3(enTrack.substring(i*16, (i+1)*16), wk, 1);
		}
		return track;
	}

	public static String getBBPosDes1(String random,String enTrack,String psamNo,String zmkk){
		String track =  DESedeUtil.DES_1(enTrack, random, 1);
		return track;
	}

	public static String decryptTrack(String enTrack,String workK,String mainK){
		enTrack = enTrack.toUpperCase();
		int loop=workK.length()/16;
		String realWorkKey="";
		String sk="";
		for (int i = 0; i < loop; i++) {
			sk=workK.substring(i*16, (i+1)*16);
			realWorkKey+=DESedeUtil.DES_3(sk, mainK, 1);
		}
		loop = enTrack.length()/16;
		String track="";
		for (int i = 0; i < loop; i++) {
			track+=DESedeUtil.DES_3(enTrack.substring(i*16, (i+1)*16), realWorkKey, 1);
		}
		System.out.println(track);
		return track;
	}

}
