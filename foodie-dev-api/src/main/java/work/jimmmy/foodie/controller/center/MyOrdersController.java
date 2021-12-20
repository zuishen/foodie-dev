package work.jimmmy.foodie.controller.center;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.spring.web.json.Json;
import work.jimmmy.foodie.controller.BaseController;
import work.jimmmy.foodie.pojo.Orders;
import work.jimmmy.foodie.service.center.MyOrdersService;
import work.jimmy.foodie.common.utils.JsonResultResponse;
import work.jimmy.foodie.common.utils.PagedGridResult;

@Api(value = "用户中心我的订单", tags = {"用户中心我的订单相关接口"})
@RestController
@RequestMapping("myorders")
public class MyOrdersController extends BaseController {

    @Autowired
    private MyOrdersService myOrdersService;

    @PostMapping("query")
    public JsonResultResponse queryMyOrders(
            @ApiParam(name = "userId", value = "用户id", required = true)
            @RequestParam String userId,
            @ApiParam(name = "orderStatus", value = "订单状态", required = false)
            @RequestParam Integer orderStatus,
            @ApiParam(name = "page", value = "查询下一页的第几页", required = false)
            @RequestParam Integer page,
            @ApiParam(name = "pageSize", value = "分页的每一页现实的条数", required = false)
            @RequestParam Integer pageSize) {
        if (StringUtils.isBlank(userId)) {
            return JsonResultResponse.errorMsg(null);
        }
        if (page == null) {
            page = 1;
        }
        if (pageSize == null) {
            pageSize = COMMON_PAGE_SIZE;
        }
        PagedGridResult grid = myOrdersService.queryMyOrders(userId, orderStatus, page, pageSize);
        return JsonResultResponse.ok(grid);
    }

    // 商家发货没有后端，所以这个接口仅仅只是用于模拟
    @ApiOperation(value = "商家发货", notes = "商家发货", httpMethod = "GET")
    @GetMapping("/deliver")
    public JsonResultResponse deliver(
            @ApiParam(name = "orderId", value = "订单id", required = true)
            @RequestParam String orderId) {
        if (StringUtils.isBlank(orderId)) {
            return JsonResultResponse.errorMsg("订单id不能为空");
        }
        myOrdersService.updateDeliverOrderStatus(orderId);
        return JsonResultResponse.ok();
    }

    @ApiOperation(value = "用户确认收货", notes = "用户确认收货", httpMethod = "POST")
    @PostMapping("/confirmReceive")
    public JsonResultResponse confirmReceive(
            @ApiParam(name = "orderId", value = "订单id", required = true)
            @RequestParam String orderId,
            @ApiParam(name = "userId", value = "用户id", required = true)
            @RequestParam String userId) {
        JsonResultResponse checkResult = checkUserOrder(userId, orderId);
        if (checkResult.getStatus() != HttpStatus.OK.value()) {
            return checkResult;
        }
        boolean res = myOrdersService.updateReceiveOrderStatus(orderId);
        if (!res) {
            return JsonResultResponse.errorMsg("订单确认收货失败");
        }

        return JsonResultResponse.ok();
    }

    @ApiOperation(value = "用户删除订单", notes = "用户删除订单", httpMethod = "POST")
    @PostMapping("/delete")
    public JsonResultResponse delete(
            @ApiParam(name = "orderId", value = "订单id", required = true)
            @RequestParam String orderId,
            @ApiParam(name = "userId", value = "用户id", required = true)
            @RequestParam String userId) {
        JsonResultResponse checkResult = checkUserOrder(userId, orderId);
        if (checkResult.getStatus() != HttpStatus.OK.value()) {
            return checkResult;
        }
        boolean res = myOrdersService.deleteOrder(userId, orderId);
        if (!res) {
            return JsonResultResponse.errorMsg("订单删除失败");
        }

        return JsonResultResponse.ok();
    }

    /**
     * 用于验证用户和订单是否有关联关系，避免非凡用户调用
     *
     * @param userId
     * @param orderId
     * @return
     */
    private JsonResultResponse checkUserOrder(String userId, String orderId) {
        Orders orders = myOrdersService.queryMyOrder(userId, orderId);
        if (orders == null) {
            return JsonResultResponse.errorMsg("订单不存在");
        }
        return JsonResultResponse.ok();
    }
}
