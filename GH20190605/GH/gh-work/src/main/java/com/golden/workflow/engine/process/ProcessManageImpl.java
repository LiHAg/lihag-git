package com.golden.workflow.engine.process;

import java.sql.Timestamp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.golden.workflow.common.ContextData;
import com.golden.workflow.common.WFConstants;
import com.golden.workflow.common.WorkflowException;
import com.golden.workflow.engine.executor.Executor;
import com.golden.workflow.engine.task.TaskManage;
import com.golden.workflow.nodeinfo.model.NodeInfo;
import com.golden.workflow.nodeinfo.service.NodeInfoService;
import com.golden.workflow.nodejnl.model.NodeJnl;
import com.golden.workflow.processjnl.model.ProcessJnl;
import com.golden.workflow.processjnl.service.ProcessJnlService;
import com.golden.workflow.template.model.TemplateInfo;
import com.golden.workflow.template.service.TemplateService;
import com.golden.workflow.util.FlowRefBuilder;
import com.golden.workflow.util.Transformer;

/**
 * @author jinma.zheng
 * @date 2019年3月29日
 */
@Service
public class ProcessManageImpl implements ProcessManage {
	private final static Logger logger = LoggerFactory.getLogger(ProcessManage.class);
	@Autowired
	private TemplateService templateService;
	@Autowired
	private ProcessJnlService processInfoService;
	@Autowired
	private NodeInfoService nodeInfoService;
	@Autowired
	TaskManage taskManage;
	@Autowired
	private Executor executor;

	public String createProcess(ContextData conData) throws WorkflowException {
		try {
			String templateId = conData.get("TEMPLATE_NAME");
			String userId = conData.get("USER_USERID");
			String busNo = conData.get("BUS_NO");
			logger.debug("startProcess templateid==>>" + templateId + "  userId==>>" + busNo + "  userId==>>" + busNo);

			TemplateInfo templateInfo = templateService.getTemplateInfo(templateId);

			ProcessJnl processJnl = new ProcessJnl();
			processJnl.setTemplateId(templateId);
			String processId = FlowRefBuilder.getProcessId(null,null);
			processJnl.setProcessId(processId);
			processJnl.setProcessName(templateInfo.getTemplateName());
			processJnl.setProcessComment(templateInfo.getTemplateComment());
			processJnl.setStartedTime(new Timestamp(System.currentTimeMillis()) + "");
			processJnl.setInitiatorId(userId);
			processJnl.setProcessState(WFConstants.PROCESS_STATE_RUNNING);
			processJnl.setBusNo(busNo);
			conData.put("PROCESS_ID", processId);
			processJnl.setContextData(Transformer.mapToJsonString(conData.getMap())); // zzz 将变量信息改放到processInfo中
		    processInfoService.addProcessInfo(processJnl);
			logger.debug("startProcess end processid ===>>" + processId);
			return processId;
		} catch (Exception ex) {
			logger.error("startProcess:" + ex.getMessage(), ex);
			throw new WorkflowException("startProcess:" + ex.getMessage());
		}
	}
	
	public String executeStartNode(ContextData conData) throws WorkflowException {
		try {
			logger.debug("begin start node");
			NodeInfo startNode = nodeInfoService.getStartNode(conData.get("TEMPLATE_NAME"));
			NodeJnl rtNodeInfo = new NodeJnl(startNode);
			rtNodeInfo.setProcessId(conData.get("PROCESS_ID"));
			rtNodeInfo.setBusNo(conData.get("BUS_NO"));
			rtNodeInfo.setUserId(conData.get("USER_USERID"));
			rtNodeInfo.setNodeContext(conData.getMap());
			rtNodeInfo.setNodeInstanceId(FlowRefBuilder.getInstanceNodeId());
			//同步处理
			logger.debug("executeFragment");
			String taskId = executor.executeFragment(rtNodeInfo);
			//异步处理
			//nodeQueueLocal.addLastNode(startNode);
			
			logger.debug("startProcess end processid ===>>" + taskId);
			return taskId;
		} catch (Exception ex) {
			logger.error("startProcess:" + ex.getMessage(), ex);
			throw new WorkflowException("startProcess:" + ex.getMessage());
		}
	}

	@Override
	public String commitProcessTask(ContextData conData) {
		return taskManage.commitTask(conData);
	}

	public void stopProcess(String processId) throws WorkflowException {
		try {
			logger.debug("stopProcess begin:");

			ProcessJnl processInfo = processInfoService.getProcessInfo(processId);
			processInfo.setCompletedTime(new Timestamp(System.currentTimeMillis()) + "");
			processInfo.setProcessState(WFConstants.PROCESS_STATE_COMPLETED);
			processInfoService.updateProcessInfo(processInfo);

			//.deleteEventWatch(nProcessId);
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error("stopProcess error:" + ex.getMessage());
			throw new WorkflowException("stopProcess:" + ex.getMessage());
		}
	}
}
