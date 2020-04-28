package com.ysdrzp.service.impl;

import com.ysdrzp.mapper.StuMapper;
import com.ysdrzp.pojo.Stu;
import com.ysdrzp.service.IStuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IStuServiceImpl implements IStuService {

    @Autowired
    StuMapper stuMapper;

    @Override
    public Stu getStu(Integer id) {
        return stuMapper.selectByPrimaryKey(id);
    }

    @Override
    public void saveStu(Stu stu) {
        stuMapper.insert(stu);
    }

    @Override
    public void deleteStu(Integer id) {
        stuMapper.deleteByPrimaryKey(id);
    }

}
