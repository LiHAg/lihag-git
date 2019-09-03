/**
 * @author jinma.zheng
 * @date 2019年3月29日
 */
package com.golden.workflow.common;

import java.util.HashMap;
import java.util.Map;

/**
 * @author jinma.zheng
 * @date 2019年3月29日
 */
public class ContextData {
	Map<String, Object> parms = new HashMap<String, Object>();
	// Map<String, Object> parms = new HashMap<String, Object>();
	public String get(String key) {
		String value = (String) parms.get(key);
		return value;
	}

	public void put(String key, String value) {
		parms.put(key, value);
	}
	public void putAll(Map<String, Object> parms) {
		this.parms.putAll(parms);
	}

	public String toString() {
		String rtn = "\n ContextData" + "\n parms=" + parms;
		return rtn;
	}
	
	public Map<String, Object> getMap() {
		return this.parms;
	}

	public void putAllStr(Map<String, String> parms) {
		this.parms.putAll(parms);
	}

}
