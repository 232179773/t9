package com.t9.system.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.t9.system.entity.SystemUser;
import com.t9.system.util.DataUtil;
import com.t9.system.web.T9Exception;
import com.t9.system.web.T9Result;

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:application-context.xml"})
public class SystemUserServiceTest {

	@Autowired
	private SystemUserService systemUserService;
	
	private SystemUser getUserData() {
		String password = "my_loginPassword";
		SystemUser user = new SystemUser();
		user.setNAME("my_name");
		user.setNICKNAME("my_nickName");
		user.setLOGIN_CODE("my_loginCode");
		user.setLOGIN_PWD(password);
		user.setSTATUS(SystemUser.STATUS_ACTIVE);
		user.setSUPER_ADMIN(false);
		user.setUSER_RIGHTTYPE(SystemUser.USER_RIGHTTYPE_BACK);
		return user;
	}
	
	@Test
	public void testSaveUser() {
		SystemUser userObj = getUserData();
		T9Result result = systemUserService.saveUser(userObj);
		Assert.isTrue(result.isSuccess());

	}
	
	@Test
	public void testUpdateUser() throws T9Exception {
		SystemUser userObj = getUserData();
		//非持久化对象
		T9Result result = systemUserService.updateUser(userObj);
		Assert.isTrue(!result.isSuccess());
		
		//非持久化对象
		userObj.setID("xxxx");
		result = systemUserService.updateUser(userObj);
		Assert.isTrue(!result.isSuccess());
		
		result = systemUserService.saveUser(userObj);
		Assert.isTrue(result.isSuccess());
		
		result = systemUserService.updateUser(userObj);
		Assert.isTrue(result.isSuccess());
	}
	

	@Test
	public void testChangePassword() {
		SystemUser userObj = getUserData();
		String oldPassword=  userObj.getLOGIN_PWD();
		systemUserService.saveUser(userObj);
		
		String newPassword=  "88888888";
		T9Result result;
		try {
			result = systemUserService.changePassword(userObj.getID(), oldPassword, newPassword);
			Assert.isTrue(result.isSuccess());
			
			SystemUser domain = systemUserService.querySystemUserById(userObj.getID());
			Assert.notNull(domain);
			
			Assert.isTrue(DataUtil.encodePassword(newPassword, null).equals(domain.getLOGIN_PWD()));
		} catch (T9Exception e) {
			e.printStackTrace();
		}

	}
	
	
	
}
