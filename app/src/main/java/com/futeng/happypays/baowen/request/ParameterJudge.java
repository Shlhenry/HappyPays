package com.futeng.happypays.baowen.request;

/**
 * 参数验证
 *  
 * @author Administrator
 * @since 20151022 syy 
 *
 */
public class ParameterJudge {

	/**
	 * 字符串是否是空的
	 * 
	 * @param input
	 * @return
	 */
	public static boolean stringIsEmpty(String input) {
		if (input == null) {
			return true;
		}
		if (input.length() == 0) {
			return true;
		}
		return false;
	}

	/**
	 * 字符串是否是int类型的值
	 * 
	 * @param input
	 * @return
	 */
	public static boolean stringIsInt(String input) {
		if (input == null) {
			return false;
		}
		try {
			Integer.parseInt(input);
		} catch (NumberFormatException exception) {
			return false;
		}
		return true;
	}

	/**
	 * 字符串是否是double类型的值
	 * 
	 * @param input
	 * @return
	 */
	public static boolean stringIsDouble(String input) {
		if (input == null) {
			return false;
		}
		try {
			Double.parseDouble(input);
		} catch (NumberFormatException exception) {
			return false;
		}
		return true;
	}

	/**
	 * Main方法
	 * @param args
	 */
	public static void main(String[] args) {
		
	}
}
