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
import com.golden.common.constant.BusinessContants;
import com.golden.common.page.TableDataInfo;
import com.golden.framework.web.base.BaseController;

/**
 * 
 * @author	liany
 */
@Controller
@RequestMapping("/testPatient/testPatientJnl")
public class TestPatientJnlController extends BaseController {
	private String prefix = "testPatient/testPatientJnl";
	@Autowired
	private IPatientJnlService patientJnlService;
	@Autowired
	private IPatientInfoService patientInfoService;
	
	@GetMapping("")
	public String view(ModelMap mmap) throws Exception {
		return prefix + "/testPatientJnl";
	}

	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(PatientJnl wj) {
		startPage();
		wj.setTestFlag(BusinessContants.TREAT_FLAG_TEST);
		List<PatientJnl> list = patientJnlService.selectPatientJnlList(wj);
		return getDataTable(list);
	}

	@GetMapping("/history/{busNo}")
	public String history(@PathVariable("busNo") String busNo, ModelMap mmap) throws Exception {
		mmap.put("busNo", busNo);
		return "workflow/processJnl/processJnl";
	}

	@GetMapping("/plan/{busNo}")
	public String plan(@PathVariable("busNo") String busNo, ModelMap mmap) throws Exception {
		mmap.put("busNo", busNo);
		return "plan/planInfo/planInfo";
	}

	@GetMapping("/testTreat/{busNo}")
	public String treat(@PathVariable("busNo") String busNo, ModelMap mmap) throws Exception {
		//busNo = patientId
		PatientJnl pj = patientJnlService.selectPatientJnlByPID(busNo);
		mmap.put("pj", pj);
		mmap.put("patientId", busNo);
		
		return "testTreat/main/main";
	}
	
}
