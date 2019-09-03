package com.golden.echarts.core;

import com.golden.echarts.DataZoom;
import com.golden.echarts.code.DataZoomType;
import com.golden.echarts.code.PointerType;
import com.golden.echarts.code.SeriesType;
import com.golden.echarts.code.Trigger;
import com.golden.echarts.config.EchartsConfig;
import com.golden.echarts.option.GsonOption;

/**
 * 
 * @author jinma.zheng
 *
 */
public class EchartsBaseCore {
	
	private GsonOption gsonOption;

	/**
	 * 标题组件，包含主标题和副标题以及链接
	 * @param echartsConfig
	 * @return
	 */
	public GsonOption getBaseTitle(EchartsConfig echartsConfig){
		gsonOption = new GsonOption();
		gsonOption.title().text(echartsConfig.getTitle());
		return gsonOption;
	}
	
	/**
	 * 坐标轴触发，主要使用类目轴的图表中使用
	 * @param echartsConfig
	 * @return
	 */
	public GsonOption getBaseTooltip(EchartsConfig echartsConfig){
		SeriesType seriesType = echartsConfig.getSeriesType();
		switch(seriesType){
			case line:
			//case radar: gsonOption.tooltip().trigger(Trigger.axis);break;
			case bar: gsonOption.tooltip().trigger(Trigger.axis).axisPointer().type(PointerType.shadow);break;
			case pie: gsonOption.tooltip().trigger(Trigger.item)
					  .formatter("{a} <br/>{b} : {c} ({d}%)")
					  .position("function(p){return [p[0]-120,p[1]-70];}");break;
			//case map: gsonOption.tooltip().trigger(Trigger.item);break;
			case scatter: gsonOption.tooltip().trigger(Trigger.axis).axisPointer().type(PointerType.cross);break;
		}
		return gsonOption;
	}
	
	
	/**
	 * 是否显示数据区域
	 */
	public GsonOption getDataZoom(EchartsConfig echartsConfig){
		Boolean show = echartsConfig.getIsNeedDataZoom();
		if(show){
			
			//gsonOption.toolbox().feature(Tool.dataZoom);
			DataZoom dataZoomInside= new DataZoom();
			dataZoomInside.start(0).end(100);
			gsonOption.dataZoom().add(dataZoomInside);
			
			DataZoom dataZoomOutside= new DataZoom();
			dataZoomOutside.start(0).end(10).handleSize(80);
			gsonOption.dataZoom().add(dataZoomOutside);
			
			//图表内部也可以操作dataZoom
			DataZoom dataZoomType= new DataZoom();
			dataZoomType.type(DataZoomType.inside);
			gsonOption.dataZoom().add(dataZoomType);
		}
		return gsonOption;
	}

	
	public GsonOption getVisualMap(EchartsConfig echartsConfig){
		// map需要特殊处理...jm.z
		return gsonOption;
	}
	
	
}
