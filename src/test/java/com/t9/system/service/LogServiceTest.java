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

import com.t9.system.entity.Log;
import com.t9.system.web.T9Result;


@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:application-context.xml"})
@SuppressWarnings({"unchecked","rawtypes"})
public class LogServiceTest {

	@Autowired
	private LogService logService;
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testQueryList() {
		HashMap map=new HashMap();
		map.put("LOG_TIME_START", "2013-07-01");
		map.put("LOG_TIME_END", "2013-07-02");
		map.put("STAFF_NAME", "");
		logService.queryList(map);
		map.put("LOG_TIME_START", "2013-07-01 00:00:00");
		map.put("LOG_TIME_END", "2013-07-01 23:59:59");
		map.put("STAFF_NAME", "a");
		logService.queryList(map);
		map.put("LOG_TIME_START", "");
		map.put("LOG_TIME_END", "");
		map.put("STAFF_NAME", "a");
		logService.queryList(map);
	}

	@Test
	public void testQueryLogById() {
		Log log=new Log();
		log.setCLIENT_IP("127.0.0.1");
		log.setLOG_CONTENT("ceshi");
		log.setLOG_TIME(new Date());
		log.setLOG_TITLE("test");
		log.setURL("test");
		logService.saveLog(log);
		Log log1=logService.queryLogById(log.getID());
		assertEquals(log1.getCLIENT_IP(), "127.0.0.1");
	}

	@Test
	public void testSaveLog() {
		Log log=new Log();
		log.setCLIENT_IP("127.0.0.1");
		log.setLOG_CONTENT("ceshi");
		log.setLOG_TIME(new Date());
		log.setLOG_TITLE("test");
		log.setURL("test");
		logService.saveLog(log);
		assertNotNull(log.getID());
	}

	@Test
	public void testDeleteLog() {
		Log log=new Log();
		log.setCLIENT_IP("127.0.0.1");
		log.setLOG_CONTENT("ceshi");
		log.setLOG_TIME(new Date());
		log.setLOG_TITLE("test");
		log.setURL("test");
		logService.saveLog(log);
		T9Result result=logService.deleteLog(log.getID());
		Assert.assertTrue("", result.isSuccess());
	}

}
