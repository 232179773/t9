package com.t9.system.dao;

import java.util.List;
import java.util.Map;

import com.t9.system.web.T9Exception;


public interface SystemConfigDao extends BaseDao {

	/**
	 * 查询指定用户有权限查看的系统参数记录
	 * @param paramName 按paramName过滤记录
	 * @param userId 用户id
	 * @param pageInfo 分页信息
	 */
	public List<Map> querySystemConfigListByUser(String paramName, String userId,  boolean paging) throws T9Exception ;
	
	/**通过code获取config对象
	 * @param code
	 * @return
	 */
	public List<Map> querySystemConfigByCodes(String... codes);

}