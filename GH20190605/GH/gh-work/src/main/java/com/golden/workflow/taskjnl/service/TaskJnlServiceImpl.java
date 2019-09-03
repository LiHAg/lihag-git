package com.golden.workflow.taskjnl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.golden.workflow.taskjnl.mapper.TaskJnlMapper;
import com.golden.workflow.taskjnl.model.TaskJnl;

/**
 * @author jinma.zheng
 * @date 2019年3月31日
 */
@Service
public class TaskJnlServiceImpl implements TaskJnlService {
	@Autowired
	TaskJnlMapper taskJnlMapper;
	
	@Override
	public int addTaskInfo(TaskJnl taskInfo) {
		return taskJnlMapper.addTaskInfo(taskInfo);
		
	}

	@Override
	public List<TaskJnl> getTaskJnlList(String processId) {
		return taskJnlMapper.getTaskJnlList(processId);
	}

	@Override
	public TaskJnl getTaskJnl(String taskId) {
		return taskJnlMapper.getTaskJnl(taskId);
	}

	@Override
	public int updateTaskJnl(TaskJnl taskJnl) {
		return taskJnlMapper.updateTaskJnl(taskJnl);
	}

}
