package com.golden.common.base;

import java.util.HashMap;

/**
 * 操作消息提醒
 * 
 * @author jinma.zheng
 */
public class AjaxRequest extends HashMap<String, Object> {
	private static final long serialVersionUID = 1L;

	/**
	 * 初始化一个新创建的 Message 对象
	 */
	public AjaxRequest() {
	}

	/**
	 * 返回成功消息
	 * 
	 * @param key
	 *            键值
	 * @param value
	 *            内容
	 * @return 成功消息
	 */
	@Override
	public AjaxRequest put(String key, Object value) {
		super.put(key, value);
		return this;
	}
}
