package com.jt.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;



public class DateUtils {
	
	/**
	 * 字符串转换为数据库类型的timestamp数据
	 * @param str
	 * @return
	 */
	public Timestamp string2time(String str){
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		//创建DateFormat对象用于规定日期格式
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd",Locale.CHINA);
		//取消日期检查
		dateFormat.setLenient(false);
		Date date;
		try {
			//parse()将字符串按照格式规定转换成Date对象
			date = dateFormat.parse(str);
			timestamp = new Timestamp(date.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return timestamp;
	}
	
	/**
	 * timestamp转换成字符串
	 * @param timestamp
	 * @return
	 */
	public String time2string (Timestamp timestamp) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
		dateFormat.setLenient(false);
		String timeStr = dateFormat.format(timestamp);
		return timeStr;
	}
	
	/**
	 * 字符串转换为sql包的Date数据
	 * @param str
	 * @return
	 */
	public java.sql.Date string2sqldate(String str) {
		java.sql.Date sqlDate = new java.sql.Date(System.currentTimeMillis());
		//创建DateFormat对象用于规定日期格式
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd",Locale.CHINA);
		//取消日期检查
		dateFormat.setLenient(false);
		Date date;
		try {
			date = dateFormat.parse(str);
			sqlDate = new java.sql.Date(date.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return sqlDate;
	}
	
	/**
	 * sql包的Date转换成String
	 * @param date
	 * @return
	 */
	public String sqldate2string (java.sql.Date date) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
		dateFormat.setLenient(false);
		String dateStr = dateFormat.format(date);
		return dateStr;
	}
	
	/**
	 * String转换成Util包的Date类型
	 * @param str
	 * @return
	 */
	public Date string2date (String str) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
		Date date = new Date();
		try {
			date = dateFormat.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	/**
	 * Util包的Date类型转换成String
	 * @param date
	 * @return
	 */
	public String date2string(Date date) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
		String dateStr = dateFormat.format(date);
		return dateStr;
	}
	
	
}
