package com.ciTreat.task.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ciTreat.prework.domain.BeamJnlDetail;
import com.ciTreat.prework.service.IBeamCalibrationService;
/**
 * @author Lianyi
 */
@Service
public class JobBeamActionService implements BeamActionService {

	@Autowired
	private IBeamCalibrationService beamCalibrationService;
	
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
		System.out.println("------------开始治疗Action----------------");
		String taskId = (String)paras.get("TASKID");
		
		//测试值
		BeamJnlDetail beamJnlDetail = new BeamJnlDetail();
		beamJnlDetail.setTaskId(taskId);
		beamJnlDetail.setReaDose(11111);
		beamJnlDetail.setIc1Num(11111111);
		beamJnlDetail.setIc1CalibFactor(11111111);
		beamJnlDetail.setIc2Num(11111111);
		beamJnlDetail.setIc2CalibFactor(11111111);
		beamJnlDetail.setIc3Num(11111111);
		beamJnlDetail.setIc3CalibFactor(11111111);
		beamCalibrationService.updateBeamByTaskId(beamJnlDetail);	

	}

}
