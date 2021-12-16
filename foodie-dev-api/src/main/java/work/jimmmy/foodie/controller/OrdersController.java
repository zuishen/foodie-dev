package work.jimmmy.foodie.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import work.jimmmy.foodie.pojo.OrderStatus;
import work.jimmmy.foodie.pojo.bo.SubmitOrderBo;
import work.jimmmy.foodie.pojo.vo.MerchantOrdersVo;
import work.jimmmy.foodie.pojo.vo.OrderVo;
import work.jimmmy.foodie.service.OrderService;
import work.jimmy.foodie.common.enums.OrderStatusEnum;
import work.jimmy.foodie.common.enums.PayMethod;
import work.jimmy.foodie.common.utils.CookieUtils;
import work.jimmy.foodie.common.utils.JsonResultResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

@Api(value = "订单相关", tags = {"订单相关的api接口"})
@RequestMapping("orders")
@RestController
public class OrdersController extends BaseController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private RestTemplate restTemplate;

    @ApiOperation(value = "用户下单", notes = "用户下单", httpMethod = "POST")
    @PostMapping("/create")
    public JsonResultResponse create(@RequestBody SubmitOrderBo submitOrderBo, HttpServletRequest request,
                                     HttpServletResponse response) {
        if (!Objects.equals(submitOrderBo.getPayMethod(), PayMethod.WEIXIN.type)
        && !Objects.equals(submitOrderBo.getPayMethod(), PayMethod.ALIPAY.type)) {
            return JsonResultResponse.errorMsg("");
        }
        System.out.println(submitOrderBo.toString());
        // 1.创建订单
        OrderVo orderVo = orderService.createOrder(submitOrderBo);
        // 2.创建订单以后，移除购物车中已提交的商品
        /**
         * 1001
         * 2002 -> 购买后 -> 从购物车中移除
         * 3003 -> 购买后 -> 从购物车中移除
         * 4004
         */
        // TODO: 整合redis之后，完善购物车中的已结算商品清楚，并且同步到前端的cookie
        CookieUtils.setCookie(request, response, FOODIE_SHOPCART, "", true);
        // TODO: 3.向支付中心发送当前订单，用于保存支付中心的订单数据
        MerchantOrdersVo merchantOrdersVo = orderVo.getMerchantOrdersVo();
        merchantOrdersVo.setReturnUrl(PAY_RETURN_URL);
        // 金额改为1分钱
        merchantOrdersVo.setAmount(1);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("imoocUserId", "123778177");
        headers.add("password", "ft43-wftv");

        HttpEntity<MerchantOrdersVo> entity = new HttpEntity<>(merchantOrdersVo, headers);

        ResponseEntity<JsonResultResponse> responseEntity =
                restTemplate.postForEntity(PAYMENT_URL, entity, JsonResultResponse.class);
        JsonResultResponse paymentResult = responseEntity.getBody();
        if (paymentResult == null || paymentResult.getStatus() != HttpStatus.OK.value()) {
            return JsonResultResponse.errorMsg("支付中心订单创建失败，请联系管理员！");
        }

        return JsonResultResponse.ok(orderVo.getOrderId());
    }

    @PostMapping("notifyMerchantOrderPaid")
    public Integer notifyMerchantOrderPaid(String merchantOrderId) {
        orderService.updateOrderStatus(merchantOrderId, OrderStatusEnum.WAIT_DELIVER.type);
        return HttpStatus.OK.value();
    }

    @PostMapping("getPaidOrderInfo")
    public JsonResultResponse getPaidOrderInfo(String orderId) {
        OrderStatus orderStatus = orderService.queryOrderStatusInfo(orderId);
        return JsonResultResponse.ok(orderStatus);
    }
}
