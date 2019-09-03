package com.ciTreat.dicom.model;

import java.util.Calendar;

import org.dcm4che.data.Dataset;
import org.dcm4che.dict.Tags;

import com.golden.common.base.BaseEntity;

/**
 * 
 * @author jinma.zheng
 */
public class StudyModel extends BaseEntity {
	private static final long serialVersionUID = 1L;
	// local variables
	private String patientID;
	private String patientName;
	private String patientGender;
	private String patientBirthDate;
	private String physicianName;
	private String studyDate;
	private Calendar parsedDate = null;
	private String studyTime;
	private String studyDescription;
	private String modalitiesInStudy;
	// private String[] modality;
	private String studyRelatedInstances;
	private String accessionNumber;
	private String studyInstanceUID;
	private String studyRelatedSeries;

	// default constructor
	public StudyModel() {
	}

	// constructor
	public StudyModel(Dataset ds) {
		setPatientID(ds.getString(Tags.PatientID));
		setPatientName(ds.getString(Tags.PatientName));
		setPatientGender(ds.getString(Tags.PatientSex));
		setPatientBirthDate(ds.getString(Tags.PatientBirthDate));
		setPhysicianName(ds.getString(Tags.ReferringPhysicianName));
		setStudyDate(ds.getString(Tags.StudyDate));
		setParsedDate();

		setStudyTime(ds.getString(Tags.StudyTime));
		setStudyDescription(ds.getString(Tags.StudyDescription));

		setModalitiesInStudy(ds.getStrings(Tags.ModalitiesInStudy));

		setStudyRelatedInstances(ds.getString(Tags.NumberOfStudyRelatedInstances));
		setAccessionNumber(ds.getString(Tags.AccessionNumber));
		setStudyInstanceUID(ds.getString(Tags.StudyInstanceUID));
		setStudyRelatedSeries(ds.getString(Tags.NumberOfStudyRelatedSeries));
	}

	public void setAccessionNumber(String accessionNumber) {
		this.accessionNumber = accessionNumber;
	}

	public String getAccessionNumber() {
		return accessionNumber != null ? accessionNumber : "";
	}

	public void setModalitiesInStudy(String[] modalities) {
		if (modalities != null) {
			for (int i = 0; i < modalities.length; i++) {
				if (i == 0) {
					modalitiesInStudy = modalities[i];
				} else {
					modalitiesInStudy += "\\" + modalities[i];
				}
			}
		}
	}

	public String getModalitiesInStudy() {
		return modalitiesInStudy;
	}

	public void setPatientBirthDate(String birthDate) {
		this.patientBirthDate = birthDate;
	}

	public String getPatientBirthDate() {
		return patientBirthDate != null ? patientBirthDate : "";
	}

	public void setPatientGender(String gender) {
		this.patientGender = gender;
	}

	public String getPatientGender() {
		return patientGender != null ? patientGender : "-";
	}

	public void setPatientID(String patID) {
		this.patientID = patID;
	}

	public String getPatientID() {
		return patientID;
	}

	public void setPatientName(String patName) {
		this.patientName = patName;
	}

	public String getPatientName() {
		return patientName != null ? patientName.replace("^", " ") : "";
	}

	public void setPhysicianName(String physicianName) {
		this.physicianName = physicianName;
	}

	public String getPhysicianName() {
		return physicianName != null ? physicianName : "";
	}

	public void setStudyDate(String studyDate) {
		this.studyDate = studyDate;
	}

	public String getStudyDate() {
		return studyDate != null ? studyDate : "[No study date]";
	}

	public void setStudyDescription(String studyDescription) {
		this.studyDescription = studyDescription;
	}

	public String getStudyDescription() {
		return studyDescription != null ? studyDescription.replace("^", " ") : "[No study description]";
	}

	public void setStudyInstanceUID(String studyInstanceUID) {
		this.studyInstanceUID = studyInstanceUID;
	}

	public String getStudyInstanceUID() {
		return studyInstanceUID;
	}

	public void setStudyRelatedInstances(String relatedInstances) {
		this.studyRelatedInstances = relatedInstances;
	}

	public String getStudyRelatedInstances() {
		return studyRelatedInstances != null ? studyRelatedInstances : "";
	}

	public void setStudyRelatedSeries(String relatedSeries) {
		this.studyRelatedSeries = relatedSeries;
	}

	public String getStudyRelatedSeries() {
		return studyRelatedSeries != null ? studyRelatedSeries : "";
	}

	public void setStudyTime(String studyTime) {
		this.studyTime = studyTime;
	}

	public String getStudyTime() {
		return studyTime != null ? studyTime : "";
	}

	public void setParsedDate() {
		parsedDate = Calendar.getInstance();

		if (studyDate != null && !studyDate.contains("[No study date]")) {
			try {

				String studyDate = this.studyDate.replace("-", "").replace(":", "").replace(" ", "").replace(".", "");

				parsedDate.set(Integer.parseInt(studyDate.substring(0, 4)),
						Integer.parseInt(studyDate.substring(4, 6)) - 1, Integer.parseInt(studyDate.substring(6, 8)));
			} catch (Exception ex) {
				ex.printStackTrace();
				parsedDate = null;
			}
		} else {
			parsedDate = null;
		}
	}

	public Calendar getParsedDate() {
		return parsedDate;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final StudyModel other = (StudyModel) obj;
		if ((this.studyInstanceUID == null) ? (other.studyInstanceUID != null)
				: !this.studyInstanceUID.equals(other.studyInstanceUID)) {
			return false;
		}
		return true;
	}

}