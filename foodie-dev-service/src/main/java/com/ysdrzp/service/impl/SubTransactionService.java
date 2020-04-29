package com.ysdrzp.service.impl;

import com.ysdrzp.mapper.StuMapper;
import com.ysdrzp.pojo.Stu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SubTransactionService {

    @Autowired
    StuMapper stuMapper;

    @Transactional(propagation = Propagation.REQUIRED)
    public void insert(){
        Stu firstChild = new Stu();
        firstChild.setName("firstChild");
        firstChild.setAge(20);
        stuMapper.insert(firstChild);

        int i = 1 / 0;

        Stu secondChild = new Stu();
        secondChild.setName("secondChild");
        secondChild.setAge(2);
        stuMapper.insert(secondChild);
    }

}
