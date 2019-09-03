package com.golden.workflow.processjnl.mapper;

import java.util.List;

import com.golden.workflow.processjnl.model.ProcessJnl;

/**
 * 
 * @author jinma.zheng
 * @date 2019年4月1日
 */
public interface ProcessJnlMapper {
	ProcessJnl getProcessInfo(String processId);

	int addProcessInfo(ProcessJnl processInfo);

	int updateProcessInfo(ProcessJnl processJnl);

	List<ProcessJnl> getProcessJnlList(String busNo);
}
