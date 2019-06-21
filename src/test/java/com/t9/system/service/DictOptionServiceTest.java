package com.t9.system.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.HashMap;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.t9.system.entity.DictOption;
import com.t9.system.web.T9Exception;
import com.t9.system.web.T9Result;
import com.t9.system.web.T9RuningTimeException;

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:application-context.xml"})
@SuppressWarnings({"unchecked","rawtypes"})
public class DictOptionServiceTest {

	@Autowired
	private DictOptionService dictOptionService;
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testQueryDictOption() {
		HashMap map=new HashMap();
		dictOptionService.queryDictOption(map,true);
		map.put("DICT_CODE", "INDUSTRY");
		dictOptionService.queryDictOption(map,true);
	}

	@Test
	public void testSaveDicOption() throws T9Exception {
		DictOption dictOption=new DictOption();
		dictOption.setDICT_CODE("INDUSTRY");
		dictOption.setOPTION_NAME("计算机");
		dictOption.setOPTION_VALUE("COMPUTER");
		dictOption.setORDER_NO(1l);
		dictOptionService.saveDictOption(dictOption);
		assertNotNull(dictOption.getID());
	}
	
	@Test(expected=T9RuningTimeException.class)	
	public void testSaveDicOptionExist() throws T9Exception {
		DictOption dictOption=new DictOption();
		dictOption.setDICT_CODE("INDUSTRY");
		dictOption.setOPTION_NAME("计算机");
		dictOption.setOPTION_VALUE("COMPUTER");
		dictOption.setORDER_NO(1l);
		dictOptionService.saveDictOption(dictOption);
		dictOptionService.saveDictOption(dictOption);
	}

	@Test
	public void testUpdateDicOption() throws T9Exception {
		DictOption dictOption=new DictOption();
		dictOption.setDICT_CODE("INDUSTRY");
		dictOption.setOPTION_NAME("计算机");
		dictOption.setOPTION_VALUE("COMPUTER");
		dictOption.setORDER_NO(1l);
		dictOptionService.saveDictOption(dictOption);

		HashMap map=new HashMap();
		dictOptionService.updateDicOption(map);
	}

	@Test
	public void testDeleteDicOption() throws T9Exception {
		DictOption dictOption=new DictOption();
		dictOption.setDICT_CODE("INDUSTRY");
		dictOption.setOPTION_NAME("计算机");
		dictOption.setOPTION_VALUE("COMPUTER");
		dictOption.setORDER_NO(1l);
		dictOptionService.saveDictOption(dictOption);
		T9Result result=dictOptionService.deleteDicOption(dictOption.getID());
		Assert.assertTrue("", result.isSuccess());
	}

	@Test
	public void testQueryDictOptionById() throws T9Exception {
		DictOption dictOption=new DictOption();
		dictOption.setDICT_CODE("INDUSTRY");
		dictOption.setOPTION_NAME("计算机");
		dictOption.setOPTION_VALUE("COMPUTER");
		dictOption.setORDER_NO(1l);
		dictOptionService.saveDictOption(dictOption);
		DictOption dictOption1=dictOptionService.queryDictOptionById(dictOption.getID());
		assertEquals(dictOption1.getDICT_CODE(), "INDUSTRY");
	}


}
