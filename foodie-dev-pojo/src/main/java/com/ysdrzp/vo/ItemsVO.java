package com.ysdrzp.vo;

import com.ysdrzp.pojo.Items;
import com.ysdrzp.pojo.ItemsImg;
import com.ysdrzp.pojo.ItemsParam;
import com.ysdrzp.pojo.ItemsSpec;
import lombok.Data;

import java.util.List;

@Data
public class ItemsVO {

    private Items item;

    private ItemsParam itemParams;

    private List<ItemsImg> itemImgList;

    private List<ItemsSpec> itemSpecList;
}
