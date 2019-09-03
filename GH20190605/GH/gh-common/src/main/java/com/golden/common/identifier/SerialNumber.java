package com.golden.common.identifier;

import java.io.Serializable;

public class SerialNumber implements Serializable {
	
	private static final long serialVersionUID = 609482713271251576L;
	
	// 16位流水号
	private String serialNumber;
	
	public String getSerialNumber() {
		return serialNumber;
	}
	
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
}
