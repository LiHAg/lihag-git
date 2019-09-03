package com.golden.echarts.adapter;

import java.util.List;
import java.util.Map;

import com.golden.echarts.Polar;
import com.golden.echarts.adapter.line.AbstractChartsList;
import com.golden.echarts.code.SeriesType;
import com.golden.echarts.code.Trigger;
import com.golden.echarts.config.EchartsConfig;
import com.golden.echarts.data.Data;
import com.golden.echarts.option.GsonOption;
import com.golden.echarts.series.Line;

/**
 * 
 * @author jinma.zheng
 *
 */
public class EchartsRadarAdapter extends AbstractChartsList<Map<String, Object>> {

	public static final Charts ME = new EchartsRadarAdapter();

	/**
	 * 适配器入口
	 * 
	 * @param echartsConfig
	 * @param gsonOption
	 * @param data
	 * @return
	 */
	public GsonOption adapter(EchartsConfig echartsConfig, GsonOption gsonOption, List<Map<String, Object>> data) {
		if (data.isEmpty()) {
			throw new RuntimeException("Collection no data!");
		}
		gsonOption = legendAdapter(gsonOption, data);
		gsonOption = radarAdapter(gsonOption, data);
		gsonOption = seriesAdapter(echartsConfig, gsonOption, data);
		return gsonOption;
	}

	/**
	 * legendAdapter
	 * 
	 * @param gsonOption
	 * @param data
	 * @return
	 */
	private GsonOption legendAdapter(GsonOption gsonOption, List<Map<String, Object>> data) {
		// 获取第一个List中的Map中的值
		for (String key : data.get(0).keySet()) {
			// 图例组件：Y轴数据类型
			if (key.startsWith("y:")) {
				gsonOption.legend().data(key.replace("y:", ""));
			}
		}
		return gsonOption;
	}

	/**
	 * radarAdapter
	 * 
	 * @param gsonOption
	 * @param data
	 * @return
	 */
	private GsonOption radarAdapter(GsonOption gsonOption, List<Map<String, Object>> data) {
		Polar polar = new Polar();
		// 获取第一个List中的Map中的值
		for (Map<String, Object> map : data) {
			// 图例组件：Y轴数据类型
			polar.indicator(new Data().text(map.get("x:name").toString()).max(map.get("x:max")));
		}
		gsonOption.polar(polar);
		return gsonOption;
	}

	/**
	 * radius轴数据处理
	 * 
	 * @param echartsConfig
	 * @param gsonOption
	 * @param orientData
	 * @return
	 */
	private GsonOption seriesAdapter(EchartsConfig echartsConfig, GsonOption gsonOption, List<Map<String, Object>> orientData) {
		Line line = new Line();
		line.tooltip().trigger(Trigger.item);
		line.type(SeriesType.radar).name("预算 vs 开销（Budget vs spending）");
		for (Object legendData : gsonOption.legend().data()) {
			Data dataArr = new Data().name((String) legendData);
			Object[] obj = new Object[orientData.size()];
			for (int i = 0; i < orientData.size(); i++) {
				Map<String, Object> legendMap = orientData.get(i);
				obj[i] = legendMap.get("y:" + legendData);

			}
			dataArr.value(obj);
			line.data(dataArr);
		}
		gsonOption.series(line);
		return gsonOption;
	}
}
