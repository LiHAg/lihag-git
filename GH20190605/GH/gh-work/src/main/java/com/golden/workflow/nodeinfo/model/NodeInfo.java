package com.golden.workflow.nodeinfo.model;

import java.io.Serializable;

import com.golden.workflow.common.AbstractInfo;

public class NodeInfo extends AbstractInfo implements Serializable {
	private static final long serialVersionUID = 1354628413457659786L;
	protected String templateId; // 模板Id号
	protected String nodeId; // 节点Id号
	protected String nodeType; // 节点类型（Join节点、等待事件节点等等）
	protected String nodeName;
	protected String nodeComment;
	protected String firstManualTask;
	protected String preNodeId;
	protected String nextNodeId; // 后继节点ID号
	protected String action; // 默认任务
	protected String initAction; // 默认任务
	private String inputPageName;

	public String toString() {
		return "[AbstractInfo][NodeInfo]:m_nTemplateId=" + templateId +  ",m_nNodeId=" + nodeId + ",m_nNodeType=" + nodeType + ",firstManualTask"+firstManualTask ;
	}

	public String getTemplateId() {
		return templateId;
	}

	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}

	public String getNodeId() {
		return nodeId;
	}

	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}

	public String getNodeType() {
		return nodeType;
	}

	public void setNodeType(String nodeType) {
		this.nodeType = nodeType;
	}

	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	public String getNodeComment() {
		return nodeComment;
	}

	public void setNodeComment(String nodeComment) {
		this.nodeComment = nodeComment;
	}

	public String getPreNodeId() {
		return preNodeId;
	}

	public void setPreNodeId(String preNodeId) {
		this.preNodeId = preNodeId;
	}

	public String getNextNodeId() {
		return nextNodeId;
	}

	public void setNextNodeId(String nextNodeId) {
		this.nextNodeId = nextNodeId;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getFirstManualTask() {
		return firstManualTask;
	}

	public void setFirstManualTask(String firstManualTask) {
		this.firstManualTask = firstManualTask;
	}

	public String getInputPageName() {
		return inputPageName;
	}

	public void setInputPageName(String inputPageName) {
		this.inputPageName = inputPageName;
	}

	public String getInitAction() {
		return initAction;
	}

	public void setInitAction(String initAction) {
		this.initAction = initAction;
	}

	
}