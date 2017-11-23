package com.futeng.happypays.utils;

import java.util.List;

/**
 * Created by Administrator on 2017/4/5.
 */
public class GetAuthenBean {
    /**
     * code : 00
     * message : 成功
     * isOK : true
     * list : [{"bfMinsize":10,"bfFileName":"身份证正面","bfName":"positiveidcard","bfMaxsize":1024,"bfExtends":"jpg,png,JPG"},{"bfMinsize":10,"bfFileName":"身份证反面","bfName":"idcard","bfMaxsize":2048,"bfExtends":"jpg,png,JPG"},{"bfMinsize":10,"bfFileName":"银行卡正面","bfName":"bankcardfront","bfMaxsize":2048,"bfExtends":"jpg,png,JPG"},{"bfMinsize":10,"bfFileName":"银行卡反面","bfName":"reversebankcard","bfMaxsize":2048,"bfExtends":"jpg,png,JPG"},{"bfMinsize":10,"bfFileName":"手持身份证和银行卡","bfName":"handidcardandbankcard","bfMaxsize":1024,"bfExtends":"jpg,png,JPG"}]
     */

    private String code;
    private String message;
    private boolean isOK;
    /**
     * bfMinsize : 10.0
     * bfFileName : 身份证正面
     * bfName : positiveidcard
     * bfMaxsize : 1024.0
     * bfExtends : jpg,png,JPG
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
        private double bfMinsize;
        private String bfFileName;
        private String bfName;
        private double bfMaxsize;
        private String bfExtends;

        public double getBfMinsize() {
            return bfMinsize;
        }

        public void setBfMinsize(double bfMinsize) {
            this.bfMinsize = bfMinsize;
        }

        public String getBfFileName() {
            return bfFileName;
        }

        public void setBfFileName(String bfFileName) {
            this.bfFileName = bfFileName;
        }

        public String getBfName() {
            return bfName;
        }

        public void setBfName(String bfName) {
            this.bfName = bfName;
        }

        public double getBfMaxsize() {
            return bfMaxsize;
        }

        public void setBfMaxsize(double bfMaxsize) {
            this.bfMaxsize = bfMaxsize;
        }

        public String getBfExtends() {
            return bfExtends;
        }

        public void setBfExtends(String bfExtends) {
            this.bfExtends = bfExtends;
        }
    }
}
