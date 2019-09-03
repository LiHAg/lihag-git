package com.golden.echarts.adapter.line;

import com.golden.echarts.config.EchartsConfig;
import com.golden.echarts.option.GsonOption;

import java.util.List;

/**
 * 
 * @author jinma.zheng
 * @date 2019年4月10日
 */
public abstract class AbstractChartsList<T> implements ChartsList<T> {
	@Override
	public GsonOption adapter(EchartsConfig echartsConfig, GsonOption gsonOption, List<T> data) {
		return new GsonOption();
	}
}
