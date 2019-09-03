package com.ciTreat.patient.mapper;

import com.ciTreat.patient.domain.PatientInfo;

/**
 * 数据层
 * 
 * @author jinma.zheng
 */
public interface PatientInfoMapper {
	public PatientInfo selectPatientInfoByPID(String patientId);
}
