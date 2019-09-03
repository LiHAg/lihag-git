package com.study.springbootday03webstduy.Listen;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class mylisten implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("监听已开启！");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("服务已销毁，监听已结束！");
    }
}
