package com.arp.system.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.arp.system.common.Expression;
import com.arp.system.common.MatchMode;
import com.arp.system.common.SQLHelper;

@Repository
@SuppressWarnings({"unchecked", "rawtypes"})
public class FastFileDaoImpl extends BaseDaoImpl{
	
	/**
	 * 列表查询
	 */
	public List<Map> queryTable(Map<String, String> map,boolean pageInfo) {
		SQLHelper sqlHelper=new SQLHelper("SELECT * FROM SYSTEM_APP_FORM WHERE 1=1");	
		sqlHelper.setAutoFilterNullValue(true);
		sqlHelper.addCondition(Expression.like("APP_NAME", map.get("APP_NAME"),MatchMode.START));
		sqlHelper.addCondition(Expression.like("TABLE_CODE", map.get("TABLE_CODE"),MatchMode.START));
		sqlHelper.addCondition(Expression.eq("ID", map.get("ID")));
		List<Map> list=null;
		if(pageInfo){
			list= sqlHelper.queryPageSql();
		}else{
			list= sqlHelper.querySql();
		}
		return list;
	}
	
	
	/**
	 * 删除记录
	 */
	public int deleteTable(String tableCode) {
		SQLHelper sqlHelper=new SQLHelper("delete FROM SYSTEM_APP_FORM WHERE 1=1");
		sqlHelper.addCondition(Expression.eq("TABLE_CODE", tableCode));
		int result=sqlHelper.execSql();

		sqlHelper=new SQLHelper("delete FROM SYSTEM_APP_COLUMN WHERE 1=1 and TABLE_CODE=?",tableCode);
		sqlHelper.execSql();
		
		return result;
	}
	
	
	/**
	 * 列列表查询
	 */
	public List<Map> queryTableColumnList(Map<String, String> map,boolean pageInfo) {
		SQLHelper sqlHelper=new SQLHelper("SELECT * FROM SYSTEM_APP_COLUMN WHERE 1=1 ");
		sqlHelper.setAutoFilterNullValue(true);
		sqlHelper.addCondition(Expression.eq("TABLE_CODE", map.get("TABLE_CODE")));
		sqlHelper.addCondition(Expression.eq("ID", map.get("ID")));
		sqlHelper.addCondition(Expression.order("COL_ORDER"));
		List<Map> list=null;
		if(pageInfo){
			list= sqlHelper.queryPageSql();
		}else{
			list= sqlHelper.querySql();
		}
		return list;
	}
	
	/**
	 * 更新列顺序
	 */
	public int saveColumnOrder(Map<String, String> map) {
		String[] colIDs=map.get("COL_ID").split(",");
		String[] sqls=new String[colIDs.length];
		int iCount=0;
		for (int i = 0; i < colIDs.length; i++) {
			SQLHelper sqlHelper=new SQLHelper("update SYSTEM_APP_COLUMN set COL_ORDER ="+i+" where COL_ID="+colIDs[i]);
			sqlHelper.execSql();
		}
		return iCount;
	}
	
	public HashMap getAllTableMap(){
		SQLHelper sqlHelper=new SQLHelper("SELECT TABLE_CODE FROM SYSTEM_APP_FORM WHERE 1=1");
		List<Map> list=sqlHelper.querySql();
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
				
				SQLHelper sqlHelper=new SQLHelper("select * from SYSTEM_APP_COLUMN where TABLE_CODE ='"+tableName+"' order by COL_ORDER");

				List tableColumnList=sqlHelper.querySql();
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
				sqlHelper=new SQLHelper("select * from SYSTEM_APP_FORM where TABLE_CODE='"+tableName+"'");
				List tableLists=sqlHelper.querySql();
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
		
}
