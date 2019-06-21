package com.t9.system.dao;

import java.util.List;
import java.util.Map;



public interface SystemUserDao extends BaseDao {
	/**
	 * 查询用户记录
	 * @param paramName 按paramName过滤记录
	 * @param currentUserId 用户id
	 * @param pageInfo 分页信息
	 */
	public List<Map> queryList(Map<String, String> map,boolean pageinfo);

	public abstract List<Map> userLogin(String loginCode, String password);
	
	public List<Map> queryUserRole(String userId) ;
}