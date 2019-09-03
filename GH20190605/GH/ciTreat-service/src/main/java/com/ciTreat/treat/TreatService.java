package com.ciTreat.treat;

import java.util.Map;

import com.golden.workflow.common.ContextData;

/**
 * 
 * @author jinma.zheng
 * @date 2019年3月28日
 */
public interface TreatService{
	String process(Map<String, Object> map);
	String commitProcessTask(ContextData conData);
	
}
