package com.golden.echarts.adapter.scatter;

import com.golden.echarts.adapter.Charts;
import com.golden.echarts.axis.AxisType;
import com.golden.echarts.axis.ValueAxis;
import com.golden.echarts.code.LineType;
import com.golden.echarts.code.Position;
import com.golden.echarts.config.EchartsConfig;
import com.golden.echarts.model.scatter.Scatter;
import com.golden.echarts.option.GsonOption;

/**
 * scatter的适配器
 * @author jinma.zheng
 *
 */
public class EchartsScatterAdapter extends AbstractChartsArray {

	public static final Charts ME = new EchartsScatterAdapter();

	/**
	 * scatter的适配器入口
	 * 
	 * @param echartsConfig
	 * @param gsonOption
	 * @param data
	 * @return
	 */
	public GsonOption adapter(EchartsConfig echartsConfig, GsonOption gsonOption, Object[][] data) {
		gsonOption = xyAxisAdapter(gsonOption);
		gsonOption = seriesAdapter(echartsConfig, gsonOption, data);
		return gsonOption;
	}

	/**
	 * x轴配置和Y轴配置
	 * 
	 * @param gsonOption
	 * @return
	 */
	private GsonOption xyAxisAdapter(GsonOption gsonOption) {
		ValueAxis valueAxis = new ValueAxis();
		valueAxis.type(AxisType.value);
		valueAxis.splitLine().lineStyle().type(LineType.dashed);
		gsonOption.xAxis(valueAxis);
		gsonOption.yAxis(valueAxis);
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
	@SuppressWarnings("unchecked")
	private GsonOption seriesAdapter(EchartsConfig echartsConfig, GsonOption gsonOption, Object[][] data) {
		Scatter scatter = new Scatter();
		scatter.name("scatter").label().emphasis().show(true).position(Position.left).textStyle().color("blue").fontSize(16);
		for (int num = 0; num < data.length; num++) {
			scatter.data().add(data[num]);
		}
		gsonOption.series(scatter);
		return gsonOption;
	}
}
