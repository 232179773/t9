package com.t9.system.service;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.t9.system.dao.RoleDao;
import com.t9.system.dao.SystemUserDao;
import com.t9.system.dao.SystemUserRoleDao;
import com.t9.system.entity.Role;
import com.t9.system.entity.SystemUser;
import com.t9.system.entity.SystemUserRole;
import com.t9.system.web.T9Exception;
import com.t9.system.web.T9Result;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleDao roleDao;
	@Autowired
	private SystemUserRoleDao userRoleDao;
	@Autowired
	private SystemUserDao systemUserDao;
	
	public List<Map> queryRoleList(Map<String, String> map,boolean pageInfo) {
		return roleDao.queryRoleList(map,pageInfo);
	}

	@Override
	public T9Result saveStore(Role role) {
		T9Result result = new T9Result();
		roleDao.save(role);
		return result;
	}

//	public List<Map> queryUsersByRoleId(String roleId) {
//		return roleDao.queryUsersWithRole(roleId, true);
//	}
//	
//	public List<Map> queryUsersWithoutRole(String roleId,String userName, String nickName) {
//		return roleDao.queryUsersWithoutRoleByPage(roleId, userName, nickName, true);
//	}
	
	public Role queryRoleById(String id) {
		
		return (Role)roleDao.get(id);
	}

	@Override
	public T9Result deleteRole(String id) throws T9Exception {
		T9Result result=new T9Result();
		roleDao.deleteRole(id);
		return result;
		
	}
	
	@Override
	public T9Result deleteRoleUsers(String roleUserIDs) {
		T9Result result=new T9Result();
		String[] array = roleUserIDs.split(",");
		for(String roleUserID: array) {
			userRoleDao.delete(roleUserID);
		}

		return result;
	}
	
	public T9Result updateRole(Role role) {
		T9Result result = new T9Result();
		roleDao.update(role);
		return result;
	}

	public T9Result addRoleUser(String roleId, String userIds) {
		T9Result result = new T9Result();
		
		try {
			//check blank: roleID, userIds
			if(StringUtils.isBlank(roleId)) {
				throw new T9Exception("000001", "角色ID");
			}
			
			if(StringUtils.isBlank(userIds)) {
				throw new T9Exception("000001", "用户ID");
			}
			
			//check exists:roleID, userIds
			if(roleDao.get(roleId) == null) {
				throw new T9Exception("system.objectNotFound", "角色:"+roleId);
			}
			
			
			//check users: not front users
			String[] userIdArray = userIds.split(",");
			for(String userId : userIdArray) {
				SystemUser userObj = (SystemUser)systemUserDao.get(userId);
				if(userObj == null) {
					throw new T9Exception("system.objectNotFound", "用户:"+userId);
				}
				if(SystemUser.USER_RIGHTTYPE_FRONT.equals(userObj.getUSER_RIGHTTYPE())) {
					throw new T9Exception("system.invalidValue", "前台用户:"+userId);
				}
			}
			
			
			for(String userId : userIdArray) {
				SystemUserRole entity = new SystemUserRole();
				entity.setROLE_ID(roleId);
				entity.setUSER_ID(userId);
				userRoleDao.save(entity);
			}
		} catch(T9Exception e) {
			result.setResult(false, e.getMessage());
		}
				
		return result;
	}
}
