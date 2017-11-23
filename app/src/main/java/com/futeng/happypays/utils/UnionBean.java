package com.futeng.happypays.utils;

/**
 * Created by Administrator on 2017/6/10.
 */
public class UnionBean {
    /**
     * code : 00
     * message : 成功
     * successMessage : 操作成功
     * isOK : true
     * map : {"psUrl":"http%3A%2F%2F101.37.168.236%3A8081%2FQRCode%2Fpage%2Findex.html%23%2Fwuka%3FmercNo%3D882803234560225"}
     */

    private String code;
    private String message;
    private String successMessage;

    public String getFailMessage() {
        return failMessage;
    }

    public void setFailMessage(String failMessage) {
        this.failMessage = failMessage;
    }

    private String failMessage;
    private boolean isOK;
    /**
     * psUrl : http%3A%2F%2F101.37.168.236%3A8081%2FQRCode%2Fpage%2Findex.html%23%2Fwuka%3FmercNo%3D882803234560225
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
        private String psUrl;

        public String getPsUrl() {
            return psUrl;
        }

        public void setPsUrl(String psUrl) {
            this.psUrl = psUrl;
        }
    }
}
