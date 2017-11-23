package com.futeng.happypays.utils;

/**
 * Created by Administrator on 2017/8/15.
 */
public class RuleBean {

    /**
     * code : 00
     * message : 成功
     * successMessage : 操作成功
     * isOK : true
     * map : {"expTime":"2017-08-15 09:07:44","expExplain":"创乐付说说明内容创乐付说明内容。","expName":"创乐付","expState":1,"expRemark":"备注说明","expId":1}
     */

    private String code;
    private String message;
    private String successMessage;
    private boolean isOK;
    /**
     * expTime : 2017-08-15 09:07:44
     * expExplain : 创乐付说说明内容创乐付说明内容。
     * expName : 创乐付
     * expState : 1
     * expRemark : 备注说明
     * expId : 1
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
        private String expTime;
        private String expExplain;
        private String expName;
        private int expState;
        private String expRemark;
        private int expId;

        public String getExpTime() {
            return expTime;
        }

        public void setExpTime(String expTime) {
            this.expTime = expTime;
        }

        public String getExpExplain() {
            return expExplain;
        }

        public void setExpExplain(String expExplain) {
            this.expExplain = expExplain;
        }

        public String getExpName() {
            return expName;
        }

        public void setExpName(String expName) {
            this.expName = expName;
        }

        public int getExpState() {
            return expState;
        }

        public void setExpState(int expState) {
            this.expState = expState;
        }

        public String getExpRemark() {
            return expRemark;
        }

        public void setExpRemark(String expRemark) {
            this.expRemark = expRemark;
        }

        public int getExpId() {
            return expId;
        }

        public void setExpId(int expId) {
            this.expId = expId;
        }
    }
}
