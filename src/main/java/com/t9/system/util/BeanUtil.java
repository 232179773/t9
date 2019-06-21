package com.t9.system.util;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Pattern;

import com.t9.system.entity.BaseDomain;
import com.t9.system.web.MySQLUtils;

@SuppressWarnings("unchecked")
public class BeanUtil {

	public static List<Map> getMapFromResultSet(ResultSet resultSet){
		List<Map> tempList = new ArrayList<Map>();
		try {
			ResultSetMetaData lineInfo = resultSet.getMetaData();
			int columnCount = lineInfo.getColumnCount();
			while (resultSet.next()) {
				Map<String, String> map = new LinkedHashMap<String, String>();
				for (int i = 1; i <= columnCount; i++) {
					String columeName = lineInfo.getColumnLabel(i);
					String columeType = lineInfo.getColumnTypeName(i);
					String columeValue =resultSet.getString(i);
					if( "datetime".equalsIgnoreCase(columeType)){
						columeValue=MySQLUtils.mysqlDateToString(columeValue);
					}else if( "date".equalsIgnoreCase(columeType)){
				//		System.out.println(columeValue);
				//		columeValue=MySQLUtils.mysqlDateToString(columeValue);
					}
					map.put(columeName, columeValue);
				}
				tempList.add(map);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tempList;
	}
	
	
	public static void copyMapToBean(Map map,BaseDomain domain){
		Iterator iter=map.keySet().iterator();
		while(iter.hasNext()){
			String key=(String)iter.next();
			String value=(String)map.get(key);
			setBeanValue(domain,key,value);
		}
	}

	static SimpleDateFormat sdf_US = new SimpleDateFormat("MMM dd, yyyy KK:mm:ss a", Locale.US);//MMM dd hh:mm:ss Z yyyy
	static SimpleDateFormat sdf_Ren = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
	static Pattern pattern_US=Pattern.compile("\\w+");
	static Pattern patternRen=Pattern.compile("\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}");
	public static void setBeanValue(BaseDomain domain,String fieldName,String value){
		Class domainClass=domain.getClass();
		Field field=null;
		try {
			field=domainClass.getDeclaredField(fieldName);
		} catch (NoSuchFieldException e) {
		}
		if(field==null){
			try {
				fieldName=StringUtil.getStringByHump(fieldName);
				field=domainClass.getDeclaredField(fieldName);
			} catch (NoSuchFieldException e) {
			}
		}
		if(field==null){
			System.out.println("-----------"+fieldName+"\t\t:"+value);
			return;
		}
    	field.setAccessible(true);
        Class type = field.getType();
        Object objValue=value;
        if(type.getSimpleName().equals("Long")){
        	objValue=new Long(value);
        }else if(type.getSimpleName().equals("Double")){
        	objValue=new Double(value);
        }else if(type.getSimpleName().equals("Date")){
            try {
            	if(patternRen.matcher(value).find()){
                	objValue=sdf_Ren.parse(value);
            	}else{// if(pattern_US.matcher(value).find()){
                	objValue=sdf_US.parse(value);
            	}
            } catch (Exception ex) {
            	ex.printStackTrace();
            }
        }
    	try {
			field.set(domain,objValue) ;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
