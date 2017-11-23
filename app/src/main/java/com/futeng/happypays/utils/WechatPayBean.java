package com.futeng.happypays.utils;

/**
 * Created by Administrator on 2017/4/13.
 */
public class WechatPayBean {

    /**
     * resultCode : 0000
     * message : 下单成功
     * orderNo : JSZT20170413155616180109861
     * codeUrl : weixin://wxpay/bizpayurl?pr=GksM6s8
     */

    private String origRespCode;
    private String origRespDesc;
    private String transAmt;
    private String orderNo;
    /**
     * resultCode : 9999
     * message : 支付中
     * smId :
     */

    private String resultCode;
    private String message;
    private String smId;

    public String getOrigRespCode() {
        return origRespCode;
    }

    public void setOrigRespCode(String origRespCode) {
        this.origRespCode = origRespCode;
    }

    public String getOrigRespDesc() {
        return origRespDesc;
    }

    public void setOrigRespDesc(String origRespDesc) {
        this.origRespDesc = origRespDesc;
    }

    public String getTransAmt() {
        return transAmt;
    }

    public void setTransAmt(String transAmt) {
        this.transAmt = transAmt;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
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

    public String getSmId() {
        return smId;
    }

    public void setSmId(String smId) {
        this.smId = smId;
    }

}
