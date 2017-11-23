package com.futeng.happypays.utils;

/**
 * Created by Administrator on 2017/9/25.
 */
public class WalletBean {

    /**
     * code : 00
     * message : 成功
     * successMessage : 操作成功
     * isOK : true
     * map : {"toDayActExaMoney":0,"sumTranMoney":335295.2,"allCount":236,"accountMoney":0.1,"toDaySumTranMoeny":0}
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
     * toDayActExaMoney : 0
     * sumTranMoney : 335295.2
     * allCount : 236
     * accountMoney : 0.1
     * toDaySumTranMoeny : 0
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
        private String toDayActExaMoney;
        private String sumTranMoney;
        private String allCount;
        private String accountMoney;

        public String getAccountInegral() {
            return accountInegral;
        }

        public void setAccountInegral(String accountInegral) {
            this.accountInegral = accountInegral;
        }

        private String accountInegral;

        public String getToDayActExaMoney() {
            return toDayActExaMoney;
        }

        public void setToDayActExaMoney(String toDayActExaMoney) {
            this.toDayActExaMoney = toDayActExaMoney;
        }

        public String getSumTranMoney() {
            return sumTranMoney;
        }

        public void setSumTranMoney(String sumTranMoney) {
            this.sumTranMoney = sumTranMoney;
        }

        public String getAllCount() {
            return allCount;
        }

        public void setAllCount(String allCount) {
            this.allCount = allCount;
        }

        public String getAccountMoney() {
            return accountMoney;
        }

        public void setAccountMoney(String accountMoney) {
            this.accountMoney = accountMoney;
        }

        public String getToDaySumTranMoeny() {
            return toDaySumTranMoeny;
        }

        public void setToDaySumTranMoeny(String toDaySumTranMoeny) {
            this.toDaySumTranMoeny = toDaySumTranMoeny;
        }

        private String toDaySumTranMoeny;




    }
}
