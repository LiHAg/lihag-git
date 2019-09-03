package com.golden.common.utils;

import java.util.Locale;

import org.springframework.context.MessageSource;

import com.golden.common.utils.spring.SpringUtils;

/**
 * 获取i18n资源文件
 * 
 * @author jinma.zheng
 */
public class MessageUtils {
	/**
	 * 根据消息键和参数 获取消息 委托给spring messageSource
	 *
	 * @param code
	 *            消息键
	 * @param args
	 *            参数
	 * @return
	 */
	public static String message(String code, Object... args) {
		MessageSource messageSource = SpringUtils.getBean(MessageSource.class);
		return messageSource.getMessage(code, args, null);
	}

	/**
	 * 根据消息代码取消息内容
	 * 
	 * @param code-消息代码
	 * @param args-参数
	 * @param defaultMessage-缺省消息
	 * @return 消息内容
	 */
	public static String getMessage(String code, Object[] args, String defaultMessage) {
		return getMessage(code, args, defaultMessage, Locale.getDefault());
	}

	public static String getMessage(String code, Object[] args, String defaultMessage, Locale locale) {
		MessageSource messageSource = SpringUtils.getBean(MessageSource.class);
		if (messageSource == null) {
			return defaultMessage;
		}
		// 消息内容并返回
		return messageSource.getMessage(code, args, defaultMessage, locale);
	}
}
