package com.golden.echarts.adapter.line;

import com.golden.echarts.adapter.Charts;
import com.golden.echarts.config.EchartsConfig;
import com.golden.echarts.option.GsonOption;

import java.util.List;

/**
 * charts List数据适配器接口
 * 
 * @author jinma.zheng
 * @date 2019年4月10日
 */
public interface ChartsList<T> extends Charts<List<T>> {
	/**
	 * 适合图表类型： EchartsLineAndBarAdapter EchartsReverseBarAdapter EchartsRadarAdapter
	 * HighChartsBar3dAdapter
	 * 
	 * @param echartsConfig
	 * @param gsonOption
	 * @param data
	 * @return
	 */
	GsonOption adapter(EchartsConfig echartsConfig, GsonOption gsonOption, List<T> data);
}
