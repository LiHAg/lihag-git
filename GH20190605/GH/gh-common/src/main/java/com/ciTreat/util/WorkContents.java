package com.ciTreat.util;

public interface WorkContents {
	public final static String[] TASK_RESERVED_WORD = { "NodeId", "PackageId", "ProcessId", "TaskName", "TemplateId" };

	/**
	 * 有效标志
	 * 0-启用
	 * 1-停用
	 */
	public static final String VALID_FG_VALID = "0";
	public static final String VALID_FG_INVALID = "1";
	
	/**
	 * 处理方式
	 * 0-同步
	 * 1-异步
	 */
	public static final String DEAL_FG_SYNC = "0";
	public static final String DEAL_FG_ASYNC = "1";
	
	/**
	 * 异常处理方式
	 * 0-忽略
	 * 1-停止当前任务的执行
	 * 2-重复执行该作业
	 */
	public static final String EX_HANDLE_FG_IGNORE = "0";
	public static final String EX_HANDLE_FG_STOP = "1";
	public static final String EX_HANDLE_FG_REPEAT = "2";
	
	/**
	 * 任务状态
	 * 0-等待执行
	 * 1-正在执行
	 * 2-执行结束
	 * 3-异常停止
	 */
	public static final String TASK_STATUS_WAIT = "0";
	public static final String TASK_STATUS_ING = "1";
	public static final String TASK_STATUS_DONE = "2";
	public static final String TASK_STATUS_FAIL_STOP = "3";
	
	/**
	 * 作业状态
	 * 0-等待执行
	 * 1-正在执行
	 * 2-执行成功
	 * 3-异常停止
	 * 4-不执行（执行条件不满足）
	 * 5-已忽略
	 */
	public static final String JOB_STATUS_WAIT = "0";
	public static final String JOB_STATUS_ING = "1";
	public static final String JOB_STATUS_DONE = "2";
	public static final String JOB_STATUS_FAIL_STOP = "3";
	public static final String JOB_STATUS_CONDITION_FALSE = "4";
	public static final String JOB_STATUS_IGNORED = "5";
	
	/**
	 * 是否测试环境
	 */
	public static final boolean IS_TEST_FLAG = true;
}
