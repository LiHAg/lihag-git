package com.ciTreat.patient.service;

import java.util.List;

import com.ciTreat.patient.domain.PatientJnl;

/**
 *  业务层
 * 
 * @author jinma.zheng
 */
public interface IPatientJnlService
{
    public List<PatientJnl> selectPatientJnlList(PatientJnl wjj);
    public List<PatientJnl> selectPatientJnlListByPID(String patientId);
    public PatientJnl selectPatientJnlByPID(String patientId);
	public PatientJnl selectPatientJnlByIdPlan(PatientJnl patientJnl);
    public int updatePatientJnl(PatientJnl pjl);
	public int updatePatJnlTreatedNum(String patientId, long treatedNum);
}
