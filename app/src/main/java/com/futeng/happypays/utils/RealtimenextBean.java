package com.futeng.happypays.utils;

/**
 * Created by Administrator on 2017/9/22.
 */
public class RealtimenextBean {
    /**
     * code : 00
     * message : 成功
     * successMessage : 操作成功
     * isOK : true
     * map : {"excValue":652,"curName":"人民币","curCode":"CNY"}
     */

    private String code;
    private String message;
    private String successMessage;
    private boolean isOK;
    /**
     * excValue : 652
     * curName : 人民币
     * curCode : CNY
     */

    private MapBean map;

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

    public boolean isIsOK() {
        return isOK;
    }

    public void setIsOK(boolean isOK) {
        this.isOK = isOK;
    }

    public MapBean getMap() {
        return map;
    }

    public void setMap(MapBean map) {
        this.map = map;
    }

    public static class MapBean {
        private String excValue;
        private String curName;
        private String curCode;

        public String getExcValue() {
            return excValue;
        }

        public void setExcValue(String excValue) {
            this.excValue = excValue;
        }

        public String getCurName() {
            return curName;
        }

        public void setCurName(String curName) {
            this.curName = curName;
        }

        public String getCurCode() {
            return curCode;
        }

        public void setCurCode(String curCode) {
            this.curCode = curCode;
        }
    }
}
