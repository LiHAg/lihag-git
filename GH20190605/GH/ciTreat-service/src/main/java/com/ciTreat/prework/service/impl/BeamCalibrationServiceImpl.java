package com.ciTreat.prework.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ciTreat.prework.domain.BeamJnlDetail;
import com.ciTreat.prework.mapper.BeamJnlDetailMapper;
import com.ciTreat.prework.service.IBeamCalibrationService;
/**
 * @author lianyi
 * @date 2019年4月10日
 */
@Service
public class BeamCalibrationServiceImpl implements IBeamCalibrationService {

	@Autowired
	private BeamJnlDetailMapper beamJnlDetailMapper;
	
	@Override
	public List<BeamJnlDetail> selectBeamJnlDetailList(String patientId) {
		
		return beamJnlDetailMapper.selectBeamJnlDetailList(patientId);
	}

	@Override
	public int deleteBeamJnlDetailByID(Long patientId) {
		
		return beamJnlDetailMapper.deleteBeamJnlDetailByID(patientId);
	}

	@Override
	public int insertBeamJnlDetail(BeamJnlDetail beamJnlDetail) {
		
		return beamJnlDetailMapper.insertBeamJnlDetail(beamJnlDetail);
	}

	@Override
	public int updateBeamBybeamNo(BeamJnlDetail beamJnlDetail) {
		
		return beamJnlDetailMapper.updateBeamBybeamNo(beamJnlDetail);
	}

	@Override
	public BeamJnlDetail selectBeamByTaskID(String taskId) {
		
		return beamJnlDetailMapper.selectBeamByTaskID(taskId);
	}

	@Override
	public int updateBeamByTaskId(BeamJnlDetail beamJnlDetail) {
		
		return beamJnlDetailMapper.updateBeamByTaskId(beamJnlDetail);
	}

}
