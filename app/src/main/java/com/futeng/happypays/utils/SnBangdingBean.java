package com.futeng.happypays.utils;

/**
 * Created by Administrator on 2017/5/16.
 */
public class SnBangdingBean {
    /**
     * code : 00
     * message : 成功
     * isOK : true
     * map : {"macZpk":"2C1123BEFBA8B55C551CE337083F8F25","macSerial":"000013","macZek":"CA83A9A1EB0ACAA0BEC722FBE4A7DBA4","macNumber":"00000102","macState":1,"macZak":"5F2D580CAAC4CF3FDEF9FCDF800BFCAB","macBatch":"000001","macId":102,"amNumber":"883000000000105","macZmk":"FD9D0C024B6D001545E5AFB90BDE72CE","macSn":"JHLM603160100066"}
     */

    private String code;
    private String message;

    public String getFailMessage() {
        return failMessage;
    }

    public void setFailMessage(String failMessage) {
        this.failMessage = failMessage;
    }

    private String failMessage;
    private boolean isOK;
    /**
     * macZpk : 2C1123BEFBA8B55C551CE337083F8F25
     * macSerial : 000013
     * macZek : CA83A9A1EB0ACAA0BEC722FBE4A7DBA4
     * macNumber : 00000102
     * macState : 1
     * macZak : 5F2D580CAAC4CF3FDEF9FCDF800BFCAB
     * macBatch : 000001
     * macId : 102
     * amNumber : 883000000000105
     * macZmk : FD9D0C024B6D001545E5AFB90BDE72CE
     * macSn : JHLM603160100066
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
        private String macZpk;
        private String macSerial;
        private String macZek;
        private String macNumber;
        private int macState;
        private String macZak;
        private String macBatch;
        private int macId;
        private String amNumber;
        private String macZmk;
        private String macSn;

        public String getMacZpk() {
            return macZpk;
        }

        public void setMacZpk(String macZpk) {
            this.macZpk = macZpk;
        }

        public String getMacSerial() {
            return macSerial;
        }

        public void setMacSerial(String macSerial) {
            this.macSerial = macSerial;
        }

        public String getMacZek() {
            return macZek;
        }

        public void setMacZek(String macZek) {
            this.macZek = macZek;
        }

        public String getMacNumber() {
            return macNumber;
        }

        public void setMacNumber(String macNumber) {
            this.macNumber = macNumber;
        }

        public int getMacState() {
            return macState;
        }

        public void setMacState(int macState) {
            this.macState = macState;
        }

        public String getMacZak() {
            return macZak;
        }

        public void setMacZak(String macZak) {
            this.macZak = macZak;
        }

        public String getMacBatch() {
            return macBatch;
        }

        public void setMacBatch(String macBatch) {
            this.macBatch = macBatch;
        }

        public int getMacId() {
            return macId;
        }

        public void setMacId(int macId) {
            this.macId = macId;
        }

        public String getAmNumber() {
            return amNumber;
        }

        public void setAmNumber(String amNumber) {
            this.amNumber = amNumber;
        }

        public String getMacZmk() {
            return macZmk;
        }

        public void setMacZmk(String macZmk) {
            this.macZmk = macZmk;
        }

        public String getMacSn() {
            return macSn;
        }

        public void setMacSn(String macSn) {
            this.macSn = macSn;
        }
    }
}
