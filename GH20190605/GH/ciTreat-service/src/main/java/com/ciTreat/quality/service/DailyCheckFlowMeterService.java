package com.ciTreat.quality.service;

import java.util.List;

import com.ciTreat.quality.domain.DailyCheckFlowMeter;
/**
 * 每日检查流水 服务层
 * 
 * @author LQY
 */
public interface DailyCheckFlowMeterService {
	/**查询所有流水*/
	public List<DailyCheckFlowMeter> selectFlowMeter(String treatMentId);
	/**新增流水*/
	public int insertDailyCheckFlowMeter(DailyCheckFlowMeter dailyCheckFlowMeter);
}
