package com.golden.platform.utils;


import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.golden.common.constant.Constants;

/**
 * 
 * @author jinma.zheng
 * @date 2019年4月1日
 */
public class JsonMapper {
	private static volatile JsonMapper instance = new JsonMapper();
	private ObjectMapper mapper;
	private String dateFormat = Constants.DATETIME_FORMAT;
	private boolean prettyPrint = false;

	public JsonMapper() {
		mapper = new ObjectMapper();
		mapper.setDateFormat(new SimpleDateFormat(dateFormat));
		mapper.configure(SerializationFeature.INDENT_OUTPUT, this.prettyPrint);
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		mapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
		mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
		mapper.configure(Feature.ALLOW_SINGLE_QUOTES, true);
		mapper.setSerializationInclusion(Include.NON_NULL);

		SimpleModule testModule = new SimpleModule();
		//testModule.addSerializer(new MoneySerializer());
		//testModule.addDeserializer(Money.class, new MoneyDeserializer());
		mapper.registerModule(testModule);		 
		instance = this;
	}

	public void init() {
		mapper.setDateFormat(new SimpleDateFormat(dateFormat));
		mapper.configure(SerializationFeature.INDENT_OUTPUT, this.prettyPrint);
	}

	public TypeFactory getTypeFactory(){
		return this.mapper.getTypeFactory();
	}
	public <T> T readValue(Reader input, Class<T> clazz) throws IOException {
		return this.mapper.readValue(input, clazz);
	}

	public <T> T readValue(Reader input, JavaType type) throws IOException {
		return this.mapper.readValue(input, type);
	}

	public <T> T readValue(Reader input, TypeReference<T> valueTypeRef) throws IOException {
		return this.mapper.readValue(input, valueTypeRef);
	}

	public <T> T readValue(Reader input, Type type) throws IOException {
		return this.mapper.readValue(input, mapper.getTypeFactory().constructType(type));
	}

	public <T> T readValue(String input, Class<T> clazz) throws IOException {
		return this.mapper.readValue(input, clazz);
	}

	public <T> T readValue(String input, JavaType type) throws IOException {
		return this.mapper.readValue(input, type);
	}

	public <T> T readValue(String input, TypeReference<T> valueTypeRef) throws IOException {
		return this.mapper.readValue(input, valueTypeRef);
	}

	public <T> T readValue(String input, Type type) throws IOException {
		return this.mapper.readValue(input, mapper.getTypeFactory().constructType(type));
	}

	public <T> T readValue(JsonNode node, Class<T> clazz) throws IOException {
		ObjectReader reader = mapper.reader(clazz);
		return reader.readValue(node);
	}

	public <T> T readValue(JsonNode node, JavaType type) throws IOException {
		ObjectReader reader = mapper.reader(type);
		return reader.readValue(node);
	}

	public <T> T readValue(JsonNode node, TypeReference<T> valueTypeRef) throws IOException {
		ObjectReader reader = mapper.reader(valueTypeRef);
		return reader.readValue(node);
	}

	public <T> T readValue(JsonNode node, Type type) throws IOException {
		ObjectReader reader = mapper.reader(mapper.getTypeFactory().constructType(type));
		return reader.readValue(node);
	}

	public JsonNode readTree(Reader input) throws IOException {
		return this.mapper.readTree(input);
	}

	public JsonNode readTree(String input) throws IOException {
		return this.mapper.readTree(input);
	}

	public void writeValue(Object value, Writer writer) throws IOException {
		this.mapper.writeValue(writer, value);
	}

	public String writeValue(Object value) throws IOException {
		return this.mapper.writeValueAsString(value);
	}

	public <T> T convertValue(Object fromValue, Class<T> type) {
		if (fromValue == null)
			return null;
		if (type.isAssignableFrom(fromValue.getClass()))
			return (T) fromValue;
		return this.mapper.convertValue(fromValue, type);
	}

	public <T> T convertValue(Object fromValue, Type type) {
		if (fromValue == null)
			return null;
		return this.mapper.convertValue(fromValue, mapper.getTypeFactory().constructType(type));
	}

	public <T> T convertValue(Object fromValue, TypeReference<T> typeReference) {
		if (fromValue == null)
			return null;
		return this.mapper.convertValue(fromValue, typeReference);
	}

	public <T> T convertValue(Object fromValue, JavaType type) {
		if (fromValue == null)
			return null;
		return this.mapper.convertValue(fromValue, type);
	}

	public void setDateFormat(String dateFormat) {
		this.dateFormat = dateFormat;
	}

	public void setPrettyPrint(boolean prettyPrint) {
		this.prettyPrint = prettyPrint;
	}

	public static JsonMapper getInstance() {
		return instance;
	}

	
	
	public static Object convert(Object object, String className) throws ClassNotFoundException{
		Class clazz = Class.forName(className);
		return instance.convertValue(object, clazz);
	}
	
	public static void main(String[] args){
		JsonMapper mapper = new JsonMapper();
		Date date = mapper.convertValue("20160119145911", java.util.Date.class);
		System.out.println("Date:" + date);
	}
}
