package com.ciTreat.prework.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ciTreat.prework.domain.BeamInfo;
import com.ciTreat.prework.mapper.BeamInfoMapper;
import com.ciTreat.prework.service.IBeamInfoService;


/**
 * @author lianyi
 * @date 2019年4月23日
 */
@Service
public class BeamInfoServiceImpl implements IBeamInfoService {

	@Autowired
	private BeamInfoMapper beamInfoMapper;
	
	@Override
	public List<BeamInfo> selectBeamInfoList(String patientId) {
		
		return beamInfoMapper.selectBeamInfoList(patientId);
	}

	@Override
	public int deleteBeamInfoByID(Long patientId) {
		
		return beamInfoMapper.deleteBeamInfoByID(patientId);
	}

	@Override
	public int insertBeamInfo(BeamInfo beamInfo) {

		return beamInfoMapper.insertBeamInfo(beamInfo);
	}

	@Override
	public int updateBeamInfoByID(BeamInfo beamInfo) {

		return beamInfoMapper.updateBeamInfoByID(beamInfo);
	}

	@Override
	public BeamInfo selectBeamInfoByIdPlan(BeamInfo beamInfo) {
		
		return beamInfoMapper.selectBeamInfoByIdPlan(beamInfo);
	}


}
