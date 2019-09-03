package com.golden.workflow.router;

import java.util.List;

import com.golden.workflow.router.model.WorkRouter;

/**
 * @author jinma.zheng
 */
public interface WorkRouterService {

	WorkRouter selectWorkRouterByRC(String routerCode);

	List<WorkRouter> selectWorkRouterList();
}
