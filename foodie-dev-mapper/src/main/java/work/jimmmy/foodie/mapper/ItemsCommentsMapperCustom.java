package work.jimmmy.foodie.mapper;

import org.apache.ibatis.annotations.Param;
import work.jimmmy.foodie.pojo.ItemsComments;
import work.jimmmy.foodie.pojo.bo.center.OrderItemsCommentBo;
import work.jimmmy.foodie.pojo.vo.MyCommentVo;
import work.jimmmy.tk.mapper.MyMapper;

import java.util.List;
import java.util.Map;

public interface ItemsCommentsMapperCustom {
    /**
     * 保存评论信息
     *
     * @param map
     */
    void saveComments(@Param("map") Map<String, Object> map);

    List<MyCommentVo> queryMyComments(@Param("paramsMap") Map<String, Object> map);
}