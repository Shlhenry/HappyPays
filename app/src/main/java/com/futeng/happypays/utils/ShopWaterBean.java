package com.futeng.happypays.utils;

import java.util.List;

/**
 * Created by Administrator on 2017/4/17.
 */
public class ShopWaterBean {
    /**
     * code : 00
     * message : 成功
     * isOK : true
     * list : [{"tranTime":"2017-04-14 14:45:27","tran2":"********","macSerial":"000001","macNumber":"00000001","tranFee":0,"tran37":"JSZT20170414144451708104743","macBatch":"000001","tranId":37,"mccName":"微信T1","amNumber":"882000000000061","tranMoney":0.1},{"tranTime":"2017-04-13 18:13:42","tran2":"********","macSerial":"000001","macNumber":"00000001","tranFee":0,"tran37":"JSZT20170413181325699102513","macBatch":"000001","tranId":36,"mccName":"微信T1","amNumber":"882000000000061","tranMoney":0.2}]
     */

    private String code;
    private String message;
    private boolean isOK;
    private String successMessage;
    /**
     * tranTime : 2017-04-14 14:45:27
     * tran2 : ********
     * macSerial : 000001
     * macNumber : 00000001
     * tranFee : 0
     * tran37 : JSZT20170414144451708104743
     * macBatch : 000001
     * tranId : 37
     * mccName : 微信T1
     * amNumber : 882000000000061
     * tranMoney : 0.1
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

    public String getSuccessMessage() {
        return successMessage;
    }

    public void setSuccessMessage(String successMessage) {
        this.successMessage = successMessage;
    }




    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        private String tranTime;
        private String tran2;
        private String macSerial;
        private String macNumber;
        private String tranFee;
        private String tran37;
        private String macBatch;
        private int tranId;
        private String mccName;
        private String amNumber;
        private double tranMoney;

        public String getTran14() {
            return tran14;
        }

        public void setTran14(String tran14) {
            this.tran14 = tran14;
        }

        private String tran14;
        /**
         * successMessage : 已加载所有数据
         */



        public String getTranTime() {
            return tranTime;
        }

        public void setTranTime(String tranTime) {
            this.tranTime = tranTime;
        }

        public String getTran2() {
            return tran2;
        }

        public void setTran2(String tran2) {
            this.tran2 = tran2;
        }

        public String getMacSerial() {
            return macSerial;
        }

        public void setMacSerial(String macSerial) {
            this.macSerial = macSerial;
        }

        public String getMacNumber() {
            return macNumber;
        }

        public void setMacNumber(String macNumber) {
            this.macNumber = macNumber;
        }

        public String getTranFee() {
            return tranFee;
        }

        public void setTranFee(String tranFee) {
            this.tranFee = tranFee;
        }

        public String getTran37() {
            return tran37;
        }

        public void setTran37(String tran37) {
            this.tran37 = tran37;
        }

        public String getMacBatch() {
            return macBatch;
        }

        public void setMacBatch(String macBatch) {
            this.macBatch = macBatch;
        }

        public int getTranId() {
            return tranId;
        }

        public void setTranId(int tranId) {
            this.tranId = tranId;
        }

        public String getMccName() {
            return mccName;
        }

        public void setMccName(String mccName) {
            this.mccName = mccName;
        }

        public String getAmNumber() {
            return amNumber;
        }

        public void setAmNumber(String amNumber) {
            this.amNumber = amNumber;
        }

        public double getTranMoney() {
            return tranMoney;
        }

        public void setTranMoney(double tranMoney) {
            this.tranMoney = tranMoney;
        }

    }
}
