package com.ysdrzp.controller;

import com.ysdrzp.pojo.Carousel;
import com.ysdrzp.service.ICarouselService;
import com.ysdrzp.utils.YSDRZPJSONResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@Api(value = "首页", tags = {"首页相关接口"}, hidden = true)
@RestController
@RequestMapping("index")
public class IndexController {

    @Autowired
    ICarouselService carouselService;

    @ApiOperation(value = "获取首页轮播图列表", notes = "created by @ysdrzp", httpMethod = "GET")
    @GetMapping("/carousel")
    public YSDRZPJSONResult carousel(){

        List<Carousel> list = carouselService.queryAll();
        return YSDRZPJSONResult.ok(list);
    }

}
