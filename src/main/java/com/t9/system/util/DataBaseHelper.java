package com.t9.system.util;

import org.apache.commons.lang.StringUtils;

import com.t9.system.web.T9RuningTimeException;

@SuppressWarnings("unchecked")
public class DataBaseHelper{
	String database="oracle";
	public DataBaseHelper(String database){
		this.database=database;
	}

	public static String databaseTypeToJavaType(String databaseType,String scale){
		String javaType=databaseType;
		databaseType=databaseType.toUpperCase();
		if(databaseType.equals("VARCHAR")||databaseType.equals("VARCHAR2"))
			javaType="String";
		else if(databaseType.equals("NUMERIC")||databaseType.equals("SMALLINT")
				||databaseType.equals("NUMBER")||databaseType.equals("INT")){
			if(scale==null||scale.equals(""))
				javaType="Long";
			else
				javaType="Double";
		}else if(databaseType.equals("DATE")||databaseType.equals("DATETIME"))
			javaType="Date";
		else 
			javaType="String";
		return javaType;
	}


	public static String getPathNameformTableName(String tableName) {
		tableName=tableName.toLowerCase();
		String[] keys =tableName.split("_");
		if (keys.length == 1) {
			return tableName;
		}
		StringBuffer keyBuf = new StringBuffer();
		keyBuf.append(keys[1]);
		for (int i = 2, len = keys.length; i < len; i++) {
			keyBuf.append(StringUtils.capitalize(keys[i]));
		}
		return keyBuf.toString();
	}
	
	public static String getClassNameFromTableName(String tableName) {
		tableName=tableName.toLowerCase();
		String[] keys =tableName.split("_");
		if (keys.length == 1) {
			return tableName;
		}
		StringBuffer keyBuf = new StringBuffer();
		for (int i = 1, len = keys.length; i < len; i++) {
			keyBuf.append(StringUtils.capitalize(keys[i]));
		}
		return keyBuf.toString();
	}
	
	public static String getBeanName(String tableName){
		tableName=tableName.toLowerCase();
		String[] keys =tableName.split("_");
		if (keys.length == 1) {
			return tableName;
		}
		StringBuffer keyBuf = new StringBuffer(keys[1]);
		for (int i = 2, len = keys.length; i < len; i++) {
			keyBuf.append(StringUtils.capitalize(keys[i]));
		}
		return keyBuf.toString();
	}
}
