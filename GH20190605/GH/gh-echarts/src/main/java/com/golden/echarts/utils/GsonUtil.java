package com.golden.echarts.utils;

import java.lang.reflect.Type;

import com.golden.echarts.axis.Axis;
import com.golden.echarts.model.scatter.Series;
import com.golden.echarts.option.Option;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

/**
 * @author jinma.zheng
 */
public class GsonUtil {

	/**
	 * 格式化对象为Json
	 *
	 * @param object
	 * @return
	 */
	public static String format(Object object) {
		Gson gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().registerTypeAdapter(java.util.Date.class, new UtilDateSerializer()).setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		JsonParser jp = new JsonParser();
		JsonElement je = jp.parse(gson.toJson(object));
		String prettyJsonString = gson.toJson(je);
		// 简单处理function
		String[] lines = prettyJsonString.split("\n");
		lines = replaceFunctionQuote(lines);
		StringBuilder stringBuilder = new StringBuilder();
		for (String line : lines) {
			stringBuilder.append(line);
		}
		return stringBuilder.toString();
	}

	/**
	 * 格式化对象为Json
	 *
	 * @param object
	 * @return
	 */
	public static String prettyFormat(Object object) {
		Gson gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().registerTypeAdapter(java.util.Date.class, new UtilDateSerializer()).setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		JsonParser jp = new JsonParser();
		JsonElement je = jp.parse(gson.toJson(object));
		String prettyJsonString = gson.toJson(je);
		// 简单处理function
		String[] lines = prettyJsonString.split("\n");
		lines = replaceFunctionQuote(lines);
		StringBuilder stringBuilder = new StringBuilder();
		for (String line : lines) {
			stringBuilder.append(line + "\n");
		}
		return stringBuilder.toString();
	}

	/**
	 * 处理字符串中的function和(function(){})()，除{}中的代码外，其他地方不允许有空格
	 *
	 * @param lines
	 * @return
	 */
	public static String[] replaceFunctionQuote(String[] lines) {
		boolean function = false;
		boolean immediately = false;
		for (int i = 0; i < lines.length; i++) {
			String line = lines[i].trim();
			if (!function && line.contains("\"function")) {
				function = true;
				line = line.replaceAll("\"function", "function");
			}
			if (function && line.contains("}\"")) {
				function = false;
				line = line.replaceAll("\\}\"", "\\}");
			}

			if (!immediately && line.contains("\"(function")) {
				immediately = true;
				line = line.replaceAll("\"\\(function", "\\(function");
			}
			if (immediately && line.contains("})()\"")) {
				immediately = false;
				line = line.replaceAll("\\}\\)\\(\\)\"", "\\}\\)\\(\\)");
			}
			lines[i] = line;
		}
		return lines;
	}

	/**
	 * 反序列化
	 *
	 * @param json
	 * @param type
	 * @param <T>
	 * @return
	 */
	public static <T extends Option> T fromJSON(String json, Class<T> type) {
		Gson gson = new GsonBuilder().setPrettyPrinting().registerTypeAdapter(Series.class, new SeriesDeserializer()).registerTypeAdapter(Axis.class, new AxisDeserializer()).create();
		return gson.fromJson(json, type);
	}

	/**
	 * 反序列化
	 *
	 * @param json
	 * @return
	 */
	public static Option fromJSON(String json) {
		Gson gson = new GsonBuilder().setPrettyPrinting().registerTypeAdapter(Series.class, new SeriesDeserializer()).registerTypeAdapter(Axis.class, new AxisDeserializer()).create();
		Option option = gson.fromJson(json, Option.class);
		return option;
	}

	/**
	 * 输出Json
	 *
	 * @param object
	 * @return
	 */
	public static void print(Object object) {
		System.out.println(format(object));
	}

	/**
	 * 输出Json
	 *
	 * @param object
	 * @return
	 */
	public static void printPretty(Object object) {
		System.out.println(prettyFormat(object));
	}

}

/**
 * 序列化日期类型的实现类
 * 
 * @author Administrator
 *
 */
class UtilDateSerializer implements JsonSerializer<java.util.Date> {

	public JsonElement serialize(java.util.Date src, Type typeOfSrc, JsonSerializationContext context) {
		return new JsonPrimitive(src.getTime());
	}

}

/**
 * 反序列化日期类型的实现类
 * 
 * @author Administrator
 */
class UtilDateDeserializer implements JsonDeserializer<java.util.Date> {
	public java.util.Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
		return new java.util.Date(json.getAsJsonPrimitive().getAsLong());
	}
}
