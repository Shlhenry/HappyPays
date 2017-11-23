package com.futeng.happypays.baowen.tool;

public class StringUtil {

	/**
	 * 补零
	 * @param str		需要补0的字符
	 * @param length	长度
	 * @return
	 */
	public static String getBinary(String str,Integer length) {
		return String.format("%"+length+"s", str).replace(" ", "0");
	}
	
	/**
	 * 字符是否是空
	 * @param str
	 * @return
	 */
	public static boolean stringIsEmpty(String str){
		if(str == null || "".equals(str)){
			return true;
		}
		return false;
	}
}
