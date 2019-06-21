package com.t9.system.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.t9.system.common.SQLQuery;

@Repository
public class EventDaoImpl extends BaseDaoImpl implements EventDao{
	/**
	 * 查询字典表
	 */
	public List<Map> queryDictListByPage(Map<String, String> map,boolean dictSqlType) {
		SQLQuery sqlQuery=new SQLQuery("SELECT * FROM T_EVENT WHERE 1=1 ");
		sqlQuery.addLikeParam("DICT_CODE", map.get("DICT_CODE"));
		List<Map> list= this.queryPageSql(sqlQuery);
		return list;
	}

	
}
