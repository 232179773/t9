package com.t9.system.service;

import java.util.List;
import java.util.Map;

import com.t9.system.entity.RoleFunctionRight;
import com.t9.system.web.T9Result;

public interface RoleFunctionRightService {
	
	public List<Map> queryRoleFRList(Map<String,String>map);

	public T9Result saveRoleFunctionRight(RoleFunctionRight roleFunctionRight);

	public T9Result deleteRoleFunctionRight(Map map);

	public List<Map> queryTreeByParentId(Map<String, String> map);

}
