package com.golden.echarts.adapter.scatter;

import com.golden.echarts.adapter.Charts;
import com.golden.echarts.config.EchartsConfig;
import com.golden.echarts.option.GsonOption;

/**
 * charts 数组数据适配器接口
 * @author jinma.zheng
 * @date 2019年4月10日
 */
public interface ChartsArray extends Charts<Object[][]> {
	/**
	 * 适合图表类型： EchartsScatterAdapter
	 * 
	 * @param echartsConfig
	 * @param gsonOption
	 * @param data
	 * @return
	 */
	GsonOption adapter(EchartsConfig echartsConfig, GsonOption gsonOption, Object[][] data);
}
