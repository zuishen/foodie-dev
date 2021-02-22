package work.jimmmy.foodie.service;

import work.jimmmy.foodie.pojo.Carousel;

import java.util.List;

public interface CarouselService {
    /**
     * 查询所有轮播图列表
     *
     * @param isShow isShow
     * @return list
     */
    List<Carousel> queryAll(Integer isShow);
}
