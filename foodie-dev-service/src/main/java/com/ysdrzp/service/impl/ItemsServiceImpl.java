package com.ysdrzp.service.impl;

import com.ysdrzp.mapper.ItemsMapper;
import com.ysdrzp.pojo.Items;
import com.ysdrzp.service.ItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

@Service
public class ItemsServiceImpl implements ItemsService {

    @Autowired
    ItemsMapper itemsMapper;

    @Override
    public Items queryItemById(String id) {
        Example itemsExample = new Example(Items.class);
        Example.Criteria itemsExampleCriteria = itemsExample.createCriteria();
        itemsExampleCriteria.andEqualTo("id", id);

        Items items = itemsMapper.selectOneByExample(itemsExample);
        return items;
    }
}
