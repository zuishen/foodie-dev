package work.jimmmy.foodie.controller.center;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import work.jimmmy.foodie.controller.BaseController;
import work.jimmmy.foodie.pojo.OrderItems;
import work.jimmmy.foodie.pojo.Orders;
import work.jimmmy.foodie.pojo.bo.center.OrderItemsCommentBo;
import work.jimmmy.foodie.service.center.MyCommentsService;
import work.jimmy.foodie.common.enums.YesOrNo;
import work.jimmy.foodie.common.utils.JsonResultResponse;
import work.jimmy.foodie.common.utils.PagedGridResult;

import java.util.List;
import java.util.Objects;

@Api(value = "用户中心评价模块", tags = {"用户中心评价模块相关接口"})
@RestController
@RequestMapping("mycomments")
public class MyCommentsController extends BaseController {
    @Autowired
    private MyCommentsService myCommentsService;

    @ApiOperation(value = "查询订单列表", notes = "查询订单列表", httpMethod = "POST")
    @PostMapping("/pending")
    public JsonResultResponse comments(
            @ApiParam(name = "userId", value = "用户id", required = true)
            @RequestParam String userId,
            @ApiParam(name = "orderId", value = "订单id", required = true)
            @RequestParam String orderId) {
        // 检查用户和订单是否是关联的
        JsonResultResponse checkResult = checkUserOrder(userId, orderId);
        if (checkResult.getStatus() != HttpStatus.OK.value()) {
            return checkResult;
        }
        // 判断该笔订单是否已经评价过，如果已经评价过，就不再继续
        Orders myOrder = (Orders) checkResult.getData();
        if (Objects.equals(myOrder.getIsComment(), YesOrNo.YES.type)) {
            return JsonResultResponse.errorMsg("该笔订单已经评价");
        }
        List<OrderItems> list = myCommentsService.queryPendingComment(orderId);

        return JsonResultResponse.ok(list);
    }

    @ApiOperation(value = "保存评论列表", notes = "保存评论列表", httpMethod = "POST")
    @PostMapping("/saveList")
    public JsonResultResponse saveList(
            @ApiParam(name = "userId", value = "用户id", required = true)
            @RequestParam String userId,
            @ApiParam(name = "orderId", value = "订单id", required = true)
            @RequestParam String orderId,
            @RequestBody List<OrderItemsCommentBo> commentList) {
        System.out.println(commentList);
        // 检查用户和订单是否是关联的
        JsonResultResponse checkResult = checkUserOrder(userId, orderId);
        if (checkResult.getStatus() != HttpStatus.OK.value()) {
            return checkResult;
        }
        // 判断评论list不能为空
        if (commentList == null || commentList.isEmpty()) {
            return JsonResultResponse.errorMsg("评论内容不能为空");
        }

        myCommentsService.saveComments(orderId, userId, commentList);

        return JsonResultResponse.ok();
    }

    @ApiOperation(value = "查询我的评价", notes = "查询我的评价", httpMethod = "GET")
    @PostMapping("query")
    public JsonResultResponse query(
            @ApiParam(name = "userId", value = "用户id", required = true)
            @RequestParam String userId,
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
        PagedGridResult grid = myCommentsService.queryMyComments(userId, page, pageSize);
        return JsonResultResponse.ok(grid);
    }
}
