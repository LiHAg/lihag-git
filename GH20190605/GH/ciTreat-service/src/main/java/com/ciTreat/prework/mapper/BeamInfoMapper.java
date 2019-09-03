package com.ciTreat.prework.mapper;

import java.util.List;

import com.ciTreat.prework.domain.BeamInfo;

/**
 * 数据层
 * 
 * @author lianyi
 */
public interface BeamInfoMapper {
	
    public List<BeamInfo> selectBeamInfoList(String patientId);
    
	public int deleteBeamInfoByID(Long patientId);
	
	public int insertBeamInfo(BeamInfo beamInfo);
	
	public int updateBeamInfoByID(BeamInfo beamInfo);
	
	public BeamInfo selectBeamInfoByIdPlan(BeamInfo beamInfo);
}
