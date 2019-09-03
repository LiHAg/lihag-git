package com.ciTreat.quality.domain;

import com.golden.common.base.BaseEntity;

public class CheckItem extends BaseEntity {
	private static final long serialVersionUID = 1L;
	private Integer checkItemId;
	private String checkItemData;
	private String remarks;
	private Integer checkStatus;
	public Integer getCheckStatus() {
		return checkStatus;
	}
	public void setCheckStatus(Integer checkStatus) {
		this.checkStatus = checkStatus;
	}
	public Integer getCheckItemId() {
		return checkItemId;
	}
	public void setCheckItemId(Integer checkItemId) {
		this.checkItemId = checkItemId;
	}
	public String getCheckItemData() {
		return checkItemData;
	}
	public void setCheckItemData(String checkItemData) {
		this.checkItemData = checkItemData;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	@Override
	public String toString() {
		return "CheckItem [checkItemId=" + checkItemId + ", checkItemData=" + checkItemData + ", remarks=" + remarks
				+ ", checkStatus=" + checkStatus + "]";
	}
	

}
