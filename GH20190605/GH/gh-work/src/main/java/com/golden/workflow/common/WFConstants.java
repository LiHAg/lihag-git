package com.golden.workflow.common;

/**
 * @author jinma.zheng
 * @date 2019年3月30日
 */
public class WFConstants {
	public final static int START_MODE_FIRSTMANUALTASK = 16;// 不执行手工任务首节点
	public final static int START_MODE_MANUAL = 8;
	public final static int START_MODE_CALL = 4;
	public final static int START_MODE_TIMER = 2;
	public final static int START_MODE_EVENT = 1;
	
	public final static int PROCESS_STATE_RUNNING = 1;
	public final static int PROCESS_STATE_PAUSED = 2;
	public final static int PROCESS_STATE_COMPLETED = 3;
	public final static int PROCESS_STATE_PENDING = 4;
	public final static int PROCESS_START_MODE_FIRSTMANUALTASK = -4;
	public final static int PROCESS_START_MODE_MANUAL = -3;
	public final static int PROCESS_START_MODE_TIMER = -2;
	public final static int PROCESS_START_MODE_EVENT = -1;
	public final static int PROCESS_START_MODE_CALL = 0;
	
	public final static int NODE_STATE_STATIC = 1;
	public final static int NODE_STATE_PROCESS_PAUSE = 2;
	public final static int NODE_STATE_PROCESS_PENDING = 3;
	public final static int NODE_STATE_ENGINE_PAUSE = 4;
	public final static int NODE_STATE_PENDING = 5;
	public final static int NODE_STATE_EVENT_WAIT = 6;
	public final static int NODE_TYPE_TASKNODE = 1;
	public final static int NODE_TYPE_STARTNODE = 2;
	public final static int NODE_TYPE_DONENODE = 4;
	public final static int NODE_TYPE_SPLITNODE = 8;
	public final static int NODE_TYPE_JOINNODE = 16;
	public final static int NODE_TYPE_HANDLENODE = 32;
	public final static int NODE_TYPE_EVENTNODE = 64;
	public final static int NODE_TYPE_TIMERNODE = 128;
	public final static int NODE_TYPE_COMPOUNDNODE = 256;
	
	public static final int TASK_STATE_CANCELED = 0;
	public static final int TASK_STATE_CREATED = 1;
	public static final int TASK_STATE_STARTED = 2;
	public static final int TASK_STATE_PENDING = 4;
	public static final int TASK_STATE_OVERDUE = 8;
	public static final int TASK_STATE_COMPLETED = 16;
	public static final int TASK_PRIORITY_LOW = 1;
	public static final int TASK_PRIORITY_MED = 2;
	public static final int TASK_PRIORITY_HIGH = 3;
	public static final int TASK_OPERATION_START = 1;
	public static final int TASK_OPERATION_COMMIT = 2;
	public static final int TASK_OPERATION_ABANDON = 4;
	public static final int TASK_OPERATION_SUSPEND = 8;
	public static final int TASK_OPERATION_RESUME = 16;
	public static final int TASK_OPERATION_REJECT = 32;
	public static final int TASK_OPERATION_RETRACT = 64;
	public static final String TASK_ORDERBY_PRIORITY = "PRIORITY";
	public static final String TASK_ORDERBY_CREATEDTIME = "STARTED";
	public static final String TASK_ORDERBY_NAME = "TASKNAME";

	/*
	 * BUSTYPE 业务类型 BUSNO 业务号 BUSORGID 业务处理机构 BUSSTEP 业务所处状态
	 * 
	 */
	public final static String TASK_BUSTYPE = "BUSTYPE";
	public final static String TASK_BUSNO = "BUSNO";
	public final static String TASK_BUSORGID = "BUSORGID";
	public final static String TASK_BUSSTEP = "BUSSTEP";
	public final static String TASK_HANDLE_DATE = "BUSHANDLEDATE";
	public final static String TASK_BUSUSERID = "BUSUSERID";
	public final static String TASK_TASKNAME = "TASKNAME";
	public final static String TASK_EXPRESSION = "EXPRESSION";
}
