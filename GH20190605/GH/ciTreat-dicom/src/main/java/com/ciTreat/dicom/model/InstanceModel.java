package com.ciTreat.dicom.model;

import java.io.Serializable;

import org.dcm4che.data.Dataset;
import org.dcm4che.dict.Tags;

/**
 * 
 * @author jinma.zheng
 */
public class InstanceModel implements Serializable {

	private static final long serialVersionUID = 1L;
	// Variables
	private String sopIUID;
	private String instanceNumber;
	private String sopClassUID;
	private String numberOfFrames;
	private String thumbSize;

	public InstanceModel(Dataset ds) {
		setSopIUID(ds.getString(Tags.SOPInstanceUID));
		setInstanceNumber(ds.getString(Tags.InstanceNumber));
		setSopClassUID(ds.getString(Tags.SOPClassUID));
		setNumberOfFrames(ds.getString(Tags.NumberOfFrames));
		int rows, columns;
		rows = ds.getInteger(Tags.Rows) != null ? ds.getInteger(Tags.Rows) : 512;
		columns = ds.getInteger(Tags.Columns) != null ? ds.getInteger(Tags.Columns) : 512;
		calculateThumbSize(String.valueOf(rows), String.valueOf(columns));
	}

	public InstanceModel() {

	}

	public String getSopIUID() {
		return sopIUID;
	}

	public void setSopIUID(String sopIUID) {
		this.sopIUID = sopIUID;
	}

	public String getInstanceNumber() {
		return instanceNumber != null ? instanceNumber : "unknown";
	}

	public void setInstanceNumber(String instanceNo) {
		this.instanceNumber = instanceNo;
	}

	public String getSopClassUID() {
		return sopClassUID != null ? sopClassUID : "unknown";
	}

	public void setSopClassUID(String sopClassUID) {
		this.sopClassUID = sopClassUID;
	}

	public String getNumberOfFrames() {
		return numberOfFrames != null ? numberOfFrames : "0";
	}

	public void setNumberOfFrames(String NoFrames) {
		this.numberOfFrames = NoFrames;
	}

	public String getThumbSize() {
		return thumbSize;
	}

	public void calculateThumbSize(String row, String column) {
		int rowSize = Integer.valueOf(row);
		int columnSize = Integer.valueOf(column);

		if (columnSize > rowSize) {
			thumbSize = "width: 70px;";
		} else {
			thumbSize = "height: 70px;";
		}
	}
}