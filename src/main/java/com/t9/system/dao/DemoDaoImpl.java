package com.t9.system.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.t9.system.common.SQLQuery;
import com.t9.system.web.QueryResult;

@Repository
public class DemoDaoImpl extends BaseDaoImpl implements DemoDao{
	
	/**
	 * 列表查询
	 */
	public List<Map> queryList(Map<String, String> map) {
		SQLQuery sqlQuery=new SQLQuery("SELECT * FROM TB_STAFF WHERE 1=1 ");
		sqlQuery.addEquealParam(map, "USER_NAME", "PHONE_NO");
//		sqlQuery.addEquealParam("USER_NAME", map.get("USER_NAME"));
//		sqlQuery.addEquealParam("USER_NAME", map.get("USER_NAME"),true);
		sqlQuery.addSQL(" order by  PHONE_NO");
//		sqlQuery.addSQL(" and USER_NAME =?", map.get("USER_NAME"));
//		sqlQuery.addLikeParam("USER_NAME", map.get("USER_NAME"));
		List<Map> list = this.queryPageSql(sqlQuery);

		return list;
	}
	
	
}
