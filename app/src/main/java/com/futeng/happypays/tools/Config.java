package com.futeng.happypays.tools;

/**
 * Created by Administrator on 2017/3/8.
 * 接口存放
 */
public class Config {

    //public static String URL = "http://101.37.168.236:8081/PhonePOSPInterface/";      // 测试地址
    public static String URL = "http://120.27.194.146:8081/PhonePOSPInterface/";      // 正式地址
    public static String PayResultServlet_URL=URL+"PayResultServlet";//银联地址
    public static String Login__URL = URL + "LoginServlet";//登入
    public static String Regist_URL = URL + "RegisterServlet";//注册phone手机号,pwd密码,repwd重复密码,code验证码,operator验证码类型（1：注册，2：重置密码））
    public static String UpPw_URL = URL + "ModifyPhonePwdServlet";//修改密码
    public static String Version_URL = URL + "GetAppVersionServlet";//版本 APP版本（packageName包名）
    public static String AllBank_URL = URL + "BankServlet";//总行
    public static String City_URL = URL + "CityServlet";//城市
    public static String GetAuthenServlet_URL=URL+"GetAuthenServlet";//获取身份证认证资料
    public static String Authen_URL = URL + "AuthenServlet";//认证
    public static String Code_URL = URL + "CodeServelt";//验证码（phone手机号，operator验证码类型（1：注册，2：重置密码） ）
    public static String TicketServlet_URL = URL + "TicketServlet";//电子小票
    public static String ReceivablesWaterServlet_URL=URL+"ReceivablesWaterServlet";//流水交易
    //public static String QrPayServlet_URL=URL+"QrPayServlet";//微信支付宝二维码付款
    public static String QrPayServlet_URL=URL+"placeAnOrder";//微信支付宝二维码付款
    public static String SubPayResultServlet_URL=URL +"query";//微信支付宝二维码查询
    public static String POSP_IP = "120.27.194.146";//Socket地址
    public static String POSP_PORT = "13079";//9539  Socket端口
    public static String AMBandCreditServlet_URL=URL+"AMBandCreditServlet";//绑定和获取绑定信用卡（amNumber：商户号，mode：1：绑定信用卡 2：获取绑定信用卡信息）
    public static String BindServlet__URL = URL + "BindServlet";//消费之前获取商户绑定的SN号,商户号，macSn：不传，传个空就OK<消费传macSn>
    public static String AMAccountServlet_URL=URL+"AMAccountServlet";//金额。可用金额，积分显示
    public static String IntegralWithdrawalsServlet_URL=URL+"IntegralWithdrawalsServlet";//积分提现（amNumber：商户号，mode：1：提现 2：获取提现规则 ）
    public static String TeamServlet_URL=URL+"TeamServlet";//我的团队接口 mode：1 我 2 我的推荐人 3 我推荐的人
    public static String ModifyBankServlet_URL=URL+"ModifyBankServlet";//修改结算信息
    public static String AMDetailsServlet_URL=URL+"AMDetailsServlet";//基本信息   参数amNumber：商户号
    public static String SwitchServlet_URL=URL+"SwitchServlet";//控制开关：SwitchServlet：参数：mode；1  switchId：开关IDswitchValue：功能开关值switchName：功能名称switchParam：功能参数名
    public static String LoginStateServlet_URL=URL+"LoginStateServlet";//phone  判断状态接口
    public static String ExplainServlet_URL=URL+"ExplainServlet";//ExplainServlet不传参数
    public static String PayResultUrlServlet_URL=URL+"PayResultUrlServlet";//查询总的接口
    public static String ExchangerateServlet_URL=URL+"ExchangerateServlet";//汇率接口
    public static String WalletServlet_URL=URL+"WalletServlet";//钱包接口
    public static String ProfitServlet_URL=URL+"ProfitServlet";//收益接口
    public static String AppSignServlet_URL=URL+"AppSignServlet";//签到接口
    public static String RedPackageServlet_URL=URL+"RedPackageServlet";//红包接口
    public static String SmCommodityServlet_URL=URL+"SmCommodityServlet";//商城接口
    public static String SmReceiptinfoServlet_URL=URL+"SmReceiptinfoServlet";//收货地址
    public static String SmPlaceanOrderServlet_URL=URL+"SmPlaceanOrderServlet";//兑换接口



    //线上无卡接口
    public static String NoCardURL = "http://101.37.168.236:8081/TransConsumer/";   // 线上无卡测试地址
    //public static String NoCardURL = "http://120.27.194.146:8081/TransConsumer/";   // 线上无卡正式地址
    //------D0-----
    //---------判断 是否需要开卡---------------------
    /**
     * 参数:
     accountNumber   消费卡号
     返回:
     resultCode:"0000":说明已经开卡过        "true":表示需要进行开卡操作       其他返回
     message:返回描述
     htmlContent:当resultCode="true"时有值
     */
    public static String getToken_NoCardURL=NoCardURL+"getToken";//accountNumber   消费卡号返回:resultCode:"0000":说明已经开卡过        "true":表示需要进行开卡操作       其他返回message:返回描述htmlContent:当resultCode="true"时有值
    //-------------获取短信验证码--------------------
    /*参数:
    *accountNumber 	-- 消费卡号
    *txnAmt 			-- 交易金额
    *identityCard 	-- 身份证号
    *phoneNo 		-- 手机号
    *mcc             -- mcc
    *mercNo          -- 商户号
    * 返回:
		resultCode:"0000":说明发送成功
		message:返回描述
		orderId:当resultCode="0000"时有值,此订单号在消费时，需要传输；*/
    public static String getSmsCode_NoCardURL=NoCardURL+"getSmsCode";

    //消费
    /*
    * 参数:
    orderId        --订单号      获取短信验证码时已返回
    smsCode  	   --短信验证码
    返回:
    resultCode:"0000":交易受理成功
    message:返回描述
    orderId:当resultCode="0000"时有值;*/
    public static String bcconServlet_NoCardURL=NoCardURL+"bcconServlet";

    //----------交易查询-------------------
    /*
    * 参数:
		orderId       --消费订单号
返回:
		resultCode:"0000":交易成功
		message:返回描述
*/
    public static String query_NoCardURL=NoCardURL+"query";

//*****************线上无卡新增接口------T1-----*****************

    public static String sdkEasy_NoCardURL=NoCardURL+"sdkEasy";//下单  获取验证码
    public static String bcconEasyPay_NoCardURL=NoCardURL+"bcconEasyPay";//确认支付
    public static String easyFindOrder_NoCardURL=NoCardURL+"easyFindOrder";//查询是否成功

}