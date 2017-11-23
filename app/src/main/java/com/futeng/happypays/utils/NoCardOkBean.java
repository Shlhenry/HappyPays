package com.futeng.happypays.utils;

/**
 * Created by Administrator on 2017/4/14.
 */
public class NoCardOkBean {
    /**
     * resultCode : 0000
     * message : 交易成功
     * orderNo : FJYL20170414175248761109358
     */

    private String resultCode;
    private String message;
    private String orderNo;

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

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }
}
