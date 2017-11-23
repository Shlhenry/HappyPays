package com.futeng.happypays.utils;

import java.util.List;

/**
 * Created by Administrator on 2017/5/16.
 */
public class SnBean {
    /**
     * code : 00
     * message : 成功
     * isOK : true
     * list : [{"macZpk":"2C1123BEFBA8B55C551CE337083F8F25","macSerial":"000013","macZek":"CA83A9A1EB0ACAA0BEC722FBE4A7DBA4","macNumber":"00000102","amId":105,"macState":1,"macZak":"5F2D580CAAC4CF3FDEF9FCDF800BFCAB","macBatch":"000001","macId":102,"amNumber":"883000000000105","macZmk":"FD9D0C024B6D001545E5AFB90BDE72CE","macSn":"JHLM603160100066"},{"macZpk":"E1AE9FDCAFAEDCCEC8DBDF99BCDC8E80","macSerial":"000001","macZek":"8EDCA06B6AAFFB4DBFBBCDE46CC8BB8F","macNumber":"00000122","amId":105,"macState":1,"macZak":"B2847E11CDE74F3AAE659BBAE2CD0DAC","macBatch":"000001","macId":122,"amNumber":"883000000000105","macZmk":"B75E74E5BA2AA26EEF0AE7BCA5FFAFD0","macSn":"JHLA802170204767"},{"macZpk":"2BCC5BCEBE4DDA9C5DBDF0D7CF991D35","macSerial":"000001","macZek":"EED6268E34FDFE1FE3C8C0E70CD61C4F","macNumber":"00000123","amId":105,"macState":1,"macZak":"FADEECCDFCAE22C00CA8C48CC55E3D13","macBatch":"000001","macId":123,"amNumber":"883000000000105","macZmk":"2BB8EF0DC3CEE4282EFFAB47EFD1AFDD","macSn":"JHLA802170204766"}]
     */

    private String code;
    private String message;
    private boolean isOK;
    /**
     * macZpk : 2C1123BEFBA8B55C551CE337083F8F25
     * macSerial : 000013
     * macZek : CA83A9A1EB0ACAA0BEC722FBE4A7DBA4
     * macNumber : 00000102
     * amId : 105
     * macState : 1
     * macZak : 5F2D580CAAC4CF3FDEF9FCDF800BFCAB
     * macBatch : 000001
     * macId : 102
     * amNumber : 883000000000105
     * macZmk : FD9D0C024B6D001545E5AFB90BDE72CE
     * macSn : JHLM603160100066
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
        private String macZpk;
        private String macSerial;
        private String macZek;
        private String macNumber;
        private int amId;
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

        public int getAmId() {
            return amId;
        }

        public void setAmId(int amId) {
            this.amId = amId;
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
