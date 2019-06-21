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

import com.t9.system.entity.Dict;

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:application-context.xml"})
public class DictDaoImplTest {

	@Autowired
	private DictDao dictDao;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testQueryDictList() {
//		private String DICT_CODE;
//		private String DICT_NAME;
//		private String DICT_TYPE;
//		private Long OPTION_MAXLEVEL;
//		private String REMARK;
		
		Dict dict=new Dict();
		dict.setDICT_CODE("testCode");
		dict.setDICT_NAME("testdictName");
		dict.setDICT_TYPE("1");
		dict.setREMARK("这是一个测试");
		dictDao.save(dict);
		Map<String,String>map=new HashMap<String,String>();
		map.put("DICT_CODE","testCode");
		map.put("DICT_NAME", "testdictName");
		List list=dictDao.queryDictList(map, true);
		Assert.assertTrue("list size is 1", list.size()==1);
		
		List list1=dictDao.queryDictList(map, false);
		Assert.assertTrue("list size is 2", list1.size()==2);
	}

}
