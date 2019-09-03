package com.study.springbootday03webstduy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

@Controller
public class SuccessController {
    @RequestMapping("/success")
    public String success(Map<String, Object> map) {
        map.put("hello", "你好！");
        map.put("dog", "小黄！");
        map.put("users", Arrays.asList("黄小薇", "李小清"));
        return "success";
    }
}
