package com.golden.echarts.core;

import java.util.List;
import java.util.Map;

import com.golden.common.charts.EchartModel;
import com.golden.echarts.adapter.EchartsRadarAdapter;
import com.golden.echarts.adapter.line.EasyEchartsLineAndBarAdapter;
import com.golden.echarts.adapter.line.EchartsLineAndBarAdapter;
import com.golden.echarts.config.EchartsConfig;
import com.golden.echarts.option.GsonOption;

/**
 * 单例模式
 * 
 * @author jinma.zheng
 *
 */
public class EchartsCore {

	private static EchartsCore instance;

	private EchartsCore() {
		super();
	}

	private static synchronized void syncInit() {
		if (instance == null) {
			instance = new EchartsCore();
		}
	}

	public static EchartsCore getInstance() {
		if (instance == null) {
			syncInit();
		}
		return instance;
	}

	public GsonOption getBaseGsonOption(EchartsConfig echartsConfig) {
		EchartsBaseCore echartsBaseCore = new EchartsBaseCore();
		// 1.获取option对象
		GsonOption gsonOption = echartsBaseCore.getBaseTitle(echartsConfig);
		// 2.提示信息
		echartsBaseCore.getBaseTooltip(echartsConfig);
		// 3.工具栏处理
		//echartsBaseCore.getBarAndLineToolBox(echartsConfig);
		// 4.数据域缩放处理
		echartsBaseCore.getDataZoom(echartsConfig);
		// 5.视觉映射组件
		//echartsBaseCore.getVisualMap(echartsConfig);
		return gsonOption;
	}

	/**
	 * 柱状图和折线图适配器
	 * 
	 * @param echartsConfig
	 * @param data
	 * @return GsonOption
	 */
	public GsonOption adapterLineAndBar(EchartsConfig echartsConfig, List<Map<String, Object>> data) {
		return EchartsLineAndBarAdapter.ME.adapter(echartsConfig, this.getBaseGsonOption(echartsConfig), data);
	}
	
	

	/**
	 * 散点图适配器
	 * 
	 * @param echartsConfig-配置
	 * @param data-数据
	 * @return GsonOption
	 */
	public GsonOption adapterScatter(EchartsConfig echartsConfig, Object[][] data) {
		return com.golden.echarts.adapter.scatter.EchartsScatterAdapter.ME.adapter(echartsConfig, this.getBaseGsonOption(echartsConfig), data);
	}

	public Object adapterEasyLineAndBar(EchartsConfig echartsConfig, List<EchartModel> data) {
		return EasyEchartsLineAndBarAdapter.ME.adapter(echartsConfig, this.getBaseGsonOption(echartsConfig), data);
	}
	
	/**
	 * 雷达图适配器
	 * @param echartsConfig
	 * @param data
	 * @return GsonOption
	 */
	public GsonOption adapterRadar(EchartsConfig echartsConfig,List<Map<String,Object>> data){		
		return EchartsRadarAdapter.ME.adapter(echartsConfig,this.getBaseGsonOption(echartsConfig),data);
	}
	

}
