package com.study.springbootday03webstduy.controller;

import com.study.springbootday03webstduy.exception.myException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExceptionTestController {
    @RequestMapping("/test")
    public String exception(@RequestParam("user") String user) {
        if ("aaa".equals(user)) {
            throw new myException();
        }
        return "it is ok";
    }
}
