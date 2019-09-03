package com.ciTreat.task.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ciTreat.patient.domain.PatientJnl;
import com.ciTreat.patient.service.IPatientJnlService;
import com.ciTreat.prework.domain.BeamJnlDetail;
import com.ciTreat.prework.service.IBeamCalibrationService;
import com.golden.workflow.processjnl.model.ProcessJnl;
import com.golden.workflow.processjnl.service.ProcessJnlService;
import com.golden.workflow.taskjnl.model.TaskJnl;
import com.golden.workflow.taskjnl.service.TaskJnlService;

/**
 * @author lianyi
 * @date 2019年4月21日
 */
@Service
public class BeamCommonPageInit {
	@Autowired
	IPatientJnlService patientJnlService;
	@Autowired
	private ProcessJnlService processInfoService;
	@Autowired
	private TaskJnlService taskJnlService;
	@Autowired
	private IBeamCalibrationService beamCalibrationService;
	
	//脊形过滤器
	public Map<String, Object> beamN2Init(Map<String, Object> paras) {
		System.out.println("---------------BeamCommonPageInit-------------");
		String taskId = (String) paras.get("TASKID");
		TaskJnl taskJnl = taskJnlService.getTaskJnl(taskId);
		String taskComment = taskJnl.getTaskComment();
		Map<String, Object> rtMap = new HashMap<String, Object>();
		rtMap.put("taskName", "脊形过滤器");
		rtMap.put("msg", "开始移动");
		return rtMap;
	}
	
	//射程移位器
	public Map<String, Object> beamN3Init(Map<String, Object> paras) {
		System.out.println("---------------BeamCommonPageInit-------------");
		String taskId = (String) paras.get("TASKID");
		TaskJnl taskJnl = taskJnlService.getTaskJnl(taskId);
		String taskComment = taskJnl.getTaskComment();
		Map<String, Object> rtMap = new HashMap<String, Object>();
		rtMap.put("taskName", "射程移位器");
		rtMap.put("msg", "开始移动");
		return rtMap;
	}
	
	//剂量配送
	public Map<String, Object> beamN4Init(Map<String, Object> paras) {
		System.out.println("---------------BeamCommonPageInit-------------");
		String taskId = (String) paras.get("TASKID");
		TaskJnl taskJnl = taskJnlService.getTaskJnl(taskId);
		String taskComment = taskJnl.getTaskComment();
		Map<String, Object> rtMap = new HashMap<String, Object>();
		rtMap.put("taskName", "标定测试[剂量配送]");
		rtMap.put("msg", "开始扫描测试");
		return rtMap;
	}
	
	//等待TCP按钮按下
	public Map<String, Object> beamN5Init(Map<String, Object> paras) {
		System.out.println("---------------BeamCommonPageInit-------------");
		Map<String, Object> rtMap = new HashMap<String, Object>();
		rtMap.put("msg", "开始治疗");
		return rtMap;
	}
}
