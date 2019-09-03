package com.ciTreat.prework.service;

/**
 * @author lianyi
 * @date 2019年4月18日
 */
import java.util.List;

import com.ciTreat.prework.domain.BeamJnlDetail;

public interface IBeamCalibrationService {
	
    public List<BeamJnlDetail> selectBeamJnlDetailList(String patientId);
    
	public int deleteBeamJnlDetailByID(Long patientId);
	
	public int insertBeamJnlDetail(BeamJnlDetail beamJnlDetail);
	
	public int updateBeamBybeamNo(BeamJnlDetail beamJnlDetail);
	
	public BeamJnlDetail selectBeamByTaskID(String taskId);
	
	public int updateBeamByTaskId(BeamJnlDetail beamJnlDetail);
}
