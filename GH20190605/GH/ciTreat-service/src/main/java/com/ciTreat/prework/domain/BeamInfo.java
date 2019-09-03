package com.ciTreat.prework.domain;

import java.io.Serializable;
import java.util.Date;

import com.golden.common.base.BaseEntity;

/**
 * @author LIANYI
 * 束流标定信息表
 */
public class BeamInfo extends BaseEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String patientId;
	private String PatientName;
	private String planId;
	private String treatHead;
	private String energy;
	private String ridgeFilter;
	private String rangeShifter;
	private Integer ic1CalibFactor;
	private Integer ic2CalibFactor;
	private Integer ic3CalibFactor;
	
	
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
	public String getTreatHead() {
		return treatHead;
	}
	public void setTreatHead(String treatHead) {
		this.treatHead = treatHead;
	}
	public String getEnergy() {
		return energy;
	}
	public void setEnergy(String energy) {
		this.energy = energy;
	}
	public String getRidgeFilter() {
		return ridgeFilter;
	}
	public void setRidgeFilter(String ridgeFilter) {
		this.ridgeFilter = ridgeFilter;
	}
	public String getRangeShifter() {
		return rangeShifter;
	}
	public void setRangeShifter(String rangeShifter) {
		this.rangeShifter = rangeShifter;
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
	@Override
	public String toString() {
		return "BeamInfo [patientId=" + patientId + ", PatientName=" + PatientName + ", planId=" + planId + ", treatHead=" + treatHead + ", energy=" + energy + ", ridgeFilter=" + ridgeFilter + ", rangeShifter=" + rangeShifter + ", ic1CalibFactor=" + ic1CalibFactor + ", ic2CalibFactor=" + ic2CalibFactor + ", ic3CalibFactor=" + ic3CalibFactor + "]";
	}
	
}
