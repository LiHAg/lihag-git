package com.golden.platform.exception;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.UndeclaredThrowableException;
import java.util.Calendar;
import java.util.Date;

import com.golden.common.utils.MessageUtils;

/**
 * 带错误码的异常类型接口定
 * 
 * @author jinma.zheng
 * @date 2019年4月1日
 */
public class ErrorCodeException extends RuntimeException implements ErrorCodes {

	private static final long serialVersionUID = 1L;

	// private static ThreadLocal<Boolean> raiseEventFlag = new
	// ThreadLocal<Boolean>();
	// static {
	// raiseEventFlag.set(true);
	// }

	private final String errorCode;
	private final String errorMessage;
	private final ExceptionLevel level;
	private volatile Object source;

	private final Object[] arguments;

	private final Date occurTime = Calendar.getInstance().getTime();

	public ErrorCodeException(String errorCode, String errorMessage, boolean raiseEvent) {
		super(errorMessage);
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
		this.arguments = null;
		this.level = ExceptionLevel.NORMAL;
		/*
		 * if (raiseEvent) { this.raiseExceptionEvent(); }
		 */
	}

	public ErrorCodeException(String errorCode, String errorMessage) {
		this(errorCode, errorMessage, true);
	}

	public ErrorCodeException(String errorCode) {
		this(errorCode, true);
	}

	public ErrorCodeException(String errorCode, boolean raiseEvent) {
		super("Error happened! Error code is: " + errorCode);
		this.errorCode = errorCode;
		this.arguments = null;
		this.level = ExceptionLevel.NORMAL;
		this.errorMessage = MessageUtils.message(this.errorCode, this.getArguments(), super.getMessage());
		// if (raiseEvent)
		// this.raiseExceptionEvent();
	}

	public ErrorCodeException(String errorCode, Object[] arguments) {
		super("Error happened! Error code is: " + errorCode);
		this.errorCode = errorCode;
		this.arguments = arguments;
		this.level = ExceptionLevel.NORMAL;
		this.errorMessage = MessageUtils.message(this.errorCode, this.getArguments(), super.getMessage());
		// this.raiseExceptionEvent();
	}

	/**
	 * 带有错误代码和错误原因的构�1�7�函敄1�7
	 * 
	 * @param errorCode
	 *            错误代码
	 * @param cause
	 *            错误原因
	 */
	public ErrorCodeException(String errorCode, Throwable cause) {
		this(errorCode, cause, (Object[]) null);
	}

	public ErrorCodeException(String errorCode, Throwable cause, Object[] arguments) {
		super("Error happend! ErrorCode is: " + errorCode, cause);
		this.errorCode = errorCode;
		this.arguments = arguments;
		this.level = ExceptionLevel.NORMAL;
		this.errorMessage = MessageUtils.message(this.errorCode, this.getArguments(), super.getMessage());
		// this.raiseExceptionEvent();
	}

	public ErrorCodeException(String errorCode, String errorMessage, boolean raiseEvent, ExceptionLevel level) {
		super(errorMessage);
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
		this.arguments = null;
		this.level = level;
		/*
		 * if (raiseEvent) { this.raiseExceptionEvent(); }
		 */
	}

	public ErrorCodeException(String errorCode, String errorMessage, ExceptionLevel level) {
		this(errorCode, errorMessage, true, level);
	}

	public ErrorCodeException(String errorCode, ExceptionLevel level) {
		this(errorCode, true, level);
	}

	public ErrorCodeException(String errorCode, boolean raiseEvent, ExceptionLevel level) {
		super("Error happened! Error code is: " + errorCode);
		this.errorCode = errorCode;
		this.arguments = null;
		this.level = level;
		this.errorMessage = MessageUtils.message(this.errorCode, this.getArguments(), super.getMessage());
		// if (raiseEvent)
		// this.raiseExceptionEvent();
	}

	public ErrorCodeException(String errorCode, Object[] arguments, ExceptionLevel level) {
		super("Error happened! Error code is: " + errorCode);
		this.errorCode = errorCode;
		this.arguments = arguments;
		this.level = level;
		this.errorMessage = MessageUtils.message(this.errorCode, this.getArguments(), super.getMessage());
		// this.raiseExceptionEvent();
	}

	public ErrorCodeException(String errorCode, Throwable cause, ExceptionLevel level) {
		this(errorCode, cause, null, level);
	}

	public ErrorCodeException(String errorCode, Throwable cause, Object[] arguments, ExceptionLevel level) {
		super("Error happend! ErrorCode is: " + errorCode, cause);
		this.errorCode = errorCode;
		this.arguments = arguments;
		this.errorMessage = MessageUtils.message(this.errorCode, this.getArguments(), super.getMessage());
		// this.raiseExceptionEvent();
		this.level = level;
	}

	public String getErrorCode() {
		return this.errorCode;
	}

	public String getStackTraceString() {
		StringWriter sw = new java.io.StringWriter(1024);
		PrintWriter pw = new java.io.PrintWriter(sw);
		this.printStackTrace(pw);
		pw.close();
		return sw.toString();
	}

	/**
	 * 转化为字符串
	 * 
	 * @return String 错误字符丄1�7
	 */
	public String toString() {
		return getMessage();
	}

	public Object[] getArguments() {
		return this.arguments;
	}

	public String getMessage() {
		if (this.errorMessage == null)
			return super.getMessage();
		return this.errorMessage;

	}

	public ErrorCodeException getRootErrorCodeException() {
		ErrorCodeException root = this;
		while (root.getCause() != null && root.getCause() instanceof ErrorCodeException) {
			root = (ErrorCodeException) root.getCause();
		}
		return root;
	}

	public Throwable getRootCause() {
		Throwable root = this;
		while (!(root.getClass().getName().startsWith("java.") || root.getClass().getName().startsWith("javax.")) && root.getCause() != null)
			root = root.getCause();
		if (root instanceof InvocationTargetException)
			root = ((InvocationTargetException) root).getTargetException();
		else if (root instanceof UndeclaredThrowableException)
			root = ((UndeclaredThrowableException) root).getUndeclaredThrowable();
		return root;
	}

	public Object getSource() {
		return source;
	}

	public void setSource(Object source) {
		this.source = source;
	}

	public Date getOccurTime() {
		return occurTime;
	}

	public boolean isSystemException() {
		return this.errorCode.startsWith(SYS);
	}

	public boolean isApplicationException() {
		return this.errorCode.startsWith(APP);
	}

	public static boolean isSystemException(String errorCode) {
		return errorCode.startsWith(SYS);
	}

	public static boolean isApplicationException(String errorCode) {
		return errorCode.startsWith(APP);
	}

	public ExceptionLevel getLevel() {
		return level;
	}

}
