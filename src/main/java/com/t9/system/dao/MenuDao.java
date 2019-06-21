package com.t9.system.dao;

import java.util.List;
import java.util.Map;

public interface MenuDao extends BaseDao {

	public List<Map> queryList(Map<String, String> e);
	
	public List<Map> queryList(Map<String, String> map,boolean pageinfo) ;

	public abstract String queryMaxCode(String parentId);

}