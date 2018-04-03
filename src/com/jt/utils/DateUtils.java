package com.jt.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;



public class DateUtils {
	
	/**
	 * �ַ���ת��Ϊ���ݿ����͵�timestamp����
	 * @param str
	 * @return
	 */
	public Timestamp string2time(String str){
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		//����DateFormat�������ڹ涨���ڸ�ʽ
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd",Locale.CHINA);
		//ȡ�����ڼ��
		dateFormat.setLenient(false);
		Date date;
		try {
			//parse()���ַ������ո�ʽ�涨ת����Date����
			date = dateFormat.parse(str);
			timestamp = new Timestamp(date.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return timestamp;
	}
	
	/**
	 * timestampת�����ַ���
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
	 * �ַ���ת��Ϊsql����Date����
	 * @param str
	 * @return
	 */
	public java.sql.Date string2sqldate(String str) {
		java.sql.Date sqlDate = new java.sql.Date(System.currentTimeMillis());
		//����DateFormat�������ڹ涨���ڸ�ʽ
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd",Locale.CHINA);
		//ȡ�����ڼ��
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
	 * sql����Dateת����String
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
	 * Stringת����Util����Date����
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
	 * Util����Date����ת����String
	 * @param date
	 * @return
	 */
	public String date2string(Date date) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
		String dateStr = dateFormat.format(date);
		return dateStr;
	}
	
	
}
