package com.futeng.happypays.utils;

import java.util.List;

/**
 * Created by Administrator on 2017/6/19.
 */
public class MyCommondBean {
    /**
     * code : 00
     * message : 成功
     * successMessage : 操作成功
     * isOK : true
     * map : {"amPersonPhone":"15195949721","gradeName":"伯","amName":"孙意遇"}
     */

    private String code;
    private String message;
    private String successMessage;
    private boolean isOK;
    /**
     * amPersonPhone : 15195949721
     * gradeName : 伯
     * amName : 孙意遇
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
        private String gradeName;
        private String amName;

        public String getAmPersonPhone() {
            return amPersonPhone;
        }

        public void setAmPersonPhone(String amPersonPhone) {
            this.amPersonPhone = amPersonPhone;
        }

        public String getGradeName() {
            return gradeName;
        }

        public void setGradeName(String gradeName) {
            this.gradeName = gradeName;
        }

        public String getAmName() {
            return amName;
        }

        public void setAmName(String amName) {
            this.amName = amName;
        }
    }
}