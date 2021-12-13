package work.jimmmy.foodie.service;

import work.jimmmy.foodie.pojo.bo.SubmitOrderBo;

public interface OrderService {
    /**
     * 创建订单
     *
     * @param submitOrderBo 创建订单bo
     * @return
     */
    String createOrder(SubmitOrderBo submitOrderBo);
}
