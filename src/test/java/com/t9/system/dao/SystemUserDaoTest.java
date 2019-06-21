package com.t9.system.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.t9.system.entity.SystemUser;
import com.t9.system.util.DataUtil;

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:application-context.xml"})
public class SystemUserDaoTest {

	@Autowired
	SystemUserDao systemUserDao;
	
	@Test
	public void testQueryList() {
		String password = "my_loginPassword";
		SystemUser user = new SystemUser();
		user.setNAME("my_name");
		user.setNICKNAME("my_nickName");
		user.setLOGIN_CODE("my_loginCode");
		user.setLOGIN_PWD(password);
		user.setSTATUS(SystemUser.STATUS_ACTIVE);
		user.setSUPER_ADMIN(false);
		user.setUSER_RIGHTTYPE("2");
		systemUserDao.save(user);
		
		String userRightType = "B"; //back, match rightType 2,3
		String roleId = null;
		HashMap map=new HashMap();
		map.put("NAME", "my_name");
		List<Map> lst  = systemUserDao.queryList(map, false);
		Assert.isTrue(lst != null && lst.size() > 0);
		Map mapObj = lst.get(0);
		Assert.isTrue(user.getNAME().equals(mapObj.get("NAME")));
		Assert.isTrue(user.getLOGIN_CODE().equals(mapObj.get("LOGIN_CODE")));

	}

	@Test
	public void testSave() {
		String password = "my_loginPassword";
		SystemUser user = new SystemUser();
		user.setNAME("my_name");
		user.setNICKNAME("my_nickName");
		user.setLOGIN_CODE("my_loginCode");
		user.setLOGIN_PWD(password);
		user.setSTATUS(SystemUser.STATUS_ACTIVE);
		user.setSUPER_ADMIN(false);
		user.setUSER_RIGHTTYPE("2");
		systemUserDao.save(user);
		
		SystemUser domain = (SystemUser)systemUserDao.get(user.getID());
		Assert.notNull(domain, "user");
		
		Assert.isTrue(user.getNAME().equals(domain.getNAME()));
		
		Assert.isTrue(DataUtil.encodePassword(password, null).equals(domain.getLOGIN_PWD()));
	}
}
