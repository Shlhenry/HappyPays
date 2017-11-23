package com.futeng.happypays.utils;

import java.util.List;

/**
 * Created by android on 2017/10/19.
 */

public class StoreBean {

    /**
     * code : 00
     * message : 成功
     * successMessage : 操作成功
     * isOK : true
     * list : [{"comExctype":2,"comNumber":"20171017100832002002","comIntegral":2000,"exchangeCount":0,"comName":"红牛10","comId":10,"comMoney":6,"comImgUrl":"http://120.27.194.146/commodityImg/1/1/20171017100832002002.png","comCount":20},{"comExctype":2,"comNumber":"20171017100832002002","comIntegral":2000,"exchangeCount":0,"comName":"红牛9","comId":9,"comMoney":6,"comImgUrl":"http://120.27.194.146/commodityImg/1/1/20171017100832002002.png","comCount":20},{"comExctype":2,"comNumber":"20171017100832002002","comIntegral":2000,"exchangeCount":0,"comName":"红牛8","comId":8,"comMoney":6,"comImgUrl":"http://120.27.194.146/commodityImg/1/1/20171017100832002002.png","comCount":20},{"comExctype":2,"comNumber":"20171017100832002002","comIntegral":2000,"exchangeCount":0,"comName":"红牛7","comId":7,"comMoney":6,"comImgUrl":"http://120.27.194.146/commodityImg/1/1/20171017100832002002.png","comCount":20},{"comExctype":2,"comNumber":"20171017100832002002","comIntegral":2000,"exchangeCount":0,"comName":"红牛6","comId":6,"comMoney":6,"comImgUrl":"http://120.27.194.146/commodityImg/1/1/20171017100832002002.png","comCount":20},{"comExctype":2,"comNumber":"20171017100832002002","comIntegral":2000,"exchangeCount":0,"comName":"红牛5","comId":5,"comMoney":6,"comImgUrl":"http://120.27.194.146/commodityImg/1/1/20171017100832002002.png","comCount":20},{"comExctype":2,"comNumber":"20171017100832002002","comIntegral":2000,"exchangeCount":0,"comName":"红牛4","comId":4,"comMoney":6,"comImgUrl":"http://120.27.194.146/commodityImg/1/1/20171017100832002002.png","comCount":20},{"comExctype":2,"comNumber":"20171017100832002002","comIntegral":2000,"exchangeCount":0,"comName":"红牛3","comId":3,"comMoney":6,"comImgUrl":"http://120.27.194.146/commodityImg/1/1/20171017100832002002.png","comCount":20},{"comExctype":2,"comNumber":"20171017100832002002","comIntegral":2000,"exchangeCount":1,"comName":"红牛2","comId":2,"comMoney":6,"comImgUrl":"http://120.27.194.146/commodityImg/1/1/20171017100832002002.png","comCount":20},{"comExctype":2,"comNumber":"20171017100832002002","comIntegral":2000,"exchangeCount":2,"comName":"红牛1","comId":1,"comMoney":6,"comImgUrl":"http://120.27.194.146/commodityImg/1/1/20171017100832002002.png","comCount":20}]
     */

    private String code;
    private String message;
    private String successMessage;
    private boolean isOK;
    /**
     * comExctype : 2
     * comNumber : 20171017100832002002
     * comIntegral : 2000
     * exchangeCount : 0
     * comName : 红牛10
     * comId : 10
     * comMoney : 6
     * comImgUrl : http://120.27.194.146/commodityImg/1/1/20171017100832002002.png
     * comCount : 20
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
        private int comExctype;
        private String comNumber;
        private int comIntegral;
        private int exchangeCount;
        private String comName;
        private int comId;
        private int comMoney;
        private String comImgUrl;
        private int comCount;

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

        public int getComIntegral() {
            return comIntegral;
        }

        public void setComIntegral(int comIntegral) {
            this.comIntegral = comIntegral;
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

        public int getComMoney() {
            return comMoney;
        }

        public void setComMoney(int comMoney) {
            this.comMoney = comMoney;
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
