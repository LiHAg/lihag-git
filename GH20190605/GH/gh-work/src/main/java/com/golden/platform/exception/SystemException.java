package com.golden.platform.exception;

/**
 * 
 * @author jinma.zheng
 * @date 2019年4月1日
 */
public class SystemException extends ErrorCodeException {
	private static final long serialVersionUID = 1L;

	public SystemException(Throwable cause) {
		this(SYS_ERROR, cause);
	}

	public SystemException(String errorCode, String errorMessage) {
		super(errorCode, errorMessage);
	}

	public SystemException(String errorCode) {
		super(errorCode);
	}

	public SystemException(String errorCode, Throwable cause) {
		super(errorCode, cause);
	}

}
