package com.t9.system.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.t9.system.dao.SystemUserDao;
import com.t9.system.dao.SystemUserRoleDao;
import com.t9.system.entity.SystemUser;
import com.t9.system.entity.SystemUserRole;
import com.t9.system.util.DataUtil;
import com.t9.system.util.StringUtil;
import com.t9.system.web.RequestContext;
import com.t9.system.web.ServletUtils;
import com.t9.system.web.T9Exception;
import com.t9.system.web.T9Result;

@Service
@Transactional
public class SystemUserServiceImpl implements SystemUserService{
	public static final String DICT_USER_RIGHTTYPE = "USER_RIGHTTYPE";
	public static final String DICT_USER_STATE = "USER_STATE";
	
	@Autowired
	private SystemUserDao systemUserDao;

	@Autowired
	private SystemUserRoleDao systemUserRoleDao;
	
	@Autowired
	private DictOptionService dictOptionService;
	
	public List<Map> searchUsers(Map map,boolean pageinfo) {
		return systemUserDao.queryList(map, pageinfo);
	}
	
	@Override
	public List<Map> userLogin(Map map) {
		String loginCode = (String)map.get("LOGIN_CODE");
		String password = (String)map.get("PASSWORD");
		return systemUserDao.userLogin(loginCode, password);
	}
	
	public List<Map> queryUserRole(String userId) {
		return systemUserDao.queryUserRole(userId);
	}
	public SystemUser querySystemUserById(String id) {
		return (SystemUser)systemUserDao.get(id);
	}
	
	public void saveUserRoleRelation(String USER_ID,String ROLE_ID){
		if(StringUtil.isEmpty(USER_ID)){
			return;
		}			
		systemUserRoleDao.deleteUserRole(USER_ID);
		if(StringUtil.isEmpty(ROLE_ID)){
			return;
		}	
		SystemUserRole systemUserRole=new SystemUserRole();
		systemUserRole.setROLE_ID(ROLE_ID);
		systemUserRole.setUSER_ID(USER_ID);
		systemUserRole.setCREATETIME(new Date());
		systemUserRole.setCREATER(getSessionCurrentUser().getLOGIN_CODE());
		systemUserRoleDao.save(systemUserRole);
	}
	

	public SystemUser getSessionCurrentUser() {
		RequestContext requestContext=RequestContext.getContext();
		return (SystemUser)requestContext.getSession().getAttribute(ServletUtils.SEESION_USERINFO);
	}
	
	/**
	 * 更新session里边的用户信息
	 * @return
	 */
	public void updateSessionCurrentUser(SystemUser systemUser) {
		RequestContext requestContext=RequestContext.getContext();
		requestContext.getSession().setAttribute(ServletUtils.SEESION_USERINFO,systemUser);
	}
	
	
	
	public T9Result deleteUsers(String... ids) {
		T9Result result=new T9Result();
		systemUserRoleDao.deleteUserRole(ids);
		systemUserDao.delete(ids);
		return result;
	}

	@Override
	public T9Result saveUser(SystemUser systemUser) {
		T9Result result=new T9Result();
		systemUserDao.save(systemUser);		
		return result;
	}
	
	/**
	 * 保存用户信息
	 * param systemUser dto
	 * @throws T9Exception 
	 */
	@Override
	public T9Result updateUser(SystemUser systemUser) throws T9Exception {
		T9Result result=new T9Result();
		if (systemUser == null) {
			throw new T9Exception("systemUser.SystemUserDao.userNotFound");
		}

		if (StringUtils.isEmpty(systemUser.getID())) {
			throw new T9Exception("systemUser.SystemUserDao.userNotFound");
		}

		SystemUser domain = querySystemUserById(systemUser.getID());
		if (domain == null) {
			throw new T9Exception("systemUser.SystemUserDao.userNotFound",
					systemUser.getID());
		}

		domain.setNAME(systemUser.getNAME());
		domain.setNICKNAME(systemUser.getNICKNAME());
		domain.setSTATUS(systemUser.getSTATUS());
		domain.setUSER_RIGHTTYPE(systemUser.getUSER_RIGHTTYPE());
		domain.setSTATUS(systemUser.getSTATUS());
		domain.setVALIDITY_DATE(systemUser.getVALIDITY_DATE());
		systemUserDao.update(domain);
		return result;
	}
	
	@Override
	public T9Result changePassword(String userId, String oldPassword, String newPassword) throws T9Exception {
         T9Result result=new T9Result();
		 SystemUser domain = querySystemUserById(userId);
		 //校验旧密码
		 String encryptedPassword_old = DataUtil.encodePassword(oldPassword, null);
		 if(!encryptedPassword_old.equals(domain.getLOGIN_PWD())) {
			 throw new T9Exception("systemUser.wrongPassword");
		 }
		 
		 //密码加密
		 if(StringUtils.isEmpty(newPassword)) {
			 throw new T9Exception("000001", "新密码");
		 }
		 String encryptedPassword = DataUtil.encodePassword(newPassword, null);
		 domain.setLOGIN_PWD(encryptedPassword);
	
	     systemUserDao.update(domain);
	     return result;
	}
	
    public boolean isSuperAdmin(String userId) {
    	SystemUser systemUser = querySystemUserById(userId);
    	if(systemUser == null) {
    		return false;
    	}
    	
    	return systemUser.getSUPER_ADMIN() && "superadmin".equals(systemUser.getLOGIN_CODE());

    }
}
