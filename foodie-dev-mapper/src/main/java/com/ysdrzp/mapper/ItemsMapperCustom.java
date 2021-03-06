package com.ysdrzp.mapper;

import com.ysdrzp.vo.ItemCommentVO;
import com.ysdrzp.vo.ItemSearchVO;
import com.ysdrzp.vo.ShopCartVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 自定义商品mapper
 */
public interface ItemsMapperCustom{

    /**
     * 查询商品评价列表
     * @param paramMap
     * @return
     */
    List<ItemCommentVO> queryItemComment(@Param("paramMap") Map<String, Object> paramMap);

    /**
     * 关键词查询商品
     * @param paramMap
     * @return
     */
    List<ItemSearchVO> searchItemByKeyWord(@Param("paramMap") Map<String, Object> paramMap);

    /**
     * 三级分类查询商品
     * @param paramMap
     * @return
     */
    List<ItemSearchVO> searchItemByThirdCat(@Param("paramMap") Map<String, Object> paramMap);

    /**
     * 获取规格最新商品信息
     * @param specIdsList
     * @return
     */
    List<ShopCartVO> queryItemsBySpecIds(@Param("specIdsList") List<String> specIdsList);

    /**
     * 乐观锁思想-扣减库存
     * @param specId
     * @param pendingCounts
     * @return
     */
    int decreaseItemSpecStock(@Param("specId") String specId, @Param("pendingCounts") int pendingCounts);
}