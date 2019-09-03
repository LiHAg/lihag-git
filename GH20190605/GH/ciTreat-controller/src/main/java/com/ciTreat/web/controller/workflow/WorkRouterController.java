package com.ciTreat.web.controller.workflow;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.golden.common.page.TableDataInfo;
import com.golden.framework.web.base.BaseController;
import com.golden.workflow.router.WorkRouterService;
import com.golden.workflow.router.model.WorkRouter;

/**
 * 服务器监控
 * 
 * @author jinma.zheng
 */
@Controller
@RequestMapping("/work/workrouter")
public class WorkRouterController extends BaseController {
	private String prefix = "work/workrouter";
    @Autowired
    private WorkRouterService workRouterService;
    
	@GetMapping()
	public String ciTtask(ModelMap mmap) throws Exception {
		return prefix + "/workrouter";
	}

	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list() {
		startPage();
		List<WorkRouter> list = workRouterService.selectWorkRouterList();
		return getDataTable(list);
	}

}
