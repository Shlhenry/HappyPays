package com.futeng.happypays.utils;

import java.util.List;

/**
 * Created by android on 2017/10/24.
 */

public class AddressBean {

    /**
     * code : 00
     * message : 成功
     * successMessage : 操作成功
     * isOK : true
     * list : [{"riPhone":"18355167945","riId":96,"riAddress":"是啊\u2026\u2026这里也很美了","riName":"王玉侠","riDefault":1,"riCity":"北京市北京市东城区"},{"riPhone":"18355167945","riId":98,"riAddress":"我了哦，我们都在这里玩吧！","riName":"我们的生活","riDefault":0,"riCity":"内蒙古自治区锡林郭勒盟二连浩特市"},{"riPhone":"18355167945","riId":97,"riAddress":"则路的咯哦哦哦\u2026\u2026是否能够","riName":"王玉侠","riDefault":0,"riCity":"上海市上海市黄浦区"}]
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
     * riPhone : 18355167945
     * riId : 96
     * riAddress : 是啊……这里也很美了
     * riName : 王玉侠
     * riDefault : 1
     * riCity : 北京市北京市东城区
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
        private String riPhone;
        private int riId;
        private String riAddress;
        private String riName;
        private int riDefault;
        private String riCity;

        public String getRiPhone() {
            return riPhone;
        }

        public void setRiPhone(String riPhone) {
            this.riPhone = riPhone;
        }

        public int getRiId() {
            return riId;
        }

        public void setRiId(int riId) {
            this.riId = riId;
        }

        public String getRiAddress() {
            return riAddress;
        }

        public void setRiAddress(String riAddress) {
            this.riAddress = riAddress;
        }

        public String getRiName() {
            return riName;
        }

        public void setRiName(String riName) {
            this.riName = riName;
        }

        public int getRiDefault() {
            return riDefault;
        }

        public void setRiDefault(int riDefault) {
            this.riDefault = riDefault;
        }

        public String getRiCity() {
            return riCity;
        }

        public void setRiCity(String riCity) {
            this.riCity = riCity;
        }
    }
}
