package com.futeng.happypays.utils;

import java.util.List;

/**
 * Created by Administrator on 2017/9/21.
 */
public class RealtimeBean {

    /**
     * code : 00
     * message : 成功
     * successMessage : 操作成功
     * isOK : true
     * list : [{"excValue":744.56,"curName":"菲律宾比索","curCode":"PHP","curImgUrl":"http://101.37.168.236/currency/PHP.png"},{"excValue":100,"curName":"人民币","curCode":"CNY","curImgUrl":"http://101.37.168.236/currency/CNY.png"},{"excValue":15.33,"curName":"美元","curCode":"USD","curImgUrl":"http://101.37.168.236/currency/USD.png"},{"excValue":120.26,"curName":"港币","curCode":"HKD","curImgUrl":"http://101.37.168.236/currency/HKD.png"},{"excValue":509.13,"curName":"泰铢","curCode":"THB","curImgUrl":"http://101.37.168.236/currency/THB.png"},{"excValue":1665,"curName":"日元","curCode":"JPY","curImgUrl":"http://101.37.168.236/currency/JPY.png"},{"excValue":19.06,"curName":"澳元","curCode":"AUD","curImgUrl":"http://101.37.168.236/currency/AUD.png"},{"excValue":20.56,"curName":"新加坡元","curCode":"SGD","curImgUrl":"http://101.37.168.236/currency/SGD.png"},{"excValue":12.74,"curName":"欧元","curCode":"EUR","curImgUrl":"http://101.37.168.236/currency/EUR.png"},{"excValue":21.16,"curName":"新西兰元","curCode":"NZD","curImgUrl":"http://101.37.168.236/currency/NZD.png"},{"excValue":11.61,"curName":"英镑","curCode":"GBP","curImgUrl":"http://101.37.168.236/currency/GBP.png"},{"excValue":18.59,"curName":"加拿大元","curCode":"CAD","curImgUrl":"http://101.37.168.236/currency/CAD.png"},{"excValue":17216,"curName":"韩元","curCode":"KRW","curImgUrl":"http://101.37.168.236/currency/KRW.png"},{"excValue":869.26,"curName":"俄罗斯卢布","curCode":"RUB","curImgUrl":"http://101.37.168.236/currency/RUB.png"},{"excValue":63.92,"curName":"马来西亚林吉特","curCode":"MYR","curImgUrl":"http://101.37.168.236/currency/MYR.png"},{"excValue":14.49,"curName":"瑞士法郎","curCode":"CHF","curImgUrl":"http://101.37.168.236/currency/CHF.png"},{"excValue":121.62,"curName":"瑞典克朗","curCode":"SEK","curImgUrl":"http://101.37.168.236/currency/SEK.png"},{"excValue":94.8,"curName":"丹麦克朗","curCode":"DKK","curImgUrl":"http://101.37.168.236/currency/DKK.png"},{"excValue":118.62,"curName":"挪威克朗","curCode":"NOK","curImgUrl":"http://101.37.168.236/currency/NOK.png"},{"excValue":56.19,"curName":"阿联酋迪拉姆","curCode":"AED","curImgUrl":"http://101.37.168.236/currency/AED.png"},{"excValue":980.02,"curName":"印尼卢比","curCode":"IDR","curImgUrl":"http://101.37.168.236/currency/IDR.png"},{"excValue":333.15,"curName":"捷克克朗","curCode":"CZK","curImgUrl":"http://101.37.168.236/currency/CZK.png"},{"excValue":2323.19,"curName":"斯里兰卡卢比","curCode":"LKR","curImgUrl":"http://101.37.168.236/currency/LKR.png"},{"excValue":197.29,"curName":"南非兰特","curCode":"ZAR","curImgUrl":"http://101.37.168.236/currency/ZAR.png"},{"excValue":456.11,"curName":"新台币","curCode":"TWD","curImgUrl":"http://101.37.168.236/currency/TWD.png"},{"excValue":123.58,"curName":"澳门币","curCode":"MOP","curImgUrl":"http://101.37.168.236/currency/MOP.png"},{"excValue":26.09,"curName":"阿塞拜疆马纳特","curCode":"AZN","curImgUrl":"http://101.37.168.236/currency/AZN.png"},{"excValue":1627.72,"curName":"冰岛克朗","curCode":"ISK","curImgUrl":"http://101.37.168.236/currency/ISK.png"},{"excValue":47.41,"curName":"巴西雷亚尔","curCode":"BRL","curImgUrl":"http://101.37.168.236/currency/BRL.png"},{"excValue":54.21,"curName":"波兰兹罗提","curCode":"PLN","curImgUrl":"http://101.37.168.236/currency/PLN.png"},{"excValue":5149.07,"curName":"哈沙克斯坦坚戈","curCode":"KZT","curImgUrl":"http://101.37.168.236/currency/KZT.png"},{"excValue":62266.5,"curName":"柬埔寨瑞尔","curCode":"KHR","curImgUrl":"http://101.37.168.236/currency/KHR.png"},{"excValue":56.65,"curName":"卡塔尔里亚尔","curCode":"QAR","curImgUrl":"http://101.37.168.236/currency/QAR.png"},{"excValue":58.78,"curName":"罗马尼亚新列伊","curCode":"RON","curImgUrl":"http://101.37.168.236/currency/RON.png"},{"excValue":1567.96,"curName":"尼泊尔卢比","curCode":"NPR","curImgUrl":"http://101.37.168.236/currency/NPR.png"},{"excValue":198.14,"curName":"纳米比亚元","curCode":"NAD","curImgUrl":"http://101.37.168.236/currency/NAD.png"},{"excValue":57.56,"curName":"沙特里亚尔","curCode":"SAR","curImgUrl":"http://101.37.168.236/currency/SAR.png"},{"excValue":980.02,"curName":"印度卢比","curCode":"INR","curImgUrl":"http://101.37.168.236/currency/INR.png"},{"excValue":7339.45,"curName":"亚美尼亚德拉姆","curCode":"AMD","curImgUrl":"http://101.37.168.236/currency/AMD.png"}]
     */

    private String code;
    private String message;
    private String successMessage;
    private boolean isOK;
    /**
     * excValue : 744.56
     * curName : 菲律宾比索
     * curCode : PHP
     * curImgUrl : http://101.37.168.236/currency/PHP.png
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
        private double excValue;
        private String curName;
        private String curCode;
        private String curImgUrl;

        public double getExcValue() {
            return excValue;
        }

        public void setExcValue(double excValue) {
            this.excValue = excValue;
        }

        public String getCurName() {
            return curName;
        }

        public void setCurName(String curName) {
            this.curName = curName;
        }

        public String getCurCode() {
            return curCode;
        }

        public void setCurCode(String curCode) {
            this.curCode = curCode;
        }

        public String getCurImgUrl() {
            return curImgUrl;
        }

        public void setCurImgUrl(String curImgUrl) {
            this.curImgUrl = curImgUrl;
        }
    }
}
