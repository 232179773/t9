package com.t9.system.web;

import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Http Servlet
 * 
 * @author husq
 */
@SuppressWarnings("rawtypes")
public class ServletUtils {

	public static final String TEXT_TYPE = "text/plain";
	public static final String JSON_TYPE = "application/json";
	public static final String XML_TYPE = "text/xml";
	public static final String HTML_TYPE = "text/html";
	public static final String JS_TYPE = "text/javascript";
		
	public  static final String RANDOMCODEKEY = "RANDOMVALIDATECODEKEY";
	
	public  static final String SEESION_USERINFO = "userinfo";
	public  static final String SEESION_USERROLE = "userrole";

	public static void setExpiresHeader(HttpServletResponse response, long expiresSeconds) {
		//Http 1.0 header
		response.setDateHeader("Expires", System.currentTimeMillis() + expiresSeconds * 1000);
		//Http 1.1 header
		response.setHeader("Cache-Control", "private, max-age=" + expiresSeconds);
	}

	public static void setDisableCacheHeader(HttpServletResponse response) {
		//Http 1.0 header
		response.setDateHeader("Expires", 1L);
		response.addHeader("Pragma", "no-cache");
		//Http 1.1 header
		response.setHeader("Cache-Control", "no-cache, no-store, max-age=0");
	}

	public static void setLastModifiedHeader(HttpServletResponse response, long lastModifiedDate) {
		response.setDateHeader("Last-Modified", lastModifiedDate);
	}

	public static boolean checkIfModifiedSince(HttpServletRequest request, HttpServletResponse response,
			long lastModified) {
		long ifModifiedSince = request.getDateHeader("If-Modified-Since");
		if ((ifModifiedSince != -1) && (lastModified < ifModifiedSince + 1000)) {
			response.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
			return false;
		}
		return true;
	}

	public static boolean checkIfNoneMatchEtag(HttpServletRequest request, HttpServletResponse response, String etag) {
		String headerValue = request.getHeader("If-None-Match");
		if (headerValue != null) {
			boolean conditionSatisfied = false;
			if (!"*".equals(headerValue)) {
				StringTokenizer commaTokenizer = new StringTokenizer(headerValue, ",");

				while (!conditionSatisfied && commaTokenizer.hasMoreTokens()) {
					String currentToken = commaTokenizer.nextToken();
					if (currentToken.trim().equals(etag)) {
						conditionSatisfied = true;
					}
				}
			} else {
				conditionSatisfied = true;
			}

			if (conditionSatisfied) {
				response.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
				response.setHeader("ETag", etag);
				return false;
			}
		}
		return true;
	}

	public static void setFileDownloadHeader(HttpServletResponse response, String fileName) {
		try {
			String encodedfileName = new String(fileName.getBytes(), "ISO8859-1");
			response.setHeader("Content-Disposition", "attachment; filename=\"" + encodedfileName + "\"");
		} catch (UnsupportedEncodingException e) {
		}
	}
	

	public static HashMap<String, String> getMapByRequest(HttpServletRequest request) {
		HashMap<String, String> map = new HashMap<String, String>();
		Enumeration enu = request.getParameterNames();
		while(enu.hasMoreElements()) {   
            String paraName = (String)enu.nextElement();   
            String paraValue = request.getParameter(paraName); 
            if(paraValue!=null){
            	map.put(paraName, paraValue);
            }           
		}
				
		return map;
	}

	public static String getParamsByRequest(HttpServletRequest request) {
		StringBuilder stringBuilder=new StringBuilder();
		Enumeration enu = request.getParameterNames();
		while(enu.hasMoreElements()) {   
            String paraName = (String)enu.nextElement();   
            String paraValue = request.getParameter(paraName); 
            if(paraValue!=null){
            	stringBuilder.append(paraName).append("=").append(paraValue).append("&");
            }           
		}
				
		return stringBuilder.toString();
	}
	/**
	 * 
	 * @param requst 判断输入验证码是否正确
	 */
	public static void checkRandomValidateCode(HttpServletRequest request,String name){
		HttpSession session = request.getSession();
		String strCode=(String)session.getAttribute(ServletUtils.RANDOMCODEKEY);
		if(!strCode.equalsIgnoreCase(name)){
			throw new T9RuningTimeException("system.verify.imageCodeerror");
		}
	}

	// 获得请求客户端的IP
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
	
	public static boolean isAjaxRequest(HttpServletRequest request) {
		String header = request.getHeader("X-Requested-With");
		return (header != null && "XMLHttpRequest".equals(header));
	}  
}
