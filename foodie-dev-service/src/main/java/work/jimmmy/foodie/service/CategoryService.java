package work.jimmmy.foodie.service;

import work.jimmmy.foodie.pojo.Category;

import java.util.List;

public interface CategoryService {
    /**
     * 查询所有一级分类
     * @return list
     */
    List<Category> queryAllRootLevelCat();
}
