package work.jimmmy.foodie.mapper;

import work.jimmmy.foodie.pojo.vo.CategoryVO;

import java.util.List;

public interface CategoryMapperCustom {
    List<CategoryVO> getSubCatList(Integer rootCatId);
}
