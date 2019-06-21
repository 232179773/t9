package com.t9.system.dao;

import java.util.List;
import java.util.Map;

import com.t9.system.web.T9Result;

public interface AppendixDao extends BaseDao {

	public List<Map> queryList(Map<String, String> e);

	public T9Result updateAppendix(String[] fileIds, String tableName, String tableId);

}