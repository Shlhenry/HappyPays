package com.futeng.happypays.baowen.tool;

import java.util.List;
import java.util.Map;

public class Service {
	
	/**
	 * 成功
	 */
	public static String successMsg = "成功";
	
	/**
	 * 失败
	 */
	public static String failMsg = "失败";
	
	/**
	 * 异常
	 */
	public static String errorMsg = "异常";
	
	/**
	 * NULL
	 */
	public static String nullMsg = "NULL";
	
	private String code;
	
	private String message;
	
	private String failMessage;
	
	private String successMessage;
	
	private Boolean isOK;
	
	private Map<Object,Object> map;
	
	private List<Map<Object,Object>> list;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Boolean getIsOK() {
		return isOK;
	}
	public void setIsOK(Boolean isOK) {
		this.isOK = isOK;
	}
	public Map<Object, Object> getMap() {
		return map;
	}
	public void setMap(Map<Object, Object> map) {
		this.map = map;
	}
	public List<Map<Object,Object>> getList() {
		return list;
	}
	public void setList(List<Map<Object,Object>> list) {
		this.list = list;
	}
	public String getFailMessage() {
		return failMessage;
	}
	public void setFailMessage(String failMessage) {
		this.failMessage = failMessage;
	}
	public String getSuccessMessage() {
		return successMessage;
	}
	public void setSuccessMessage(String successMessage) {
		this.successMessage = successMessage;
	}
}
