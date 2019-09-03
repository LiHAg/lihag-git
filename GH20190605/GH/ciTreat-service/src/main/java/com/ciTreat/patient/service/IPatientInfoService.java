package com.ciTreat.patient.service;

import com.ciTreat.patient.domain.PatientInfo;

/**
 *  业务层
 * 
 * @author jinma.zheng
 */
public interface IPatientInfoService
{
    public PatientInfo selectPatientInfoByPID(String patientId);
}
