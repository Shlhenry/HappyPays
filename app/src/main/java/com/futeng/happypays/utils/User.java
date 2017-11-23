package com.futeng.happypays.utils;

/**
 * Created by Administrator on 2017/4/5.
 */
public class User {


    /**
     * code : 00
     * message : 成功
     * isOK : true
     * map : {"amAddress":"南京市江宁区","pState":1,"amPerson":"李祥花","pPhone":"13913022428","amName":"沐益昇","amNumber":"882000000000061","amIdNumber":"320121199011280320"}
     */

    private String code;
    private String message;
    private boolean isOK;

    public String getFailMessage() {
        return failMessage;
    }

    public void setFailMessage(String failMessage) {
        this.failMessage = failMessage;
    }

    private String failMessage;

    /**
     * amAddress : 南京市江宁区
     * pState : 1
     * amPerson : 李祥花
     * pPhone : 13913022428
     * amName : 沐益昇
     * amNumber : 882000000000061
     * amIdNumber : 320121199011280320
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
        private String amAddress;
        private int pState;
        private String amPerson;
        private String pPhone;
        private String amName;
        private String amNumber;
        private String amIdNumber;
        private String gradeName;

        public String getGradeName() {
            return gradeName;
        }

        public void setGradeName(String gradeName) {
            this.gradeName = gradeName;
        }

        public String getAmAddress() {
            return amAddress;
        }

        public void setAmAddress(String amAddress) {
            this.amAddress = amAddress;
        }

        public int getPState() {
            return pState;
        }

        public void setPState(int pState) {
            this.pState = pState;
        }

        public String getAmPerson() {
            return amPerson;
        }

        public void setAmPerson(String amPerson) {
            this.amPerson = amPerson;
        }

        public String getPPhone() {
            return pPhone;
        }

        public void setPPhone(String pPhone) {
            this.pPhone = pPhone;
        }

        public String getAmName() {
            return amName;
        }

        public void setAmName(String amName) {
            this.amName = amName;
        }

        public String getAmNumber() {
            return amNumber;
        }

        public void setAmNumber(String amNumber) {
            this.amNumber = amNumber;
        }

        public String getAmIdNumber() {
            return amIdNumber;
        }

        public void setAmIdNumber(String amIdNumber) {
            this.amIdNumber = amIdNumber;
        }
    }
}

