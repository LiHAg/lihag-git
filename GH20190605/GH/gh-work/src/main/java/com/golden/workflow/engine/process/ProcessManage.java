package com.golden.workflow.engine.process;

import com.golden.workflow.common.ContextData;
import com.golden.workflow.common.WorkflowException;

/**
 * @author jinma.zheng
 * @date 2019年3月30日
 */
public interface ProcessManage {
	String createProcess(ContextData conData) throws WorkflowException;
	String executeStartNode(ContextData conData) throws WorkflowException;
	String commitProcessTask(ContextData conData);
	void stopProcess(String nProcessId) throws WorkflowException;
}
