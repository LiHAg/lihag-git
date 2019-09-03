package com.ciTreat.dicom.handler;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Vector;

import org.dcm4che.data.Dataset;
import org.dcm4che.dict.Tags;
import org.dcm4che.util.DcmURL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ciTreat.dicom.model.SeriesModel;
import com.ciTreat.dicom.services.CDimseService;
import com.ciTreat.dicom.util.ConfigProperties;
import com.ciTreat.dicom.util.SeriesComparator;

/**
 *
 * @author jinma.zheng
 */
public class SeriesDetailsHandler {

	private static Logger log = LoggerFactory.getLogger(SeriesDetailsHandler.class);
	ArrayList<SeriesModel> seriesList = null;
	private String patientId;
	private String study;
	private String dcmURL;
	// private ArrayList<SeriesModel> seriesList;
	private HashMap<Integer, SeriesModel> series;

	public SeriesDetailsHandler() {
		series = new HashMap<Integer, SeriesModel>();
	}

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

	public void setDcmURL(String dcmURL) {
		this.dcmURL = dcmURL;
	}

	public ArrayList<SeriesModel> handle() {

		try {
			callFindWithQuery(patientId, study, dcmURL);
			seriesList = getSeriesList();
		} catch (Exception e) {
			log.error("Unable to create instance of SeriesInfo and access its callFindWithQuery()", e);
			return null;
		}

		Collections.sort(seriesList, new SeriesComparator());
		return seriesList;
	}

	private void callFindWithQuery(String patientID, String studyInstanceUID, String dcmURL) {

		ConfigProperties cfgProperties;
		boolean isOpen;
		Vector dsVector;
		CDimseService cDimseService;

		try {
			cfgProperties = new ConfigProperties(CDimseService.class.getClassLoader().getResource("Series.cfg"));
		} catch (IOException ioe) {
			log.error("Error while loading configuration properties");
			return;
		}

		DcmURL url = new DcmURL(dcmURL);

		cfgProperties.put("key.PatientID", patientID);
		cfgProperties.put("key.StudyInstanceUID", studyInstanceUID);

		try {
			cDimseService = new CDimseService(cfgProperties, url);
		} catch (ParseException pe) {
			log.error("Unable to create instance of CDimseService", pe);
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
			log.error("Error while opeing association ", gse);
			return;
		}

		try {
			dsVector = cDimseService.cFIND();
		} catch (Exception e) {
			log.error("Error while querying... ", e);
			return;
		}

		for (int i = 0; i < dsVector.size(); i++) {
			try {
				Dataset dataSet = (Dataset) dsVector.elementAt(i);
				System.out.println("dataSet=" + dataSet);
				System.out.println("Tags.Modality=" + Tags.ModalitiesInStudy);
				System.out.println("Tags.Modality=" + dataSet.getString(Tags.ModalitiesInStudy));
				if (!dataSet.getString(Tags.Modality).equals("PR")) {
					SeriesModel sm = new SeriesModel(dataSet);
					series.put(i, sm);
				}
			} catch (Exception e) {
				log.error("Error while adding SeriesModel in HashMap series ", e);
				return;
			}
		}

		try {
			cDimseService.aRELEASE(true);
		} catch (IOException e) {
			log.error("Error while releasing association ", e);
		} catch (InterruptedException ie) {
			log.error("Error while releasing association ", ie);
		}
	}

	public ArrayList<SeriesModel> getSeriesList() {
		return new ArrayList<SeriesModel>(series.values());
	}

}