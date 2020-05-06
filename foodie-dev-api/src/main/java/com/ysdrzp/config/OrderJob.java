package com.ysdrzp.config;

import com.ysdrzp.service.IOrdersService;
import com.ysdrzp.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 定时扫描超时未支付订单
 */
@Component
public class OrderJob {

    @Autowired
    private IOrdersService ordersService;

    //@Scheduled(cron = "0/5 * * * * ? ")
    @Scheduled(cron = "0 0 0/1 * * ? *")
    public void autoCloseOrder(){
        System.out.println("定时任务执行时间：" + DateUtil.getCurrentDateString(DateUtil.DATETIME_PATTERN));
        ordersService.closeOrder();
    }
}
