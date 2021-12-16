package work.jimmmy.foodie.service;

import work.jimmmy.foodie.pojo.OrderStatus;
import work.jimmmy.foodie.pojo.bo.SubmitOrderBo;
import work.jimmmy.foodie.pojo.vo.OrderVo;

public interface OrderService {
    /**
     * 创建订单
     *
     * @param submitOrderBo 创建订单bo
     * @return
     */
    OrderVo createOrder(SubmitOrderBo submitOrderBo);

    /**
     * 修改订单状态
     *
     * @param orderId
     * @param orderStatus
     */
    void updateOrderStatus(String orderId, Integer orderStatus);

    /**
     * 查询订单状态
     *
     * @param orderId
     * @return OrderStatus
     */
    OrderStatus queryOrderStatusInfo(String orderId);
}
