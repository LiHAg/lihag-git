package com.golden.workflow.common;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author jinma.zheng
 * @date 2019年3月29日
 */
public class WorkflowException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private final static Logger logger = LoggerFactory.getLogger(WorkflowException.class);
	public final static String EXP_CODE_TASKINFO_NOT_EXIST = "20";
    public final static String EXP_CODE_TASK_COMMIT_ERROR = "66";
    public final static String EXP_CODE_TASK_RETRACT_ERROR = "61";
	private String errorCode;// 错误码
	private String errorMsg;// 错误信息
	private String errorClass;// 错误类
	private String stackTrace;// 堆栈信息
	private Throwable t;// 错误原因
	private StackTraceElement[] errStack;// 错误堆栈

	public WorkflowException(String errorCode) {
		super();
		this.errorCode = errorCode;
		this.errorMsg = "WorkflowException(Code=" + errorCode + "):";
		// this.errorClass = sun.reflect.Reflection.getCallerClass(2).getName();
		this.errStack = this.getStackTrace();
		logger.debug("WorkflowException:" + errorMsg);
	}

	public WorkflowException(String errorCode, String resultMsg) {
		super();
		this.errorCode = errorCode;
		this.errorMsg = resultMsg;
		logger.debug("WorkflowException:" + resultMsg);
	}

	public WorkflowException(Throwable t) {
		if (t instanceof WorkflowException) {
			WorkflowException ue = (WorkflowException) t;
			this.errorClass = ue.getErrorClass();
			this.errorMsg = ue.getErrMsg();
			this.errStack = ue.getErrStack();
			this.errorCode = ue.getErrorCode();
		} else {
			this.errorClass = t.getClass().getName();
			this.errorMsg = t.getMessage();
			this.errStack = t.getStackTrace();
			t.printStackTrace();
		}
		logger.debug("WorkflowException:" + errorMsg);
	}

	public WorkflowException(String sErrorCode, Throwable t) {
		this.errorMsg = t.getMessage();
		errorCode = sErrorCode;
		if (t instanceof WorkflowException) {
			WorkflowException ue = (WorkflowException) t;
			this.errorMsg = ue.getErrMsg();
			this.errStack = ue.getErrStack();
			this.errorClass = ue.getErrorClass();
		} else {
			this.errorClass = t.getClass().getName();
			this.errorMsg = t.getMessage();
			this.errStack = t.getStackTrace();
		}
	}

	public String getErrorCode() {
		return this.errorCode;
	}

	public String getStackInfo() {
		return this.stackTrace;
	}

	protected void setStackTrace(Throwable t) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		t.printStackTrace(pw);
		this.stackTrace = sw.toString() + "\n";
	}

	public Throwable getOriginalEx() {
		return this.t;
	}

	public String getMessage() {
		return this.errorMsg;
	}

	/**
	 * @return 返回 errorMsg。
	 */
	public String getErrMsg() {
		return errorMsg;
	}

	/**
	 * 
	 * @return 返回 errStack。
	 */
	public StackTraceElement[] getErrStack() {
		return errStack;
	}

	/**
	 * 打印所有的错误信息
	 *
	 */
	public void printAllStack(PrintStream ps) {
		synchronized (ps) {
			ps.println("Caused by: " + errorClass);
			ps.println("ErrMsg: " + errorMsg);
			for (int i = 0; i < errStack.length; i++)
				ps.println("\tat " + errStack[i]);

			ps.flush();
		}
	}

	/**
	 * @return 返回 errorClass。
	 */
	public String getErrorClass() {
		return errorClass;
	}

}
