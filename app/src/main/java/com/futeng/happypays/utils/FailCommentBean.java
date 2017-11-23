package com.futeng.happypays.utils;

import java.util.List;

/**
 * Created by Administrator on 2017/9/14.
 */
public class FailCommentBean {
    /**
     * code : 00
     * message : 成功
     * successMessage : 操作成功
     * isOK : true
     * list : [{"pPhone":"15252874890","pStateName":"待认证","pTime":"2017-07-19 16:47:30"}]
     */

    private String code;
    private String message;
    private String successMessage;
    private boolean isOK;
    /**
     * pPhone : 15252874890
     * pStateName : 待认证
     * pTime : 2017-07-19 16:47:30
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
        private String pPhone;
        private String pStateName;
        private String pTime;

        public String getPPhone() {
            return pPhone;
        }

        public void setPPhone(String pPhone) {
            this.pPhone = pPhone;
        }

        public String getPStateName() {
            return pStateName;
        }

        public void setPStateName(String pStateName) {
            this.pStateName = pStateName;
        }

        public String getPTime() {
            return pTime;
        }

        public void setPTime(String pTime) {
            this.pTime = pTime;
        }
    }
}
