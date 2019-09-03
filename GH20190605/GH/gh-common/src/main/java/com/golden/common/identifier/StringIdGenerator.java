package com.golden.common.identifier;


/**
 * 字符串ID产生器
 */
public interface StringIdGenerator {
	/**
	 * 产生字符串ID
	 * 
	 * @param seed
	 *            种子对象
	 * @return 字符串ID
	 */
	String generateId();
}
