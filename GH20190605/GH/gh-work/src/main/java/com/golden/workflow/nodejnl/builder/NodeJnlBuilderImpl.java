package com.golden.workflow.nodejnl.builder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.golden.workflow.common.WorkflowException;
import com.golden.workflow.nodeinfo.model.NodeInfo;
import com.golden.workflow.nodeinfo.service.NodeInfoService;
import com.golden.workflow.nodejnl.model.NodeJnl;
import com.golden.workflow.util.FlowRefBuilder;

/**
 * 
 * @author jinma.zheng
 * @date 2019年3月31日
 */
@Service
public class NodeJnlBuilderImpl implements NodeJnlBuilder{
	private static Logger logger = LoggerFactory.getLogger(NodeJnlBuilderImpl.class.getName());
	@Autowired
	private NodeInfoService nodeService;
	
	public NodeJnl buildNextNode(NodeJnl curNodeInfo) throws WorkflowException {
		try {
			String processid = curNodeInfo.getProcessId();
			String nextNodeId = curNodeInfo.getNextNodeId();
			String tempID = curNodeInfo.getTemplateId();
			logger.debug("buildNode start :nProcessId==>>" + processid + "  nStaticNodeId==>>" + nextNodeId);
			NodeInfo nextNodeInfo = nodeService.getNodeInfo(nextNodeId);
			logger.debug("nodeInfo==>>" + nextNodeInfo);
			NodeJnl rtNodeInfo = new NodeJnl(nextNodeInfo);
			rtNodeInfo.setProcessId(processid);
			rtNodeInfo.setBusNo(curNodeInfo.getBusNo());
			rtNodeInfo.setUserId(curNodeInfo.getUserId());
			rtNodeInfo.setNodeInstanceId(FlowRefBuilder.getInstanceNodeId());
			rtNodeInfo.setNodeContext(curNodeInfo.getNodeContext());
			logger.debug("buildNode end.");
			return rtNodeInfo;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new WorkflowException(ex.getMessage());
		}
	}
}