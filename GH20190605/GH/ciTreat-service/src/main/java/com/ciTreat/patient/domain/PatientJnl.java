package com.ciTreat.patient.domain;

import com.golden.common.base.BaseEntity;

public class PatientJnl extends BaseEntity {
	private static final long serialVersionUID = 1L;
	private String patientId;
	private String beamStatus;
	private String treatStatus;
	private long totalNum;
	private long treatedNum;
	private String taskSrlNum;
	
	private String patientName;
	private String sex;
	private String planName;
	private String planDate;
	private String treatType;
	private String treatHead;
	private long totalDose;
	private long treatedDose;
	private String testFlag;
	
	public String getTestFlag() {
		return testFlag;
	}

	public void setTestFlag(String testFlag) {
		this.testFlag = testFlag;
	}

	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public String getBeamStatus() {
		return beamStatus;
	}

	public void setBeamStatus(String beamStatus) {
		this.beamStatus = beamStatus;
	}

	public String getTreatStatus() {
		return treatStatus;
	}

	public void setTreatStatus(String treatStatus) {
		this.treatStatus = treatStatus;
	}

	public long getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(long totalNum) {
		this.totalNum = totalNum;
	}

	public long getTreatedNum() {
		return treatedNum;
	}

	public void setTreatedNum(long treatedNum) {
		this.treatedNum = treatedNum;
	}

	public String getTaskSrlNum() {
		return taskSrlNum;
	}

	public void setTaskSrlNum(String taskSrlNum) {
		this.taskSrlNum = taskSrlNum;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getPlanName() {
		return planName;
	}

	public void setPlanName(String planName) {
		this.planName = planName;
	}

	public String getPlanDate() {
		return planDate;
	}

	public void setPlanDate(String planDate) {
		this.planDate = planDate;
	}

	public String getTreatType() {
		return treatType;
	}

	public void setTreatType(String treatType) {
		this.treatType = treatType;
	}

	public String getTreatHead() {
		return treatHead;
	}

	public void setTreatHead(String treatHead) {
		this.treatHead = treatHead;
	}

	public long getTotalDose() {
		return totalDose;
	}

	public void setTotalDose(long totalDose) {
		this.totalDose = totalDose;
	}

	public long getTreatedDose() {
		return treatedDose;
	}

	public void setTreatedDose(long treatedDose) {
		this.treatedDose = treatedDose;
	}

	@Override
	public String toString() {
		return "PatientJnl [patientId=" + patientId + ", beamStatus=" + beamStatus + ", treatStatus=" + treatStatus + ", totalNum=" + totalNum + ", treatedNum=" + treatedNum + ", taskSrlNum=" + taskSrlNum + ", patientName=" + patientName + ", sex=" + sex + ", planName=" + planName + ", planDate=" + planDate + ", treatType=" + treatType + ", treatHead=" + treatHead + ", totalDose=" + totalDose + ", treatedDose=" + treatedDose + ", testFlag=" + testFlag + "]";
	}
	
}
