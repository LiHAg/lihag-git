package com.golden.common.identifier;

import java.util.Calendar;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

/**
 * 基于时间的数字ID产生器
 * 
 * @author
 * @version 1.0
 * @since
 */
@Service("numberIdGenerator")
public class TimeBasedNumberIdGenerator implements NumberIdGenerator {

	private final AtomicLong confusionValue = new AtomicLong(0);

	/**
	 * 产生数字ID
	 * 
	 * @param seed
	 *            种子对象
	 * @return 数字ID
	 */
	public long generateId() {
		long result = Calendar.getInstance().getTimeInMillis() * 1000 + confusionValue.getAndIncrement() % 1000;
		return result;
	}

	public static void main(String[] args) {
		TimeBasedNumberIdGenerator t = new TimeBasedNumberIdGenerator();
		System.out.println(t.generateId());
	}
}
