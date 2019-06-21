package com.t9.system.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class BuildTreeTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testCreateTree() {
		Map<String, String> m1 = new HashMap<String, String>();
		m1.put("a", "1");
		m1.put("b", "");
		m1.put("cPowerSysName", "AAA");
		Map<String, String> m2 = new HashMap<String, String>();
		m2.put("a", "2");
		m2.put("b", "1");
		m2.put("cPowerSysName", "BBB");
		Map<String, String> m3 = new HashMap<String, String>();
		m3.put("a", "3");
		m3.put("b", "2");
		m3.put("cPowerSysName", "CCC");
		Map<String, String> m4 = new HashMap<String, String>();
		m4.put("a", "4");
		m4.put("b", "");
		m4.put("cPowerSysName", "DDD");
		Map<String, String> m5 = new HashMap<String, String>();
		m5.put("a", "5");
		m5.put("b", "4");
		m5.put("cPowerSysName", "EEE");
	
		Map<String, String> m6 = new HashMap<String, String>();
		m6.put("a", "6");
		m6.put("b", "1");
		m6.put("cPowerSysName", "FFFF");
		List list = new ArrayList<Map>();
		list.add(m5);
		list.add(m2);
		list.add(m3);
		list.add(m4);
		list.add(m1);
		list.add(m6);
		List<Map> ls = BuildTree.createTree(list, "b", "a");
		String json = JsonUtil.getJsonString(ls);
		System.out.println(json);
	}

}
