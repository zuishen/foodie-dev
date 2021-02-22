package work.jimmmy.foodie.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import work.jimmmy.foodie.pojo.Carousel;
import work.jimmmy.foodie.service.CarouselService;
import work.jimmy.foodie.common.enums.YesOrNo;
import work.jimmy.foodie.common.utils.JsonResultResponse;

import java.util.List;

@RestController
@RequestMapping("index")
public class IndexController {
    @Autowired
    private CarouselService carouselService;

    /**
     * 获取首页轮播图列表
     * @return list
     */
    @GetMapping("/carousel")
    public JsonResultResponse carousel() {
        List<Carousel> list = carouselService.queryAll(YesOrNo.YES.type);
        return JsonResultResponse.ok(list);
    }
}
