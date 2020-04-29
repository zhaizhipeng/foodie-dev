package com.ysdrzp.controller;

import com.ysdrzp.pojo.Stu;
import com.ysdrzp.service.IStuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@ApiIgnore
public class StuController {

    @Autowired
    IStuService stuService;

    @GetMapping("/getStu/{id}")
    public Stu getStu(Integer id){
        System.out.println(stuService.getStu(id));
        return stuService.getStu(id);
    }

    @PostMapping("/saveStu")
    public String getUser(){
        Stu stu = new Stu();
        stu.setName("花花");
        stu.setAge(10);
        stuService.saveStu(stu);
        return "success";
    }

    @DeleteMapping("/deleteStu/{id}")
    public String getUser(Integer id){
        stuService.deleteStu(id);
        return "success";
    }
}
