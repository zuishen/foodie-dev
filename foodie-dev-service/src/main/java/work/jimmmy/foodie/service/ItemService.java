package work.jimmmy.foodie.service;

import work.jimmmy.foodie.pojo.Items;
import work.jimmmy.foodie.pojo.ItemsImg;
import work.jimmmy.foodie.pojo.ItemsParam;
import work.jimmmy.foodie.pojo.ItemsSpec;

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
}
