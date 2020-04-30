package com.ysdrzp.service;

import com.ysdrzp.pojo.ItemsImg;

import java.util.List;

public interface ItemsImgService {

    /**
     * 根据商品 Id 获取商品图片信息列表
     * @param itemId
     * @return
     */
    List<ItemsImg> queryItemsImgByItemId(String itemId);
}
