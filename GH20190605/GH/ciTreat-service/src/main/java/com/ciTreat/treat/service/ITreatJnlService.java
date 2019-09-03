package com.ciTreat.treat.service;

import java.util.List;

import com.ciTreat.treat.domain.TreatJnl;

/**
 *  业务层
 * 
 * @author jinma.zheng
 */
public interface ITreatJnlService
{
    public List<TreatJnl> selectTreatJnlList(String patientId);
    public TreatJnl selectTreatJnl(String taskSrlNum);
    public int insertTreatJnl(TreatJnl tj);
	public int updateTreatJnl(TreatJnl treatJnl);
	public TreatJnl selectTreatJnlByTaskId(String taskId);
	public int deleteTreatJnlByBusNo(String busNo);
	
}
