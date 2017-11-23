package com.futeng.happypays.utils;

import java.util.List;

/**
 * Created by android on 2017/10/11.
 */

public class RedPacketBean {

    /**
     * code : 00
     * message : 成功
     * successMessage : 操作成功
     * failMessage : 用户不存在
     * isOK : true
     * list : [{"rpOrderNo":"rp201710101742356","rpType":1,"rpMoney":14.24,"rpId":37},{"rpOrderNo":"rp201710101745110","rpType":1,"rpMoney":1.2,"rpId":38},{"rpOrderNo":"rp201710101749528","rpType":2,"rpMoney":14,"rpId":39}]
     */

    private String code;
    private String message;
    private String successMessage;
    private String failMessage;
    private boolean isOK;
    /**
     * rpOrderNo : rp201710101742356
     * rpType : 1
     * rpMoney : 14.24
     * rpId : 37
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

    public String getFailMessage() {
        return failMessage;
    }

    public void setFailMessage(String failMessage) {
        this.failMessage = failMessage;
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
        private String rpOrderNo;
        private String rpType;
        private String rpMoney;
        private String rpId;

        public String getRpOrderNo() {
            return rpOrderNo;
        }

        public void setRpOrderNo(String rpOrderNo) {
            this.rpOrderNo = rpOrderNo;
        }

        public String getRpType() {
            return rpType;
        }

        public void setRpType(String rpType) {
            this.rpType = rpType;
        }

        public String getRpMoney() {
            return rpMoney;
        }

        public void setRpMoney(String rpMoney) {
            this.rpMoney = rpMoney;
        }

        public String getRpId() {
            return rpId;
        }

        public void setRpId(String rpId) {
            this.rpId = rpId;
        }
    }
}
