package com.futeng.happypays.utils;

import java.util.List;

/**
 * Created by Administrator on 2017/11/16.
 */

public class LogicticBean {

    /**
     * code : 10000
     * charge : false
     * msg : 查询成功
     * result : {"msg":"ok","result":{"issign":"1","number":"71230274219266","deliverystatus":"3","type":"htky","list":[{"time":"2017-10-23 11:01:20","status":"[南京市] 南京市【BEX南京江宁区一部】，公司前台，如有疑问及寄件请与我联系17502505200 已签收                        "},{"time":"2017-10-21 17:04:16","status":"[南京市] 南京市【BEX南京江宁区一部】，【余站东/17502505200】正在派件                        "},{"time":"2017-10-21 17:04:16","status":"[南京市] 到南京市【BEX南京江宁区一部】                        "},{"time":"2017-10-21 12:52:29","status":"[南京市] 到南京市【BEX南京江宁区一部】                        "},{"time":"2017-10-21 7:59:30","status":"[南京市] 南京市【南京转运中心】，正发往【BEX南京江宁区一部】                        "},{"time":"2017-10-21 7:09:22","status":"[南京市] 到南京市【南京转运中心】                        "},{"time":"2017-10-20 23:16:37","status":"[金华市] 金华市【义乌转运中心】，正发往【南京转运中心】                        "},{"time":"2017-10-20 22:43:09","status":"[金华市] 到金华市【义乌转运中心】                        "},{"time":"2017-10-20 19:27:21","status":"[金华市] 到金华市【义乌荷叶塘分部集货点】                        "},{"time":"2017-10-20 18:40:13","status":"[金华市] 金华市【义乌荷叶塘分部】，【程舒/17705793186】已揽收                        "}]},"status":"0"}
     */

    private String code;
    private boolean charge;
    private String msg;
    private ResultBeanX result;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean isCharge() {
        return charge;
    }

    public void setCharge(boolean charge) {
        this.charge = charge;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ResultBeanX getResult() {
        return result;
    }

    public void setResult(ResultBeanX result) {
        this.result = result;
    }

    public static class ResultBeanX {
        /**
         * msg : ok
         * result : {"issign":"1","number":"71230274219266","deliverystatus":"3","type":"htky","list":[{"time":"2017-10-23 11:01:20","status":"[南京市] 南京市【BEX南京江宁区一部】，公司前台，如有疑问及寄件请与我联系17502505200 已签收                        "},{"time":"2017-10-21 17:04:16","status":"[南京市] 南京市【BEX南京江宁区一部】，【余站东/17502505200】正在派件                        "},{"time":"2017-10-21 17:04:16","status":"[南京市] 到南京市【BEX南京江宁区一部】                        "},{"time":"2017-10-21 12:52:29","status":"[南京市] 到南京市【BEX南京江宁区一部】                        "},{"time":"2017-10-21 7:59:30","status":"[南京市] 南京市【南京转运中心】，正发往【BEX南京江宁区一部】                        "},{"time":"2017-10-21 7:09:22","status":"[南京市] 到南京市【南京转运中心】                        "},{"time":"2017-10-20 23:16:37","status":"[金华市] 金华市【义乌转运中心】，正发往【南京转运中心】                        "},{"time":"2017-10-20 22:43:09","status":"[金华市] 到金华市【义乌转运中心】                        "},{"time":"2017-10-20 19:27:21","status":"[金华市] 到金华市【义乌荷叶塘分部集货点】                        "},{"time":"2017-10-20 18:40:13","status":"[金华市] 金华市【义乌荷叶塘分部】，【程舒/17705793186】已揽收                        "}]}
         * status : 0
         */

        private String msg;
        private ResultBean result;
        private String status;

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public ResultBean getResult() {
            return result;
        }

        public void setResult(ResultBean result) {
            this.result = result;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public static class ResultBean {
            /**
             * issign : 1
             * number : 71230274219266
             * deliverystatus : 3
             * type : htky
             * list : [{"time":"2017-10-23 11:01:20","status":"[南京市] 南京市【BEX南京江宁区一部】，公司前台，如有疑问及寄件请与我联系17502505200 已签收                        "},{"time":"2017-10-21 17:04:16","status":"[南京市] 南京市【BEX南京江宁区一部】，【余站东/17502505200】正在派件                        "},{"time":"2017-10-21 17:04:16","status":"[南京市] 到南京市【BEX南京江宁区一部】                        "},{"time":"2017-10-21 12:52:29","status":"[南京市] 到南京市【BEX南京江宁区一部】                        "},{"time":"2017-10-21 7:59:30","status":"[南京市] 南京市【南京转运中心】，正发往【BEX南京江宁区一部】                        "},{"time":"2017-10-21 7:09:22","status":"[南京市] 到南京市【南京转运中心】                        "},{"time":"2017-10-20 23:16:37","status":"[金华市] 金华市【义乌转运中心】，正发往【南京转运中心】                        "},{"time":"2017-10-20 22:43:09","status":"[金华市] 到金华市【义乌转运中心】                        "},{"time":"2017-10-20 19:27:21","status":"[金华市] 到金华市【义乌荷叶塘分部集货点】                        "},{"time":"2017-10-20 18:40:13","status":"[金华市] 金华市【义乌荷叶塘分部】，【程舒/17705793186】已揽收                        "}]
             */

            private String issign;
            private String number;
            private String deliverystatus;
            private String type;
            private List<ListBean> list;

            public String getIssign() {
                return issign;
            }

            public void setIssign(String issign) {
                this.issign = issign;
            }

            public String getNumber() {
                return number;
            }

            public void setNumber(String number) {
                this.number = number;
            }

            public String getDeliverystatus() {
                return deliverystatus;
            }

            public void setDeliverystatus(String deliverystatus) {
                this.deliverystatus = deliverystatus;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public List<ListBean> getList() {
                return list;
            }

            public void setList(List<ListBean> list) {
                this.list = list;
            }

            public static class ListBean {
                /**
                 * time : 2017-10-23 11:01:20
                 * status : [南京市] 南京市【BEX南京江宁区一部】，公司前台，如有疑问及寄件请与我联系17502505200 已签收
                 */

                private String time;
                private String status;

                public String getTime() {
                    return time;
                }

                public void setTime(String time) {
                    this.time = time;
                }

                public String getStatus() {
                    return status;
                }

                public void setStatus(String status) {
                    this.status = status;
                }
            }
        }
    }
}
