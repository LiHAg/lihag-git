package com.golden.platform.expression.context;

import java.util.Map;

import org.springframework.expression.AccessException;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.PropertyAccessor;
import org.springframework.expression.TypedValue;

/**
 * 
 * @author jinma.zheng
 * @date 2019年4月2日
 */
public class MapPropertyAccessor implements PropertyAccessor {

	public boolean canRead(EvaluationContext context, Object target, String name) throws AccessException {
		if (target == null)
			return false;
		if (target instanceof Map) {
			return true;
		}
		return false;
	}

	public TypedValue read(EvaluationContext context, Object target, String name) throws AccessException {
		if (target == null) {
			throw new AccessException("Cannot read property of null target");
		}
		if (!(target instanceof Map))
			throw new AccessException("Target is not a map");
		Map<?, ?> map = (Map<?, ?>) target;
		Object value = null;
		if ("values".equals(name))
			value = map.values();
		else if ("keySet".equals(name))
			value = map.keySet();
		else if ("entrySet".equals(name))
			value = map.entrySet();
		else if ("empty".equals(name))
			value = map.isEmpty();
		else if ("size".equals(name))
			value = map.size();
		else
			value = map.get(name);
		return new TypedValue(value);
	}

	public boolean canWrite(EvaluationContext context, Object target, String name) throws AccessException {
		if (target == null) {
			return false;
		}
		if (target instanceof Map)
			return true;
		return false;
	}

	@SuppressWarnings("unchecked")
	public void write(EvaluationContext context, Object target, String name, Object newValue) throws AccessException {
		if (target == null) {
			throw new AccessException("Cannot write property on null target");
		}
		if (!(target instanceof Map))
			throw new AccessException("Target is not a map");
		Map map = (Map) target;
		map.put(name, newValue);
	}

	public Class[] getSpecificTargetClasses() {
		return new Class[] { Map.class };
	}
}
