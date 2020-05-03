package com.ysdrzp.service.impl;

import com.ysdrzp.bo.SubmitOrderBO;
import com.ysdrzp.enums.OrderStatusEnum;
import com.ysdrzp.enums.YesOrNo;
import com.ysdrzp.mapper.OrderItemsMapper;
import com.ysdrzp.mapper.OrderStatusMapper;
import com.ysdrzp.mapper.OrdersMapper;
import com.ysdrzp.pojo.*;
import com.ysdrzp.service.IOrdersService;
import com.ysdrzp.service.IUserAddressService;
import com.ysdrzp.service.ItemsService;
import com.ysdrzp.utils.DateUtil;
import com.ysdrzp.vo.MerchantOrdersVO;
import com.ysdrzp.vo.OrderVO;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class OrdersServiceImpl implements IOrdersService {

    @Autowired
    private OrdersMapper ordersMapper;

    @Autowired
    private OrderItemsMapper orderItemsMapper;

    @Autowired
    private IUserAddressService addressService;

    @Autowired
    private ItemsService itemsService;

    @Autowired
    private OrderStatusMapper orderStatusMapper;

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public OrderVO createOrder(SubmitOrderBO submitOrderBO) {

        String userId = submitOrderBO.getUserId();
        String addressId = submitOrderBO.getAddressId();
        String itemSpecIds = submitOrderBO.getItemSpecIds();
        Integer payMethod = submitOrderBO.getPayMethod();
        String leftMsg = submitOrderBO.getLeftMsg();
        // 包邮费用设置为0
        Integer postAmount = 0;

        String orderId = DateUtil.generateId();

        UserAddress address = addressService.queryUserAddres(userId, addressId);

        // 1. 新订单数据保存
        Orders newOrder = new Orders();
        newOrder.setId(orderId);
        newOrder.setUserId(userId);

        newOrder.setReceiverName(address.getReceiver());
        newOrder.setReceiverMobile(address.getMobile());
        newOrder.setReceiverAddress(address.getProvince() + " " + address.getCity() + " "
                + address.getDistrict() + " " + address.getDetail());

        newOrder.setPostAmount(postAmount);
        newOrder.setPayMethod(payMethod);
        newOrder.setLeftMsg(leftMsg);
        newOrder.setIsComment(YesOrNo.no.type);
        newOrder.setIsDelete(YesOrNo.no.type);
        newOrder.setCreatedTime(new Date());
        newOrder.setUpdatedTime(new Date());

        // 2. 循环根据itemSpecIds保存订单商品信息表
        String itemSpecIdArr[] = itemSpecIds.split(",");
        Integer totalAmount = 0;    // 商品原价累计
        Integer realPayAmount = 0;  // 优惠后的实际支付价格累计
        for (String itemSpecId : itemSpecIdArr) {
            // TODO 整合redis后，商品购买的数量重新从redis的购物车中获取
            int buyCounts = 1;

            // 2.1 根据规格id，查询规格的具体信息，主要获取价格
            ItemsSpec itemSpec = itemsService.queryItemSpecById(itemSpecId);
            totalAmount += itemSpec.getPriceNormal() * buyCounts;
            realPayAmount += itemSpec.getPriceDiscount() * buyCounts;

            // 2.2 根据商品id，获得商品信息以及商品图片
            String itemId = itemSpec.getItemId();
            Items item = itemsService.queryItemById(itemId);
            String imgUrl = itemsService.queryItemMainImgById(itemId);

            // 2.3 循环保存子订单数据到数据库
            String subOrderId = DateUtil.generateId();
            OrderItems subOrderItem = new OrderItems();
            subOrderItem.setId(subOrderId);
            subOrderItem.setOrderId(orderId);
            subOrderItem.setItemId(itemId);
            subOrderItem.setItemName(item.getItemName());
            subOrderItem.setItemImg(imgUrl);
            subOrderItem.setBuyCounts(buyCounts);
            subOrderItem.setItemSpecId(itemSpecId);
            subOrderItem.setItemSpecName(itemSpec.getName());
            subOrderItem.setPrice(itemSpec.getPriceDiscount());
            orderItemsMapper.insert(subOrderItem);

            // 2.4 在用户提交订单以后，规格表中需要扣除库存
            itemsService.decreaseItemSpecStock(itemSpecId, buyCounts);
        }

        newOrder.setTotalAmount(totalAmount);
        newOrder.setRealPayAmount(realPayAmount);
        ordersMapper.insert(newOrder);

        // 3. 保存订单状态表
        OrderStatus waitPayOrderStatus = new OrderStatus();
        waitPayOrderStatus.setOrderId(orderId);
        waitPayOrderStatus.setOrderStatus(OrderStatusEnum.WAIT_PAY.type);
        waitPayOrderStatus.setCreatedTime(new Date());
        orderStatusMapper.insert(waitPayOrderStatus);

        // 4. 构建商户订单，用于传给支付中心
        MerchantOrdersVO merchantOrdersVO = new MerchantOrdersVO();
        merchantOrdersVO.setMerchantOrderId(orderId);
        merchantOrdersVO.setMerchantUserId(userId);
        merchantOrdersVO.setAmount(realPayAmount + postAmount);
        merchantOrdersVO.setPayMethod(payMethod);

        // 5. 构建自定义订单vo
        OrderVO orderVO = new OrderVO();
        orderVO.setOrderId(orderId);
        orderVO.setMerchantOrdersVO(merchantOrdersVO);

        orderVO.setOrderId(orderId);
        return orderVO;
    }

    @Override
    public void updateOrderStatus(String merchantOrderId, Integer status) {
        OrderStatus orderStatus = new OrderStatus();
        orderStatus.setOrderId(merchantOrderId);
        orderStatus.setOrderStatus(status);
        orderStatusMapper.updateByPrimaryKey(orderStatus);
    }

    @Override
    public OrderStatus queryOrderStatusInfo(String orderId) {
        OrderStatus orderStatus = new OrderStatus();
        orderStatus.setOrderId(orderId);
        orderStatus = orderStatusMapper.selectByPrimaryKey(orderStatus);
        return orderStatus;
    }

}
