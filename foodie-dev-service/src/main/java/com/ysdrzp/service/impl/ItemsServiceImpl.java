package com.ysdrzp.service.impl;

import com.ysdrzp.enums.CommentLevel;
import com.ysdrzp.mapper.*;
import com.ysdrzp.pojo.*;
import com.ysdrzp.service.ItemsService;
import com.ysdrzp.vo.CommentsCountVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class ItemsServiceImpl implements ItemsService {

    @Autowired
    ItemsMapper itemsMapper;

    @Autowired
    ItemsImgMapper itemsImgMapper;

    @Autowired
    ItemsSpecMapper itemsSpecMapper;

    @Autowired
    ItemsParamMapper itemsParamMapper;

    @Autowired
    ItemsCommentsMapper itemsCommentsMapper;

    @Override
    public Items queryItemById(String id) {
        return itemsMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<ItemsImg> queryItemsImgByItemId(String itemId) {
        Example itemsImgExample = new Example(ItemsImg.class);
        Example.Criteria itemsImgExampleCriteria = itemsImgExample.createCriteria();
        itemsImgExampleCriteria.andEqualTo("itemId", itemId);

        return itemsImgMapper.selectByExample(itemsImgExample);
    }

    @Override
    public List<ItemsSpec> queryItemsSpecByItemId(String itemId) {
        Example itemsSpecExample = new Example(ItemsSpec.class);
        Example.Criteria itemsSpecExampleCriteria = itemsSpecExample.createCriteria();
        itemsSpecExampleCriteria.andEqualTo("itemId", itemId);

        return itemsSpecMapper.selectByExample(itemsSpecExample);
    }

    @Override
    public ItemsParam queryItemsParamByItemId(String itemId) {
        Example itemsParamExample = new Example(ItemsParam.class);
        Example.Criteria itemsParamExampleCriteria = itemsParamExample.createCriteria();
        itemsParamExampleCriteria.andEqualTo("itemId", itemId);

        return itemsParamMapper.selectOneByExample(itemsParamExample);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public CommentsCountVO queryCommentCount(String itemId) {

        Integer goodCounts = getItemCommentsCount(itemId, CommentLevel.Good.type);
        Integer normalCounts = getItemCommentsCount(itemId, CommentLevel.Normal.type);
        Integer badCounts = getItemCommentsCount(itemId, CommentLevel.Bad.type);
        Integer totalCounts = goodCounts + normalCounts + badCounts;

        CommentsCountVO commentsCountVO = new CommentsCountVO();
        commentsCountVO.setTotalCounts(totalCounts);
        commentsCountVO.setGoodCounts(goodCounts);
        commentsCountVO.setNormalCounts(normalCounts);
        commentsCountVO.setBadCounts(badCounts);

        return commentsCountVO;
    }

    /**
     * 获取商品指定评价等级的评论数
     * @param itemId
     * @param commentsLevel
     * @return
     */
    private Integer getItemCommentsCount(String itemId, Integer commentsLevel){

        ItemsComments itemsComments = new ItemsComments();
        itemsComments.setItemId(itemId);
        itemsComments.setCommentLevel(commentsLevel);
        Integer commentsCount = itemsCommentsMapper.selectCount(itemsComments);

        return commentsCount;
    }

}
