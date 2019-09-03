package com.ciTreat.task.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ciTreat.patient.domain.PatientJnl;
import com.ciTreat.patient.service.IPatientJnlService;
import com.golden.workflow.processjnl.model.ProcessJnl;
import com.golden.workflow.processjnl.service.ProcessJnlService;

/**
 * @author jinma.zheng
 * @date 2019年4月7日
 */
@Service
public class TreatCommonPageInit {
	@Autowired
	IPatientJnlService patientJnlService;
	@Autowired
	private ProcessJnlService processInfoService;
	
	//患者身份验证
	public Map<String, Object> treatN2Init(Map<String, Object> paras) {
		System.out.println("---------------PatientValidate-------------");
		String processId = (String) paras.get("PROCESSID");
		ProcessJnl processJnl = processInfoService.getProcessInfo(processId);
		String patientId = processJnl.getBusNo();
		PatientJnl patientJnl = patientJnlService.selectPatientJnlByPID(patientId);
		
		Map<String, Object> rtMap = new HashMap<String, Object>();
		
		rtMap.put("taskName", "患者身份验证");
		rtMap.put("msg", "开始移动");
		
		//获取已治疗次数  总治疗次数  相等时修改治疗状态为 治疗完成 否则为 治疗未完成	
		
		//修改 结束时间
		
		return rtMap;
	}
	
	public Map<String,Object> treatN3Init (Map<String, Object> pars){
		
		Map<String, Object> rtMap = new HashMap<String, Object>();
		rtMap.put("taskName", "设备初始化");
		rtMap.put("msg", "开始移动");
		return rtMap;
	}
	public Map<String,Object> treatN4Init (Map<String, Object> pars){
		
		Map<String, Object> rtMap = new HashMap<String, Object>();
		
		return rtMap;
	}
	public Map<String,Object> treatN5Init (Map<String, Object> pars){
		
		Map<String, Object> rtMap = new HashMap<String, Object>();
		
		return rtMap;
	}
	public Map<String,Object> treatN6Init (Map<String, Object> pars){
		
		Map<String, Object> rtMap = new HashMap<String, Object>();
		
		return rtMap;
	}
	public Map<String,Object> treatN7Init (Map<String, Object> pars){
		
		Map<String, Object> rtMap = new HashMap<String, Object>();
		
		return rtMap;
	}
	public Map<String,Object> treatN8Init (Map<String, Object> pars){
		
		Map<String, Object> rtMap = new HashMap<String, Object>();
		
		return rtMap;
	}
	public Map<String,Object> treatN9Init (Map<String, Object> pars){
		
		Map<String, Object> rtMap = new HashMap<String, Object>();
		
		rtMap.put("taskName", "患者定位");
		rtMap.put("msg", "开始移动");
		
		return rtMap;
	}
	public Map<String,Object> treatN10Init (Map<String, Object> pars){
		
		Map<String, Object> rtMap = new HashMap<String, Object>();
		
		return rtMap;
	}
	public Map<String,Object> treatN11Init (Map<String, Object> pars){
		
		Map<String, Object> rtMap = new HashMap<String, Object>();
		
		return rtMap;
	}
	public Map<String,Object> treatN12Init (Map<String, Object> pars){
		
		Map<String, Object> rtMap = new HashMap<String, Object>();
		
		return rtMap;
	}
	public Map<String,Object> treatN13Init (Map<String, Object> pars){
		
		Map<String, Object> rtMap = new HashMap<String, Object>();
		
		return rtMap;
	}
	public Map<String,Object> treatN14Init (Map<String, Object> pars){
		
		Map<String, Object> rtMap = new HashMap<String, Object>();
		
		return rtMap;
	}
	public Map<String,Object> treatN15Init (Map<String, Object> pars){
		
		Map<String, Object> rtMap = new HashMap<String, Object>();
		rtMap.put("taskName", "患者定位结果");
		rtMap.put("msg", "开始移动");
		return rtMap;
	}
	public Map<String,Object> treatN16Init (Map<String, Object> pars){
		
		Map<String, Object> rtMap = new HashMap<String, Object>();
		rtMap.put("taskName", "定位验证");
		rtMap.put("msg", "开始移动");
		return rtMap;
	}
	public Map<String,Object> treatN17Init (Map<String, Object> pars){
		
		Map<String, Object> rtMap = new HashMap<String, Object>();
		rtMap.put("taskName", "准备治疗");
		rtMap.put("msg", "开始移动");
		return rtMap;
	}
	public Map<String,Object> treatN18Init (Map<String, Object> pars){
		
		Map<String, Object> rtMap = new HashMap<String, Object>();
		
		return rtMap;
	}
	
}
