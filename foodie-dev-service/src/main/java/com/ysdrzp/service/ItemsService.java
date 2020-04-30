package com.ysdrzp.service;

import com.ysdrzp.pojo.Items;
import com.ysdrzp.pojo.ItemsImg;
import com.ysdrzp.pojo.ItemsParam;
import com.ysdrzp.pojo.ItemsSpec;
import java.util.List;

public interface ItemsService {

    /**
     * 根据商品Id获取商品信息
     * @param id
     * @return
     */
    Items queryItemById(String id);

    /**
     * 根据商品Id获取商品图片信息
     * @param itemId
     * @return
     */
    List<ItemsImg> queryItemsImgByItemId(String itemId);

    /**
     * 根据商品Id获取商品规格信息
     * @param itemId
     * @return
     */
    List<ItemsSpec> queryItemsSpecByItemId(String itemId);

    /**
     * 根据商品Id获取商品参数信息
     * @param itemId
     * @return
     */
    ItemsParam queryItemsParamByItemId(String itemId);

}
