package com.ciTreat.dicom.handler;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Vector;
import java.text.ParseException;

import org.dcm4che.data.Dataset;
import org.dcm4che.util.DcmURL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ciTreat.dicom.model.InstanceModel;
import com.ciTreat.dicom.services.CDimseService;
import com.ciTreat.dicom.util.ConfigProperties;
import com.ciTreat.dicom.util.InstanceComparator;

/**
 * 
 * @author jinma.zheng
 */
public class ImageHandler {
	private static Logger log = LoggerFactory.getLogger(ImageHandler.class);
	private String patientId;
	private String study;
	private String series;
	private String dcmURL;
	private ArrayList<InstanceModel> instancesList;

	public void setPatientId(String patientId) {
		if (patientId == null) {
			this.patientId = "";
		} else {
			this.patientId = patientId;
		}
	}

	public void setStudy(String study) {
		this.study = study;
	}

	public void setSeries(String series) {
		this.series = series;
	}

	public void setDcmURL(String dcmURL) {
		this.dcmURL = dcmURL;
	}

	public ArrayList<InstanceModel> handle() {
		try {
			callFindWithQuery(patientId, study, series, null, dcmURL);
			// Collections.sort(instancesList, new InstanceComparator());
			return instancesList;
		} catch (Exception e) {
			log.error("Unable to create instance of ImageInfo and access the callFindWithQuery()", e);
			return null;
		}

	}

	public ImageHandler() {
		instancesList = new ArrayList<InstanceModel>();
	}

	public void callFindWithQuery(String patientID, String studyInstanceUID, String seriesInstanceUID,
			String SOPInstanceUID, String dcmURL) {
		ConfigProperties cfgProperties;
		boolean isOpen;
		Vector dsVector;
		CDimseService cDimseService;

		try {
			cfgProperties = new ConfigProperties(CDimseService.class.getClassLoader().getResource("Image.cfg"));
		} catch (IOException e) {
			log.error("Error while loading configuration properties");
			return;
		}

		cfgProperties.put("key.PatientID", patientID.trim());
		// cfgProperties.put("key.StudyInstanceUID", studyInstanceUID.trim());
		// cfgProperties.put("key.SeriesInstanceUID", seriesInstanceUID.trim());
		if (SOPInstanceUID != null) {
			cfgProperties.put("key.SOPInstanceUID", SOPInstanceUID.trim());
		}

		DcmURL url = new DcmURL(dcmURL);

		try {
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
		} catch (IOException ioe) {
			log.error("Error while opening association ", ioe);
			return;
		} catch (GeneralSecurityException gse) {
			log.error("Error while opening association ", gse);
			return;
		}

		try {
			dsVector = cDimseService.cFIND();
		} catch (Exception e) {
			log.error("Error while querying ", e);
			return;
		}

		Dataset dataSet = null;
		for (int i = 0; i < dsVector.size(); i++) {
			try {
				dataSet = (Dataset) dsVector.elementAt(i);
				instancesList.add(new InstanceModel(dataSet));
			} catch (Exception e) {
				log.error(e.getMessage());
				return;
			}
		}

		try {
			cDimseService.aRELEASE(true);
		} catch (IOException e) {
			log.error(e.getMessage());
		} catch (InterruptedException e) {
			log.error(e.getMessage());
		}

	}

	public ArrayList<InstanceModel> getInstancesList() {
		return instancesList;
	}

}