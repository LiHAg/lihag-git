package com.ciTreat.web.controller.workflow;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.golden.common.base.AjaxResult;
import com.golden.framework.web.base.BaseController;
import com.golden.workflow.nodeinfo.service.NodeInfoService;

/**
 * 
 * @author jinma.zheng
 */
@Controller
@RequestMapping("/workflow/doTask")
public class DoTaskController extends BaseController {
//	@Autowired
//	private ProcessJnlService processJnlService;
	@Autowired
	private NodeInfoService nodeInfoService;
	@PostMapping("")
    @ResponseBody
	public AjaxResult doTask(@RequestBody MultiValueMap<String,String> json,ModelMap mmap) {
		System.out.println("+++++++++++++=3333+++++++++++++++++++" + json);
		//return toAjax();
		return null;
		
	}
	
}
