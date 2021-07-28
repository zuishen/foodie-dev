package work.jimmmy.foodie.mapper;

import org.apache.ibatis.annotations.Param;
import work.jimmmy.foodie.pojo.vo.ItemCommentVO;

import java.util.List;
import java.util.Map;

public interface ItemsCommentsCustomMapper {
    List<ItemCommentVO> queryItemComments(@Param("paramsMap") Map<String, Object> map);
}
