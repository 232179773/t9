package com.t9.system.dao;

import java.util.List;
import java.util.Map;

public interface RoleFunctionRightDao extends BaseDao{
	
	public List<Map> queryRoleFRList(Map<String,String>map);

	public void deleteRoleFunctionRight(Map<String,String> map);

	public List<Map> queryTreeByParentId(Map<String, String> map);

}
