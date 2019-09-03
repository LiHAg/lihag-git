package com.ciTreat.dev.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ciTreat.dev.domain.DevManager;
import com.ciTreat.dev.mapper.DevManageMapper;
import com.ciTreat.dev.service.IDevManageService;

@Service
public class DevManageServiceImpl implements IDevManageService {

	@Autowired
	private DevManageMapper devManageMapper;
	
	@Override
	public List<DevManager> selectDevManageList(DevManager devManager) {
	
		return devManageMapper.selectDevManageList(devManager);
	}

	@Override
	public DevManager selectDevManageById(DevManager devManager) {
		
		return devManageMapper.selectDevManageById(devManager);
	}

	@Override
	public int updateDetailDevManage(DevManager devManager) {
		
		return devManageMapper.updateDetailDevManage(devManager);
	}

	@Override
	public List<DevManager> selectDevCalibration(DevManager devManager) {
		
		return devManageMapper.selectDevCalibration(devManager);
	}

}
