package com.ciTreat.web.controller.treat;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ciTreat.patient.domain.PatientJnl;
import com.ciTreat.patient.service.IPatientJnlService;
import com.ciTreat.patient.service.impl.PatientJnlServiceImpl;
import com.ciTreat.treat.TreatService;
import com.ciTreat.treat.domain.TreatJnl;
import com.ciTreat.treat.service.ITreatJnlService;
import com.golden.common.base.AjaxResult;
import com.golden.common.constant.BusinessContants;
import com.golden.framework.web.base.BaseController;
import com.golden.workflow.common.ContextData;

/**
 * 
 * @author jinma.zheng
 */
@Controller
@RequestMapping("/treat")
public class TreatController extends BaseController {

	@Autowired
	TreatService treatService;
	
	@Autowired
	private ITreatJnlService treatJnlService;
	@Autowired
	private IPatientJnlService patientJnlService; 

	@PostMapping("/createTask")
	@ResponseBody
	public AjaxResult createTask(String pid) {
		System.out.println("---------------dotask++++-------------" + pid);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("TRAN_CODE", "8001");
		map.put("PATIENT_ID", pid);
		treatService.process(map);
		AjaxResult ajax = AjaxResult.success();
		return ajax;
	}
	
	@PostMapping("/commitProcessTask")
    @ResponseBody
	public AjaxResult commitProcessTask(@RequestBody MultiValueMap<String,String> json,ModelMap mmap) {
		System.out.println("+++++++++++++=3333+++++++++++++++++++" + json);
		ContextData conData = new ContextData();
		conData.putAllStr(json.toSingleValueMap());
		String taskId = conData.get("TASKID");
		String nodeId = conData.get("NODEID");
		String patientId = conData.get("patientId");
		
		String nextTaskId = treatService.commitProcessTask(conData);
		TreatJnl tj = treatJnlService.selectTreatJnlByTaskId(taskId);
		tj.setTaskId(nextTaskId);
		//修改单次治疗状态()
		if("N17".equals(nodeId)) {
			tj.setStatus(BusinessContants.TREAT_STATUS_FINISH);
		}else {
			tj.setStatus(BusinessContants.TREAT_STATUS_UNDONE);
		}
		treatJnlService.updateTreatJnl(tj);
	
		AjaxResult ajax = AjaxResult.success();
		return ajax;
	}
	
	
}
