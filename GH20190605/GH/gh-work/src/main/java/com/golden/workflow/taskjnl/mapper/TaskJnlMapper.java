package com.golden.workflow.taskjnl.mapper;

import java.util.List;

import com.golden.workflow.taskjnl.model.TaskJnl;

/**
 * 
 * @author jinma.zheng
 */
public interface TaskJnlMapper {
	int addTaskInfo(TaskJnl taskInfo);
	List<TaskJnl> getTaskJnlList(String processId);
	TaskJnl getTaskJnl(String taskId);
	int updateTaskJnl(TaskJnl taskJnl);
}
