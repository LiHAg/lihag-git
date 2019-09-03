package com.ciTreat.web.controller.citreat;

import java.util.ArrayList;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ciTreat.dicom.handler.ImageHandler;
import com.ciTreat.dicom.handler.InstanceHandler;
import com.ciTreat.dicom.handler.PatientInfoHandler;
import com.ciTreat.dicom.handler.SeriesDetailsHandler;
import com.ciTreat.dicom.model.InstanceModel;
import com.ciTreat.dicom.model.SeriesModel;
import com.ciTreat.dicom.model.StudyModel;
import com.ciTreat.system.domain.SysOperLog;
import com.golden.common.json.JSONObject;
import com.golden.common.page.TableDataInfo;
import com.golden.framework.web.base.BaseController;
/**
 * DICOM数据加载
 * 
 * @author jinma.zheng
 */
@Controller
@RequestMapping("/ciTreat/ciTtask")
public class CiTreatTaskController extends BaseController {
	private static final Logger log = LoggerFactory.getLogger(CiTreatTaskController.class);
	String dcmURL = "DICOM://DCM4CHEE:OVIYAM2@localhost:11112";
	private String prefix = "/ciTreat/ciTtask";

	@GetMapping()
	public String ciTtask(ModelMap mmap) throws Exception {
		return prefix + "/ciTtask";
	}

	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(SysOperLog operLog) {
		startPage();
		PatientInfoHandler ph = new PatientInfoHandler();
		ph.setDcmURL(dcmURL);
		ArrayList<StudyModel> studyList = ph.handle();
		for (StudyModel sd : studyList) {
			System.out.println("StudyModel:\n" + ReflectionToStringBuilder.toString(sd));
			System.out.println("\n");
		}

		return getDataTable(studyList);
	}

	@GetMapping("/detail/{patientID}")
	public String detail(@PathVariable("patientID") String patientID, ModelMap mmap) {
		// mmap.put("operLog", operLogService.selectOperLogById(operId));

		System.out.println("patientID=" + patientID);
		PatientInfoHandler ph = new PatientInfoHandler();
		ph.setPatientId(patientID);
		ph.setDcmURL(dcmURL);
		ArrayList<StudyModel> studyList = ph.handle();
		for (StudyModel sd : studyList) {
			System.out.println("StudyModel:\n" + ReflectionToStringBuilder.toString(sd));
			System.out.println("\n");
		}
		if (studyList != null) {
			mmap.put("stl", studyList);
		}

		SeriesDetailsHandler sh = new SeriesDetailsHandler();
		sh.setDcmURL(dcmURL);
		sh.setPatientId("9993");
		sh.setStudy("1.3.12.2.1107.5.1.4.95787.30000018111402453786900000004");
		ArrayList<SeriesModel> sml = sh.handle();
		for (SeriesModel s : sml) {
			System.out.println("SeriesModel:\n" + ReflectionToStringBuilder.toString(s));
		}

		if (sml != null) {
			mmap.put("sml", sml);
		}

		ImageHandler ih = new ImageHandler();
		ih.setDcmURL(dcmURL);
		ih.setPatientId("9993");
		// ih.setStudy("1.3.12.2.1107.5.1.4.95787.30000018111402453786900000004");
		// ih.setSeries("1.2.276.0.7230010.3.1.3.3193618520.2368.1542164462.876");

		ArrayList<InstanceModel> iml = ih.handle();
		for (InstanceModel im : iml) {
			System.out.println("InstanceModel:\n" + ReflectionToStringBuilder.toString(im));
		}

		if (sml != null) {
			mmap.put("iml", iml);
		}

		return prefix + "/detail";
	}

	@GetMapping("/instance/{sopIUID}")
	public String instance(@PathVariable("sopIUID") String sopIUID, ModelMap mmap) {
		System.out.println("-----------------------------" + sopIUID);
		InstanceHandler ih = new InstanceHandler();
		ih.setStudyUID("1.3.12.2.1107.5.1.4.95787.30000018111402453786900000004");
		ih.setSeriesUID("1.3.12.2.1107.5.1.4.95787.30000018111402463285300000111");
		ih.setObjectUID(sopIUID);
		ih.setWadoURL("http://localhost:8081/wado");
		JSONObject jsonObject = ih.handle();
		mmap.put("jo", jsonObject);
		System.out.println(jsonObject);
		return prefix + "/instance";
	}

}
