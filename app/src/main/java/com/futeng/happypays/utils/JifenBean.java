package com.futeng.happypays.utils;

/**
 * Created by Administrator on 2017/5/16.
 */
public class JifenBean {
    /**
     * code : 00
     * message : 成功
     * isOK : true
     * map : {"actAccountMoney":119.39,"accountMoney":119.39,"accountIntegral":0}
     */

    private String code;
    private String message;
    private boolean isOK;
    /**
     * actAccountMoney : 119.39
     * accountMoney : 119.39
     * accountIntegral : 0
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
        private String actAccountMoney;
        private String accountMoney;
        private String accountIntegral;

        public String getAbleIntegral() {
            return ableIntegral;
        }

        public void setAbleIntegral(String ableIntegral) {
            this.ableIntegral = ableIntegral;
        }

        private String ableIntegral;

        public String getActAccountMoney() {
            return actAccountMoney;
        }

        public void setActAccountMoney(String actAccountMoney) {
            this.actAccountMoney = actAccountMoney;
        }

        public String getAccountMoney() {
            return accountMoney;
        }

        public void setAccountMoney(String accountMoney) {
            this.accountMoney = accountMoney;
        }

        public String getAccountIntegral() {
            return accountIntegral;
        }

        public void setAccountIntegral(String accountIntegral) {
            this.accountIntegral = accountIntegral;
        }
    }
}
