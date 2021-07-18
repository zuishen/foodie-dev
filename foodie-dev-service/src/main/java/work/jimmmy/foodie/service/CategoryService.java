package work.jimmmy.foodie.service;

import work.jimmmy.foodie.pojo.Category;
import work.jimmmy.foodie.pojo.vo.CategoryVO;

import java.util.List;

public interface CategoryService {
    /**
     * 查询所有一级分类
     * @return list
     */
    List<Category> queryAllRootLevelCat();

    /**
     * 根据一级分类Id查询子分类
     *
     * @param rootCatId rootCatId
     * @return LIST
     */
    List<CategoryVO> getSubCatList(Integer rootCatId);
}
