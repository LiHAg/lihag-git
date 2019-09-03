package com.golden.workflow.router.mapper;

import java.util.List;

import com.golden.workflow.router.model.WorkRouter;

/**
 * 数据层
 * 
 * @author jinma.zheng
 */
public interface WorkRouterMapper {
	public WorkRouter selectWorkRouter(String routerCode);
	public List<WorkRouter> selectWorkRouterList();

}
