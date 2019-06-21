package com.t9.system.service;

import java.util.List;
import java.util.Map;

import com.t9.system.entity.SystemConfig;
import com.t9.system.web.T9Result;
import com.t9.system.web.T9Exception;


public interface SystemConfigService {

	/**
	 * 根据主键ID查询
	 * @param id
	 * @return SystemConfig
	 */
	public SystemConfig querySystemConfigById(String id);
	
	public T9Result updateSystemConfig(String ID, String value);
	
	public List<Map> searchSystemCongfig(Map map, String userId) throws T9Exception;
	
	public List<Map> querySystemConfigByCodes(String... codes); 

}