package com.golden.workflow.engine.action;

import java.sql.Timestamp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.golden.platform.expression.ExpressionEvaluator;
import com.golden.workflow.common.WFConstants;
import com.golden.workflow.common.WorkflowException;
import com.golden.workflow.nodejnl.model.NodeJnl;
import com.golden.workflow.taskjnl.model.TaskJnl;
import com.golden.workflow.taskjnl.service.TaskJnlService;

/**
 * 
 * @author jinma.zheng
 * @date 2019年4月3日
 */
@Service("handAction")
public class HandAction implements Action {
	private static Logger logger = LoggerFactory.getLogger(HandAction.class.getName());
	@Autowired
	private ExpressionEvaluator expressionEvaluator;
	@Autowired
	private TaskJnlService taskJnlService;

	public boolean execute(NodeJnl node) throws WorkflowException {
		try {
			logger.debug("============HandAction======start====" + node.getAction());
			boolean bResult = false;
			TaskJnl taskJnl = TaskJnlUtils.createTaskJnlInstance(node);
			String action = taskJnl.getAction();
			if (node.getFirstManualTask() != null && node.getFirstManualTask().equals("Y")) {
				if (action != null && !action.trim().equals("")) {
					Object result = expressionEvaluator.getValue(node.getNodeContext(), node.getAction()); // jm.z
				}
				taskJnl.setTaskState(WFConstants.TASK_STATE_COMPLETED);
				taskJnl.setCompletedTime(new Timestamp(System.currentTimeMillis()) + "");
				bResult = true;
			} else {
				taskJnl.setTaskState(WFConstants.TASK_STATE_CREATED);
				bResult = false;
			}
			// zzz do something....

			taskJnlService.addTaskInfo(taskJnl);
			logger.debug("============HandAction======end====");
			return bResult;
		} catch (Exception ex) {
			WorkflowException wfe = new WorkflowException("HandAction:" + ex.getMessage());
			throw wfe;
		}
	}

}