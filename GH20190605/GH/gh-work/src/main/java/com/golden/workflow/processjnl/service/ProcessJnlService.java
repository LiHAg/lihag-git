package com.golden.workflow.processjnl.service;

import java.util.List;

import com.golden.workflow.processjnl.model.ProcessJnl;

/**
 * @author jinma.zheng
 * @date 2019年3月30日
 */
public interface ProcessJnlService {
	ProcessJnl getProcessInfo(String nProcessid);
	int addProcessInfo(ProcessJnl processInfo);
	int updateProcessInfo(ProcessJnl processInfo);
	List<ProcessJnl> getProcessJnlList(String busNo);
}
