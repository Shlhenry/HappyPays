package com.futeng.happypays.utils;

/**
 * Created by Administrator on 2017/6/10.
 */
public class Geturl {
    /**
     * code : 00
     * message : 成功
     * successMessage : 操作成功
     * isOK : true
     * map : {"psType":1,"psId":1,"mccId":45,"psUrl":"http://120.27.138.219:8080/PhonePOSPInterface/SubPayResultServlet","psState":1}
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
     * psType : 1
     * psId : 1
     * mccId : 45
     * psUrl : http://120.27.138.219:8080/PhonePOSPInterface/SubPayResultServlet
     * psState : 1
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
        private int psType;
        private int psId;
        private int mccId;
        private String psUrl;
        private int psState;

        public String getTran37() {
            return tran37;
        }

        public void setTran37(String tran37) {
            this.tran37 = tran37;
        }

        private String tran37;

        public int getPsType() {
            return psType;
        }

        public void setPsType(int psType) {
            this.psType = psType;
        }

        public int getPsId() {
            return psId;
        }

        public void setPsId(int psId) {
            this.psId = psId;
        }

        public int getMccId() {
            return mccId;
        }

        public void setMccId(int mccId) {
            this.mccId = mccId;
        }

        public String getPsUrl() {
            return psUrl;
        }

        public void setPsUrl(String psUrl) {
            this.psUrl = psUrl;
        }

        public int getPsState() {
            return psState;
        }

        public void setPsState(int psState) {
            this.psState = psState;
        }
    }
}
