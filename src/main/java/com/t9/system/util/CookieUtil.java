package com.t9.system.util;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.crypto.codec.Base64;

/**
 * 对cookie的操作
 * 
 * @author t9
 * @Description:TODO
 * @date:2013-7-25
 * @version:
 */
public class CookieUtil {

	/**
	 * 添加cookie
	 * 
	 * @param response
	 * @param name
	 * @param value
	 * @param maxAge
	 * @throws UnsupportedEncodingException
	 */
	public static void addCookie(HttpServletResponse response, String name,
			String value, int maxAge) throws UnsupportedEncodingException {
		String valueBase64 = new String(Base64.encode(value.getBytes("UTF-8")));
		System.out.println(valueBase64);
	
		Cookie cookie = new Cookie(name, valueBase64);
		cookie.setPath("/");
		if (maxAge > 0) {
			cookie.setMaxAge(maxAge);
		}
		response.addCookie(cookie);
	}

	public static Map<String, Cookie> readCookieMap(HttpServletRequest request,
			int maxAge, String mateName) {

		Map<String, Cookie> map = new HashMap<String, Cookie>();
		Cookie[] cookies = request.getCookies();
		if (null != cookies) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().startsWith(mateName)) {
					cookie.setMaxAge(maxAge);
					map.put(cookie.getName(), cookie);
				}

			}
		}

		return map;

	}

	/**
	 * 读取cookie
	 * 
	 * @param request
	 * @param name
	 * @return
	 */
	public static Cookie readCookie(HttpServletRequest request, String name,
			int maxAge, String mateName) {
		Map<String, Cookie> cookieMap = readCookieMap(request, maxAge, mateName);
		if (cookieMap.containsKey(name)) {
			Cookie cookie = (Cookie) cookieMap.get(name);
			return cookie;
		} else {
			return null;
		}
	}

	/**
	 * 删除cookie
	 * 
	 * @param request
	 * @param response
	 * @param mateName
	 *            cookie 名字以什么开头“A”，“B”
	 * @throws UnsupportedEncodingException 
	 * @throws NumberFormatException 
	 */
	public static void deleteCookie(HttpServletRequest request,
			HttpServletResponse response, String mateName) throws NumberFormatException, UnsupportedEncodingException {
		Cookie[] cookies = request.getCookies();
		StringBuffer valueCookie = new StringBuffer();
		StringBuffer nameCookie = new StringBuffer();

		for (int i = 0; i < cookies.length; i++) {
			String name = cookies[i].getName();
			if (name.startsWith(mateName)) {
				String[] values =new String(Base64.decode(cookies[i].getValue().getBytes()),"UTF-8").split(":");
			//	String[] values = cookies[i].getValue().split(":");
				valueCookie.append(values[0]).append(",");
				nameCookie.append(cookies[i].getName()).append(",");
			}
		}
		String[] valueArray = valueCookie.toString().split(",");
		String[] nameArray = nameCookie.toString().split(",");
		int n=0; 
		//new String(Base64.decode(valueArray[0].getBytes()),"UTF-8");
		Long min=Long.parseLong(valueArray[0]);
		for( int i=1; i<valueArray.length; i++){
		  if(Long.parseLong(valueArray[i])<min){
			n=i;
			min=Long.parseLong(valueArray[i]);
		  }
		}
		Cookie cookie = new Cookie(nameArray[n], null);
		cookie.setMaxAge(0);// cookie立即失效
		cookie.setPath("/");
		response.addCookie(cookie);
	}

	/**
	 * map1 map2 的要相同
	 * @param map1
	 * @param map2
	 * @return
	 */
	public static Map<String, String> getMap(Map<String, String> map1,Map<String, String> map2) {
		LinkedHashMap<String, String> mapEnd = new LinkedHashMap<String, String>();
		//1374823059181
		List<Map.Entry<String, String>> mappingList = new ArrayList<Map.Entry<String, String>>(
				map1.entrySet());
		// 通过比较器实现比较排序
		Collections.sort(mappingList,
				new Comparator<Map.Entry<String, String>>() {
					public int compare(Map.Entry<String, String> mapping1,
							Map.Entry<String, String> mapping2) {
						if (Long.parseLong(mapping1.getValue()) < Long.parseLong(mapping2.getValue())) {
							return 1;
						} else {
							return -1;
						}
					}
				});

		for (Map.Entry<String, String> mapping : mappingList) {
			System.out.println(mapping.getKey() + ":" +map2.get(mapping.getKey()));
			
			mapEnd.put(mapping.getKey(),map2.get(mapping.getKey()));
			
		}
		return mapEnd;
	}

}
