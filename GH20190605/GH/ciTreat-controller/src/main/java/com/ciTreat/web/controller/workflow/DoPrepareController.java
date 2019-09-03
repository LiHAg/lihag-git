package com.ciTreat.web.controller.workflow;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.golden.framework.web.base.BaseController;
import com.golden.platform.expression.ExpressionEvaluator;
import com.golden.workflow.common.ContextData;
import com.golden.workflow.nodeinfo.model.NodeInfo;
import com.golden.workflow.nodeinfo.service.NodeInfoService;
import com.golden.workflow.service.DoPrepareService;

/**
 * 
 * @author jinma.zheng
 */
@Controller
@RequestMapping("/workflow/doPrepare")
public class DoPrepareController extends BaseController {
	@Autowired
	private DoPrepareService doPrepareService;
	@Autowired
	private NodeInfoService nodeInfoService;
/*	@Autowired
	private NodeInfoService nodeInfoService;*/
	@GetMapping("/beam")
	public String doPrepareBeam(String nodeId,String processId,String taskId,ModelMap mmap) {
		System.out.println("----------------111111111111--------------");
		System.out.println(nodeId);
		System.out.println(processId);
		//NodeInfo nodeInfo = nodeInfoService.getNodeInfo(nodeId);
		//System.out.println("nodeInfo="+nodeInfo);
		//System.out.println("inputPageName="+inputPageName);
		ContextData contextData= new ContextData();
		contextData.put("NODEID", nodeId);
		contextData.put("PROCESSID", processId);
		contextData.put("TASKID", taskId);
		doPrepareService.doPrepare(contextData);
		mmap.putAll(contextData.getMap());
		String inputPageName = contextData.get("INPUTPAGENAME");
		mmap.put("NODEID", nodeId);
		mmap.put("PROCESSID", processId);
		mmap.put("TASKID", taskId);
		if(inputPageName !=null && !inputPageName.isEmpty()) {
			return inputPageName;
		}else {
			return "error/404";
		}
		
	}
	
	
	@GetMapping("")
	public String doPrepare(String nodeId,String processId,String taskId,ModelMap mmap) {
		System.out.println("----------------111111111111--------------");
		System.out.println(nodeId);
		System.out.println(processId);
		//NodeInfo nodeInfo = nodeInfoService.getNodeInfo(nodeId);
		//System.out.println("nodeInfo="+nodeInfo);
		//System.out.println("inputPageName="+inputPageName);
		ContextData contextData= new ContextData();
		contextData.put("NODEID", nodeId);
		contextData.put("PROCESSID", processId);
		contextData.put("TASKID", taskId);
		doPrepareService.doPrepare(contextData);
		mmap.putAll(contextData.getMap());
		String inputPageName = contextData.get("INPUTPAGENAME");
		mmap.put("NODEID", nodeId);
		mmap.put("PROCESSID", processId);
		mmap.put("TASKID", taskId);
		if(inputPageName !=null && !inputPageName.isEmpty()) {
			return inputPageName;
		}else {
			return "error/404";
		}
		
	}


	
}
