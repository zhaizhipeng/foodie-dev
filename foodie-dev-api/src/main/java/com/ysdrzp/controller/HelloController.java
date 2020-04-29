package com.ysdrzp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@ApiIgnore
public class HelloController {

    @GetMapping("/hello")
    public String hello(){
        return "Hello World";
    }
}
