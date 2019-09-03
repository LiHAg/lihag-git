package com.ciTreat.treat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ciTreat.treat.domain.TreatJnl;
import com.ciTreat.treat.mapper.TreatJnlMapper;

/**
 * 业务层处理
 * 
 * @author jinma.zheng
 */
@Service
public class TreatJnlServiceImpl implements ITreatJnlService {

	@Autowired
	private TreatJnlMapper TreatJnlMapper;

	@Override
	public List<TreatJnl> selectTreatJnlList(String patientId) {
		return TreatJnlMapper.selectTreatJnlList(patientId);
	}

	@Override
	public TreatJnl selectTreatJnl(String taskSrlNum) {
		return TreatJnlMapper.selectTreatJnl(taskSrlNum);
	}

	@Override
	public int insertTreatJnl(TreatJnl treatJnl) {
		return TreatJnlMapper.insertTreatJnl(treatJnl);
	}

	@Override
	public int updateTreatJnl(TreatJnl tj) {
		return TreatJnlMapper.updateTreatJnl(tj);
	}

	@Override
	public TreatJnl selectTreatJnlByTaskId(String taskId) {
		
		return TreatJnlMapper.selectTreatJnlByTaskId(taskId);
	}

	@Override
	public int deleteTreatJnlByBusNo(String busNo) {
		
		return TreatJnlMapper.deleteTreatJnlByBusNo(busNo);
	}

}
