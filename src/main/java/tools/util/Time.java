package tools.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Time {

	public static void main(String[] args) {
		Logger.log(Time.getTimeStampAsName());
	}
	
	static long startTimeMillis = 0;

	public static void setStartTime(long startTimeMillis) {
		Time.startTimeMillis = startTimeMillis;
	}

	public static void setStartTimeForNow() {
		Time.startTimeMillis = System.currentTimeMillis();
	}

	public static long getTimeLengthForNow() {
		return System.currentTimeMillis() - startTimeMillis;
	}

	public static String getTimeStamp() {
		Date date = new Date();
		return (new Timestamp(date.getTime())).toString();
//		Date date = new Date();
//		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy h:mm:ss a");
//		String formattedDate = sdf.format(date);
	}
	
	public static String getTimeStampAsName() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_h-mm-ss");
		return sdf.format(date);
	}
}
