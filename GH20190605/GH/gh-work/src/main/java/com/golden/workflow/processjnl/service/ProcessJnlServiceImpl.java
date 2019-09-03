package com.golden.workflow.processjnl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.golden.workflow.common.WorkflowException;
import com.golden.workflow.processjnl.mapper.ProcessJnlMapper;
import com.golden.workflow.processjnl.model.ProcessJnl;

/**
 * @author jinma.zheng
 * @date 2019年3月30日
 */
@Service
public class ProcessJnlServiceImpl implements ProcessJnlService{
	@Autowired
	ProcessJnlMapper processJnlMapper;
	
	@Override
	public int addProcessInfo(ProcessJnl processInfo) {
		int rows = processJnlMapper.addProcessInfo(processInfo);
		if( rows <= 0) {
			throw new WorkflowException("新增数据失败");
		}
		return rows;
	}

	@Override
	public ProcessJnl getProcessInfo(String processid) {
		 return processJnlMapper.getProcessInfo(processid);
	}

	@Override
	public int updateProcessInfo(ProcessJnl processJnl) {
		 return processJnlMapper.updateProcessInfo(processJnl);
	}

	@Override
	public List<ProcessJnl> getProcessJnlList(String busNo) {
		return processJnlMapper.getProcessJnlList(busNo);
	}

}
