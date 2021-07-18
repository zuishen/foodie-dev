package work.jimmmy.foodie.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;
import work.jimmmy.foodie.pojo.Carousel;
import work.jimmmy.foodie.service.CarouselService;
import work.jimmmy.foodie.service.CategoryService;
import work.jimmy.foodie.common.enums.YesOrNo;
import work.jimmy.foodie.common.utils.JsonResultResponse;

import javax.websocket.server.PathParam;
import java.util.List;

@Api(value = "首页", tags = "首页展示相关的接口")
@RestController
@RequestMapping("index")
public class IndexController {
    @Autowired
    private CarouselService carouselService;

    @Autowired
    private CategoryService categoryService;

    /**
     * 获取首页轮播图列表
     * @return list
     */
    @ApiOperation(value = "获取轮播图列表", notes = "获取轮播图列表", httpMethod = "GET")
    @GetMapping("/carousel")
    public JsonResultResponse carousel() {
        List<Carousel> list = carouselService.queryAll(YesOrNo.YES.type);
        return JsonResultResponse.ok(list);
    }

    /**
     * 查询首页的商品一级类别
     * 首页分类展示需求：
     * 1. 第一次刷新主页查询大分类，渲染展示到首页
     * 2. 如果鼠标上移到大分类，则加载其子分类的内容，如果已经存在子分类，则不需要加载（懒加载）
     */
    @ApiOperation(value = "获取商品的一级分类", notes = "获取商品分类", httpMethod = "GET")
    @GetMapping("/cats")
    public JsonResultResponse cats() {
        return JsonResultResponse.ok(categoryService.queryAllRootLevelCat());
    }

    @ApiOperation(value = "获取商品子分类", notes = "获取商品子分类", httpMethod = "GET")
    @GetMapping("/subCat/{rootCatId}")
    public JsonResultResponse subcats(@PathVariable @ApiParam(name = "rootCatId", value = "一级分类id", required = true)
                                                  Integer rootCatId) {
        if (rootCatId == null) {
            return JsonResultResponse.errorMsg("分类不存在");
        }
        return JsonResultResponse.ok(categoryService.getSubCatList(rootCatId));
    }

    @ApiOperation(value = "查询每个一级分类下的最新6条商品数据", notes = "查询每个一级分类下的最新6条商品数据", httpMethod = "GET")
    @GetMapping("/sixNewItems/{rootCatId}")
    public JsonResultResponse sixNewItems(@PathVariable @ApiParam(name = "rootCatId", value = "一级分类id", required = true)
                                              Integer rootCatId) {
        if (rootCatId == null) {
            return JsonResultResponse.errorMsg("分类不存在");
        }
        return JsonResultResponse.ok(categoryService.getSixNewItemLazy(rootCatId));
    }
}
