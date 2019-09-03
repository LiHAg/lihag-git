package com.ciTreat.quality.mapper;

import java.util.List;

import com.ciTreat.quality.domain.DailyCheckFlowMeter;

public interface DailyCheckFlowMeterMapper {
	/**查询所有流水*/
	public List<DailyCheckFlowMeter> selectFlowMeter(String treatMentId);
	/**插入流水*/
	public int insertDailyCheckFlowMeter(DailyCheckFlowMeter dailyCheckFlowMeter);
}
