package com.ciTreat.patient.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ciTreat.patient.domain.PatientJnl;

/**
 * 数据层
 * 
 * @author jinma.zheng
 */
public interface PatientJnlMapper {
	public List<PatientJnl> selectPatientJnlList(PatientJnl pj);
	public List<PatientJnl> selectPatientJnlListByPID(String patientId);
	public PatientJnl selectPatientJnlByPID(String patientId);
	public PatientJnl selectPatientJnlByIdPlan(PatientJnl patientJnl);
	public int updatePatJnlTreatedNum(@Param(value="patientId") String patientId, @Param(value="treatedNum") long treatedNum);
	public int updatePatientJnl(PatientJnl patientJnl);
}
