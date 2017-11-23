package com.futeng.happypays.utils;

import java.util.List;

/**
 * Created by Administrator on 2017/9/26.
 */
public class SignBean {


    /**
     * code : 00
     * message : 成功
     * successMessage : 操作成功
     * isOK : true
     * map : {"nowSignIntegral":50,"nowSignSongIntegral":50,"signTodayCount":1,"nowSignAllIntegral":150,"asState":1,"signCount":3}
     * list : [{"asTime":"2017-09-26"},{"asTime":"2017-09-25"},{"asTime":"2017-09-23"}]
     */

    private String code;
    private String message;
    private String successMessage;
    private boolean isOK;
    /**
     * nowSignIntegral : 50
     * nowSignSongIntegral : 50
     * signTodayCount : 1
     * nowSignAllIntegral : 150
     * asState : 1
     * signCount : 3
     */

    private MapBean map;
    /**
     * asTime : 2017-09-26
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

    public MapBean getMap() {
        return map;
    }

    public void setMap(MapBean map) {
        this.map = map;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class MapBean {
        private int nowSignIntegral;
        private int nowSignSongIntegral;
        private int signTodayCount;
        private int nowSignAllIntegral;
        private int asState;
        private int signCount;

        public int getNowSignIntegral() {
            return nowSignIntegral;
        }

        public void setNowSignIntegral(int nowSignIntegral) {
            this.nowSignIntegral = nowSignIntegral;
        }

        public int getNowSignSongIntegral() {
            return nowSignSongIntegral;
        }

        public void setNowSignSongIntegral(int nowSignSongIntegral) {
            this.nowSignSongIntegral = nowSignSongIntegral;
        }

        public int getSignTodayCount() {
            return signTodayCount;
        }

        public void setSignTodayCount(int signTodayCount) {
            this.signTodayCount = signTodayCount;
        }

        public int getNowSignAllIntegral() {
            return nowSignAllIntegral;
        }

        public void setNowSignAllIntegral(int nowSignAllIntegral) {
            this.nowSignAllIntegral = nowSignAllIntegral;
        }

        public int getAsState() {
            return asState;
        }

        public void setAsState(int asState) {
            this.asState = asState;
        }

        public int getSignCount() {
            return signCount;
        }

        public void setSignCount(int signCount) {
            this.signCount = signCount;
        }
    }

    public static class ListBean {
        private String asTime;

        public String getAsTime() {
            return asTime;
        }

        public void setAsTime(String asTime) {
            this.asTime = asTime;
        }
    }
}
