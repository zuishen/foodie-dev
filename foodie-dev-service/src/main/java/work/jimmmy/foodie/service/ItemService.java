package work.jimmmy.foodie.service;

import work.jimmmy.foodie.pojo.Items;
import work.jimmmy.foodie.pojo.ItemsImg;
import work.jimmmy.foodie.pojo.ItemsParam;
import work.jimmmy.foodie.pojo.ItemsSpec;
import work.jimmmy.foodie.pojo.vo.CommentLevelCountsVO;
import work.jimmmy.foodie.pojo.vo.ItemCommentVO;
import work.jimmy.foodie.common.utils.PagedGridResult;

import java.util.List;

public interface ItemService {
    /**
     * 根据商品ID查询详情
     *
     * @param itemId itemId
     * @return 商品
     */
    Items queryItemById(String itemId);

    /**
     * 根据商品id查询商品图片列表
     *
     * @param itemId itemId
     * @return 商品图片
     */
    List<ItemsImg> queryItemImgList(String itemId);

    /**
     * 根据商品ID查询商品规格
     *
     * @param itemId itemId
     * @return 商品规格
     */
    List<ItemsSpec> queryItemSpecList(String itemId);

    /**
     * 根据商品id查询商品参数
     *
     * @param itemId itemId
     * @return 商品参数
     */
    ItemsParam queryItemParam(String itemId);

    /**
     * 根据商品id查询商品评价的等级数量
     *
     * @param itemId 商品id
     */
    CommentLevelCountsVO queryCommentCounts(String itemId);

    /**
     * 根据商品id分页查询商品评价
     *
     * @param itemId itemId
     * @param level level
     * @param page page
     * @param pageSize pageSize
     * @return list
     */
    PagedGridResult queryPagedComments(String itemId, Integer level, int page, int pageSize);
}
