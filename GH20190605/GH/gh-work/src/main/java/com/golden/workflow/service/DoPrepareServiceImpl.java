package com.golden.workflow.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.golden.platform.expression.ExpressionEvaluator;
import com.golden.workflow.common.ContextData;
import com.golden.workflow.nodeinfo.model.NodeInfo;
import com.golden.workflow.nodeinfo.service.NodeInfoService;

/**
 * @author jinma.zheng
 * @date 2019年4月7日
 */
@Service
public class DoPrepareServiceImpl implements DoPrepareService{
	@Autowired
	private NodeInfoService nodeInfoService;
	@Autowired
	private ExpressionEvaluator expressionEvaluator;
	@Override
	public void doPrepare(ContextData contextData) {
		String nodeId = contextData.get("NODEID");
		NodeInfo nodeInfo = nodeInfoService.getNodeInfo(nodeId);
		contextData.put("INPUTPAGENAME", nodeInfo.getInputPageName());
		String initAction = nodeInfo.getInitAction();
		if (initAction != null && !initAction.trim().equals("")) {
			Object result = expressionEvaluator.getValue(contextData.getMap(), initAction); // jm.z
			if(result != null) {
				System.out.println("result=" + result);
				System.out.println("result=" + ((Map<String, Object>)result).size());
				contextData.putAll((Map<String, Object>)result);
			}
		}
	}
	
}
