package com.ciTreat.web.controller.prework;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ciTreat.patient.domain.PatientJnl;
import com.ciTreat.patient.service.IPatientInfoService;
import com.ciTreat.patient.service.IPatientJnlService;
import com.ciTreat.prework.domain.BeamInfo;
import com.ciTreat.prework.domain.BeamJnlDetail;
import com.ciTreat.prework.service.BeamService;
import com.ciTreat.prework.service.IBeamCalibrationService;
import com.ciTreat.prework.service.IBeamInfoService;
import com.golden.common.base.AjaxResult;
import com.golden.common.constant.BusinessContants;
import com.golden.common.identifier.NumberIdGenerator;
import com.golden.common.page.TableDataInfo;
import com.golden.framework.web.base.BaseController;
import com.golden.workflow.common.ContextData;
import com.golden.workflow.taskjnl.model.TaskJnl;
import com.golden.workflow.taskjnl.service.TaskJnlService;

/**
 * 束流标定
 * @author LIANYI
 */
@Controller
@RequestMapping("/prework/beam")
public class BeamCalibrationController extends BaseController {

	private String prefix = "prework/beam";
	private String prefix_Jnl = "prework/beamJnl";
	
	@Autowired
	private IPatientJnlService patientJnlService;
	@Autowired
	private IPatientInfoService patientInfoService;
	@Autowired
	private IBeamCalibrationService beamCalibrationService;
	@Autowired
	private BeamService beamService;
	@Autowired
	private NumberIdGenerator numberIdGenerator;
	@Autowired
	private TaskJnlService taskJnlService; 
	@Autowired
	private IBeamInfoService beamInfoService;

	@GetMapping()
	public String beamCalibration() {
		return prefix + "/beam";
	}
	
	/**
	 *	新增测试
	 */
	@GetMapping("/addbeamJnl")
	public String addBeamJnl(String patientId,ModelMap mmap) {
		mmap.put("patientId", patientId);
		System.out.println("-------------" + patientId);
		return prefix_Jnl+"/addbeamJnl";
	}
	
	/**
	 * 创建流程
	 */
	@PostMapping("/createBeamProcess")
	@ResponseBody
	public AjaxResult createBeamProcess(BeamJnlDetail beamJnlDetail, ModelMap mmp) throws ParseException {
		//beam_jnl add 更新主表...
		
		//创建状态
		PatientJnl ptj = patientJnlService.selectPatientJnlByPID(beamJnlDetail.getPatientId());
		beamJnlDetail.setPatientName(ptj.getPatientName());
		beamJnlDetail.setPlanId(ptj.getPlanName());
		beamJnlDetail.setStatus(BusinessContants.BUSINESS_EXECUTE_INTO);
		String busNo = String.valueOf(numberIdGenerator.generateId());
		beamJnlDetail.setBeamNo(busNo);
		
		//启动流程....
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("BUS_NO", busNo);
		map.put("TRAN_CODE", "6001");
		map.put("PATIENT_ID", beamJnlDetail.getPatientId());
		String taskId = beamService.process(map);
		
		beamJnlDetail.setTaskId(taskId);
		beamCalibrationService.insertBeamJnlDetail(beamJnlDetail);
		AjaxResult ajax = AjaxResult.success();
		return ajax;
	}
	
	/**
	 * 提交流程
	 */
	@PostMapping("/commitProcessTask")
    @ResponseBody
	public AjaxResult commitProcessTask(@RequestBody MultiValueMap<String,String> json,ModelMap mmap) {
		System.out.println("+++++++++++++束流标定提交流程+++++++++++++++++++" + json);
		ContextData conData = new ContextData();
		conData.putAllStr(json.toSingleValueMap());
		String taskId = conData.get("TASKID");
		String nodeId = conData.get("NODEID");
		BeamJnlDetail bj = beamCalibrationService.selectBeamByTaskID(taskId);
		String NextTaskId = beamService.commitProcessTask(conData);
		System.out.println("++++++++++++NextTaskId="+NextTaskId);
		
		//更新taskID
		bj.setTaskId(NextTaskId);
		//判断是否是尾结点-状态设置为完成 16	(若新增流程需修改)
		if("BeamNo5".equals(nodeId)) {
			bj.setStatus(BusinessContants.BUSINESS_EXECUTE_SUCCESS);
		}else {
			bj.setStatus(BusinessContants.BUSINESS_EXECUTE_INTO);
		}
		beamCalibrationService.updateBeamBybeamNo(bj);
		AjaxResult ajax = AjaxResult.success();
		return ajax;
	}
	
	/**
	 * 束流测试页面初始化
	 */
	@GetMapping("/beamJnl")
	public String beamCalibration(PatientJnl patientJnl, ModelMap mmap) {
		
		//查询患者ID 姓名 计划      (根据ID 和   plan 查询)
		PatientJnl pj = patientJnlService.selectPatientJnlByIdPlan(patientJnl);
		//查询beam_info (治疗头、能量、。。。。)
		BeamInfo beamInfo = new BeamInfo();
		beamInfo.setPatientId(pj.getPatientId());
		beamInfo.setPlanId(pj.getPlanName());
		BeamInfo bi = beamInfoService.selectBeamInfoByIdPlan(beamInfo);
		if(bi == null) {
			BeamInfo beamInfo2 = new BeamInfo();
			mmap.put("bi", beamInfo2);
		}else {
			mmap.put("bi", bi);
		}
		mmap.put("pj", pj);
		mmap.put("patientId", pj.getPatientId());
		mmap.put("processId", pj.getTaskSrlNum());
		
		return "prework/beamJnl/beamJnl";
	}

	/**
	 * 束流标定列表
	 */
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(PatientJnl patientJnl) {
		startPage();
		patientJnl.setTestFlag(BusinessContants.TREAT_FLAG_NORMAL);
		List<PatientJnl> selectPatientJnlList = patientJnlService.selectPatientJnlList(patientJnl);
		return getDataTable(selectPatientJnlList);
	}
	
	/**
	 * 束流测试列表
	 */
	@PostMapping("/beamJnlDetailList/{patientId}")
	@ResponseBody
	public TableDataInfo BeamList(@PathVariable("patientId") String patientId) {
		startPage();
		//PatientJnl patientJnl = new PatientJnl();
		//List<PatientJnl> selectPatientJnlList = patientJnlService.selectPatientJnlList(patientJnl);
		List<BeamJnlDetail> selectBeamList = beamCalibrationService.selectBeamJnlDetailList(patientId);
		for(BeamJnlDetail ss : selectBeamList) {
			String taskId = ss.getTaskId();
			TaskJnl taskJnl = taskJnlService.getTaskJnl(taskId);
			ss.setTaskComment(taskJnl.getTaskComment());
			ss.setTaskState(taskJnl.getTaskState());
			ss.setNodeId(taskJnl.getNodeId());
			ss.setProcessId(taskJnl.getProcessId());
		}
		
		return getDataTable(selectBeamList);
	}
	
	/**
	 * 束流测试页面---作废
	 */
	@PostMapping("/remove/{patientId}")
	@ResponseBody
	public AjaxResult remove(@PathVariable("patientId") Long patientId) {
 
		return toAjax(beamCalibrationService.deleteBeamJnlDetailByID(patientId));
	}
	
	/**
	 * 束流测试页面---历史
	 */
	@GetMapping("/history/{busNo}")
	public String history(@PathVariable("busNo") String busNo, ModelMap mmap) throws Exception {
		mmap.put("busNo", busNo);
		return "workflow/processJnl/processJnl";
	}
	
	/**
	 * 确认标定 (算法需修改)
	 */
	@PostMapping("/commitBeam")
    @ResponseBody
	public AjaxResult commitBeam(String patientId,String patientName,String planName) {
		System.out.println("+++++++++++++确认标定+++++++++++++++++++");
		//查询beam_jnl_detail表  多条标定因子  求平均数
		List<BeamJnlDetail> list = beamCalibrationService.selectBeamJnlDetailList(patientId);
		int ic1num = 0, ic2num=0, ic3num=0;
		for(BeamJnlDetail bjd : list) {
			ic1num += bjd.getIc1CalibFactor();
			ic2num += bjd.getIc2CalibFactor();
			ic3num += bjd.getIc3CalibFactor();
		}
		Integer sum1 = ic1num/list.size();
		Integer sum2 = ic2num/list.size();
		Integer sum3 = ic3num/list.size();
	
		//插入beam_info表
		BeamInfo beamInfo =  new BeamInfo();
		beamInfo.setPatientId(patientId);
		beamInfo.setPatientName(patientName);
		beamInfo.setPlanId(planName);
		beamInfo.setIc1CalibFactor(sum1);
		beamInfo.setIc2CalibFactor(sum2);
		beamInfo.setIc3CalibFactor(sum3);
		beamInfoService.insertBeamInfo(beamInfo);
		
		//修改标定状态
		PatientJnl pjl = new  PatientJnl();
		pjl.setBeamStatus("1");
		pjl.setPatientId(patientId);
		patientJnlService.updatePatientJnl(pjl);
		
		AjaxResult ajax = AjaxResult.success();
		return ajax;
	}

}
