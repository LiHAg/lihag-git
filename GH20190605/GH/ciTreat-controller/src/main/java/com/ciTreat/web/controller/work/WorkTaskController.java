package com.ciTreat.web.controller.work;

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
@RequestMapping("/work/worktask")
public class WorkTaskController extends BaseController {
	private String prefix = "work/worktask";
   /* @Autowired
    private IWorkTaskService workTaskService;*/
    
	@GetMapping()
	public String ciTtask(ModelMap mmap) throws Exception {
		return prefix + "/worktask";
	}

	/*@RequiresPermissions("work.worktask:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(WorkTask wt) {
		startPage();
		List<WorkTask> list = workTaskService.selectWorkTaskList(wt);
		return getDataTable(list);
	}*/


}
