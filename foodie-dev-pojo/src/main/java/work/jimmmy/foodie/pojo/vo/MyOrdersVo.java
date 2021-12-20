package work.jimmmy.foodie.pojo.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

/**
 * 用户中心，我的订单列表vo
 */
@Getter
@Setter
public class MyOrdersVo {
    private String orderId;

    private Date createdTime;

    private Integer payMethod;

    private Integer realPayAmount;

    private Integer postAmount;

    private Integer isComment;

    private Integer orderStatus;

    private List<MySubOrderItemVo> subOrderItemList;
}
