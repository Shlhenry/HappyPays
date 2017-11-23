package com.futeng.happypays.utils;

/**
 * Created by Administrator on 2017/5/15.
 */
public class Bangkabean {
    /**
     * code : 00
     * message : 成功
     * successMessage : 绑定成功
     * isOK : true
     */

    private String code;
    private String message;
    private String successMessage;
    private String failMessage;
    private boolean isOK;

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

    public String getSuccessMessage() {
        return successMessage;
    }

    public void setSuccessMessage(String successMessage) {
        this.successMessage = successMessage;
    }

    public String getFailMessage() {
        return failMessage;
    }

    public void setFailMessage(String failMessage) {
        this.failMessage = failMessage;
    }

    public boolean isIsOK() {
        return isOK;
    }

    public void setIsOK(boolean isOK) {
        this.isOK = isOK;
    }
}
