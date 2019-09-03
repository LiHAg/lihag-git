package com.golden.echarts.adapter.line;

import com.golden.echarts.adapter.Charts;
import com.golden.echarts.axis.AxisType;
import com.golden.echarts.axis.CategoryAxis;
import com.golden.echarts.axis.ValueAxis;
import com.golden.echarts.code.MarkType;
import com.golden.echarts.code.Position;
import com.golden.echarts.code.SeriesType;
import com.golden.echarts.config.EchartsConfig;
import com.golden.echarts.data.PointData;
import com.golden.echarts.option.GsonOption;
import com.golden.echarts.series.Bar;
import com.golden.echarts.series.Line;

import java.util.List;
import java.util.Map;

/**
 * 线图、柱状图适配器
 * 
 * @author jinma.zheng
 * @date 2019年4月10日
 */
public class EchartsLineAndBarAdapter extends AbstractChartsList<Map<String, Object>> {

	public static final Charts ME = new EchartsLineAndBarAdapter();

	/**
	 * 适配器入口
	 * 
	 */
	public GsonOption adapter(EchartsConfig echartsConfig, GsonOption gsonOption, List<Map<String, Object>> data) {
		if (data.isEmpty()) {
			throw new RuntimeException("Collection no data!");
		}
		gsonOption = legendAdapter(gsonOption, data);
		gsonOption = xAxisAdapter(echartsConfig, gsonOption, data);
		gsonOption = yAxisAdapter(echartsConfig, gsonOption);
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
			String[] k = key.split(":");
			// 图例组件：Y轴数据类型
			if (k[0].startsWith("y")) {
				gsonOption.legend().data(k[1]);
			}
		}
		return gsonOption;
	}

	/**
	 * x轴配置以及单位配置
	 * 
	 */
	private GsonOption xAxisAdapter(EchartsConfig echartsConfig, GsonOption gsonOption, List<Map<String, Object>> data) {
		CategoryAxis categoryAxis = new CategoryAxis();
		categoryAxis.name(echartsConfig.getXaxisName());
		if (SeriesType.line == echartsConfig.getSeriesType()) {
			categoryAxis.boundaryGap(false);
		}
		// X轴数据封装并解析
		for (Map<String, Object> map : data) {
			// 遍历Map的有关x:deX轴数据
			for (String key : map.keySet()) {
				if (key.equals("x:")) {
					categoryAxis.type(AxisType.category).data(map.get(key));
				}
			}
		}
		gsonOption.xAxis(categoryAxis);
		return gsonOption;
	}

	/**
	 * Y轴单位配置
	 * 
	 * @param echartsConfig
	 * @param gsonOption
	 * @param data
	 * @return
	 */
	private GsonOption yAxisAdapter(EchartsConfig echartsConfig, GsonOption gsonOption) {
		String[] unitNameArray = echartsConfig.getYaxisName().split(",");
		for (String s : unitNameArray) {
			ValueAxis categoryAxis = new ValueAxis();
			categoryAxis.type(AxisType.value);
			gsonOption.yAxis(categoryAxis.name(s));
		}
		return gsonOption;
	}

	/**
	 * Y轴数据处理
	 * 
	 * @param echartsConfig
	 * @param gsonOption
	 * @param data
	 * @return
	 */
	private GsonOption seriesAdapter(EchartsConfig echartsConfig, GsonOption gsonOption, List<Map<String, Object>> data) {
		for (Object legendData : gsonOption.legend().data()) {
			// echarts-type图表类型
			if (SeriesType.line == echartsConfig.getSeriesType()) {
				Line series = new Line().name((String) legendData);
				series.type(SeriesType.line);
				// 是否是面积图
				if (echartsConfig.getIsNeedStack()) {
					series.stack("总量").areaStyle().normal();
				}
				if (echartsConfig.getIsNeedMarkPoint()) {
					series.markPoint().data(new PointData().type(MarkType.max).name("最大值"), new PointData().type(MarkType.min).name("最小值"));
				}
				if (echartsConfig.getIsNeedMarkLine()) {
					series.markLine().data(new PointData().type(MarkType.average).name("平均值"), new PointData().type(MarkType.max).name("最大值"), new PointData().type(MarkType.min).name("最小值"));
				}
				// 每个图表的Y轴数据匹配
				for (Map<String, Object> map : data) {
					for (String key : map.keySet()) {
						if (key.endsWith((String) legendData)) {
							series.data(map.get(key));
							if (key.startsWith("y0")) {
								series.yAxisIndex(0);
							} else {
								series.yAxisIndex(1);
							}
						}
					}
				}
				gsonOption.series(series);
			}
			if (SeriesType.bar == echartsConfig.getSeriesType()) {
				Bar series = new Bar().name((String) legendData);
				series.type(SeriesType.bar);
				// 数量非常少时会显得傻大
				if (data.size() <= 5) {
					series.barWidth(30);
				}
				// 是否是堆砌图
				if (echartsConfig.getIsNeedStack()) {
					series.stack("总量").label().normal().show(true).position(Position.inside);
				}
				// 每个图表的Y轴数据匹配
				for (Map<String, Object> map : data) {
					for (String key : map.keySet()) {
						if (key.endsWith((String) legendData)) {
							series.data(map.get(key));
							if (key.startsWith("y0")) {
								series.yAxisIndex(0);
							} else {
								series.yAxisIndex(1);
							}
						}
					}
				}
				gsonOption.series(series);
			}
		}
		return gsonOption;
	}
}
