package com.golden.workflow.nodejnl.builder;

import com.golden.workflow.common.WorkflowException;
import com.golden.workflow.nodejnl.model.NodeJnl;

/**
 * @author jinma.zheng
 * @date 2019年3月31日
 */
public interface NodeJnlBuilder {
	public NodeJnl buildNextNode(NodeJnl curNodeInfo) throws WorkflowException;
}
