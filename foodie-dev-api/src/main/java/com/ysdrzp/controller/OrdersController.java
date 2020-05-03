package com.ysdrzp.controller;

import com.ysdrzp.bo.SubmitOrderBO;
import com.ysdrzp.enums.OrderStatusEnum;
import com.ysdrzp.enums.PayMethod;
import com.ysdrzp.pojo.OrderStatus;
import com.ysdrzp.service.IOrdersService;
import com.ysdrzp.utils.JsonUtils;
import com.ysdrzp.utils.YSDRZPJSONResult;
import com.ysdrzp.vo.MerchantOrdersVO;
import com.ysdrzp.vo.OrderVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Api(value = "订单模块", tags = {"订单 API"})
@RestController
@RequestMapping("orders")
public class OrdersController {

    private static Logger logger = LoggerFactory.getLogger(OrdersController.class);

    /**
     * 支付中心通知后台更新订单状态地址
     */
    private static final String PAY_RETURN_URL = "http://localhost:8088/orders/notifyMerchantOrderPaid";

    /**
     * 支付中心下单地址
     */
    private static final String PAYMENT_URL = "http://localhost:8089/payment/createMerchantOrder";

    @Autowired
    IOrdersService ordersService;

    @Autowired
    RestTemplate restTemplate;

    @ApiOperation(value = "创建订单", notes = "created by @ysdrzp", httpMethod = "POST")
    @PostMapping("/create")
    public YSDRZPJSONResult create(@RequestBody SubmitOrderBO submitOrderBO, HttpServletRequest request, HttpServletResponse response){

        System.out.println(JsonUtils.objectToJson(submitOrderBO));

        if (submitOrderBO.getPayMethod() != PayMethod.WEIXIN.type && submitOrderBO.getPayMethod() != PayMethod.ALIPAY.type ) {
            return YSDRZPJSONResult.errorMsg("支付方式不支持!");
        }

        // 1. 创建订单
        OrderVO orderVO = ordersService.createOrder(submitOrderBO);

        // 2. 创建订单以后，移除购物车中已结算（已提交）的商品
        // TODO 整合redis之后，完善购物车中的已结算商品清除，并且同步到前端的cookie
        // CookieUtils.setCookie(request, response, FOODIE_SHOPCART, "", true);

        // 3. 向支付中心发送当前订单，用于保存支付中心的订单数据
        MerchantOrdersVO merchantOrdersVO = orderVO.getMerchantOrdersVO();
        merchantOrdersVO.setReturnUrl(PAY_RETURN_URL);

        // 为了方便测试购买，所以所有的支付金额都统一改为1分钱
        merchantOrdersVO.setAmount(1);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("imoocUserId","imooc");
        headers.add("password","imooc");

        HttpEntity<MerchantOrdersVO> entity = new HttpEntity<>(merchantOrdersVO, headers);

        ResponseEntity<YSDRZPJSONResult> responseEntity = restTemplate.postForEntity(PAYMENT_URL, entity, YSDRZPJSONResult.class);

        YSDRZPJSONResult paymentResult = responseEntity.getBody();
        if (paymentResult.getStatus() != 200) {
            logger.error("发送错误：{}", paymentResult.getMsg());
            return YSDRZPJSONResult.errorMsg("支付中心订单创建失败，请联系管理员！");
        }

        return YSDRZPJSONResult.ok(orderVO.getOrderId());
    }

    /**
     * 支付中心回调通知-更新订单状态
     * @param merchantOrderId
     * @return
     */
    @PostMapping("notifyMerchantOrderPaid")
    public Integer notifyMerchantOrderPaid(String merchantOrderId) {
        ordersService.updateOrderStatus(merchantOrderId, OrderStatusEnum.WAIT_DELIVER.type);
        return HttpStatus.OK.value();
    }

    /**
     * 查询订单交易状态 - 用于支付成功页面跳转
     * @param orderId
     * @return
     */
    @PostMapping("getPaidOrderInfo")
    public YSDRZPJSONResult getPaidOrderInfo(String orderId) {

        OrderStatus orderStatus = ordersService.queryOrderStatusInfo(orderId);
        return YSDRZPJSONResult.ok(orderStatus);
    }

}
