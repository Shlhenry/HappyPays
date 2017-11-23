package com.futeng.happypays.utils;

/**
 * Created by Administrator on 2017/5/20.
 */
public class JifenguizeBean {
    /**
     * code : 00
     * message : 成功
     * isOK : true
     * map : {"ipRmb":1,"ipState":1,"ipIntegral":100,"ipFee":0.5,"ipTime":"2017-05-18 10:31:29","ipId":1,"ipRule":"MTAwOjFbMTAw56ev5YiGPTFSTUJd"}
     */

    private String code;
    private String message;
    private boolean isOK;

    public String getFailMessage() {
        return failMessage;
    }

    public void setFailMessage(String failMessage) {
        this.failMessage = failMessage;
    }

    public String getSuccessMessage() {
        return successMessage;
    }

    public void setSuccessMessage(String successMessage) {
        this.successMessage = successMessage;
    }

    private String failMessage;

    public boolean isOK() {
        return isOK;
    }

    public void setOK(boolean OK) {
        isOK = OK;
    }

    private String successMessage;
    /**
     * ipRmb : 1
     * ipState : 1
     * ipIntegral : 100
     * ipFee : 0.5
     * ipTime : 2017-05-18 10:31:29
     * ipId : 1
     * ipRule : MTAwOjFbMTAw56ev5YiGPTFSTUJd
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
        private int ipRmb;
        private int ipState;
        private int ipIntegral;
        private double ipFee;
        private String ipTime;
        private int ipId;
        private String ipRule;

        public int getIpRmb() {
            return ipRmb;
        }

        public void setIpRmb(int ipRmb) {
            this.ipRmb = ipRmb;
        }

        public int getIpState() {
            return ipState;
        }

        public void setIpState(int ipState) {
            this.ipState = ipState;
        }

        public int getIpIntegral() {
            return ipIntegral;
        }

        public void setIpIntegral(int ipIntegral) {
            this.ipIntegral = ipIntegral;
        }

        public double getIpFee() {
            return ipFee;
        }

        public void setIpFee(double ipFee) {
            this.ipFee = ipFee;
        }

        public String getIpTime() {
            return ipTime;
        }

        public void setIpTime(String ipTime) {
            this.ipTime = ipTime;
        }

        public int getIpId() {
            return ipId;
        }

        public void setIpId(int ipId) {
            this.ipId = ipId;
        }

        public String getIpRule() {
            return ipRule;
        }

        public void setIpRule(String ipRule) {
            this.ipRule = ipRule;
        }
    }
}
