package work.jimmmy.foodie.controller;

public class BaseController {
    public static final Integer COMMENT_PAGE_SIZE = 10;

    public static final Integer SEARCH_PAGE_SIZE = 20;

    public static final String FOODIE_SHOPCART = "shopcart";

    public static final String PAYMENT_URL = "http://payment.t.mukewang.com/foodie-payment/payment/createMerchantOrder";

    // 微信支付成功 -> 支付中心 -> 天天吃货平台
    //                      ｜ -> 回调通知的url
    public static final String PAY_RETURN_URL = "http://vwujxa.natappfree.cc/orders/notifyMerchantOrderPaid";
}
