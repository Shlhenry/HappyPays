package com.futeng.happypays.utils;

import java.util.List;

/**
 * Created by Administrator on 2017/6/13.
 */
public class TeamMyBean {
    /**
     * code : 00
     * message : 成功
     * successMessage : 操作成功
     * isOK : true
     * list : [{"payState":1,"mccCap":999999,"ammMaxMoney":500000,"amId":385,"ammState":1,"mccId":45,"ammMinMoney":15,"ammFee":0,"mccRate":0.0035,"mccProfit":1,"mccName":"微信T1","ammId":947},{"payState":1,"mccCap":999999,"ammMaxMoney":50000,"amId":385,"ammState":1,"mccId":46,"ammMinMoney":15,"ammFee":2,"mccRate":0.0038,"mccProfit":1,"mccName":"微信D0","ammId":942},{"payState":1,"mccCap":999999,"ammMaxMoney":50000,"amId":385,"ammState":1,"mccId":47,"ammMinMoney":15,"ammFee":0,"mccRate":0.0035,"mccProfit":1,"mccName":"支付宝T1","ammId":943},{"payState":1,"mccCap":999999,"ammMaxMoney":50000,"amId":385,"ammState":1,"mccId":48,"ammMinMoney":15,"ammFee":2,"mccRate":0.0038,"mccProfit":1,"mccName":"支付宝D0","ammId":944},{"payState":1,"mccCap":999999,"ammMaxMoney":50000,"amId":385,"ammState":1,"mccId":82,"ammMinMoney":15,"ammFee":0,"mccRate":0.006,"mccProfit":1,"mccName":"无卡支付D0","ammId":946},{"payState":1,"mccCap":999999,"ammMaxMoney":20000,"amId":385,"ammState":1,"mccId":83,"ammMinMoney":15,"ammFee":0,"mccRate":0.0048,"mccProfit":0,"mccName":"线上无卡T1","ammId":950},{"payState":1,"mccCap":999999,"ammMaxMoney":20000,"amId":385,"ammState":1,"mccId":81,"ammMinMoney":110,"ammFee":0,"mccRate":0.0051,"mccProfit":1,"mccName":"2W无卡支付T1","ammId":945}]
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
     * payState : 1
     * mccCap : 999999.0
     * ammMaxMoney : 500000.0
     * amId : 385
     * ammState : 1
     * mccId : 45
     * ammMinMoney : 15.0
     * ammFee : 0.0
     * mccRate : 0.0035
     * mccProfit : 1.0
     * mccName : 微信T1
     * ammId : 947
     */

    private List<ListBean> list;

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

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        private int payState;
        private double mccCap;
        private double ammMaxMoney;
        private int amId;
        private int ammState;
        private int mccId;
        private double ammMinMoney;
        private double ammFee;
        private double mccRate;
        private double mccProfit;
        private String mccName;
        private int ammId;

        public int getPayState() {
            return payState;
        }

        public void setPayState(int payState) {
            this.payState = payState;
        }

        public double getMccCap() {
            return mccCap;
        }

        public void setMccCap(double mccCap) {
            this.mccCap = mccCap;
        }

        public double getAmmMaxMoney() {
            return ammMaxMoney;
        }

        public void setAmmMaxMoney(double ammMaxMoney) {
            this.ammMaxMoney = ammMaxMoney;
        }

        public int getAmId() {
            return amId;
        }

        public void setAmId(int amId) {
            this.amId = amId;
        }

        public int getAmmState() {
            return ammState;
        }

        public void setAmmState(int ammState) {
            this.ammState = ammState;
        }

        public int getMccId() {
            return mccId;
        }

        public void setMccId(int mccId) {
            this.mccId = mccId;
        }

        public double getAmmMinMoney() {
            return ammMinMoney;
        }

        public void setAmmMinMoney(double ammMinMoney) {
            this.ammMinMoney = ammMinMoney;
        }

        public double getAmmFee() {
            return ammFee;
        }

        public void setAmmFee(double ammFee) {
            this.ammFee = ammFee;
        }

        public double getMccRate() {
            return mccRate;
        }

        public void setMccRate(double mccRate) {
            this.mccRate = mccRate;
        }

        public double getMccProfit() {
            return mccProfit;
        }

        public void setMccProfit(double mccProfit) {
            this.mccProfit = mccProfit;
        }

        public String getMccName() {
            return mccName;
        }

        public void setMccName(String mccName) {
            this.mccName = mccName;
        }

        public int getAmmId() {
            return ammId;
        }

        public void setAmmId(int ammId) {
            this.ammId = ammId;
        }
    }
}
