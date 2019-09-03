package com.golden.workflow.nodeinfo.service;

import java.util.List;

import com.golden.workflow.nodeinfo.model.NodeInfo;

/**
 * @author jinma.zheng
 * @date 2019年3月30日
 */
public interface NodeInfoService {
	NodeInfo getStartNode(String templateId);
	NodeInfo getNodeInfo(String nStaticNodeId);
	List<NodeInfo> getNodeInfoList(String templateId);
	int insertNodeInfo(NodeInfo nodeInfo);
	int updateNextNodeId(String nextNodeId, String nodeId);
}
