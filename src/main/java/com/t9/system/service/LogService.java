package com.t9.system.service;

import java.util.List;
import java.util.Map;

import com.t9.system.entity.Log;
import com.t9.system.entity.Demo;
import com.t9.system.web.QueryResult;
import com.t9.system.web.T9Exception;
import com.t9.system.web.T9Result;

public interface LogService {

	/**
	 * 列表查询
	 * @param Map
	 * @return QueryResult
	 */
	public List<Map>  queryList(Map<String, String> map);
	
	public List<Map>  pvStat(Map<String, String> map) throws T9Exception;

	/**
	 * 根据主键ID查询
	 * @param id
	 * @return Log
	 */
	public Log queryLogById(String id);

	/**
	 * 保存数据
	 * @param staff
	 * @return
	 */
	public T9Result saveLog(Log log);

	public T9Result deleteLog(String... ids);

	public abstract List<Map> ipStat(Map<String, String> map) throws T9Exception;

	

}