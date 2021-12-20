package work.jimmmy.foodie.pojo.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * 用户中心，我的订单列表嵌套商品vo
 */
@Getter
@Setter
public class MySubOrderItemVo {
    private String itemId;

    private String itemImg;

    private String itemName;

    private String itemSpecId;

    private String itemSpecName;

    private Integer buyCounts;

    private Integer price;
}
