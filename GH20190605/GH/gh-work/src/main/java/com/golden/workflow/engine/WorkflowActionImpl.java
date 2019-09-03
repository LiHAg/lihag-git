package com.golden.workflow.engine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.golden.workflow.common.ContextData;
import com.golden.workflow.common.WorkflowException;
import com.golden.workflow.engine.process.ProcessManage;
import com.golden.workflow.router.WorkRouterService;
import com.golden.workflow.router.model.WorkRouter;

/**
 * 
 * @author jinma.zheng
 * @date 2019年3月27日
 */
@Service
public class WorkflowActionImpl implements WorkflowAction {
	private static Logger logger = LoggerFactory.getLogger(WorkflowActionImpl.class);
	@Autowired
	private WorkRouterService workRouterService;
	@Autowired
	ProcessManage processService;
	
	public String createProcess(ContextData conData) throws WorkflowException {
		logger.debug("createProcessInstance begin");
		String templateName = "";
		// String processNo = "";
		String tranCode = conData.get("TRAN_CODE");
		String userId = conData.get("USER_ID");
		String busNo = conData.get("BUS_NO");
		// String wfFlag = conData.get("WFFLAG");
		
		try {
			if (tranCode == null) {
				throw new WorkflowException("交易码不能为空");
			}
			logger.info("创建工作流开始>>>>>>>>>交易码[" + tranCode + "] 用户[" + userId + "] 业务流水[" + busNo + "]");
			WorkRouter workRouter = workRouterService.selectWorkRouterByRC(tranCode);// 路由信息
			if (workRouter == null) {
				throw new WorkflowException("路由不存在");
			}
			templateName = workRouter.getTaskCode();// 通过路由得到任务代码
			conData.put("TEMPLATE_NAME", templateName);

			// 判断工作流是否启动(异步机制) zzz
			long startTime = System.currentTimeMillis();
			processService.createProcess(conData);
			logger.debug("init template use：" + (System.currentTimeMillis() - startTime) + "ms");
			logger.info("创建工作流结束>>>>>>>>>交易码[" + tranCode + "] 用户[" + userId + "] 业务流水[" + busNo + "]");
			String taskId = executeStartNode(conData);
			return taskId;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("create workflow process instance error! flow template name is " + templateName);
			throw new WorkflowException(e);
		}
	}

	public String commitProcessTask(ContextData conData) {
		try {
			String processId = conData.get("PROCESSID");
			logger.debug("processId:" + processId);
			if (processId == null || "".equals(processId)) {
				String sMsg = "工作流流程号(PROCESSID)不能为空，请检查!";
				throw new WorkflowException(sMsg);
			}
			String taskId = processService.commitProcessTask(conData);
			return taskId;
		} catch (Exception e) {
			logger.error("commit workflow process instance error!  " + e.getMessage(), e);
			throw new WorkflowException(e);
		}
	}

	@Override
	public String executeStartNode(ContextData contextData) {
		String taskId = processService.executeStartNode(contextData);
		return taskId;
	}

}
