package com.ysdrzp.controller;

import com.ysdrzp.pojo.*;
import com.ysdrzp.service.*;
import com.ysdrzp.utils.YSDRZPJSONResult;
import com.ysdrzp.vo.ItemsVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(value = "商品详情", tags = {"商品详情信息"})
@RestController
@RequestMapping("items")
public class ItemsController {

    @Autowired
    ItemsService itemsService;

    @Autowired
    ItemsImgService itemsImgService;

    @Autowired
    ItemsSpecService itemsSpecService;

    @Autowired
    ItemsParamService itemsParamService;


    @ApiOperation(value = "获取商品详情信息", notes = "created by @ysdrzp", httpMethod = "GET")
    @GetMapping("/info/{itemId}")
    public YSDRZPJSONResult info(@PathVariable String itemId){

        if (StringUtils.isBlank(itemId)){
            return YSDRZPJSONResult.errorMsg("商品 Id 不能为空");
        }

        Items items = itemsService.queryItemById(itemId);

        ItemsParam itemsParam = itemsParamService.queryItemsParamByItemId(itemId);

        List<ItemsImg> itemsImgs = itemsImgService.queryItemsImgByItemId(itemId);

        List<ItemsSpec> itemsSpecs = itemsSpecService.queryItemsSpecByItemId(itemId);

        ItemsVO itemsVO = new ItemsVO();
        itemsVO.setItem(items);
        itemsVO.setItemParams(itemsParam);
        itemsVO.setItemImgList(itemsImgs);
        itemsVO.setItemSpecList(itemsSpecs);

        return YSDRZPJSONResult.ok(itemsVO);
    }

}
