package com.ysdrzp.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ysdrzp.enums.CommentLevel;
import com.ysdrzp.mapper.*;
import com.ysdrzp.pojo.*;
import com.ysdrzp.service.ItemsService;
import com.ysdrzp.utils.DesensitizationUtil;
import com.ysdrzp.utils.PagedGridResult;
import com.ysdrzp.vo.CommentsCountVO;
import com.ysdrzp.vo.ItemCommentVO;
import com.ysdrzp.vo.ItemSearchVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Autowired
    ItemsMapperCustom itemsMapperCustom;

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

    @Override
    public PagedGridResult queryItemComment(String itemId, Integer level, Integer page, Integer pageSize) {

        PageHelper.startPage(page, pageSize);

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("itemId", itemId);
        paramMap.put("level", level);
        List<ItemCommentVO> list = itemsMapperCustom.queryItemComment(paramMap);
        for (ItemCommentVO itemCommentVO : list){
            // 昵称脱敏
            itemCommentVO.setNickname(DesensitizationUtil.commonDisplay(itemCommentVO.getNickname()));
        }
        return setterPagedGridResult(list, page);
    }

    @Override
    public PagedGridResult pagedQueryItemByKeyWord(String keywords, String sort, Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("keywords", keywords);
        paramMap.put("sort", sort);
        List<ItemSearchVO> list = itemsMapperCustom.searchItemByKeyWord(paramMap);
        return setterPagedGridResult(list, page);
    }

    /**
     * 封装分页结果
     * @param list
     * @param page
     * @return
     */
    private PagedGridResult setterPagedGridResult(List<?> list, Integer page){
        PageInfo<?> pageInfo = new PageInfo<>(list);

        PagedGridResult pagedGridResult = new PagedGridResult();
        pagedGridResult.setPage(page);
        pagedGridResult.setRows(list);
        // 总记录数
        pagedGridResult.setRecords(pageInfo.getTotal());
        // 总页数
        pagedGridResult.setTotal(pageInfo.getPages());
        return pagedGridResult;
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
