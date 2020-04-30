package com.ysdrzp.service;

import com.ysdrzp.pojo.ItemsSpec;

import java.util.List;

public interface ItemsSpecService {

    /**
     * 根据商品 Id 获取商品规格信息列表
     * @param itemId
     * @return
     */
    List<ItemsSpec> queryItemsSpecByItemId(String itemId);
}
