package work.jimmmy.foodie.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import work.jimmmy.foodie.pojo.bo.SubmitOrderBo;
import work.jimmmy.foodie.service.OrderService;
import work.jimmy.foodie.common.enums.PayMethod;
import work.jimmy.foodie.common.utils.CookieUtils;
import work.jimmy.foodie.common.utils.JsonResultResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

@Api(value = "订单相关", tags = {"订单相关的api接口"})
@RequestMapping("orders")
@RestController
public class OrdersController {
    @Autowired
    private OrderService orderService;

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
        String orderId = orderService.createOrder(submitOrderBo);
        // 2.创建订单以后，移除购物车中已提交的商品
        /**
         * 1001
         * 2002 -> 购买后 -> 从购物车中移除
         * 3003 -> 购买后 -> 从购物车中移除
         * 4004
         */
        // TODO: 整合redis之后，完善购物车中的已结算商品清楚，并且同步到前端的cookie
        CookieUtils.setCookie(request, response, BaseController.FOODIE_SHOPCART, "", true);
        // TODO: 3.向支付中心发送当前订单，用于保存支付中心的订单数据
        return JsonResultResponse.ok(orderId);
    }


}
