package com.ciTreat.treat.domain;

import com.golden.common.base.BaseEntity;

public class TreatJnl extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	private String patientId;
	private long num;
	//任务代码
	private String taskCode;
	//任务流水号
	private String busNo;
	//状态
	private String status;
	//开始时间
	private String startTime;
	//结束时间
	private String endTime;
	//上下文信息
	private String contextInfo;
	
	private String taskComment;
	private String nodeId;
	private String processId;
	private String taskId;
	
	public String getNodeId() {
		return nodeId;
	}
	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}
	public String getProcessId() {
		return processId;
	}
	public void setProcessId(String processId) {
		this.processId = processId;
	}
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public String getTaskComment() {
		return taskComment;
	}
	public void setTaskComment(String taskComment) {
		this.taskComment = taskComment;
	}
	public String getPatientId() {
		return patientId;
	}
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	public long getNum() {
		return num;
	}
	public void setNum(long num) {
		this.num = num;
	}
	public String getTaskCode() {
		return taskCode;
	}
	public void setTaskCode(String taskCode) {
		this.taskCode = taskCode;
	}
	public String getBusNo() {
		return busNo;
	}
	public void setBusNo(String busNo) {
		this.busNo = busNo;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getContextInfo() {
		return contextInfo;
	}
	public void setContextInfo(String contextInfo) {
		this.contextInfo = contextInfo;
	}
	
	@Override
	public String toString() {
		return "TreatJnl [patientId=" + patientId + ", num=" + num + ", taskCode=" + taskCode + ", busNo=" + busNo + ", status=" + status + ", startTime=" + startTime + ", endTime=" + endTime + ", contextInfo=" + contextInfo + ", taskComment=" + taskComment + ", nodeId=" + nodeId + ", processId=" + processId + ", taskId=" + taskId + "]";
	}
	
}
