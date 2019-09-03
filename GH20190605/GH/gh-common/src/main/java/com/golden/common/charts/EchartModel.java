package com.golden.common.charts;

/**
 * @author jinma.zheng
 * @date 2019年4月18日
 */
public class EchartModel {
	String x;
	Object y;

	public EchartModel(String x, Object y) {
		this.x = x;
		this.y = y;
	}

	public String getX() {
		return x;
	}

	public void setX(String x) {
		this.x = x;
	}

	public Object getY() {
		return y;
	}

	public void setY(Object y) {
		this.y = y;
	}

}
