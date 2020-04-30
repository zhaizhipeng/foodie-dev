package com.ysdrzp.mapper;

import com.ysdrzp.vo.CategoryVO;

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

}