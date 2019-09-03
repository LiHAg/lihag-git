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
import com.golden.workflow.template.model.TemplateInfo;
import com.golden.workflow.template.service.TemplateService;

/**
 * 服务器监控
 * 
 * @author jinma.zheng
 */
@Controller
@RequestMapping("/workflow/template")
public class TemplateController extends BaseController {
	private String prefix = "workflow/template";
	@Autowired
    private TemplateService templateService;
    
	@GetMapping()
	public String ciTtask(ModelMap mmap) throws Exception {
		return prefix + "/template";
	}

	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list() {
		startPage();
		List<TemplateInfo> list = templateService.getTemplateInfoList();
		return getDataTable(list);
	}


}
