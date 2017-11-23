package com.futeng.happypays.utils;

/**
 * Created by Administrator on 2017/8/21.
 */
public class GettokenBean {
    /**
     * resultCode : true
     * message : 正常交互
     * htmlContent : <html><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/></head><body><form id = "pay_form" action="https://gateway.chinaepay.com/trans/frontTransTokenURL" method="post"><input type="hidden" name="bizType" id="bizType" value="000902"/><input type="hidden" name="tokenPayData" id="tokenPayData" value="{trId=170703102736016&tokenType=01}"/><input type="hidden" name="txnSubType" id="txnSubType" value="00"/><input type="hidden" name="orderId" id="orderId" value="20170821100026712"/><input type="hidden" name="backUrl" id="backUrl" value="http://120.132.39.35/qpay/receiveOpenCardBack.app"/><input type="hidden" name="signature" id="signature" value="pZrMCaxXPTXbK9kAU83f/54htl7SX8EIGE4XKTjK6QgqmX52Xr7TSsRoiA9VLI/VTwTe4OWdXqspBVbcDoPhfMqP6ZWZB7CebHY3vH3CFdt/wRyro/SSxsgCjBc20zmeGsBrhdmggOqjQP0T6r5DK2y4SZDitaXyKQNO/DaxOAU="/><input type="hidden" name="accNo" id="accNo" value="PHz79s96eprRm3CsQZQHKXoqsR6v7UrUf/VikvK4UGltVqi5rIA5rI9Ot1DoNZ2DW3nB3y2q1sCE/h0UTwxR6/Ba9yy9bOBTHDtfUfQ/i1c7IvhNQL6Ec9wxBAZCNQnIZa8vevqZldJciXoWRIFr45KuK4wkQ4TmeSLNDkMqU6U="/><input type="hidden" name="txnType" id="txnType" value="79"/><input type="hidden" name="channelType" id="channelType" value="07"/><input type="hidden" name="certId" id="certId" value="74698424416884260086015896489323850379"/><input type="hidden" name="encoding" id="encoding" value="UTF-8"/><input type="hidden" name="version" id="version" value="5.1.0"/><input type="hidden" name="accessType" id="accessType" value="0"/><input type="hidden" name="txnTime" id="txnTime" value="20170821100026"/><input type="hidden" name="merId" id="merId" value="170703102736016"/><input type="hidden" name="accType" id="accType" value="01"/><input type="hidden" name="signMethod" id="signMethod" value="01"/></form></body><script type="text/javascript">document.all.pay_form.submit();</script></html>
     */

    private String resultCode;
    private String message;
    private String htmlContent;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    private String orderId;

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getHtmlContent() {
        return htmlContent;
    }

    public void setHtmlContent(String htmlContent) {
        this.htmlContent = htmlContent;
    }
}
