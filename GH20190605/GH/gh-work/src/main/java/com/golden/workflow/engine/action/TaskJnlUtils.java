package com.golden.workflow.engine.action;

import java.sql.Timestamp;

import com.golden.workflow.nodejnl.model.NodeJnl;
import com.golden.workflow.taskjnl.model.TaskJnl;

/**
 * @author jinma.zheng
 * @date 2019年4月3日
 */
public class TaskJnlUtils{
	public static TaskJnl createTaskJnlInstance(NodeJnl node){
		TaskJnl taskJnl = new TaskJnl();
		taskJnl.setTemplateId(node.getTemplateId());
		taskJnl.setProcessId(node.getProcessId());
		taskJnl.setProcessName(node.getNodeName()); // zzz 需要改成processInfo.getProcessName()

		taskJnl.setNodeId(node.getNodeId());
		taskJnl.setTaskId(node.getNodeInstanceId());
		taskJnl.setAction(node.getAction());
		taskJnl.setTaskName(node.getNodeName());
		taskJnl.setTaskComment(node.getNodeComment());
		taskJnl.setBusNo(node.getBusNo());
		taskJnl.setExecutorId(node.getUserId());
		// 设置变量信息
		taskJnl.setCreatedTime(new Timestamp(System.currentTimeMillis()) + "");
		return taskJnl;
	}
}
