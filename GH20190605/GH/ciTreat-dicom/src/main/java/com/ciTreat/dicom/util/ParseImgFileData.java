package com.ciTreat.dicom.util;

import java.io.IOException;
import java.io.InputStream;

import org.apache.log4j.Logger;
import org.dcm4che2.data.DicomElement;
import org.dcm4che2.data.DicomObject;
import org.dcm4che2.data.Tag;
import org.dcm4che2.io.DicomInputStream;

import com.ciTreat.dicom.model.InstanceModel;
import com.golden.common.json.JSONObject;

/**
 * 
 * @author jinma.zheng
 *
 */
public class ParseImgFileData {

	private static Logger log = Logger.getLogger(ParseImgFileData.class);

	InputStream is = null;
	DicomInputStream dis = null;
	JSONObject jsonObj = null;

	public ParseImgFileData(InputStream is) {
		this.is = is;
	}

	@SuppressWarnings("finally")
	public JSONObject readDicomFile() {
		try {
			jsonObj = new JSONObject();
			//zzz jsonObj.put("SopUID", im.getSopIUID());
			//zzz jsonObj.put("InstanceNo", im.getInstanceNumber());
			//zzz jsonObj.put("SopClassUID", im.getSopClassUID());
			dis = new DicomInputStream(is);

			DicomObject dcmObj = dis.readDicomObject();
			DicomElement wcDcmElement = dcmObj.get(Tag.WindowCenter);
			DicomElement wwDcmElement = dcmObj.get(Tag.WindowWidth);
			DicomElement rowDcmElement = dcmObj.get(Tag.Rows);
			DicomElement colDcmElement = dcmObj.get(Tag.Columns);
			DicomElement imgOrientation = dcmObj.get(Tag.ImageOrientationPatient);
			DicomElement imgPosition = dcmObj.get(Tag.ImagePositionPatient);
			DicomElement sliceThick = dcmObj.get(Tag.SliceThickness);
			DicomElement frameOfRefUID = dcmObj.get(Tag.FrameOfReferenceUID);
			DicomElement pixelSpacingEle = dcmObj.get(Tag.PixelSpacing);
			DicomElement imgPixelSpacingEle = dcmObj.get(Tag.ImagerPixelSpacing);
			DicomElement totalFramesEle = dcmObj.get(Tag.NumberOfFrames);
			DicomElement rescaleSlope = dcmObj.get(Tag.RescaleSlope);
			DicomElement rescaleIntercept = dcmObj.get(Tag.RescaleIntercept);
			DicomElement bitsStored = dcmObj.get(Tag.BitsStored);
			DicomElement photometricInterpretation = dcmObj.get(Tag.PhotometricInterpretation);
			DicomElement pixelRep = dcmObj.get(Tag.PixelRepresentation);
			DicomElement frmTime = dcmObj.get(Tag.FrameTime);
			DicomElement imgLaterality = dcmObj.get(Tag.ImageLaterality);
			DicomElement viewPosition = dcmObj.get(Tag.ViewPosition);
			DicomElement mod = dcmObj.get(Tag.Modality);

			// To get the Image Type (LOCALIZER / AXIAL / OTHER)
			DicomElement imageType = dcmObj.get(Tag.ImageType);
			String image_type = "";
			if (imageType != null) {
				image_type = new String(imageType.getBytes());
				String[] imageTypes = image_type.split("\\\\");
				if (imageTypes.length >= 3) {
					image_type = imageTypes[2];
				}
			}

			// To get the Referenced SOP Instance UID
			DicomElement refImageSeq = dcmObj.get(Tag.ReferencedImageSequence);
			DicomElement refSOPInsUID = null;
			String referSopInsUid = "";

			if (refImageSeq != null) {
				if (refImageSeq.hasItems()) {
					int cnt = refImageSeq.countItems();
					for (int j = 0; j < cnt; j++) {
						DicomObject dcmObj1 = refImageSeq.getDicomObject(j);
						refSOPInsUID = dcmObj1.get(Tag.ReferencedSOPInstanceUID);
						if (referSopInsUid != "") {
							if (refSOPInsUID != null) {
								referSopInsUid += "," + new String(refSOPInsUID.getBytes());
							}
						} else {
							referSopInsUid = refSOPInsUID != null ? new String(refSOPInsUID.getBytes()) : "";
						}
					}
				}
			}

			String windowCenter = wcDcmElement != null ? new String(wcDcmElement.getBytes()) : "";
			String windowWidth = wwDcmElement != null ? new String(wwDcmElement.getBytes()) : "";
			int nativeRows = rowDcmElement != null ? rowDcmElement.getInt(false) : 0;
			int nativeColumns = colDcmElement != null ? colDcmElement.getInt(false) : 0;
			String imgOrient = imgOrientation != null ? new String(imgOrientation.getBytes()) : "";
			String sliceThickness = sliceThick != null ? new String(sliceThick.getBytes()) : "";
			String forUID = frameOfRefUID != null ? new String(frameOfRefUID.getBytes()) : "";
			String resclaeslp = rescaleSlope != null ? new String(rescaleSlope.getBytes()) : "1.0";
			String resclaeintercept = rescaleIntercept != null ? new String(rescaleIntercept.getBytes()) : "0.0";
			int bitsStored1 = bitsStored != null ? bitsStored.getInt(false) : 12;
			boolean monochrome = photometricInterpretation != null
					? new String(photometricInterpretation.getBytes()).trim().equalsIgnoreCase("MONOCHROME1") ? true
							: false
					: false;
			String photometric = photometricInterpretation != null
					? new String(photometricInterpretation.getBytes()).trim()
					: " ";

			int pixRep = pixelRep != null ? pixelRep.getInt(false) : -1;
			double frameTime = frmTime != null ? frmTime.getDouble(false) : 0;

			String sliceLoc = "";
			String imagePosition = "";
			if (imgPosition != null) {
				imagePosition = new String(imgPosition.getBytes());
				sliceLoc = imagePosition.substring(imagePosition.lastIndexOf("\\") + 1);
			}

			String pixelSpacing = pixelSpacingEle != null ? new String(pixelSpacingEle.getBytes()) : "";
			String imgPixelSpacing = imgPixelSpacingEle != null ? new String(imgPixelSpacingEle.getBytes()) : "";
			String totalFrames = totalFramesEle != null ? new String(totalFramesEle.getBytes()) : "";
			boolean multiFrame = true;
			if (totalFrames.trim().equalsIgnoreCase("1") || totalFrames.trim().length() == 0) {
				multiFrame = false;
			}
			String imgLtr = imgLaterality != null ? new String(imgLaterality.getBytes()) : "";
			String viewPos = viewPosition != null ? new String(viewPosition.getBytes()) : "";
			String modality = mod != null ? new String(mod.getBytes()) : "";

			jsonObj.put("windowCenter", windowCenter.replaceAll("\\\\", "|"));
			jsonObj.put("windowWidth", windowWidth.replaceAll("\\\\", "|"));
			jsonObj.put("nativeRows", nativeRows);
			jsonObj.put("nativeColumns", nativeColumns);

			if (imgOrient.length() > 0)
				jsonObj.put("imageOrientation", ImageOrientation.getOrientation(imgOrient));
			else
				jsonObj.put("imageOrientation", imgOrient);

			jsonObj.put("sliceLocation", sliceLoc);
			jsonObj.put("sliceThickness", sliceThickness);
			jsonObj.put("frameOfReferenceUID", forUID.replaceAll("\u0000", ""));

			jsonObj.put("imagePositionPatient", imagePosition);
			jsonObj.put("imageOrientPatient", imgOrient);
			jsonObj.put("pixelSpacing", pixelSpacing);
			jsonObj.put("imagerPixelSpacing", imgPixelSpacing);
			jsonObj.put("refSOPInsUID", referSopInsUid.replaceAll("\u0000", ""));
			jsonObj.put("imageType", image_type);
			jsonObj.put("numberOfFrames", totalFrames.trim());

			jsonObj.put("rescaleSlope", resclaeslp);
			jsonObj.put("rescaleIntercept", resclaeintercept);
			jsonObj.put("BitsStored", bitsStored1);
			jsonObj.put("monochrome1", monochrome);
			jsonObj.put("PixelRepresentation", pixRep);
			jsonObj.put("frameTime", frameTime);
			jsonObj.put("imgLaterality", imgLtr);
			jsonObj.put("viewPosition", viewPos);
			jsonObj.put("modality", modality);
			jsonObj.put("photometric", photometric);
			jsonObj.put("multiframe", multiFrame);

		} catch (Exception ex) {
			log.error("Unable to read Dicom Data", ex);
		} finally {
			try {
				if (dis != null) {
					dis.close();
				}
				if (is != null) {
					is.close();
				}
			} catch (IOException e) {
				log.error("IO EXCEPTION ", e);
			}
			return jsonObj;
		}
	}

}
