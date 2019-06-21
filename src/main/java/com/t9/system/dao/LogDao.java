package com.t9.system.dao;

import java.util.List;
import java.util.Map;

public interface LogDao extends BaseDao {

	public List<Map>  queryList(Map<String, String> e);

	public abstract List<Map> statPageViewByMONTH(Map<String, String> map);

	public abstract List<Map> statPageViewByDay(Map<String, String> map);

	public abstract List<Map> statPageViewByURL(Map<String, String> map);

	public abstract List<Map> statIP(Map<String, String> map);

}