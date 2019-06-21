package com.t9.system.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.t9.system.web.T9RuningTimeException;
/**
 * DateUtil工具类
 * @author HUSQ
 *
 */
public class DateUtil {
	/**
	 * 获取两个date之间的差值
	 * @param sDate
	 * @param eDate
	 * @return
	 */
	public static String getTimeInMillis(Date sDate, Date eDate){
		Calendar calendar = Calendar.getInstance(); 
		calendar.setTime(sDate); 
		long timethis = calendar.getTimeInMillis();  
		
		calendar.setTime(eDate); 
		long timeend = calendar.getTimeInMillis(); 
		long thedaymillis = timeend-timethis; 
		return thedaymillis < 1000 ? thedaymillis + " min second!" : (thedaymillis/1000) + " second!";
	}

	public static Date addDay(Date sDate, int day){
		Calendar calendar = Calendar.getInstance(); 
		calendar.setTime(sDate); 
		
		calendar.add(Calendar.DATE, day);
		return calendar.getTime();
	}

	public static Date addMonth(Date sDate, int month){
		Calendar calendar = Calendar.getInstance(); 
		calendar.setTime(sDate); 
		
		calendar.add(Calendar.MONTH, month);
		return calendar.getTime();
	}
	
	private static SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static SimpleDateFormat dateShortFormat=new SimpleDateFormat("yyyy-MM-dd");
	
	public static String dateToString(Date date){
		if(date==null)
			return "";
		return dateFormat.format(date);
	}

	public static String dateToShortString(Date date){
		return dateShortFormat.format(date);
	}
	
	public static Date parasDate(String datestr){
		if(datestr==null)
			return null;		
		try {
			return dateFormat.parse(datestr);
		} catch (ParseException e) {
	//		e.printStackTrace();
			throw new T9RuningTimeException("datestr:"+datestr +"is error");
		}
	}
	

	public static Date parasDate(String datestr,String format){
		if(datestr==null)
			return null;		
		try {
			return new SimpleDateFormat(format).parse(datestr);
		} catch (ParseException e) {
	//		e.printStackTrace();
			throw new T9RuningTimeException("datestr:"+datestr +"is error");
		}
	}
	
}
