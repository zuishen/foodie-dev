package work.jimmmy.foodie.mapper;

import org.apache.ibatis.annotations.Param;
import work.jimmmy.foodie.pojo.vo.CategoryVO;
import work.jimmmy.foodie.pojo.vo.NewItemsVO;

import java.util.List;
import java.util.Map;

public interface CategoryMapperCustom {
    List<CategoryVO> getSubCatList(Integer rootCatId);

    List<NewItemsVO> getSixNewItemsLazy(@Param("paramsMap") Map<String, Object> map);
}
