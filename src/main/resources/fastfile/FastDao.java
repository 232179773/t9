package com.arp.system.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.arp.system.common.Expression;
import com.arp.system.common.MatchMode;
import com.arp.system.common.SQLHelper;
import com.arp.system.util.DateUtil;

@Repository
@SuppressWarnings({"unchecked", "rawtypes"})
public class FastDao extends BaseDaoImpl{
	
	/**
	 * 列表查询
	 */
	public List<Map> queryList(Map<String, String> map,HashMap tableMap) {
		String tableCode=(String)tableMap.get("TABLE_CODE");
		SQLHelper sqlHelper=new SQLHelper("SELECT * FROM "+tableCode+" WHERE 1=1");
		sqlHelper.setAutoFilterNullValue(true);
		List tableColumnList=(List)tableMap.get("columnList");
		for (int i = 0; i < tableColumnList.size(); i++) {
			HashMap columnHashMap=(HashMap)tableColumnList.get(i);	
			if("1".equals(columnHashMap.get("SHOW_QUERY"))){
				if("Date".equals(columnHashMap.get("JAVA_TYPE"))){
					String colCode=(String)columnHashMap.get("COL_CODE");
					sqlHelper.addCondition(Expression.gt(colCode,DateUtil.parasStartDate((String)map.get(colCode+"_START")) ));
					sqlHelper.addCondition(Expression.lt(colCode, DateUtil.parasEndDate((String)map.get(colCode+"_END")) ));
					
				}else{
					sqlHelper.addCondition(Expression.like((String)columnHashMap.get("COL_CODE"), 
									map.get(columnHashMap.get("COL_CODE")),MatchMode.START));
				}
			}
		}
		sqlHelper.addCondition(Expression.eq("ID", (String)map.get("ID")));
		List<Map> list=null;
		list= sqlHelper.querySql();
		return list;
	}

	
}
