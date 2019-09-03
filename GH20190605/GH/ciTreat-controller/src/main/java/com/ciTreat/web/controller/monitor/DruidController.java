package com.ciTreat.web.controller.monitor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.golden.framework.web.base.BaseController;

/**
 * druid 监控
 * 
 * @author jinma.zheng
 */
@Controller
@RequestMapping("/monitor/data")
public class DruidController extends BaseController
{
    private String prefix = "/monitor/druid";

    @GetMapping()
    public String index()
    { 
        return redirect(prefix + "/index");
    }
}
