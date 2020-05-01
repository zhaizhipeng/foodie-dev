package com.ysdrzp.service;

import com.ysdrzp.pojo.Items;
import com.ysdrzp.pojo.ItemsImg;
import com.ysdrzp.pojo.ItemsParam;
import com.ysdrzp.pojo.ItemsSpec;
import com.ysdrzp.utils.PagedGridResult;
import com.ysdrzp.vo.CommentsCountVO;
import com.ysdrzp.vo.ShopCartVO;

import java.util.List;

public interface ItemsService {

    /**
     * 根据商品Id获取商品信息
     * @param id
     * @return
     */
    Items queryItemById(String id);

    /**
     * 根据商品Id获取商品图片信息
     * @param itemId
     * @return
     */
    List<ItemsImg> queryItemsImgByItemId(String itemId);

    /**
     * 根据商品Id获取商品规格信息
     * @param itemId
     * @return
     */
    List<ItemsSpec> queryItemsSpecByItemId(String itemId);

    /**
     * 根据商品Id获取商品参数信息
     * @param itemId
     * @return
     */
    ItemsParam queryItemsParamByItemId(String itemId);

    /**
     * 统计商品评价等级
     * @param itemId
     * @return
     */
    CommentsCountVO queryCommentCount(String itemId);

    /**
     * 分页查询商品评论列表
     * @param itemId
     * @param level
     * @param page
     * @param pageSize
     * @return
     */
    PagedGridResult queryItemComment(String itemId, Integer level, Integer page, Integer pageSize);

    /**
     * 根据关键词分页查询商品
     * @param keywords
     * @param sort
     * @param page
     * @param pageSize
     * @return
     */
    PagedGridResult pagedQueryItemByKeyWord(String keywords, String sort, Integer page, Integer pageSize);

    /**
     * 根据三级分类分页查询商品
     * @param catId
     * @param sort
     * @param page
     * @param pageSize
     * @return
     */
    PagedGridResult pagedQueryItemByThirdCat(String catId, String sort, Integer page, Integer pageSize);

    /**
     * 获取规格下最新商品信息
     * @param itemSpecIds
     * @return
     */
    List<ShopCartVO> queryItemsBySpecIds(String itemSpecIds);

}
