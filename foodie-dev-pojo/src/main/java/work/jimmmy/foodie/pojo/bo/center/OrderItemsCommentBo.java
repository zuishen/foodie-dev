package work.jimmmy.foodie.pojo.bo.center;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(of = {"commentLevel", "content"})
public class OrderItemsCommentBo {
    private String commentId;

    private String itemId;

    private String itemName;

    private String itemSpecId;

    private String itemSpecName;

    private Integer commentLevel;

    private String content;
}
