package com.ysdrzp.vo;

import io.swagger.models.auth.In;
import lombok.Data;

/**
 *
 */
@Data
public class CommentsCountVO {

    /**
     * 总评价数
     */
    private Integer totalCounts;

    /**
     * 好评
     */
    private Integer goodCounts;

    /**
     * 中评
     */
    private Integer normalCounts;

    /**
     * 差评
     */
    private Integer badCounts;

}
