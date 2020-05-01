package com.ysdrzp.controller;

import com.ysdrzp.bo.SubmitOrderBO;
import com.ysdrzp.enums.PayMethod;
import com.ysdrzp.service.IOrdersService;
import com.ysdrzp.utils.JsonUtils;
import com.ysdrzp.utils.YSDRZPJSONResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Api(value = "订单模块", tags = {"订单 API"})
@RestController
@RequestMapping("orders")
public class OrdersController {

    @Autowired
    IOrdersService ordersService;

    @ApiOperation(value = "创建订单", notes = "created by @ysdrzp", httpMethod = "POST")
    @PostMapping("/create")
    public YSDRZPJSONResult create(@RequestBody SubmitOrderBO submitOrderBO, HttpServletRequest request, HttpServletResponse response){

        System.out.println(JsonUtils.objectToJson(submitOrderBO));

        if (submitOrderBO.getPayMethod() != PayMethod.WEIXIN.type && submitOrderBO.getPayMethod() != PayMethod.ALIPAY.type ) {
            return YSDRZPJSONResult.errorMsg("支付方式不支持!");
        }

        // 1. 创建订单
        String orderId = ordersService.createOrder(submitOrderBO);

        // 2. 创建订单以后，移除购物车中已结算（已提交）的商品
        // TODO 整合redis之后，完善购物车中的已结算商品清除，并且同步到前端的cookie
        // CookieUtils.setCookie(request, response, FOODIE_SHOPCART, "", true);

        return YSDRZPJSONResult.ok(orderId);
    }

}
