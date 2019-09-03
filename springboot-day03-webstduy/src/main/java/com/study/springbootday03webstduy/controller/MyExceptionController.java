package com.study.springbootday03webstduy.controller;

import com.study.springbootday03webstduy.exception.myException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class MyExceptionController {
    //    @ResponseBody
////    @ExceptionHandler(UserNotExist.class)
////    public Map<String,Object> MyExceptionHandle(Exception e){
////        Map<String,Object> map=new HashMap<>();
////        map.put("code","user.notexist");
////        map.put("message",e.getMessage());
////        return map;
////    }
    @ExceptionHandler(myException.class)
    public String MyExceptionHandle(Exception e, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        //传入我们自己的错误状态码  4xx 5xx
        /**
         * Integer statusCode = (Integer) request
         .getAttribute("javax.servlet.error.status_code");
         */
        request.setAttribute("javax.servlet.error.status_code", 500);
        map.put("code", "user.notexist");
        map.put("message", "用户出错啦！");
        request.setAttribute("ext", map);
        return "forward:/error";
    }
}
