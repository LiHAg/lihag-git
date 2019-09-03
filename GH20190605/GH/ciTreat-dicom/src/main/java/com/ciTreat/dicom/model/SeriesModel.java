
package com.ciTreat.dicom.model;

import java.io.Serializable;

import org.dcm4che.data.Dataset;
import org.dcm4che.dict.Tags;

/**
 *
 * @author jinma.zheng
 */
public class SeriesModel implements Serializable {

	private static final long serialVersionUID = 1L;
	// variables
	private String seriesIUID;
	private String seriesNumber;
	private String seriesDate;
	private String seriesTime;
	private String seriesDescription;
	private String modality;
	private String numberOfInstances;
	private String bodyPartExamined;

	public SeriesModel(Dataset ds) {
		setSeriesIUID(ds.getString(Tags.SeriesInstanceUID));
		setSeriesNumber(ds.getString(Tags.SeriesNumber));
		setSeriesDate(ds.getString(Tags.SeriesDate));
		setSeriesTime(ds.getString(Tags.SeriesTime));
		setSeriesDescription(ds.getString(Tags.SeriesDescription));
		setModality(ds.getString(Tags.Modality));
		setNumberOfInstances(ds.getString(Tags.NumberOfSeriesRelatedInstances));
		setBodyPartExamined(ds.getString(Tags.BodyPartExamined));
	}

	public SeriesModel() {

	}

	public String getSeriesIUID() {
		return seriesIUID;
	}

	public void setSeriesIUID(String seriesIUID) {
		this.seriesIUID = seriesIUID;
	}

	public String getSeriesNumber() {
		return seriesNumber != null ? seriesNumber : "unknown";
	}

	public void setSeriesNumber(String seriesNumber) {
		this.seriesNumber = seriesNumber;
	}

	public String getSeriesDate() {
		return seriesDate != null ? seriesDate : "";
	}

	public void setSeriesDate(String seriesDate) {
		this.seriesDate = seriesDate;
	}

	public String getSeriesTime() {
		return seriesTime != null ? seriesTime : "";
	}

	public void setSeriesTime(String seriesTime) {
		this.seriesTime = seriesTime;
	}

	public String getSeriesDescription() {
		return seriesDescription != null ? seriesDescription.replace("^", " ") : "";
	}

	public void setSeriesDescription(String seriesDesc) {
		this.seriesDescription = seriesDesc;
	}

	public String getModality() {
		return modality != null ? modality : "unknown";
	}

	public void setModality(String modality) {
		this.modality = modality;
	}

	public String getNumberOfInstances() {
		return numberOfInstances != null ? numberOfInstances : "";
	}

	public void setNumberOfInstances(String NoInstances) {
		this.numberOfInstances = NoInstances;
	}

	public String getBodyPartExamined() {
		return bodyPartExamined != null ? bodyPartExamined : "";
	}

	public void setBodyPartExamined(String bodyPart) {
		this.bodyPartExamined = bodyPart;
	}

}