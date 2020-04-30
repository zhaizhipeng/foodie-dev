package com.ysdrzp.service;

import com.ysdrzp.pojo.Carousel;

import java.util.List;

/**
 * 轮播图接口
 */
public interface ICarouselService {

    /**
     * 查询首页轮播图列表
     * @return
     */
    List<Carousel> queryAll();
}
