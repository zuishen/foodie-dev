package work.jimmmy.foodie.controller;

import org.springframework.beans.factory.annotation.Autowired;
import work.jimmmy.foodie.pojo.Orders;
import work.jimmmy.foodie.service.center.MyOrdersService;
import work.jimmy.foodie.common.utils.JsonResultResponse;

public class BaseController {
    public static final Integer COMMON_PAGE_SIZE = 10;

    public static final Integer SEARCH_PAGE_SIZE = 20;

    public static final String FOODIE_SHOPCART = "shopcart";

    public static final String PAYMENT_URL = "http://payment.t.mukewang.com/foodie-payment/payment/createMerchantOrder";

    // 微信支付成功 -> 支付中心 -> 天天吃货平台
    //                      ｜ -> 回调通知的url
    public static final String PAY_RETURN_URL = "http://fcevm3.natappfree.cc/orders/notifyMerchantOrderPaid";

    @Autowired
    protected MyOrdersService myOrdersService;

    /**
     * 用于验证用户和订单是否有关联关系，避免非凡用户调用
     *
     * @param userId
     * @param orderId
     * @return
     */
    protected JsonResultResponse checkUserOrder(String userId, String orderId) {
        Orders orders = myOrdersService.queryMyOrder(userId, orderId);
        if (orders == null) {
            return JsonResultResponse.errorMsg("订单不存在");
        }
        return JsonResultResponse.ok(orders);
    }
}
