package com.ysdrzp.mapper;

import com.ysdrzp.vo.ItemCommentVO;
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
}