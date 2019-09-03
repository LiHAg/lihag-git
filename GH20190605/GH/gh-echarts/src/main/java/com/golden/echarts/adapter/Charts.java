package com.golden.echarts.adapter;

import com.golden.echarts.config.EchartsConfig;
import com.golden.echarts.option.GsonOption;

/**
 * Charts图表接口
 * 
 * @author jinma.zheng
 * @date 2019年4月10日
 */
public interface Charts<T> {
	/**
	 * 将前台配置EchartsConfig和后台数据T进行整合 通过ajax输出给页面GsonOption的json数据格式
	 */
	GsonOption adapter(EchartsConfig echartsConfig, GsonOption gsonOption, T data);
}
