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
@RequestMapping("/work/workjob")
public class WorkJobController extends BaseController {
	//private static final Logger log = LoggerFactory.getLogger(WorkJobController.class);
	private String prefix = "work/workjob";
    //@Autowired
    //private IWorkJobService workJobService;
    
	@GetMapping()
	public String ciTtask(ModelMap mmap) throws Exception {
		return prefix + "/workjob";
	}

	/*@RequiresPermissions("work.workjob:list")
	@PostMapping("/list")
	@ResponseBody*/
	//public TableDataInfo list(WorkJob wj) {
		//startPage();
		//List<WorkJob> list = workJobService.selectWorkJobList(wj);
		//return getDataTable(list);
//		return null;
	//}


}
