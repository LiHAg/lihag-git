package com.ciTreat.dev.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.golden.common.base.BaseEntity;


/**
 * @author LIANYI
 * 
 * 治疗室数据表
 */
public class DevTreatmentRoom extends BaseEntity {

	private static final long serialVersionUID = 1L;

	private String treatMentId;
	
	private String treatMentName;

	private String description;
	
	private String validFg;
	
	private Integer checkStatus;
	
	public Integer getCheckStatus() {
		return checkStatus;
	}

	public void setCheckStatus(Integer checkStatus) {
		this.checkStatus = checkStatus;
	}

	public String getValidFg() {
		return validFg;
	}

	public void setValidFg(String validFg) {
		this.validFg = validFg;
	}

	public String getTreatMentId() {
		return treatMentId;
	}

	public void setTreatMentId(String treatMentId) {
		this.treatMentId =treatMentId;
	}

	public String getTreatMentName() {
		return treatMentName;
	}

	public void setTreatMentName(String treatMentName) {
		this.treatMentName = treatMentName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "DevTreatmentRoom [treatMentId=" + treatMentId + ", treatMentName=" + treatMentName + ", description="
				+ description + ", validFg=" + validFg + ", checkStatus=" + checkStatus + "]";
	}

	
}
