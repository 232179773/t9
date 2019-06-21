package com.t9.system.dao;

import java.util.List;
import java.util.Map;

import com.t9.system.common.SQLQuery;
import com.t9.system.entity.BaseDomain;
import com.t9.system.web.QueryResult;

@SuppressWarnings("unchecked")
public interface BaseDao {

	public Object get(String id);

	public void save(BaseDomain... pojos);

	public void update(BaseDomain... domain);

	public int delete(String... ids);

	/**
	 * 普通查询
	 * 
	 * @param sql
	 * @return
	 */
	public List<Map> querySql(SQLQuery sqlQuery);

	/**
	 * 分页查询
	 * 
	 * @param sql
	 * @return
	 */
	public List<Map>  queryPageSql(SQLQuery sqlQuery);
	
	/**
	 * 执行更新删除
	 * 
	 * @param sql
	 * @return
	 */
	public int execSql(SQLQuery sqlQuery);

}