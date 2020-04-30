package com.ysdrzp.service.impl;

import com.ysdrzp.mapper.ItemsImgMapper;
import com.ysdrzp.pojo.ItemsImg;
import com.ysdrzp.service.ItemsImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class ItemsImgServiceImpl implements ItemsImgService {

    @Autowired
    ItemsImgMapper itemsImgMapper;

    @Override
    public List<ItemsImg> queryItemsImgByItemId(String itemId) {
        Example itemsImgExample = new Example(ItemsImg.class);
        Example.Criteria itemsImgExampleCriteria = itemsImgExample.createCriteria();
        itemsImgExampleCriteria.andEqualTo("itemId", itemId);

        List<ItemsImg> list = itemsImgMapper.selectByExample(itemsImgExample);
        return list;
    }
}
