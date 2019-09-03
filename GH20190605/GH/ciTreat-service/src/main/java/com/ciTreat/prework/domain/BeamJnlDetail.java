package com.ciTreat.prework.domain;

import java.io.Serializable;
import java.util.Date;

import com.golden.common.base.BaseEntity;

/**
 * @author LIANYI
 * 束流标定明细表
 */
public class BeamJnlDetail extends BaseEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String patientId;
	private String PatientName;
	private String planId;
	private String operator;
	private String executeDate;
	private Integer preDose;
	private Integer reaDose;
	private Integer ic1Num;
	private Integer ic2Num;
	private Integer ic3Num;
	private Integer ic1CalibFactor;
	private Integer ic2CalibFactor;
	private Integer ic3CalibFactor;
	private String beamNo;
	private String status;
	
	private String taskId;
	private String taskComment;
	private int taskState;
	private String nodeId;
	private String processId;
	
	
	public String getPatientId() {
		return patientId;
	}
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	public String getPatientName() {
		return PatientName;
	}
	public void setPatientName(String PatientName) {
		this.PatientName = PatientName;
	}
	public String getPlanId() {
		return planId;
	}
	public void setPlanId(String planId) {
		this.planId = planId;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public String getExecuteDate() {
		return executeDate;
	}
	public void setExecuteDate(String executeDate) {
		this.executeDate = executeDate;
	}
	public Integer getPreDose() {
		return preDose;
	}
	public void setPreDose(Integer preDose) {
		this.preDose = preDose;
	}
	public Integer getReaDose() {
		return reaDose;
	}
	public void setReaDose(Integer reaDose) {
		this.reaDose = reaDose;
	}
	public Integer getIc1Num() {
		return ic1Num;
	}
	public void setIc1Num(Integer ic1Num) {
		this.ic1Num = ic1Num;
	}
	public Integer getIc2Num() {
		return ic2Num;
	}
	public void setIc2Num(Integer ic2Num) {
		this.ic2Num = ic2Num;
	}
	public Integer getIc3Num() {
		return ic3Num;
	}
	public void setIc3Num(Integer ic3Num) {
		this.ic3Num = ic3Num;
	}
	public Integer getIc1CalibFactor() {
		return ic1CalibFactor;
	}
	public void setIc1CalibFactor(Integer ic1CalibFactor) {
		this.ic1CalibFactor = ic1CalibFactor;
	}
	public Integer getIc2CalibFactor() {
		return ic2CalibFactor;
	}
	public void setIc2CalibFactor(Integer ic2CalibFactor) {
		this.ic2CalibFactor = ic2CalibFactor;
	}
	public Integer getIc3CalibFactor() {
		return ic3CalibFactor;
	}
	public void setIc3CalibFactor(Integer ic3CalibFactor) {
		this.ic3CalibFactor = ic3CalibFactor;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getBeamNo() {
		return beamNo;
	}
	public void setBeamNo(String beamNo) {
		this.beamNo = beamNo;
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
	public int getTaskState() {
		return taskState;
	}
	public void setTaskState(int taskState) {
		this.taskState = taskState;
	}
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
	@Override
	public String toString() {
		return "BeamJnlDetail [patientId=" + patientId + ", PatientName=" + PatientName + ", planId=" + planId + ", operator=" + operator + ", executeDate=" + executeDate + ", preDose=" + preDose + ", reaDose=" + reaDose + ", ic1Num=" + ic1Num + ", ic2Num=" + ic2Num + ", ic3Num=" + ic3Num + ", ic1CalibFactor=" + ic1CalibFactor + ", ic2CalibFactor=" + ic2CalibFactor + ", ic3CalibFactor=" + ic3CalibFactor + ", createTime=" + getCreateTime() + ", updateTime=" + getUpdateTime() + ", beamNo=" + beamNo + ", status=" + status
				+ ", taskId=" + taskId + ", taskComment=" + taskComment + ", taskState=" + taskState + ", nodeId=" + nodeId + ", processId=" + processId + "]";
	}
	
}
