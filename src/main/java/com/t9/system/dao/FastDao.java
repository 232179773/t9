package com.t9.system.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.t9.system.common.SQLQuery;
import com.t9.system.util.StringUtil;
import com.t9.system.web.T9Result;

@Repository
@SuppressWarnings({"unchecked", "rawtypes"})
public class FastDao extends BaseDaoImpl{
	
	/**
	 * 列表查询
	 */
	public List<Map> queryList(Map<String, String> map,HashMap tableMap) {
		String tableCode=(String)tableMap.get("TABLE_CODE");
		SQLQuery sqlQuery=new SQLQuery("SELECT * FROM "+tableCode+" WHERE 1=1");
		List tableColumnList=(List)tableMap.get("columnList");
		for (int i = 0; i < tableColumnList.size(); i++) {
			HashMap columnHashMap=(HashMap)tableColumnList.get(i);	
			if("1".equals(columnHashMap.get("SHOW_QUERY"))){
				if("Date".equals(columnHashMap.get("JAVA_TYPE"))){
					sqlQuery.addDateRange(map, (String)columnHashMap.get("COL_CODE"));
				}else{
					sqlQuery.addEquealParam(map, (String)columnHashMap.get("COL_CODE"));
				}
			}
		}
		sqlQuery.addEquealParam(map,"ID");
		List<Map> list=null;
		list= this.queryPageSql(sqlQuery);
		return list;
	}
	
	/**
	 * 列表查询
	 */
	public Map queryById(Map<String, String> map,HashMap tableMap) {
		String tableCode=(String)tableMap.get("TABLE_CODE");
		SQLQuery sqlQuery=new SQLQuery("SELECT * FROM "+tableCode+" WHERE 1=1");
		sqlQuery.addEquealParam(map,"ID");
		List<Map> list=null;
		list= this.querySql(sqlQuery);
		return list.get(0);
	}

	
}
