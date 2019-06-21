package com.t9.system.util;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.apache.log4j.Logger;
/**
 * 
 * 功能描述：日志工具类
 *
 * @author husq
 * @since 1.0
 * create on: 07/02/2013 
 *
 */
public class LogUtil {

	private static Logger logger=Logger.getLogger(LogUtil.class);
	
	public static void logException(Throwable e){
		StringWriter stringWriter=new StringWriter();
		e.printStackTrace(new PrintWriter(stringWriter));
		String result=stringWriter.toString();
		logger.error(result);
	}
	/**
	 * 获取执行路径
	 * @return string
	 */
	private static String getLogPath(){
		StackTraceElement[] ste = new Throwable().getStackTrace();
		StringBuffer CallStack = new StringBuffer();
		for (int i = 2; i < ste.length; i++) {
			String clsString=ste[i].toString();
			if(clsString.indexOf("com.t9")>-1)
				CallStack.append(ste[i].toString() + " | ");
			if(clsString.indexOf("com.t9.system.action")==0)
				break;
		}
		return CallStack.toString();
	}
	/**
	 * 路径信息打印
	 */
	public static void logInfoPath(){
		if(logger.isInfoEnabled()){
			logger.info("exec path:"+getLogPath());
		}
	}
}
