package com.duncan.blog.utils;

import java.util.Date;

public class DateKit {

	public static Integer getCurrentUnixTime() {
		return getUnixTimeByDate(new Date());
	}
	
	public static int getUnixTimeByDate(Date date) {
		return (int) (date.getTime() / 1000L);
	}
}
