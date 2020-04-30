package com.ysdrzp.controller;

import com.ysdrzp.pojo.*;
import com.ysdrzp.service.*;
import com.ysdrzp.utils.JsonUtils;
import com.ysdrzp.utils.PagedGridResult;
import com.ysdrzp.utils.YSDRZPJSONResult;
import com.ysdrzp.vo.CommentsCountVO;
import com.ysdrzp.vo.ItemsVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "商品信息", tags = {"商品信息"})
@RestController
@RequestMapping("items")
public class ItemsController {

    @Autowired
    ItemsService itemsService;

    @ApiOperation(value = "获取商品详情信息", notes = "created by @ysdrzp", httpMethod = "GET")
    @GetMapping("/info/{itemId}")
    public YSDRZPJSONResult info(@ApiParam(value = "商品Id", name = "itemId", required = true) @PathVariable String itemId){

        if (StringUtils.isBlank(itemId)){
            return YSDRZPJSONResult.errorMsg(null);
        }

        Items items = itemsService.queryItemById(itemId);

        ItemsParam itemsParam = itemsService.queryItemsParamByItemId(itemId);

        List<ItemsImg> itemsImgs = itemsService.queryItemsImgByItemId(itemId);

        List<ItemsSpec> itemsSpecs = itemsService.queryItemsSpecByItemId(itemId);

        ItemsVO itemsVO = new ItemsVO();
        itemsVO.setItem(items);
        itemsVO.setItemParams(itemsParam);
        itemsVO.setItemImgList(itemsImgs);
        itemsVO.setItemSpecList(itemsSpecs);

        return YSDRZPJSONResult.ok(itemsVO);
    }

    @ApiOperation(value = "获取商品评价等级", notes = "created by @ysdrzp", httpMethod = "GET")
    @GetMapping("/commentLevel")
    public YSDRZPJSONResult commentLevel(@ApiParam(value = "商品Id", name = "itemId", required = true) @RequestParam String itemId){

        if (StringUtils.isBlank(itemId)){
            return YSDRZPJSONResult.errorMsg(null);
        }

        CommentsCountVO commentsCountVO = itemsService.queryCommentCount(itemId);
        return YSDRZPJSONResult.ok(commentsCountVO);
    }

    @ApiOperation(value = "分页获取商品评价列表", notes = "created by @ysdrzp", httpMethod = "GET")
    @GetMapping("/comments")
    public YSDRZPJSONResult comments(@ApiParam(value = "商品Id", name = "itemId", required = true) @RequestParam String itemId,
                                     @ApiParam(value = "评价等级", name = "level") @RequestParam Integer level,
                                     @ApiParam(value = "第几页", name = "page") @RequestParam Integer page,
                                     @ApiParam(value = "每页显示的记录数", name = "pageSize") @RequestParam Integer pageSize){

        if (StringUtils.isBlank(itemId)){
            return YSDRZPJSONResult.errorMsg(null);
        }

        if (page == null){
            page = 1;
        }

        if (pageSize == null){
            pageSize = 20;
        }

        PagedGridResult pagedGridResult = itemsService.queryItemComment(itemId, level, page, pageSize);
        System.out.println(JsonUtils.objectToJson(pagedGridResult));
        return YSDRZPJSONResult.ok(pagedGridResult);
    }

    @ApiOperation(value = "根据关键词搜索商品", notes = "created by @ysdrzp", httpMethod = "GET")
    @GetMapping("/search")
    public YSDRZPJSONResult search(@ApiParam(value = "搜索关键词", name = "keywords", required = true) @RequestParam String keywords,
                                     @ApiParam(value = "排序", name = "sort") @RequestParam String sort,
                                     @ApiParam(value = "第几页", name = "page") @RequestParam Integer page,
                                     @ApiParam(value = "每页显示的记录数", name = "pageSize") @RequestParam Integer pageSize){

        if (StringUtils.isBlank(keywords)){
            return YSDRZPJSONResult.errorMsg(null);
        }

        if (page == null){
            page = 1;
        }

        if (pageSize == null){
            pageSize = 20;
        }

        PagedGridResult pagedGridResult = itemsService.pagedQueryItemByKeyWord(keywords, sort, page, pageSize);
        return YSDRZPJSONResult.ok(pagedGridResult);
    }

    @ApiOperation(value = "根据三级分类Id搜索商品", notes = "created by @ysdrzp", httpMethod = "GET")
    @GetMapping("/catItems")
    public YSDRZPJSONResult catItems(@ApiParam(value = "三级分类Id", name = "catId", required = true) @RequestParam String catId,
                                   @ApiParam(value = "排序", name = "sort") @RequestParam String sort,
                                   @ApiParam(value = "第几页", name = "page") @RequestParam Integer page,
                                   @ApiParam(value = "每页显示的记录数", name = "pageSize") @RequestParam Integer pageSize){

        if (StringUtils.isBlank(catId)){
            return YSDRZPJSONResult.errorMsg(null);
        }

        if (page == null){
            page = 1;
        }

        if (pageSize == null){
            pageSize = 20;
        }

        PagedGridResult pagedGridResult = itemsService.pagedQueryItemByThirdCat(catId, sort, page, pageSize);
        return YSDRZPJSONResult.ok(pagedGridResult);
    }
}
