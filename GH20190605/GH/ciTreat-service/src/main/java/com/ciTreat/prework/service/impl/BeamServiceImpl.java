package com.ciTreat.prework.service.impl;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ciTreat.prework.service.BeamService;
import com.ciTreat.treat.TreatServiceImpl;
import com.golden.workflow.common.ContextData;
import com.golden.workflow.engine.WorkflowAction;

/**
 * @author lianyi
 * @date 2019年4月18日
 */
@Service
public class BeamServiceImpl implements BeamService {
	private static Logger logger = LoggerFactory.getLogger(BeamServiceImpl.class);
	@Autowired
	WorkflowAction workflowAction;
	
	@Override
	public String process(Map<String, Object> map) {
		ContextData contextData = new ContextData();
		//...
		
		contextData.putAll(map);
		String taskId = workflowAction.createProcess(contextData);//开启工作流
		return taskId;
	}

	@Override
	public String commitProcessTask(ContextData contextData) {
		String processId = contextData.get("PROCESSID");
		String taskId = contextData.get("TASKID");
		logger.info("交易[" + processId + "] ["+taskId +  "]>>>>>>>>>>>>>>>>>>>>>>>>>>开始");
		String rtTaskId = workflowAction.commitProcessTask(contextData);
		System.out.println("----------rtTaskId"+rtTaskId);
		logger.info("交易[" + processId + "] ["+taskId +  "]>>>>>>>>>>>>>>>>>>>>>>>>>>返回");
		return rtTaskId;
	}

}
