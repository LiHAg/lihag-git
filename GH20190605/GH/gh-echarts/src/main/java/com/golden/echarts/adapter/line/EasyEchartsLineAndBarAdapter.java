package com.golden.echarts.adapter.line;

import java.util.List;

import com.golden.common.charts.EchartModel;
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

/**
 * 简单线图、柱状图适配器
 * 
 * @author jinma.zheng
 * @date 2019年4月10日
 */
public class EasyEchartsLineAndBarAdapter extends AbstractChartsList<EchartModel> {

	public static final Charts ME = new EasyEchartsLineAndBarAdapter();

	/**
	 * 适配器入口
	 * 
	 */
	public GsonOption adapter(EchartsConfig echartsConfig, GsonOption gsonOption, List<EchartModel> data) {
		if (data.isEmpty()) {
			throw new RuntimeException("Collection no data!");
		}
		gsonOption.legend().data(echartsConfig.getYaxisName());
		gsonOption = xAxisAdapter(echartsConfig, gsonOption, data);
		gsonOption = yAxisAdapter(echartsConfig, gsonOption);
		gsonOption = seriesAdapter(echartsConfig, gsonOption, data);
		return gsonOption;
	}

	/**
	 * x轴配置以及单位配置
	 * 
	 */
	private GsonOption xAxisAdapter(EchartsConfig echartsConfig, GsonOption gsonOption, List<EchartModel> data) {
		CategoryAxis categoryAxis = new CategoryAxis();
		categoryAxis.name(echartsConfig.getXaxisName());
		if (SeriesType.line == echartsConfig.getSeriesType()) {
			categoryAxis.boundaryGap(false);
		}
		// X轴数据封装并解析
		for (EchartModel em : data) {
			categoryAxis.type(AxisType.category).data(em.getX());
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
	private GsonOption seriesAdapter(EchartsConfig echartsConfig, GsonOption gsonOption, List<EchartModel> data) {
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
				series.yAxisIndex(0);
				//series.yAxisIndex(1);
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
				for (EchartModel em : data) {
					series.data(em.getY());
					series.yAxisIndex(0);
					//series.yAxisIndex(1);
				}
				gsonOption.series(series);
			}
		}
		return gsonOption;
	}
}
