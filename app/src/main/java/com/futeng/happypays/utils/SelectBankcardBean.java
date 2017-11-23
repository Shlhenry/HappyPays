package com.futeng.happypays.utils;

import java.util.List;

/**
 * Created by Administrator on 2017/9/22.
 */
public class SelectBankcardBean {

    /**
     * code : 00
     * message : 成功
     * successMessage : 操作成功
     * isOK : true
     * list : [{"abcPicUrl":"http://101.37.168.236/bankIcon/ABC.png","abcDefaultNum":0,"accountNumber":"6228481778591517376","accountName":"邵亨林","abcRemark":"农业银行储蓄卡(7376)","amId":105,"bankSimpleCode":"ABC","abcState":1,"abcId":253,"amNumber":"883000000000105","abcBank":"农业银行"},{"abcPicUrl":"http://101.37.168.236/bankIcon/PSBC.png","abcDefaultNum":1,"accountNumber":"6221503610008217088","accountName":"邵亨林","abcRemark":"邮储银行储蓄卡(7088)","amId":105,"bankSimpleCode":"PSBC","abcState":1,"abcId":348,"amNumber":"883000000000105","abcBank":"邮储银行"},{"abcPicUrl":"http://101.37.168.236/bankIcon/PSBC.png","abcDefaultNum":0,"accountNumber":"6221503610008217086","accountName":"邵亨林","abcRemark":"邮储银行储蓄卡(7086)","amId":105,"bankSimpleCode":"PSBC","abcState":1,"abcId":349,"amNumber":"883000000000105","abcBank":"邮储银行"},{"abcPicUrl":"http://101.37.168.236/bankIcon/ABC.png","abcDefaultNum":0,"accountNumber":"6228481778591517370","accountName":"邵亨林","abcRemark":"农业银行储蓄卡(7370)","amId":105,"bankSimpleCode":"ABC","abcState":1,"abcId":350,"amNumber":"883000000000105","abcBank":"农业银行"},{"abcPicUrl":"http://101.37.168.236/bankIcon/ABC.png","abcDefaultNum":0,"accountNumber":"6228481778591517372","accountName":"邵亨林","abcRemark":"农业银行储蓄卡(7372)","amId":105,"bankSimpleCode":"ABC","abcState":1,"abcId":302,"amNumber":"883000000000105","abcBank":"农业银行"},{"abcPicUrl":"http://101.37.168.236/bankIcon/HXB.png","abcDefaultNum":0,"accountNumber":"6230200022520279","accountName":"邵亨林","abcRemark":"华夏银行储蓄卡(0279)","amId":105,"bankSimpleCode":"HXB","abcState":1,"abcId":322,"amNumber":"883000000000105","abcBank":"华夏银行"}]
     */

    private String code;
    private String message;
    private String successMessage;
    private boolean isOK;
    /**
     * abcPicUrl : http://101.37.168.236/bankIcon/ABC.png
     * abcDefaultNum : 0
     * accountNumber : 6228481778591517376
     * accountName : 邵亨林
     * abcRemark : 农业银行储蓄卡(7376)
     * amId : 105
     * bankSimpleCode : ABC
     * abcState : 1
     * abcId : 253
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
        private String abcPicUrl;
        private int abcDefaultNum;
        private String accountNumber;
        private String accountName;
        private String abcRemark;
        private int amId;
        private String bankSimpleCode;
        private int abcState;
        private int abcId;
        private String amNumber;
        private String abcBank;

        public String getAbcPicUrl() {
            return abcPicUrl;
        }

        public void setAbcPicUrl(String abcPicUrl) {
            this.abcPicUrl = abcPicUrl;
        }

        public int getAbcDefaultNum() {
            return abcDefaultNum;
        }

        public void setAbcDefaultNum(int abcDefaultNum) {
            this.abcDefaultNum = abcDefaultNum;
        }

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

        public String getAbcRemark() {
            return abcRemark;
        }

        public void setAbcRemark(String abcRemark) {
            this.abcRemark = abcRemark;
        }

        public int getAmId() {
            return amId;
        }

        public void setAmId(int amId) {
            this.amId = amId;
        }

        public String getBankSimpleCode() {
            return bankSimpleCode;
        }

        public void setBankSimpleCode(String bankSimpleCode) {
            this.bankSimpleCode = bankSimpleCode;
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
