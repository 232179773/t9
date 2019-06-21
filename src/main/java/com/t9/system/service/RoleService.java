package com.t9.system.service;

import java.util.List;
import java.util.Map;

import com.t9.system.entity.Role;
import com.t9.system.web.T9Exception;
import com.t9.system.web.T9Result;

public interface RoleService {

	public List<Map> queryRoleList(Map<String, String> map,boolean pageInfo);

//	public List<Map> queryUsersByRoleId(String roleId);
//	public List<Map> queryUsersWithoutRole(String roleId, String userName, String nickName);
	
	public T9Result saveStore(Role role);
	
	public Role queryRoleById(String id);

	public T9Result deleteRole(String id) throws T9Exception;

	public T9Result updateRole(Role role);

	public T9Result deleteRoleUsers(String roleUserIDs);

	public T9Result addRoleUser(String roleId, String userIds);
}
