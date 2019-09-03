package com.ciTreat.web.controller.workflow;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.golden.common.page.TableDataInfo;
import com.golden.framework.web.base.BaseController;
import com.golden.workflow.processjnl.model.ProcessJnl;
import com.golden.workflow.processjnl.service.ProcessJnlService;

/**
 * 
 * @author jinma.zheng
 */
@Controller
@RequestMapping("/workflow/processJnl")
public class ProcessJnlController extends BaseController {
	@Autowired
	private ProcessJnlService processJnlService;

	@PostMapping("/{busNo}")
	@ResponseBody
	public TableDataInfo list(@PathVariable("busNo") String busNo) {
		startPage();
		List<ProcessJnl> pjl = processJnlService.getProcessJnlList(busNo);
		return getDataTable(pjl);
	}

	@GetMapping("/taskJnl/{processId}")
	public String taskJnl(@PathVariable("processId") String processId, ModelMap mmap) throws Exception {
		mmap.put("processId", processId);
		return "workflow/taskJnl/taskJnl";
	}
}
