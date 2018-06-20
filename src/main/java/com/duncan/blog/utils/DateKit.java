package com.duncan.blog.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateKit {

	public static Integer getCurrentUnixTime() {
		return getUnixTimeByDate(new Date());
	}
	
	public static int getUnixTimeByDate(Date date) {
		return (int) (date.getTime() / 1000L);
	}

	public static String formatDateByUnixTime(long unixTime, String pattern) {
		return dateFormat(new Date(unixTime * 1000L), pattern);
	}
	
	public static String dateFormat(Date date, String pattern) {
		if (date != null) {
			SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
			return dateFormat.format(date);
		}
		return "";
	}
}
