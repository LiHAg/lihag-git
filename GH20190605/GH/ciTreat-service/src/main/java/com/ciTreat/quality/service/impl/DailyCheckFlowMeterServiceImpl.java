package com.ciTreat.quality.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ciTreat.quality.domain.DailyCheckFlowMeter;
import com.ciTreat.quality.mapper.DailyCheckFlowMeterMapper;
import com.ciTreat.quality.service.DailyCheckFlowMeterService;



@Service
public class DailyCheckFlowMeterServiceImpl implements DailyCheckFlowMeterService{
	@Autowired
	private DailyCheckFlowMeterMapper dailyCheckFlowMeterMapper;

	@Override
	public int insertDailyCheckFlowMeter(DailyCheckFlowMeter dailyCheckFlowMeter) {
		// TODO Auto-generated method stub
		return dailyCheckFlowMeterMapper.insertDailyCheckFlowMeter(dailyCheckFlowMeter);
	}

	@Override
	public List<DailyCheckFlowMeter> selectFlowMeter(String treatMentId) {
		// TODO Auto-generated method stub
		return dailyCheckFlowMeterMapper.selectFlowMeter(treatMentId);
	}
}
