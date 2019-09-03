package com.golden.workflow.nodeinfo.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.golden.workflow.common.WorkflowException;
import com.golden.workflow.engine.process.ProcessManage;
import com.golden.workflow.nodeinfo.mapper.NodeInfoMapper;
import com.golden.workflow.nodeinfo.model.NodeInfo;
import com.golden.workflow.util.FlowRefBuilder;

/**
 * @author jinma.zheng
 * @date 2019年3月30日
 */
@Service
public class NodeInfoServiceImpl implements NodeInfoService {
	private final static Logger logger = LoggerFactory.getLogger(NodeInfoServiceImpl.class);
	@Autowired
	NodeInfoMapper nodeMapper;

	@Override
	public NodeInfo getStartNode(String templateId){
		return nodeMapper.getStartNode(templateId);
	}

	@Override
	public NodeInfo getNodeInfo(String nodeId) {
		return nodeMapper.getNodeInfo(nodeId);
	}

	@Override
	public List<NodeInfo> getNodeInfoList(String templateId) {
		return nodeMapper.getNodeInfoList(templateId);
	}

	@Override
	public int insertNodeInfo(NodeInfo nodeInfo) {
		return nodeMapper.insertNodeInfo(nodeInfo);
	}

	@Override
	public int updateNextNodeId(String nextNodeId, String nodeId) {
		return nodeMapper.updateNextNodeId(nextNodeId, nodeId);
	}

}
