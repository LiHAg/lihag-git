package com.study.springbootday03webstduy.component;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        Object loginUsername = httpServletRequest.getSession().getAttribute("LoginUsername");
        if (loginUsername == null) {
            //session 中不存在该用户名，用户未登录，转发到登录页面重新登录
            httpServletRequest.setAttribute("msg", "没有权限请先登录！");
            httpServletRequest.getRequestDispatcher("/index.html").forward(httpServletRequest, httpServletResponse);
            return false;
        } else {
            //session 存在用户  说明用户已登录！  放行
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
