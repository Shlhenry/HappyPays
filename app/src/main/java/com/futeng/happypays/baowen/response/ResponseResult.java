package com.futeng.happypays.baowen.response;

import com.google.gson.annotations.Expose;

public class ResponseResult {
	
	public ResponseResult() {
		super();
	}
	
	@Expose
	private String resultCode;
	@Expose
	private String message;
	@Expose
	private String error;
	@Expose
	private Integer RenMark;
	@Expose
	private String RenMessage;
	
	public Integer getRenMark() {
		return RenMark;
	}
	public void setRenMark(Integer renMark) {
		RenMark = renMark;
	}
	
	public String getRenMessage() {
		return RenMessage;
	}
	public void setRenMessage(String renMessage) {
		RenMessage = renMessage;
	}
	@Override
	public String toString() {
		return "resultCode"+resultCode+"message"+message;
	}
	public static ResponseResult getInstance(String code,String message){
		return new ResponseResult(code,message);
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public ResponseResult(String resultCode, String message) {
		super();
		this.resultCode = resultCode;
		this.message = message;
	}
	public String getResultCode() {
		return resultCode;
	}
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
