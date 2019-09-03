package com.ciTreat.dicom.handler;

import java.io.InputStream;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ciTreat.dicom.util.ParseImgFileData;
import com.golden.common.json.JSONObject;
import com.golden.common.json.JSONObject.JSONArray;

/**
 * 
 * @author jinma.zheng
 */
public class InstanceHandler {
	private static Logger log = LoggerFactory.getLogger(InstanceHandler.class);
	private String patID = null;
	private String studyUID = null;
	private String seriesUID = null;
	private String dcmURL = null;
	private String wadoURL = null;
	private String objectUID = null;

	public JSONObject handle() {
		try {
			wadoURL += "?requestType=WADO&contentType=application/dicom&studyUID=" + studyUID + "&seriesUID=" + seriesUID;
			JSONObject jsonObject = new JSONObject();
			InputStream is = null;
			String UrlTmp = null;
			UrlTmp = wadoURL + "&objectUID=" + objectUID + "&transferSyntax=1.2.840.10008.1.2.1";
			URL url = new URL(UrlTmp);
			is = url.openStream();
			ParseImgFileData dicomFile = new ParseImgFileData(is);
			jsonObject = dicomFile.readDicomFile();
			return jsonObject;
		} catch (Exception e) {
			log.error("instanceHandler error", e);
			return null;
		}
	}

	public String getPatID() {
		return patID;
	}

	public void setPatID(String patID) {
		this.patID = patID;
	}

	public String getStudyUID() {
		return studyUID;
	}

	public void setStudyUID(String studyUID) {
		this.studyUID = studyUID;
	}

	public String getSeriesUID() {
		return seriesUID;
	}

	public void setSeriesUID(String seriesUID) {
		this.seriesUID = seriesUID;
	}

	public String getDcmURL() {
		return dcmURL;
	}

	public void setDcmURL(String dcmURL) {
		this.dcmURL = dcmURL;
	}

	public String getWadoURL() {
		return wadoURL;
	}

	public void setWadoURL(String wadoURL) {
		this.wadoURL = wadoURL;
	}

	public String getObjectUID() {
		return objectUID;
	}

	public void setObjectUID(String objectUID) {
		this.objectUID = objectUID;
	}

}