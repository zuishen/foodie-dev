package work.jimmmy.foodie.pojo.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class MyCommentVo {
    private String commentId;

    private String content;

    private Date createdTime;

    private String itemId;

    private String itemName;

    private String specName;

    private String itemImg;
}
