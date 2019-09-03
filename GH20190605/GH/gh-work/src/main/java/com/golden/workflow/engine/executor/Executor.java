package com.golden.workflow.engine.executor;

import com.golden.workflow.common.WorkflowException;
import com.golden.workflow.nodejnl.model.NodeJnl;

/**
 * @author jinma.zheng
 * @date 2019年3月31日
 */
public interface Executor {
	public String executeFragment(NodeJnl node) throws WorkflowException;
}
