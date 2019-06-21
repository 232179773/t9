package com.t9.system.util;

import java.io.StringWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;

import com.t9.system.web.T9RuningTimeException;
/**
 * 
 * 功能描述：JsonUtil工具类
 *
 * @author husq
 * @since 1.0
 * create on: 07/08/2013 
 *
 */
@SuppressWarnings({ "rawtypes", "unchecked", "unused" })
public class JsonUtil {
	

	private static ObjectMapper mapper = new ObjectMapper();
	static {
        mapper.configure(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS, false);
        DateFormat myDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        mapper.getSerializationConfig().setDateFormat(myDateFormat);
	}
	
	/**
	 * 对象转json串
	 * @param obj
	 * @return
	 */
	public static String getJsonString(Object obj){
		StringWriter writer = new StringWriter();
		if(obj != null){
			try {
				mapper.writeValue(writer, obj);
			} catch (Exception e) {
				throw new T9RuningTimeException("json error");
			}
		}
		return writer.toString();
    } 
	/**
	 * json数组文本串转集合
	 * @param json
	 * @return
	 */
	public static List getListByJsonArray(String json){
		List<LinkedHashMap<String, Object>> list=null;
		try {
			ObjectMapper mapper = new ObjectMapper();
			list = mapper.readValue(json, List.class); 
		} catch (Exception e) {
			throw new T9RuningTimeException(e.getMessage());
		}
		return list;
	}
	/**
	 * json串转对象
	 * @param json
	 * @param c
	 * @return
	 */
	public static Object getObjectByJson(String json,Class c){
		Object obj = null;
		try {
			ObjectMapper mapper = new ObjectMapper();
			obj=mapper.readValue(json, c);  
		} catch (Exception e) {
			throw new T9RuningTimeException(e.getMessage());
		}
		return obj;
	}
	

	public static Map getMapByJsonString(String jsonStr){
		HashMap m=null;
		try {
			ObjectMapper mapper = new ObjectMapper();
			m = mapper.readValue(jsonStr, HashMap.class); 
		} catch (Exception e) {
			throw new T9RuningTimeException(e.getMessage());
		}
		return m;
	}
	
}
