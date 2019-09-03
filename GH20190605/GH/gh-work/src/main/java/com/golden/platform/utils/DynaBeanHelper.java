package com.golden.platform.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

/**
 * 
 * @author jinma.zheng
 * @date 2019年4月1日
 */
public class DynaBeanHelper {
	private static Logger logger = LoggerFactory.getLogger(DynaBeanHelper.class);

	public static Map<Object, Object> createMap(Object... objects) {
		Map<Object, Object> result = new HashMap<Object, Object>();
		if (objects == null)
			return result;
		for (int i = 0; i < objects.length / 2; i++) {
			result.put(objects[2 * i], objects[2 * i + 1]);
		}
		return result;
	}

	public static Object[] createArray(Object... objects) {
		if (objects == null)
			return new Object[] {};
		Object[] result = new Object[objects.length];
		for (int i = 0; i < objects.length; i++) {
			result[i] = objects[i];
		}
		return result;
	}

	public static List<Object> createList(Object... objects) {
		if (objects == null)
			return new ArrayList<Object>();
		List<Object> result = new ArrayList<Object>(objects.length);
		for (int i = 0; i < objects.length; i++) {
			result.add(objects[i]);
		}
		return result;
	}

	public static Object setProperties(Object target, Map<String, Object> properties) {
		if (target == null)
			return target;
		if (target instanceof Map) {
			((Map) target).putAll(properties);
		} else {
			BeanWrapper wrapper = new BeanWrapperImpl(target);
			wrapper.setPropertyValues(properties);
		}
		return target;
	}
}
