package com.futeng.happypays.utils;

import java.util.List;

/**
 * Created by Administrator on 2017/6/20.
 * 我推荐的人Bean
 */
public class CommendBean {

    /**
     * code : 00
     * message : 成功
     * successMessage : 操作成功
     * isOK : true
     * list : [{"amPersonPhone":"15195949721","gradeName":"伯","amName":"孟孟"}]
     */

    private String code;
    private String message;
    private String successMessage;
    private boolean isOK;
    /**
     * amPersonPhone : 15195949721
     * gradeName : 伯
     * amName : 孟孟
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
        private String amPersonPhone;
        private String gradeName;
        private String amName;

        public String getAmPersonPhone() {
            return amPersonPhone;
        }

        public void setAmPersonPhone(String amPersonPhone) {
            this.amPersonPhone = amPersonPhone;
        }

        public String getGradeName() {
            return gradeName;
        }

        public void setGradeName(String gradeName) {
            this.gradeName = gradeName;
        }

        public String getAmName() {
            return amName;
        }

        public void setAmName(String amName) {
            this.amName = amName;
        }
    }
}