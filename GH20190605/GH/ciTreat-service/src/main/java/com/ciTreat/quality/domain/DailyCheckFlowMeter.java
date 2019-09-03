package com.ciTreat.quality.domain;

import java.util.Date;

import com.golden.common.base.BaseEntity;

public class DailyCheckFlowMeter extends BaseEntity{
	private static final long serialVersionUID = 1L;
	private String flowMeterId;
	private Date createTime;
	private Integer checkStatus;
	private String treatMentId;
	public String getFlowMeterId() {
		return flowMeterId;
	}
	public void setFlowMeterId(String flowMeterId) {
		this.flowMeterId = flowMeterId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Integer getCheckStatus() {
		return checkStatus;
	}
	public void setCheckStatus(Integer checkStatus) {
		this.checkStatus = checkStatus;
	}
	public String getTreatMentId() {
		return treatMentId;
	}
	public void setTreatMentId(String treatMentId) {
		this.treatMentId = treatMentId;
	}
	@Override
	public String toString() {
		return "DailyCheckFlowMeter [flowMeterId=" + flowMeterId + ", createTime=" + createTime + ", checkStatus="
				+ checkStatus + ", treatMentId=" + treatMentId + "]";
	}
	
	
}
