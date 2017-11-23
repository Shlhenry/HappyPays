package com.futeng.happypays.utils;

import java.util.List;

/**
 * Created by Administrator on 2017/5/16.
 */
public class CardBean {

    /*
     * code : 00
     * message : 成功
     * isOK : true
     * list : [{"accountNumber":"6228481778591517370","accountName":"邵亨林","amId":105,"abcState":1,"abcId":28,"amNumber":"883000000000105","abcBank":"农业银行"},{"accountNumber":"6228481778591517371","accountName":"邵亨林","amId":105,"abcState":1,"abcId":29,"amNumber":"883000000000105","abcBank":"农业银行"}]
     */

    private String code;
    private String message;

    public String getFailMessage() {
        return failMessage;
    }

    public void setFailMessage(String failMessage) {
        this.failMessage = failMessage;
    }

    private String failMessage;

    public String getSuccessMessage() {
        return successMessage;
    }

    public void setSuccessMessage(String successMessage) {
        this.successMessage = successMessage;
    }

    private String successMessage;
    private boolean isOK;
    /**
     * accountNumber : 6228481778591517370
     * accountName : 邵亨林
     * amId : 105
     * abcState : 1
     * abcId : 28
     * amNumber : 883000000000105
     * abcBank : 农业银行
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
        private String accountNumber;
        private String accountName;
        private int amId;
        private int abcState;
        private int abcId;
        private String amNumber;
        private String abcBank;

        public String getAccountNumber() {
            return accountNumber;
        }

        public void setAccountNumber(String accountNumber) {
            this.accountNumber = accountNumber;
        }

        public String getAccountName() {
            return accountName;
        }

        public void setAccountName(String accountName) {
            this.accountName = accountName;
        }

        public int getAmId() {
            return amId;
        }

        public void setAmId(int amId) {
            this.amId = amId;
        }

        public int getAbcState() {
            return abcState;
        }

        public void setAbcState(int abcState) {
            this.abcState = abcState;
        }

        public int getAbcId() {
            return abcId;
        }

        public void setAbcId(int abcId) {
            this.abcId = abcId;
        }

        public String getAmNumber() {
            return amNumber;
        }

        public void setAmNumber(String amNumber) {
            this.amNumber = amNumber;
        }

        public String getAbcBank() {
            return abcBank;
        }

        public void setAbcBank(String abcBank) {
            this.abcBank = abcBank;
        }
    }
}
