package com.futeng.happypays.utils;

import java.util.List;

/**
 * Created by Administrator on 2017/4/7.
 */
public class BankBean {
    /**
     * code : 00
     * message : 成功
     * isOK : true
     * list : [{"bankName":"广东发展银行","bankId":1},{"bankName":"华夏银行股份有限公司","bankId":2},{"bankName":"浙江泰隆商业银行","bankId":3},{"bankName":"杭州银行","bankId":4},{"bankName":"平安银行股份有限公司","bankId":5},{"bankName":"青岛银行股份有限公司","bankId":6},{"bankName":"浙江省农村信用社联合社","bankId":7},{"bankName":"哈尔滨银行","bankId":8},{"bankName":"龙江银行","bankId":9},{"bankName":"中国民生银行","bankId":10},{"bankName":"光大银行","bankId":11},{"bankName":"哈尔滨银行","bankId":12},{"bankName":"中国建设银行股份有限公司总行","bankId":13},{"bankName":"中国农业银行股份有限公司","bankId":14},{"bankName":"招商银行股份有限公司","bankId":15},{"bankName":"北京银行","bankId":16},{"bankName":"交通银行","bankId":17},{"bankName":"中国银行总行","bankId":18},{"bankName":"兴业银行总行","bankId":19},{"bankName":"中信银行股份有限公司","bankId":20},{"bankName":"浦发银行","bankId":21},{"bankName":"中国光大银行","bankId":22},{"bankName":"中国邮政储蓄银行有限责任公司","bankId":23},{"bankName":"中国工商银行股份有限公司","bankId":24},{"bankName":"利津舜丰村镇银行","bankId":25},{"bankName":"杭州联合农村商业银行股份有限公司","bankId":26},{"bankName":"恒丰银行","bankId":27},{"bankName":"浙江民泰商业银行股份有限公司","bankId":28}]
     */

    private String code;
    private String message;
    private boolean isOK;
    /**
     * bankName : 广东发展银行
     * bankId : 1
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
        private String bankName;
        private int bankId;

        public String getBankSimpleName() {
            return bankSimpleName;
        }

        public void setBankSimpleName(String bankSimpleName) {
            this.bankSimpleName = bankSimpleName;
        }

        private String bankSimpleName;

        public String getBankName() {
            return bankName;
        }

        public void setBankName(String bankName) {
            this.bankName = bankName;
        }

        public int getBankId() {
            return bankId;
        }

        public void setBankId(int bankId) {
            this.bankId = bankId;
        }
    }

}
