package com.golden.workflow.engine.executor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.golden.workflow.common.AppContextProvider;
import com.golden.workflow.common.WorkflowException;
import com.golden.workflow.engine.executor.node.NodeExecutor;
import com.golden.workflow.nodejnl.builder.NodeJnlBuilder;
import com.golden.workflow.nodejnl.model.NodeJnl;

/**
 * @author jinma.zheng
 * @date 2019年3月30日
 */
@Service
public class ExecutorImpl implements Executor {
	private final static Logger logger = LoggerFactory.getLogger(ExecutorImpl.class);
	@Autowired
	NodeJnlBuilder nodeJnlBuilder;

	public String executeFragment(NodeJnl nodeJnl) throws WorkflowException {
		try {
			logger.debug("executeFragment begin,NodeID[" + nodeJnl.getNodeId() + "]");
			String nextNodeId = execute(nodeJnl);
			while (!nextNodeId.equals("-1")) {  //非手工节点
				logger.debug("begin next node:" + nextNodeId);
				nodeJnl = nodeJnlBuilder.buildNextNode(nodeJnl);
				nextNodeId = execute(nodeJnl);
				logger.debug("executeFragment nextNodeId==[" + nextNodeId + "]");
			}
			logger.debug("executeFragment end ");
			return nodeJnl.getNodeInstanceId();
		} catch (WorkflowException ex) {
			logger.error(ex.getMessage(), ex);
			// zzz setRollbackOnly();
			throw new WorkflowException(ex.getMessage());
		}
	}
	
	public String execute(NodeJnl nodeJnl) throws WorkflowException{
		String nodeType = nodeJnl.getNodeType();
		String nextNodeId = "";
		NodeExecutor nodeExecutor = (NodeExecutor) AppContextProvider.getBean(nodeType);
		nextNodeId = nodeExecutor.execute(nodeJnl);
		
		/*if(nodeType.equals("START_NODE")) {
			NodeExecutor startNodeExecutor = AppContextProvider.getBean("startNodeExecutor",StartNodeExecutor.class);
			nextNodeId = startNodeExecutor.execute(nodeJnl);
		}else if(nodeType.equals("DONE_NODE")) {
			DoneNodeExecutor doneNodeExecutor = AppContextProvider.getBean("doneNodeExecutor",DoneNodeExecutor.class);
			nextNodeId = doneNodeExecutor.execute(nodeJnl);
		}else if(nodeType.equals("AUTO_NODE")) {
			TaskNodeExecutor taskNodeExecutor = AppContextProvider.getBean("taskNodeExecutor",TaskNodeExecutor.class);
			nextNodeId = taskNodeExecutor.execute(nodeJnl);
		}else if(nodeType.equals("HAND_NODE")) {
			HandleNodeExecutor taskNodeExecutor = AppContextProvider.getBean("taskNodeExecutor",HandleNodeExecutor.class);
			nextNodeId = taskNodeExecutor.execute(nodeJnl);
		}*/
		return nextNodeId;
	}
	
	

}
