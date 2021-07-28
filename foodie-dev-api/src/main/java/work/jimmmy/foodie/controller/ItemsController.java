package work.jimmmy.foodie.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import work.jimmmy.foodie.mapper.ItemsCommentsMapper;
import work.jimmmy.foodie.pojo.Items;
import work.jimmmy.foodie.pojo.ItemsImg;
import work.jimmmy.foodie.pojo.ItemsParam;
import work.jimmmy.foodie.pojo.ItemsSpec;
import work.jimmmy.foodie.pojo.vo.CommentLevelCountsVO;
import work.jimmmy.foodie.pojo.vo.ItemInfoVO;
import work.jimmmy.foodie.service.ItemService;
import work.jimmy.foodie.common.utils.JsonResultResponse;

import java.util.List;

@Api(value = "商品接口", tags = "商品信息展示的相关接口")
@RestController
@RequestMapping("items")
public class ItemsController {
    @Autowired
    ItemService itemService;

    @ApiOperation(value = "查询商品详情", notes = "查询商品详情", httpMethod = "GET")
    @GetMapping("/info/{itemId}")
    public JsonResultResponse itemInfo(
            @ApiParam(name = "itemId", value = "商品id", required = true)
            @PathVariable String itemId) {
        if (StringUtils.isBlank(itemId)) {
            return JsonResultResponse.errorMsg(null);
        }
        ItemInfoVO itemInfoVO = new ItemInfoVO();
        Items item = itemService.queryItemById(itemId);
        itemInfoVO.setItem(item);
        List<ItemsImg> itemsImgList = itemService.queryItemImgList(itemId);
        itemInfoVO.setItemImgList(itemsImgList);
        List<ItemsSpec> itemsSpecList = itemService.queryItemSpecList(itemId);
        itemInfoVO.setItemSpecList(itemsSpecList);
        ItemsParam itemParams = itemService.queryItemParam(itemId);
        itemInfoVO.setItemParams(itemParams);
        return JsonResultResponse.ok(itemInfoVO);
    }

    @ApiOperation(value = "查询商品评价等级", notes = "查询商品评价等级", httpMethod = "GET")
    @GetMapping("/commentLevel")
    public JsonResultResponse commentLevel(
            @ApiParam(name = "itemId", value="商品id", required = true)
            @RequestParam String itemId) {
        if (StringUtils.isBlank(itemId)) {
            return JsonResultResponse.errorMsg(null);
        }
        CommentLevelCountsVO vo = itemService.queryCommentCounts(itemId);
        return JsonResultResponse.ok(vo);
    }
}
