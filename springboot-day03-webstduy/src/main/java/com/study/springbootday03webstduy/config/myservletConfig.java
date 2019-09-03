package com.study.springbootday03webstduy.config;

import com.study.springbootday03webstduy.Listen.mylisten;
import com.study.springbootday03webstduy.Servlet.myservlet;
import com.study.springbootday03webstduy.filter.myfilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.ServletContextListener;
import java.util.Arrays;

@Configuration
public class myservletConfig {

    @Bean
    public ServletRegistrationBean myservletbean() {
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new myservlet(), "/servlet");
        return servletRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean myfilterbean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new myfilter());
        filterRegistrationBean.setUrlPatterns(Arrays.asList("/filter", "/servlet"));
        return filterRegistrationBean;
    }

    @Bean
    public ServletListenerRegistrationBean mylistenbean() {
        ServletListenerRegistrationBean<mylisten> servletListenerRegistrationBean = new ServletListenerRegistrationBean<>(new mylisten());
        return servletListenerRegistrationBean;
    }
}
