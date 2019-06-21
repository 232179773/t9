package com.t9.system.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Date;
import java.util.HashMap;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.t9.system.entity.Demo;
import com.t9.system.web.T9Result;

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:application-context.xml"})
@SuppressWarnings({"unchecked","rawtypes"})
public class DemoServiceTest {

	@Autowired
	private DemoService demoService;
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testQueryList() {
		HashMap map=new HashMap();
		map.put("PHONE_NO", "1");
		demoService.queryList(map);
	}

	@Test
	public void testQueryDemoById() {
		Demo demo=new Demo();
		demo.setUSER_NAME("aa");
		demo.setCREATE_TIME(new Date());
		demo.setPHONE_NO("123");
		demo.setSTAFF_TYPE("1");
		demoService.saveDemo(demo);
		Demo demo1=demoService.queryDemoById(demo.getID());
		assertEquals(demo1.getUSER_NAME(), "aa");
	}

	@Test
	public void testSaveDemo() {
		Demo demo=new Demo();
		demo.setUSER_NAME("aa");
		demo.setCREATE_TIME(new Date());
		demo.setPHONE_NO("123");
		demo.setSTAFF_TYPE("1");
		demoService.saveDemo(demo);
		assertNotNull(demo.getID());
	}

	@Test
	public void testUpdateDemo() {
		Demo demo=new Demo();
		demo.setUSER_NAME("aa");
		demo.setCREATE_TIME(new Date());
		demo.setPHONE_NO("123");
		demo.setSTAFF_TYPE("1");
		demoService.saveDemo(demo);
		demo.setUSER_NAME("bb");
		T9Result result=demoService.updateDemo(demo);
		Assert.assertTrue("", result.isSuccess());
	}

	@Test
	public void testDeleteDemo() {
		Demo demo=new Demo();
		demo.setUSER_NAME("aa");
		demo.setPHONE_NO("123");
		demo.setSTAFF_TYPE("1");
		demo.setCREATE_TIME(new Date());
		demoService.saveDemo(demo);
		T9Result result=demoService.deleteDemo(demo.getID());
		Assert.assertTrue("", result.isSuccess());
	}

}
