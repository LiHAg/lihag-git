package com.golden.workflow.engine.action;

import com.golden.workflow.common.WorkflowException;
import com.golden.workflow.nodejnl.model.NodeJnl;

/**
 * @author jinma.zheng
 * @date 2019年4月6日
 */
public interface Action {
	public boolean execute(NodeJnl node) throws WorkflowException;
}
