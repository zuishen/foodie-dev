package work.jimmmy.foodie.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderStatusCountsVo {
    private Integer waitPayCounts;

    private Integer waitDeliverCounts;

    private Integer waitReceiveCounts;

    private Integer waitCommentCounts;
}
