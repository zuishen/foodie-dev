package work.jimmmy.foodie.service.center;

import work.jimmmy.foodie.pojo.Orders;
import work.jimmy.foodie.common.utils.PagedGridResult;

public interface MyOrdersService {
    /**
     * 查询我的订单列表
     * @param userId
     * @param orderStatus
     * @param page
     * @param pageSize
     * @return
     */
    PagedGridResult queryMyOrders(String userId, Integer orderStatus, Integer page, Integer pageSize);

    /**
     * 订单状态 --> 商家发货
     * @param orderId
     */
    void updateDeliverOrderStatus(String orderId);

    /**
     * 查询我的订单
     *
     * @param userId
     * @param orderId
     * @return
     */
    Orders queryMyOrder(String userId, String orderId);

    /**
     * 更新订单状态 -> 确认收货
     *
     * @param orderId
     * @return
     */
    boolean updateReceiveOrderStatus(String orderId);

    /**
     * 删除订单 - 逻辑删除
     * @param userId
     * @param orderId
     * @return
     */
    boolean deleteOrder(String userId, String orderId);
}
