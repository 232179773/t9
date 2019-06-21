package com.t9.system.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.t9.system.common.SQLQuery;

@Repository
@SuppressWarnings({ "rawtypes"})
public class RenRenLoanDaoImpl extends BaseDaoImpl{
	
	/**
	 * 列表查询
	 */
	public List<Map> queryList(Map<String, String> map) {
		SQLQuery sqlQuery=new SQLQuery("SELECT * FROM TB_RENREN_LOAN WHERE 1=1 ");
		sqlQuery.addEquealParam(map, "LOAN_ID");
		List<Map> list = this.querySql(sqlQuery);
		return list;
	}
}
