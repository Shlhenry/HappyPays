package com.futeng.happypays.utils;

import java.util.List;

/**
 * Created by Administrator on 2017/4/6.
 */
public class CityBean {
    /**
     * code : 00
     * message : 成功
     * isOK : true
     * list : [{"cityId":330000,"cityName":"浙江省","cityStates":""},{"cityId":340000,"cityName":"安徽省","cityStates":""},{"cityId":350000,"cityName":"福建省","cityStates":""},{"cityId":150000,"cityName":"内蒙古自治区","cityStates":""},{"cityId":140000,"cityName":"山西省","cityStates":""},{"cityId":210000,"cityName":"辽宁省","cityStates":""},{"cityId":220000,"cityName":"吉林省","cityStates":""},{"cityId":230000,"cityName":"黑龙江省","cityStates":""},{"cityId":310000,"cityName":"上海","cityStates":""},{"cityId":320000,"cityName":"江苏省","cityStates":""},{"cityId":110000,"cityName":"北京","cityStates":""},{"cityId":120000,"cityName":"天津","cityStates":""},{"cityId":130000,"cityName":"河北省","cityStates":""},{"cityId":360000,"cityName":"江西省","cityStates":""},{"cityId":460000,"cityName":"海南省","cityStates":""},{"cityId":500000,"cityName":"重庆","cityStates":""},{"cityId":510000,"cityName":"四川省","cityStates":""},{"cityId":450000,"cityName":"广西壮族自治区","cityStates":""},{"cityId":520000,"cityName":"贵州省","cityStates":""},{"cityId":440000,"cityName":"广东省","cityStates":""},{"cityId":370000,"cityName":"山东省","cityStates":""},{"cityId":410000,"cityName":"河南省","cityStates":""},{"cityId":420000,"cityName":"湖北省","cityStates":""},{"cityId":430000,"cityName":"湖南省","cityStates":""},{"cityId":620000,"cityName":"甘肃省","cityStates":""},{"cityId":810000,"cityName":"香港特别行政区","cityStates":""},{"cityId":820000,"cityName":"澳门特别行政区","cityStates":""},{"cityId":900000,"cityName":"钓鱼岛","cityStates":""},{"cityId":530000,"cityName":"云南省","cityStates":""},{"cityId":540000,"cityName":"西藏自治区","cityStates":""},{"cityId":610000,"cityName":"陕西省","cityStates":""},{"cityId":710000,"cityName":"台湾","cityStates":""},{"cityId":630000,"cityName":"青海省","cityStates":""},{"cityId":640000,"cityName":"宁夏回族自治区","cityStates":""},{"cityId":650000,"cityName":"新疆维吾尔自治区","cityStates":""}]
     */

    private String code;
    private String message;
    private boolean isOK;
    /**
     * cityId : 330000
     * cityName : 浙江省
     * cityStates :
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
        private int cityId;
        private String cityName;
        private String cityStates;

        public int getCityId() {
            return cityId;
        }

        public void setCityId(int cityId) {
            this.cityId = cityId;
        }

        public String getCityName() {
            return cityName;
        }

        public void setCityName(String cityName) {
            this.cityName = cityName;
        }

        public String getCityStates() {
            return cityStates;
        }

        public void setCityStates(String cityStates) {
            this.cityStates = cityStates;
        }
    }
}
