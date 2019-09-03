package com.golden.workflow.taskjnl.service;

import java.util.List;

import com.golden.workflow.taskjnl.model.TaskJnl;

/**
 * @author jinma.zheng
 * @date 2019年3月31日
 */
public interface TaskJnlService {
	int addTaskInfo(TaskJnl taskInfo);
	List<TaskJnl> getTaskJnlList(String processId);
	//TaskJnl getTaskJnl(String processId,String nodeInstanceId);
	TaskJnl getTaskJnl(String taskId);
	int updateTaskJnl(TaskJnl taskJnl);
}
