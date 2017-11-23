package com.futeng.happypays.baowen.money;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Money 处理类
 * @author Administrator
 */
public class MoneyUtil {


	/**
	 * 验证字符是否是金额
	 * @param money
	 * @return
     */
	public static Boolean isMoney(String  money){
		Pattern pattern = Pattern.compile("^(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d){0,2})?$"); // 判断小数点后2位的数字的正则表达式
		Matcher match = pattern.matcher(money);
		if(match.matches() == false)
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	
	
	/**
	 * 
	 * @param d1
	 * @return
	 */
	public static String formaterMoney(Double d1) {
		DecimalFormat df = new DecimalFormat("#.00");
		return String.format("%12s", df.format(d1).replace(".", "")).replace(" ", "0");
	}

	/**
	 * 
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static double add(double d1, double d2) {
		return new BigDecimal(d1 + "").add(new BigDecimal(d2 + "")).doubleValue();
	}
	
	
	public static Double subtract(Double d1, Double d2) {
		if (d1 == null) {
			d1 = 0.0;
		}
		if (d2 == null) {
			d2 = 0.0;
		}
		BigDecimal bd1 = new BigDecimal(Double.toString(d1));
		BigDecimal bd2 = new BigDecimal(Double.toString(d2));
		return bd1.subtract(bd2).doubleValue();
	}

	/**
	 * 
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static double subtract(double d1, double d2) {
		return new BigDecimal(d1 + "").subtract(new BigDecimal(d2 + "")).doubleValue();
	}

	/**
	 * 
	 * @param ds
	 * @return
	 */
	public static double sum(double... ds) {
		double sum = 0.0;
		for (double d : ds) {
			sum = add(sum, d);
		}
		return sum;
	}
	
	/**
	 * 保留两位小数，进一
	 * 
	 * @param d
	 * @return
	 */
	public static double ceil(double d) {
		return Math.ceil(d * 100) / 100;
	}

	/**
	 * 保留两位小数，去尾
	 * @param d
	 * @return
	 */
	public static double floor(double d) {
		return Math.floor(d * 100) / 100;
	}
	
	public static double multiply(Double d1, Double d2) {
		if (d1 == null) {
			d1 = 0.0;
		}
		if (d2 == null) {
			d2 = 0.0;
		}
		BigDecimal bd1 = new BigDecimal(d1.toString());
		BigDecimal bd2 = new BigDecimal(d2.toString());
		return bd1.multiply(bd2).doubleValue();
	}
	
	public static double divide(Double d1, Double d2, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException(
					"The scale must be a positive integer or zero");
		}
		BigDecimal b1 = new BigDecimal(d1.toString());
		BigDecimal b2 = new BigDecimal(d2.toString());
		return new Double(b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue());
	}
	
	public static long mulLong(double d1, double d2) {
		BigDecimal bd1 = new BigDecimal(Double.toString(d1));
		BigDecimal bd2 = new BigDecimal(Double.toString(d2));
		return bd1.multiply(bd2).longValue();
	}
}
