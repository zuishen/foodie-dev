package work.jimmmy.foodie.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;
import work.jimmmy.foodie.mapper.*;
import work.jimmmy.foodie.pojo.*;
import work.jimmmy.foodie.pojo.vo.CommentLevelCountsVO;
import work.jimmmy.foodie.pojo.vo.ItemCommentVO;
import work.jimmmy.foodie.service.ItemService;
import work.jimmy.foodie.common.enums.CommentLevel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private ItemsMapper itemsMapper;

    @Autowired
    ItemsImgMapper imgMapper;

    @Autowired
    ItemsSpecMapper itemsSpecMapper;

    @Autowired
    ItemsParamMapper itemsParamMapper;

    @Autowired
    ItemsCommentsMapper itemsCommentsMapper;

    @Autowired
    ItemsCommentsCustomMapper itemsCommentsCustomMapper;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Items queryItemById(String itemId) {
        return itemsMapper.selectByPrimaryKey(itemId);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<ItemsImg> queryItemImgList(String itemId) {
        Example itemsImgExp = new Example(ItemsImg.class);
        Example.Criteria criteria = itemsImgExp.createCriteria();
        criteria.andEqualTo("itemId", itemId);
        return imgMapper.selectByExample(itemsImgExp);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<ItemsSpec> queryItemSpecList(String itemId) {
        Example itemsImgExp = new Example(ItemsSpec.class);
        Example.Criteria criteria = itemsImgExp.createCriteria();
        criteria.andEqualTo("itemId", itemId);
        return itemsSpecMapper.selectByExample(itemsImgExp);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public ItemsParam queryItemParam(String itemId) {
        Example itemsImgExp = new Example(ItemsParam.class);
        Example.Criteria criteria = itemsImgExp.createCriteria();
        criteria.andEqualTo("itemId", itemId);
        return itemsParamMapper.selectOneByExample(itemsImgExp);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public CommentLevelCountsVO queryCommentCounts(String itemId) {
        Integer goodCounts = getCommentCounts(itemId, CommentLevel.GOOD.getType());
        Integer normalCounts = getCommentCounts(itemId, CommentLevel.GOOD.getType());
        Integer badCounts = getCommentCounts(itemId, CommentLevel.GOOD.getType());
        Integer totalCounts = goodCounts + normalCounts + badCounts;
        CommentLevelCountsVO vo = new CommentLevelCountsVO();
        vo.setGoodCounts(goodCounts);
        vo.setNormalCounts(normalCounts);
        vo.setBadCounts(badCounts);
        vo.setTotalCounts(totalCounts);
        return vo;
    }

    @Override
    public List<ItemCommentVO> queryPagedComments(String itemId, Integer level) {
        Map<String, Object> map = new HashMap<>();
        map.put("itemId", itemId);
        map.put("level", level);
        List<ItemCommentVO> vos = itemsCommentsCustomMapper.queryItemComments(map);
        return vos;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    Integer getCommentCounts(String itemId, Integer commentLevel) {
        ItemsComments condition = new ItemsComments();
        condition.setItemId(itemId);
        if (commentLevel != null) {
            condition.setCommentLevel(commentLevel);
        }
        return itemsCommentsMapper.selectCount(condition);
    }
}