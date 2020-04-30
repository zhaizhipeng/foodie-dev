package com.ysdrzp.service;

import com.ysdrzp.pojo.ItemsParam;

public interface ItemsParamService {

    /**
     * 根据商品 Id 获取商品参数信息
     * @param itemId
     * @return
     */
    ItemsParam queryItemsParamByItemId(String itemId);
}
