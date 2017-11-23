package com.futeng.happypays.utils;

import java.util.List;

/**
 * Created by Administrator on 2017/4/19.
 */
public class BalanceBean {
    /**
     * code : 00
     * message : 成功
     * isOK : true
     * list : [{"exaTimeTwo":"2017-03-20 00:00:00","exaCostFee":1,"exaMoney":65000,"exaMarkName":"T1","exaId":2,"exaRemark":"PROCEDURE","exaMark":1,"exaActmoney":65000},{"exaTimeTwo":"2017-03-20 00:00:00","exaCostFee":1,"exaMoney":130000,"exaMarkName":"T1","exaId":1,"exaRemark":"PROCEDURE","exaMark":1,"exaActmoney":130000}]
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

    private String successMessage;
    /**
     * exaTimeTwo : 2017-03-20 00:00:00
     * exaCostFee : 1
     * exaMoney : 65000
     * exaMarkName : T1
     * exaId : 2
     * exaRemark : PROCEDURE
     * exaMark : 1
     * exaActmoney : 65000
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

    public static class ListBean {
        private String exaTimeTwo;
        private int exaCostFee;
        private int exaMoney;
        private String exaMarkName;
        private int exaId;
        private String exaRemark;
        private int exaMark;
        private double exaActmoney;

        public String getExaTimeTwo() {
            return exaTimeTwo;
        }

        public void setExaTimeTwo(String exaTimeTwo) {
            this.exaTimeTwo = exaTimeTwo;
        }

        public int getExaCostFee() {
            return exaCostFee;
        }

        public void setExaCostFee(int exaCostFee) {
            this.exaCostFee = exaCostFee;
        }

        public int getExaMoney() {
            return exaMoney;
        }

        public void setExaMoney(int exaMoney) {
            this.exaMoney = exaMoney;
        }

        public String getExaMarkName() {
            return exaMarkName;
        }

        public void setExaMarkName(String exaMarkName) {
            this.exaMarkName = exaMarkName;
        }

        public int getExaId() {
            return exaId;
        }

        public void setExaId(int exaId) {
            this.exaId = exaId;
        }

        public String getExaRemark() {
            return exaRemark;
        }

        public void setExaRemark(String exaRemark) {
            this.exaRemark = exaRemark;
        }

        public int getExaMark() {
            return exaMark;
        }

        public void setExaMark(int exaMark) {
            this.exaMark = exaMark;
        }

        public double getExaActmoney() {
            return exaActmoney;
        }

        public void setExaActmoney(double exaActmoney) {
            this.exaActmoney = exaActmoney;
        }
    }
}
