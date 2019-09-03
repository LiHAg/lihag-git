package com.golden.common.identifier;


/**
 * 数字ID产生器
 * 
 * @author
 * @version 1.0
 * @since
 */
public interface NumberIdGenerator {
	/**
	 * 产生数字ID
	 * @param seed 种子对象
	 * @return 数字ID
	 */
	long generateId();
}
