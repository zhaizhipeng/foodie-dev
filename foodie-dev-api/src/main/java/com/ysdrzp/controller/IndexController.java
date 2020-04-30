package com.ysdrzp.controller;

import com.ysdrzp.pojo.Carousel;
import com.ysdrzp.pojo.Category;
import com.ysdrzp.service.ICarouselService;
import com.ysdrzp.service.ICategoryService;
import com.ysdrzp.utils.YSDRZPJSONResult;
import com.ysdrzp.vo.CategoryVO;
import com.ysdrzp.vo.SixItemVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@Api(value = "首页", tags = {"首页相关接口"}, hidden = true)
@RestController
@RequestMapping("index")
public class IndexController {

    @Autowired
    ICarouselService carouselService;

    @Autowired
    ICategoryService categoryService;

    @ApiOperation(value = "获取首页轮播图列表", notes = "created by @ysdrzp", httpMethod = "GET")
    @GetMapping("/carousel")
    public YSDRZPJSONResult carousel(){

        List<Carousel> list = carouselService.queryAll();
        return YSDRZPJSONResult.ok(list);
    }

    @ApiOperation(value = "获取首页商品分类-大分类", notes = "created by @ysdrzp", httpMethod = "GET")
    @GetMapping("/cats")
    public YSDRZPJSONResult cats(){

        List<Category> list = categoryService.queryAllRootLevelCat();
        return YSDRZPJSONResult.ok(list);
    }

    @ApiOperation(value = "获取首页商品分类-子类别", notes = "created by @ysdrzp", httpMethod = "GET")
    @GetMapping("/subCat/{rootCatId}")
    public YSDRZPJSONResult subCat(@PathVariable Integer rootCatId){

        List<CategoryVO> list = categoryService.getSubCatList(rootCatId);
        return YSDRZPJSONResult.ok(list);
    }

    @ApiOperation(value = "获取首页推荐商品", notes = "created by @ysdrzp", httpMethod = "GET")
    @GetMapping("/sixNewItems/{rootCatId}")
    public YSDRZPJSONResult sixNewItems(@PathVariable Integer rootCatId){

        List<SixItemVO> list = categoryService.getSixNewItemsLazy(rootCatId);
        return YSDRZPJSONResult.ok(list);
    }

}
