package com.t9.system.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.t9.system.entity.RoleFunctionRight;
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:application-context.xml"})
public class RoleFunctionRightDaoTest {
	@Autowired
	private RoleFunctionRightDao roleFunctionRightDao;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testQueryRoleFRListByPage() {
		//和testQueryTreeByParentId一样
	}

	@Test
	public void testDeleteRoleFunctionRight() {
		RoleFunctionRight roleFunctionRight=new RoleFunctionRight();
		roleFunctionRight.setMENU_ID("123");
		roleFunctionRight.setCREATETIME(new Date());
		roleFunctionRight.setCREATER("123");
		roleFunctionRight.setROLE_ID("123");
		roleFunctionRightDao.save(roleFunctionRight);
		Map<String,String>map=new HashMap<String, String>();
		map.put("ROLE_ID", roleFunctionRight.getROLE_ID());
		List list=roleFunctionRightDao.queryTreeByParentId(map);
		Assert.assertTrue("list size is 1", list.size()==1);
		
		roleFunctionRightDao.deleteRoleFunctionRight(map);
		List list1=roleFunctionRightDao.queryTreeByParentId(map);
		Assert.assertTrue("list1 size is 0", list1.size()==0);
	}

	@Test
	public void testQueryTreeByParentId() {
		RoleFunctionRight roleFunctionRight=new RoleFunctionRight();
		roleFunctionRight.setMENU_ID("123");
		roleFunctionRight.setCREATETIME(new Date());
		roleFunctionRight.setCREATER("123");
		roleFunctionRight.setROLE_ID("123");
		roleFunctionRightDao.save(roleFunctionRight);
		Map<String,String>map=new HashMap<String, String>();
		map.put("ROLE_ID", roleFunctionRight.getROLE_ID());
		List list=roleFunctionRightDao.queryTreeByParentId(map);
		Assert.assertTrue("list size is 1", list.size()==1);
	}

}
