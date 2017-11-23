package com.futeng.happypays.activity.kit;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Administrator on 2017/9/26.
 */

public class TransactionResult {

    /**
     * code : 00
     * message : 成功
     * successMessage : 操作成功
     * isOK : true
     * map : {"47":{"mcc":47,"count":1,"money":15,"mccName":"支付宝T1"},"81":{"mcc":81,"count":2,"money":20818,"mccName":"2W无卡支付T1"},"99":{"count":3,"money":20833}}
     * list : [{"tranTime":"2017-09-04","count":3,"money":0.03},{"tranTime":"2017-09-15","count":9,"money":54000},{"tranTime":"2017-09-16","count":8,"money":73125},{"tranTime":"2017-09-18","count":3,"money":31000},{"tranTime":"2017-09-19","count":10,"money":0.1},{"tranTime":"2017-09-25","count":3,"money":20833},{"tranTime":"2017-09-26","count":1,"money":10000}]
     */

    private String code;
    private String message;
    private String successMessage;
    private boolean isOK;
    private MapBean map;
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

    public MapBean getMap() {
        return map;
    }

    public void setMap(MapBean map) {
        this.map = map;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class MapBean {
        /**
         * 47 : {"mcc":47,"count":1,"money":15,"mccName":"支付宝T1"}
         * 81 : {"mcc":81,"count":2,"money":20818,"mccName":"2W无卡支付T1"}
         * 99 : {"count":3,"money":20833}
         */
//            45   46  47  48  81  82  83  84  99
        @SerializedName("45")
        private PayTypeBean _$45;
        @SerializedName("46")
        private PayTypeBean _$46;
        @SerializedName("47")
        private PayTypeBean _$47;
        @SerializedName("48")
        private PayTypeBean _$48;
        @SerializedName("81")
        private PayTypeBean _$81;
        @SerializedName("82")
        private PayTypeBean _$82;
        @SerializedName("83")
        private PayTypeBean _$83;
        @SerializedName("84")
        private PayTypeBean _$84;
        @SerializedName("99")
        private PriceBean _$99;

        public PayTypeBean get_$45() {
            return _$45;
        }

        public void set_$45(PayTypeBean _$45) {
            this._$45 = _$45;
        }

        public PayTypeBean get_$46() {
            return _$46;
        }

        public void set_$46(PayTypeBean _$46) {
            this._$46 = _$46;
        }

        public PayTypeBean get_$47() {
            return _$47;
        }

        public void set_$47(PayTypeBean _$47) {
            this._$47 = _$47;
        }

        public PayTypeBean get_$48() {
            return _$48;
        }

        public void set_$48(PayTypeBean _$48) {
            this._$48 = _$48;
        }

        public PayTypeBean get_$81() {
            return _$81;
        }

        public void set_$81(PayTypeBean _$81) {
            this._$81 = _$81;
        }

        public PayTypeBean get_$82() {
            return _$82;
        }

        public void set_$82(PayTypeBean _$82) {
            this._$82 = _$82;
        }

        public PayTypeBean get_$83() {
            return _$83;
        }

        public void set_$83(PayTypeBean _$83) {
            this._$83 = _$83;
        }

        public PayTypeBean get_$84() {
            return _$84;
        }

        public void set_$84(PayTypeBean _$84) {
            this._$84 = _$84;
        }

        public PriceBean get_$99() {
            return _$99;
        }

        public void set_$99(PriceBean _$99) {
            this._$99 = _$99;
        }

        public static class PayTypeBean {
            /**
             * mcc : 47
             * count : 1
             * money : 15
             * mccName : 支付宝T1
             */

            private int mcc;
            private int count;
            private double money;
            private String mccName;

            public int getMcc() {
                return mcc;
            }

            public void setMcc(int mcc) {
                this.mcc = mcc;
            }

            public int getCount() {
                return count;
            }

            public void setCount(int count) {
                this.count = count;
            }

            public double getMoney() {
                return money;
            }

            public void setMoney(double money) {
                this.money = money;
            }

            public String getMccName() {
                return mccName;
            }

            public void setMccName(String mccName) {
                this.mccName = mccName;
            }
        }

        public static class PriceBean {
            /**
             * count : 3
             * money : 20833
             */

            private double count;
            private double money;

            public double getCount() {
                return count;
            }

            public void setCount(int count) {
                this.count = count;
            }

            public double getMoney() {
                return money;
            }

            public void setMoney(int money) {
                this.money = money;
            }
        }
    }

    public static class ListBean {
        /**
         * tranTime : 2017-09-04
         * count : 3
         * money : 0.03
         */

        private String tranTime;
        private int count;
        private double money;

        public String getTranTime() {
            return tranTime;
        }

        public void setTranTime(String tranTime) {
            this.tranTime = tranTime;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public double getMoney() {
            return money;
        }

        public void setMoney(double money) {
            this.money = money;
        }
    }
}
