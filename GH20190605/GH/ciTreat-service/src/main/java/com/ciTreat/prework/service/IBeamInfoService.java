package com.ciTreat.prework.service;

/**
 * @author lianyi
 * @date 2019年4月23日
 */
import java.util.List;

import com.ciTreat.prework.domain.BeamInfo;

public interface IBeamInfoService {
	
    public List<BeamInfo> selectBeamInfoList(String patientId);
    
	public int deleteBeamInfoByID(Long patientId);
	
	public int insertBeamInfo(BeamInfo beamInfo);
	
	public int updateBeamInfoByID(BeamInfo beamInfo);
	
	public BeamInfo selectBeamInfoByIdPlan(BeamInfo beamInfo);
	
}
