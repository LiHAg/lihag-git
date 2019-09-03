package com.golden.workflow.nodejnl.model;

import java.io.Serializable;
import java.util.Map;

import com.golden.workflow.nodeinfo.model.NodeInfo;

public class NodeJnl extends NodeInfo implements Serializable {
	private static final long serialVersionUID = 1354628413457659786L;
	protected String processId; // 流程Id号
	protected String nodeInstanceId; // 实例节点id号
	protected int nodeState; // 节点状态（暂停、挂起等）
	protected String busNo; // 业务号
	protected String userId; // 业务处理用户
    protected Map<String,Object> nodeContext;

	public NodeJnl(NodeInfo nodeInfo) {
		super.templateId = nodeInfo.getTemplateId();
		super.nodeId = nodeInfo.getNodeId();
		super.nodeType = nodeInfo.getNodeType();
		super.nodeName = nodeInfo.getNodeName();
		super.nodeComment = nodeInfo.getNodeComment();
		super.preNodeId = nodeInfo.getPreNodeId();
		super.nextNodeId = nodeInfo.getNextNodeId();
		super.action = nodeInfo.getAction();
		super.firstManualTask = nodeInfo.getFirstManualTask();
	}

	public int getNodeState() {
		return nodeState;
	}

	public void setNodeState(int nodeState) {
		this.nodeState = nodeState;
	}

	public String getNodeInstanceId() {
		return nodeInstanceId;
	}

	public void setNodeInstanceId(String nodeInstanceId) {
		this.nodeInstanceId = nodeInstanceId;
	}

	public String getProcessId() {
		return processId;
	}

	public void setProcessId(String processId) {
		this.processId = processId;
	}

	public String getBusNo() {
		return busNo;
	}

	public void setBusNo(String busNo) {
		this.busNo = busNo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Map<String,Object> getNodeContext() {
		return nodeContext;
	}

	public void setNodeContext(Map<String,Object> nodeContext) {
		this.nodeContext = nodeContext;
	}
	
	

	/*
	 * public String toString() { return "[AbstractInfo][NodeInfo]:m_nPackageId=" +
	 * m_nPackageId + ",m_nTemplateId=" + m_nTemplateId + ",m_nProcessId=" +
	 * m_nProcessId + ",nodeInstanceId=" + nodeInstanceId + ",m_nNodeId=" +
	 * m_nNodeId + ",m_nNodeType=" + m_nNodeType + ",m_nNodeState=" + m_nNodeState +
	 * ",m_NodeObject=>" + m_NodeObject.toString() + ",verId=" + verId +
	 * ",Expression:" + Expression; }
	 */

}