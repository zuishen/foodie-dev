package work.jimmmy.foodie.pojo.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class OrderVo {
    private String orderId;

    private MerchantOrdersVo merchantOrdersVo;
}
