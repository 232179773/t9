package com.t9.system.common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.t9.system.web.MySQLUtils;
import com.t9.system.web.T9RuningTimeException;
/**
 * 
 * 功能描述：DataBaseUtil工具类
 *
 * @author husq
 * @since 1.0
 * create on: 07/02/2013 
 *
 */
@SuppressWarnings("rawtypes")
public class DataBaseUtil {
	/**
	 * 在connection连接中执行SQL并返回List<Map>形式结果
	 * @param connection
	 * @param sql
	 * @return List<Map>
	 */
	public static List<Map> querySql(Connection connection,SQLQuery sqlQuery) {
		List<Map> tempList = new ArrayList<Map>();
		try{
			PreparedStatement statement = connection.prepareStatement(sqlQuery.getSql());
			Object[] obj=sqlQuery.getParams();
			for (int i = 0; i < obj.length; i++) {
				statement.setObject(i+1, obj[i]);
			}
			ResultSet rs = statement.executeQuery();
			ResultSetMetaData lineInfo = rs.getMetaData();
			int columnCount = lineInfo.getColumnCount();
			while (rs.next()) {
				Map<String, String> map = new LinkedHashMap<String, String>();
				for (int i = 1; i <= columnCount; i++) {
					String columeName = lineInfo.getColumnLabel(i);
					String columeType = lineInfo.getColumnTypeName(i);
					String columeValue =rs.getString(i);
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
		}catch(Exception e){
			throw new T9RuningTimeException("database.error",e);
		}finally{
//			try {
//				connection.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
		}
		return tempList;
	}
	
	/**
	 * 在connection连接中执行SQL并返回List<Map>形式结果
	 * @param connection
	 * @param sql
	 * @return List<Map>
	 */
	public static int execSql(Connection connection,SQLQuery sqlQuery) {
		try{
			PreparedStatement statement = connection.prepareStatement(sqlQuery.getSql());
			Object[] obj=sqlQuery.getParams();
			for (int i = 0; i < obj.length; i++) {
				statement.setObject(i+1, obj[i]);
			}
			return  statement.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
			throw new T9RuningTimeException("database.error",e);
		}finally{
//			try {
//				connection.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
		}
	}
	
}
