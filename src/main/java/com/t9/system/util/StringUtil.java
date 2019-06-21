package com.t9.system.util;

import java.util.ArrayList;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
/**
 * 
 * 功能描述：StringUtil工具类
 *
 * @author husq
 * @since 1.0
 * create on: 07/02/2013 
 *
 */
public class StringUtil {
	
	/**
	 * 判断字符串是否为空
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str){
		return StringUtils.isEmpty(str);
	}
	/**
	 * 判断字符串是否不为空
	 * @param str
	 * @return
	 */
	public static boolean isNotEmpty(String str){
		return StringUtils.isNotEmpty(str);
	}
	
	/**
	 * 判断对象是否不为空
	 * @param str
	 * @return
	 */
	public static boolean checkObj(Object obj){
		return obj!=null;
	}
	
	/**
	 * 将字符串数组转换为'',''形式
	 * @param str
	 * @return
	 */
	public static String getInStr(String[] strs){
		StringBuilder builder=new StringBuilder();
		for (int i = 0; i < strs.length; i++) {
			builder.append("'").append(strs[i]).append("',");
		}
		if(builder.length()>0)
			builder.deleteCharAt(builder.length()-1);
		return builder.toString();
	}
	
	public static String substringBefore(String str,String separator){
		return StringUtils.substringBefore(str, separator);
	}
	
	public static String substringAfter(String str,String separator){
		return StringUtils.substringAfter(str, separator);
	}

	public static String capitalize(String str){
		return StringUtils.capitalize(str);
	}
	

	public static boolean equalsIgnoreCase(String str1,String str2){
		return StringUtils.equalsIgnoreCase(str1,str2);
	}
	
	static Pattern pattern=Pattern.compile("\\{([a-zA-Z_]+)\\}");
	/**
	 * 参数替换 如"DICT_CODE={DICT_CODE} and DICT_VALUE={DICT_VALUE}"转换为new String[]{"DICT_CODE=? and DICT_VALUE=?","sex","male"}
	 * @param str
	 * @param map
	 * @return
	 */
	public static String[] getParamString(String str,Map<String,String> map){
		ArrayList<String> list=new ArrayList<String>();
		list.add(str.replaceAll("\\{[a-zA-Z_]+\\}", "?"));
		Matcher matcher=pattern.matcher(str);
		while(matcher.find()){
		//	int i=matcher.groupCount();
			String key=matcher.group(1);
			String value=map.get(key);
			list.add(value);
		}
		String[] result={};
		return list.toArray(result);
	}
	/**
	 * 参数替换 如"DICT_CODE={DICT_CODE} and DICT_VALUE={DICT_VALUE}"转换为"DICT_CODE=sex and DICT_VALUE=male"
	 * @param str
	 * @param map
	 * @return
	 */
	public static String getRightString(String str,Map<String,String> map){
		String result=str;
		Matcher matcher=pattern.matcher(str);
		while(matcher.find()){
			String key=matcher.group(1);
			String value=map.get(key);
			result=result.replaceFirst("\\{[a-zA-Z_]+\\}", value);
		}
		return result;
	}
	/**
	 * 驼峰写法转换成大写形式
	 * @param str
	 * @return
	 */
	public static String getStringByHump(String str){
		StringBuilder stringBuilder=new StringBuilder();
		char[] chars=str.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			if(chars[i]>='A'&&chars[i]<='Z'){
				stringBuilder.append("_");
			}
			stringBuilder.append(chars[i]);
		}
		return stringBuilder.toString().toUpperCase();
	}
}
