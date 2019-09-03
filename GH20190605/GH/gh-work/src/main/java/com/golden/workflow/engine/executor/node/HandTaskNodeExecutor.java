package com.golden.workflow.engine.executor.node;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.golden.workflow.common.WorkflowException;
import com.golden.workflow.engine.action.Action;
import com.golden.workflow.nodejnl.model.NodeJnl;

/**
 * 
 * @author jinma.zheng
 * @date 2019年3月31日
 */
@Service("HAND_TASK_NODE")
public class HandTaskNodeExecutor implements NodeExecutor {
	private static Logger logger = LoggerFactory.getLogger(HandTaskNodeExecutor.class.getName());
	@Autowired
	private Action handAction;

	public String toString() {
		return "[AbstractNode][TaskNode]:" + super.toString();
	}

	public String execute(NodeJnl nodeJnl) throws WorkflowException {
		try {
			logger.debug("task node begin:" + nodeJnl.getNodeName() + "Action=" + nodeJnl.getAction());
			boolean bResult = false;
			bResult = handAction.execute(nodeJnl);
			if (bResult) {
				return nodeJnl.getNextNodeId();
			} else {
				return "-1";
			}
		} catch (Exception ex) {
			logger.error("tasknode execute error:" + ex.getMessage(), ex);
			throw new WorkflowException(ex.getMessage());
		}
	}

}