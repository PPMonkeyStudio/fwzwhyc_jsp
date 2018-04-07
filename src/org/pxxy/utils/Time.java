package org.pxxy.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Time {
	// 获得精确到秒的时间 string类型
	public static String getDateSecond() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date secondDate = new Date();
		String date = formatter.format(secondDate);
		try {
			secondDate = formatter.parse(date);

			return date;
		} catch (Exception e) {
			e.printStackTrace();
			return "0000-00-00";
		}

	}
}
