package com.futeng.happypays.baowen.iso8583;

//import com.cn.util.response.ResponseResult;
import com.futeng.happypays.baowen.response.ResponseResult;
import com.google.gson.annotations.Expose;

/**
 * ISO8583报文域
 * @author
 */
public class ISO8583 extends ResponseResult {

	@Expose
	private String cankaohao;
	@Expose
	private String tran44;
	@Expose
	private String tran14;
	@Expose
	private String tran2;
	@Expose
	private String tran4;
	@Expose
	private String tran37;
	@Expose
	private String tran12;
	@Expose
	private String tran13;
	@Expose
	private String tran41;
	@Expose
	private String tran42;
	@Expose
	private String tran38;
	@Expose
	private String tran11;
	@Expose
	private String pici;
	@Expose
	private String name;
	@Expose
	private String tran47;

	
	
	public String getCankaohao() {
		if (cankaohao == null) {
			return "";
		}
		return cankaohao;
	}

	public void setCankaohao(String cankaohao) {
		this.cankaohao = cankaohao;
	}

	public String getTran11() {
		if (tran11 == null) {
			return "";
		}
		return tran11;
	}

	public void setTran11(String tran11) {
		this.tran11 = tran11;
	}

	public String getTran2() {
		if (tran2 == null) {
			return "";
		}
		return tran2;
	}

	public void setTran2(String tran2) {
		this.tran2 = tran2;
	}

	public String getTran4() {
		if (tran4 == null) {
			return "";
		}
		return tran4;
	}

	public void setTran4(String tran4) {
		this.tran4 = tran4;
	}

	public String getTran37() {
		if (tran37 == null) {
			return "";
		}
		return tran37;
	}

	public void setTran37(String tran37) {
		this.tran37 = tran37;
	}

	public String getTran12() {
		if (tran12 == null) {
			return "";
		}
		return tran12;
	}

	public void setTran12(String tran12) {
		this.tran12 = tran12;
	}

	public String getTran13() {
		if (tran13 == null) {
			return "";
		}
		return tran13;
	}

	public void setTran13(String tran13) {
		this.tran13 = tran13;
	}

	public String getTran38() {
		if (tran38 == null) {
			return "";
		}
		return tran38;
	}

	public void setTran38(String tran38) {
		this.tran38 = tran38;
	}

	public String getTran41() {
		if (tran41 == null) {
			return "";
		}
		return tran41;
	}

	public void setTran41(String tran41) {
		this.tran41 = tran41;
	}

	public String getTran42() {
		if (tran42 == null) {
			return "";
		}
		return tran42;
	}

	public void setTran42(String tran42) {
		this.tran42 = tran42;
	}

	public String getName() {
		if (name == null) {
			return "";
		}
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTran44() {
		if (tran44 == null) {
			return "";
		}
		return tran44;
	}

	public void setTran44(String tran44) {
		this.tran44 = tran44;
	}

	public String getTran14() {
		if (tran14 == null) {
			return "";
		}
		return tran14;
	}

	public void setTran14(String tran14) {
		this.tran14 = tran14;
	}
	
	public String getPici() {
		return pici;
	}

	public void setPici(String pici) {
		this.pici = pici;
	}
}
