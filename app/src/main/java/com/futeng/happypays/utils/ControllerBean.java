package com.futeng.happypays.utils;

import java.util.List;

/**
 * Created by Administrator on 2017/7/29.
 */
public class ControllerBean {
    /**
     * code : 00
     * message : 成功
     * successMessage : 操作成功
     * isOK : true
     * list : [{"switchParam":"qrCode","switchName":"二维码收款","switchValue":"1,1","switchMessage":"暂未开通1","switchId":101},{"switchParam":"mposReceivables","switchName":"MPOS收款","switchValue":"1,1","switchMessage":"暂未开通2","switchId":102},{"switchParam":"noCardShortcut","switchName":"无卡快捷","switchValue":"1,1","switchMessage":"暂未开通3","switchId":103},{"switchParam":"qrCodeWater","switchName":"二维码交易流水","switchValue":"1,1","switchMessage":"暂未开通4","switchId":104},{"switchParam":"mposReceivablesWater","switchName":"MPOS收款流水","switchValue":"1,1","switchMessage":"暂未开通5","switchId":105},{"switchParam":"noCardShortcutWater","switchName":"无卡快捷流水","switchValue":"1,1","switchMessage":"暂未开通6","switchId":106},{"switchParam":"creditCardApplication","switchName":"信用卡申请","switchValue":"0,0","switchMessage":"暂未开通7","switchId":107},{"switchParam":"creditCardPayment","switchName":"信用卡还款","switchValue":"0,0","switchMessage":"暂未开通8","switchId":108},{"switchParam":"quickAndEasyCredit","switchName":"易速贷","switchValue":"0,0","switchMessage":"暂未开通9","switchId":109},{"switchParam":"phoneReplenishing","switchName":"手机充值","switchValue":"0,0","switchMessage":"暂未开通10","switchId":110},{"switchParam":"wateFeePaid","switchName":"缴水费","switchValue":"0,0","switchMessage":"暂未开通11","switchId":111},{"switchParam":"payElectricityBill","switchName":"缴电费","switchValue":"0,0","switchMessage":"暂未开通12","switchId":112},{"switchParam":"auth","switchName":"实名认证","switchValue":"1,1","switchMessage":"暂未开通13","switchId":113},{"switchParam":"myTeam","switchName":"我的团队","switchValue":"1,1","switchMessage":"暂未开通14","switchId":114},{"switchParam":"sharingCeremony","switchName":"分享有礼","switchValue":"1,1","switchMessage":"暂未开通15","switchId":115}]
     */

    private String code;
    private String message;
    private String successMessage;
    private boolean isOK;
    /**
     * switchParam : qrCode
     * switchName : 二维码收款
     * switchValue : 1,1
     * switchMessage : 暂未开通1
     * switchId : 101
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
        private String switchParam;
        private String switchName;
        private String switchValue;
        private String switchMessage;
        private int switchId;

        public String getSwitchParam() {
            return switchParam;
        }

        public void setSwitchParam(String switchParam) {
            this.switchParam = switchParam;
        }

        public String getSwitchName() {
            return switchName;
        }

        public void setSwitchName(String switchName) {
            this.switchName = switchName;
        }

        public String getSwitchValue() {
            return switchValue;
        }

        public void setSwitchValue(String switchValue) {
            this.switchValue = switchValue;
        }

        public String getSwitchMessage() {
            return switchMessage;
        }

        public void setSwitchMessage(String switchMessage) {
            this.switchMessage = switchMessage;
        }

        public int getSwitchId() {
            return switchId;
        }

        public void setSwitchId(int switchId) {
            this.switchId = switchId;
        }
    }
}
