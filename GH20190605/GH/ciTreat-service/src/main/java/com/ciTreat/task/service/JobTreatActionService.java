package com.ciTreat.task.service;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ciTreat.patient.domain.PatientJnl;
import com.ciTreat.patient.service.IPatientJnlService;
import com.ciTreat.treat.domain.TreatJnl;
import com.ciTreat.treat.service.ITreatJnlService;
import com.ciTreat.treat.service.TreatJnlServiceImpl;
import com.ciTreat.util.DateHelper;
import com.golden.workflow.taskjnl.model.TaskJnl;
import com.golden.workflow.taskjnl.service.TaskJnlService;
/**
 * @author Lianyi
 */
@Service
public class JobTreatActionService implements TreatActionService {

	private static Logger logger = LoggerFactory.getLogger(JobTreatActionService.class);
	
	@Autowired
	IPatientJnlService patientJnlService;
	@Autowired
	ITreatJnlService treatJnlService;
	//
	@Override
	public void serviceNo1(Map<String, Object> paras) {

	
	}

	//
	@Override
	public void serviceNo2(Map<String, Object> paras) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void serviceNo3(Map<String, Object> paras) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void serviceNo4(Map<String, Object> paras) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void serviceNo5(Map<String, Object> paras) {
		logger.info("DoneOndeTreatService, busNo = " + (String) paras.get("BUS_NO"));
		String patientId = (String) paras.get("PATIENT_ID");
		//检查患者所有的治疗期数是否完成...需要校验期数 
		//PatientJnl pj = patientJnlService.selectPatientJnlByPID(patientId);
		//pj.setTreatedNum(pj.getTreatedNum() + 1);
		//patientJnlService.updatePatientJnl(pj);
		
	}

	@Override
	public void serviceNo6(Map<String, Object> paras) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void serviceNo7(Map<String, Object> paras) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void serviceNo8(Map<String, Object> paras) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void serviceNo9(Map<String, Object> paras) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void serviceNo10(Map<String, Object> paras) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void serviceNo11(Map<String, Object> paras) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void serviceNo12(Map<String, Object> paras) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void serviceNo13(Map<String, Object> paras) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void serviceNo14(Map<String, Object> paras) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void serviceNo15(Map<String, Object> paras) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void serviceNo16(Map<String, Object> paras) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void serviceNo17(Map<String, Object> paras) {
		// TASKID , PROCESSID, NODEID
		String taskId = (String) paras.get("TASKID");
		TreatJnl tj = treatJnlService.selectTreatJnlByTaskId(taskId);
		PatientJnl pj = patientJnlService.selectPatientJnlByPID(tj.getPatientId());
		
		//已治疗次数=总治疗次数  治疗完成-3,已治疗次数=0  等待治疗-0,已治疗次数<总治疗次数	未完成治疗-1
		long treatedDose = pj.getTreatedDose();
		long treatedNum = pj.getTreatedNum();
		if(treatedNum == treatedDose) {
			pj.setTreatStatus("3");
		}else if(0 == treatedNum){
			pj.setTreatStatus("0");
		}else{
			pj.setTreatStatus("1");
		}
		patientJnlService.updatePatientJnl(pj);
		
		//修改单次治疗结束时间
		tj.setEndTime(DateHelper.getCurrentDatetimeString());
		treatJnlService.updateTreatJnl(tj);
		
	}

	@Override
	public void serviceNo18(Map<String, Object> paras) {
		// TODO Auto-generated method stub
		
	}

	

}
