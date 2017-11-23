package com.futeng.happypays.utils;

import java.util.List;

/**
 * Created by Administrator on 2017/11/17.
 */

public class LogisticListBean {

    /**
     * code : 10000
     * charge : false
     * msg : 查询成功
     * result : {"msg":"ok","result":[{"number":"30288063886","letter":"A","name":"Aramex","tel":"400-631-8388","type":"ARAMEX"},{"number":"30000014025846","letter":"A","name":"安能快递","tel":"","type":"ANEEX"},{"number":"AJ80862351","letter":"A","name":"安捷","tel":"400-619-7776","type":"ANJELEX"},{"number":"220014594841","letter":"A","name":"安能","tel":"40010-40088","type":"ANE"},{"number":"ZA02610043077","letter":"A","name":"澳邮","tel":"","type":"AUEXPRESS"},{"number":"131685763","letter":"A","name":"AAE","tel":"400-6100-400 021-51008888","type":"AAEWEB"},{"number":"","letter":"A","name":"安信达","tel":"021-54224681","type":"ANXINDA"},{"number":"","letter":"B","name":"百福东方","tel":"400-706-0609","type":"EES"},{"number":"BSD211640","letter":"B","name":"蓝天","tel":"0061-3-94950283","type":"BLUESKY"},{"number":"1002868661","letter":"B","name":"黑狗","tel":"400-106-1234","type":"BLACKDOG"},{"number":"10288328982","letter":"B","name":"百世快运","tel":"","type":"BSKY"},{"number":"6071706513739","letter":"C","name":"秦远物流","tel":"","type":"CHINZ56"},{"number":"100001362043","letter":"C","name":"程光","tel":"0064-09-2759536","type":"FLYWAYEX"},{"number":"5180435727","letter":"D","name":"德邦","tel":"95353","type":"DEPPON"},{"number":"","letter":"D","name":"DPEX","tel":"0755-88297707","type":"DPEX"},{"number":"616906328376","letter":"D","name":"D速","tel":"0531-88636363","type":"DEXP"},{"number":"5846399171","letter":"D","name":"DHL","tel":"800-810-8000 400-810-8000","type":"DHL"},{"number":"","letter":"D","name":"大田","tel":"400-626-1166","type":"DTW"},{"number":"2994861011569","letter":"D","name":"东骏物流","tel":"","type":"DJ56"},{"number":"9572253781500","letter":"E","name":"EMS","tel":"40080-11183","type":"EMS"},{"number":"EF990157553NZ","letter":"E","name":"平安快递","tel":"0773-2315320","type":"EFSPOST"},{"number":"mhu00061","letter":"E","name":"EWE","tel":"1300-09-6655","type":"EWE"},{"number":"808446554948","letter":"F","name":"FedEx国际","tel":"800-988-1888 400-886-1888","type":"FEDEXIN"},{"number":"NZ1200523","letter":"F","name":"富腾达","tel":"0064-09-4432342","type":"FTD"},{"number":"120949498648","letter":"F","name":"FedEx","tel":"800-988-1888 400-886-1888","type":"FEDEX"},{"number":"","letter":"F","name":"凤凰","tel":"010-85826200","type":"PHOENIXEXP"},{"number":"2840737926","letter":"G","name":"国通","tel":"4001-111-123","type":"GTO"},{"number":"","letter":"G","name":"能达","tel":"400-6886-765","type":"ND56"},{"number":"","letter":"G","name":"共速达","tel":"400-111-0005","type":"GSD"},{"number":"GC501115760US","letter":"G","name":"飞洋","tel":"010-87785733","type":"GCE"},{"number":"350630538314","letter":"H","name":"百世快递","tel":"4009565656","type":"HTKY"},{"number":"LB603036351US","letter":"H","name":"鸿远","tel":"","type":"HYE"},{"number":"612007013934946","letter":"H","name":"黄马甲","tel":"","type":"HUANGMAJIA"},{"number":"63760347","letter":"H","name":"锦程快递","tel":"","type":"HREX"},{"number":"03122576","letter":"H","name":"恒路","tel":"400-182-6666","type":"HENGLU"},{"number":"","letter":"H","name":"华企","tel":"400-626-2356","type":"HQKY"},{"number":"316B455817673","letter":"J","name":"嘉里物流","tel":"852-2410-3600","type":"KERRY"},{"number":"729976312","letter":"J","name":"佳吉","tel":"400-820-5566","type":"JIAJI"},{"number":"JY0001964522","letter":"J","name":"九曳","tel":"400-6199-939","type":"JIUYESCM"},{"number":"12290972964","letter":"J","name":"京东","tel":"","type":"JD"},{"number":"7102293245","letter":"J","name":"京广","tel":"852-23329918","type":"KKE"},{"number":"","letter":"J","name":"佳怡","tel":"400-631-9999","type":"JIAYI"},{"number":"2197050107","letter":"J","name":"加运美","tel":"0769-85515555 ","type":"TMS"},{"number":"","letter":"J","name":"急先达","tel":"400-694-1256","type":"JOUST"},{"number":"","letter":"J","name":"晋越","tel":"台北：+886-2-25016988澳门：00853-28520722福建：0592-5569715广东：0769-88763939","type":"PEWKEE"},{"number":"2628904","letter":"K","name":"跨越","tel":"4008-098-098","type":"KYEXPRESS"},{"number":"KF400023AU","letter":"K","name":"货运皇","tel":"","type":"KINGFREIGHT"},{"number":"536135784093","letter":"K","name":"快捷","tel":"4008-333-666","type":"FASTEXPRESS"},{"number":"686013186447","letter":"L","name":"龙邦","tel":"021-59218889","type":"LBEX"},{"number":"236012014633","letter":"L","name":"联昊通","tel":"400-888-8887","type":"LTS"},{"number":"CAE602232295","letter":"M","name":"民航","tel":"4008-17-4008","type":"CAE"},{"number":"","letter":"P","name":"配思航宇","tel":"010-65489928","type":"PEISI"},{"number":"EAU839001631","letter":"P","name":"PCA","tel":"1800518000","type":"PCA"},{"number":"720166045326","letter":"Q","name":"全峰","tel":"4001-000-001","type":"QFKD"},{"number":"112393742650","letter":"Q","name":"全一","tel":"400-663-1111","type":"APEX"},{"number":"2233244233","letter":"Q","name":"全晨","tel":"0769-82026703","type":"QCKD"},{"number":"6800000635515","letter":"R","name":"如风达","tel":"400-010-6660","type":"RFD"},{"number":"952727764582","letter":"S","name":"顺丰","tel":"95338","type":"SFEXPRESS"},{"number":"403234843091","letter":"S","name":"申通","tel":"95543","type":"STO"},{"number":"SN0030000041011500","letter":"S","name":"苏宁","tel":"95315","type":"SUNING"},{"number":"SD100330011","letter":"S","name":"顺达快递","tel":"","type":"SDEX"},{"number":"","letter":"S","name":"三态","tel":"400-881-8106","type":"SFC"},{"number":"238601965","letter":"S","name":"盛辉","tel":"400-822-2222","type":"SHENGHUI"},{"number":"39562381","letter":"S","name":"盛丰","tel":"4008-556688","type":"SFWL"},{"number":"880218258595","letter":"S","name":"速尔","tel":"400-158-9888","type":"SURE"},{"number":"560623753489","letter":"T","name":"天天","tel":"4001-888-888","type":"TTKDEX"},{"number":"335939905","letter":"T","name":"TNT","tel":"800-820-9868","type":"TNT"},{"number":"020286402","letter":"T","name":"天地华宇","tel":"400-808-6666","type":"HOAU"},{"number":"1ZV6509Y0468336755","letter":"U","name":"UPS","tel":"800-820-8388 400-820-8388","type":"UPS"},{"number":"000000092332","letter":"V","name":"万庚","tel":"","type":"VANGEN"},{"number":"","letter":"W","name":"文捷航空","tel":"020-36680069","type":"GZWENJIE"},{"number":"2225195562855","letter":"W","name":"万象","tel":"400-820-8088","type":"EWINSHINE"},{"number":"31000001425628","letter":"W","name":"万家物流","tel":"4001-156-561","type":"WANJIA"},{"number":"137417768454","letter":"X","name":"信丰","tel":"4008-306-333","type":"XFEXPRESS"},{"number":"27454784","letter":"X","name":"新邦","tel":"4008-000-222","type":"XBWL"},{"number":"100668657244","letter":"Y","name":"圆通","tel":"021-69777888 021-69777999","type":"YTO"},{"number":"1202237859178","letter":"Y","name":"韵达","tel":"95546","type":"YUNDA"},{"number":"9610027635439","letter":"Y","name":"邮政包裹","tel":"11185","type":"CHINAPOST"},{"number":"666316719","letter":"Y","name":"运通","tel":"0769-81156999","type":"YTEXPRESS"},{"number":"","letter":"Y","name":"越丰","tel":"(852) 2390 9969 ","type":"YFEXPRESS"},{"number":"300011079526","letter":"Y","name":"远成","tel":"400-820-1646","type":"YCGWL"},{"number":"50042647157","letter":"Y","name":"亚风","tel":"4001-000-002","type":"BROADASIA"},{"number":"518148752202","letter":"Y","name":"优速","tel":"400-1111-119","type":"UC56"},{"number":"8090337382","letter":"Y","name":"原飞航","tel":"0755-29778899 / 29778100","type":"YFHEX"},{"number":"","letter":"Y","name":"源安达","tel":"0769-85021875","type":"YADEX"},{"number":"0125194699","letter":"Y","name":"宜送","tel":"","type":"YIEXPRESS"},{"number":"8000199455","letter":"Y","name":"易通达","tel":"0898-65339299","type":"ETD"},{"number":"ZY030841509NZ","letter":"Y","name":"易达通","tel":"0064-09-8388681","type":"QEXPRESS"},{"number":"421447644512","letter":"Z","name":"中通","tel":"95311","type":"ZTO"},{"number":"A002083939830","letter":"Z","name":"宅急送","tel":"400-6789-000","type":"ZJS"},{"number":"201605182527","letter":"Z","name":"中通快运","tel":"4000-270-270","type":"ZTO56"},{"number":"119005886864","letter":"Z","name":"中铁物流","tel":"400-000-5566","type":"ZTKY"},{"number":"611008025181750","letter":"Z","name":"芝麻开门","tel":"4001-056-056","type":"ZMKMEX"},{"number":"","letter":"Z","name":"中国东方","tel":"755-83575000","type":"COE"},{"number":"NE88379114242","letter":"Z","name":"中邮","tel":"11183","type":"CNPL"},{"number":"k19110633973","letter":"Z","name":"中铁快运","tel":"95572","type":"CRE"}],"status":"0"}
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
         * result : [{"number":"30288063886","letter":"A","name":"Aramex","tel":"400-631-8388","type":"ARAMEX"},{"number":"30000014025846","letter":"A","name":"安能快递","tel":"","type":"ANEEX"},{"number":"AJ80862351","letter":"A","name":"安捷","tel":"400-619-7776","type":"ANJELEX"},{"number":"220014594841","letter":"A","name":"安能","tel":"40010-40088","type":"ANE"},{"number":"ZA02610043077","letter":"A","name":"澳邮","tel":"","type":"AUEXPRESS"},{"number":"131685763","letter":"A","name":"AAE","tel":"400-6100-400 021-51008888","type":"AAEWEB"},{"number":"","letter":"A","name":"安信达","tel":"021-54224681","type":"ANXINDA"},{"number":"","letter":"B","name":"百福东方","tel":"400-706-0609","type":"EES"},{"number":"BSD211640","letter":"B","name":"蓝天","tel":"0061-3-94950283","type":"BLUESKY"},{"number":"1002868661","letter":"B","name":"黑狗","tel":"400-106-1234","type":"BLACKDOG"},{"number":"10288328982","letter":"B","name":"百世快运","tel":"","type":"BSKY"},{"number":"6071706513739","letter":"C","name":"秦远物流","tel":"","type":"CHINZ56"},{"number":"100001362043","letter":"C","name":"程光","tel":"0064-09-2759536","type":"FLYWAYEX"},{"number":"5180435727","letter":"D","name":"德邦","tel":"95353","type":"DEPPON"},{"number":"","letter":"D","name":"DPEX","tel":"0755-88297707","type":"DPEX"},{"number":"616906328376","letter":"D","name":"D速","tel":"0531-88636363","type":"DEXP"},{"number":"5846399171","letter":"D","name":"DHL","tel":"800-810-8000 400-810-8000","type":"DHL"},{"number":"","letter":"D","name":"大田","tel":"400-626-1166","type":"DTW"},{"number":"2994861011569","letter":"D","name":"东骏物流","tel":"","type":"DJ56"},{"number":"9572253781500","letter":"E","name":"EMS","tel":"40080-11183","type":"EMS"},{"number":"EF990157553NZ","letter":"E","name":"平安快递","tel":"0773-2315320","type":"EFSPOST"},{"number":"mhu00061","letter":"E","name":"EWE","tel":"1300-09-6655","type":"EWE"},{"number":"808446554948","letter":"F","name":"FedEx国际","tel":"800-988-1888 400-886-1888","type":"FEDEXIN"},{"number":"NZ1200523","letter":"F","name":"富腾达","tel":"0064-09-4432342","type":"FTD"},{"number":"120949498648","letter":"F","name":"FedEx","tel":"800-988-1888 400-886-1888","type":"FEDEX"},{"number":"","letter":"F","name":"凤凰","tel":"010-85826200","type":"PHOENIXEXP"},{"number":"2840737926","letter":"G","name":"国通","tel":"4001-111-123","type":"GTO"},{"number":"","letter":"G","name":"能达","tel":"400-6886-765","type":"ND56"},{"number":"","letter":"G","name":"共速达","tel":"400-111-0005","type":"GSD"},{"number":"GC501115760US","letter":"G","name":"飞洋","tel":"010-87785733","type":"GCE"},{"number":"350630538314","letter":"H","name":"百世快递","tel":"4009565656","type":"HTKY"},{"number":"LB603036351US","letter":"H","name":"鸿远","tel":"","type":"HYE"},{"number":"612007013934946","letter":"H","name":"黄马甲","tel":"","type":"HUANGMAJIA"},{"number":"63760347","letter":"H","name":"锦程快递","tel":"","type":"HREX"},{"number":"03122576","letter":"H","name":"恒路","tel":"400-182-6666","type":"HENGLU"},{"number":"","letter":"H","name":"华企","tel":"400-626-2356","type":"HQKY"},{"number":"316B455817673","letter":"J","name":"嘉里物流","tel":"852-2410-3600","type":"KERRY"},{"number":"729976312","letter":"J","name":"佳吉","tel":"400-820-5566","type":"JIAJI"},{"number":"JY0001964522","letter":"J","name":"九曳","tel":"400-6199-939","type":"JIUYESCM"},{"number":"12290972964","letter":"J","name":"京东","tel":"","type":"JD"},{"number":"7102293245","letter":"J","name":"京广","tel":"852-23329918","type":"KKE"},{"number":"","letter":"J","name":"佳怡","tel":"400-631-9999","type":"JIAYI"},{"number":"2197050107","letter":"J","name":"加运美","tel":"0769-85515555 ","type":"TMS"},{"number":"","letter":"J","name":"急先达","tel":"400-694-1256","type":"JOUST"},{"number":"","letter":"J","name":"晋越","tel":"台北：+886-2-25016988澳门：00853-28520722福建：0592-5569715广东：0769-88763939","type":"PEWKEE"},{"number":"2628904","letter":"K","name":"跨越","tel":"4008-098-098","type":"KYEXPRESS"},{"number":"KF400023AU","letter":"K","name":"货运皇","tel":"","type":"KINGFREIGHT"},{"number":"536135784093","letter":"K","name":"快捷","tel":"4008-333-666","type":"FASTEXPRESS"},{"number":"686013186447","letter":"L","name":"龙邦","tel":"021-59218889","type":"LBEX"},{"number":"236012014633","letter":"L","name":"联昊通","tel":"400-888-8887","type":"LTS"},{"number":"CAE602232295","letter":"M","name":"民航","tel":"4008-17-4008","type":"CAE"},{"number":"","letter":"P","name":"配思航宇","tel":"010-65489928","type":"PEISI"},{"number":"EAU839001631","letter":"P","name":"PCA","tel":"1800518000","type":"PCA"},{"number":"720166045326","letter":"Q","name":"全峰","tel":"4001-000-001","type":"QFKD"},{"number":"112393742650","letter":"Q","name":"全一","tel":"400-663-1111","type":"APEX"},{"number":"2233244233","letter":"Q","name":"全晨","tel":"0769-82026703","type":"QCKD"},{"number":"6800000635515","letter":"R","name":"如风达","tel":"400-010-6660","type":"RFD"},{"number":"952727764582","letter":"S","name":"顺丰","tel":"95338","type":"SFEXPRESS"},{"number":"403234843091","letter":"S","name":"申通","tel":"95543","type":"STO"},{"number":"SN0030000041011500","letter":"S","name":"苏宁","tel":"95315","type":"SUNING"},{"number":"SD100330011","letter":"S","name":"顺达快递","tel":"","type":"SDEX"},{"number":"","letter":"S","name":"三态","tel":"400-881-8106","type":"SFC"},{"number":"238601965","letter":"S","name":"盛辉","tel":"400-822-2222","type":"SHENGHUI"},{"number":"39562381","letter":"S","name":"盛丰","tel":"4008-556688","type":"SFWL"},{"number":"880218258595","letter":"S","name":"速尔","tel":"400-158-9888","type":"SURE"},{"number":"560623753489","letter":"T","name":"天天","tel":"4001-888-888","type":"TTKDEX"},{"number":"335939905","letter":"T","name":"TNT","tel":"800-820-9868","type":"TNT"},{"number":"020286402","letter":"T","name":"天地华宇","tel":"400-808-6666","type":"HOAU"},{"number":"1ZV6509Y0468336755","letter":"U","name":"UPS","tel":"800-820-8388 400-820-8388","type":"UPS"},{"number":"000000092332","letter":"V","name":"万庚","tel":"","type":"VANGEN"},{"number":"","letter":"W","name":"文捷航空","tel":"020-36680069","type":"GZWENJIE"},{"number":"2225195562855","letter":"W","name":"万象","tel":"400-820-8088","type":"EWINSHINE"},{"number":"31000001425628","letter":"W","name":"万家物流","tel":"4001-156-561","type":"WANJIA"},{"number":"137417768454","letter":"X","name":"信丰","tel":"4008-306-333","type":"XFEXPRESS"},{"number":"27454784","letter":"X","name":"新邦","tel":"4008-000-222","type":"XBWL"},{"number":"100668657244","letter":"Y","name":"圆通","tel":"021-69777888 021-69777999","type":"YTO"},{"number":"1202237859178","letter":"Y","name":"韵达","tel":"95546","type":"YUNDA"},{"number":"9610027635439","letter":"Y","name":"邮政包裹","tel":"11185","type":"CHINAPOST"},{"number":"666316719","letter":"Y","name":"运通","tel":"0769-81156999","type":"YTEXPRESS"},{"number":"","letter":"Y","name":"越丰","tel":"(852) 2390 9969 ","type":"YFEXPRESS"},{"number":"300011079526","letter":"Y","name":"远成","tel":"400-820-1646","type":"YCGWL"},{"number":"50042647157","letter":"Y","name":"亚风","tel":"4001-000-002","type":"BROADASIA"},{"number":"518148752202","letter":"Y","name":"优速","tel":"400-1111-119","type":"UC56"},{"number":"8090337382","letter":"Y","name":"原飞航","tel":"0755-29778899 / 29778100","type":"YFHEX"},{"number":"","letter":"Y","name":"源安达","tel":"0769-85021875","type":"YADEX"},{"number":"0125194699","letter":"Y","name":"宜送","tel":"","type":"YIEXPRESS"},{"number":"8000199455","letter":"Y","name":"易通达","tel":"0898-65339299","type":"ETD"},{"number":"ZY030841509NZ","letter":"Y","name":"易达通","tel":"0064-09-8388681","type":"QEXPRESS"},{"number":"421447644512","letter":"Z","name":"中通","tel":"95311","type":"ZTO"},{"number":"A002083939830","letter":"Z","name":"宅急送","tel":"400-6789-000","type":"ZJS"},{"number":"201605182527","letter":"Z","name":"中通快运","tel":"4000-270-270","type":"ZTO56"},{"number":"119005886864","letter":"Z","name":"中铁物流","tel":"400-000-5566","type":"ZTKY"},{"number":"611008025181750","letter":"Z","name":"芝麻开门","tel":"4001-056-056","type":"ZMKMEX"},{"number":"","letter":"Z","name":"中国东方","tel":"755-83575000","type":"COE"},{"number":"NE88379114242","letter":"Z","name":"中邮","tel":"11183","type":"CNPL"},{"number":"k19110633973","letter":"Z","name":"中铁快运","tel":"95572","type":"CRE"}]
         * status : 0
         */

        private String msg;
        private String status;
        private List<ResultBean> result;

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public List<ResultBean> getResult() {
            return result;
        }

        public void setResult(List<ResultBean> result) {
            this.result = result;
        }

        public static class ResultBean {
            /**
             * number : 30288063886
             * letter : A
             * name : Aramex
             * tel : 400-631-8388
             * type : ARAMEX
             */

            private String number;
            private String letter;
            private String name;
            private String tel;
            private String type;

            public String getNumber() {
                return number;
            }

            public void setNumber(String number) {
                this.number = number;
            }

            public String getLetter() {
                return letter;
            }

            public void setLetter(String letter) {
                this.letter = letter;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getTel() {
                return tel;
            }

            public void setTel(String tel) {
                this.tel = tel;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }
        }
    }
}
