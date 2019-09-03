package com.ciTreat.web.controller.work;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.golden.common.base.AjaxResult;
import com.golden.common.page.TableDataInfo;
import com.golden.framework.web.base.BaseController;
import com.golden.workflow.nodeinfo.model.NodeInfo;
import com.golden.workflow.nodeinfo.service.NodeInfoService;
import com.golden.workflow.util.FlowRefBuilder;

/**
 * 服务器监控
 * 
 * @author jinma.zheng
 */
@Controller
@RequestMapping("/work/nodeInfo")
public class NodeInfoController extends BaseController {
	//private static final Logger log = LoggerFactory.getLogger(WorkJobController.class);
	private String prefix = "work/nodeInfo";
    @Autowired
    private NodeInfoService nodeInfoService;
    
	@GetMapping()
	public String ciTtask(ModelMap mmap) throws Exception {
		return prefix + "/nodeInfo";
	}

	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(String templateId) {
		startPage();
		List<NodeInfo> list = nodeInfoService.getNodeInfoList(templateId);
		return getDataTable(list);
	}


    @GetMapping("/add/{templateId}")
    public String add(@PathVariable("templateId") String templateId, ModelMap mmap)
    {
    	System.out.println("---------------------" + templateId);
        mmap.put("templateId", templateId);
        return prefix + "/add";
    }
    
    
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(NodeInfo nodeInfo)
    {
    	String nodeId;
		try {
			nodeId = FlowRefBuilder.getBigInstanceNodeId();
		} catch (Exception e) {
			return error("生成节点ID异常");
		}
		nodeInfo.setNodeId(nodeId);
		
    	String nodeType = nodeInfo.getNodeType();
    	if(!"START_NODE".equals(nodeType)) {
    		String preNodeId = nodeInfo.getPreNodeId();
    		if(preNodeId == null || preNodeId.equals("")) {
    			return error("未设置上节点");
    		}
    		NodeInfo preNodeInfo = nodeInfoService.getNodeInfo(preNodeId);
    		if(preNodeInfo == null) {
    			return error("未找到上节点");
    		}
    		
    		preNodeInfo.setNextNodeId(nodeId);
    		int rs = nodeInfoService.updateNextNodeId(nodeId,preNodeInfo.getNodeId());
    		
    	}
    	int rs = nodeInfoService.insertNodeInfo(nodeInfo);
        return toAjax(rs);
    }
}
