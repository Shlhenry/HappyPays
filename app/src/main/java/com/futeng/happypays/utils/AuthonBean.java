package com.futeng.happypays.utils;

/**
 * Created by Administrator on 2017/4/10.
 */
public class AuthonBean {
    /**
     * code : 99
     * message : NULL
     * isOK : false
     */

    private String code;
    private String message;
    private boolean isOK;

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

    private String successMessage;
    private String failMessage;


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

    public boolean isIsOK() {
        return isOK;
    }

    public void setIsOK(boolean isOK) {
        this.isOK = isOK;
    }
}
