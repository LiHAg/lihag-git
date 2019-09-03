package com.ciTreat.treat;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ciTreat.patient.domain.PatientJnl;
import com.ciTreat.patient.service.IPatientJnlService;
import com.ciTreat.treat.domain.TreatJnl;
import com.ciTreat.treat.service.ITreatJnlService;
import com.ciTreat.util.DateHelper;
import com.golden.common.constant.BusinessContants;
import com.golden.common.identifier.NumberIdGenerator;
import com.golden.workflow.common.ContextData;
import com.golden.workflow.engine.WorkflowAction;

/**
 * 
 * @author jinma.zheng
 * @date 2019年3月28日
 */
@Service
public class TreatServiceImpl implements TreatService{
	private static Logger logger = LoggerFactory.getLogger(TreatServiceImpl.class);
	@Autowired
	WorkflowAction workflowAction;
	@Autowired
	ITreatJnlService treatJnlService;
	@Autowired
	IPatientJnlService patientJnlService;
	@Autowired
	private NumberIdGenerator numberIdGenerator;
	
	@Override
	public String process(Map<String, Object> map) {
		String tranCode = (String) map.get("TRAN_CODE");
		String patientId = (String) map.get("PATIENT_ID");
		logger.info("交易[" + tranCode + "]患者[" + patientId + "]>>>>>>>>>>>>>>>>>>>>>>>>>>开始");
		
		// 产生任务流水号
		// String aSerialNumber =
		// serialNumberFactoryService.getCurrentSerialNumber().getSerialNumber();//
		String busNo = String.valueOf(numberIdGenerator.generateId());
		PatientJnl pj = patientJnlService.selectPatientJnlByPID(patientId);
		TreatJnl tj = new TreatJnl();
		tj.setPatientId(patientId);
		tj.setBusNo(busNo);
		tj.setStatus(BusinessContants.TREAT_STATUS_UNDONE);
		//本次治疗次数
		tj.setNum(pj.getTreatedNum()+1);
		tj.setStartTime(DateHelper.getCurrentDatetimeString());
		map.put("BUS_NO",busNo);
		ContextData contextData = new ContextData();
		contextData.putAll(map);
		String taskId = workflowAction.createProcess(contextData);
		
		Map<String, Object> map2 = contextData.getMap();
		String processId = (String) map2.get("PROCESS_ID");
		pj.setTreatedNum(pj.getTreatedNum() + 1);
		pj.setTaskSrlNum(processId);
		patientJnlService.updatePatientJnl(pj);
		
		tj.setTaskId(taskId);
		treatJnlService.insertTreatJnl(tj);
		logger.info("交易[" + tranCode + "]患者[" + patientId + "]>>>>>>>>>>>>>>>>>>>>>>>>>>返回");
		return taskId;
	}

	
	@Override
	public String commitProcessTask(ContextData contextData) {
		String processId = contextData.get("PROCESSID");
		String taskId = contextData.get("TASKID");
		logger.info("交易[" + processId + "] ["+taskId +  "]>>>>>>>>>>>>>>>>>>>>>>>>>>开始");
		String rtTaskId = workflowAction.commitProcessTask(contextData);
		logger.info("交易[" + processId + "] ["+taskId +  "]>>>>>>>>>>>>>>>>>>>>>>>>>>返回");
		return rtTaskId;
	}
	
	
}
