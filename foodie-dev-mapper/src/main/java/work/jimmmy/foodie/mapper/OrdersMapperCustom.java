package work.jimmmy.foodie.mapper;

import org.apache.ibatis.annotations.Param;
import work.jimmmy.foodie.pojo.vo.MyOrdersVo;

import java.util.List;
import java.util.Map;

public interface OrdersMapperCustom  {
    List<MyOrdersVo> queryMyOrders(@Param(value = "paramMap") Map<String,Object> paramMap);
}
