package work.jimmmy.foodie.mapper;

import org.apache.ibatis.annotations.Param;
import work.jimmmy.foodie.pojo.vo.SearchItemsVO;

import java.util.List;
import java.util.Map;

public interface ItemsMapperCustom {
    List<SearchItemsVO> searchItems(@Param("paramsMap") Map<String ,Object> map);

    List<SearchItemsVO> searchItemsBy3rdCat(@Param("paramsMap") Map<String ,Object> map);
}
