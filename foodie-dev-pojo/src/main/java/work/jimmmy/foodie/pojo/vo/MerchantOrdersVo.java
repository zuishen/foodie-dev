package work.jimmmy.foodie.pojo.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MerchantOrdersVo {
    /**
     * 商户订单号
     */
    private String merchantOrderId;

    /**
     * 商户方的发起用户的用户id
     */
    private String merchantUserId;

    /**
     * 实际支付的总金额
     */
    private Integer amount;

    /**
     * 支付方式 1微信 2支付宝
     */
    private Integer payMethod;

    /**
     * 支付成功后的回调地址
     */
    private String returnUrl;
}
