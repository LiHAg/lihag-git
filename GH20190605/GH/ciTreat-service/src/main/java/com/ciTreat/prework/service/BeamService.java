package com.ciTreat.prework.service;

import java.util.Map;

import com.golden.workflow.common.ContextData;

/**
 * @author lianyi
 * @date 2019年4月18日
 */
public interface BeamService{
	String process(Map<String, Object> map);
	String commitProcessTask(ContextData conData);
	
}
