package com.golden.echarts.model.scatter;

/**
 * @author jinma.zheng
 */
public interface Data<T> {
	/**
	 * 添加元素
	 *
	 * @param values
	 * @return
	 */
	T data(Object... values);
}
