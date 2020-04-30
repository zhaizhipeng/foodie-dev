package com.ysdrzp.service.impl;

import com.ysdrzp.enums.YesOrNo;
import com.ysdrzp.mapper.CarouselMapper;
import com.ysdrzp.pojo.Carousel;
import com.ysdrzp.service.ICarouselService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * 轮播图接口实现类
 * @author ysdrzp
 */
@Service
public class CarouselServiceImpl implements ICarouselService {

    @Autowired
    CarouselMapper carouselMapper;

    @Override
    public List<Carousel> queryAll() {
        Example carouselExample = new Example(Carousel.class);
        Example.Criteria carouselCriteria = carouselExample.createCriteria();
        carouselCriteria.andEqualTo("isShow", YesOrNo.yes.type);

        List<Carousel> list = carouselMapper.selectByExample(carouselExample);
        return list;
    }

}
