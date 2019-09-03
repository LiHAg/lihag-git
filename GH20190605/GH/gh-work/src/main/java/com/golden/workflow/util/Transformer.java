package com.golden.workflow.util;

import java.util.LinkedHashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author jinma.zheng
 * @date 2019年3月29日
 */
public class Transformer {
	public static String mapToJsonString(Map<String, Object> context) {
		try {
			return new ObjectMapper().writeValueAsString(context);
		} catch (JsonProcessingException jpe) {
			jpe.printStackTrace();
			// logger.error("mapToJsonString error\n");
			return "";
		}
	}

	public static Map<String, Object> readContext(String contextInfo) {
		try {
			return new ObjectMapper().readValue(contextInfo, Map.class);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String linkedHashmapToJsonString(LinkedHashMap<String, String> formData) {
		try {
			return new ObjectMapper().writeValueAsString(formData);
		} catch (JsonProcessingException jpe) {
			jpe.printStackTrace();
			// logger.error("mapToJsonString error\n");
			return "";
		}
		
	}
}
