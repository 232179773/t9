package com.t9.system.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.t9.system.common.SQLQuery;
import com.t9.system.web.QueryResult;

@Repository
public class LogDaoImpl extends BaseDaoImpl implements LogDao{
	
	/**
	 * 列表查询
	 */
	public List<Map>  queryList(Map<String, String> map) {
		SQLQuery sqlQuery=new SQLQuery("SELECT * FROM TB_LOG WHERE 1=1 ");
		sqlQuery.addEquealParam(map, "STAFF_NAME","CLIENT_IP");
		sqlQuery.addDateRange(map, "LOG_TIME");
//		sqlQuery.addSQL(" and STAFF_NAME =?", map.get("STAFF_NAME"));
//		sqlQuery.addLikeParam("STAFF_NAME", map.get("STAFF_NAME"));
		List<Map>  list = this.queryPageSql(sqlQuery);

		return list;
	}
	
	/**
	 * 按url统计每个链接的PV值
	 */
	@Override
	public List<Map>  statPageViewByURL(Map<String, String> map) {
		SQLQuery sqlQuery=new SQLQuery("SELECT URL PVSTAT_TYPE,COUNT(*) PVSTAT_COUNT FROM TB_LOG WHERE 1=1 ");
//		sqlQuery.addEquealParam(map, "STAFF_NAME");
		sqlQuery.addDateRange(map, "LOG_TIME");
		sqlQuery.addSQL(" group by URL");
		sqlQuery.addSQL(" order by PVSTAT_TYPE");
		List<Map>  list = this.queryPageSql(sqlQuery);
		return list;
	}
	
	/**
	 * 按日统计每天的PV值
	 */
	@Override
	public List<Map>  statPageViewByDay(Map<String, String> map) {
		SQLQuery sqlQuery=new SQLQuery("select DATE_FORMAT(LOG_TIME,'%Y-%m-%d') PVSTAT_TYPE,count(*) PVSTAT_COUNT FROM TB_LOG where 1=1 ");
//		sqlQuery.addEquealParam(map, "STAFF_NAME");
		sqlQuery.addDateRange(map, "LOG_TIME");
		sqlQuery.addSQL(" group by  DATE_FORMAT(LOG_TIME,'%Y-%m-%d')");
		sqlQuery.addSQL(" order by PVSTAT_TYPE");
		List<Map>  list = this.queryPageSql(sqlQuery);
		return list;
	}
	/**
	 * 按月统计每月的PV值
	 */
	@Override
	public List<Map>  statPageViewByMONTH(Map<String, String> map) {
		SQLQuery sqlQuery=new SQLQuery("select DATE_FORMAT(LOG_TIME,'%Y%m') PVSTAT_TYPE,count(*) PVSTAT_COUNT FROM TB_LOG where 1=1 ");
//		sqlQuery.addEquealParam(map, "STAFF_NAME");
		sqlQuery.addDateRange(map, "LOG_TIME");
		sqlQuery.addSQL(" group by  DATE_FORMAT(LOG_TIME,'%Y%m')");
		sqlQuery.addSQL(" order by PVSTAT_TYPE");
		List<Map>  list = this.queryPageSql(sqlQuery);
		return list;
	}
	
	/**
	 * 统计每段时间的独立IP值
	 */
	@Override
	public List<Map>  statIP(Map<String, String> map) {
		String timeType=map.get("timeType");
		SQLQuery sqlQuery=new SQLQuery("select DATE_FORMAT(LOG_TIME,'"+timeType+"') PVSTAT_TYPE,count(DISTINCT(CLIENT_IP)) PVSTAT_COUNT FROM  TB_LOG where 1=1 ");
//		sqlQuery.addEquealParam(map, "STAFF_NAME");
		sqlQuery.addDateRange(map, "LOG_TIME");
		sqlQuery.addSQL(" group by DATE_FORMAT(LOG_TIME,'"+timeType+"')");
		sqlQuery.addSQL(" order by PVSTAT_TYPE");
		List<Map>  list = this.queryPageSql(sqlQuery);
		return list;
	}
}
