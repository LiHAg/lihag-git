package com.golden.echarts.adapter.scatter;

import com.golden.echarts.config.EchartsConfig;
import com.golden.echarts.option.GsonOption;

/**
 * 
 * @author jinma.zheng
 * @date 2019年4月10日
 */
public abstract class AbstractChartsArray implements ChartsArray {
	@Override
	public GsonOption adapter(EchartsConfig echartsConfig, GsonOption gsonOption, Object[][] data) {
		return new GsonOption();
	}
}
