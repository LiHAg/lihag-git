package com.golden.platform.expression;

import java.util.Map;

/**
 * 
 * @author jinma.zheng
 * @date 2019年4月1日
 */
public interface ExpressionEvaluator {

	// 根据表达式取得的值
	public Object getValue(Map<String, Object> context, String expression);
	//public String evaluateTemplate(Object root, String expression);
	//public String evaluateTemplate(Map<String, Object> context, Object root, String expression);
	//public String getLanguage();
	// public Object getValue(Object root, String expression);
	// public <T> T getValue(Object root, String expression, Class<T> resultType);
	// public <T> T getValue(Map<String, Object> context, Object root, String expression, Class<T> resultType);
}
