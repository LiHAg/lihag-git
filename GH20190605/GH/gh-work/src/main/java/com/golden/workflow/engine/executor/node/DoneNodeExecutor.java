package com.golden.workflow.engine.executor.node;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.golden.workflow.common.WorkflowException;
import com.golden.workflow.engine.action.Action;
import com.golden.workflow.engine.process.ProcessManage;
import com.golden.workflow.nodejnl.model.NodeJnl;

/**
 * 
 * @author jinma.zheng
 * @date 2019年3月31日
 */
@Service("DONE_NODE")
public class DoneNodeExecutor implements NodeExecutor {
	private static Logger log = LoggerFactory.getLogger(DoneNodeExecutor.class.getName());
	@Autowired
	private ProcessManage processService;
	@Autowired
	private Action doneAction;

	public String execute(NodeJnl nodeJnl) throws WorkflowException {
		log.debug("done node execute begin " + nodeJnl.getNodeName());
		try {
			// nodeQueueLocal.deleteNode(this.m_nProcessId, abstractNode.getNodeId());//若是异步
			// 则从队列中删除节点jm.z
			// .deleteByProcessId(this.m_nProcessId);//清空运行时节点 jm.z
			// .deleteEventWatch(m_nProcessId);
			doneAction.execute(nodeJnl);
			log.debug("done node end." + nodeJnl.getNodeName());
			processService.stopProcess(nodeJnl.getProcessId());
			return "-1";
		} catch (WorkflowException ex) {
			log.error("execute error:" + ex.getMessage());
			throw ex;
		} catch (Exception ex) {
			ex.printStackTrace();
			log.error("execute error:" + ex.getMessage());
			throw new WorkflowException("done node execute:" + ex.getMessage());
		}
	}
}