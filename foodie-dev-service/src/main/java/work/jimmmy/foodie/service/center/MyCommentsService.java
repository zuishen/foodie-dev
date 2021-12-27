package work.jimmmy.foodie.service.center;

import work.jimmmy.foodie.pojo.OrderItems;
import work.jimmmy.foodie.pojo.bo.center.OrderItemsCommentBo;
import work.jimmy.foodie.common.utils.PagedGridResult;

import java.util.List;

public interface MyCommentsService {

    /**
     * 根据订单id查询关联的商品
     *
     * @param orderId
     * @return
     */
    List<OrderItems> queryPendingComment(String orderId);

    /**
     * 保存用户的评论
     *
     * @param orderId
     * @param userId
     * @param itemsComments
     */
    void saveComments(String orderId, String userId, List<OrderItemsCommentBo> itemsComments);

    /**
     * 我的评价分页查询
     *
     * @param userId
     * @param page
     * @param pageSize
     * @return
     */
    PagedGridResult queryMyComments(String userId, Integer page, Integer pageSize);
}
