package com.golden.workflow.processjnl.model;

import java.io.Serializable;

import com.golden.workflow.common.AbstractInfo;

public class ProcessJnl extends AbstractInfo implements Serializable {
	private static final long serialVersionUID = 4197714189981434960L;
	private String templateId; // 所属模板Id号
	private String processId; // 流程Id号
	private String processName; // 流程名称
	private String processComment; // 流程的注解信息
	private int processState; // 流程的状态，四种之一（挂起、运行、终止、完成）
	private String initiatorId; // 启动者Id号
	private String startedTime; // 流程启动时间戳
	private String completedTime; // 流程完成时间戳
	private String busNo; // 业务号
	private String contextData;

	public String getTemplateId() {
		return templateId;
	}

	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}

	public String getProcessId() {
		return processId;
	}

	public void setProcessId(String processId) {
		this.processId = processId;
	}

	public String getProcessName() {
		return processName;
	}

	public void setProcessName(String processName) {
		this.processName = processName;
	}

	public String getInitiatorId() {
		return initiatorId;
	}

	public void setInitiatorId(String initiatorId) {
		this.initiatorId = initiatorId;
	}

	public int getProcessState() {
		return processState;
	}

	public void setProcessState(int processState) {
		this.processState = processState;
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

	public String getProcessComment() {
		return processComment;
	}

	public void setProcessComment(String processComment) {
		this.processComment = processComment;
	}

	public String getBusNo() {
		return busNo;
	}

	public void setBusNo(String busNo) {
		this.busNo = busNo;
	}

	public String getContextData() {
		return contextData;
	}

	public void setContextData(String contextData) {
		this.contextData = contextData;
	}



}