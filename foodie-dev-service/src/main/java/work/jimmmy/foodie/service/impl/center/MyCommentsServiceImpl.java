package work.jimmmy.foodie.service.impl.center;

import com.github.pagehelper.PageHelper;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import work.jimmmy.foodie.mapper.ItemsCommentsMapperCustom;
import work.jimmmy.foodie.mapper.OrderItemsMapper;
import work.jimmmy.foodie.mapper.OrderStatusMapper;
import work.jimmmy.foodie.mapper.OrdersMapper;
import work.jimmmy.foodie.pojo.OrderItems;
import work.jimmmy.foodie.pojo.OrderStatus;
import work.jimmmy.foodie.pojo.Orders;
import work.jimmmy.foodie.pojo.bo.center.OrderItemsCommentBo;
import work.jimmmy.foodie.pojo.vo.MyCommentVo;
import work.jimmmy.foodie.service.center.MyCommentsService;
import work.jimmmy.foodie.service.impl.BaseService;
import work.jimmy.foodie.common.enums.YesOrNo;
import work.jimmy.foodie.common.utils.PagedGridResult;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MyCommentsServiceImpl extends BaseService implements MyCommentsService {
    @Autowired
    private OrdersMapper ordersMapper;

    @Autowired
    private OrderItemsMapper orderItemsMapper;

    @Autowired
    private ItemsCommentsMapperCustom itemsCommentsMapperCustom;

    @Autowired
    private OrderStatusMapper orderStatusMapper;

    @Autowired
    private Sid sid;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<OrderItems> queryPendingComment(String orderId) {
        OrderItems query = new OrderItems();
        query.setOrderId(orderId);
        return orderItemsMapper.select(query);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void saveComments(String orderId, String userId, List<OrderItemsCommentBo> commentList) {
        // 1. 保存评价 items_comments
        for (OrderItemsCommentBo oic : commentList) {
            oic.setCommentId(sid.nextShort());
        }
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("commentList", commentList);
        itemsCommentsMapperCustom.saveComments(map);

        // 2. 修改订单表改已评价 orders
        Orders order = new Orders();
        order.setId(orderId);
        order.setIsComment(YesOrNo.YES.type);
        ordersMapper.updateByPrimaryKeySelective(order);

        // 3. 修改订单状态表的留言时间 order_status
        OrderStatus orderStatus = new OrderStatus();
        orderStatus.setOrderId(orderId);
        orderStatus.setCommentTime(new Date());
        orderStatusMapper.updateByPrimaryKeySelective(orderStatus);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public PagedGridResult queryMyComments(String userId, Integer page, Integer pageSize) {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);

        PageHelper.startPage(page, pageSize);
        List<MyCommentVo> list = itemsCommentsMapperCustom.queryMyComments(map);
        return setPagedGrid(list, page);
    }
}
