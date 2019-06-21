package com.t9.system.service;

import java.util.List;
import java.util.Map;

import com.t9.system.entity.SystemUser;
import com.t9.system.web.T9Result;
import com.t9.system.web.T9Exception;



public interface SystemUserService {

	public List<Map> searchUsers(Map map,boolean pageinfo);
	
	public SystemUser querySystemUserById(String id);
	
	public T9Result saveUser(SystemUser systemUser);
	
	public T9Result updateUser(SystemUser systemUser) throws T9Exception;
	
	public T9Result deleteUsers(String... ids);
	
	public boolean isSuperAdmin(String userId);

	public T9Result changePassword(String userId,String oldPassword, String newPassword) throws T9Exception;
	
	public SystemUser getSessionCurrentUser();

	public abstract List<Map> userLogin(Map map);
	
	public List<Map> queryUserRole(String userId);
	
	/**
	 * 更新session里边的用户信息
	 * @return
	 */
	public void updateSessionCurrentUser(SystemUser systemUser);
	/**
	 * 更新用户和角色关系
	 * @param USER_ID
	 * @param ROLE_ID
	 */
	public void saveUserRoleRelation(String USER_ID,String ROLE_ID);
}