package com.ciTreat.web.controller.prework;

import java.util.LinkedHashMap;

import com.golden.echarts.code.SeriesType;

/**
 * 
 * @author jinma.zheng
 *
 */
public class DeviceCalibrationModel {
	
	private String deviceId;
	
	private LinkedHashMap<String, String> formData;

	
	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public LinkedHashMap<String, String> getFormData() {
		return formData;
	}

	public void setFormData(LinkedHashMap<String, String> formData) {
		this.formData = formData;
	}

	@Override
	public String toString() {
		return "DeviceCalibrationModel [deviceId=" + deviceId + ", formData=" + formData + "]";
	}
	
}
