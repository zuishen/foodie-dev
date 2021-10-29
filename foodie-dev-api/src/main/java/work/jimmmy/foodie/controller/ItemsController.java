package work.jimmmy.foodie.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.spring.web.json.Json;
import work.jimmmy.foodie.mapper.ItemsCommentsMapper;
import work.jimmmy.foodie.pojo.Items;
import work.jimmmy.foodie.pojo.ItemsImg;
import work.jimmmy.foodie.pojo.ItemsParam;
import work.jimmmy.foodie.pojo.ItemsSpec;
import work.jimmmy.foodie.pojo.vo.CommentLevelCountsVO;
import work.jimmmy.foodie.pojo.vo.ItemInfoVO;
import work.jimmmy.foodie.service.ItemService;
import work.jimmy.foodie.common.utils.JsonResultResponse;
import work.jimmy.foodie.common.utils.PagedGridResult;

import java.util.List;

@Api(value = "商品接口", tags = "商品信息展示的相关接口")
@RestController
@RequestMapping("items")
public class ItemsController extends BaseController {
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

    @ApiOperation(value = "查询商品评价", notes = "查询商品评价", httpMethod = "GET")
    @GetMapping("/comments")
    public JsonResultResponse comments(
            @ApiParam(name = "itemId", value = "商品id", required = true)
            @RequestParam String itemId,
            @ApiParam(name = "level", value = "评价等级", required = false)
            @RequestParam Integer level,
            @ApiParam(name = "page", value = "查询下一页的第几页", required = false)
            @RequestParam Integer page,
            @ApiParam(name = "pageSize", value = "分页的每一页显示的条数", required = false)
            @RequestParam Integer pageSize
            ) {
        if (StringUtils.isBlank(itemId)) {
            return JsonResultResponse.errorMsg(null);
        }
        if (page == null) {
            page = 1;
        }
        if (pageSize == null) {
            pageSize = COMMENT_PAGE_SIZE;
        }
        PagedGridResult grid = itemService.queryPagedComments(itemId, level, page, pageSize);
        return JsonResultResponse.ok(grid);
    }

    @ApiOperation(value = "搜索商品列表", notes = "搜索商品列表", httpMethod = "GET")
    @GetMapping("/search")
    public JsonResultResponse search(
            @ApiParam(name = "keywords", value = "关键字", required = true)
            @RequestParam String keywords,
            @ApiParam(name = "sort", value = "排序", required = false)
            @RequestParam(required = false) String sort,
            @ApiParam(name = "page", value = "查询下一页的第几页", required = false)
            @RequestParam(required = false) Integer page,
            @ApiParam(name = "pageSize", value = "分页的每一页显示的条数", required = false)
            @RequestParam(required = false) Integer pageSize
    ) {
        if (StringUtils.isBlank(keywords)) {
            return JsonResultResponse.errorMsg(null);
        }
        if (page == null) {
            page = 1;
        }
        if (pageSize == null) {
            pageSize = SEARCH_PAGE_SIZE;
        }
        PagedGridResult grid = itemService.searchItems(keywords, sort, page, pageSize);
        return JsonResultResponse.ok(grid);
    }

    @ApiOperation(value = "通过分类id搜索商品列表", notes = "通过分类id搜索商品列表", httpMethod = "GET")
    @GetMapping("/catItems")
    public JsonResultResponse search(
            @ApiParam(name = "catId", value = "三级分类id", required = true)
            @RequestParam Integer catId,
            @ApiParam(name = "sort", value = "排序", required = false)
            @RequestParam(required = false) String sort,
            @ApiParam(name = "page", value = "查询下一页的第几页", required = false)
            @RequestParam(required = false) Integer page,
            @ApiParam(name = "pageSize", value = "分页的每一页显示的条数", required = false)
            @RequestParam(required = false) Integer pageSize
    ) {
        if (catId == null) {
            return JsonResultResponse.errorMsg(null);
        }
        if (page == null) {
            page = 1;
        }
        if (pageSize == null) {
            pageSize = SEARCH_PAGE_SIZE;
        }
        PagedGridResult grid = itemService.searchItems(catId, sort, page, pageSize);
        return JsonResultResponse.ok(grid);
    }

    /**
     * 用于用户长时间未登录网站，刷新购物车中的数据（主要是商品价格），类似淘宝京东
     * @return
     */
    @ApiOperation(value = "根据商品规格ids查找最新的商品数据", notes = "根据商品规格ids查找最新的商品数据")
    @RequestMapping("/refresh")
    public JsonResultResponse cartItems(
            @ApiParam(name = "itemSpecIds", value = "拼接的规格ids", required = true, example = "1001,1003,1005")
            @RequestParam String itemSpecIds) {
        if (StringUtils.isBlank(itemSpecIds)) {
            return JsonResultResponse.ok();
        }
        return JsonResultResponse.ok(itemService.queryItemsBySpecIds(itemSpecIds));
    }
}
