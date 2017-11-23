package com.futeng.happypays.baowen.money;

import java.math.BigDecimal;

public class DecimalUtil {
	
	public static Double around(String money){
		return around(money,2); 
	}
	
	
	public static Double around(Double money){
		return around(money,2); 
	}
	
	public static Double around(String money,Integer scale){
		return new BigDecimal(money).setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	
	public static Double around(Double money,Integer scale){
		return new BigDecimal(money).setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	
	public static void main(String[] args) {
		System.out.println(around(0.4 + 0.0041));
	}
}
