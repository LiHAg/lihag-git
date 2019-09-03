package com.ciTreat.web.controller.work;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.golden.framework.web.base.BaseController;

/**
 * 
 * 
 * @author jinma.zheng
 */
@Controller
@RequestMapping("/work/worktaskjnl")
public class WorkTaskJnlController extends BaseController {
	private String prefix = "work/worktaskjnl";
  /*  @Autowired
    private IWorkTaskJnlService workTaskJnlkService;*/
    
	@GetMapping()
	public String view(ModelMap mmap) throws Exception {
		return prefix + "/worktaskjnl";
	}

/*	@RequiresPermissions("work.worktaskjnl:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(WorkTaskJnl wtj) {
		startPage();
		List<WorkTaskJnl> list = workTaskJnlkService.selectWorkTaskJnlList(wtj);
		return getDataTable(list);
	}*/
	
    /**
     * 流程详细
     */
    @GetMapping("/worktaskjnl/{taskSrlNum}")
    public String workjobjnl(@PathVariable("taskSrlNum") String taskSrlNum, ModelMap mmap)
    {
    	mmap.put("taskSrlNum", taskSrlNum);
        return "/work/workjobjnl/workjobjnl";
    }

    
/*	@PostMapping("/list/{patientId}")
	@ResponseBody
	public TableDataInfo list(@PathVariable("patientId") String patientId) {
		startPage();
		List<WorkTaskJnl> list = workTaskJnlkService.selectListByPID(patientId);
		return getDataTable(list);
	}*/
}
