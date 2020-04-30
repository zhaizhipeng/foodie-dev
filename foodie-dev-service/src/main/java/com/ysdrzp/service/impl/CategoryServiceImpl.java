package com.ysdrzp.service.impl;

import com.ysdrzp.mapper.CategoryMapper;
import com.ysdrzp.mapper.CategoryMapperCustom;
import com.ysdrzp.pojo.Category;
import com.ysdrzp.service.ICategoryService;
import com.ysdrzp.vo.CategoryVO;
import com.ysdrzp.vo.SixItemVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * 首页分类-实现类
 */
@Service
public class CategoryServiceImpl implements ICategoryService {

    @Autowired
    CategoryMapper categoryMapper;

    @Autowired
    CategoryMapperCustom categoryMapperCustom;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<Category> queryAllRootLevelCat() {
        Example categoryExample = new Example(Category.class);
        Example.Criteria categoryCriteria = categoryExample.createCriteria();
        categoryCriteria.andEqualTo("type", 1);
        List<Category> list = categoryMapper.selectByExample(categoryExample);
        return list;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<CategoryVO> getSubCatList(Integer rootCatId) {
        return categoryMapperCustom.getSubCatList(rootCatId);
    }

    @Override
    public List<SixItemVO> getSixNewItemsLazy(Integer rootCatId) {
        return categoryMapperCustom.getSixNewItemsLazy(rootCatId);
    }

}
