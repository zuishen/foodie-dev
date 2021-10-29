package work.jimmmy.foodie.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;
import work.jimmmy.foodie.pojo.bo.ShopCartBo;
import work.jimmy.foodie.common.utils.JsonResultResponse;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Api(value = "购物车接口controller", tags = {"购物车接口相关api"})
@RestController
@RequestMapping("shopcart")
public class ShopCartController {

    @ApiOperation(value = "添加商品到购物车", notes = "添加商品到购物车", httpMethod = "POST")
    @GetMapping("/add")
    public JsonResultResponse add(
            @RequestParam String userId,
            @RequestBody ShopCartBo shopCartBo,
            HttpServletRequest request,
            HttpServletResponse response
            ) {
        if (StringUtils.isEmpty(userId)) {
            return JsonResultResponse.errorMsg("");
        }
        // TODO: 前端用户在登录的情况下，添加商品到购物车，会同时在后端同步购物车到redis缓存
        return JsonResultResponse.ok();
    }
}
