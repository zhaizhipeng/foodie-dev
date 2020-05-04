package com.ysdrzp.mapper;

import com.ysdrzp.vo.center.MyCommentVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 评论管理-自定义mapper
 */
public interface ItemsCommentsMapperCustom{

    /**
     * 保存评论
     * @param map
     */
    void saveComments(Map<String, Object> map);

    /**
     * 分页查询评论列表
     * @param map
     * @return
     */
    List<MyCommentVO> queryMyComments(@Param("paramsMap") Map<String, Object> map);

}