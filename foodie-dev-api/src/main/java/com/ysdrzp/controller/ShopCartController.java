package com.ysdrzp.controller;

import com.ysdrzp.bo.ShopCartBO;
import com.ysdrzp.utils.YSDRZPJSONResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Api(value = "购物车", tags = {"购物车接口 API"})
@RestController
public class ShopCartController {

    @ApiOperation(value = "商品添加到购物车", tags = {"将商品添加到购物车"}, httpMethod = "POST")
    @PostMapping("/add")
    public YSDRZPJSONResult add(@RequestParam String userId, @RequestBody ShopCartBO shopCartBO,
                                HttpServletRequest request, HttpServletResponse response){

        if (StringUtils.isBlank(userId)){
            return YSDRZPJSONResult.errorMsg("参数不能为空");
        }

        // TODO: 2020/5/1 0001
        // 用户登录情况下，前端页面选择商品加入购物车，需要将购物车信息同步刷新到 Redis 缓存

        return YSDRZPJSONResult.ok();
    }

    @ApiOperation(value = "商品从购物车移除", tags = {"将商品从购物车移除"}, httpMethod = "GET")
    @GetMapping("/delete")
    public YSDRZPJSONResult delete(@RequestParam String userId, @ApiParam(value = "商品规格 ids", name = "itemSpecId", required = true) @RequestParam String itemSpecId, HttpServletRequest request, HttpServletResponse response){

        if (StringUtils.isBlank(userId) || StringUtils.isBlank(itemSpecId)){
            return YSDRZPJSONResult.errorMsg("参数不能为空");
        }

        // TODO: 2020/5/1 0001
        // 用户登录情况下，前端页面选择将商品从购物车中移除，需要同步删除 Redis 中的购物车信息

        return YSDRZPJSONResult.ok();
    }

}
