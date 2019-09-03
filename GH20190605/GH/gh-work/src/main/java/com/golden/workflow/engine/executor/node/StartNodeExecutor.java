package com.golden.workflow.engine.executor.node;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.golden.workflow.common.WorkflowException;
import com.golden.workflow.engine.action.TaskJnlUtils;
import com.golden.workflow.engine.action.Action;
import com.golden.workflow.engine.executor.ExecutorImpl;
import com.golden.workflow.nodejnl.model.NodeJnl;

/**
 * @author jinma.zheng
 * @date 2019年3月30日
 */
@Service("START_NODE")
public class StartNodeExecutor implements NodeExecutor {
	private final static Logger logger = LoggerFactory.getLogger(ExecutorImpl.class);
	@Autowired
	private Action startAction;

	public String execute(NodeJnl nodeJnl) throws WorkflowException {
		try {
			logger.debug("StartNode execute ==>>" + this.toString());
			boolean bResult = false;
			String nextNodeId;
			bResult = startAction.execute(nodeJnl);
			if (!bResult) {
				nextNodeId = "-1";
			} else {
				nextNodeId = nodeJnl.getNextNodeId();
			}

			logger.debug("StartNode execute end ." + nodeJnl.getNodeName());
			return nextNodeId;
		} catch (Exception e) {
			throw new WorkflowException("StartNode node execute:" + e.getMessage());
		}
	}
}
