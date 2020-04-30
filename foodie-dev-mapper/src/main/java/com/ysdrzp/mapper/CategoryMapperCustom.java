package com.ysdrzp.mapper;

import com.ysdrzp.vo.CategoryVO;
import com.ysdrzp.vo.SixItemVO;

import java.util.List;

/**
 * 自定义分类 Mapper
 */
public interface CategoryMapperCustom {

    /**
     * 根据 一级分类 Id 获取子类信息
     * @param rootCatId
     * @return
     */
    List<CategoryVO> getSubCatList(Integer rootCatId);

    /**
     * 获取首页商品推荐信息
     * @param rootCatId
     * @return
     */
    List<SixItemVO> getSixNewItemsLazy(Integer rootCatId);

}