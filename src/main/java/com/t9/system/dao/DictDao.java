package com.t9.system.dao;

import java.util.List;
import java.util.Map;

import com.t9.system.entity.Dict;
import com.t9.system.web.QueryResult;

public interface DictDao extends BaseDao{

	/**
	 * 
	 * @param e
	 * @param dictSqlType 分页查询
	 * @return
	 */
	public List<Map> queryDictListByPage(Map<String, String> e,boolean dictSqlType);
	
	/**
	 * 
	 * @param e
	 * @param dictSqlType 普通查询
	 * @return
	 */
	public List<Map> queryDictList(Map<String, String> e,boolean dictSqlType);
}
