package com.futeng.happypays.utils;

/**
 * Created by android on 2017/10/21.
 */

public class StoredetailsBean {


    /**
     * code : 00
     * message : 成功
     * successMessage : 操作成功
     * isOK : true
     * map : {"activityBeginTimeDefault":"2017-10-16 00:00:00","activityEndTimeDefault":"2017-10-17 15:03:43","comMailType":0,"comConvertible":5,"mattersContentDefault":"1.此商品每人限兑一份;2.此商品不退换,敬请谅解","comIntegral":4000,"mattersType":1,"mattersContent":"1.每人限兑一份;2.不退换,敬请谅解","comMoney":2,"comMailMoney":0,"comExctype":2,"comNumber":"20171017101912102442","activityBeginTime":"2017-10-16 00:00:00","activityTimetype":3,"activityEndTime":"2017-10-16 00:00:00","exchangeCount":0,"comName":"红牛40","comId":61,"comBrief":"红牛40","activityType":1,"comImgUrl":"http://120.27.194.146/commodityImg/1/1/20171017100832002002.png","comCount":20}
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
     * activityBeginTimeDefault : 2017-10-16 00:00:00
     * activityEndTimeDefault : 2017-10-17 15:03:43
     * comMailType : 0
     * comConvertible : 5
     * mattersContentDefault : 1.此商品每人限兑一份;2.此商品不退换,敬请谅解
     * comIntegral : 4000
     * mattersType : 1
     * mattersContent : 1.每人限兑一份;2.不退换,敬请谅解
     * comMoney : 2
     * comMailMoney : 0
     * comExctype : 2
     * comNumber : 20171017101912102442
     * activityBeginTime : 2017-10-16 00:00:00
     * activityTimetype : 3
     * activityEndTime : 2017-10-16 00:00:00
     * exchangeCount : 0
     * comName : 红牛40
     * comId : 61
     * comBrief : 红牛40
     * activityType : 1
     * comImgUrl : http://120.27.194.146/commodityImg/1/1/20171017100832002002.png
     * comCount : 20
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
        private String activityBeginTimeDefault;
        private String activityEndTimeDefault;
        private int comMailType;
        private int comConvertible;
        private String mattersContentDefault;
        private int comIntegral;
        private int mattersType;
        private String mattersContent;
        private int comMoney;
        private int comMailMoney;
        private int comExctype;
        private String comNumber;
        private String activityBeginTime;
        private int activityTimetype;
        private String activityEndTime;
        private int exchangeCount;
        private String comName;
        private int comId;
        private String comBrief;
        private int activityType;
        private String comImgUrl;
        private int comCount;

        public String getActivityBeginTimeDefault() {
            return activityBeginTimeDefault;
        }

        public void setActivityBeginTimeDefault(String activityBeginTimeDefault) {
            this.activityBeginTimeDefault = activityBeginTimeDefault;
        }

        public String getActivityEndTimeDefault() {
            return activityEndTimeDefault;
        }

        public void setActivityEndTimeDefault(String activityEndTimeDefault) {
            this.activityEndTimeDefault = activityEndTimeDefault;
        }

        public int getComMailType() {
            return comMailType;
        }

        public void setComMailType(int comMailType) {
            this.comMailType = comMailType;
        }

        public int getComConvertible() {
            return comConvertible;
        }

        public void setComConvertible(int comConvertible) {
            this.comConvertible = comConvertible;
        }

        public String getMattersContentDefault() {
            return mattersContentDefault;
        }

        public void setMattersContentDefault(String mattersContentDefault) {
            this.mattersContentDefault = mattersContentDefault;
        }

        public int getComIntegral() {
            return comIntegral;
        }

        public void setComIntegral(int comIntegral) {
            this.comIntegral = comIntegral;
        }

        public int getMattersType() {
            return mattersType;
        }

        public void setMattersType(int mattersType) {
            this.mattersType = mattersType;
        }

        public String getMattersContent() {
            return mattersContent;
        }

        public void setMattersContent(String mattersContent) {
            this.mattersContent = mattersContent;
        }

        public int getComMoney() {
            return comMoney;
        }

        public void setComMoney(int comMoney) {
            this.comMoney = comMoney;
        }

        public int getComMailMoney() {
            return comMailMoney;
        }

        public void setComMailMoney(int comMailMoney) {
            this.comMailMoney = comMailMoney;
        }

        public int getComExctype() {
            return comExctype;
        }

        public void setComExctype(int comExctype) {
            this.comExctype = comExctype;
        }

        public String getComNumber() {
            return comNumber;
        }

        public void setComNumber(String comNumber) {
            this.comNumber = comNumber;
        }

        public String getActivityBeginTime() {
            return activityBeginTime;
        }

        public void setActivityBeginTime(String activityBeginTime) {
            this.activityBeginTime = activityBeginTime;
        }

        public int getActivityTimetype() {
            return activityTimetype;
        }

        public void setActivityTimetype(int activityTimetype) {
            this.activityTimetype = activityTimetype;
        }

        public String getActivityEndTime() {
            return activityEndTime;
        }

        public void setActivityEndTime(String activityEndTime) {
            this.activityEndTime = activityEndTime;
        }

        public int getExchangeCount() {
            return exchangeCount;
        }

        public void setExchangeCount(int exchangeCount) {
            this.exchangeCount = exchangeCount;
        }

        public String getComName() {
            return comName;
        }

        public void setComName(String comName) {
            this.comName = comName;
        }

        public int getComId() {
            return comId;
        }

        public void setComId(int comId) {
            this.comId = comId;
        }

        public String getComBrief() {
            return comBrief;
        }

        public void setComBrief(String comBrief) {
            this.comBrief = comBrief;
        }

        public int getActivityType() {
            return activityType;
        }

        public void setActivityType(int activityType) {
            this.activityType = activityType;
        }

        public String getComImgUrl() {
            return comImgUrl;
        }

        public void setComImgUrl(String comImgUrl) {
            this.comImgUrl = comImgUrl;
        }

        public int getComCount() {
            return comCount;
        }

        public void setComCount(int comCount) {
            this.comCount = comCount;
        }
    }
}
