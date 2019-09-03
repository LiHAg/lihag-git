package com.ciTreat.patient.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ciTreat.patient.domain.PatientJnl;
import com.ciTreat.patient.mapper.PatientJnlMapper;
import com.ciTreat.patient.service.IPatientJnlService;

/**
 * 业务层
 * 
 * @author jinma.zheng
 */
@Service("patientJnlService")
public class PatientJnlServiceImpl implements IPatientJnlService {
	@Autowired
	private PatientJnlMapper patientJnlMapper;

	public List<PatientJnl> selectPatientJnlList(PatientJnl pj) {
		return patientJnlMapper.selectPatientJnlList(pj);
	}

	public PatientJnl selectPatientJnlByPID(String patientId) {
		return patientJnlMapper.selectPatientJnlByPID(patientId);
	}

	@Override
	public int updatePatientJnl(PatientJnl patientJnl) {
		return patientJnlMapper.updatePatientJnl(patientJnl);
	}

	@Override
	public int updatePatJnlTreatedNum(String patientId, long treatedNum) {
		return patientJnlMapper.updatePatJnlTreatedNum(patientId, treatedNum);
	}

	@Override
	public PatientJnl selectPatientJnlByIdPlan(PatientJnl patientJnl) {
		
		return patientJnlMapper.selectPatientJnlByIdPlan(patientJnl);
	}

	@Override
	public List<PatientJnl> selectPatientJnlListByPID(String patientId) {
		
		return patientJnlMapper.selectPatientJnlListByPID(patientId);
	}


}
