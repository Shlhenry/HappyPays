package com.futeng.happypays.utils;

import android.util.Log;

import java.util.List;

/**
 * Created by Administrator on 2017/4/17.
 */
public class WaterBean {
    /**
     * code : 00
     * message : 成功
     * isOK : true
     * list : [{"wamDate":"2017-04-14 16:11:29","wamBeforeMoney":61.2,"wamActMoney":0.99,"awtName":"银行卡消费","mccName":"无卡支付T1","wamMoney":0.99,"wamAfterMoney":62.19,"wamId":54},{"wamDate":"2017-04-14 14:45:28","wamBeforeMoney":61.1,"wamActMoney":0.1,"awtName":"银行卡消费","mccName":"微信T1","wamMoney":0.1,"wamAfterMoney":61.2,"wamId":53}]
     */

    private String code;
    private String message;

    private boolean isOK;
    private String successMessage;
    private String failMessage;

    public String getFailMessage() {
        return failMessage;
    }

    public void setFailMessage(String failMessage) {
        this.failMessage = failMessage;
    }

    /**
     * wamDate : 2017-04-14 16:11:29
     * wamBeforeMoney : 61.2
     * wamActMoney : 0.99
     * awtName : 银行卡消费
     * mccName : 无卡支付T1
     * wamMoney : 0.99
     * wamAfterMoney : 62.19
     * wamId : 54
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

    public String getSuccessMessage() {
        return successMessage;
    }

    public void setSuccessMessage(String successMessage) {
        this.successMessage = successMessage;
    }

    public static class ListBean {
        private String wamDate;
        private double wamBeforeMoney;
        private double wamActMoney;
        private String awtName;
        private String mccName;
        private double wamMoney;
        private double wamAfterMoney;
        private int wamId;

        public String getWamDate() {
            return wamDate;
        }

        public void setWamDate(String wamDate) {
            this.wamDate = wamDate;
        }

        public double getWamBeforeMoney() {
            return wamBeforeMoney;
        }

        public void setWamBeforeMoney(double wamBeforeMoney) {
            this.wamBeforeMoney = wamBeforeMoney;
        }

        public double getWamActMoney() {
            return wamActMoney;
        }

        public void setWamActMoney(double wamActMoney) {
            this.wamActMoney = wamActMoney;
        }

        public String getAwtName() {
            return awtName;
        }

        public void setAwtName(String awtName) {
            this.awtName = awtName;
        }

        public String getMccName() {
            return mccName;
        }

        public void setMccName(String mccName) {
            this.mccName = mccName;
        }

        public double getWamMoney() {
            return wamMoney;
        }

        public void setWamMoney(double wamMoney) {
            this.wamMoney = wamMoney;
        }

        public double getWamAfterMoney() {
            return wamAfterMoney;
        }

        public void setWamAfterMoney(double wamAfterMoney) {
            this.wamAfterMoney = wamAfterMoney;
        }

        public int getWamId() {
            return wamId;
        }

        public void setWamId(int wamId) {
            this.wamId = wamId;
        }
    }
}
