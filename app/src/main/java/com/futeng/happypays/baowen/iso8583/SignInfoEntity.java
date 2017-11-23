package com.futeng.happypays.baowen.iso8583;

import java.io.Serializable;

//import com.cn.util.response.ResponseResult;
import com.futeng.happypays.baowen.response.ResponseResult;
import com.google.gson.annotations.Expose;

public class SignInfoEntity extends ResponseResult implements Serializable {

	@Expose
	private String amNumer;			// 商户号
	@Expose
	private String macNumer;		// 终端号
	@Expose
	private String lordnum;			// 批次号
	@Expose
	private String iinvnum;			// 流水号
	@Expose
	private Integer amId;
	@Expose
	private String key62;
	@Expose
	private String zmk;				// zmk
	
	private String macSn;			// SN
	private String passwrod;		// 密码
	private String pinkey;			// zpk
	private String mackey;			// zak
	private String zekKey;			// 
	private String mainKey;
	private String random;
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("amNumer:");sb.append(amNumer);
		sb.append("macNumer:");sb.append(macNumer);
		sb.append("lordnum:");sb.append(lordnum);
		sb.append("iinvnum:");sb.append(iinvnum);
		sb.append("passwrod:");sb.append(passwrod);
		sb.append("pinkey:");sb.append(pinkey);
		sb.append("mackey:");sb.append(mackey);
		sb.append("mainKey:");sb.append(mainKey);
		sb.append("zekkey");sb.append(zekKey);
		sb.append("key62");sb.append(key62);
		return sb.toString();
	}

	public String getZmk() {
		return zmk;
	}
	public void setZmk(String zmk) {
		this.zmk = zmk;
	}
	public Integer getAmId() {
		return amId;
	}
	public void setAmId(Integer amId) {
		this.amId = amId;
	}
	public String getMacSn() {
		return macSn;
	}
	public void setMacSn(String macSn) {
		this.macSn = macSn;
	}
	public String getZekKey() {
		return zekKey;
	}
	public void setZekKey(String zekKey) {
		this.zekKey = zekKey;
	}
	public String getRandom() {
		return random;
	}
	public void setRandom(String random) {
		this.random = random;
	}
	public String getKey62() {
		return key62;
	}
	public void setKey62(String key62) {
		this.key62 = key62;
	}
	public String getMainKey() {
		return mainKey;
	}
	public void setMainKey(String mainKey) {
		this.mainKey = mainKey;
	}
	public String getPinkey() {
		return pinkey;
	}
	public void setPinkey(String pinkey) {
		this.pinkey = pinkey;
	}
	public String getMackey() {
		return mackey;
	}
	public void setMackey(String mackey) {
		this.mackey = mackey;
	}
	public String getAmNumer() {
		return amNumer;
	}
	public void setAmNumer(String amNumer) {
		this.amNumer = amNumer;
	}
	public String getMacNumer() {
		return macNumer;
	}
	public void setMacNumer(String macNumer) {
		this.macNumer = macNumer;
	}
	public void setLordnum(String lordnum) {
		this.lordnum = lordnum;
	}
	public String getIinvnum() {
		return iinvnum;
	}
	public void setIinvnum(String iinvnum) {
		this.iinvnum = iinvnum;
	}
	public String getLordnum() {
		return lordnum;
	}
	public String getPasswrod() {
		return passwrod;
	}
	public void setPasswrod(String passwrod) {
		this.passwrod = passwrod;
	}
}
