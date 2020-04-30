package com.ysdrzp.service.impl;

import com.ysdrzp.mapper.ItemsParamMapper;
import com.ysdrzp.pojo.ItemsParam;
import com.ysdrzp.service.ItemsParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

@Service
public class ItemsParamServiceImpl implements ItemsParamService {

    @Autowired
    ItemsParamMapper itemsParamMapper;

    @Override
    public ItemsParam queryItemsParamByItemId(String itemId) {
        Example itemsParamExample = new Example(ItemsParam.class);
        Example.Criteria itemsParamExampleCriteria = itemsParamExample.createCriteria();
        itemsParamExampleCriteria.andEqualTo("itemId", itemId);

        ItemsParam itemsParam = itemsParamMapper.selectOneByExample(itemsParamExample);
        return itemsParam;
    }

}
