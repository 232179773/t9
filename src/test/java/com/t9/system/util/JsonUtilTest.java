package com.t9.system.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

@SuppressWarnings({"unchecked","rawtypes"})
public class JsonUtilTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGetJsonString() {
		HashMap map=new HashMap();
		JsonUtil.getJsonString(map);
	}

	@Test
	public void testGetListByJsonArray() {
		List list=new ArrayList();
		HashMap map=new HashMap();
		map.put("aa", "bb");
		list.add(map);
		String jsonStr=JsonUtil.getJsonString(list);
		JsonUtil.getListByJsonArray(jsonStr);
	}

	@Test
	public void testGetObjectByJson() {
		HashMap map=new HashMap();
		map.put("aa", "bb");
		String aa=JsonUtil.getJsonString(map);
		map=(HashMap)JsonUtil.getObjectByJson(aa, HashMap.class);
	}

	@Test
	public void testGetMapByJsonString() {
		HashMap map=new HashMap();
		map.put("aa", "bb");
		String jsons=JsonUtil.getJsonString(map);
		JsonUtil.getMapByJsonString(jsons);
	}

}
