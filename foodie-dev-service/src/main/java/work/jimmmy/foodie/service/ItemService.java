package work.jimmmy.foodie.service;

import work.jimmmy.foodie.pojo.Items;
import work.jimmmy.foodie.pojo.ItemsImg;
import work.jimmmy.foodie.pojo.ItemsParam;
import work.jimmmy.foodie.pojo.ItemsSpec;
import work.jimmmy.foodie.pojo.vo.CommentLevelCountsVO;
import work.jimmmy.foodie.pojo.vo.ItemCommentVO;
import work.jimmmy.foodie.pojo.vo.ShopCartVo;
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

    /**
     * 搜索商品列表
     *
     * @param keywords keywords
     * @param sort sort
     * @param page page
     * @param pageSize pageSize
     * @return result
     */
    PagedGridResult searchItems(String keywords, String sort, Integer page, Integer pageSize);

    /**
     * 通过三级分类id搜索商品列表
     *
     * @param catId catId
     * @param sort sort
     * @param page page
     * @param pageSize pageSize
     * @return result
     */
    PagedGridResult searchItems(Integer catId, String sort, Integer page, Integer pageSize);

    /**
     * 根据规格ids查询最新的购物车中的商品数据（用于刷新渲染购物车中的商品数据）
     * @param specIds 规格ids
     * @return list
     */
    List<ShopCartVo> queryItemsBySpecIds(String specIds);
}
