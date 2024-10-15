package com.sh.utils;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * @ClassName: DateUtils
 * @Description:
 * @Version: 1.0.0
 * @Date: 2024/10/15 14:39
 * @Author: SH
 */
public class DateUtils {
	/**
	 * date转换为LocalDateTime
	 * @param date
	 * @return
	 */
	public static LocalDateTime dateToLocalDateTime(Date date) {
		Instant instant = date.toInstant();
		ZoneId zone = ZoneId.systemDefault();
		return LocalDateTime.ofInstant(instant, zone);
	}
	
	
	/**
	 * java.util.Date --> java.time.LocalDate
	 * @param date
	 * @return
	 */
	public static LocalDate dateToLocalDate(Date date) {
		Instant instant = date.toInstant();
		ZoneId zone = ZoneId.systemDefault();
		LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
		return localDateTime.toLocalDate();
	}
	
	/**
	 * java.util.Date --> java.time.LocalTime
	 * @param date
	 * @return
	 */
	public LocalTime dateToLocalTime(Date date) {
		Instant instant = date.toInstant();
		ZoneId zone = ZoneId.systemDefault();
		LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
		return localDateTime.toLocalTime();
	}
	
	
	/**
	 * java.time.LocalDateTime --> java.util.Date
	 * @param localDateTime
	 * @return
	 */
	public static Date localDateTimeToUdate(LocalDateTime localDateTime) {
		ZoneId zone = ZoneId.systemDefault();
		Instant instant = localDateTime.atZone(zone).toInstant();
		return Date.from(instant);
	}
	
	
	/**
	 * java.time.LocalDate --> java.util.Date
	 * @param localDate
	 * @return
	 */
	public static Date localDateToUdate(LocalDate localDate) {
		ZoneId zone = ZoneId.systemDefault();
		Instant instant = localDate.atStartOfDay().atZone(zone).toInstant();
		return Date.from(instant);
	}
	
	/**
	 * java.time.LocalTime --> java.util.Date
	 */
	public static Date localTimeToUdate(LocalTime localTime) {
		LocalDate localDate = LocalDate.now();
		LocalDateTime localDateTime = LocalDateTime.of(localDate, localTime);
		ZoneId zone = ZoneId.systemDefault();
		Instant instant = localDateTime.atZone(zone).toInstant();
		return Date.from(instant);
	}
	public static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HHmmss");
	public static final DateTimeFormatter YEAR_MONTH_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM");
	public static final DateTimeFormatter SHORT_DATE_FORMATTER = DateTimeFormatter.ofPattern("yyMMdd");
	public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	public static final DateTimeFormatter SHORT_DATETIME_FORMATTER = DateTimeFormatter.ofPattern("yyMMddHHmmss");
	public static final DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	public static final DateTimeFormatter LONG_DATETIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss SSS");
	public static final ZoneId ZONE_ID = ZoneId.of("Asia/Shanghai");
	
	/**
	 * 返回当前的日期
	 */
	public static LocalDate getCurrentLocalDate() {
		return LocalDate.now(ZONE_ID);
	}
	
	/**
	 * 返回当前时间
	 */
	public static LocalTime getCurrentLocalTime() {
		return LocalTime.now(ZONE_ID);
	}
	
	/**
	 * 返回当前日期时间
	 */
	public static LocalDateTime getCurrentLocalDateTime() {
		return LocalDateTime.now(ZONE_ID);
	}
	
	/**
	 * yyyy-MM-dd
	 */
	public static String getCurrentDateStr() {
		return LocalDate.now().format(DATE_FORMATTER);
	}
	
	/**
	 * yyMMdd
	 */
	public static String getCurrentShortDateStr() {
		return LocalDate.now().format(SHORT_DATE_FORMATTER);
	}
	
	public static String getCurrentMonthStr() {
		return LocalDate.now().format(YEAR_MONTH_FORMATTER);
	}
	
	/**
	 * yyyy-MM-dd HH:mm:ss
	 */
	public static String getCurrentDateTimeStr() {
		return LocalDateTime.now().format(DATETIME_FORMATTER);
	}
	
	
	public static String getCurrentLongDateTimeStr() {
		return LocalDateTime.now().format(LONG_DATETIME_FORMATTER);
	}
	
	/**
	 * yyMMddHHmmss
	 */
	public static String getCurrentShortDateTimeStr() {
		return LocalDateTime.now().format(SHORT_DATETIME_FORMATTER);
	}
	
	/**
	 * HHmmss
	 */
	public static String getCurrentTimeStr() {
		return LocalTime.now().format(TIME_FORMATTER);
	}
	
	/**
	 * 获取当前日期字符
	 *
	 * @param pattern
	 * @return
	 */
	public static String getCurrentDateStr(String pattern) {
		return LocalDate.now().format(DateTimeFormatter.ofPattern(pattern));
	}
	
	public static String getCurrentDateTimeStr(String pattern) {
		return LocalDateTime.now().format(DateTimeFormatter.ofPattern(pattern));
	}
	
	public static String getCurrentTimeStr(String pattern) {
		return LocalTime.now().format(DateTimeFormatter.ofPattern(pattern));
	}
	
	public static LocalDate parseLocalDate(String dateStr, String pattern) {
		return LocalDate.parse(dateStr, DateTimeFormatter.ofPattern(pattern));
	}
	
	public static LocalDateTime parseLocalDateTime(String dateTimeStr, String pattern) {
		return LocalDateTime.parse(dateTimeStr, DateTimeFormatter.ofPattern(pattern));
	}
	
	public static LocalTime parseLocalTime(String timeStr, String pattern) {
		return LocalTime.parse(timeStr, DateTimeFormatter.ofPattern(pattern));
	}
	
	public static String formatLocalDate(LocalDate date, String pattern) {
		return date.format(DateTimeFormatter.ofPattern(pattern));
	}
	
	public static String formatLocalDateTime(LocalDateTime datetime, String pattern) {
		return datetime.format(DateTimeFormatter.ofPattern(pattern));
	}
	
	public static String formatLocalTime(LocalTime time, String pattern) {
		return time.format(DateTimeFormatter.ofPattern(pattern));
	}
	
	public static LocalDate parseLocalDate(String dateStr) {
		return LocalDate.parse(dateStr, DATE_FORMATTER);
	}
	
	public static LocalDateTime parseLocalDateTime(String dateTimeStr) {
		return LocalDateTime.parse(dateTimeStr, DATETIME_FORMATTER);
	}
	
	public static LocalDateTime parseLongLocalDateTime(String longDateTimeStr) {
		return LocalDateTime.parse(longDateTimeStr, LONG_DATETIME_FORMATTER);
	}
	
	public static LocalTime parseLocalTime(String timeStr) {
		return LocalTime.parse(timeStr, TIME_FORMATTER);
	}
	
	public static String formatLocalDate(LocalDate date) {
		return date.format(DATE_FORMATTER);
	}
	
	public static String formatLocalDateTime(LocalDateTime datetime) {
		return datetime.format(DATETIME_FORMATTER);
	}
	
	public static String formatLocalTime(LocalTime time) {
		return time.format(TIME_FORMATTER);
	}
	
	/**
	 * LocalDate转Date
	 */
	public static Date localDateToDate(LocalDate localDate) {
		return Date.from(localDate.atStartOfDay(ZONE_ID).toInstant());
	}
	
	/**
	 * LocalDateTime转Date
	 */
	public static Date localDateTimeToDate(LocalDateTime localDateTime) {
		return Date.from(localDateTime.atZone(ZONE_ID).toInstant());
	}
	
	
	/**
	 * LocalDate转时间戳
	 */
	public static long localDateToTimestamp(LocalDate localDate) {
		return localDate.atStartOfDay(ZONE_ID).toInstant().toEpochMilli();
	}
	
	/**
	 * LocalDateTime转时间戳
	 */
	public static long localDateTimeToTimestamp(LocalDateTime localDateTime) {
		return localDateTime.toInstant(ZoneOffset.ofHours(8)).toEpochMilli();
	}
	
	/**
	 * 时间戳转LocalDate
	 */
	public static LocalDate timestampToLocalDate(long timestamp) {
		return Instant.ofEpochMilli(timestamp).atZone(ZONE_ID).toLocalDate();
	}
	
	/**
	 * 时间戳(毫秒)转LocalDateTime
	 */
	public static LocalDateTime timestampMilliToLocalDateTime(long timestamp) {
		return Instant.ofEpochMilli(timestamp).atZone(ZONE_ID).toLocalDateTime();
	}
	
	/**
	 * 时间戳(秒)转LocalDateTime
	 */
	public static LocalDateTime timestampSecondToLocalDateTime(long timestamp) {
		return Instant.ofEpochSecond(timestamp).atZone(ZONE_ID).toLocalDateTime();
	}
	
	/**
	 * 日期相隔秒
	 */
	public static long periodHours(LocalDateTime startDateTime, LocalDateTime endDateTime) {
		return Duration.between(startDateTime, endDateTime).get(ChronoUnit.SECONDS);
	}
	
	/**
	 * 日期相隔天数
	 */
	public static long periodDays(LocalDate startDate, LocalDate endDate) {
		return startDate.until(endDate, ChronoUnit.DAYS);
	}
	
	/**
	 * 日期相隔周数
	 */
	public static long periodWeeks(LocalDate startDate, LocalDate endDate) {
		return startDate.until(endDate, ChronoUnit.WEEKS);
	}
	
	/**
	 * 日期相隔月数
	 */
	public static long periodMonths(LocalDate startDate, LocalDate endDate) {
		return startDate.until(endDate, ChronoUnit.MONTHS);
	}
	
	/**
	 * 日期相隔年数
	 */
	public static long periodYears(LocalDate startDate, LocalDate endDate) {
		return startDate.until(endDate, ChronoUnit.YEARS);
	}
	
	/**
	 * 是否当天
	 */
	public static boolean isToday(LocalDate date) {
		return getCurrentLocalDate().equals(date);
	}
	
	/**
	 * 获取当前时间辍毫秒数
	 */
	public static Long toEpochMilli(LocalDateTime dateTime) {
		return dateTime.atZone(ZONE_ID).toInstant().toEpochMilli();
	}
	
	/**
	 * 获取当前时间辍毫秒数
	 */
	public static Long getMilli() {
		return getCurrentLocalDateTime().atZone(ZONE_ID).toInstant().toEpochMilli();
	}
	
	/**
	 * 获取当前时间辍秒数
	 */
	public static Long getSecond() {
		return getCurrentLocalDateTime().atZone(ZONE_ID).toInstant().toEpochMilli() / 1000;
	}
	
	/**
	 * 获取当前时间辍秒数
	 */
	public static Long toEpochSecond(LocalDateTime dateTime) {
		return dateTime.atZone(ZONE_ID).toInstant().toEpochMilli() / 1000;
	}
	
	/**
	 * 判断是否为闰年
	 */
	public static boolean isLeapYear(LocalDate localDate) {
		return localDate.isLeapYear();
	}
	
	/**
	 * 获取当天的开始时间
	 */
	public static Date getDayBegin() {
		Calendar cal = new GregorianCalendar();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}
	
	/**
	 * 获取当天的结束时间
	 */
	public static Date getDayEnd() {
		Calendar cal = new GregorianCalendar();
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 999);
		return cal.getTime();
	}
	
	/**
	 * 获取昨天的开始时间
	 */
	public static Date getBeginDayOfYesterday() {
		Calendar cal = new GregorianCalendar();
		cal.setTime(getDayBegin());
		cal.add(Calendar.DAY_OF_MONTH, -1);
		return cal.getTime();
	}
	
	/**
	 * 获取昨天的结束时间
	 */
	public static Date getEndDayOfYesterDay() {
		Calendar cal = new GregorianCalendar();
		cal.setTime(getDayEnd());
		cal.add(Calendar.DAY_OF_MONTH, -1);
		return cal.getTime();
	}
	
	/**
	 * 获取明天的开始时间
	 */
	public static Date getBeginDayOfTomorrow() {
		Calendar cal = new GregorianCalendar();
		cal.setTime(getDayBegin());
		cal.add(Calendar.DAY_OF_MONTH, 1);
		return cal.getTime();
	}
	
	/**
	 * 获取明天的结束时间
	 */
	public static Date getEndDayOfTomorrow() {
		Calendar cal = new GregorianCalendar();
		cal.setTime(getDayEnd());
		cal.add(Calendar.DAY_OF_MONTH, 1);
		return cal.getTime();
	}
	
	
	
	
	/**
	 * 获取今年是哪一年
	 */
	public static Integer getNowYear() {
		Date date = new Date();
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		gc.setTime(date);
		return Integer.valueOf(gc.get(1));
	}
	
	/**
	 * 获取本月是哪一月
	 */
	public static int getNowMonth() {
		Date date = new Date();
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		gc.setTime(date);
		return gc.get(Calendar.MONTH) + 1;
	}
	
	/**
	 * 两个日期相减得到的天数
	 */
	public static int getDiffDays(Date beginDate, Date endDate) {
		if (beginDate == null || endDate == null) {
			throw new IllegalArgumentException("getDiffDays param is null!");
		}
		long diff = (endDate.getTime() - beginDate.getTime()) / (1000 * 60 * 60 * 24);
		return Long.valueOf(diff).intValue();
	}
	
	/**
	 * 两个日期相减得到的毫秒数
	 */
	public static long dateDiff(Date beginDate, Date endDate) {
		long date1ms = beginDate.getTime();
		long date2ms = endDate.getTime();
		return date2ms - date1ms;
	}
	
	/**
	 * 获取两个日期中的最大日期
	 */
	public static Date max(Date beginDate, Date endDate) {
		if (beginDate == null) {
			return endDate;
		}
		if (endDate == null) {
			return beginDate;
		}
		if (beginDate.after(endDate)) {
			return beginDate;
		}
		return endDate;
	}
	
	/**
	 * 获取两个日期中的最小日期
	 */
	public static Date min(Date beginDate, Date endDate) {
		if (beginDate == null) {
			return endDate;
		}
		if (endDate == null) {
			return beginDate;
		}
		if (beginDate.after(endDate)) {
			return endDate;
		}
		return beginDate;
	}
	
	/**
	 * 返回某月该季度的第一个月
	 */
	public static Date getFirstSeasonDate(Date date) {
		final int[] SEASON = {1, 1, 1, 2, 2, 2, 3, 3, 3, 4, 4, 4};
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int sean = SEASON[cal.get(Calendar.MONTH)];
		cal.set(Calendar.MONTH, sean * 3 - 3);
		return cal.getTime();
	}
	
	/**
	 * 返回某个日期下几天的日期
	 */
	public static Date getNextDay(Date date, int i) {
		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		cal.set(Calendar.DATE, cal.get(Calendar.DATE) + i);
		return cal.getTime();
	}
	
	/**
	 * 返回某个日期前几天的日期
	 */
	public static Date getFrontDay(Date date, int i) {
		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		cal.set(Calendar.DATE, cal.get(Calendar.DATE) - i);
		return cal.getTime();
	}
	
	/**
	 * 获取某年某月到某年某月按天的切片日期集合（间隔天数的日期集合）
	 */
	public static List getTimeList(int beginYear, int beginMonth, int endYear, int endMonth, int k) {
		List list = new ArrayList();
		if (beginYear == endYear) {
			for (int j = beginMonth; j <= endMonth; j++) {
				list.add(getTimeList(beginYear, j, k));
			}
		} else {
			for (int j = beginMonth; j < 12; j++) {
				list.add(getTimeList(beginYear, j, k));
			}
			for (int i = beginYear + 1; i < endYear; i++) {
				for (int j = 0; j < 12; j++) {
					list.add(getTimeList(i, j, k));
				}
			}
			for (int j = 0; j <= endMonth; j++) {
				list.add(getTimeList(endYear, j, k));
			}
		}
		return list;
	}
	
	/**
	 * 获取某年某月按天切片日期集合（某个月间隔多少天的日期集合）
	 */
	public static List getTimeList(int beginYear, int beginMonth, int k) {
		List list = new ArrayList();
		Calendar begincal = new GregorianCalendar(beginYear, beginMonth, 1);
		int max = begincal.getActualMaximum(Calendar.DATE);
		for (int i = 1; i < max; i = i + k) {
			list.add(begincal.getTime());
			begincal.add(Calendar.DATE, k);
		}
		begincal = new GregorianCalendar(beginYear, beginMonth, max);
		list.add(begincal.getTime());
		return list;
	}
	
	/**
	 * 获取某年某月的第一天日期
	 */
	public static Date getStartMonthDate(int year, int month) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month - 1, 1);
		return calendar.getTime();
	}
	
	/**
	 * 获取某年某月的最后一天日期
	 */
	public static Date getEndMonthDate(int year, int month) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month - 1, 1);
		int day = calendar.getActualMaximum(5);
		calendar.set(year, month - 1, day);
		return calendar.getTime();
	}
	
	
	
	
	/***
	 * @param localDateTime
	 * @return: boolean
	 * @since 1.0.0
	 * @Description: 根据LocalDateTime来判断是否是今天
	 */
	public static boolean judgeTimeIsToday(LocalDateTime localDateTime) {
		boolean result = false;
		//今天开始时间0时0分0秒
		LocalDateTime todayStartTime = LocalDateTime.of(LocalDate.now(),LocalTime.MIN);
		//今天结束时间23时59分59秒
		LocalDateTime todayEndTime = LocalDateTime.of(LocalDate.now(), LocalTime.MAX);
		//a.isAfter(b) a在b时间之后   a.isBefore(b) a在b时间之前
		if(localDateTime.isAfter(todayStartTime) && localDateTime.isBefore(todayEndTime )){
			result = true;
		}else if(localDateTime.equals(todayStartTime ) || localDateTime.equals(todayStartTime )){
			result = true;
		}
		return result;
	}
}
