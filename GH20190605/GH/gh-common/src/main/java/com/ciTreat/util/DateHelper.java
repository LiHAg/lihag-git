package com.ciTreat.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 
 * @author
 *
 * @version
 * @since
 */
public abstract class DateHelper implements Constants {

	private static Logger logger = LoggerFactory.getLogger(DateHelper.class);

	public static Date currentTime() {
		return Calendar.getInstance().getTime();
	}

	/**
	 * Date对象转日期字符串
	 * 
	 * @param date
	 *            待转换日期
	 * @return 日期字符串
	 */
	public static String date2String(Date date) {
		return date2String(date, DATE_FORMAT);
	}

	public static String date2String(Date date, String format) {
		if (date == null)
			return null;
		if (StringUtils.isEmpty(format))
			format = DATE_FORMAT;
		SimpleDateFormat sd = new SimpleDateFormat(format);
		// 日期格式化对象将日期对象转换为日期字符串
		return sd.format(date);
	}

	public static String time2String(Date date) {
		return time2String(date, TIME_FORMAT);
	}

	public static String time2String(Date date, String format) {
		if (date == null)
			return null;
		if (StringUtils.isEmpty(format))
			format = TIME_FORMAT;
		SimpleDateFormat sd = new SimpleDateFormat(format);
		// 日期格式化对象将日期对象转换为日期字符串
		return sd.format(date);
	}

	public static String currentDateString() {
		return date2String(Calendar.getInstance().getTime(), DATE_FORMAT);
	}

	public static String currentDateString(String format) {
		return date2String(Calendar.getInstance().getTime(), format);
	}

	public static String currentDateTimeString() {
		return datetime2String(Calendar.getInstance().getTime(),
				DATETIME_FORMAT);
	}

	public static String currentDateTimeString(String format) {
		return datetime2String(Calendar.getInstance().getTime(), format);
	}

	/**
	 * 
	 * Date对象转日期时间字符串
	 * 
	 * @param date
	 *            待转换日期
	 * @return 日期时间字符串
	 */
	public static String datetime2String(Date date) {
		return datetime2String(date, DATETIME_FORMAT);
	}

	public static String datetime2String(Date date, String format) {
		if (date == null)
			return null;
		if (StringUtils.isEmpty(format))
			format = DATETIME_FORMAT;
		SimpleDateFormat sd = new SimpleDateFormat(format);
		// 日期格式化对象将日期对象转换为日期时间字符串
		return sd.format(date);
	}

	public static boolean isValidFormat(String date, String format) {
		if (StringUtils.isEmpty(date))
			return true;
		if (StringUtils.isEmpty(format))
			return true;
		SimpleDateFormat sd = new SimpleDateFormat(format);
		try {
			sd.parse(date);
			return true;
		} catch (ParseException e) {
			return false;
		}
	}

	/**
	 * 日期字符串转Date对象
	 * 
	 * @param String
	 *            待转换日期字符串
	 * @return Date
	 */
	public static Date string2Date(String date) {
		return string2Date(date, DATE_FORMAT);
	}

	public static Date string2Date(String date, String format) {
		if (date == null)
			return null;
		if (StringUtils.isEmpty(format))
			format = DATE_FORMAT;
		SimpleDateFormat df = new SimpleDateFormat(format);
		Date returnDate = null;
		try {
			// 日期格式化对象将日期字符串转换为日期对象
			returnDate = df.parse(date);
		} catch (ParseException e) {
			logger.error("Parse date:" + date + " with format:" + format
					+ " failed!", e);
			throw new IllegalArgumentException("Date:" + date
					+ " is not in format:" + format, e);
		}
		return returnDate;
	}

	/**
	 * 日期时间字符串转Date对象
	 * 
	 * @param date
	 *            待转换日期时间字符串
	 * @return Date
	 */
	public static Date string2Datetime(String datetime) {
		return string2Datetime(datetime, DATETIME_FORMAT);
	}

	public static Date string2Datetime(String datetime, String format) {
		if (datetime == null)
			return null;
		if (StringUtils.isEmpty(format))
			format = DATETIME_FORMAT;
		// 创建日期格式化对象
		SimpleDateFormat df = new SimpleDateFormat(format);
		Date returnDate = null;
		try {
			// 日期格式化对象将日期时间字符串转换为日期对象
			returnDate = df.parse(datetime);
		} catch (ParseException e) {
			logger.error("Parse datetime:" + datetime + " with format:"
					+ format + " failed!", e);
			throw new IllegalArgumentException("Datetime:" + datetime
					+ " is not in format:" + format, e);
		}
		return returnDate;
	}

	/**
	 * 
	 * @param date
	 * @return
	 */
	public static Date date2Date(Date date) {
		if (date == null) {
			return null;
		}
		if (date instanceof java.sql.Date || date instanceof java.sql.Timestamp) {
			return new Date(date.getTime());
		} else {
			return date;
		}
	}

	public static boolean between(Date from, Date to, Date date) {

		long fromMills = 0, dateMills = 0, toMills = Long.MAX_VALUE;
		if (from != null)
			fromMills = from.getTime();
		if (to != null)
			toMills = to.getTime();
		if (date != null)
			dateMills = date.getTime();
		return fromMills <= dateMills && dateMills <= toMills;
	}

	public static int getYear(Date date) {
		Validate.notNull(date, "Parameter date cann't be null!");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.YEAR);
	}

	public static int getMonth(Date date) {
		Validate.notNull(date, "Parameter date cann't be null!");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.MONTH);
	}

	public static int getYearDay(Date date) {
		Validate.notNull(date, "Parameter date cann't be null!");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.DAY_OF_YEAR);
	}

	public static int getMonthDay(Date date) {
		Validate.notNull(date, "Parameter date cann't be null!");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.DAY_OF_MONTH);
	}

	public static int getWeekDay(Date date) {
		Validate.notNull(date, "Parameter date cann't be null!");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.DAY_OF_WEEK);
	}

	public static int getHours(Date date) {
		Validate.notNull(date, "Parameter date cann't be null!");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.HOUR_OF_DAY);
	}

	public static int getMinutes(Date date) {
		Validate.notNull(date, "Parameter date cann't be null!");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.MINUTE);
	}

	public static int getSeconds(Date date) {
		Validate.notNull(date, "Parameter date cann't be null!");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.SECOND);
	}

	public static boolean isWeekEnd(Date date) {
		if (date == null)
			return false;
		return getWeekDay(date) == Calendar.SUNDAY;
	}

	public static boolean isMonthEnd(Date date) {
		if (date == null)
			return false;
		Calendar current = Calendar.getInstance();
		current.setTime(date);
		int curMonth = current.get(Calendar.MONTH);
		current.add(Calendar.DAY_OF_MONTH, 1);
		return curMonth != current.get(Calendar.MONTH);
	}

	public static boolean isTenDaysEnd(Date date) {
		if (date == null)
			return false;
		if (isMonthEnd(date))
			return true;
		return getMonthDay(date) == 10 || getMonthDay(date) == 20;
	}

	public static boolean isQuarterEnd(Date date) {
		if (date == null)
			return false;
		if (!isMonthEnd(date))
			return false;
		return getMonth(date) == Calendar.MARCH
				|| getMonth(date) == Calendar.JUNE
				|| getMonth(date) == Calendar.SEPTEMBER
				|| getMonth(date) == Calendar.DECEMBER;
	}

	public static boolean isHalfYearEnd(Date date) {
		if (date == null)
			return false;
		if (!isMonthEnd(date))
			return false;
		return getMonth(date) == Calendar.JUNE
				|| getMonth(date) == Calendar.DECEMBER;
	}

	public static boolean isYearEnd(Date date) {
		if (date == null)
			return false;
		if (!isMonthEnd(date))
			return false;
		return getMonth(date) == Calendar.DECEMBER;
	}

	/**
	 * 获得当天的日期字符串
	 * 
	 * @return 日期字符串
	 */
	public static String getCurrentDateString() {
		return getCurrentDateString(DATE_FORMAT);
	}

	public static String getCurrentDateString(String format) {
		Date today = new Date();
		return date2String(today, format);
	}

	/**
	 * 获得当前的时间字符串
	 * 
	 * @return 日期字符串
	 */
	public static String getCurrentDatetimeString() {
		return getCurrentDatetimeString(DATETIME_FORMAT);
	}

	public static String getCurrentDatetimeString(String format) {
		Date now = new Date();
		return datetime2String(now, format);
	}

	public static String getCurrentTimeString() {
		return getCurrentTimeString(TIME_FORMAT);
	}

	public static String getCurrentTimeString(String format) {
		Date now = new Date();
		if (StringUtils.isEmpty(format))
			format = TIME_FORMAT;
		SimpleDateFormat sd = new SimpleDateFormat(format);
		// 日期格式化对象将日期对象转换为日期时间字符串
		return sd.format(now);
	}

	/**
	 * 检查日期字符串
	 * 
	 * @param dateString
	 *            日期字符串
	 * @param formatStyle
	 *            日期格式
	 * @return
	 */
	public static boolean checkDateFormat(String dateString, String format) {
		if (StringUtils.isEmpty(dateString) || StringUtils.isEmpty(format))
			return false;
		SimpleDateFormat sd = new SimpleDateFormat(format);
		try {
			// 日期字符串转换为日期对象
			Date date = sd.parse(dateString);
			// 日期对象转换为日期字符串
			String formatDate = sd.format(date);
			// 检查转换后的日期字符串是否正确
			if (StringUtils.equals(dateString, formatDate)) {
				return true;
			} else {
				return false;
			}
		} catch (ParseException e) {
			return false;
		}
	}

	public static boolean isValidDateRange(Date start, Date end) {
		if (start == null || end == null)
			return true;
		return start.before(end);

	}

	public static boolean isInDateRange(Date point, Date start, Date end) {
		long pointtime = 0, starttime = 0, endtime = Long.MAX_VALUE;
		if (point != null)
			pointtime = point.getTime();
		if (start != null)
			starttime = start.getTime();
		if (end != null)
			endtime = end.getTime();
		return starttime <= pointtime && pointtime <= endtime;
	}

	public static Date beforeDays(Date date, long days) {
		Validate.notNull(date, "Parameter date cann't be null!");
		long milseconds = date.getTime() - days * 24 * 3600 * 1000;
		return new Date(milseconds);
	}

	public static Date beforeDays(long days) {
		return new Date(Calendar.getInstance().getTimeInMillis() - days * 24
				* 3600 * 1000);
	}

	public static Date beforeHours(Date date, long hours) {
		Validate.notNull(date, "Parameter date cann't be null!");
		long milseconds = date.getTime() - hours * 3600 * 1000;
		return new Date(milseconds);
	}

	public static Date beforeHours(long hours) {
		return new Date(Calendar.getInstance().getTimeInMillis() - hours * 3600
				* 1000);
	}

	public static Date beforeMinutes(Date date, long minutes) {
		Validate.notNull(date, "Parameter date cann't be null!");
		long milseconds = date.getTime() - minutes * 60 * 1000;
		return new Date(milseconds);
	}

	public static Date beforeMinutes(long minutes) {
		return new Date(Calendar.getInstance().getTimeInMillis() - minutes * 60
				* 1000);
	}

	public static Date beforeSeconds(Date date, long seconds) {
		Validate.notNull(date, "Parameter date cann't be null!");
		long milseconds = date.getTime() - seconds * 1000;
		return new Date(milseconds);
	}

	public static Date beforeSeconds(long seconds) {
		return new Date(Calendar.getInstance().getTimeInMillis() - seconds
				* 1000);
	}

	public static Date beforeMillis(Date date, long millis) {
		Validate.notNull(date, "Parameter date cann't be null!");
		long milseconds = date.getTime() - millis;
		return new Date(milseconds);
	}

	public static Date beforeMillis(long millis) {
		return new Date(Calendar.getInstance().getTimeInMillis() - millis);
	}

	public static Date afterDays(Date date, long days) {
		Validate.notNull(date, "Parameter date cann't be null!");
		long milseconds = date.getTime() + days * 24 * 3600 * 1000;
		return new Date(milseconds);
	}

	public static Date afterDays(long days) {
		return new Date(Calendar.getInstance().getTimeInMillis() + (long)days * 24
				* 3600 * 1000);
	}

	public static Date afterHours(Date date, long hours) {
		Validate.notNull(date, "Parameter date cann't be null!");
		long milseconds = date.getTime() + hours * 3600 * 1000;
		return new Date(milseconds);
	}

	public static Date afterHours(long hours) {
		return new Date(Calendar.getInstance().getTimeInMillis() + hours * 3600
				* 1000);
	}

	public static Date afterMinutes(Date date, int minutes) {
		Validate.notNull(date, "Parameter date cann't be null!");
		long milseconds = date.getTime() + (long)minutes * 60 * 1000;
		return new Date(milseconds);
	}

	public static Date afterMinutes(long minutes) {
		return new Date(Calendar.getInstance().getTimeInMillis() + minutes * 60
				* 1000);
	}

	public static Date afterSeconds(Date date, long seconds) {
		Validate.notNull(date, "Parameter date cann't be null!");
		long milseconds = date.getTime() + seconds * 1000;
		return new Date(milseconds);
	}

	public static Date afterSeconds(long seconds) {
		return new Date(Calendar.getInstance().getTimeInMillis() + seconds
				* 1000);
	}

	public static Date afterMillis(Date date, long millis) {
		Validate.notNull(date, "Parameter date cann't be null!");
		long milseconds = date.getTime() + millis;
		return new Date(milseconds);
	}

	public static Date afterMillis(long millis) {
		return new Date(Calendar.getInstance().getTimeInMillis() + millis);
	}

	public static int daysBetween(String startDate, String endDate,
			String format) {
		Validate.notNull(startDate, "Parameter startDate cann't be null!");
		Validate.notNull(endDate, "Parameter endDate cann't be null!");
		if (format == null)
			format = Constants.DATE_FORMAT;
		Date start = DateHelper.string2Date(startDate, format);
		Date end = DateHelper.string2Date(endDate, format);
		return (int) ((end.getTime() - start.getTime()) / (24 * 3600 * 1000));
	}

	public static int daysBetween(Date startDate, Date endDate) {
		return daysBetween(date2String(startDate, Constants.DATE_FORMAT),
				date2String(endDate, Constants.DATE_FORMAT),
				Constants.DATE_FORMAT);

	}

	public static boolean isSameDay(Date date1, Date date2) {
		if (date1 == null || date2 == null)
			return false;
		Calendar calendar1 = Calendar.getInstance();
		calendar1.setTime(date1);
		Calendar calendar2 = Calendar.getInstance();
		calendar2.setTime(date2);

		return calendar1.get(Calendar.DAY_OF_MONTH) == calendar2
				.get(Calendar.DAY_OF_MONTH)
				&& calendar1.get(Calendar.MONTH) == calendar2
						.get(Calendar.MONTH)
				&& calendar1.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR);
	}

	public static boolean isSameMonth(Date date1, Date date2) {
		if (date1 == null || date2 == null)
			return false;
		Calendar calendar1 = Calendar.getInstance();
		calendar1.setTime(date1);
		Calendar calendar2 = Calendar.getInstance();
		calendar2.setTime(date2);

		return calendar1.get(Calendar.MONTH) == calendar2.get(Calendar.MONTH)
				&& calendar1.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR);
	}

	public static boolean isSameYear(Date date1, Date date2) {
		if (date1 == null || date2 == null)
			return false;

		Calendar calendar1 = Calendar.getInstance();
		calendar1.setTime(date1);
		Calendar calendar2 = Calendar.getInstance();
		calendar2.setTime(date2);

		return calendar1.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR);
	}
	
	public static String nextDate(String date, String format){
		if(format == null) format = Constants.DATE_FORMAT;
		Date temp = DateHelper.string2Date(date, format);
		Date future = DateHelper.afterHours(temp, 24);
		return DateHelper.date2String(future, format);
	}

	public static void main(String[] argc) {
		System.out.println("Days between 19980701 and 19980703:"
				+ daysBetween("19980701", "19980703", null));
		System.out.println("Days between 19980701 and 19980701:"
				+ daysBetween("19980701", "19980701", null));
		System.out.println("Days between 19980701 and 19980702:"
				+ daysBetween("19980701", "19980702", null));
		System.out.println("Next date:" + nextDate("19980701", Constants.DATE_FORMAT));
	}
}