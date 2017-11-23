package com.futeng.happypays.utils;

public class ScanPayResponseBean {

	private String code;
	
	private Boolean isOK;
	
	private String message;
	
	private String imageUrl;
	
	private String tran37;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Boolean getIsOK() {
		return isOK;
	}

	public void setIsOK(Boolean isOK) {
		this.isOK = isOK;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getTran37() {
		return tran37;
	}

	public void setTran37(String tran37) {
		this.tran37 = tran37;
	}
}
