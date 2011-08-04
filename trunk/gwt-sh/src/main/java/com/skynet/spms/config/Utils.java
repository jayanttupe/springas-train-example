package com.skynet.spms.config;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * 一些通用的工具处理方法
 * 
 * @author tqc
 * 
 */
public class Utils {

	public static final String FULL_DATEFORMAT = "yyyy-MM-dd HH:mm:ss";

	public static final String SHORT_DATEFORMAT = "yyyy-MM-dd";

	public static final String SHORT_TIMEFORMAT = "HH:mm:ss";

	private Utils() {
	}

	/**
	 * 检查字符串是否为空或NULL
	 * 
	 * @param s
	 * @return
	 */
	public static boolean isNotBlank(String s) {
		if (null == s) {
			return false;
		}
		if (s.trim().equals("")) {
			return false;
		}
		return true;
	}

	/**
	 * 返回系统当前时间
	 * 
	 * @return String 当前时间
	 */
	public static String getNowTime() {
		return dateFormat(getNowDate(), FULL_DATEFORMAT);
	}

	/**
	 * 获取指定的时间yyyy-MM-dd HH:mm:ss格式的字符串
	 * 
	 * @param date
	 * @return
	 */
	public static String getFullDateString(Date date) {
		return dateFormat(date, FULL_DATEFORMAT);
	}

	/**
	 * 按照指定方式格式化时间
	 * 
	 * @param date
	 *            时间
	 * @param type
	 *            格式
	 * @return
	 */
	public static String dateFormat(Date date, String type) {
		SimpleDateFormat sdf = new SimpleDateFormat(type);
		return sdf.format(date);
	}

	/**
	 * 按照指定方式格式化时间
	 * 
	 * @param type
	 *            格式
	 * @return
	 */
	public static String dateFormat(String type) {
		return dateFormat(getNowDate(), type);
	}

	/**
	 * 返回系统当前时间的date对象
	 * 
	 * @return
	 */
	public static Date getNowDate() {
		return new Date();
	}

	/**
	 * 返回系统当前日期
	 * 
	 * @return String 日期
	 */
	public static String getNowShortDate() {
		return dateFormat(getNowDate(), SHORT_DATEFORMAT);
	}

	/**
	 * 返回日期
	 * 
	 * @param date
	 * @return
	 */
	public static String getShortDate(Date date) {
		return dateFormat(date, SHORT_DATEFORMAT);
	}

	/**
	 * 返回系统当前时间
	 * 
	 * @return 时间
	 */
	public static String getNowShortTime() {
		return dateFormat(getNowDate(), SHORT_TIMEFORMAT);
	}

	/**
	 * 判断时间格式
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static boolean checkDate(String date, String format) {
		SimpleDateFormat df = new SimpleDateFormat(format);
		try {
			df.parse(date);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	/**
	 * 转换字符串为date类型
	 * 
	 * @param time
	 * @return
	 * @throws ParseException
	 */
	public static Date parseDate(String time) throws ParseException {
		return new SimpleDateFormat(FULL_DATEFORMAT).parse(time);
	}

	/**
	 * 返回文件是否存在
	 * 
	 * @param filePath
	 *            文件名
	 * @return 是否存在
	 */
	public static boolean fileExists(String filePath) {
		File file = new File(filePath);
		return file.exists();
	}

	/**
	 * 创建文件夹
	 * 
	 * @param filePath
	 */
	public static void createFolder(String filePath) {
		File file = new File(filePath);
		try {
			file.mkdirs();
		} catch (RuntimeException e) {
			e.printStackTrace();
			
		}
	}

	/**
	 * 获得文件后缀名 返回的后缀名带有"."
	 * 
	 * @param fileName
	 * @return
	 */
	public static String getExtention(String fileName) {
		int pos = fileName.lastIndexOf(".");
		return fileName.substring(pos);
	}

	/**
	 * 文件采用UUID重命名
	 * 
	 * @param oldName
	 * @return
	 */
	public static String renameFile(String oldName) {
		return UUID.randomUUID().toString() + getExtention(oldName);
	}

}