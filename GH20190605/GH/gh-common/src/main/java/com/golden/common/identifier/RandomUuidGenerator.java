package com.golden.common.identifier;

import java.util.UUID;

import org.springframework.stereotype.Service;

/**
 * 随机UUID产生器
 */
@Service
public class RandomUuidGenerator implements StringIdGenerator{
	/**
	 * 产生字符串ID
	 * 
	 * @param seed
	 *            种子对象
	 * @return 字符串ID
	 */
	public String generateId(){
		UUID uuid = UUID.randomUUID();
		return Long.toHexString(uuid.getLeastSignificantBits()) + Long.toHexString(uuid.getMostSignificantBits());
	}

	public static void main(String[] args) {
		RandomUuidGenerator r = new RandomUuidGenerator();
		System.out.println(r.generateId());
	}
}
