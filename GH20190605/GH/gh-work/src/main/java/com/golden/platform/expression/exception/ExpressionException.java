package com.golden.platform.expression.exception;

import com.golden.common.utils.MessageUtils;
import com.golden.platform.exception.ErrorCodes;
import com.golden.platform.exception.ExceptionLevel;

/**
 * 
 * @author
 * @since
 * @version 1.0
 */

public class ExpressionException extends RuntimeException implements ErrorCodes {
	private static final long serialVersionUID = 1L;
	private final String expression;
	private final String errorCode;
	private final String errorMessage;
	private final ExceptionLevel level;
	private volatile Object source;
	private final Object[] arguments;

	public ExpressionException(String expression, Throwable cause) {
		super("Error happend! ErrorCode is: " + SYS_EXPRESSION_EVALUATE, cause);
		this.errorCode = SYS_EXPRESSION_EVALUATE;
		this.arguments = new Object[] { expression };
		this.level = ExceptionLevel.NORMAL;
		this.errorMessage = MessageUtils.message(this.errorCode, this.getArguments(), super.getMessage());
		// this.raiseExceptionEvent(); //创建事件
		this.expression = expression;
	}

	public String getExpression() {
		return expression;
	}

	public Object getSource() {
		return source;
	}

	public void setSource(Object source) {
		this.source = source;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public ExceptionLevel getLevel() {
		return level;
	}

	public Object[] getArguments() {
		return arguments;
	}

}
