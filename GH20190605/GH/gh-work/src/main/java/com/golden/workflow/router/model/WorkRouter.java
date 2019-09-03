package com.golden.workflow.router.model;

import java.io.Serializable;

public class WorkRouter implements Serializable {
	
	private static final long serialVersionUID = -6585754655381421966L;
	
	// 路由代码
	private String routerCode;
	// 路由名称
	private String routerName;
	// 路由说明
	private String routerExplain;
	// 有效标志
	private String validFg;
	// 任务代码
	private String taskCode;
	// 处理方式
	private String dealFg;
	
	// 任务配置
	//private WorkTask workTask;
	
	public String getRouterCode() {
		return routerCode;
	}
	
	public void setRouterCode(String routerCode) {
		this.routerCode = routerCode;
	}
	
	public String getRouterName() {
		return routerName;
	}
	
	public void setRouterName(String routerName) {
		this.routerName = routerName;
	}
	
	public String getRouterExplain() {
		return routerExplain;
	}
	
	public void setRouterExplain(String routerExplain) {
		this.routerExplain = routerExplain;
	}
	
	public String getValidFg() {
		return validFg;
	}
	
	public void setValidFg(String validFg) {
		this.validFg = validFg;
	}
	
	public String getTaskCode() {
		return taskCode;
	}
	
	public void setTaskCode(String taskCode) {
		this.taskCode = taskCode;
	}
	
	public String getDealFg() {
		return dealFg;
	}
	
	public void setDealFg(String dealFg) {
		this.dealFg = dealFg;
	}
	
}
