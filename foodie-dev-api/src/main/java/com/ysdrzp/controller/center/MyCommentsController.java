package com.ysdrzp.controller.center;

import com.ysdrzp.bo.center.OrderItemsCommentBO;
import com.ysdrzp.enums.YesOrNo;
import com.ysdrzp.pojo.OrderItems;
import com.ysdrzp.pojo.Orders;
import com.ysdrzp.service.center.MyCommentsService;
import com.ysdrzp.service.center.MyOrdersService;
import com.ysdrzp.utils.PagedGridResult;
import com.ysdrzp.utils.YSDRZPJSONResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "用户中心评价模块", tags = {"用户中心评价模块相关接口"})
@RestController
@RequestMapping("mycomments")
public class MyCommentsController {

    private static final int COMMON_PAGE_SIZE = 10;

    @Autowired
    private MyCommentsService myCommentsService;

    @Autowired
    private MyOrdersService myOrdersService;

    @ApiOperation(value = "查询订单列表", notes = "查询订单列表", httpMethod = "POST")
    @PostMapping("/pending")
    public YSDRZPJSONResult pending(@ApiParam(name = "userId", value = "用户id", required = true) @RequestParam String userId,
            @ApiParam(name = "orderId", value = "订单id", required = true) @RequestParam String orderId) {

        // 判断用户和订单是否关联
        YSDRZPJSONResult checkResult = checkUserOrder(userId, orderId);
        if (checkResult.getStatus() != HttpStatus.OK.value()) {
            return checkResult;
        }

        // 判断该笔订单是否已经评价过，评价过了就不再继续
        Orders myOrder = (Orders) checkResult.getData();
        if (myOrder.getIsComment() == YesOrNo.yes.type) {
            return YSDRZPJSONResult.errorMsg("该笔订单已经评价");
        }

        List<OrderItems> list = myCommentsService.queryPendingComment(orderId);
        return YSDRZPJSONResult.ok(list);
    }

    @ApiOperation(value = "保存评论列表", notes = "保存评论列表", httpMethod = "POST")
    @PostMapping("/saveList")
    public YSDRZPJSONResult saveList(@ApiParam(name = "userId", value = "用户id", required = true) @RequestParam String userId,
            @ApiParam(name = "orderId", value = "订单id", required = true) @RequestParam String orderId, @RequestBody List<OrderItemsCommentBO> commentList) {

        // 判断用户和订单是否关联
        YSDRZPJSONResult checkResult = checkUserOrder(userId, orderId);
        if (checkResult.getStatus() != HttpStatus.OK.value()) {
            return checkResult;
        }

        // 判断评论内容list不能为空
        if (commentList == null || commentList.isEmpty() || commentList.size() == 0) {
            return YSDRZPJSONResult.errorMsg("评论内容不能为空！");
        }

        myCommentsService.saveComments(orderId, userId, commentList);
        return YSDRZPJSONResult.ok();
    }

    @ApiOperation(value = "查询我的评价", notes = "查询我的评价", httpMethod = "POST")
    @PostMapping("/query")
    public YSDRZPJSONResult query(@ApiParam(name = "userId", value = "用户id", required = true) @RequestParam String userId,
            @ApiParam(name = "page", value = "查询下一页的第几页") @RequestParam Integer page,
            @ApiParam(name = "pageSize", value = "分页的每一页显示的条数") @RequestParam Integer pageSize) {

        if (StringUtils.isBlank(userId)) {
            return YSDRZPJSONResult.errorMsg(null);
        }

        if (page == null) {
            page = 1;
        }

        if (pageSize == null) {
            pageSize = COMMON_PAGE_SIZE;
        }

        PagedGridResult grid = myCommentsService.queryMyComments(userId, page, pageSize);
        return YSDRZPJSONResult.ok(grid);
    }

    /**
     * 用于验证用户和订单是否有关联关系
     * @param userId
     * @param orderId
     * @return
     */
    private YSDRZPJSONResult checkUserOrder(String userId, String orderId) {
        Orders order = myOrdersService.queryMyOrder(userId, orderId);
        if (order == null) {
            return YSDRZPJSONResult.errorMsg("订单不存在！");
        }
        return YSDRZPJSONResult.ok(order);
    }

}
