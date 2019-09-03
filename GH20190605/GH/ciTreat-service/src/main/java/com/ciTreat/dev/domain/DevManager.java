package com.ciTreat.dev.domain;


import com.golden.common.base.BaseEntity;


/**
 * @author LIANYI
 * 
 * 治疗室数据表
 */
public class DevManager extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	private String deviceId;
	
	private String treatmentId;
	
	private String deviceName;
	
	/**工作站名称*/
	private String stationName;
	
	/**使用地址*/
	private String institutionDddress;
	
	/**设备制造商*/
	private String manufacturer;
	
	/**设备型号*/
	private String modelName;
	
	/**设备序列号*/
	private String deviceSerialNumber;
	
	/**设备软件版本*/
	private String softwareVersion;
	
	/**操作人员*/
	private String operatorName;
	
	/**操作人员电话*/
	private String operatorTelephone;
	
	/**设备描述*/
	private String description;
	
	/**设备标定状态*/
	private String status;
	
	/**开始使用时间*/
	private String startUseDate;
	
	/**最后巡检日期*/
	private String lastUpdate;
	
	/**标定详细信息*/
	private String details;
	
	/**标定页面*/
	private String page;

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	
	public String getTreatmentId() {
		return treatmentId;
	}

	public void setTreatmentId(String treatmentId) {
		this.treatmentId = treatmentId;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getStationName() {
		return stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

	public String getInstitutionDddress() {
		return institutionDddress;
	}

	public void setInstitutionDddress(String institutionDddress) {
		this.institutionDddress = institutionDddress;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public String getDeviceSerialNumber() {
		return deviceSerialNumber;
	}

	public void setDeviceSerialNumber(String deviceSerialNumber) {
		this.deviceSerialNumber = deviceSerialNumber;
	}

	public String getSoftwareVersion() {
		return softwareVersion;
	}

	public void setSoftwareVersion(String softwareVersion) {
		this.softwareVersion = softwareVersion;
	}

	public String getOperatorName() {
		return operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}

	public String getOperatorTelephone() {
		return operatorTelephone;
	}

	public void setOperatorTelephone(String operatorTelephone) {
		this.operatorTelephone = operatorTelephone;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStartUseDate() {
		return startUseDate;
	}

	public void setStartUseDate(String startUseDate) {
		this.startUseDate = startUseDate;
	}

	public String getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(String lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}
	
	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	@Override
	public String toString() {
		return "DevManager [deviceId=" + deviceId + ", treatmentId=" + treatmentId + ", deviceName=" + deviceName + ", stationName=" + stationName + ", institutionDddress=" + institutionDddress + ", manufacturer=" + manufacturer + ", modelName=" + modelName + ", deviceSerialNumber=" + deviceSerialNumber + ", softwareVersion=" + softwareVersion + ", operatorName=" + operatorName + ", operatorTelephone=" + operatorTelephone + ", description=" + description + ", status=" + status + ", startUseDate=" + startUseDate
				+ ", lastUpdate=" + lastUpdate + ", details=" + details + ", page=" + page + "]";
	}

}
