package com.golden.platform.exception;

public interface ErrorCodes {
	/*
	 * 编码方式为AXXYYZZ：A=0:系统错误， A=1：应用错误， XX代表系统编码， YY代表模块编码，ZZ代表该模块内的错误编码,
	 */
	String SYS = "0";
	String APP = "1";
	/* 内模块编码规则如下： */
	String DEFAULT = "00";
	
	/* 00:通用模块 */
	String GENERAL = "00";
	String SYS_ERROR = SYS + DEFAULT + GENERAL + "01";  //0000001 应用错误！
	
	/* 03:expression模块 */
	String EXPRESSION = "03";
	String SYS_EXPRESSION_EVALUATE = SYS + DEFAULT + EXPRESSION + "01";  //0000301
}
