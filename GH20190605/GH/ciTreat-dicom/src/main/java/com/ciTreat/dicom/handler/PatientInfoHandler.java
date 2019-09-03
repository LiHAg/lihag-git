package com.ciTreat.dicom.handler;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Vector;

import org.dcm4che.data.Dataset;
import org.dcm4che.util.DcmURL;
import org.dcm4che2.net.service.StorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ciTreat.dicom.model.StudyModel;
import com.ciTreat.dicom.services.CDimseService;
import com.ciTreat.dicom.util.ConfigProperties;

/**
 *
 * @author jinma.zheng
 */
public class PatientInfoHandler {
	private static Logger log = LoggerFactory.getLogger(PatientInfoHandler.class);

	private String patientId = "";
	private String patientName = "";
	private String birthDate = "";
	private String modality = "";
	private String from = "";
	private String to = "";
	private String accessionNumber = "";
	private String dcmURL = "";
	private String fromTime = "";
	private String toTime = "";
	private String referPhysician = "";
	private String studyDescription = "";
	private ArrayList<StudyModel> studyList = new ArrayList<StudyModel>();

	public PatientInfoHandler() {
		studyList.clear();
	}

	public void setPatientId(String patientId) {
		if (patientId == null) {
			this.patientId = "";
		} else {
			this.patientId = patientId;
		}
	}

	public void setPatientName(String patientName) {
		if (patientName == null) {
			this.patientName = "";
		} else {
			this.patientName = patientName;
		}
	}

	public void setBirthDate(String birthDate) {
		if (birthDate == null) {
			this.birthDate = "";
		} else {
			this.birthDate = birthDate;
		}
	}

	public void setModality(String modality) {
		if (modality == null) {
			this.modality = "";
		} else {
			this.modality = modality;
		}
	}

	public void setFrom(String from) {
		if (from == null) {
			this.from = "";
		} else {
			this.from = from;
		}
	}

	public void setTo(String to) {
		if (to == null) {
			this.to = "";
		} else {
			this.to = to;
		}
	}

	public void setSearchDays(String searchDays) {
		if (searchDays == null) {
		} else {
		}
	}

	public void setAccessionNumber(String accessionNumber) {
		if (accessionNumber == null) {
			this.accessionNumber = "";
		} else {
			this.accessionNumber = accessionNumber;
		}
	}

	public void setDcmURL(String dcmURL) {
		if (dcmURL == null) {
			this.dcmURL = "";
		} else {
			this.dcmURL = dcmURL;
		}
	}

	public void setFromTime(String fromTime) {
		if (fromTime == null) {
			this.fromTime = "";
		} else {
			this.fromTime = fromTime;
		}
	}

	public void setToTime(String toTime) {
		if (toTime == null) {
			this.toTime = "";
		} else {
			this.toTime = toTime;
		}
	}

	public void setReferPhysician(String referPhysician) {
		if (referPhysician == null) {
			this.referPhysician = "";
		} else {
			this.referPhysician = referPhysician;
		}
	}

	public void setStudyDescription(String studyDescription) {
		if (studyDescription == null) {
			this.studyDescription = "";
		} else {
			this.studyDescription = studyDescription;
		}
	}

	public ArrayList<StudyModel> handle() {
		try {
			String searchDates = from + "-" + to;

			if (searchDates.trim().equals("-")) {
				searchDates = "";
			}

			String studyTime = fromTime + "-" + toTime;

			if (studyTime.trim().equals("-")) {
				studyTime = "";
			}

			callFindWithQuery(patientId, patientName, birthDate, searchDates, studyTime, modality, accessionNumber,
					referPhysician, studyDescription, dcmURL);
			return studyList;

		} catch (Exception e) {
			log.error("Error while accessing callFindWithQuery method of PatientInfo.", e);
			e.printStackTrace();
			return null;
		}

	}

	private void callFindWithQuery(String searchPatientID, String searchPatientName, String searchDob,
			String searchDate, String studyTime, String searchModality, String accessionNumber, String referPhysician,
			String studyDesc, String dcmURL) {

		ConfigProperties cfgProperties;

		try {
			cfgProperties = new ConfigProperties(
					CDimseService.class.getClassLoader().getResource("CDimseService.cfg"));
		} catch (IOException ioe) {
			log.error("Unable to create ConfigProperties instance ", ioe);
			return;
		}

		try {
			cfgProperties.put("key.PatientID", searchPatientID);

			cfgProperties.put("key.PatientName", searchPatientName + "*");

			if (searchDate.length() > 0) {
				cfgProperties.put("key.StudyDate", searchDate);
			}

			if (studyTime.length() > 0) {
				cfgProperties.put("key.StudyTime", studyTime);
			}

			if (searchDob.length() > 0) {
				cfgProperties.put("key.PatientBirthDate", searchDob);
			}

			if (accessionNumber.length() > 0) {
				cfgProperties.put("key.AccessionNumber", accessionNumber);
			}

			if (referPhysician.length() > 0) {
				cfgProperties.put("key.ReferringPhysicianName", referPhysician);
			}

			if (studyDesc.length() > 0) {
				cfgProperties.put("key.StudyDescription", studyDesc);
			}

			searchModality = searchModality.toUpperCase();
			if (!searchModality.equalsIgnoreCase("ALL")) {
				cfgProperties.put("key.ModalitiesInStudy", searchModality);
			}
		} catch (Exception e) {
			log.error("Unable to set key values for query ", e);
			return;
		}

		doQuery(cfgProperties, dcmURL);
	}

	private void callFindWithQuery(String patientID, String studyUID, String dcmUrl) {

		ConfigProperties cfgProperties;

		try {
			cfgProperties = new ConfigProperties(CDimseService.class.getClassLoader().getResource("/resources/CDimseService.cfg"));
		} catch (IOException ioe) {
			log.error("Unable to create ConfigProperties instance ", ioe);
			return;
		}
		try {
			if (patientID.length() > 0) {
				cfgProperties.put("key.PatientID", patientID);
			}

			if (studyUID != null) {
				cfgProperties.put("key.StudyInstanceUID", studyUID);
			}
		} catch (Exception e) {
			log.error("Unable to set key values for query ", e);
			return;
		}

		doQuery(cfgProperties, dcmUrl);

	}

	private void doQuery(ConfigProperties cfgProperties, String dcmUrl) {
		boolean isOpen;
		Vector dsVector;
		CDimseService cDimseService;
		DcmURL url = new DcmURL(dcmUrl);

		try {
			System.out.println(cfgProperties.toString());
			System.out.println("url=" + url);
			cDimseService = new CDimseService(cfgProperties, url);
		} catch (ParseException pe) {
			log.error("Unable to create CDimseService instance ", pe);
			return;
		}

		try {
			isOpen = cDimseService.aASSOCIATE();
			if (!isOpen) {
				return;
			}
		} catch (IOException e) {
			log.error("Error while opening association ", e);
			return;
		} catch (GeneralSecurityException gse) {
			log.error("Error while opeing association ", gse);
			return;
		}

		try {
			dsVector = cDimseService.cFIND();
		} catch (Exception e) {
			log.error("Error while calling cFIND() ", e);
			return;
		}

		for (int dsCount = 0; dsCount < dsVector.size(); dsCount++) {
			try {
				Dataset dataSet = (Dataset) dsVector.elementAt(dsCount);
				StudyModel studyModel = new StudyModel(dataSet);
				studyList.add(studyModel);
			} catch (Exception e) {
				log.error("Error while adding Dataset in studyList ", e);
			}
		}

		try {
			cDimseService.aRELEASE(true);
		} catch (IOException e) {
			log.equals(e.getMessage());
		} catch (InterruptedException ie) {
			log.error(ie.getMessage());
		}

	}

	public ArrayList<StudyModel> getStudyList() {
		return studyList;
	}

}