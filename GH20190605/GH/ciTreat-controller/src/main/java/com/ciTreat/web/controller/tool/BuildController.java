package com.ciTreat.web.controller.tool;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.golden.framework.web.base.BaseController;

/**
 * build 表单构建
 * 
 * @author jinma.zheng
 */
@Controller
@RequestMapping("/tool/build")
public class BuildController extends BaseController
{
    private String prefix = "tool/build";

    @GetMapping()
    public String build()
    {
        return prefix + "/build";
    }
}
