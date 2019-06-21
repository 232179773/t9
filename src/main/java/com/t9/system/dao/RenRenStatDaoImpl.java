package com.t9.system.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.t9.system.common.SQLQuery;

@Repository
@SuppressWarnings({ "rawtypes"})
public class RenRenStatDaoImpl extends BaseDaoImpl{
	
	/**
	 * 列表查询
	 */
	public List<Map> queryList(Map<String, String> map) {
		SQLQuery sqlQuery=new SQLQuery("SELECT * FROM TB_SYSTEM_APPENDIX WHERE 1=1 ");
		sqlQuery.addEquealParam(map, "TABLE_NAME", "DATA_ID");
		List<Map> list = this.queryPageSql(sqlQuery);
		return list;
	}
}
