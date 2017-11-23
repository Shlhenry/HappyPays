package com.futeng.happypays.utils;

/**
 * Created by Administrator on 2017/7/15.
 */
public class InfoBean {
    /**
     * code : 00
     * message : 成功
     * successMessage : 操作成功
     * isOK : true
     * map : {"amPersonPhone":"18795883130","accountNumber":"6214922000352629","accountName":"邓南通","amPerson":"邵亨林","amName":"邵亨林","bankName":"光大银行","bankBranchNumber":"303301000807","amIdNumber":"362322199705068435"}
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
     * amPersonPhone : 18795883130
     * accountNumber : 6214922000352629
     * accountName : 邓南通
     * amPerson : 邵亨林
     * amName : 邵亨林
     * bankName : 光大银行
     * bankBranchNumber : 303301000807
     * amIdNumber : 362322199705068435
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
        private String amPersonPhone;
        private String accountNumber;
        private String accountName;
        private String amPerson;
        private String amName;
        private String bankName;
        private String bankBranchNumber;
        private String amIdNumber;

        public String getAmPersonPhone() {
            return amPersonPhone;
        }

        public void setAmPersonPhone(String amPersonPhone) {
            this.amPersonPhone = amPersonPhone;
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

        public String getAmPerson() {
            return amPerson;
        }

        public void setAmPerson(String amPerson) {
            this.amPerson = amPerson;
        }

        public String getAmName() {
            return amName;
        }

        public void setAmName(String amName) {
            this.amName = amName;
        }

        public String getBankName() {
            return bankName;
        }

        public void setBankName(String bankName) {
            this.bankName = bankName;
        }

        public String getBankBranchNumber() {
            return bankBranchNumber;
        }

        public void setBankBranchNumber(String bankBranchNumber) {
            this.bankBranchNumber = bankBranchNumber;
        }

        public String getAmIdNumber() {
            return amIdNumber;
        }

        public void setAmIdNumber(String amIdNumber) {
            this.amIdNumber = amIdNumber;
        }
    }
}
