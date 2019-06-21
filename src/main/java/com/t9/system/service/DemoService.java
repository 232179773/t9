package com.t9.system.service;

import java.util.List;
import java.util.Map;

import com.t9.system.entity.Demo;
import com.t9.system.web.QueryResult;
import com.t9.system.web.T9Result;

public interface DemoService {

	/**
	 * 列表查询
	 * @param Map
	 * @return QueryResult
	 */
	public List<Map> queryList(Map<String, String> map);

	/**
	 * 根据主键ID查询
	 * @param id
	 * @return Demo
	 */
	public Demo queryDemoById(String id);

	/**
	 * 保存数据
	 * @param staff
	 * @return
	 */
	public T9Result saveDemo(Demo staff);

	
	/**
	 * 更新数据
	 * @param staff
	 * @return
	 */
	public T9Result updateDemo(Demo staff);
	
	public T9Result deleteDemo(String... ids);

}