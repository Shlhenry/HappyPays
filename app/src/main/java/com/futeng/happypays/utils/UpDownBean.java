package com.futeng.happypays.utils;

/**
 * Created by Administrator on 2017/5/2.
 */
public class UpDownBean {

    /**
     * code : 00
     * message : 成功
     * isOK : true
     * map : {"packageName":"com.futeng.happypays","urlPath":"http://120.27.138.219:8083/app/app-release.apk","versionNumber":"1.0.2"}
     */

    private String code;
    private String message;
    private boolean isOK;
    /**
     * packageName : com.futeng.happypays
     * urlPath : http://120.27.138.219:8083/app/app-release.apk
     * versionNumber : 1.0.2
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
        private String packageName;
        private String urlPath;
        private String versionNumber;
        public int getAvForce() {
            return avForce;
        }

        public void setAvForce(int avForce) {
            this.avForce = avForce;
        }

        private int avForce;

        public String getPackageName() {
            return packageName;
        }

        public void setPackageName(String packageName) {
            this.packageName = packageName;
        }

        public String getUrlPath() {
            return urlPath;
        }

        public void setUrlPath(String urlPath) {
            this.urlPath = urlPath;
        }

        public String getVersionNumber() {
            return versionNumber;
        }

        public void setVersionNumber(String versionNumber) {
            this.versionNumber = versionNumber;
        }
    }
}
