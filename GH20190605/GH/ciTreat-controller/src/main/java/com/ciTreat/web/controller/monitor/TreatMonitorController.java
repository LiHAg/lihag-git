package com.ciTreat.web.controller.monitor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.golden.framework.web.base.BaseController;

/**
 * 服务器监控
 * 
 * @author jinma.zheng
 */
@Controller
@RequestMapping("/monitor/treat")
public class TreatMonitorController extends BaseController
{
    private String prefix = "monitor/treat";

    @GetMapping()
    public String server(ModelMap mmap) throws Exception
    {
        //zzz Server server = new Server();
        //server.copyTo();
       //zzz mmap.put("server", server);
        return prefix + "/treat";
    }
}
