package com.tencoding.bank.utils;


import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class TimestampUtil {

	public static String timestampToString(Timestamp timestamp) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
		return simpleDateFormat.format(timestamp);
	}
	
}
