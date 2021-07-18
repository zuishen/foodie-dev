package work.jimmmy.foodie.service;

import org.apache.ibatis.annotations.Param;
import work.jimmmy.foodie.pojo.Category;
import work.jimmmy.foodie.pojo.vo.CategoryVO;
import work.jimmmy.foodie.pojo.vo.NewItemsVO;

import java.util.List;
import java.util.Map;

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

    /**
     * 查询首页每个一级分类下的6条最新商品数据
     * @param rootCatId rootCatId
     * @return list
     */
    List<NewItemsVO> getSixNewItemLazy(Integer rootCatId);
}
