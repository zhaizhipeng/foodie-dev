package com.ysdrzp.service;

import com.ysdrzp.pojo.Category;
import com.ysdrzp.vo.CategoryVO;
import java.util.List;

/**
 * 首页分类接口
 */
public interface ICategoryService {

    /**
     * 查询所有一级分类
     * @return
     */
    List<Category> queryAllRootLevelCat();

    /**
     * 根据一级分类id查询子分类信息
     * @param rootCatId
     * @return
     */
    List<CategoryVO> getSubCatList(Integer rootCatId);

}
