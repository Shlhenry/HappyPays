package com.futeng.happypays.baowen.iso8583;

public class ISO8583Entity {
	private String tran0;
	private String tran2;
	private String tran3;
	private String tran4;
	private String tran11;
	private String tran12;
	private String tran13;
	private String tran14;
	private String tran22;
	private String tran23;
	private String tran25;
	private String tran26;
	private String tran36;
	private String tran35;
	private String tran37;
	private String tran38;
	private String tran41;
	private String tran42;
	private String tran44;
	private String tran45;
	private String tran46;
	private String tran47;
	private String tran49;
	private String tran52;
	private String tran53;
	private String tran55;
	private String tran58;
	private String tran60;
	
	private String macZak;
	
	public String getMacZak() {
		return macZak;
	}
	public void setMacZak(String macZak) {
		this.macZak = macZak;
	}
	public String getTran0() {
		return tran0;
	}
	public void setTran0(String tran0) {
		this.tran0 = tran0;
	}
	public String getTran2() {
		return tran2;
	}
	public void setTran2(String tran2) {
		this.tran2 = tran2;
	}
	public String getTran3() {
		return tran3;
	}
	public void setTran3(String tran3) {
		this.tran3 = tran3;
	}
	public String getTran4() {
		return tran4;
	}
	public void setTran4(String tran4) {
		this.tran4 = tran4;
	}
	public String getTran11() {
		return tran11;
	}
	public void setTran11(String tran11) {
		this.tran11 = tran11;
	}
	public String getTran12() {
		return tran12;
	}
	public void setTran12(String tran12) {
		this.tran12 = tran12;
	}
	public String getTran13() {
		return tran13;
	}
	public void setTran13(String tran13) {
		this.tran13 = tran13;
	}
	public String getTran14() {
		return tran14;
	}
	public void setTran14(String tran14) {
		this.tran14 = tran14;
	}
	public String getTran22() {
		return tran22;
	}
	public void setTran22(String tran22) {
		this.tran22 = tran22;
	}
	public String getTran23() {
		return tran23;
	}
	public void setTran23(String tran23) {
		this.tran23 = tran23;
	}
	public String getTran25() {
		return tran25;
	}
	public void setTran25(String tran25) {
		this.tran25 = tran25;
	}
	public String getTran26() {
		return tran26;
	}
	public void setTran26(String tran26) {
		this.tran26 = tran26;
	}
	public String getTran36() {
		return tran36;
	}
	public void setTran36(String tran36) {
		this.tran36 = tran36;
	}
	public String getTran35() {
		return tran35;
	}
	public void setTran35(String tran35) {
		this.tran35 = tran35;
	}
	public String getTran37() {
		return tran37;
	}
	public void setTran37(String tran37) {
		this.tran37 = tran37;
	}
	public String getTran38() {
		return tran38;
	}
	public void setTran38(String tran38) {
		this.tran38 = tran38;
	}
	public String getTran41() {
		return tran41;
	}
	public void setTran41(String tran41) {
		this.tran41 = tran41;
	}
	public String getTran42() {
		return tran42;
	}
	public void setTran42(String tran42) {
		this.tran42 = tran42;
	}
	public String getTran44() {
		return tran44;
	}
	public void setTran44(String tran44) {
		this.tran44 = tran44;
	}
	public String getTran45() {
		return tran45;
	}
	public void setTran45(String tran45) {
		this.tran45 = tran45;
	}
	public String getTran46() {
		return tran46;
	}
	public void setTran46(String tran46) {
		this.tran46 = tran46;
	}
	public String getTran47() {
		return tran47;
	}
	public void setTran47(String tran47) {
		this.tran47 = tran47;
	}
	public String getTran49() {
		return tran49;
	}
	public void setTran49(String tran49) {
		this.tran49 = tran49;
	}
	public String getTran52() {
		return tran52;
	}
	public void setTran52(String tran52) {
		this.tran52 = tran52;
	}
	public String getTran53() {
		return tran53;
	}
	public void setTran53(String tran53) {
		this.tran53 = tran53;
	}
	public String getTran55() {
		return tran55;
	}
	public void setTran55(String tran55) {
		this.tran55 = tran55;
	}
	public String getTran58() {
		return tran58;
	}
	public void setTran58(String tran58) {
		this.tran58 = tran58;
	}
	public String getTran60() {
		return tran60;
	}
	public void setTran60(String tran60) {
		this.tran60 = tran60;
	}
	private Integer length;
	private LengthType lengthType;
	private PatternType patternType;
	public LengthType getLengthType() {
		return lengthType;
	}
	public PatternType getPatternType() {
		return patternType;
	}
	public Integer getLength() {
		return length;
	}
	
	public ISO8583Entity() {
		super();
	}
	public ISO8583Entity(Integer length,PatternType patternType, LengthType lengthType) {
		super();
		this.length = length;
		this.lengthType = lengthType;
		this.patternType = patternType;
	}
}

