package com.futeng.happypays.utils;

/**
 * Created by Administrator on 2017/9/26.
 */
public class ProfitBean {


    /**
     * code : 00
     * message : 成功
     * successMessage : 操作成功
     * isOK : true
     * map : {"recomIntegral":200,"sumIntegral":200}
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
     * recomIntegral : 200
     * sumIntegral : 200
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
        private int recomIntegral;
        private int sumIntegral;

        public int getRecomIntegral() {
            return recomIntegral;
        }

        public void setRecomIntegral(int recomIntegral) {
            this.recomIntegral = recomIntegral;
        }

        public int getSumIntegral() {
            return sumIntegral;
        }

        public void setSumIntegral(int sumIntegral) {
            this.sumIntegral = sumIntegral;
        }
    }
}
