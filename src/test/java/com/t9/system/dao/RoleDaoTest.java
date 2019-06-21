package com.t9.system.dao;

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

import com.t9.system.entity.Role;
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:application-context.xml"})
public class RoleDaoTest {
	@Autowired
	private RoleDao roleDao;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testQueryRoleListByPage() {
		Role role=new Role();
		role.setNAME("testRole");
		roleDao.save(role);
		Map map=new HashMap();
		map.put("NAME","testRole");
		List<Map>list=roleDao.queryRoleList(map,true);
		Assert.assertTrue("LIST IS NOT NULL", list.size()>0);
		StringBuffer sb=new StringBuffer();
		for(Map map1:list){
			sb.append(map1.get("ID"));
		}
		List<Map>list1=roleDao.queryRoleList(map,true);
	}

	@Test
	public void testQueryRoleListByParentId() {
		Role role=new Role();
		role.setNAME("testRole");
		roleDao.save(role);
		List<Map> list=roleDao.queryRoleListByParentId(null);
		Assert.assertTrue("list is null", list.size()==0);
		
		Map map=new HashMap();
		map.put("NAME","testRole");
		List<Map>list1=roleDao.queryRoleList(map,true);
		
		Map map1=list1.get(0);
		
		Role role1=new Role();
		role1.setNAME("testRole1");
		role1.setPARENTROLEID(map1.get("ID").toString());
		roleDao.save(role1);
		List<Map> list2=roleDao.queryRoleListByParentId(map1.get("ID").toString());
		
		Assert.assertTrue("list2 is null", list2.size()>0);
	}

	@Test
	public void testDeleteRole() {
		Role role=new Role();
		role.setNAME("testRole");
		roleDao.save(role);
		Map map=new HashMap();
		map.put("NAME","testRole");
		List<Map>list=roleDao.queryRoleList(map,true);
		Assert.assertTrue("LIST IS NOT NULL", list.size()>0);
		StringBuffer sb=new StringBuffer();
		for(Map map1:list){
			sb.append(map1.get("ID"));
		}
		List<Map>list1=roleDao.queryRoleList(map,true);
	}

}
