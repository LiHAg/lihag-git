package com.ciTreat.web.controller.patient;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ciTreat.patient.domain.PatientInfo;
import com.ciTreat.patient.domain.PatientJnl;
import com.ciTreat.patient.service.IPatientInfoService;
import com.ciTreat.patient.service.IPatientJnlService;
import com.ciTreat.treat.domain.TreatJnl;
import com.ciTreat.treat.service.ITreatJnlService;
import com.golden.common.base.AjaxResult;
import com.golden.common.constant.BusinessContants;
import com.golden.common.page.TableDataInfo;
import com.golden.framework.web.base.BaseController;
import com.golden.workflow.taskjnl.model.TaskJnl;
import com.golden.workflow.taskjnl.service.TaskJnlService;

/**
 * @author jinma.zheng
 */
@Controller
@RequestMapping("/treatJnl/treatJnl")
public class TreatJnlController extends BaseController {
	private String prefix = "treat/main";
	@Autowired
	private ITreatJnlService treatJnlService;
	@Autowired
	private TaskJnlService taskJnlService; 
	@Autowired
	private IPatientJnlService patientJnlService; 
	
	@GetMapping("")
	public String view(ModelMap mmap) throws Exception {
		return prefix + "/main";
	}

	/**治疗实施初始化*/
	@PostMapping("/list/{patientId}")
	@ResponseBody
	public TableDataInfo list(@PathVariable("patientId") String patientId) {
		startPage();
		List<TreatJnl> selectTreatJnlList = treatJnlService.selectTreatJnlList(patientId);
		for(TreatJnl tj : selectTreatJnlList) {
			String taskId = tj.getTaskId();
			TaskJnl taskJnl = taskJnlService.getTaskJnl(taskId);
			tj.setTaskComment(taskJnl.getTaskComment());
			tj.setProcessId(taskJnl.getProcessId());
			tj.setNodeId(taskJnl.getNodeId());
		}
		return getDataTable(selectTreatJnlList);
	}

	/**单次作废*/
	@PostMapping("/unTreat")
	@ResponseBody
	public AjaxResult unTreat(String pid,String busNo) {
		System.out.println("---------------治疗单次作废++++-------------" + pid);
		PatientJnl pj = patientJnlService.selectPatientJnlByPID(pid);
		pj.setTreatedNum(pj.getTreatedNum()-1);
		patientJnlService.updatePatientJnl(pj);
		
		treatJnlService.deleteTreatJnlByBusNo(busNo);
		
		AjaxResult ajax = AjaxResult.success();
		return ajax;
	}
	
	/**治疗完成*/
	@PostMapping("/completed")
	@ResponseBody
	public AjaxResult completed(String pid) {
		System.out.println("---------------治疗完成++++-------------" + pid);
		//修改患者总状态为 治疗完成-3 
		PatientJnl pj = patientJnlService.selectPatientJnlByPID(pid);
		pj.setTreatStatus(BusinessContants.TREAT_STATUS_FINISH);
		patientJnlService.updatePatientJnl(pj);
		
		AjaxResult ajax = AjaxResult.success();
		return ajax;
	}
	
	/**治疗失败*/
	@PostMapping("/treatfaild")
	@ResponseBody
	public AjaxResult treatfaild(String pid) {
		System.out.println("---------------治疗失败++++-------------" + pid);
		//修改患者总状态为 治疗失败-2 
		PatientJnl pj = patientJnlService.selectPatientJnlByPID(pid);
		pj.setTreatStatus(BusinessContants.TREAT_STATUS_FAILD);
		patientJnlService.updatePatientJnl(pj);
		
		AjaxResult ajax = AjaxResult.success();
		return ajax;
	}
	
}
