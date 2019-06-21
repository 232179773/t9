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

import com.t9.system.entity.DictOption;

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:application-context.xml"})
public class DictOptionDaoImplTest {

	@Autowired
	private DictOptionDao dictOptionDao;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		
	}

	@Test
	public void testQueryDictOptionListByPage() {
//		//	private String DICT_CODE;
//		private String OPTION_CODE;
//		private String OPTION_VALUE;
//		private String OPTION_NAME;
//		private Long ORDER_NO; 
//		private String PARENT_OPTION_ID;
//		private String ISDISPLAY;
//		private Long OPTION_LEVEL;
		DictOption dictOption=new DictOption();
		dictOption.setDICT_CODE("1");
		dictOption.setOPTION_VALUE("3");
		dictOption.setOPTION_NAME("test");
		
		dictOptionDao.save(dictOption);
		
		DictOption dictOptionSun=new DictOption();
		
		dictOptionSun.setDICT_CODE("1");
		dictOptionSun.setOPTION_VALUE("3");
		dictOptionSun.setOPTION_NAME("test");
		
		dictOptionDao.save(dictOptionSun);
		//DICT_CODE PARENT_OPTION_ID
		Map<String,String>map1=new HashMap<String, String>();
		map1.put("DICT_CODE", dictOption.getDICT_CODE());
		List  list1=dictOptionDao.queryDictOption(map1,true);
		Assert.assertTrue("list1 size is 1", list1.size()>=0);
		
		Map<String,String>map2=new HashMap<String, String>();
		List  list2=dictOptionDao.queryDictOption(map1,true);
		Assert.assertTrue("list2 size is 1", list2.size()==1);
		
	}

	@Test
	public void testQueryDictOptionByContion() {
		
	}

	@Test
	public void testDeleteDictOption() {
		
	}

	@Test
	public void testUpdateDictOption() {
		
	}

	@Test
	public void testQueryDictOptionByValue() {
		
	}

}
