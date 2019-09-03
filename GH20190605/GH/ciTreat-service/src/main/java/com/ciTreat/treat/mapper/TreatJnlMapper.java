package com.ciTreat.treat.mapper;

import java.util.List;

import com.ciTreat.treat.domain.TreatJnl;

/**
 * 数据层
 * 
 * @author jinma.zheng
 */
public interface TreatJnlMapper {
	public List<TreatJnl> selectTreatJnlList(String patientId);
	public int updateTreatJnl(TreatJnl treatJnl);
	public int insertTreatJnl(TreatJnl treatJnl);
	public TreatJnl selectTreatJnl(String taskSrlNum);
	public TreatJnl selectTreatJnlByTaskId(String taskId);
	public int deleteTreatJnlByBusNo(String busNo);
}
