package com.ciTreat.prework.mapper;

import java.util.List;

import com.ciTreat.prework.domain.BeamJnlDetail;

/**
 * 数据层
 * 
 * @author lianyi
 */
public interface BeamJnlDetailMapper {
	
	public List<BeamJnlDetail> selectBeamJnlDetailList(String patientId);
	
	public int deleteBeamJnlDetailByID(Long patientId);
	
	public int insertBeamJnlDetail(BeamJnlDetail beamJnlDetail);
	
	public int updateBeamBybeamNo(BeamJnlDetail beamJnlDetail);
	
	public BeamJnlDetail selectBeamByTaskID(String taskId);
	
	public int updateBeamByTaskId(BeamJnlDetail beamJnlDetail);

}
