package com.ysdrzp.service.center;

import com.ysdrzp.bo.center.OrderItemsCommentBO;
import com.ysdrzp.pojo.OrderItems;
import com.ysdrzp.utils.PagedGridResult;
import java.util.List;

/**
 * 评论管理
 */
public interface MyCommentsService {

    /**
     * 根据订单id查询关联的商品评论
     * @param orderId
     * @return
     */
    List<OrderItems> queryPendingComment(String orderId);

    /**
     * 保存用户的评论
     * @param orderId
     * @param userId
     * @param commentList
     */
    void saveComments(String orderId, String userId, List<OrderItemsCommentBO> commentList);

    /**
     * 我的评价查询 分页
     * @param userId
     * @param page
     * @param pageSize
     * @return
     */
    PagedGridResult queryMyComments(String userId, Integer page, Integer pageSize);
}
