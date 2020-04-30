package com.ysdrzp.service;

import com.ysdrzp.pojo.Items;

public interface ItemsService {

    /**
     * 根据商品 Id 获取商品信息
     * @param id
     * @return
     */
    Items queryItemById(String id);

}
