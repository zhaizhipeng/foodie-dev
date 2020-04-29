package com.ysdrzp.service.impl;

import com.ysdrzp.mapper.StuMapper;
import com.ysdrzp.pojo.Stu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransactionService {

    @Autowired
    StuMapper stuMapper;

    @Autowired
    SubTransactionService subTransactionService;

    @Transactional
    public void insert(){
        Stu stu = new Stu();
        stu.setName("parent");
        stu.setAge(50);
        stuMapper.insert(stu);
        subTransactionService.insert();
        //int a = 1 / 0;
    }

}
