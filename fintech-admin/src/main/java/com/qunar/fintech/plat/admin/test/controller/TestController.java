package com.qunar.fintech.plat.admin.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test/test1")
public class TestController {

    @GetMapping
    public String staticHtml(){
        return "test/queryStatistic/queryStatistic";
    }





}
