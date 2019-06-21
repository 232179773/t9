package com.t9.system.dao;

import java.util.List;
import java.util.Map;

public interface DemoDao extends BaseDao {

	public List<Map> queryList(Map<String, String> e);

}