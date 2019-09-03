package com.golden.workflow.engine.task;

import java.sql.Timestamp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.golden.platform.expression.ExpressionEvaluator;
import com.golden.workflow.common.ContextData;
import com.golden.workflow.common.WFConstants;
import com.golden.workflow.common.WorkflowException;
import com.golden.workflow.engine.executor.Executor;
import com.golden.workflow.nodeinfo.model.NodeInfo;
import com.golden.workflow.nodeinfo.service.NodeInfoService;
import com.golden.workflow.nodejnl.model.NodeJnl;
import com.golden.workflow.taskjnl.model.TaskJnl;
import com.golden.workflow.taskjnl.service.TaskJnlService;
import com.golden.workflow.util.FlowRefBuilder;

/**
 * @author jinma.zheng
 * @date 2019年3月29日
 */
@Service
public class TaskManageImpl implements TaskManage {
	private final static Logger logger = LoggerFactory.getLogger(TaskManageImpl.class);
	@Autowired
	TaskJnlService taskJnlService;
	@Autowired
	private NodeInfoService nodeInfoService;
	@Autowired
	private Executor executor;
	@Autowired
	private ExpressionEvaluator expressionEvaluator;

	@Override
	public String commitTask(ContextData conData) {
		try {
			String processId = conData.get("PROCESSID");
			String taskId = conData.get("TASKID");
			TaskJnl taskJnl = taskJnlService.getTaskJnl(taskId);
			if (taskJnl == null) {
				logger.error("workflow doesn't exist:nProcessId" + processId + " taskId:" + taskId);
				throw new WorkflowException(WorkflowException.EXP_CODE_TASKINFO_NOT_EXIST);
			}
			String userId = taskJnl.getExecutorId();
			String busNo = taskJnl.getBusNo();

			logger.debug("contextbusno:" + busNo);

			if (taskJnl.getTaskState() == WFConstants.TASK_STATE_COMPLETED) {
				logger.error("workflow already finished :nProcessId" + processId + " taskId:" + taskId);
				throw new WorkflowException(WorkflowException.EXP_CODE_TASK_COMMIT_ERROR);
			}

			if (taskJnl.getTaskState() != WFConstants.TASK_STATE_CREATED) {
				logger.error("workflow doesn't create :nProcessId" + processId + " taskId:" + taskId);
				throw new WorkflowException(WorkflowException.EXP_CODE_TASK_RETRACT_ERROR);
			}

			String action = taskJnl.getAction();
			if (action != null && !action.trim().equals("")) {
				Object result = expressionEvaluator.getValue(conData.getMap(), action);
			}

			taskJnl.setTaskId(taskJnl.getTaskId());//zzz或从condata中获取
			taskJnl.setTaskState(WFConstants.TASK_STATE_COMPLETED);
			taskJnl.setCompletedTime(new Timestamp(System.currentTimeMillis()) + "");
			taskJnlService.updateTaskJnl(taskJnl);

			String nodeId = taskJnl.getNodeId();
			NodeInfo nodeInfo = nodeInfoService.getNodeInfo(nodeId);
			NodeInfo nextNodeInfo = nodeInfoService.getNodeInfo(nodeInfo.getNextNodeId());
			NodeJnl rtNextNodeInfo = new NodeJnl(nextNodeInfo);
			rtNextNodeInfo.setProcessId(processId);
			rtNextNodeInfo.setBusNo(busNo);
			rtNextNodeInfo.setUserId(userId);
			rtNextNodeInfo.setNodeContext(conData.getMap());
			rtNextNodeInfo.setNodeInstanceId(FlowRefBuilder.getInstanceNodeId());
			String rtTaskId = executor.executeFragment(rtNextNodeInfo);
			conData.put("TASK_ID", rtTaskId);
			logger.debug("Commit task end.");
			return rtTaskId;
		} catch (Exception e) {
			logger.error("commit task error." + e.getMessage(), e);
			// getSessionContext().setRollbackOnly();
			throw new WorkflowException(e);
		}
	}

}
