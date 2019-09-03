package com.golden.workflow.router;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.golden.workflow.router.mapper.WorkRouterMapper;
import com.golden.workflow.router.model.WorkRouter;

/**
 * @author jinma.zheng
 */
@Service("workRouterService")
public class WorkRouterServiceImpl implements WorkRouterService {

	@Autowired
	private WorkRouterMapper workRouterMapper;
	
	@Override
	public WorkRouter selectWorkRouterByRC(String routerCode) {
		return workRouterMapper.selectWorkRouter(routerCode);
	}

	@Override
	public List<WorkRouter> selectWorkRouterList() {
		return workRouterMapper.selectWorkRouterList();
	}

}
