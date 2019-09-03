package com.golden.workflow.engine;

import com.golden.workflow.common.ContextData;
import com.golden.workflow.common.WorkflowException;

/**
 * 
 * @author jinma.zheng
 * @date 2019年3月27日
 */
public interface WorkflowAction {
	public String createProcess(ContextData contextData) throws WorkflowException;
	public String executeStartNode(ContextData contextData);
	public String commitProcessTask(ContextData contextData);
	// public void process(Map<String, Object> map);
}