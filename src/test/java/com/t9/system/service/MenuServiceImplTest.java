package com.t9.system.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.t9.system.entity.Menu;
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:application-context.xml"})
@SuppressWarnings({ "unchecked", "rawtypes" })
public class MenuServiceImplTest {

	@Autowired
	private MenuService menuService;
	Menu menu=new Menu();
	@Before
	public void setUp() throws Exception {
		menu.setNAME("测试");
		menuService.saveMenu(menu);
	}

	@Test
	public void testQueryList() {
		HashMap map =new HashMap();
		map.put("NAME", "测试");
		List<Map> list=menuService.queryList(map,true);
		Assert.assertTrue(list.size()>0);
	}

	@Test
	public void testQueryMenuById() {
		Menu menu2=menuService.queryMenuById(menu.getID());
		Assert.assertEquals(menu2.getNAME(), menu.getNAME());
	}

	@Test
	public void testSaveMenu() {
		Menu menu1=new Menu();
		menu1.setNAME("测试22");
		menuService.saveMenu(menu1);
	}

	@Test
	public void testUpdateMenu() {
		menu.setREMARK("aa");
		menuService.updateMenu(menu);
	}

	@Test
	public void testDeleteMenu() {
		menuService.deleteMenu(menu.getID());
	}

}
