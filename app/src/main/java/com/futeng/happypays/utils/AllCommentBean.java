package com.futeng.happypays.utils;

/**
 * Created by Administrator on 2017/9/14.
 */
public class AllCommentBean {
    /**
     * code : 00
     * message : 成功
     * successMessage : 操作成功
     * isOK : true
     * map : {"amState":5,"pState":1}
     */

    private String code;
    private String message;
    private String successMessage;
    private boolean isOK;
    /**
     * amState : 5
     * pState : 1
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
        private int amState;
        private int pState;

        public int getAmState() {
            return amState;
        }

        public void setAmState(int amState) {
            this.amState = amState;
        }

        public int getPState() {
            return pState;
        }

        public void setPState(int pState) {
            this.pState = pState;
        }
    }
}
