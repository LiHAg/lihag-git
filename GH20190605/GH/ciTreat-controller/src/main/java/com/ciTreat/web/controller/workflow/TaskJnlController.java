package com.ciTreat.web.controller.workflow;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ciTreat.treat.TreatService;
import com.golden.common.page.TableDataInfo;
import com.golden.framework.web.base.BaseController;
import com.golden.workflow.processjnl.service.ProcessJnlService;
import com.golden.workflow.taskjnl.model.TaskJnl;
import com.golden.workflow.taskjnl.service.TaskJnlService;


/**
 * 
 * @author jinma.zheng
 */
@Controller
@RequestMapping("/workflow/taskJnl")
public class TaskJnlController extends BaseController {

	@Autowired
	private TaskJnlService taskJnlService;
	@Autowired
	private ProcessJnlService processJnlService;
	@Autowired
	TreatService treatService;

	@PostMapping("/{processId}")
	@ResponseBody
	public TableDataInfo list(@PathVariable("processId") String processId) {
		startPage();
		
		//此处需要优化
		List<TaskJnl> list = new ArrayList<TaskJnl>();
		list = taskJnlService.getTaskJnlList(processId);
		/*List<ProcessJnl> pjl = processJnlService.getProcessJnlList(busNo);
		for(ProcessJnl pj : pjl) {
			if(pj.getProcessState() !=3) {
				String processId = pj.getProcessId();
				list = taskJnlService.getTaskJnlList(busNo,processId);
				return getDataTable(list);
			}
		}*/
		
		return getDataTable(list);
		
	}

}
