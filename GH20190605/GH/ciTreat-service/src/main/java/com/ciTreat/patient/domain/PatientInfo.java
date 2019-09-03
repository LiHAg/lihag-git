package com.ciTreat.patient.domain;

import com.golden.common.base.BaseEntity;

public class PatientInfo extends BaseEntity {
	private static final long serialVersionUID = 1L;
	private String patientId;
	private String patientName;
	private String sex;
	private long totalNum;
	private long totalDose;

	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
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

	public long getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(long totalNum) {
		this.totalNum = totalNum;
	}

	public long getTotalDose() {
		return totalDose;
	}

	public void setTotalDose(long totalDose) {
		this.totalDose = totalDose;
	}

	

}
