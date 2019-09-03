package com.golden.workflow.taskjnl.model;

import java.io.Serializable;

import com.golden.workflow.common.AbstractInfo;

/**
 * 
 * @author jinma.zheng
 * @date 2019年4月1日
 */
public class TaskJnl extends AbstractInfo implements Serializable {
	private static final long serialVersionUID = -2902759359760278023L;
	// private String packageId; //包ID号
	private String templateId; // 模板ID号
	private String templateName;// 对应的机构模板名称
	private String processId; // 流程ID号
	private String processName; // 流程名称
	private String nodeId; // 任务静态ID号
	//private String nodeInstanceId; // 任务节点实例ID号
	private String taskId; // 任务节点实例ID号
	private String action; // 业务ID
	private String taskName; // 任务名称
	private int taskState; // 任务状态
	private String taskComment; // 任务注解
	private String busNo; // 业务号
	private String executorId; // 任务执行者ID号
	private String createdTime; // 任务创建时间
	private String completedTime; // 任务完成时间
	private String startedTime; // 任务开始时间
	//private String nextUser;
	//private HashMap taskContext; // 任务数据

	public String toString() {
		return "[TaskInfo]:TemplateId=" + templateId + ",m_nProcessId=" + processId + ",action=" + action + ",m_strProcessName=" + processName + ",taskId=" + taskId + ",m_nNodeId=" + nodeId + ",executorId=" + executorId + ",m_strTaskName=" + taskName + ",m_nTaskState=" + taskState + ",m_tsCreatedTime=" + createdTime + ",m_tsStartedTime=" + startedTime + ",m_tsCompletedTime=" + completedTime + ",m_strTaskComment=" + taskComment ;
	}

	public String getTemplateId() {
		return templateId;
	}

	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public String getProcessId() {
		return processId;
	}

	public void setProcessId(String processId) {
		this.processId = processId;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getProcessName() {
		return processName;
	}

	public void setProcessName(String processName) {
		this.processName = processName;
	}

	public String getNodeId() {
		return nodeId;
	}

	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}

	public String getExecutorId() {
		return executorId;
	}

	public void setExecutorId(String executorId) {
		this.executorId = executorId;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public int getTaskState() {
		return taskState;
	}

	public void setTaskState(int taskState) {
		this.taskState = taskState;
	}

	public String getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}

	public String getStartedTime() {
		return startedTime;
	}

	public void setStartedTime(String startedTime) {
		this.startedTime = startedTime;
	}

	public String getCompletedTime() {
		return completedTime;
	}

	public void setCompletedTime(String completedTime) {
		this.completedTime = completedTime;
	}

	public String getBusNo() {
		return busNo;
	}

	public void setBusNo(String busNo) {
		this.busNo = busNo;
	}


	public String getTaskComment() {
		return taskComment;
	}

	public void setTaskComment(String taskComment) {
		this.taskComment = taskComment;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

}