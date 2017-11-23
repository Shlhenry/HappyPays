package com.futeng.happypays.utils;

import java.util.List;

/**
 * Created by Administrator on 2017/11/14.
 */

public class ChangerecordBean {


    /**
     * code : 00
     * message : 成功
     * successMessage : 操作成功
     * isOK : true
     * list : [{"paoState":"待发货","oldIntegral":2,"exrcOrderNo":"20171120101516854775","exrcCount":1,"comName":"红牛58","exrcId":162,"comId":58,"exrcTime":"2017-11-20 10:15:16","url":"http://120.27.194.146/commodityImg/1/1/20171017100832002002.png"},{"paoState":"待发货","oldIntegral":2,"exrcOrderNo":"20171120094232565966","exrcCount":1,"comName":"红牛58","exrcId":161,"comId":58,"exrcTime":"2017-11-20 09:42:32","url":"http://120.27.194.146/commodityImg/1/1/20171017100832002002.png"},{"paoState":"待发货","oldIntegral":2,"exrcOrderNo":"20171117181449548554","exrcCount":1,"comName":"红牛59","exrcId":146,"comId":59,"exrcTime":"2017-11-17 18:14:49","url":"http://120.27.194.146/commodityImg/1/1/20171017100832002002.png"},{"paoState":"待发货","oldIntegral":2,"exrcOrderNo":"20171117181244057587","exrcCount":1,"comName":"红牛59","exrcId":145,"comId":59,"exrcTime":"2017-11-17 18:12:44","url":"http://120.27.194.146/commodityImg/1/1/20171017100832002002.png"},{"paoState":"待发货","oldIntegral":2,"exrcOrderNo":"20171117172708960253","exrcCount":1,"comName":"红牛59","exrcId":144,"comId":59,"exrcTime":"2017-11-17 17:27:08","url":"http://120.27.194.146/commodityImg/1/1/20171017100832002002.png"},{"paoState":"待发货","oldIntegral":2,"exrcOrderNo":"20171117171058777523","exrcCount":1,"comName":"红牛57","exrcId":143,"comId":57,"exrcTime":"2017-11-17 17:10:58","url":"http://120.27.194.146/commodityImg/1/1/20171017100832002002.png"},{"paoState":"待发货","oldIntegral":2,"exrcOrderNo":"20171117170752057240","exrcCount":1,"comName":"红牛59","exrcId":142,"comId":59,"exrcTime":"2017-11-17 17:07:52","url":"http://120.27.194.146/commodityImg/1/1/20171017100832002002.png"},{"paoState":"已发货","oldIntegral":560,"exrcOrderNo":"20171117170653738998","exrcCount":1,"expName":"中通快递","comName":"纯天然植物卸妆油","exrcId":141,"comId":114,"exrcTime":"2017-11-17 17:06:53","expressOrderNo":"164516455","url":"http://120.27.194.146/commodityImg/3/26/20171117161255003026.png"},{"paoState":"待发货","oldIntegral":2,"exrcOrderNo":"20171114101205730627","exrcCount":2,"comName":"红牛58","exrcId":123,"comId":58,"exrcTime":"2017-11-14 10:12:05","url":"http://120.27.194.146/commodityImg/1/1/20171017100832002002.png"},{"paoState":"待发货","oldIntegral":2,"exrcOrderNo":"20171114101121069308","exrcCount":1,"comName":"红牛59","exrcId":122,"comId":59,"exrcTime":"2017-11-14 10:11:21","url":"http://120.27.194.146/commodityImg/1/1/20171017100832002002.png"},{"paoState":"待发货","oldIntegral":2,"exrcOrderNo":"20171114100620742071","exrcCount":1,"comName":"红牛59","exrcId":121,"comId":59,"exrcTime":"2017-11-14 10:06:20","url":"http://120.27.194.146/commodityImg/1/1/20171017100832002002.png"},{"paoState":"待发货","oldIntegral":2,"exrcOrderNo":"20171114095841392904","exrcCount":5,"comName":"红牛59","exrcId":120,"comId":59,"exrcTime":"2017-11-14 09:58:41","url":"http://120.27.194.146/commodityImg/1/1/20171017100832002002.png"},{"paoState":"待发货","oldIntegral":2,"exrcOrderNo":"20171114095815209727","exrcCount":1,"comName":"红牛59","exrcId":119,"comId":59,"exrcTime":"2017-11-14 09:58:15","url":"http://120.27.194.146/commodityImg/1/1/20171017100832002002.png"},{"paoState":"待发货","oldIntegral":2,"exrcOrderNo":"20171114095808419765","exrcCount":1,"comName":"红牛59","exrcId":118,"comId":59,"exrcTime":"2017-11-14 09:58:08","url":"http://120.27.194.146/commodityImg/1/1/20171017100832002002.png"},{"paoState":"待发货","oldIntegral":2,"exrcOrderNo":"20171114095447935815","exrcCount":1,"comName":"红牛58","exrcId":117,"comId":58,"exrcTime":"2017-11-14 09:54:47","url":"http://120.27.194.146/commodityImg/1/1/20171017100832002002.png"},{"paoState":"待发货","oldIntegral":2,"exrcOrderNo":"20171113174859581937","exrcCount":5,"comName":"红牛58","exrcId":116,"comId":58,"exrcTime":"2017-11-13 17:48:59","url":"http://120.27.194.146/commodityImg/1/1/20171017100832002002.png"},{"paoState":"待发货","oldIntegral":2,"exrcOrderNo":"20171113174831419701","exrcCount":2,"comName":"红牛60","exrcId":115,"comId":60,"exrcTime":"2017-11-13 17:48:31","url":"http://120.27.194.146/commodityImg/1/1/20171017100832002002.png"},{"paoState":"待发货","oldIntegral":2,"exrcOrderNo":"20171113174812281241","exrcCount":1,"comName":"红牛60","exrcId":114,"comId":60,"exrcTime":"2017-11-13 17:48:12","url":"http://120.27.194.146/commodityImg/1/1/20171017100832002002.png"},{"paoState":"待发货","oldIntegral":2,"exrcOrderNo":"20171113174810386648","exrcCount":1,"comName":"红牛60","exrcId":113,"comId":60,"exrcTime":"2017-11-13 17:48:10","url":"http://120.27.194.146/commodityImg/1/1/20171017100832002002.png"},{"paoState":"待发货","oldIntegral":2,"exrcOrderNo":"20171113174808101113","exrcCount":1,"comName":"红牛60","exrcId":112,"comId":60,"exrcTime":"2017-11-13 17:48:08","url":"http://120.27.194.146/commodityImg/1/1/20171017100832002002.png"}]
     */

    private String code;
    private String message;
    private String successMessage;
    private boolean isOK;
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
        /**
         * paoState : 待发货
         * oldIntegral : 2
         * exrcOrderNo : 20171120101516854775
         * exrcCount : 1
         * comName : 红牛58
         * exrcId : 162
         * comId : 58
         * exrcTime : 2017-11-20 10:15:16
         * url : http://120.27.194.146/commodityImg/1/1/20171017100832002002.png
         * expName : 中通快递
         * expressOrderNo : 164516455
         */

        private String paoState;
        private int oldIntegral;
        private String exrcOrderNo;
        private int exrcCount;
        private String comName;
        private int exrcId;
        private int comId;
        private String exrcTime;
        private String url;
        private String expName;
        private String expressOrderNo;

        public String getPaoState() {
            return paoState;
        }

        public void setPaoState(String paoState) {
            this.paoState = paoState;
        }

        public int getOldIntegral() {
            return oldIntegral;
        }

        public void setOldIntegral(int oldIntegral) {
            this.oldIntegral = oldIntegral;
        }

        public String getExrcOrderNo() {
            return exrcOrderNo;
        }

        public void setExrcOrderNo(String exrcOrderNo) {
            this.exrcOrderNo = exrcOrderNo;
        }

        public int getExrcCount() {
            return exrcCount;
        }

        public void setExrcCount(int exrcCount) {
            this.exrcCount = exrcCount;
        }

        public String getComName() {
            return comName;
        }

        public void setComName(String comName) {
            this.comName = comName;
        }

        public int getExrcId() {
            return exrcId;
        }

        public void setExrcId(int exrcId) {
            this.exrcId = exrcId;
        }

        public int getComId() {
            return comId;
        }

        public void setComId(int comId) {
            this.comId = comId;
        }

        public String getExrcTime() {
            return exrcTime;
        }

        public void setExrcTime(String exrcTime) {
            this.exrcTime = exrcTime;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getExpName() {
            return expName;
        }

        public void setExpName(String expName) {
            this.expName = expName;
        }

        public String getExpressOrderNo() {
            return expressOrderNo;
        }

        public void setExpressOrderNo(String expressOrderNo) {
            this.expressOrderNo = expressOrderNo;
        }
    }
}
