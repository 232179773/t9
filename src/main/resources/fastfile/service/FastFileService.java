package com.arp.system.service;

import java.util.List;
import java.util.Map;

public interface FastFileService {

	/**
	 * 列表查询
	 * @param Map
	 * @return QueryResult
	 */
	public List<Map> queryList(Map<String, String> map);


}