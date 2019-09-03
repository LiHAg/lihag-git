package com.golden.echarts.config;

import java.util.LinkedHashMap;

import com.golden.echarts.code.SeriesType;

/**
 * 图表公共配置选项
 * 
 * @author jinma.zheng
 *
 */
public class EchartsConfig {
	/**
	 * 图表ID(每个页面唯一就行))
	 *
	 */
	private String id = "";
	/**
	 * 图表类型
	 */
	private String type = "line";
	/**
	 * 图表类型枚举
	 */
	private SeriesType seriesType;
	/**
	 * 图表标题
	 */
	private String title = "";
	/**
	 * 图表标题链接
	 */
	private String link = "";
	/**
	 * 图表副标题
	 */
	private String subtitle = "";
	/**
	 * 图表副标题链接
	 */
	private String sublink = "";

	/**
	 * X轴单位
	 */
	private String xaxisName = "X轴单位";
	/**
	 * Y轴单位
	 */
	private String yaxisName = "Y轴单位";

	/**
	 * 是否需要工具栏
	 */
	private Boolean isNeedToolBox = false;
	/**
	 * 是否需要数据域
	 */
	private Boolean isNeedDataZoom = false;
	/**
	 * 是否需要堆积图
	 */
	private Boolean isNeedStack = false;
	/**
	 * 是否显示最大值和最小值的点
	 */
	private Boolean isNeedMarkPoint = false;
	/**
	 * 是否显示最大值和最小值、平均值的基线
	 */
	private Boolean isNeedMarkLine = false;

	/**
	 * form表单传递的参数
	 */
	private LinkedHashMap<String, String> parameters;

	/**
	 * 坐标轴的分割段数，需要注意的是这个分割段数只是个预估值，最后实际显示的段数会在这个基础上根据分割后坐标轴刻度显示的易读程度作调整。
	 */
	private Integer splitNumber = 5;
	/**
	 * 自动计算的坐标轴最小间隔大小。
	 */
	private Double minInterval = 0.0;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public EchartsConfig() {
		super();
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public SeriesType getSeriesType() {
		this.seriesType = SeriesType.valueOf(type);
		return seriesType;
	}

	public void setSeriesType(SeriesType seriesType) {
		this.seriesType = seriesType;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getSublink() {
		return sublink;
	}

	public void setSublink(String sublink) {
		this.sublink = sublink;
	}

	public String getXaxisName() {
		return xaxisName;
	}

	public void setXaxisName(String xaxisName) {
		this.xaxisName = xaxisName;
	}

	public String getYaxisName() {
		return yaxisName;
	}

	public void setYaxisName(String yaxisName) {
		this.yaxisName = yaxisName;
	}

	public Boolean getIsNeedToolBox() {
		return isNeedToolBox;
	}

	public void setIsNeedToolBox(Boolean isNeedToolBox) {
		this.isNeedToolBox = isNeedToolBox;
	}

	public Boolean getIsNeedDataZoom() {
		return isNeedDataZoom;
	}

	public void setIsNeedDataZoom(Boolean isNeedDataZoom) {
		this.isNeedDataZoom = isNeedDataZoom;
	}

	public Boolean getIsNeedStack() {
		return isNeedStack;
	}

	public void setIsNeedStack(Boolean isNeedStack) {
		this.isNeedStack = isNeedStack;
	}

	public Boolean getIsNeedMarkPoint() {
		return isNeedMarkPoint;
	}

	public void setIsNeedMarkPoint(Boolean isNeedMarkPoint) {
		this.isNeedMarkPoint = isNeedMarkPoint;
	}

	public Boolean getIsNeedMarkLine() {
		return isNeedMarkLine;
	}

	public void setIsNeedMarkLine(Boolean isNeedMarkLine) {
		this.isNeedMarkLine = isNeedMarkLine;
	}

	public LinkedHashMap<String, String> getParameters() {
		return parameters;
	}

	public void setParameters(LinkedHashMap<String, String> parameters) {
		this.parameters = parameters;
	}

	public Integer getSplitNumber() {
		return splitNumber;
	}

	public void setSplitNumber(Integer splitNumber) {
		this.splitNumber = splitNumber;
	}

	public Double getMinInterval() {
		return minInterval;
	}

	public void setMinInterval(Double minInterval) {
		this.minInterval = minInterval;
	}

	@Override
	public String toString() {
		return "EchartsConfig [id=" + id + ", type=" + type + ", title=" + title + ", link=" + link + ", subtitle=" + subtitle + ", sublink=" + sublink + ", xaxisName=" + xaxisName + ", yaxisName=" + yaxisName + ", isNeedToolBox=" + isNeedToolBox + ", isNeedDataZoom=" + isNeedDataZoom + ", isNeedStack=" + isNeedStack + ", isNeedMarkPoint=" + isNeedMarkPoint + ", isNeedMarkLine=" + isNeedMarkLine + ", parameters=" + parameters + ", splitNumber=" + splitNumber + ", minInterval=" + minInterval + "]";
	}
}
