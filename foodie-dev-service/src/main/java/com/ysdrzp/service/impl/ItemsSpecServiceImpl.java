package com.ysdrzp.service.impl;

import com.ysdrzp.mapper.ItemsSpecMapper;
import com.ysdrzp.pojo.ItemsSpec;
import com.ysdrzp.service.ItemsSpecService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class ItemsSpecServiceImpl implements ItemsSpecService {

    @Autowired
    ItemsSpecMapper itemsSpecMapper;

    @Override
    public List<ItemsSpec> queryItemsSpecByItemId(String itemId) {
        Example itemsSpecExample = new Example(ItemsSpec.class);
        Example.Criteria itemsSpecExampleCriteria = itemsSpecExample.createCriteria();
        itemsSpecExampleCriteria.andEqualTo("itemId", itemId);

        List<ItemsSpec> itemsSpecList = itemsSpecMapper.selectByExample(itemsSpecExample);
        return itemsSpecList;
    }

}
