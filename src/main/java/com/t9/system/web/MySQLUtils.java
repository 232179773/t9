package com.t9.system.web;


/**
 * 
 * 功能描述：MySQLUtils工具类
 *
 * @author husq
 * @since 1.0
 * create on: 07/02/2013 
 *
 */
public class MySQLUtils {
	
	public static String mysqlDateToString(String mysqlDate){
		if(mysqlDate==null)
			return "";
		String result=mysqlDate.substring(0, 19);
		return result;
	}
}
