package com.futeng.happypays.utils;

import java.util.List;

/**
 * Created by Administrator on 2017/5/4.
 */
public class ZfbwatermoneyBean {
    /**
     * code : 00
     * message : 成功
     * isOK : true
     * list : [{"tranTime":"2017-05-03 20:20:23","tran2":"********","macSerial":"000001","macNumber":"00000000","tranFee":0.06,"tran37":"51863176170503201913","macBatch":"000001","tranId":122,"mccName":"支付宝T1","amNumber":"883000000000105","tranMoney":12},{"tranTime":"2017-05-03 20:18:53","tran2":"********","macSerial":"000001","macNumber":"00000000","tranFee":0.08,"tran37":"58294826170503201804","macBatch":"000001","tranId":121,"mccName":"支付宝D0","amNumber":"883000000000105","tranMoney":12}]
     */

    private String code;
    private String message;
    private boolean isOK;

    public String getSuccessMessage() {
        return successMessage;
    }

    public void setSuccessMessage(String successMessage) {
        this.successMessage = successMessage;
    }

    private String successMessage;
    /**
     * tranTime : 2017-05-03 20:20:23
     * tran2 : ********
     * macSerial : 000001
     * macNumber : 00000000
     * tranFee : 0.06
     * tran37 : 51863176170503201913
     * macBatch : 000001
     * tranId : 122
     * mccName : 支付宝T1
     * amNumber : 883000000000105
     * tranMoney : 12
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
        private String tranTime;
        private String tran2;
        private String macSerial;
        private String macNumber;
        private double tranFee;
        private String tran37;
        private String macBatch;
        private int tranId;
        private String mccName;
        private String amNumber;
        private double tranMoney;

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

        public double getTranFee() {
            return tranFee;
        }

        public void setTranFee(double tranFee) {
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
