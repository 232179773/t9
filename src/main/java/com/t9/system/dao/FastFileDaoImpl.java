package com.t9.system.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.t9.system.common.SQLQuery;
import com.t9.system.web.T9Result;

@Repository
@SuppressWarnings({"unchecked", "rawtypes"})
public class FastFileDaoImpl extends BaseDaoImpl{
	
	/**
	 * 列表查询
	 */
	public List<Map> queryTable(Map<String, String> map,boolean pageInfo) {
		SQLQuery sqlQuery=new SQLQuery("SELECT * FROM TB_APP_FORM WHERE 1=1");
		sqlQuery.addLikeParam("APP_NAME", map.get("APP_NAME"));
		sqlQuery.addLikeParam("TABLE_CODE", map.get("TABLE_CODE"));
		sqlQuery.addEquealParam(map,"ID");
		List<Map> list=null;
		if(pageInfo){
			list= this.queryPageSql(sqlQuery);
		}else{
			list= this.querySql(sqlQuery);
		}
		return list;
	}
	
	
	/**
	 * 删除记录
	 */
	public int deleteTable(String tableCode) {
		SQLQuery sqlQuery=new SQLQuery("delete FROM TB_APP_FORM WHERE 1=1");
		sqlQuery.addEquealParam("TABLE_CODE", tableCode,true);
		int result=this.execSql(sqlQuery);

		sqlQuery=new SQLQuery("delete FROM TB_APP_COLUMN WHERE 1=1 and TABLE_CODE=?",tableCode);
		this.execSql(sqlQuery);
		
		return result;
	}
	
	
	/**
	 * 列列表查询
	 */
	public List<Map> queryTableColumnList(Map<String, String> map,boolean pageInfo) {
		SQLQuery sqlQuery=new SQLQuery("SELECT * FROM TB_APP_COLUMN WHERE 1=1 ");
		sqlQuery.addEquealParam("TABLE_CODE", map.get("TABLE_CODE"));
		sqlQuery.addEquealParam("COL_ID", map.get("COL_ID"));
		sqlQuery.addEquealParam("ID", map.get("ID"));
		sqlQuery.addSQL("order by COL_ORDER");
		List<Map> list=null;
		if(pageInfo){
			list= this.queryPageSql(sqlQuery);
		}else{
			list= this.querySql(sqlQuery);
		}
		return list;
	}
	
	/**
	 * 更新列顺序
	 */
	public T9Result saveColumnOrder(Map<String, String> map) {
		T9Result result=new T9Result();
		String[] colIDs=map.get("COL_ID").split(",");
		String[] sqls=new String[colIDs.length];
		for (int i = 0; i < colIDs.length; i++) {
			SQLQuery sqlQuery=new SQLQuery("update tb_app_column set COL_ORDER ="+i+" where COL_ID="+colIDs[i]);
			this.execSql(sqlQuery);
		}
		return result;
	}
	
	public HashMap getAllTableMap(){
		SQLQuery sqlQuery=new SQLQuery("SELECT TABLE_CODE FROM TB_APP_FORM WHERE 1=1");
		List<Map> list=this.querySql(sqlQuery);
		HashMap tablesMap=new HashMap();
		for (int i = 0; i <list.size(); i++) {
			String tableName=(String)list.get(i).get("TABLE_CODE");
			ArrayList tableList=generateTableList(tableName);
			HashMap tableMap=new HashMap();
			if(tableList.size()==1){
				tableMap=(HashMap)tableList.get(0);
				tablesMap.put(tableName, tableMap);
			}
		}
		return tablesMap;
	}
	

	public HashMap getTableMap(String tableName){
		ArrayList tableList=generateTableList(tableName);
		HashMap tableMap=new HashMap();
		if(tableList.size()==1){
			tableMap=(HashMap)tableList.get(0);
		//	resetColumnList(tableMap);
			return tableMap;
		}
		return null;
	}
		
	public ArrayList generateTableList(String tableNames){
		ArrayList tableList=new ArrayList();
		try {
			if(tableNames.length()>0){
				String[] tables=tableNames.split(",");
				for (int i = 0; i < tables.length; i++) {
					HashMap map=new HashMap();
					map.put("tableName", tables[i]);
					tableList.add(map);
				}
			}

			for (Object object : tableList) {
				HashMap tableMap=(HashMap)object;
				HashMap keyMap=new HashMap();
				HashMap beMap=new HashMap();
				tableMap.put("keyMap", keyMap);
				String tableName=(String)tableMap.get("tableName");
				String tableDataKeyColumn="";
				String tableDataNameColumn="";
				
				SQLQuery sqlQuery=new SQLQuery("select * from TB_APP_COLUMN where TABLE_CODE ='"+tableName+"' order by COL_ORDER");

				List tableColumnList=querySql(sqlQuery);
				tableMap.put("columnList", tableColumnList);
				
				for (int i = 0; i < tableColumnList.size(); i++) {
					HashMap columnHashMap=(HashMap)tableColumnList.get(i);	
					String COL_USE=(String)columnHashMap.get("COL_USE");
					if("2".equals(COL_USE)){
						tableDataNameColumn=(String)columnHashMap.get("COL_CODE");
					}
					if("1".equals(COL_USE)){
						tableDataKeyColumn=(String)columnHashMap.get("COL_CODE");
					}
				}
				sqlQuery=new SQLQuery("select * from TB_APP_FORM where TABLE_CODE='"+tableName+"'");
				List tableLists=querySql(sqlQuery);
				tableMap.putAll((Map)tableLists.get(0));
				
				tableMap.put("tableDataKeyColumn", tableDataKeyColumn);
				tableMap.put("tableDataNameColumn", tableDataNameColumn);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		return tableList;
	}
	
	public static void initMap(HashMap map){
		Iterator iter=map.keySet().iterator();
		while(iter.hasNext()){
			String key=(String)iter.next();
			Object value=map.get(key);
			if(value==null)
			map.put(key, "");
		}
	}
	
	public static HashMap getAllMap(){
		HashMap map=new HashMap();
		HashMap pathMap=new HashMap();
		HashMap nameMap=new HashMap();
		map.put("pathMap", pathMap);
		map.put("nameMap", nameMap);
		try{
			String sql="select * from TB_APP_FORM";
			Class.forName("com.mysql.jdbc.Driver");
//			Connection conn = DriverManager.getConnection(tns, userName,password);		
//			Statement stmt = conn.createStatement();
//			ResultSet rs = stmt.executeQuery(sql);
//			while (rs.next()) {
//				HashMap tableMap=new HashMap();
//				String tableName= (String)rs.getObject("APP_NAME");
//				String tableCode= (String)rs.getObject("TABLE_CODE");
//				String appPath= (String)rs.getObject("APP_PATH");
//				tableMap.put("tableCode", tableCode);
//				tableMap.put("tableName",tableName);
//				tableMap.put("package",(String)rs.getObject("PACKAGE"));
//				tableMap.put("appPath", appPath);
//				tableMap.put("classPath",(String)rs.getObject("CLASS_PATH"));
//				tableMap.put("uniqueCol",(String)rs.getObject("UNIQUE_COL"));
//				tableMap.put("toolbar",(String)rs.getObject("TOOLBAR"));
//				pathMap.put(appPath, tableCode);
//				nameMap.put(tableName, tableCode);
//			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return map;
	}
}
