package com.ciTreat.patient.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ciTreat.patient.domain.PatientInfo;
import com.ciTreat.patient.mapper.PatientInfoMapper;
import com.ciTreat.patient.service.IPatientInfoService;

/**
 * 业务层
 * 
 * @author jinma.zheng
 */
@Service
public class PatientInfoServiceImpl implements IPatientInfoService {
	@Autowired
	private PatientInfoMapper patientInfoMapper;

	public PatientInfo selectPatientInfoByPID(String patientId) {
		return patientInfoMapper.selectPatientInfoByPID(patientId);
	}


}
