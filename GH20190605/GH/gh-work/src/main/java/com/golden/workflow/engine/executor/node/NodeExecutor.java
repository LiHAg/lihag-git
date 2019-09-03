package com.golden.workflow.engine.executor.node;

import com.golden.workflow.common.WorkflowException;
import com.golden.workflow.nodejnl.model.NodeJnl;

/**
 * @author jinma.zheng
 * @date 2019年3月30日
 */
public interface NodeExecutor {
	public String execute(NodeJnl nodeJnl) throws WorkflowException;
}
