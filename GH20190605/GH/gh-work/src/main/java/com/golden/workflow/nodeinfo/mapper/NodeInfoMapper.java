package com.golden.workflow.nodeinfo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.golden.workflow.nodeinfo.model.NodeInfo;

/**
 * 
 * @author jinma.zheng
 */
public interface NodeInfoMapper {
	NodeInfo getStartNode(String templateId);
	//NodeInfo getNodeInfo(@Param("templateId") String templateId, @Param("nodeId") String nodeId);
	NodeInfo getNodeInfo(String nodeId);
	List<NodeInfo> getNodeInfoList(String templateId);
	int insertNodeInfo(NodeInfo nodeInfo);
	int updateNextNodeId(@Param("nextNodeId") String nextNodeId, @Param("nodeId") String nodeId);

}
